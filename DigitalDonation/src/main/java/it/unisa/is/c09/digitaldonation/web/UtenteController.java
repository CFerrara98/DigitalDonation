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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;

@Controller
public class UtenteController {
    @Autowired
    UtenteService utenteService;
    @Autowired
    LoginFormValidate loginFormValidator;

    private Logger logger = Logger.getLogger(String.valueOf(UtenteController.class));

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
        return "GUIOrganizzazioneSedute/inserimentoUtenteGuest";
    }


    /**
     * Metodo che permette di andare alla pagina di login, risponde a richieste GET.
     * @param loginForm è il form compilato dall'utente.
     * @param result sono i campi del form.
     * @param redirectAttribute è un attributo che permette di cambiare pagina.
     * @param model è l'oggetto Model.
     * @return String ridirezione alla pagina.
     */
    @RequestMapping(value ="/goLogin", method = RequestMethod.GET)
    public String goLogin(HttpServletRequest request,@ModelAttribute("loginModel")LoginForm loginForm,
                          BindingResult result, RedirectAttributes redirectAttribute, Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "GUIGestioneUtente/login";
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

        logger.info(loginForm.getEmail() + "   " + loginForm.getPassword());
        logger.info(("Path"+ request.getContextPath() + " Uri: "+request.getRequestURI()));
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(utente != null) logger.info("Utente già loggato che tenta di loggare "+utente.toString());

        if (utente == null) {
            loginFormValidator.validate(loginForm, result);
            if (result.hasErrors()) {
                // se ci sono errori il metodo controller setta tutti i parametri
                redirectAttribute.addFlashAttribute("EmailError", "Email o password errati, per favore riprova");
                return "redirect:/goLogin";
            }
            try {
                utente = utenteService.login(loginForm.getEmail(), loginForm.getPassword());
            } catch (UserNotLoggedException e) {
                redirectAttribute.addFlashAttribute("EmailPrecedente", loginForm.getEmail());
                redirectAttribute.addFlashAttribute("PasswordError", "Email o password errati, per favore riprova");
                //model.addAttribute("utente",utente);
                return "redirect:/goLogin";
            } catch (NoSuchAlgorithmException e) {
                return "redirect:/goLogin";
            }
        }
        if (utente != null) {
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
        }
        return "redirect:/login";
    }

    /**
     * Metodo che permette ad un utente di effettuare il logout
     * @param request è la richiesta inviata dall'utente.
     * @return String che ridireziona ad una pagina.
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        try {
            utenteService.logout(utente);
        } catch (AccessNotAuthorizedException e) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/";
        }
        request.getSession().invalidate();
        return "GUIGestioneUtente/homepage";
    }

    /**
     * Metodo che permette di andare alla dashboard del donatore.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina.
     */
    @RequestMapping(value ="/dashboardDonatore", method = RequestMethod.GET)
    public String dashboardDonatore(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("utente") instanceof Donatore){
            //TODO Caricare la img del donatore come da mock-up
            return "GUIGestioneUtente/dashboardDonatore";
        }
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
        return "redirect:/";
    }

    /**
     * Metodo che permette di andare alla dashboard dell'operatore.
     * @param model è l'oggetto model.
     * @return String ridirezione alla pagina.
     */
    @RequestMapping(value ="/dashboardOperatore", method = RequestMethod.GET)
    public String dashboardOperatore(HttpServletRequest request, Model model) {
        if(request.getSession().getAttribute("utente") instanceof Operatore){
            return "GUIGestioneUtente/dashboardOperatore";
        }
        request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
        return "redirect:/";
    }
}
