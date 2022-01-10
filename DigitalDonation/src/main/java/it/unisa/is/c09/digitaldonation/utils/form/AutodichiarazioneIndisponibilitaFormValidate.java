package it.unisa.is.c09.digitaldonation.utils.form;

import it.unisa.is.c09.digitaldonation.erroremanagement.gestionetesserinoerror.TesserinoFormException;
import it.unisa.is.c09.digitaldonation.gestionetesserinomanagement.GestioneTesserinoService;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link AutodichiarazioneIndisponibilitaForm}.
 * Il controllo effettuato rigurda la
 * validità di alcuni campi definiti nell'entità Donazione.
 *
 * @author Mattia Sapere
 */
@Component
public class AutodichiarazioneIndisponibilitaFormValidate implements Validator {
  @Autowired
  private GestioneTesserinoService gestioneTesserinoService;

  @Override
  public boolean supports(Class<?> aclass) {
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

    AutodichiarazioneIndisponibilitaForm autodichiarazioneIndisponibilitaForm =
            (AutodichiarazioneIndisponibilitaForm) target;
    gestioneTesserinoService = new GestioneTesserinoService();

    //Validazione del campo motivazioni
    try {
      gestioneTesserinoService
              .validaMotivazioni(autodichiarazioneIndisponibilitaForm.getMotivazioni());
    } catch (TesserinoFormException e) {
      errors.reject("MotivazioneError", e.getMessage());
      autodichiarazioneIndisponibilitaForm.setMotivazioni("");
    }

    //Validazione del campo dataProssimaDisponibilità
    try {
      gestioneTesserinoService
              .validaDataProssimaDisponibilitaDonazione(autodichiarazioneIndisponibilitaForm
                      .getDataProssimaDisponibilita());
    } catch (TesserinoFormException e) {
      errors.reject("DataProssimaDisponibilitaError", e.getMessage());
      Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
      autodichiarazioneIndisponibilitaForm.setDataProssimaDisponibilita(myCalendar.getTime());
    }
    return;
  }
}
