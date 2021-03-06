
package it.unisa.is.c09.digitaldonation.model.repository;

import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.UserNotLoggedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per il login form
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@RunWith(MockitoJUnitRunner.class)
public class UtenteRepositoryIT {


    @Mock
    private DonatoreRepository donatoreRepository;

    @Mock
    private DonazioneRepository donazioneRepository;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private IndisponibilitaRepository indisponibilitaRepository;

    @Mock
    private OperatoreRepository operatoreRepository;

    @Mock
    private SedeLocaleRepository sedeLocaleRepository;

    @Mock
    private SedutaRepository sedutaRepository;

    @Mock
    private TesserinoRepository tesserinoRepository;

    @Mock
    private UtenteRepository utenteRepository;

    private String email;
    private String password;

    public void validaCampi() throws MailNonValidaException, MailNonEsistenteException, UserNotLoggedException {
        utenteRepository.findByEmailAndPassword(email, password);
    }

    /**
     * Verifica che email e password siano associati nel database
     */
    @Test
    public void VerificaEmail() {
        email = "mattiasapre@gmail.com";
        password = "Password123";

        final String message = "Email o password errati, per favore riprova.";

        try {
            validaCampi();
        } catch (MailNonEsistenteException exception) {
            assertEquals(message, exception.getMessage());
        } catch (MailNonValidaException exception) {
            assertEquals(message, exception.getMessage());
        } catch (UserNotLoggedException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che email e password siano associati nel database
     */
    @Test
    public void VerificaPassword() {
        email = "mattiasapere@gmail.com";
        password = "Mattiaspere.123";

        final String message = "Email o password errati, per favore riprova.";

        try {
            validaCampi();
        } catch (MailNonEsistenteException exception) {
            assertEquals(message, exception.getMessage());
        } catch (MailNonValidaException exception) {
            assertEquals(message, exception.getMessage());
        } catch (UserNotLoggedException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che email e password siano corretti
     */
    @Test
    public void VerificaEmailPasswordSuccesso() {
        email = "mattiasapere@gmail.com";
        password = "Mattiaspere.123";

        try {
            validaCampi();
            utenteRepository.existsUtenteByEmailAndPassword(email, password);
        } catch (MailNonValidaException e) {
            e.printStackTrace();
        } catch (MailNonEsistenteException e) {
            e.printStackTrace();
        } catch (UserNotLoggedException e) {
            e.printStackTrace();
        }
    }
}

