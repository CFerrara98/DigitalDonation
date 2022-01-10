package it.unisa.is.c09.digitaldonation.utils.form;


import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.utentemanagement.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Classe che definisce un validatore per {@link LoginForm}.
 * Il controllo effettuato rigurda la
 * validità di alcuni campi definiti nell'entità Utente.
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@Component
public class LoginFormValidate implements Validator {

  @Autowired
  private UtenteService utenteService;

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
    LoginForm loginForm = (LoginForm) target;


    //Validazione del campo email
    //TODO DA VALIDARE LA PASSWORD!
    try {
      utenteService.validaMail(loginForm.getEmail());
    } catch (MailNonValidaException e1) {
      errors.reject("errore", e1.getMessage());
      loginForm.setEmail("");
      return;
    } catch (MailNonEsistenteException e2) {
      errors.reject("errore", e2.getMessage());
      loginForm.setEmail("");
      return;
    }
  }
}
