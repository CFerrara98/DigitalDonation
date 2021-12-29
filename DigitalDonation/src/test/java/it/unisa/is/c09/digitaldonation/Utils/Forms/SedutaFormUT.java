package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.GuestFormException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.SedutaFormException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement.OrganizzazioneSeduteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per il form di creazione di una seduta
 *
 * @author Mattia Sapere, Fabio Siepe, Marika Spagna Zito
 */
@RunWith(MockitoJUnitRunner.class)
public class SedutaFormUT {

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

    private Date dataSeduta;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String CAP;
    private int numeroPartecipanti;
    private Date dataInizioPrenotazione;
    private Date dataFinePrenotazione;

    private Seduta seduta;

    public void validaCampi() throws SedutaFormException {
        organizzazioneSeduteService.validaDataSeduta(dataSeduta);
        organizzazioneSeduteService.validaIndirizzo(indirizzo);
        organizzazioneSeduteService.validaCitta(citta);
        organizzazioneSeduteService.validaProvincia(provincia);
        organizzazioneSeduteService.validaCAP(CAP);
        organizzazioneSeduteService.validaNumeroPartecipanti(numeroPartecipanti);
        organizzazioneSeduteService.validaDataInizioPrenotazioni(seduta);
        organizzazioneSeduteService.validaDataFinePrenotazioni(seduta);
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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
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

        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);

        final String message = "La data seduta inserita è minore della data corrente.";
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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "L’indirizzo inserito non è corretto.";

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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "La città inserita non è corretta: non ammette caratteri numeri.";

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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "Il CAP inserito non è corretto: ammette solo 5 caratteri numerici.";

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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "La provincia inserita non è corretta: ammette solo due caratteri.";

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
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "Il numero di Partecipanti inserito non è corretto: il limite massimo è 9999";

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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "La data inizio partecipazione inserita è maggiore della data seduta.";

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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "La data fine partecipazione inserita è minore della data corrente.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica che il campo dataFinePartecipazione non sia minore della data inizio partecipazione
     */
    @Test
    public void VerificaDataFineSedutaMinorePartecipazione() {
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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
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
        seduta = new Seduta(80l, dataSeduta, null, null, null, 420, dataInizioPrenotazione, dataFinePrenotazione, null, null, null);
        final String message = "La schedulazione di una nuova seduta va a buon fine.";

        try {
            validaCampi();
        } catch (SedutaFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

}
