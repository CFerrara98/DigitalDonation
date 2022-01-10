package it.unisa.is.c09.digitaldonation.utils.form;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.IndisponibilitaDonazioneFormException;
import it.unisa.is.c09.digitaldonation.gestionesedutemanagement.GestioneSeduteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Classe che definisce un validatore per {@link IndisponibilitaDonazioneForm}.
 * Il controllo effettuato rigurda la
 * validità di alcuni campi definiti nell'entità Indisponibilita.
 *
 * @author Mattia Sapere
 */
@Component
public class IndisponibilitaDonazioneFormValidate implements Validator {

    @Autowired
    private GestioneSeduteService gestioneSeduteService;

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

        IndisponibilitaDonazioneForm indisponibilitaDonazioneForm = (IndisponibilitaDonazioneForm) target;
        gestioneSeduteService = new GestioneSeduteService();

        //Validazione del campo motivazioni
        try {
            gestioneSeduteService.validaMotivazioni(indisponibilitaDonazioneForm.getMotivazioni());
        } catch (IndisponibilitaDonazioneFormException e) {
            errors.reject("MotivazioniIndisponibilitaError", e.getMessage());
            indisponibilitaDonazioneForm.setMotivazioni("");
        }

        //Validazione del campo nomeMedico
        try {
            gestioneSeduteService.validaNomeMedico(indisponibilitaDonazioneForm.getNomeMedico());
        } catch (IndisponibilitaDonazioneFormException e) {
            errors.reject("NomeMedicoIndisponibilitaError", e.getMessage());
            indisponibilitaDonazioneForm.setNomeMedico("");
        }

        //Validazione del campo dataProssimaDisponibilita
        try {
            gestioneSeduteService.validaDataProssimaDisponibilitaDonazione(indisponibilitaDonazioneForm.getDataProssimaDisponibilita());
        } catch (IndisponibilitaDonazioneFormException e) {
            errors.reject("DataProssimaDisponibilitaError", e.getMessage());
            Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
            indisponibilitaDonazioneForm.setDataProssimaDisponibilita(myCalendar.getTime());
        }
        return;
    }
}

