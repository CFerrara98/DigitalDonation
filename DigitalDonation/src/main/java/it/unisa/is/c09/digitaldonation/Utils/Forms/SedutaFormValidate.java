package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.SedutaFormException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

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
        //Validazione del campo dataSeduta
        try {

            organizzazioneSeduteService.validaDataSeduta(sedutaForm.getDataSeduta());

        } catch (SedutaFormException e1) {
            errors.reject("Data non valida.", e1.getMessage());
            sedutaForm.setDataSeduta(null);
            return;
        }

        //Validazione del campo indirizzo
        try {
            organizzazioneSeduteService.validaIndirizzo(sedutaForm.getIndirizzo());
        } catch (SedutaFormException e1) {
            errors.reject("Indirizzo non valido.", e1.getMessage());
            sedutaForm.setIndirizzo(null);
            return;

        }

        //Validazione del campo città
        try {
            organizzazioneSeduteService.validaCitta(sedutaForm.getCitta());
        } catch (SedutaFormException e1) {
            errors.reject("Citta non valida.", e1.getMessage());
            sedutaForm.setCitta(null);
            return;
        }

        //Validazione del campo provincia
        try {
            organizzazioneSeduteService.validaProvincia(sedutaForm.getProvincia());
        } catch (SedutaFormException e1) {
            errors.reject("Provincia non valida.", e1.getMessage());
            sedutaForm.setProvincia(null);
            return;
        }

        //Validazione del campo CAP
        try {
            organizzazioneSeduteService.validaCAP(sedutaForm.getCAP());
        } catch (SedutaFormException e1) {
            errors.reject("CAP non valido.", e1.getMessage());
            sedutaForm.setCAP(null);
            return;
        }

        //Validazione del campo numeroPartecipanti
        try {
            organizzazioneSeduteService.validaNumeroPartecipanti(sedutaForm.getNumeroPartecipanti());
        } catch (SedutaFormException e1) {
            errors.reject("Numero partecipanti non valido. cià", e1.getMessage());
            sedutaForm.setNumeroPartecipanti(0);
            return;
        }

        //Validazione del campo dataInizioPrenotazione
        try {
            organizzazioneSeduteService.validaDataInizioPrenotazioni(sedutaForm);
        } catch (SedutaFormException e1) {
            errors.reject("Errore nella data dell'inizio.", e1.getMessage());
            sedutaForm.setDataInizioPrenotazione(null);
            return;
        }
        //Validazione del campo dataFinePrenotazione
        try {
            organizzazioneSeduteService.validaDataFinePrenotazioni(sedutaForm);
        } catch (SedutaFormException e1) {
            errors.reject("Data di fine prenotazione non valida.", e1.getMessage());
            sedutaForm.setDataFinePrenotazione(null);
            return;
        }
    }

}
