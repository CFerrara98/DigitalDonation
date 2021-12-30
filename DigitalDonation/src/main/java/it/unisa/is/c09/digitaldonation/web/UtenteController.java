package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import it.unisa.is.c09.digitaldonation.Utils.Forms.LoginForm;
import it.unisa.is.c09.digitaldonation.Utils.Forms.LoginFormValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
public class UtenteController {

    @Autowired
    UtenteService utenteService;
    @Autowired
    LoginFormValidate loginFormValidator;

    /**
     * Metodo per la visualizzazione dell'homepage o della dashboard
     *
     * @param model è l'oggetto Model.
     * @param session è la sessione dell'utente.
     * @return String stringa che rappresenta la pagina da visualizzare.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String visualizzaHome(HttpSession session, Model model) {
        session.invalidate();
        return "GUIGestioneUtente/homepage";
    }

    /**
     * Questo metodo permette di effettuare il login ad un utente registrate.
     * @param request è la richiesta inviata dall'utente.
     * @param loginForm è il form compilato dall'utente.
     * @param result sono i campi del form.
     * @param redirectAttribute è un attributo che permette di cambiare pagina.
     * @param model è l'oggetto Model.
     * @return String che ridireziona ad una pagina.
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request,@ModelAttribute("loginForm") LoginForm loginForm,
                        BindingResult result, RedirectAttributes redirectAttribute, Model model) {

        Utente utente = null;
        loginFormValidator.validate(loginForm, result);
        if (result.hasErrors()) {
            System.out.println("result.hasErrors()");
            // se ci sono errori il metodo controller setta tutti i parametri
            redirectAttribute.addFlashAttribute("EmailError", "Email o password errati, per favore riprova");
            return "redirect:/goLogin";
        }

        try {
            utente = utenteService.login(loginForm.getEmail(), loginForm.getPassword());
        } catch (UserNotLoggedException e) {
            System.out.println("provo a ricaricare la mail");
            redirectAttribute.addFlashAttribute("EmailPrecedente", loginForm.getEmail());
            redirectAttribute.addFlashAttribute("PasswordError", "Email o password errati, per favore riprova");
            return "redirect:/goLogin";
        }

        //Se è un operatore
        if (utente instanceof Operatore) {
            request.getSession().setAttribute("utente", utente);
            return "GUIGestioneUtente/dashboardOperatore";
        }
        //Se è un donatore
        else if (utente instanceof Donatore) {
            request.getSession().setAttribute("utente", utente);
            return "GUIGestioneUtente/dashboardDonatore";
        }
        else {
            return "redirect:/goLogin";
        }
    }

    /**
     * Metodo che permette ad un utente di effettuare il logout
     * @param request è la richiesta inviata dall'utente.
     * @return String che ridireziona ad una pagina.
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model){
        Utente utente = utenteService.getUtenteAutenticato();
        try {
            utenteService.logout(utente);
        } catch (AccessNotAuthorizedException e) {
            return "GUIGestioneUtente/homepage";
        }
        request.getSession().invalidate();
        return "GUIGestioneUtente/homepage";
    }

    @RequestMapping(value ="/goLogin", method = RequestMethod.GET)
    public String goLogin(Model model) {
        return "GUIGestioneUtente/login";
    }

    @RequestMapping(value ="/dashboardDonatore", method = RequestMethod.GET)
    public String dashboardDonatore(Model model) {
        return "GUIGestioneUtente/dashboardDonatore";
    }

    @RequestMapping(value ="/dashboardOperatore", method = RequestMethod.GET)
    public String dashboardOperatore(Model model) {
        return "GUIGestioneUtente/dashboardOperatore";
    }
}
