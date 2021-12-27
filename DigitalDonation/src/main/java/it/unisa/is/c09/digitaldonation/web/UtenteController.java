package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;

@Controller
public class UtenteController {

    @Autowired
    UtenteService utenteService;

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
}
