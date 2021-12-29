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

    @RequestMapping(value = "/feedback", method = RequestMethod.POST)
    public String feedbackDonatore(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model){
        Boolean feedback = (Boolean) model.getAttribute("feedback");
        Long idSeduta = (Long) model.getAttribute("idSeduta");
        Donatore donatore = (Donatore) utenteService.getUtenteAutenticato();
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, feedback, idSeduta);
        } catch (CannotRelaseFeedbackException e) {
            return "GUIGestioneUtente/dashboardDonatore";
        }

        return "GUIGestioneUtente/dashboardDonatore";
    }

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

    @RequestMapping(value = "/visualizzaElencoSedute", method = RequestMethod.GET)
    public String visualizzaElencoSedute(HttpServletRequest request, Model model){
        List<Seduta> listaSedutePrenotabili = organizzazioneSeduteService.getListaSedutePrenotabili();
        model.addAttribute("listaSedutePrenotabili", listaSedutePrenotabili);
        Utente utente = (Utente) model.getAttribute("utente");
        if(utente instanceof Donatore){
            return "GUIOrganizzazioneSedute/seduteDisponibili";
        }
        else{
            return "GUIOrganizzazioneSedute/monitoraggioSedute";
        }
    }

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
                return "GUIOrganizzazioneSedute/monitoraggioSedute";
            } catch (CannotSaveDataRepositoryException e) {
                return "errorsPages/error503";
            }
        }
        else
            return "errorsPages/error401";

    }

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
}
