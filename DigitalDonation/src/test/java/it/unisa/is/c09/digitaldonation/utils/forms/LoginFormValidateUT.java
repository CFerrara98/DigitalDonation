package it.unisa.is.c09.digitaldonation.utils.forms;

import it.unisa.is.c09.digitaldonation.model.repository.UtenteRepository;
import it.unisa.is.c09.digitaldonation.utentemanagement.UtenteService;
import it.unisa.is.c09.digitaldonation.utils.form.LoginForm;
import it.unisa.is.c09.digitaldonation.utils.form.LoginFormValidate;
import it.unisa.is.c09.digitaldonation.utils.form.SedutaFormValidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.fail;

import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per il guest form
 *
 * @author Fabio Siepe
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginFormValidateUT {

    @Autowired
    private UtenteService utenteService = new UtenteService();

    @Autowired
    @Mock
    private UtenteRepository utenteRepository;

    @InjectMocks
    private LoginFormValidate loginFormValidate;

    @Mock
    private BindingResult errors;

    LoginForm loginForm = new LoginForm();

    /**
     * Verifica che tutti i campi rispettino il formato
     */
    /*@Test
    public void validateEmailNull() {
        loginForm = new LoginForm("", "Password123");

        loginFormValidate = new LoginFormValidate();

        loginFormValidate.validate(loginForm, errors);

        assertEquals("Data seduta non valida.", "", loginForm.getEmail());
    }*/

    /**
     * Verifica che la mail non esiste sul database
     */
    /*@Test
    public void validateEmailNonEsistente() throws MailNonValidaException, MailNonEsistenteException {
        loginForm = new LoginForm("fabio.siepe@gmail.com", "Password123");
        loginFormValidate = new LoginFormValidate();
        loginFormValidate.validate(loginForm, errors);

        when(utenteRepository.existsUtenteByEmail(loginForm.getEmail())).thenReturn(false);

        assertEquals("Data seduta non valida.", "", loginForm.getEmail());
    }*/


    @Test
    public void VerificaSupports() {

        assertEquals("Volevo Solo più branche coverage", false, loginFormValidate.supports(SedutaFormValidate.class));

    }
}
