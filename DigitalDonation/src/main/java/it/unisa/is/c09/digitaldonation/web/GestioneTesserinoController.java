package it.unisa.is.c09.digitaldonation.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GestioneTesserinoController {

    @RequestMapping(value = "/goTesserino", method = RequestMethod.GET)
    public String goVisualizzaTesserino(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {
        return "GUIOrganizzazioneSedute/visualizzaTesserino";
    }

    @RequestMapping(value = "/goCreazioneTesserino", method = RequestMethod.GET)
    public String gocreazioneTesserino(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {

        return "GUIOrganizzazioneSedute/creazioneTesserino";
    }
}
