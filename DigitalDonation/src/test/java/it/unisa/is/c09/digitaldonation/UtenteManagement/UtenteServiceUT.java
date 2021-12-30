package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

/**
 * Test di unità per la classe UtenteService; tipologia di test:
 * whitebox strategia: branch coverage.
 *
 * @author Mattia Sapere, Fabio Siepe, Marika Spagna Zito
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class UtenteServiceUT {

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

    @Autowired
    @InjectMocks
    private UtenteService utenteService;

    private Utente utente;

    /**
     * Testa il caso in cui l'utente è nullo.
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaUtenteMailVuota() {

        String messaggio = "L'email non può essere nulla.";

        try {
            utenteService.login(null, "Mattiasapere.123");
        } catch (UserNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testa il caso in cui la password è nulla.
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaUtentePasswordVuoto() {

        String messaggio = "La password non è valida.";

        try {
            utenteService.login("mattiasapere@gmail.com", null);
        } catch (UserNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testa il caso in cui la password o l'email sono errati.
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaUtentePasswordMailErrati() {

        String messaggio = "Email o password errati.";

        try {
            utenteService.login("mattiasapere.com", "1");
        } catch (UserNotLoggedException e) {
            assertEquals(messaggio, e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Testa il caso in cui il logout è errato.
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaLogoutErrato() {

        String messaggio = "Errore durante il logout.";

        try {
            utenteService.logout(null);
        } catch (AccessNotAuthorizedException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }
}