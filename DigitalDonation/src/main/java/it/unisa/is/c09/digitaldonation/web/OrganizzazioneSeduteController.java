package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotRelaseFeedbackException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotLoadDataRepositoryException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotUpdateDataRepositoryException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotDeleteDataRepositoryException;
import it.unisa.is.c09.digitaldonation.gestionetesserinomanagement.GestioneTesserinoService;
import it.unisa.is.c09.digitaldonation.model.entity.Utente;
import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Seduta;
import it.unisa.is.c09.digitaldonation.model.entity.Operatore;
import it.unisa.is.c09.digitaldonation.model.entity.Tesserino;


import it.unisa.is.c09.digitaldonation.model.repository.SedutaRepository;
import it.unisa.is.c09.digitaldonation.organizzazionesedutemanagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.utentemanagement.UtenteService;
import it.unisa.is.c09.digitaldonation.utils.form.GuestForm;
import it.unisa.is.c09.digitaldonation.utils.form.GuestFormValidate;
import it.unisa.is.c09.digitaldonation.utils.form.SedutaForm;
import it.unisa.is.c09.digitaldonation.utils.form.SedutaFormValidate;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;




/**
 * Controller per l'organizzazione delle sedute.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 */
@Controller
public class OrganizzazioneSeduteController {

  @Autowired
  OrganizzazioneSeduteService organizzazioneSeduteService;
  @Autowired
  GestioneTesserinoService gestioneTesserinoService;
  @Autowired
  UtenteService utenteService;
  @Autowired
  GuestFormValidate guestFormValidate;
  @Autowired
  SedutaFormValidate sedutaFormValidate;
  @Autowired
  SedutaRepository sedutaRepository;

  /**
   * Metodo che permette al donatore di poter inviare un feedback.
   *
   * @param request           è la richiesta dalla sessione.
   * @param redirectAttribute è l'attributo di ridirezione.
   * @param model             è l'oggetto Model.
   * @return String ridirezione ad una pagina.
   */
  @RequestMapping(value = "/feedback", method = RequestMethod.GET)
  public String feedbackDonatore(HttpServletRequest request, RedirectAttributes redirectAttribute,
                  Model model, @RequestParam(name = "feedbackSeduta") String feedbackSeduta,
                                 @RequestParam(name = "idSeduta") Long idSeduta) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (!(utente instanceof Donatore) || utente == null) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
    if (feedbackSeduta.equals("positivo")) {
      try {
        organizzazioneSeduteService.feedbackDonatore((Donatore) utente, idSeduta);
        model.addAttribute("success", "Ti sei prenotato alla seduta, controlla la tua email.");
        Donatore donatore = (Donatore) utente;
        try {
          Tesserino tesserino = gestioneTesserinoService.aggiornaTesserino(donatore);
          model.addAttribute("tesserino", tesserino);
        } catch (CannotSaveDataRepositoryException e) {
          e.printStackTrace();
          return "GUIGestioneUtente/dashboardDonatore";
        }
        return "GUIGestioneUtente/dashboardDonatore";
      } catch (CannotRelaseFeedbackException e) {
        return "GUIGestioneUtente/dashboardDonatore";
      }
    }
    return "redirect:/dashboardDonatore";
  }

  /**
   * Metodo che permette all'operatore di poter visualizzare una seduta con
   * l'elenco dei partecipanti.
   *
   * @param request           è la richiesta dalla sessione.
   * @param redirectAttribute è l'attributo di ridirezione.
   * @param model             è l'oggetto Model.
   * @return String ridirezione ad una pagina.
   */
  @RequestMapping(value = "/monitoraggioSeduta", method = RequestMethod.GET)
  public String monitoraggioSeduta(HttpServletRequest request,
                                   RedirectAttributes redirectAttribute, Model model) {
    Utente utente = (Utente) model.getAttribute("utente");
    Long idSeduta = (Long) model.getAttribute("idSeduta");
    request.getSession().setAttribute("idSeduta", idSeduta);
    if (utente instanceof Operatore) {
      try {
        ArrayList<Object> listaPartecipanti =
                organizzazioneSeduteService.monitoraggioSeduta(idSeduta);
        model.addAttribute("listaPartecipanti", listaPartecipanti);
        List<Seduta> listaSedute = (List<Seduta>) model.getAttribute("listaSedutePrenotabili");
        for (int i = 0; i < listaSedute.size(); i++) {
          if (listaSedute.get(i).getIdSeduta() == idSeduta) {
            model.addAttribute("sedutaScelta", listaSedute.get(i));
          }
        }
        return "GUIOrganizzazioneSedute/elencoPartecipanti";
      } catch (CannotLoadDataRepositoryException e) {
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
        return "redirect:/error";
      }
    }
    request.getSession().setAttribute("codiceErrore", 401);
    return "redirect:/error";
  }

  /**
   * Metodo che permette all'operatore di poter visualizzare l'elenco delle sedute.
   *
   * @param request è la richiesta dalla sessione.
   * @param model   è l'oggetto Model.
   * @return String ridirezione ad una pagina.
   */
  @RequestMapping(value = "/visualizzaElencoSedute", method = RequestMethod.GET)
  public String visualizzaElencoSedute(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente instanceof Operatore) {
      try {
        List<Seduta> lista = organizzazioneSeduteService.visualizzaElencoSedute();
        model.addAttribute("listaSedute", lista);
      } catch (CannotLoadDataRepositoryException e) {
        e.printStackTrace();
      }
    } else {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
    return "GUIOrganizzazioneSedute/monitoraggioSedute";
  }

  /**
   * Metodo che permette di andare alla pagina dell'elenco dei partecipanti.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina delle sedute disponibile.
   */
  @RequestMapping(value = "/goSeduteDisponibili", method = RequestMethod.GET)
  public String seduteDisponibili(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");

    if (!(utente instanceof Donatore) || utente == null) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
    try {
      List<Seduta> lista = organizzazioneSeduteService
              .visualizzaElencoSeduteDisponibili(utente.getCodiceFiscale());
      model.addAttribute("listaSedutePrenotabili", lista);
    } catch (CannotLoadDataRepositoryException e) {
      e.printStackTrace();
    }
    return "GUIOrganizzazioneSedute/seduteDisponibili";
  }

  /**
   * Metodo che permette al donatore di poter visualizzare una seduta.
   *
   * @param request è la richiesta dalla sessione.
   * @param model   è l'oggetto Model.
   * @return String ridirezione ad una pagina.
   */
  @RequestMapping(value = "/visualizzaSeduta", method = RequestMethod.GET)
  public String visualizzaSeduta(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (!(utente instanceof Donatore) || utente == null) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
    Long idSeduta = (Long) model.getAttribute("idSeduta");
    List<Seduta> lista = (List<Seduta>) model.getAttribute("listaSedutePrenotabili");
    for (int i = 0; i < lista.size(); i++) {
      if (lista.get(i).getIdSeduta() == idSeduta) {
        model.addAttribute("sedutaScelta", lista.get(i));
      }
    }
    return "GUIOrganizzazioneSedute/partecipaSeduta";
  }

  /**
   * Metodo che permette di andare alla pagina dell'elenco dei partecipanti.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina.
   */
  @RequestMapping(value = "/goElencoPartecipanti", method = RequestMethod.GET)
  public String elencoPartecipanti(Model model, HttpServletRequest request) {

    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }

    long idSeduta = Long.valueOf(request.getParameter("idSeduta"));
    String successo = request.getParameter("successo");
    model.addAttribute("idSeduta", idSeduta);

    Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
    model.addAttribute("Seduta", seduta);
    request.getSession().setAttribute("idSeduta", idSeduta);
    try {
      ArrayList<Object> list = organizzazioneSeduteService.monitoraggioSeduta(idSeduta);
      model.addAttribute("listaUtenti", list);
      //Array list per le instanza della lista 1/true = Donatore 0/false= Guest
      ArrayList<Boolean> partecipanti = new ArrayList<>();
      if (list.size() > 0) {

        for (Object o : list) {
          if (o instanceof Donatore) {
            partecipanti.add(true);
          } else {
            partecipanti.add(false);
          }
        }
      } else {
        partecipanti.add(0, null);
      }
      model.addAttribute("success", successo);
      model.addAttribute("listaPartecipanti", partecipanti);
    } catch (CannotLoadDataRepositoryException e) {
      e.printStackTrace();
    }

    return "GUIOrganizzazioneSedute/elencoPartecipanti";
  }


  /**
   * Metodo che permette di andare alla pagina dell'elenco delle sedute.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina.
   */
  @RequestMapping(value = "/goMonitoraggioSedute", method = RequestMethod.GET)
  public String monitoraggioSedute(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
    return "GUIOrganizzazioneSedute/monitoraggioSedute";
  }

  /**
   * Metodo che permette di andare alla pagina di feedback della seduta.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina delle sedute disponibile.
   */
  @RequestMapping(value = "/goPartecipaSeduta", method = RequestMethod.GET)
  public String partecipaSeduta(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Operatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }

    Long idSeduta = Long.valueOf(request.getParameter("idSeduta"));
    model.addAttribute("idSeduta", idSeduta);

    try {
      Seduta seduta = organizzazioneSeduteService.visualizzaSeduta(idSeduta);
      model.addAttribute("seduta", seduta);
      return "GUIOrganizzazioneSedute/partecipaSeduta";

    } catch (CannotLoadDataRepositoryException e) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
  }

  /**
   * Metodo che permette di andare alla pagina di schedulazione di una nuova seduta.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina delle sedute disponibile.
   */
  @RequestMapping(value = "/goSchedulazioneSeduta", method = RequestMethod.GET)
  public String schedulazioneSeduta(HttpServletRequest request, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }

    if (model.getAttribute("sedutaForm") != null) {
      return "GUIOrganizzazioneSedute/schedulazioneSeduta";
    }
    SedutaForm sedutaForm = new SedutaForm();
    model.addAttribute("sedutaForm", sedutaForm);
    return "GUIOrganizzazioneSedute/schedulazioneSeduta";
  }

  /**
   * Metodo che permette di andare alla pagina di modifica seduta.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina delle sedute disponibile.
   */
  @RequestMapping(value = "/goModificaSeduta", method = RequestMethod.GET)
  public String goModificaSeduta(HttpServletRequest request,
                 @RequestParam(name = "idSeduta") Long idSeduta, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }

    if (model.getAttribute("sedutaForm") != null) {
      return "GUIOrganizzazioneSedute/modificaSeduta";
    }
    SedutaForm sedutaForm = new SedutaForm();
    request.getSession().setAttribute("idSeduta", idSeduta);
    model.addAttribute("sedutaForm", sedutaForm);
    return "GUIOrganizzazioneSedute/modificaSeduta";
  }

  /**
   * Metodo che permette di andare alla pagina di inserimento utente guest.
   *
   * @param model è l'oggetto model.
   * @return String ridirezione alla pagina delle sedute disponibile.
   */
  @RequestMapping(value = "/goInserimentoUtenteGuest", method = RequestMethod.GET)
  public String inserimentoUtenteGuest(HttpServletRequest request, @ModelAttribute("guestForm")
          GuestForm guestForm, BindingResult result, RedirectAttributes redirectAttribute,
                                       Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }

    long idSeduta = Long.valueOf(request.getParameter("idSeduta"));
    model.addAttribute("idSeduta", idSeduta);
    return "GUIOrganizzazioneSedute/inserimentoUtenteGuest";
  }

  /**
   * Metodo che permette all'operatore di poter inserire un utente guest all'interno di una seduta.
   *
   * @param request           è la richiesta dalla sessione.
   * @param guestForm         è l'oggetto form dell'utente guest.
   * @param redirectAttribute è l'attributo di ridirezione.
   * @param model             è l'oggetto Model.
   * @param result            è la variabile di binding.
   * @return String ridirezione ad una pagina.
   */
  @RequestMapping(value = "/inserimentoGuest", method = RequestMethod.POST)
  public String inserimentoGuest(HttpServletRequest request, @ModelAttribute GuestForm guestForm,
               BindingResult result, RedirectAttributes redirectAttribute, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }

    long idSeduta = Long.valueOf(request.getParameter("idSeduta"));
    guestFormValidate.validate(guestForm, result);
    if (result.hasErrors()) {
      redirectAttribute.addFlashAttribute("guestForm", guestForm);
      for (ObjectError x : result.getGlobalErrors()) {
        redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
      }
      return "redirect:/goInserimentoUtenteGuest?idSeduta=" + idSeduta;
    }

    Guest guest = new Guest();
    guest.setNome(guestForm.getNome());
    guest.setCognome(guestForm.getCognome());
    guest.setcodiceFiscaleGueste(guestForm.getCodiceFiscale());
    guest.setPatologie(guestForm.getPatologie());
    guest.setTelefono(guestForm.getTelefono());
    guest.setGruppoSanguigno(guestForm.getGruppoSanguigno());
    try {
      organizzazioneSeduteService.inserimentoGuest(idSeduta, guest);
      return "redirect:/goElencoPartecipanti?idSeduta=" + idSeduta + "&successo="
              + "Utente guest inserito correttamente";
    } catch (CannotSaveDataRepositoryException e) {
      redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
      return "redirect:/goInserimentoUtenteGuest?idSeduta=" + idSeduta;
    }
  }

  /**
   * Metodo che permette all'operatore di poter creare una nuova seduta.
   *
   * @param request           è la richiesta dalla sessione.
   * @param sedutaForm        è l'oggetto form della seduta.
   * @param redirectAttribute è l'attributo di ridirezione.
   * @param model             è l'oggetto Model.
   * @return String ridirezione ad una pagina.
   */

  @RequestMapping(value = "/schedulazioneSeduta", method = RequestMethod.POST)
  public String schedulazioneSeduta(HttpServletRequest request,
                @ModelAttribute SedutaForm sedutaForm,
                RedirectAttributes redirectAttribute, BindingResult result, Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
    LocalTime oraInizio = LocalTime.of(8, 0);
    sedutaForm.setOrarioInizio(oraInizio);

    LocalTime oraFine = LocalTime.of(12, 0);
    sedutaForm.setOrarioFine(oraFine);

    try {
      sedutaFormValidate.validate(sedutaForm, result);
      if (result.hasErrors()) {
        // se ci sono errori il metodo controller setta tutti i parametri
        redirectAttribute.addFlashAttribute("sedutaForm", sedutaForm);
        for (ObjectError x : result.getGlobalErrors()) {
          redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
        }
        return "redirect:/goSchedulazioneSeduta";
      }
      DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
      Operatore operatore = (Operatore) utente;
      SedeLocale sedeLocale = operatore.getSedeLocale();

      Seduta seduta = new Seduta();
      seduta.setDataFinePrenotazione(sedutaForm.getDataFinePrenotazione());
      seduta.setDataSeduta(sedutaForm.getDataSeduta());
      seduta.setDataInizioPrenotazione(sedutaForm.getDataInizioPrenotazione());
      seduta.setNumeroPartecipanti(sedutaForm.getNumeroPartecipanti());
      seduta.setOraInizio(Time.valueOf(sedutaForm.getOrarioInizio()));
      seduta.setOraFine(Time.valueOf(sedutaForm.getOrarioFine()));
      seduta.setSedeLocale(sedeLocale.getCodiceIdentificativo());
      String luogo = Seduta.parseToLuogo(sedutaForm.getIndirizzo(), sedutaForm.getCitta(),
              sedutaForm.getCap(), sedutaForm.getProvincia());
      seduta.setLuogo(luogo);
      organizzazioneSeduteService.schedulazioneSeduta(seduta);
    } catch (CannotSaveDataRepositoryException e) {
      redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
    }
    model.addAttribute("success", "Seduta schedulata con successo!");
    return "GUIGestioneUtente/dashboardOperatore";
  }

  /**
   * Metodo che permette all'operatore di poter modificare una seduta.
   *
   * @param request           è la richiesta dalla sessione.
   * @param sedutaForm        è l'oggetto form della seduta.
   * @param redirectAttribute è l'attributo di ridirezione.
   * @param result            è la variabile di binding.
   * @param model             è l'oggetto Model.
   * @return String ridirezione ad una pagina.
   */
  @RequestMapping(value = "/modificaSeduta", method = RequestMethod.POST)
  public String modificaSeduta(HttpServletRequest request, @ModelAttribute SedutaForm sedutaForm,
                 RedirectAttributes redirectAttribute, BindingResult result, Model model) {

    Utente utente = (Utente) request.getSession().getAttribute("utente");
    Long idSeduta = (Long) request.getSession().getAttribute("idSeduta");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }
    if (idSeduta == null) {
      request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
      return "redirect:/error";
    }
    sedutaFormValidate.validate(sedutaForm, result);
    if (result.hasErrors()) {
      // se ci sono errori il metodo controller setta tutti i parametri
      redirectAttribute.addFlashAttribute("sedutaForm", sedutaForm);
      for (ObjectError x : result.getGlobalErrors()) {
        redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
      }
      return "redirect:/goModificaSeduta";
    }
    try {
      organizzazioneSeduteService.modificaSeduta(sedutaForm, idSeduta, utente);
    } catch (CannotUpdateDataRepositoryException e) {
      redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
    }
    model.addAttribute("success", "Seduta modificata con successo");
    return "GUIGestioneUtente/dashboardOperatore";
  }

  /**
   * Metodo che permette all'operatore di poter eliminare una seduta.
   *
   * @param request           è la richiesta dalla sessione.
   * @param redirectAttribute è l'attributo di ridirezione.
   * @param model             è l'oggetto Model.
   * @return String ridirezione ad una pagina.
   */
  @RequestMapping(value = "/goEliminaSeduta", method = RequestMethod.GET)
  public String goEliminaSeduta(HttpServletRequest request, RedirectAttributes redirectAttribute,
                                Model model) {
    Utente utente = (Utente) request.getSession().getAttribute("utente");
    if (utente == null || utente instanceof Donatore) {
      request.getSession().setAttribute("codiceErrore", 401);
      return "redirect:/error";
    }

    long idSeduta = Long.valueOf(request.getParameter("idSeduta"));
    try {
      organizzazioneSeduteService.eliminaSeduta(idSeduta);
    } catch (CannotDeleteDataRepositoryException e) {
      redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
    }
    model.addAttribute("success", "Seduta eliminata con successo");
    return "GUIGestioneUtente/dashboardOperatore";
  }
}
