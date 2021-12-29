package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.SedutaFormException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link SedutaForm}.
 * Il controllo effettuato rigurda la
 * validità di alcuni campi definiti nell'entità Seduta.
 *
 * @author Mattia Sapere, Fabio Siepe
 */

@Component
public class SedutaFormValidate implements Validator {
    @Autowired
    private OrganizzazioneSeduteService organizzazioneSeduteService;

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
        SedutaForm sedutaForm = (SedutaForm) target;
        Seduta seduta = (Seduta) target;
        //Validazione del campo dataSeduta
        try{
            organizzazioneSeduteService.validaDataSeduta(sedutaForm.getDataSeduta());
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo indirizzo
        try{
            organizzazioneSeduteService.validaIndirizzo(sedutaForm.getIndirizzo());
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo città
        try{
            organizzazioneSeduteService.validaCitta(sedutaForm.getCitta());
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo provincia
        try{
            organizzazioneSeduteService.validaProvincia(sedutaForm.getProvincia());
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo CAP
        try{
            organizzazioneSeduteService.validaCAP(sedutaForm.getCAP());
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo numeroPartecipanti
        try{
            organizzazioneSeduteService.validaNumeroPartecipanti(sedutaForm.getNumeroPartecipanti());
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo dataInizioPrenotazione
        try{
            organizzazioneSeduteService.validaDataInizioPrenotazioni(seduta);
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }
        //Validazione del campo dataFinePrenotazione
        try{
            organizzazioneSeduteService.validaDataFinePrenotazioni(seduta);
        } catch(SedutaFormException e1) {
            errors.reject("errore", e1.getMessage());
        }
    }

}
