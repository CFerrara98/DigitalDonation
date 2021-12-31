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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrganizzazioneSeduteController {
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
    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public String feedbackDonatore(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model){
        Boolean feedback = (Boolean) model.getAttribute("feedback");
        Long idSeduta = (Long) model.getAttribute("idSeduta");
        Donatore donatore = (Donatore) request.getSession().getAttribute("utente");
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, feedback, idSeduta);
        } catch (CannotRelaseFeedbackException e) {
            return "GUIGestioneUtente/dashboardDonatore";
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
            return "errorsPages/error401";
    }

    /**
     * Metodo che permette all'operatore di poter visualizzare l'elenco delle sedute.
     * @param request è la richiesta dalla sessione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/visualizzaElencoSedute", method = RequestMethod.GET)
    public String visualizzaElencoSedute(HttpServletRequest request, Model model){
        // List<Seduta> listaSedutePrenotabili = organizzazioneSeduteService.getListaSedutePrenotabili();
        //model.addAttribute("listaSedutePrenotabili", listaSedutePrenotabili);
        Utente utente = (Utente) model.getAttribute("utente");
        if(utente instanceof Donatore){
            return "GUIOrganizzazioneSedute/seduteDisponibili";
        }
        else{
            return "GUIOrganizzazioneSedute/monitoraggioSedute";
        }
    }

    /**
     * Metodo che permette al donatore di poter visualizzare una seduta.
     * @param request è la richiesta dalla sessione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/visualizzaSeduta", method = RequestMethod.GET)
    public String visualizzaSeduta(HttpServletRequest request, Model model){
        Utente utente = (Utente) model.getAttribute("utente");
        if(utente instanceof Donatore) {
            Long idSeduta = (Long) request.getSession().getAttribute("idSeduta");
            List<Seduta> lista = (List<Seduta>) model.getAttribute("listaSedutePrenotabili");
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getIdSeduta() == idSeduta) {
                    model.addAttribute("sedutaScelta", lista.get(i));
                }
            }
            return "GUIOrganizzazioneSedute/partecipaSeduta";
        }

        else{
            return "errorsPages/error401";
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
        Utente utente = (Utente) model.getAttribute("utente");
        Long idSeduta = (Long) model.getAttribute("idSeduta");
        guestFormValidate.validate(guestForm, result);
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
                return "errorsPages/error503";
            }
        }
        else
            return "errorsPages/error401";

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
    public String schedulazioneSeduta(HttpServletRequest request, @ModelAttribute SedutaForm sedutaForm, RedirectAttributes redirectAttribute, BindingResult result, Model model){
        Utente utente = (Utente) model.getAttribute("utente");
        sedutaFormValidate.validate(sedutaForm, result);
        if(utente instanceof Operatore) {
            Operatore operatore = (Operatore) utente;
            SedeLocale sedeLocale = operatore.getSedeLocale();

            Seduta seduta = new Seduta();
            seduta.setDataFinePrenotazione(sedutaForm.getDataFinePrenotazione());
            seduta.setDataSeduta(sedutaForm.getDataSeduta());
            seduta.setDataInizioPrenotazione(sedutaForm.getDataInizioPrenotazione());
            seduta.setNumeroPartecipanti(0);
            seduta.setOraInizio(sedutaForm.getOrarioInizio());
            seduta.setOraFine(sedutaForm.getOrarioFine());
            seduta.setSedeLocale(sedeLocale);

            String luogo = Seduta.parseToLuogo(sedutaForm.getIndirizzo(), sedutaForm.getCitta(), sedutaForm.getCAP(), sedutaForm.getProvincia());
            seduta.setLuogo(luogo);
            try {
                organizzazioneSeduteService.schedulazioneSeduta(seduta);
            } catch (CannotSaveDataRepositoryException e) {
                return "errorsPages/error503";
            }
            return "GUIGestioneUtente/dashboardOperatore";
        }
        else
            return "errorsPages/error401";

    }

    /**
     * Metodo che permette di andare alla pagina dell'elenco dei partecipanti.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina.
     */
    @RequestMapping(value ="/elencoPartecipanti", method = RequestMethod.GET)
    public String elencoPartecipanti(Model model) {
        return "GUIOrganizzazioneSedute/elencoPartecipanti";
    }

    /**
     * Metodo che permette di andare alla pagina di inserimento utente guest.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/inserimentoUtenteGuest", method = RequestMethod.GET)
    public String inserimentoUtenteGuest(Model model) {
        return "GUIOrganizzazioneSedute/inserimentoUtenteGuest";
    }

    /**
     * Metodo che permette di andare alla pagina dell'elenco delle sedute.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina.
     */
    @RequestMapping(value ="/monitoraggioSedute", method = RequestMethod.GET)
    public String monitoraggioSedute(Model model) {
        return "GUIOrganizzazioneSedute/monitoraggioSedute";
    }

    /**
     * Metodo che permette di andare alla pagina di feedback della seduta.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/partecipaSeduta", method = RequestMethod.GET)
    public String partecipaSeduta(Model model) {
        return "GUIOrganizzazioneSedute/partecipaSeduta";
    }

    /**
     * Metodo che permette di andare alla pagina di schedulazione di una nuova seduta.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/schedulazioneSeduta", method = RequestMethod.GET)
    public String schedulazioneSeduta(Model model) {
        return "GUIOrganizzazioneSedute/schedulazioneSeduta";
    }

    /**
     * Metodo che permette di andare alla pagina dell'elenco dei partecipanti.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina delle sedute disponibile.
     */
    @RequestMapping(value ="/seduteDisponibili", method = RequestMethod.GET)
    public String seduteDisponibili(Model model) {
        return "GUIOrganizzazioneSedute/seduteDisponibili";
    }
}
