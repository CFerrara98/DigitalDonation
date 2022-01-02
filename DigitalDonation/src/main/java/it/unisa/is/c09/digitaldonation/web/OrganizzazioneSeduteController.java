package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotLoadDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotRelaseFeedbackException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import it.unisa.is.c09.digitaldonation.Utils.Forms.GuestForm;
import it.unisa.is.c09.digitaldonation.Utils.Forms.GuestFormValidate;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaFormValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class OrganizzazioneSeduteController {

    private static Logger logger = Logger.getLogger(String.valueOf(OrganizzazioneSeduteController.class));

    @Autowired
    OrganizzazioneSeduteService organizzazioneSeduteService;
    @Autowired
    UtenteService utenteService;
    @Autowired
    GuestFormValidate guestFormValidate;
    @Autowired
    SedutaFormValidate sedutaFormValidate;

    /**
     * Metodo che permette al donatore di poter inviare un feedback.
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/feedback", method = RequestMethod.GET)
    public String feedbackDonatore(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {
        Boolean feedback;
        Long idSeduta;
        try{
            feedback = Boolean.parseBoolean((String) request.getParameter("feedback"));
            idSeduta = Long.valueOf(((String) request.getParameter("" +
                    "")));
        }catch (Exception e){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        Donatore utente = (Donatore) request.getSession().getAttribute("utente");
        logger.info("Inserimento feedback. Id seduta: "+idSeduta+
                " feedback: "+feedback + " utente: "+utente.toString());
        if (utente != null && utente instanceof Donatore && feedback == true) {
            try {
                organizzazioneSeduteService.feedbackDonatore(utente, feedback, idSeduta);
            } catch (CannotRelaseFeedbackException e) {
                return "GUIGestioneUtente/dashboardDonatore";
            }
        }
        return "GUIGestioneUtente/dashboardDonatore";
    }

    /**
     * Metodo che permette all'operatore di poter visualizzare una seduta con l'elenco dei partecipanti.
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/monitoraggioSeduta", method = RequestMethod.GET)
    public String monitoraggioSeduta(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model){
        Utente utente = (Utente) model.getAttribute("utente");
        Long idSeduta = (Long) model.getAttribute("idSeduta");
        if(utente instanceof Operatore) {
            try {
                ArrayList<Object> listaPartecipanti = organizzazioneSeduteService.monitoraggioSeduta(idSeduta);
                model.addAttribute("listaPartecipanti", listaPartecipanti);
                List<Seduta> listaSedute = (List<Seduta>) model.getAttribute("listaSedutePrenotabili");
                for (int i=0; i < listaSedute.size(); i++) {
                    if (listaSedute.get(i).getIdSeduta() == idSeduta) {
                        model.addAttribute("sedutaScelta", listaSedute.get(i));
                    }
                }
                return "GUIOrganizzazioneSedute/elencoPartecipanti";
            } catch (CannotLoadDataRepositoryException e) {
                return "errorsPages/error503";
            }
        }
        else
            return "redirect:/";
    }

    /**
     * Metodo che permette all'operatore di poter visualizzare l'elenco delle sedute.
     * @param request è la richiesta dalla sessione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/visualizzaElencoSedute", method = RequestMethod.GET)
    public String visualizzaElencoSedute(HttpServletRequest request, Model model){
        return "GUIOrganizzazioneSedute/monitoraggioSedute";
    }

    /**
     * Metodo che permette di andare alla pagina dell'elenco dei partecipanti.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/goSeduteDisponibili", method = RequestMethod.GET)
    public String seduteDisponibili(Model model) {
        return "GUIOrganizzazioneSedute/seduteDisponibili";
    }

    /**
     * Metodo che permette al donatore di poter visualizzare una seduta.
     * @param request è la richiesta dalla sessione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/visualizzaSeduta", method = RequestMethod.GET)
    public String visualizzaSeduta(HttpServletRequest request, Model model){
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(utente instanceof Donatore) {
            Long idSeduta = (Long) model.getAttribute("idSeduta");
            List<Seduta> lista = (List<Seduta>) model.getAttribute("listaSedutePrenotabili");
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getIdSeduta() == idSeduta) {
                    model.addAttribute("sedutaScelta", lista.get(i));
                }
            }
            return "GUIOrganizzazioneSedute/partecipaSeduta";
        }

        else{
            return "redirect:/\"";
        }
    }

    /**
     * Metodo che permette all'operatore di poter inserire un utente guest all'interno di una seduta.
     * @param request è la richiesta dalla sessione.
     * @param guestForm è l'oggetto form dell'utente guest.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @param result è la variabile di binding.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/inserimentoGuest", method = RequestMethod.POST)
    public String inserimentoGuest(HttpServletRequest request, @ModelAttribute GuestForm guestForm, BindingResult result, RedirectAttributes redirectAttribute, Model model){
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        guestFormValidate.validate(guestForm, result);
        Long idSeduta = (Long) model.getAttribute("idSeduta");
        System.out.println(guestForm.toString());
        if (result.hasErrors()) {
            // se ci sono errori il metodo controller setta tutti i parametri
            redirectAttribute.addFlashAttribute("guestForm", guestForm);
            for (ObjectError x : result.getGlobalErrors()) {
                redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
                System.out.println(x.getCode());
            }
            return "redirect:/goInserimentoUtenteGuest";
        }
        if(utente instanceof Operatore) {
            Guest guest = new Guest();
            guest.setNome(guestForm.getNome());
            guest.setCognome(guestForm.getCognome());
            guest.setcodiceFiscaleGueste(guestForm.getCodiceFiscale());
            guest.setPatologie(guestForm.getPatologie());
            guest.setTelefono(guestForm.getTelefono());
            guest.setGruppoSanguigno(guestForm.getGruppoSanguigno());
            try {
                organizzazioneSeduteService.inserimentoGuest(idSeduta, guest);
                return "redirect:/monitoraggioSeduta";
            } catch (CannotSaveDataRepositoryException e) {
                return "redirect:/goInserimentoUtenteGuest";
            }
        }
        else
            return "redirect:/";

    }

    /**
     * Metodo che permette all'operatore di poter creare una nuova seduta.
     * @param request è la richiesta dalla sessione.
     * @param sedutaForm è l'oggetto form della seduta.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/schedulazioneSeduta", method = RequestMethod.POST)
    public String schedulazioneSeduta(HttpServletRequest request, @ModelAttribute SedutaForm sedutaForm, RedirectAttributes redirectAttribute, BindingResult result, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        //String dataSeduta = organizzazioneSeduteService.parsDateToString(sedutaForm.getDataSeduta());
        //String dataInizio = organizzazioneSeduteService.parsDateToString(sedutaForm.getDataInizioPrenotazione());
        //String dataFine = organizzazioneSeduteService.parsDateToString(sedutaForm.getDataFinePrenotazione());
        try {
//            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
//            Date dataSedutaD = (Date) formatter.parse(dataSeduta);
//            Date dataInizioD = (Date) formatter.parse(dataInizio);
//            Date dataFineD = (Date) formatter.parse(dataFine);
//            sedutaForm.setDataSeduta(dataSedutaD);
//            sedutaForm.setDataInizioPrenotazione(dataInizioD);
//            sedutaForm.setDataFinePrenotazione(dataFineD);
//            System.out.println(sedutaForm.toString());
            sedutaFormValidate.validate(sedutaForm, result);
            if (result.hasErrors()) {
                // se ci sono errori il metodo controller setta tutti i parametri
                redirectAttribute.addFlashAttribute("sedutaForm", sedutaForm);
                for (ObjectError x : result.getGlobalErrors()) {
                    redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
                    logger.info("Prova" + x.getCode().toString());
                }
                return "redirect:/goSchedulazioneSeduta";
            }

            if (utente instanceof Operatore) {
                DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
//                Date data1 = (Date)formatter2.parse(dataSeduta);
//                Date data2 = (Date)formatter2.parse(dataInizio);
//                Date data3 = (Date)formatter2.parse(dataFine);

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
                String luogo = Seduta.parseToLuogo(sedutaForm.getIndirizzo(), sedutaForm.getCitta(), sedutaForm.getCAP(), sedutaForm.getProvincia());
                seduta.setLuogo(luogo);
                try {
                    organizzazioneSeduteService.schedulazioneSeduta(seduta);
                    return "GUIGestioneUtente/dashboardOperatore";
                } catch (CannotSaveDataRepositoryException e) {
                    //TODO SET ERROR CODE PROBLEMI COL SERVER
                    return "redirect:/error";
                }
            } else
                //TODO SET ERROR CODE NOT AUTORIZATE http status
                return "redirect:/error";

        }catch (Exception e){
            //TODO da controllare
            return "redirect:/error";
        }
    }

    /**
     * Metodo che permette di andare alla pagina dell'elenco dei partecipanti.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina.
     */
    @RequestMapping(value ="/goElencoPartecipanti", method = RequestMethod.GET)
    public String elencoPartecipanti(Model model) {
        return "GUIOrganizzazioneSedute/elencoPartecipanti";
    }

    /**
     * Metodo che permette di andare alla pagina di inserimento utente guest.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/goInserimentoUtenteGuest", method = RequestMethod.GET)
    public String inserimentoUtenteGuest(Model model) {
       //lo abbiamo messo provvisoriamente per provare il guestform nella jsp "inserimentoUtenteGuest"
        GuestForm guestForm = new GuestForm();
        model.addAttribute("guestForm", guestForm);

        return "GUIOrganizzazioneSedute/inserimentoUtenteGuest";
    }

    /**
     * Metodo che permette di andare alla pagina dell'elenco delle sedute.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina.
     */
    @RequestMapping(value ="/goMonitoraggioSedute", method = RequestMethod.GET)
    public String monitoraggioSedute(Model model) {
        return "GUIOrganizzazioneSedute/monitoraggioSedute";
    }

    /**
     * Metodo che permette di andare alla pagina di feedback della seduta.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/goPartecipaSeduta", method = RequestMethod.GET)
    public String partecipaSeduta(Model model) {
        return "GUIOrganizzazioneSedute/partecipaSeduta";
    }

    /**
     * Metodo che permette di andare alla pagina di schedulazione di una nuova seduta.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/goSchedulazioneSeduta", method = RequestMethod.GET)
    public String schedulazioneSeduta(Model model) {
        if(model.getAttribute("sedutaForm") != null) {
            return "GUIOrganizzazioneSedute/schedulazioneSeduta";
        }
        SedutaForm sedutaForm = new SedutaForm();
        model.addAttribute("sedutaForm", sedutaForm);
        return "GUIOrganizzazioneSedute/schedulazioneSeduta";
    }
}
