package it.unisa.is.c09.digitaldonation.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

public class GestioneSeduteController {

    @RequestMapping(value = "/goConfermaDonazione", method = RequestMethod.GET)
    public String goConfermaDonazione(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {

        return "GUIOrganizzazioneSedute/confermaDonazione";
    }

    @RequestMapping(value = "/goSalvataggioIndisponibilita", method = RequestMethod.GET)
    public String goSalvataggioIndisponibilita(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {

        return "GUIOrganizzazioneSedute/salvataggioIndisponibilita";
    }
}
