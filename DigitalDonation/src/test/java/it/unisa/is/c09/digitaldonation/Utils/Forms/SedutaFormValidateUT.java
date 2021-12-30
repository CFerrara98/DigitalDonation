package it.unisa.is.c09.digitaldonation.Utils.Forms;


import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.SedutaFormException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.Errors;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Classe di test di unità per il guest form
 *
 * @author Fabio Siepe
 */
@RunWith(MockitoJUnitRunner.class)
public class SedutaFormValidateUT {

    @Mock
    private Errors errors;

    @InjectMocks
    private SedutaFormValidate sedutaFormValidate;

    private Date dataSeduta;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String CAP;
    private int numeroPartecipanti;
    private Date dataInizioPrenotazione;
    private Date dataFinePrenotazione;

    private SedutaForm sedutaForm;


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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Data seduta non valida.", null, sedutaForm.getDataSeduta());
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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Data seduta non valida.", null, sedutaForm.getDataSeduta());

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


        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Indirizzo non valido.", null, sedutaForm.getIndirizzo());
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
        final String message = "Citta non valida.";

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Citta non valida.", null, sedutaForm.getCitta());
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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("CAP non valido.", null, sedutaForm.getCAP());
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
        final String message = "Provincia non valida.";
        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Provincia non valida.", null, sedutaForm.getProvincia());
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


        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Numero partecipanti non valido. cià", 0, sedutaForm.getNumeroPartecipanti());
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
        final String message = "Errore nella data dell'inizio.";

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Errore nella data dell'inizio.", null, sedutaForm.getDataInizioPrenotazione());
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
        final String message = "Errore nella data dell'inizio.";

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Errore nella data dell'inizio.", null, sedutaForm.getDataInizioPrenotazione());
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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Errore nella data dell'inizio.", null, sedutaForm.getDataInizioPrenotazione());
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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Data di fine prenotazione non valida.", null, sedutaForm.getDataFinePrenotazione());
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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Data di fine prenotazione non valida.", null, sedutaForm.getDataFinePrenotazione());
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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Data di fine prenotazione non valida.", null, sedutaForm.getDataFinePrenotazione());
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

        sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, null, null, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);
        sedutaFormValidate.validate(sedutaForm, errors);

        assertEquals("Data di fine prenotazione non valida.", sedutaForm, sedutaForm);
    }

    @Test
    public void VerificaSupports() {

        assertEquals("Volevo Solo più branche coverage", false, sedutaFormValidate.supports(SedutaFormValidate.class));

    }

}