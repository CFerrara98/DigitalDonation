package it.unisa.is.c09.digitaldonation.organizzazionesedutemanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.GuestFormException;
import it.unisa.is.c09.digitaldonation.model.entity.Guest;
import it.unisa.is.c09.digitaldonation.model.entity.Seduta;
import it.unisa.is.c09.digitaldonation.model.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

/**
 * Classe di test di unità per il guest form
 *
 * @author Mattia Sapere, Fabio Siepe, Marika Spagna Zito
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizzazioneSeduteServiceGuestUT {


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

    private Guest guest;

    public void validaCampi() throws GuestFormException {
        organizzazioneSeduteService.validaCodiceFiscaleGuest(codiceFiscaleGuest);
        organizzazioneSeduteService.validaNome(nome);
        organizzazioneSeduteService.validaCognome(cognome);
        organizzazioneSeduteService.validaTelefono(telefono);
        organizzazioneSeduteService.validaPatologie(patologie);
        organizzazioneSeduteService.validaGruppoSanguigno(gruppoSanguigno);
    }

    /**
     * Verifica che il codice fiscale non sia null
     */
    @Test
    public void VerificaCodiceFiscaleGuestNotNull() {
        codiceFiscaleGuest = null;
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        final String message = "Il formato del codice fiscale è errato: bisogna inserire solo caratteri alfanumerici.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
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
        gruppoSanguigno = "AB+";

        final String message = "Il formato del codice fiscale è errato: bisogna inserire solo caratteri alfanumerici.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il nome non sia null
     */
    @Test
    public void VerificaNomeNotNull() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = null;
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        final String message = "Il formato del nome è errato: bisogna inserire solo caratteri alfabetici.";

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
        gruppoSanguigno = "AB+";

        final String message = "Il formato del nome è errato: bisogna inserire solo caratteri alfabetici.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il cognome non sia null
     */
    @Test
    public void VerificaCognomeNotNull() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = null;
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        final String message = "Il formato del cognome è errato: bisogna inserire solo caratteri alfabetici.";

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
        gruppoSanguigno = "AB+";

        final String message = "Il formato del cognome è errato: bisogna inserire solo caratteri alfabetici.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il numero di telefono non sia null
     */
    @Test
    public void VerificaTelefonoNotNull() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = null;
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        final String message = "Il formato del numero di telefono è errato: bisogna inserire solo caratteri numerici e simboli.";

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
        gruppoSanguigno = "AB+";

        final String message = "Il formato del numero di telefono è errato: bisogna inserire solo caratteri numerici e simboli.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che le patologie non sia null
     */
    @Test
    public void VerificaPatologieNotNUll() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = null;
        gruppoSanguigno = "AB+";

        final String message = "Il formato delle patologie è errato: bisogna inserire solo caratteri alfanumerici e simboli composti da almeno 2 caratteri.";

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
        gruppoSanguigno = "AB+";

        final String message = "Il formato delle patologie è errato: bisogna inserire solo caratteri alfanumerici e simboli composti da almeno 2 caratteri.";

        try {
            validaCampi();
        } catch (GuestFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il gruppo sanguigno non sia null
     */
    @Test
    public void VerificaGruppoSanguignoNotNull() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = null;

        final String message = "Il formato del gruppo sanguigno è errato, è possibile inserire solo le seguenti combinazioni: 0-, 0+, A-, A+, B-, B+, AB-, AB+";

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
        gruppoSanguigno = "AB+";



        guest = new Guest(codiceFiscaleGuest, nome, cognome, telefono, patologie, gruppoSanguigno);

        try {
            validaCampi();
            organizzazioneSeduteService.salvaGuest(guest);
        } catch (GuestFormException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Verifica inserimento Guest nel caso in cui l'id della seduta è null
     */
    @Test
    public void VerificaInserimentoGuestIdSedutaNull() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guest = new Guest(codiceFiscaleGuest, nome, cognome, telefono, patologie, gruppoSanguigno);

        Long idSeduta = null;
        final String message = "Il campo id della seduta non può essere null.";
        try {
            organizzazioneSeduteService.inserimentoGuest(idSeduta, guest);
        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica inserimento Guest nel caso in cui il codice fiscale del guest è null
     */
    @Test
    public void VerificaInserimentoGuestCodiceFiscaleGuestNull() {
        codiceFiscaleGuest = null;
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guest = new Guest(codiceFiscaleGuest, nome, cognome, telefono, patologie, gruppoSanguigno);

        Long idSeduta = 745l;
        final String message = "il campo CF del guest non può essere null";
        try {
            organizzazioneSeduteService.inserimentoGuest(idSeduta, guest);
        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica inserimento Guest nel caso in cui l'id non esiste sul db
     */
    @Test
    public void VerificaInserimentoGuestNoDB() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";
        guest = new Guest(codiceFiscaleGuest, nome, cognome, telefono, patologie, gruppoSanguigno);
        Long idSeduta = 745l;
        final String message = "Il guest è gia presente nella seduta";
        when(sedutaRepository.existsByIdSedutaAndListaGuest_CodiceFiscaleGuest(idSeduta, guest.getcodiceFiscaleGuest())).thenReturn(true);
        try {
            organizzazioneSeduteService.inserimentoGuest(idSeduta, guest);
        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
            ;
        }
    }

    /**
     * Verifica inserimento Guest nel caso in cui l'id del guest è null
     */
    @Test
    public void VerificaInserimentoGuestSuccesso() {
        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guest = new Guest(codiceFiscaleGuest, nome, cognome, telefono, patologie, gruppoSanguigno);

        Long idSeduta = 745l;
        Seduta seduta = new Seduta();
        when(sedutaRepository.existsByIdSedutaAndListaGuest_CodiceFiscaleGuest(idSeduta, guest.getcodiceFiscaleGuest())).thenReturn(false);
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(seduta);
        try {
            assertEquals(guest, organizzazioneSeduteService.inserimentoGuest(idSeduta, guest));
        } catch (CannotSaveDataRepositoryException exception) {
            fail("errore");
        }
    }
}

