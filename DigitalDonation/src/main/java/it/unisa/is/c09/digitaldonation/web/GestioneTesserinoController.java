package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement.GestioneTesserinoService;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class GestioneTesserinoController {

    private static Logger logger = Logger.getLogger(String.valueOf(OrganizzazioneSeduteController.class));

    @Autowired
    GestioneTesserinoService gestioneTesserinoService;
    @Autowired
    AutodichiarazioneIndisponibilitaFormValidate autodichiarazioneFormValidate;
    @Autowired
    OrganizzazioneSeduteService organizzazioneSeduteService;
    @Autowired
    UtenteService utenteService;
    @Autowired
    GuestFormValidate guestFormValidate;
    @Autowired
    SedutaFormValidate sedutaFormValidate;
    @Autowired
    TesserinoFormValidate tesserinoFormValidate;

    /**
     * Metodo che permette al donatore di compilare un autodichiarazione di indisponibilità.
     *
     * @param request           è la richiesta dalla sessione.
     * @param autodichiarazioneForm         è l'oggetto form dell'utente guest.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model             è l'oggetto Model.
     * @param result            è la variabile di binding.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/autodichiarazioneIndisponibilita", method = RequestMethod.POST)
    public String autodichiarazioneDiIndisponibilita(HttpServletRequest request, @ModelAttribute AutodichiarazioneIndisponibilitaForm autodichiarazioneForm,
                                                     BindingResult result, RedirectAttributes redirectAttribute, Model model) {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || utente instanceof Operatore) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/error";
        }

        autodichiarazioneFormValidate.validate(autodichiarazioneForm, result);
        if (result.hasErrors()) {
            redirectAttribute.addFlashAttribute("autodichiarazioneForm", autodichiarazioneForm);
            for (ObjectError x : result.getGlobalErrors()) {
                redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
            }
            return "redirect:/goAutodichiarazioneIndisponibilita";
        }

        Indisponibilita indisponibilita = new Indisponibilita();
        indisponibilita.setCodiceFiscaleDonatore(utente.getCodiceFiscale());
        indisponibilita.setDataProssimaDisponibilita(autodichiarazioneForm.getDataProssimaDisponibilita());
        indisponibilita.setMotivazioni(autodichiarazioneForm.getMotivazioni());

        try {
            gestioneTesserinoService.autodichiarazioneIndisponibilita(indisponibilita);
        } catch (CannotSaveDataRepositoryException e) {
            redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
            return "redirect:/goAutodichiarazioneIndisponibilita";
        }

        model.addAttribute("success", "Autodichiarazione compilata con successo");
        return "GUIGestioneUtente/dashboardDonatore";
    }

    /**
     * Metodo che permette al donatore di andare alla pagina dell'autodichiarazione di indisponibilità.
     *
     * @param request è la richiesta dalla sessione.
     * @param model   è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goAutodichiarazioneIndisponibilita", method = RequestMethod.GET)
    public String goAutodichiarazioneDiIndisponibilita(HttpServletRequest request, Model model){

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(utente==null || utente instanceof Operatore){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/error";
        }

        AutodichiarazioneIndisponibilitaForm autodichiarazioneIndisponibilitaForm = new AutodichiarazioneIndisponibilitaForm();
        model.addAttribute("autodichiarazioneForm", autodichiarazioneIndisponibilitaForm);
        return "GUIGestioneTesserinoDigitale/autodichiarazioneIndisponibilita";
    }

    /**
     * Metodo che permette al donatore di visualizzare il proprio tesserino.
     *
     * @param request è la richiesta dalla sessione.
     * @param model   è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goTesserino", method = RequestMethod.GET)
    public String goVisualizzaTesserino(HttpServletRequest request, RedirectAttributes redirectAttribute, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(utente==null || utente instanceof Operatore){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/error";
        }
        return "GUIOrganizzazioneSedute/visualizzaTesserino";
    }

    /**
     * Metodo che permette di andare alla pagine creazione nuovo tesserino.
     *
     * @param request è la richiesta dalla sessione.
     * @param model   è l'oggetto Model.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/goCreazioneTesserino", method = RequestMethod.GET)
    public String goCreazioneTesserino(HttpServletRequest request, Model model){
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if(utente==null || utente instanceof Donatore){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/error";
        }

        TesserinoForm tesserinoForm = new TesserinoForm();
        model.addAttribute("tesserinoForm", tesserinoForm);
        return "GUIGestioneTesserinoDigitale/creazioneTesserino";
    }

    /**
     * Metodo che permette la creazione di un nuovo tesserino.
     *
     * @param request           è la richiesta dalla sessione.
     * @param tesserinoForm     è l'oggetto form dell'utente guest.
     * @param redirectAttribute è l'attributo di ridirezione.
     * @param model             è l'oggetto Model.
     * @param result            è la variabile di binding.
     * @return String ridirezione ad una pagina.
     */
    @RequestMapping(value = "/creazioneTesserino", method = RequestMethod.POST)
    public String creazioneTesserino(HttpServletRequest request, @ModelAttribute TesserinoForm tesserinoForm,
                                                     BindingResult result, RedirectAttributes redirectAttribute, Model model){
        Utente user = (Utente) request.getSession().getAttribute("utente");
        if(user==null || user instanceof Donatore){
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/error";
        }

        tesserinoFormValidate.validate(tesserinoForm, result);
        if(result.hasErrors()){
            redirectAttribute.addFlashAttribute("tesserinoForm", tesserinoForm);
            for (ObjectError x : result.getGlobalErrors()) {
                redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
            }
            return "redirect:/goCreazioneTesserino";
        }
        //TODO NON FUNZIONANTE
        Utente utente = new Utente();
        utente.setCodiceFiscale(tesserinoForm.getCodiceFiscale());
        utente.setNome(tesserinoForm.getNome());
        utente.setCognome(tesserinoForm.getCognome());
        utente.setEmail(tesserinoForm.getEmail());

        Donatore donatore = new Donatore();
        donatore.setCodiceFiscale(tesserinoForm.getCodiceFiscale());
        donatore.setDataDiNascita(tesserinoForm.getDataNascita());
        donatore.setLuogoDiNascita(tesserinoForm.getLuogoNascita());
        donatore.setResidenza(tesserinoForm.getResidenza());

        Tesserino tesserino = new Tesserino();
        tesserino.setDonatoreUtenteCodiceFiscale(tesserinoForm.getCodiceFiscale());
        tesserino.setDataRilascio(tesserinoForm.getDataRilascio());
        tesserino.setGruppoSanguigno(tesserinoForm.getGruppoSanguigno());
        tesserino.setNumeroMatricola(tesserinoForm.getNumeroMatricola());
        tesserino.setRh(tesserinoForm.getRh());

        Donazione donazione = new Donazione();
        donazione.setTipoDonazione(tesserinoForm.getTipoDonazione());
        donazione.setDataDonazione(tesserinoForm.getDataDonazione());
        donazione.setTesserino(tesserino);

        List<Donazione> listaDonazioni = new ArrayList<>();
        listaDonazioni.add(donazione);
        tesserino.setListaDonazioni(listaDonazioni);

        try {
            gestioneTesserinoService.creazioneTesserino(utente, donatore, tesserino, donazione);
        } catch (CannotSaveDataRepositoryException e) {
            redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
            return "redirect:/goCreazioneTesserino";
        }
        model.addAttribute("success", "Tesserino creato con successo");
        return "GUIGestioneUtente/dashboardDonatore";
    }
}
