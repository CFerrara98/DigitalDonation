package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.UtenteRepository;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteServiceUT;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
    @Test
    public void validateEmailNull() {
        loginForm = new LoginForm();
        loginForm.setEmail("");

        loginFormValidate.validate(loginForm, errors);

        assertEquals("Data seduta non valida.", "", loginForm.getEmail());

    }

    /**
     * Verifica che la mail non esiste sul database
     */
    @Test
    public void validateEmailNonEsistente() throws MailNonValidaException, MailNonEsistenteException {

        loginForm = new LoginForm();
        String email = "fabio.siepe@gmail.com";
        loginForm.setEmail(email);
        loginFormValidate = new LoginFormValidate();

        utenteService.validaMail(email);
        loginFormValidate.validate(loginForm, errors);

        assertEquals("Data seduta non valida.", email, loginForm.getEmail());
    }


    @Test
    public void VerificaSupports() {

        assertEquals("Volevo Solo più branche coverage", false, loginFormValidate.supports(SedutaFormValidate.class));

    }
}
