package it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement;


import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.*;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaFormValidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;
import static org.mockito.Mockito.when;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Classe di test di unità per il form di creazione di una seduta
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@RunWith(MockitoJUnitRunner.class)
public class OrganizzazioneSeduteServiceUT {

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

    @Mock
    private SedutaFormValidate sedutaFormValidate;


    @InjectMocks
    private OrganizzazioneSeduteService organizzazioneSeduteService;

    private Date dataSeduta;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String CAP;
    private int numeroPartecipanti;
    private Date dataInizioPrenotazione;
    private Date dataFinePrenotazione;

    private SedutaForm sedutaForm;


    public void validaCampi() throws SedutaFormException {

        organizzazioneSeduteService.validaDataSeduta(dataSeduta);
        organizzazioneSeduteService.validaIndirizzo(indirizzo);
        organizzazioneSeduteService.validaCitta(citta);
        organizzazioneSeduteService.validaProvincia(provincia);
        organizzazioneSeduteService.validaCAP(CAP);
        organizzazioneSeduteService.validaNumeroPartecipanti(numeroPartecipanti);
        organizzazioneSeduteService.validaDataInizioPrenotazioni(sedutaForm);
        organizzazioneSeduteService.validaDataFinePrenotazioni(sedutaForm);
    }

    /**
     * Verifica che il campo dataSeduta non sia null
     */
    @Test
    public void VerificaDataSedutaNotNull() {
        dataSeduta = null;
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data seduta inserita non ispetta il formato: gg/mm/aaaa";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataSeduta rispetti il formato
     */
    @Test
    public void VerificaFormatoDataSeduta() {
        Calendar myCalendar = new GregorianCalendar(202, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data seduta inserita non ispetta il formato: gg/mm/aaaa";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataSeduta sia dopo la data di oggi
     */
    @Test
    public void VerificaDataSeduta() {
        Calendar myCalendar = new GregorianCalendar(2010, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);

        final String message = "La data seduta inserita è minore della data corrente.";
        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo indirizzo non sia null
     */
    @Test
    public void VerificaFormatoIndirizzoNotNull() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = null;
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "L’indirizzo inserito non è corretto.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo indirizzo sia corretto
     */
    @Test
    public void VerificaFormatoIndirizzo() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "V";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "L’indirizzo inserito non è corretto.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo città non sia null
     */
    @Test
    public void VerificaCittaNotNull() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = null;
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La città inserita non è corretta: non ammette caratteri numeri.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo città sia corretto
     */
    @Test
    public void VerificaFormatoCitta() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Sal3rno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La città inserita non è corretta: non ammette caratteri numeri.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo CAP no sia null
     */
    @Test
    public void VerificaCAPNotNull() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = null;
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "Il CAP inserito non è corretto: ammette solo 5 caratteri numerici.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo CAP sia corretto
     */
    @Test
    public void VerificaFormatoCAP() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "821000";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "Il CAP inserito non è corretto: ammette solo 5 caratteri numerici.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo provincia non sia null
     */
    @Test
    public void VerificaProvinciaNotNull() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = null;
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La provincia inserita non è corretta: ammette solo due caratteri.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo provincia rispetti il formato
     */
    @Test
    public void VerificaFormatoProvincia() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SALE";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La provincia inserita non è corretta: ammette solo due caratteri.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo numero partecipanti sia maggiore di 0
     */
    @Test
    public void VerificaNumeroPartecipantiMaggioreDiZero() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = -23;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "Il numero di Partecipanti inserito non è corretto: il limite massimo è 9999";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo numero partecipanti rispetti il fomrato
     */
    @Test
    public void VerificaFormatoNumeroPartecipanti() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 99999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "Il numero di Partecipanti inserito non è corretto: il limite massimo è 9999";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataInizioPartecipazione sia null
     */
    @Test
    public void VerificaDataInizioSedutaNotNull() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        dataInizioPrenotazione = null;
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data inizio partecipazione inserita non rispetta il formato: gg/mm/aaaa.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataInizioPartecipazione rispetti il formato
     */
    @Test
    public void VerificaFormatoDataInizioSeduta() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(202, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data inizio partecipazione inserita non rispetta il formato: gg/mm/aaaa.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataInizioPartecipazione non sia minore della data corrente
     */
    @Test
    public void VerificaDataInizioSedutaCorrente() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Cava de tirreni";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2020, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data inizio partecipazione inserita è minore della data corrente";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataInizioPartecipazione non sia maggiore della data della seduta
     */
    @Test
    public void VerificaDataInizioSedutaMaggioreSeduta() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 5, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data inizio partecipazione inserita è maggiore della data seduta.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }



    /**
     * Verifica che il campo dataFinePartecipazione non sia null
     */
    @Test
    public void VerificaDataFineSedutaNotNull() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        dataInizioPrenotazione = myCalendar1.getTime();
        dataFinePrenotazione = null;
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data fine partecipazione inserita non rispetta il formato: gg/mm/aaaa.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }
    /**
     * Verifica che il campo dataFinePartecipazione rispetti il formato
     */
    @Test
    public void VerificaFormatoDataFineSeduta() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(202, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data fine partecipazione inserita non rispetta il formato: gg/mm/aaaa.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataFinePartecipazione non sia minore della data corrente
     */
    @Test
    public void VerificaDataFineSedutaCorrente() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2020, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data fine partecipazione inserita è minore della data corrente.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataFinePartecipazione non sia dopo la data della seduta
     */
    @Test
    public void VerificaDataFineSedutaMaggioreSeduta() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 5, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data fine partecipazione inserita è maggiore della data seduta.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataFinePartecipazione non sia minore della data inizio prenotazione
     */
    @Test
    public void VerificaDataFineSedutaMinorePrenotazione() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 3, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La data fine partecipazione inserita è minore della data inizio partecipazione.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che tutti i campi rispettino il formato
     */
    @Test
    public void VerificaFormatoSuccessoSeduta() {
        Calendar myCalendar = new GregorianCalendar(2022, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        Time orarioInizio = new Time(8,30,00);
        Time orarioFine = new Time(17,00,00);
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        final String message = "La schedulazione di una nuova seduta va a buon fine.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica feedbackDonatore con il campo codiceFisacle null
     */
    /*@Test
    public void VerificaFeedbackDonatoreCodiceFiscaleNull() {
        Donatore donatore;
        boolean fedback = true;
        Long idSeduta = 420l;
        Calendar myCalendar1 = new GregorianCalendar(1999, 8, 10);
        Date dataDiNacita = myCalendar1.getTime();
        donatore = new Donatore(null, "Fabio", "Siepe", "fabio.siepe@gmail.com", "Password123", "Via Di Casa", dataDiNacita, "Mercato S.Severino", null, null);
        final String message = "Il campo id non può essere null.";
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, fedback, idSeduta);
        } catch (CannotRelaseFeedbackException exception) {
            assertEquals(message, exception.getMessage());
        }
    }*/


    /**
     * Verifica feedbackDonatore nel caso in cui l'id seduta non corrisponde a nessuna seduta
     */
    /*@Test
    public void VerificaFeedbackDonatorePositivoSedutaNonTrovata() {
        Donatore donatore;
        boolean fedback = true;
        Long idSeduta = 420l;
        Calendar myCalendar1 = new GregorianCalendar(1999, 8, 10);
        Date dataDiNacita = myCalendar1.getTime();
        donatore = new Donatore("SPIFBA99M10F138Y", "Fabio", "Siepe", "fabio.siepe@gmail.com", "Password123", "Via Di Casa", dataDiNacita, "Mercato S.Severino", null, null);
        final String message = "Errore! Seduta non trovata.";
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, fedback, idSeduta);
        } catch (CannotRelaseFeedbackException exception) {
            assertEquals(message, exception.getMessage());
        }
    }*/



    /**
     * Verifica feedbackDonatore nel caso in cui il feedback sia false(negativo)
     */
   /* @Test
    public void VerificaFeedbackDonatoreNegativo() {
        Donatore donatore;
        boolean fedback = false;
        Long idSeduta = 420l;
        Calendar myCalendar1 = new GregorianCalendar(1999, 8, 10);
        Date dataDiNacita = myCalendar1.getTime();
        donatore = new Donatore("SPIFBA99M10F138Y", "Fabio", "Siepe", "fabio.siepe@gmail.com", "Password123", "Via Di Casa", dataDiNacita, "Mercato S.Severino", null, null);
        final String message = "Il campo id non può essere null.";
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, fedback, idSeduta);
        } catch (CannotRelaseFeedbackException exception) {
            assertEquals(message, exception.getMessage());
        }
    }*/

    /**
     * Verifica feedbackDonatore nel caso di Successo
     */
    /*@Test
    public void VerificaFeedbackDonatoreSuccesso() {
        Donatore donatore;
        boolean fedback = true;
        Long idSeduta = 420l;
        Calendar myCalendar1 = new GregorianCalendar(1999, 8, 10);
        Date dataDiNacita = myCalendar1.getTime();
        donatore = new Donatore("SPIFBA99M10F138Y", "Fabio", "Siepe", "fabio.siepe@gmail.com", "Password123", "Via Di Casa", dataDiNacita, "Mercato S.Severino", null, null);
        Seduta seduta = new Seduta();
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(seduta);
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, fedback, idSeduta);
        } catch (CannotRelaseFeedbackException exception) {
            fail("errore");
        }
    }*/

    /**
     * Verifica MonitoraggioSeduta nel caso in cui l'id seduta è null
     */
    @Test
    public void VerificaMonitoraggioSeddutaIdnull() {
        Long idSeduta = null;
        final String message = "Il campo id della seduta non può essere null.";
        try {
            organizzazioneSeduteService.monitoraggioSeduta(idSeduta);
        } catch (CannotLoadDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }


    /**
     * Verifica MonitoraggioSeduta nel caso in cui l'id seduta non corrisponde a nessuna seduta
     */
    @Test
    public void VerificaMonitoraggioSeddutaSedutaNull() {
        Long idSeduta = 420l;
        final String message = "Non è stata trovata nessuna seduta con questo id.";
        try {
            organizzazioneSeduteService.monitoraggioSeduta(idSeduta);
        } catch (CannotLoadDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica MonitoraggioSeduta nel caso in cui ha successo
     */
    @Test
    public void VerificaMonitoraggioSeddutaSuccesso() {
        Long idSeduta = 420l;
        Seduta seduta = new Seduta();
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(seduta);
        try {
            organizzazioneSeduteService.monitoraggioSeduta(idSeduta);
        } catch (CannotLoadDataRepositoryException exception) {
            fail("errore!");
        }
    }

    /**
     * Verifica schedulazione sedute nel caso in cui l'id della seduta sia null
     */
    @Test
    public void VerificaSchedulazioneSedutaIdSedutaNull() {
        Long idSeduta = null;
        Seduta seduta = new Seduta();
        seduta.setIdSeduta(idSeduta);
        final String message = "Il campo id della seduta non può essere null.";
        try {
            organizzazioneSeduteService.schedulazioneSeduta(seduta);
        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica schedulazione sedute quadno va a buon fine
     */
    @Test
    public void VerificaSchedulazioneSedutaCorretta() {
        Long idSeduta = null;
        Seduta seduta = new Seduta(2l, null, null, null, null, 0, null, null, null, null, null);

        final String message = "Il campo id della seduta non può essere null.";
        try {
            assertEquals(seduta, organizzazioneSeduteService.schedulazioneSeduta(seduta));
        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica modificaSeduta nel caso in cui la seduta è null
     */
    /*@Test
    public void VerificaModificaSedutaSedutaNull() {
        SedutaForm sedutaForm = null;
        Long idSeduta = 2l;
        Utente utente = new Utente();
        final String message = "La seduta non può essere null";
        try {
           organizzazioneSeduteService.modificaSeduta(sedutaForm, idSeduta, utente);
        } catch (CannotUpdateDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }*/

    /**
     * Verifica modificaSeduta nel caso in cui l'id della seduta è null
     */
    /*@Test
    public void VerificaModificaSedutaIdSedutaNull() {
        Seduta seduta = new Seduta(2l, null, null, null, null, 0, null, null, null, null, null);
        Utente utente = new Utente("PCFKVN01A14D390G", "Kevin", "Pacifico", "kevinpacifico2001@gmail.com", "Kevin@14");
        Long idSeduta = null;
        final String message = "La seduta da modificare non può essere null";
        try {
            organizzazioneSeduteService.modificaSeduta(seduta, idSeduta, utente);
        } catch (CannotUpdateDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }*/

    /**
     * Verifica modificaSeduta nel caso in cui va a buon fine
     */
    /*@Test
    public void VerificaModificaSedutaSuccesso() {
        Seduta seduta = new Seduta(2l, null, null, null, null, 0, null, null, null, null, null);
        Long idSeduta = 2l;
        final String message = "La seduta da modificare non può essere null successo";
        try {
            assertEquals(seduta, organizzazioneSeduteService.modificaSeduta(seduta, idSeduta));
        } catch (CannotUpdateDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }*/


    /**
     * Verifica eliminaSeduta nel caso in cui l'id sia null
     */
    @Test
    public void VerificaEliminaSedutaidSedutaNull() {
        Long idSeduta = 745l;
        final String message = "Errore durante l'eliminazione della seduta";
        try {
            organizzazioneSeduteService.eliminaSeduta(idSeduta);
        } catch (CannotDeleteDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica eliminaSeduta nel caso di success
     */
    @Test
    public void VerificaEliminaSedutaSuccesso() {
        Long idSeduta = null;
        Seduta seduta = new Seduta();
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(seduta);
        try {
            organizzazioneSeduteService.eliminaSeduta(idSeduta);
        } catch (CannotDeleteDataRepositoryException exception) {
            fail("errore");
        }
    }
    /**
     * Verifica visualizzaSeduta nel caso in cui l'id sia null
     */
    @Test
    public void VerificaVisualizzaSedutaIdNull() {
        Long idSeduta = null;
        final String message = "La seduta da visualizzare deve esistere";
        try {
            organizzazioneSeduteService.visualizzaSeduta(idSeduta);
        } catch (CannotLoadDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica visualizzaSeduta nel caso in cui vada tutto bene
     */
    @Test
    public void VerificaVisualizzaSedutaSuccesso() {
        Long idSeduta = 745l;
        Seduta seduta = new Seduta();
        seduta.setIdSeduta(idSeduta);
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(seduta);
        try {
            assertEquals(seduta, organizzazioneSeduteService.visualizzaSeduta(idSeduta));
        } catch (CannotLoadDataRepositoryException exception) {
            fail("error");
        }
    }

    /**
     * Verifica visualizzaSeduta nel caso in cui vada tutto bene
     */
    @Test
    public void VerificaVisualizzaElencoSedute() {
        try {
            assertNotNull(organizzazioneSeduteService.visualizzaElencoSedute());
        } catch (CannotLoadDataRepositoryException exception) {
            fail("Nessuna seduta trovata");
        }
    }
}
