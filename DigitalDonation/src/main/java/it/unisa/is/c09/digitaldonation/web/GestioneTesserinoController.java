package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotLoadDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import it.unisa.is.c09.digitaldonation.Utils.Forms.AutodichiarazioneIndisponibilitaForm;
import it.unisa.is.c09.digitaldonation.Utils.Forms.GuestForm;
import it.unisa.is.c09.digitaldonation.Utils.Forms.GuestFormValidate;
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
import java.util.List;
import java.util.logging.Logger;

@Controller
public class GestioneTesserinoController {

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
     * Metodo che permette al donatore di andare alla pagina dell'autodichiarazione di indisponibilità.
     *
     * @param request è la richiesta dalla sessione.
     * @param model   è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goAutodichiarazioneIndisponibilita", method = RequestMethod.GET)
    public String compilaAutodichiarazioneDiIndisponibilita(HttpServletRequest request, Model model){
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        AutodichiarazioneIndisponibilitaForm autodichiarazioneIndisponibilitaForm1 = new AutodichiarazioneIndisponibilitaForm();
        model.addAttribute("autodichiarazioneForm", autodichiarazioneIndisponibilitaForm1);

        return "GUIGestioneTesserinoDigitale/autodichiarazioneIndisponibilita";
    }
}
