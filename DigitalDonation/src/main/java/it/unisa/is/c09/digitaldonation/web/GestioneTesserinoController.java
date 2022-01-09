package it.unisa.is.c09.digitaldonation.web;

import com.google.api.services.drive.model.File;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement.GestioneTesserinoService;
import it.unisa.is.c09.digitaldonation.GoogleDriveAPI.GoogleDriveService;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import it.unisa.is.c09.digitaldonation.Utils.Forms.*;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.System.in;
import static java.lang.System.out;

@Controller
@MultipartConfig(fileSizeThreshold = 1024*1024*10 ,
        maxFileSize = (1024*1024*10)/2
)
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
    @Autowired
    GoogleDriveService googleDriveService;

    /**
     * Metodo che permette al donatore di compilare un autodichiarazione di indisponibilità.
     *
     * @param request               è la richiesta dalla sessione.
     * @param autodichiarazioneForm è l'oggetto form dell'utente guest.
     * @param redirectAttribute     è l'attributo di ridirezione.
     * @param model                 è l'oggetto Model.
     * @param result                è la variabile di binding.
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
    public String goAutodichiarazioneDiIndisponibilita(HttpServletRequest request, Model model) {

        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || utente instanceof Operatore) {
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
        if (utente == null || utente instanceof Operatore) {
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
    public String goCreazioneTesserino(HttpServletRequest request, Model model) {
        Utente utente = (Utente) request.getSession().getAttribute("utente");
        if (utente == null || utente instanceof Donatore) {
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
                                     BindingResult result, RedirectAttributes redirectAttribute, Model model) {
        Utente user = (Utente) request.getSession().getAttribute("utente");
        if (user == null || user instanceof Donatore) {
            request.setAttribute(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.UNAUTHORIZED);
            return "redirect:/error";
        }

        //Validate del form CreazioneTesserino
        tesserinoFormValidate.validate(tesserinoForm, result);
        if (result.hasErrors()) {
            redirectAttribute.addFlashAttribute("tesserinoForm", tesserinoForm);
            for (ObjectError x : result.getGlobalErrors()) {
                redirectAttribute.addFlashAttribute(x.getCode(), x.getDefaultMessage());
            }
            return "redirect:/goCreazioneTesserino";
        }
        Donatore donatore = new Donatore();
        Tesserino tesserino = new Tesserino();
        Donazione donazione = null;
        String pathForView;
        //Parsing dati del form in istanza di oggetti Entity
        do {
            donatore.setNome(tesserinoForm.getNome());
            donatore.setCognome(tesserinoForm.getCognome());
            donatore.setCodiceFiscale(tesserinoForm.getCodiceFiscale());
            donatore.setDataDiNascita(tesserinoForm.getDataNascita());
            donatore.setLuogoDiNascita(tesserinoForm.getLuogoNascita());
            donatore.setResidenza(tesserinoForm.getResidenza());
            donatore.setEmail(tesserinoForm.getEmail());


            tesserino.setDonatoreUtenteCodiceFiscale(tesserinoForm.getCodiceFiscale());
            tesserino.setDataRilascio(tesserinoForm.getDataRilascio());
            tesserino.setGruppoSanguigno(tesserinoForm.getGruppoSanguigno());
            tesserino.setNumeroMatricola(tesserinoForm.getNumeroMatricola());
            tesserino.setRh(tesserinoForm.getRh());


            if (tesserinoForm.getTipoDonazione() != null && tesserinoForm.getDataDonazione() != null) {
                donazione = new Donazione();
                donazione.setTipoDonazione(tesserinoForm.getTipoDonazione());
                donazione.setDataDonazione(tesserinoForm.getDataDonazione());
                donazione.setTesserino(tesserino);
            }
            List<Donazione> listaDonazioni = new ArrayList<>();
            if (donazione != null)
                listaDonazioni.add(donazione);
            tesserino.setListaDonazioni(listaDonazioni);
        } while (false);
        //Caricamento della foto sul Drive Google
        do{
            MultipartFile myFile = tesserinoForm.getImage();
            //Nuovo file
            java.io.File newFile = new java.io.File(myFile.getOriginalFilename());
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = new BufferedInputStream(myFile.getInputStream());
                outputStream = new BufferedOutputStream(new FileOutputStream(myFile.getOriginalFilename()));
                byte[] buffer = new byte[1024];
                int lenghtRead;
                while ((lenghtRead = inputStream.read(buffer)) > 0){
                    outputStream.write(buffer, 0, lenghtRead);
                    outputStream.flush();
                }
                inputStream.close();
                outputStream.close();
            }catch (Exception e){
                e.printStackTrace();
            }
            //il type lo si prende da myFile.getContentType()
            File file = googleDriveService.upLoadFile(donatore.getCodiceFiscale(), newFile.getAbsolutePath(), myFile.getContentType());
            //Rende l'URL visualizzabile
            pathForView = googleDriveService.pathSavedToView(file.getWebViewLink());
            newFile.delete();
        }while (false);
        //Setting del source dell'immagine
        tesserino.setImgSource(pathForView);
        try {
            gestioneTesserinoService.creazioneTesserino(donatore, tesserino, donazione);
        } catch (CannotSaveDataRepositoryException e) {
            e.printStackTrace();
            redirectAttribute.addFlashAttribute(e.getTarget(), e.getMessage());
            out.println("CannotSaveDataRepositoryException");
            return "redirect:/goCreazioneTesserino";
        }
        model.addAttribute("success", "Tesserino creato con successo");
        return "GUIGestioneUtente/dashboardOperatore";

    }
}