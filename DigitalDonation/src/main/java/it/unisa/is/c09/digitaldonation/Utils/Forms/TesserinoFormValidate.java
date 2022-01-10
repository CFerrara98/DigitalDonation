package it.unisa.is.c09.digitaldonation.Utils.Forms;


import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneTesserinoError.TesserinoFormException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.SedutaFormException;
import it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement.GestioneTesserinoService;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe che definisce un validatore per {@link TesserinoForm}.
 * Il controllo effettuato rigurda la
 * validità di alcuni campi definiti nell'entità Tesserino.
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@Component
public class TesserinoFormValidate implements Validator {

    @Autowired
    private GestioneTesserinoService gestioneTesserinoService;

    private static Logger logger = Logger.getLogger(String.valueOf(TesserinoFormValidate.class));

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    /**
     * Effettua la validazione dell'oggetto target riportando gli errori
     * nell'oggetto errors.
     *
     * @param target Oggetto da validare
     * @param errors Oggetto in cui salvare l'esito della validazione
     */
    @Override
    public void validate(Object target, Errors errors) {
        TesserinoForm tesserinoForm = (TesserinoForm) target;
        //gestioneTesserinoService = new GestioneTesserinoService();
        //Valida Campo Nome
        try {
            gestioneTesserinoService.validaNome(tesserinoForm.getNome());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoNomeError", e1.getMessage());
            tesserinoForm.setNome("");
        }

        //Valida Campo Cognome
        try {
            gestioneTesserinoService.validaCognome(tesserinoForm.getCognome());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoCognomeError", e1.getMessage());
            tesserinoForm.setCognome("");
        }

        //Valida Campo Codice fiscale
        try {
            gestioneTesserinoService.validaCodiceFiscale(tesserinoForm.getCodiceFiscale());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoCodiceFiscaleError", e1.getMessage());
            tesserinoForm.setCodiceFiscale("");
        }


        try {
            gestioneTesserinoService.validaImage(tesserinoForm.getImage().getOriginalFilename());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoImageError", e1.getMessage());
            tesserinoForm.setImage(null);
        }

        //Valida Campo Data Di Nascita
        try {
            gestioneTesserinoService.validaDataDiNascita(tesserinoForm.getDataNascita());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoDataNascitaError", e1.getMessage());
            Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
            tesserinoForm.setDataNascita(myCalendar.getTime());
        }

        //Valida Campo Luogo Di Nascita
        try {
            gestioneTesserinoService.validaLuogoDiNascita(tesserinoForm.getLuogoNascita());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoLuogoNascitaError", e1.getMessage());
            tesserinoForm.setLuogoNascita("");
        }

        //Valida Campo Residenza
        try {
            gestioneTesserinoService.validaResidenza(tesserinoForm.getResidenza());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoResidenzaError", e1.getMessage());
            tesserinoForm.setResidenza("");
        }

        //Valida Campo Email
        try {
            gestioneTesserinoService.validaEmail(tesserinoForm.getEmail(), tesserinoForm.getCodiceFiscale());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoEmailError", e1.getMessage());
            tesserinoForm.setEmail("");
        }

        //Valida Campo Gruppo Sanguigno
        try {
            gestioneTesserinoService.validaGruppoSanguigno(tesserinoForm.getGruppoSanguigno());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoGruppoSanguignoError", e1.getMessage());
            tesserinoForm.setGruppoSanguigno("");
        }

        //Valida Campo rh
        try {
            gestioneTesserinoService.validaRh(tesserinoForm.getRh());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoRhError", e1.getMessage());
            tesserinoForm.setRh("");
        }

        //Valida Campo Altre Indicazioni
        try {
            if(tesserinoForm.getAltreIndicazioni()==null) tesserinoForm.setAltreIndicazioni("Nessuna");
            gestioneTesserinoService.validaAltreIndicazioni(tesserinoForm.getAltreIndicazioni());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoAltreIndicazioniError", e1.getMessage());
            tesserinoForm.setAltreIndicazioni("");
        }

        //Valida Campo Numero Matricola
        try {
            gestioneTesserinoService.validaNumeroMatricola(tesserinoForm.getNumeroMatricola());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoNumeroMatricolaError", e1.getMessage());
            tesserinoForm.setNumeroMatricola(0);
        }

//        //Valida Campo Numero Tessera
//        try {
//            gestioneTesserinoService.validaNumeroTessera(tesserinoForm.getNumeroTessera());
//        } catch (TesserinoFormException e1) {
//            errors.reject("TesserinoNumeroTesseraError", e1.getMessage());
//            tesserinoForm.setNumeroTessera(0);
//        }

        //Valida Campo Data Rilascio
        try {
            gestioneTesserinoService.validaDataRilascio(tesserinoForm.getDataRilascio());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoDataRilascioError", e1.getMessage());
            Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
            tesserinoForm.setDataRilascio(myCalendar.getTime());
        }


        //Valida Campo Data Donazione
        try {
            gestioneTesserinoService.validaDataDonazione(tesserinoForm.getDataDonazione());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoDataDonazioneError", e1.getMessage());
            Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
            tesserinoForm.setDataDonazione(myCalendar.getTime());
        }

        //Valida tipo di donazione
        try {
            gestioneTesserinoService.validaTipoDonazione(tesserinoForm.getTipoDonazione());
        } catch (TesserinoFormException e1) {
            errors.reject("TesserinoTipoDonazioneError", e1.getMessage());
            tesserinoForm.setTipoDonazione("");
        }

        return;
    }
}
