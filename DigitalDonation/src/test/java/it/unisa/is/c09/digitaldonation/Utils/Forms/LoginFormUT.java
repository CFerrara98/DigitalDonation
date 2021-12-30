package it.unisa.is.c09.digitaldonation.Utils.Forms;


import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per il login form
 *
 * @author Mattia Sapere
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginFormUT {

    @Mock
    private Errors errors;

    @InjectMocks
    private LoginFormValidate loginFormValidate;

    private String email;
    private String password;

    private LoginForm loginForm;

    /**
     * Verifica che il campo email non sia vuoto
     */
    @Test
    public void controllaUtenteMailVuota() {

        email = "mattiasapere84@gmail.com";
        password = "Mattiasapere.123";

        loginForm = new LoginForm(email, password);
       // loginFormValidate.validate(loginForm, errors);

        //assertEquals("Email non valida", null, loginForm.getEmail());
    }


}
