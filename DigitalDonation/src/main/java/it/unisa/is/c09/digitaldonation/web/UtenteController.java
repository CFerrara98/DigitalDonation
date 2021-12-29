package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.PasswordNonValidaException;
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
     * @param model
     * @param session
     * @return String stringa che rappresenta la pagina da visualizzare
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String visualizzaHome(HttpSession session, Model model) {
        Utente utente = utenteService.getUtenteAutenticato();

        /*if (session.getAttribute("email") != null && utente != null) {
            if (utente instanceof Operatore) {
                return "operatoreDashboard";
            } else if (utente instanceof Volontario) {
                return "volontarioDashboard";
            }
            }*/
            return "GUIGestioneUtente/homepage";
    }


    /**
     * Metodo per effettuare il login
     *
     * @param request
     * @param loginForm
     * @return String stringa che rapprestenta la pagina da visualizzare
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletRequest request, @ModelAttribute LoginForm loginForm,
                        BindingResult result, RedirectAttributes redirectAttribute, Model model) {

        Utente utente = null;
        loginFormValidator.validate(loginForm, result);
        if (result.hasErrors()) {
            // se ci sono errori il metodo controller setta tutti i parametri
            redirectAttribute.addFlashAttribute("EmailError", "Email non valida");
            return "redirect:/loginPage";
        }

        try {
            utente = utenteService.login(loginForm.getEmail(), loginForm.getPassword());
        } catch (PasswordNonValidaException e) {
            redirectAttribute.addFlashAttribute("EmailPrecedente", loginForm.getEmail());
            redirectAttribute.addFlashAttribute("PasswordError", e.getMessage());
            model.addAttribute("utente", utente);
            return "redirect:/loginPage";
        }
        //Se è un operatore
        if (utente instanceof Operatore) {
            request.getSession().setAttribute("email", utente.getEmail());
            return "GUIGestioneUtente/dashboardOperatore";
        }
        //Se è un donatore
        else if (utente instanceof Donatore) {
            request.getSession().setAttribute("email", utente.getEmail());
            return "GUIGestioneUtente/dashboardDonatore";
        }
        else
        {
            return "redirect:/";
        }

    }

    @RequestMapping("/goLogin")
    public String contactinfo() {
        return "GUIGestioneUtente/loginPage";
    }



}
