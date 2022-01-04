package it.unisa.is.c09.digitaldonation.web;


import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.IndisponibilitaDonazioneFormException;
import it.unisa.is.c09.digitaldonation.GestioneSeduteManagement.GestioneSeduteService;
import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
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
     * Metodo GET per inserire l'indisponibilità di un donatore a seguito della visita medica
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goIndisponibilitaByOperatore", method = RequestMethod.GET)
    public String indisponibilitaByOperatoreGET(HttpServletRequest request, @ModelAttribute IndisponibilitaDonazioneForm indisponibilitaDonazioneForm,
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
        if(indisponibilitaDonazioneForm == null) indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm();
        model.addAttribute("indisponibilitaDonazioneForm",indisponibilitaDonazioneForm);
        model.addAttribute("codiceFiscale",codiceFiscale);
        return "/errorsPages/workingInProgress";//TODO jsp per la indisponibilita inserita dall'operatore
    }

    /**
     * Metodo Post per inserire l'indisponibilità di un donatore a seguito della visita medica
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/IndisponibilitaByOperatore", method = RequestMethod.POST)
    public String indisponibilitaByOperatorePOST(HttpServletRequest request, @ModelAttribute IndisponibilitaDonazioneForm indisponibilitaDonazioneForm,
                                                 RedirectAttributes redirectAttribute, BindingResult result, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String codiceFiscale = (String)  model.getAttribute("codiceFiscale");
        Long idSeduta = (Long) model.getAttribute("idSeduta");
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
            return "/errorsPages/workingInProgress";//TODO JSP  indisponibilita inserita dall'operatore
        }
        try {
            gestioneSeduteService.salvataggioIndisponibilita(codiceFiscale,idSeduta,indisponibilitaDonazioneForm);
        } catch (CannotSaveDataRepositoryException e) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        model.addAttribute("success","Indisponibilità aggiunta con successo!");
        return "redirect:/goElencoPartecipanti?idSeduta="+model.getAttribute("idSeduta");
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
        if(confermaDonazioneForm == null) confermaDonazioneForm= new ConfermaDonazioneForm();
        model.addAttribute("confermaDonazioneForm",confermaDonazioneForm);
        model.addAttribute("codiceFiscale",codiceFiscale);
        return "/errorsPages/workingInProgress";//TODO jsp per la indisponibilita inserita dall'operatore
    }

    /**
     * Metodo Post per inserire l'indisponibilità di un donatore a seguito della visita medica
     * @param request è la richiesta dalla sessione.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/SalvataggioDonazione", method = RequestMethod.POST)
    public String indisponibilitaByOperatorePost(HttpServletRequest request, @ModelAttribute ConfermaDonazioneForm confermaDonazioneForm,
                                                 RedirectAttributes redirectAttribute, BindingResult result, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        String codiceFiscale = (String)  model.getAttribute("codiceFiscale");
        Long idSeduta = (Long) model.getAttribute("idSeduta");
        try{
            if(utente == null) new IllegalArgumentException();
            if(codiceFiscale.matches(Utente.CF_REGEX));
        }catch (Exception e){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        confermaDonazioneFormValidate.validate(confermaDonazioneForm,result);
        if(result.hasErrors()){
            redirectAttribute.addFlashAttribute("confermaDonazioneForm",confermaDonazioneForm);
            for(ObjectError x : result.getGlobalErrors()){
                redirectAttribute.addFlashAttribute(x.getCode(),x.getDefaultMessage());
            }
            return "/errorsPages/workingInProgress";//TODO JSP conferma donazione  inserita dall'operatore
        }
        try {
            gestioneSeduteService.salvataggioDonazione(codiceFiscale,idSeduta,confermaDonazioneForm.getTipoDonazione());
        } catch (CannotSaveDataRepositoryException e) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.INTERNAL_SERVER_ERROR);
            return "redirect:/error";
        }
        model.addAttribute("success","Salvataggio donazione avvenuto con successo!");
        return "redirect:/goElencoPartecipanti?idSeduta="+model.getAttribute("idSeduta");
    }











}
