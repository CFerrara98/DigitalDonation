package it.unisa.is.c09.digitaldonation.Utils.Forms;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per il guest form
 *
 * @author Mattia Sapere
 */
@RunWith(MockitoJUnitRunner.class)
public class GuestFormValidateUT {

    @Mock
    private BindingResult errors;

    @InjectMocks
    private GuestFormValidate guestFormValidate;

    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String telefono;
    private String patologie;
    private String gruppoSanguigno;

    private GuestForm guestForm;

    /**
     * Verifica che il campo nome sia corretto
     */
    @Test
    public void VerificaFormatoNome() {
        codiceFiscale = "SBLQYD34M51G902W";
        nome = "";
        cognome = "Sapere";
        telefono = "3208146934";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guestForm = new GuestForm(codiceFiscale, nome, cognome, telefono, patologie, gruppoSanguigno);
        guestFormValidate.validate(guestForm, errors);

        assertEquals("errore", "", guestForm.getNome());
    }

    /**
     * Verifica che il campo cognome sia corretto
     */
    @Test
    public void VerificaFormatoCognome() {
        codiceFiscale = "SBLQYD34M51G902W";
        nome = "Mattia";
        cognome = "";
        telefono = "3208146934";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guestForm = new GuestForm(codiceFiscale, nome, cognome, telefono, patologie, gruppoSanguigno);
        guestFormValidate.validate(guestForm, errors);

        assertEquals("errore", "", guestForm.getCognome());
    }

    /**
     * Verifica che il campo telefono sia corretto
     */
    @Test
    public void VerificaFormatoTelefono() {
        codiceFiscale = "SBLQYD34M51G902W";
        nome = "Mattia";
        cognome = "Sapere";
        telefono = "";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guestForm = new GuestForm(codiceFiscale, nome, cognome, telefono, patologie, gruppoSanguigno);
        guestFormValidate.validate(guestForm, errors);

        assertEquals("errore", "", guestForm.getTelefono());
    }

    /**
     * Verifica che il campo codice fiscale sia corretto
     */
    @Test
    public void VerificaFormatoCodiceFiscale() {
        codiceFiscale = "";
        nome = "Mattia";
        cognome = "Sapere";
        telefono = "3208156934";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guestForm = new GuestForm(codiceFiscale, nome, cognome, telefono, patologie, gruppoSanguigno);
        guestFormValidate.validate(guestForm, errors);

        assertEquals("errore", "", guestForm.getCodiceFiscale());
    }

    /**
     * Verifica che il campo patologie sia corretto
     */
    @Test
    public void VerificaFormatoPatologie() {
        codiceFiscale = "SBLQYD34M51G902W";
        nome = "Mattia";
        cognome = "Sapere";
        telefono = "3208156934";
        patologie = "";
        gruppoSanguigno = "AB+";

        guestForm = new GuestForm(codiceFiscale, nome, cognome, telefono, patologie, gruppoSanguigno);
        guestFormValidate.validate(guestForm, errors);

        assertEquals("errore", "", guestForm.getPatologie());
    }

    /**
     * Verifica che il campo gruppo sanguigno sia corretto
     */
    @Test
    public void VerificaFormatoGruppoSanguigno() {

        codiceFiscale = "SBLQYD34M51G902W";
        nome = "Mattia";
        cognome = "Sapere";
        telefono = "3208156934";
        patologie = "Nessuna";
        gruppoSanguigno = "";

        guestForm = new GuestForm(codiceFiscale, nome, cognome, telefono, patologie, gruppoSanguigno);
        guestFormValidate.validate(guestForm, errors);

        assertEquals("errore", "", guestForm.getGruppoSanguigno());
    }

    /**
     * Verifica che tutti i campi rispettino il formato
     */
    @Test
    public void VerificaSuccessoGuest() {
        codiceFiscale = "SBLQYD34M51G902W";
        nome = "Mattia";
        cognome = "Sapere";
        telefono = "3208156934";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guestForm = new GuestForm(codiceFiscale, nome, cognome, telefono, patologie, gruppoSanguigno);
        guestFormValidate.validate(guestForm, errors);

        assertEquals("Data di fine prenotazione non valida.", guestForm, guestForm);
    }

    @Test
    public void VerificaSupports() {

        assertEquals("Volevo Solo più branche coverage", false, guestFormValidate.supports(GuestFormValidate.class));

    }

}