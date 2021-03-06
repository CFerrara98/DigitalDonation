package it.unisa.is.c09.digitaldonation.utils.form;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.SedutaFormException;
import it.unisa.is.c09.digitaldonation.organizzazionesedutemanagement.OrganizzazioneSeduteService;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    SedutaForm sedutaForm = (SedutaForm) target;
    organizzazioneSeduteService = new OrganizzazioneSeduteService();
    //Validazione del campo dataSeduta
    try {
      organizzazioneSeduteService.validaDataSeduta(sedutaForm.getDataSeduta());
    } catch (SedutaFormException e1) {
      errors.reject("DataError", e1.getMessage());
      Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
      sedutaForm.setDataSeduta(myCalendar.getTime());

    }

    //Validazione del campo indirizzo
    try {
      organizzazioneSeduteService.validaIndirizzo(sedutaForm.getIndirizzo());
    } catch (SedutaFormException e1) {
      errors.reject("IndirizzoError", e1.getMessage());
      sedutaForm.setIndirizzo("");

    }

    //Validazione del campo città
    try {
      organizzazioneSeduteService.validaCitta(sedutaForm.getCitta());
    } catch (SedutaFormException e1) {
      errors.reject("CittaError", e1.getMessage());
      sedutaForm.setCitta("");
    }

    //Validazione del campo provincia
    try {
      organizzazioneSeduteService.validaProvincia(sedutaForm.getProvincia());
    } catch (SedutaFormException e1) {
      errors.reject("ProvinciaError", e1.getMessage());
      sedutaForm.setProvincia("");
    }

    //Validazione del campo CAP
    try {
      organizzazioneSeduteService.validaCap(sedutaForm.getCap());
    } catch (SedutaFormException e1) {
      errors.reject("CAPError", e1.getMessage());
      sedutaForm.setCap("");
    }

    //Validazione del campo numeroPartecipanti
    try {
      organizzazioneSeduteService.validaNumeroPartecipanti(sedutaForm.getNumeroPartecipanti());
    } catch (NumberFormatException e1) {
      errors.reject("NumPartecipantiError", e1.getMessage());
      sedutaForm.setNumeroPartecipanti(0);
    } catch (SedutaFormException e2) {
      errors.reject("NumPartecipantiError", e2.getMessage());
      sedutaForm.setNumeroPartecipanti(0);
    }

    //Validazione del campo dataInizioPrenotazione
    try {
      organizzazioneSeduteService.validaDataInizioPrenotazioni(sedutaForm);
    } catch (SedutaFormException e1) {
      errors.reject("DataInizioError", e1.getMessage());
      Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
      sedutaForm.setDataInizioPrenotazione(myCalendar.getTime());
    }
    //Validazione del campo dataFinePrenotazione
    try {
      organizzazioneSeduteService.validaDataFinePrenotazioni(sedutaForm);
    } catch (SedutaFormException e1) {
      errors.reject("DataFineError", e1.getMessage());
      Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
      sedutaForm.setDataFinePrenotazione(myCalendar.getTime());
    }
    return;
  }
}
