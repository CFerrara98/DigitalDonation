package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.GuestFormException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Classe di test di unità per il guest form
 *
 * @author Mattia Sapere, Fabio Siepe, Marika Spagna Zito
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class GuestFormUT {

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

    @InjectMocks
    private OrganizzazioneSeduteService organizzazioneSeduteService;

    private String codiceFiscaleGuest;
    private String nome;
    private String cognome;
    private String telefono;
    private String patologie;
    private String gruppoSanguigno;

    private GuestForm guestForm;

    public void validaCampi() throws GuestFormException {
        organizzazioneSeduteService.validaCodiceFiscaleGuest(codiceFiscaleGuest);
        organizzazioneSeduteService.validaNome(nome);
        organizzazioneSeduteService.validaCognome(cognome);
        organizzazioneSeduteService.validaTelefono(telefono);
        organizzazioneSeduteService.validaPatologie(patologie);
        organizzazioneSeduteService.validaGruppoSanguigno(gruppoSanguigno);
    }

    /**
     * Verifica che il codice fiscale rispetti il formato
     */
    @Test
    public void VerificaFormatoCodiceFiscaleGuest() {
        codiceFiscaleGuest = "..............";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB";

        final String message = "Il formato del codice fiscale è errato: bisogna inserire solo caratteri alfanumerici.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il nome rispetti il formato
     */
    @Test
    public void VerificaFormatoNome() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela333";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB";

        final String message = "Il formato del nome è errato: bisogna inserire solo caratteri alfabetici.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il cognome rispetti il formato
     */
    @Test
    public void VerificaFormatoCognome() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino333";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB";

        final String message = "Il formato del cognome è errato: bisogna inserire solo caratteri alfabetici.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il numero di telefono rispetti il formato
     */
    @Test
    public void VerificaFormatoTelefono() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "AAAAAAAAAA";
        patologie = "Nessuna";
        gruppoSanguigno = "AB";

        final String message = "Il formato del numero di telefono è errato: bisogna inserire solo caratteri numerici e simboli.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che le patologie rispettino il formato
     */
    @Test
    public void VerificaFormatoPatologie() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = ".";
        gruppoSanguigno = "AB";

        final String message = "Il formato delle patologie è errato: bisogna inserire solo caratteri alfanumerici e simboli composti da almeno 2 caratteri.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il gruppo sanguigno rispetti il formato
     */
    @Test
    public void VerificaFormatoGruppoSanguigno() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "90";

        final String message = "Il formato del gruppo sanguigno è errato, è possibile inserire solo le seguenti combinazioni: 0-, 0+, A-, A+, B-, B+, AB-, AB+";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che tutti i campi rispettino il formato
     */
    @Test
    public void VerificaFormatoSuccessoTotale() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB";

       //when(guestRepository.exists(codiceFiscaleGuest)).thenReturn(false);

       Guest guest = new Guest(codiceFiscaleGuest, nome, cognome, telefono, patologie, gruppoSanguigno);

        try {
            validaCampi();
            organizzazioneSeduteService.salvaGuest(guest);
        } catch (GuestFormException exception) {
            exception.printStackTrace();
        }
    }
}