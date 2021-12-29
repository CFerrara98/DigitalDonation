package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.PasswordNonValidaException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

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

        String messaggio = "Il campo mail non può essere nullo";

        try {
            utenteService.login(null, "Mattiaspere.123");
        } catch (PasswordNonValidaException e) {
            assertEquals(messaggio, e.getMessage());
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

        String messaggio = "Il campo password non può essere nullo";

        try {
            utenteService.login("mattiasapere@gmail.com", "Password123");
        } catch (PasswordNonValidaException e) {
            assertEquals(messaggio, e.getMessage());
        }
    }


}
