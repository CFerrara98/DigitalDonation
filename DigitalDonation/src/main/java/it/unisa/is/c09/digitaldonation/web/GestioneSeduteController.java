package it.unisa.is.c09.digitaldonation.web;


import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.GestioneSeduteManagement.GestioneSeduteService;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.logging.Logger;

@Controller
public class GestioneSeduteController {

    private static Logger logger = Logger.getLogger(String.valueOf(OrganizzazioneSeduteController.class));

    @Autowired
    IndisponibilitaDonazioneFormValidate indisponibilitaDonazioneFormValidate;
    @Autowired
    GestioneSeduteService gestioneSeduteService;
    @Autowired
    ConfermaDonazioneFormValidate confermaDonazioneFormValidate;

    /**
     * Metodo GET per inserire l'indisponibilità di un donatore a seguito della visita medica.
     * @param request è la richiesta dalla sessione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goIndisponibilitaByOperatore", method = RequestMethod.GET)
    public String indisponibilitaByOperatoreGET(HttpServletRequest request, Model model, @RequestParam("codiceFiscale") String codiceFiscale) {

        IndisponibilitaDonazioneForm indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm();
        model.addAttribute("indisponibilitaDonazioneForm", indisponibilitaDonazioneForm);
        request.getSession().setAttribute("codiceFiscale",codiceFiscale);
        return "GUIGestioneSedute/salvataggioIndisponibilitaDonare";
    }

    /**
     * Metodo Post per inserire l'indisponibilità di un donatore a seguito della visita medica
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/indisponibilitaByOperatore", method = RequestMethod.POST)
    public String indisponibilitaByOperatorePOST(HttpServletRequest request, @ModelAttribute IndisponibilitaDonazioneForm indisponibilitaDonazioneForm,
                                                 RedirectAttributes redirectAttribute, BindingResult result, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String codiceFiscale = (String)  request.getSession().getAttribute("codiceFiscale");
        Long idSeduta = (Long) request.getSession().getAttribute("idSeduta");
        try{
            if(utente == null) new IllegalArgumentException();
            if(codiceFiscale.matches(Utente.CF_REGEX));
        }catch (Exception e){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        indisponibilitaDonazioneFormValidate.validate(indisponibilitaDonazioneForm,result);
        if(result.hasErrors()){
            redirectAttribute.addFlashAttribute("indisponibilitaDonazioneForm",indisponibilitaDonazioneForm);
            for(ObjectError x : result.getGlobalErrors()){
                redirectAttribute.addFlashAttribute(x.getCode(),x.getDefaultMessage());
            }
            return "redirect:/goIndisponibilitaByOperatore?codiceFiscale="+codiceFiscale;
        }
        try {
            gestioneSeduteService.salvataggioIndisponibilita(codiceFiscale,idSeduta,indisponibilitaDonazioneForm);
        } catch (CannotSaveDataRepositoryException e) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        model.addAttribute("success","Indisponibilità aggiunta con successo!");
        return "redirect:/goElencoPartecipanti?idSeduta="+idSeduta+"&successo=Indisponibilità aggiunta con successo!";
    }



    /**
     * Metodo GET per la conferma dell'avenuta donazione
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goSalvataggioDonazione", method = RequestMethod.GET)
    public String confermaDonazioneGET(HttpServletRequest request, @ModelAttribute ConfermaDonazioneForm confermaDonazioneForm,
                                             RedirectAttributes redirectAttribute, BindingResult result, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");

        String codiceFiscale = request.getParameter("codiceFiscale");
        try{
            if(utente == null) new IllegalArgumentException();
            if(codiceFiscale.matches(Utente.CF_REGEX));
        }catch (Exception e){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        /*if(confermaDonazioneForm == null) confermaDonazioneForm= new ConfermaDonazioneForm();
        model.addAttribute("confermaDonazioneForm",confermaDonazioneForm);
        request.getSession().setAttribute("confermaDonazioneForm" , confermaDonazioneForm);*/

        model.addAttribute("codiceFiscale",codiceFiscale);
        request.getSession().setAttribute("codiceFiscale" , codiceFiscale);

        model.addAttribute("idSeduta",request.getSession().getAttribute("idSeduta"));
        request.getSession().setAttribute("idSeduta" , request.getSession().getAttribute("idSeduta"));
        return "/GUIGestioneSedute/confermaDonazione";
    }

    /**
     * Metodo Post per inserire l'indisponibilità di un donatore a seguito della visita medica
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/salvataggioDonazione", method = RequestMethod.POST)
    public String indisponibilitaByOperatorePost(HttpServletRequest request, @ModelAttribute ConfermaDonazioneForm confermaDonazioneForm,
                                                 RedirectAttributes redirectAttribute, BindingResult result, Model model, @RequestParam(name = "gruppoSanguigno")String gruppoSanguigno) {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String codiceFiscale = (String) request.getSession().getAttribute("codiceFiscale");
        Long idSeduta = (Long) request.getSession().getAttribute("idSeduta");

        try{
            if(utente == null) new IllegalArgumentException();
            if(codiceFiscale.matches(Utente.CF_REGEX));
        }catch (Exception e){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        confermaDonazioneForm.setTipoDonazione(gruppoSanguigno);
        confermaDonazioneFormValidate.validate(confermaDonazioneForm,result);
        if(result.hasErrors()){
            redirectAttribute.addFlashAttribute("confermaDonazioneForm",confermaDonazioneForm);
            for(ObjectError x : result.getGlobalErrors()){
                redirectAttribute.addFlashAttribute(x.getCode(),x.getDefaultMessage());
            }
            return "/GUIGestioneSedute/confermaDonazione";
        }

        String tipoDonazione = null;
        if(gruppoSanguigno.equals("plasma")){
            tipoDonazione = "plasma";
        }else if(gruppoSanguigno.equals("cito")){
            tipoDonazione = "cito";
        }else if(gruppoSanguigno.equals("sangue")){
            tipoDonazione = "sangue";
        }

        try {
            gestioneSeduteService.salvataggioDonazione(codiceFiscale,idSeduta,tipoDonazione);
        } catch (CannotSaveDataRepositoryException e) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        return "redirect:/goElencoPartecipanti?idSeduta="+idSeduta + "&successo=" + "Donazione avvenuta con successo!";
    }
}