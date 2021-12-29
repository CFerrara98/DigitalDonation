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
    public String feedbackDonatore(HttpServletRequest request){
        Boolean feedback = (Boolean)request.getSession().getAttribute("feedback");
        Long idSeduta = (Long) request.getSession().getAttribute("idSeduta");
        Donatore donatore = (Donatore)utenteService.getUtenteAutenticato();
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, feedback, idSeduta);
        } catch (CannotRelaseFeedbackException e) {
            return "ErrorePagina";
        }
        return "GUIGestioneUtente/Dashboarddonatore";
    }

    @RequestMapping(value = "/monitoraggioSeduta", method = RequestMethod.GET)
    public String monitoraggioSeduta(HttpServletRequest request){
        Utente utente = utenteService.getUtenteAutenticato();
        Long idSeduta = (Long) request.getSession().getAttribute("idSeduta");
        if(utente instanceof Operatore) {
            try {
                ArrayList<Object> lista = organizzazioneSeduteService.monitoraggioSeduta(idSeduta);
                request.getSession().setAttribute("lista", lista);
                return "GUIOrganizzazioneSedute/Monitoraggiosedute";
            } catch (CannotLoadDataRepositoryException e) {
                return "ErrorePagina";
            }
        }
        else
            return "ErrorePagina";
    }

    @RequestMapping(value = "/inserimentoGuest", method = RequestMethod.POST)
    public String inserimentoGuest(HttpServletRequest request, @ModelAttribute GuestForm guestForm, BindingResult result){
        Utente utente = utenteService.getUtenteAutenticato();
        Long idSeduta = (Long) request.getSession().getAttribute("idSeduta");
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
                return "ElencoSedute";
            } catch (CannotSaveDataRepositoryException e) {
                return "ErrorePagina";
            }
        }
        else
            return "ErrorePagina";

    }

   /* @RequestMapping(value = "/schedulazioneSeduta", method = RequestMethod.POST)
    public String schedulazioneSeduta(HttpServletRequest request, @ModelAttribute SedutaForm sedutaForm, BindingResult result){
        Utente utente = utenteService.getUtenteAutenticato();
        sedutaFormValidate.validate(sedutaForm, result);
        if(utente instanceof Operatore) {
            Operatore operatore = (Operatore) utente;
            String email = utente.getEmail();
            //SedeLocale sedeLocale = operatore.findByEmail(email);

            Seduta seduta = new Seduta();
            seduta.setDataFinePrenotazione(sedutaForm.getDataFinePrenotazione());
            seduta.setDate(sedutaForm.getDataSeduta());
            seduta.setDataInizioPrenotazione(sedutaForm.getDataInizioPrenotazione());
            seduta.setNumeroPartecipanti(0);


            try {
                //organizzazioneSeduteService.inserimentoGuest(idSeduta, guest);
                return "ElencoSedute";
            } catch (CannotSaveDataRepositoryException e) {
                return "ErrorePagina";
            }
        }
        else
            return "ErrorePagina";

    }*/
}
