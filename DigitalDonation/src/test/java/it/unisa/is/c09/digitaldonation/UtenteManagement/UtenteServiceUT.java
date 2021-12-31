package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Test di unità per la classe UtenteService; tipologia di test:
 * whitebox strategia: branch coverage.
 *
 * @author Mattia Sapere, Fabio Siepe, Marika Spagna Zito
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
     * Testa il caso in cui l'utente riesce a loggarsi..
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaLoginSuccesso() {
        Utente utente = new Utente();

        when(utenteRepository.findByEmailAndPassword("fabio.siepe@gmail.com", "password123")).thenReturn(utente);

        try {
            utenteService.login("fabio.siepe@gmail.com", "password123");
        } catch (UserNotLoggedException e) {
            fail("errore nel login!");
        } catch (NoSuchAlgorithmException e) {
            fail("errore nel login!");
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

    /**
     * Testa il caso in cui il logout è ha successo.
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaLogoutSuccesso() {

        Utente utente = new Utente();

        try {
            utenteService.logout(utente);
        } catch (AccessNotAuthorizedException e) {
            fail(("Errore durante il logout"));
        }
    }


    /**
     * controllaValida nel caso in cui l'email è null
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaValidaEmailNull() {

        String messaggio = "Email o password errati, per favore riprova.";
        String eMail = null;

        try {
            utenteService.validaMail(eMail);
        } catch (MailNonValidaException e) {
            assertEquals(messaggio, e.getMessage());
        } catch (MailNonEsistenteException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    /**
     * controllaValida nel caso in cui l'email non rispetta la regex
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaValidaEmailFormato() {

        String messaggio = "Email o password errati, per favore riprova.";
        String eMail = "....";

        try {
            utenteService.validaMail(eMail);
        } catch (MailNonValidaException e) {
            assertEquals(messaggio, e.getMessage());
        } catch (MailNonEsistenteException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    /**
     * controllaValida nel caso in cui non esiste nessun utenten con questa mail
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaValidaEmailNonEsistente() {

        String messaggio = "Email o password errati, per favore riprova.";
        String email = "fabio.siepe@gmail.com";

        when(utenteRepository.existsUtenteByEmail(email)).thenReturn(false);
        try {
            utenteService.validaMail(email);
        } catch (MailNonValidaException e) {
            assertEquals(messaggio, e.getMessage());
        } catch (MailNonEsistenteException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }

    /**
     * controllaValida nel caso in cui la mail viene validata ed esite un utente
     *
     * @result Il test è superato se il messaggio generato dal sistema è uguale a quello
     * previsto dall'oracolo.
     */
    @Test
    public void controllaValidaEmailSuccesso() {

        String messaggio = "Email o password errati, per favore riprova.";
        String email = "fabio.siepe@gmail.com";
        Utente utente = new Utente();
         when(utenteRepository.existsUtenteByEmail(email)).thenReturn(true);
        try {
            assertEquals(email, utenteService.validaMail(email));
        } catch (MailNonValidaException e) {
            assertEquals(messaggio, e.getMessage());
        } catch (MailNonEsistenteException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }


}