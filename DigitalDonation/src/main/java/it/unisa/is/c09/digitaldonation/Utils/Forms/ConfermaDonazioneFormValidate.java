package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.GuestFormException;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link ConfermaDonazioneForm}.
 * Il controllo effettuato rigurda la
 * validità di alcuni campi definiti nell'entità Donazione.
 *
 * @author Mattia Sapere
 */

@Component
public class ConfermaDonazioneFormValidate implements Validator {

    //TODO da cambiare
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
        organizzazioneSeduteService = new OrganizzazioneSeduteService();


        //Validazione del campo nome
        try {
            organizzazioneSeduteService.validaNome(guestForm.getNome());
        } catch (GuestFormException e1) {
            errors.reject("NomeError", e1.getMessage());
            guestForm.setNome("");
        }
    }
}
