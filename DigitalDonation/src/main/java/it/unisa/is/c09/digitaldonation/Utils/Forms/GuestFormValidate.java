package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.GuestFormException;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteServiceInterface;
import it.unisa.is.c09.digitaldonation.UtenteManagement.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.UtenteManagement.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link GuestForm}.
 * Il controllo effettuato rigurda la
 * validità di alcuni campi definiti nell'entità Guest.
 *
 * @author Mattia Sapere, Fabio Siepe
 */

@Component
public class GuestFormValidate implements Validator {

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
        GuestForm guestForm = (GuestForm) target;

        //Validazione del campo nome
        try{
            organizzazioneSeduteService.validaNome(guestForm.getNome());
        } catch(GuestFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo cognome
        try{
            organizzazioneSeduteService.validaCognome(guestForm.getCognome());
        } catch(GuestFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo telefono
        try{
            organizzazioneSeduteService.validaTelefono(guestForm.getTelefono());
        } catch(GuestFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo codice fiscale
        try{
            organizzazioneSeduteService.validaCodiceFiscaleGuest(guestForm.getCodiceFiscale());
        } catch(GuestFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo patologie
        try{
            organizzazioneSeduteService.validaPatologie(guestForm.getPatologie());
        } catch(GuestFormException e1) {
            errors.reject("errore", e1.getMessage());
        }

        //Validazione del campo gruppo sanguigno
        try{
            organizzazioneSeduteService.validaGruppoSanguigno(guestForm.getGruppoSanguigno());
        } catch(GuestFormException e1) {
            errors.reject("errore", e1.getMessage());
        }
    }
}


