package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotLoadDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement.GestioneTesserinoService;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import it.unisa.is.c09.digitaldonation.Utils.Forms.*;
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
import java.util.List;
import java.util.logging.Logger;

@Controller
public class GestioneTesserinoController {

    private static Logger logger = Logger.getLogger(String.valueOf(OrganizzazioneSeduteController.class));

    @Autowired
    GestioneTesserinoService gestioneTesserinoService;
    @Autowired
    AutodichiarazioneIndisponibilitaFormValidate autodichiarazioneFormValidate;
    @Autowired
    OrganizzazioneSeduteService organizzazioneSeduteService;
    @Autowired
    UtenteService utenteService;
    @Autowired
    GuestFormValidate guestFormValidate;
    @Autowired
    SedutaFormValidate sedutaFormValidate;

    @RequestMapping(value = "/autodichiarazioneIndisponibilita", method = RequestMethod.POST)
    public String autodichiarazioneDiIndisponibilita(HttpServletRequest request, @ModelAttribute AutodichiarazioneIndisponibilitaForm autodichiarazioneForm,
                  BindingResult result, RedirectAttributes redirectAttribute, Model model){
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        autodichiarazioneFormValidate.validate(autodichiarazioneForm, result);
        if(result.hasErrors()){
            redirectAttribute.addFlashAttribute("autodichiarazioneForm", autodichiarazioneForm);
            for (ObjectError x : result.getGlobalErrors()) {
                redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
            }
            return "redirect:/goAutodichiarazioneIndisponibilita";
        }
        if(utente instanceof Donatore){
            Indisponibilita indisponibilita = new Indisponibilita();
            indisponibilita.setCodiceFiscaleDonatore(utente.getCodiceFiscale());
            indisponibilita.setDataProssimaDisponibilita(autodichiarazioneForm.getDataProssimaDisponibilita());
            indisponibilita.setMotivazioni(autodichiarazioneForm.getMotivazioni());

            try {
                gestioneTesserinoService.autodichiarazioneIndisponibilita(indisponibilita);
            } catch (CannotSaveDataRepositoryException e) {
                redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
                return "redirect:/goAutodichiarazioneIndisponibilita";
            }
        }
        else{
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/error";
        }
        model.addAttribute("success", "Autodichiarazione compilata con successo");
        return "GUIGestioneUtente/dashboardDonatore";
    }

    /**
     * Metodo che permette al donatore di andare alla pagina dell'autodichiarazione di indisponibilità.
     *
     * @param request è la richiesta dalla sessione.
     * @param model   è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goAutodichiarazioneIndisponibilita", method = RequestMethod.GET)
    public String goAutodichiarazioneDiIndisponibilita(HttpServletRequest request, Model model){
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        AutodichiarazioneIndisponibilitaForm autodichiarazioneIndisponibilitaForm = new AutodichiarazioneIndisponibilitaForm();
        model.addAttribute("autodichiarazioneForm", autodichiarazioneIndisponibilitaForm);
        return "GUIGestioneTesserinoDigitale/autodichiarazioneIndisponibilita";
    }

    @RequestMapping(value = "/goTesserino", method = RequestMethod.GET)
    public String goVisualizzaTesserino(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {
        return "GUIOrganizzazioneSedute/visualizzaTesserino";
    }

    @RequestMapping(value = "/goCreazioneTesserino", method = RequestMethod.GET)
    public String gocreazioneTesserino(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model){

        return "GUIOrganizzazioneSedute/creazioneTesserino";
    }
}
