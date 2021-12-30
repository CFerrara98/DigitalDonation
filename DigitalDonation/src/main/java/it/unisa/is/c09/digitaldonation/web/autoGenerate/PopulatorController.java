package it.unisa.is.c09.digitaldonation.web.autoGenerate;


import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpSession;


@Controller
public class PopulatorController {

    @Autowired
    UtilServicePopulator utilService;

    @RequestMapping(value = "/generateEntity", method = RequestMethod.GET)
    public String generateEntity(HttpSession session) {
        /*utilService.doDonatori(200);
        utilService.doGuests(500);
        utilService.doOperatoriAndSedeLocale(10);
        utilService.doSedute(10);
        utilService.doDonazioni(80);
        return "GUIGestioneUtente/homepage";*/
        utilService.testMD5();
        return "GUIGestioneUtente/homepage";
    }

}
