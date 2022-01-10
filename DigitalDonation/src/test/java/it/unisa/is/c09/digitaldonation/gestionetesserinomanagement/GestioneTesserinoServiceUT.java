package it.unisa.is.c09.digitaldonation.gestionetesserinomanagement;


import it.unisa.is.c09.digitaldonation.erroremanagement.gestionetesserinoerror.TesserinoFormException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.model.entity.*;
import it.unisa.is.c09.digitaldonation.model.repository.*;
import it.unisa.is.c09.digitaldonation.utils.form.SedutaFormValidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import it.unisa.is.c09.digitaldonation.utentemanagement.MailSingletonSender;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per la gestione del tesserino
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@RunWith(MockitoJUnitRunner.class)
public class GestioneTesserinoServiceUT {

    @Mock
    private MailSingletonSender mailSingletonSender;


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
    private GestioneTesserinoService gestioneTesserinoService;

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Image image;
    private Date dataDiNascita;
    private String luogoDiNascita;
    private String residenza;
    private String email;
    private String gruppoSanguigno;
    private String rh;
    private String altreIndicazioni;
    private int numeroMatricola;
    private int numeroTessera;
    private Date dataRilascio;
    private Date dataDonazione;
    private String tipoDonazione;
    private Tesserino tesserino;
    private Donatore donatore;
    private Utente utente;
    private Donazione donazione;
    private Date dataProssimaDisponibilita;

    /**
     * Funzione per il testing del form
     */
    public void validaCampi() throws TesserinoFormException {
        tesserino = null;
        when(tesserinoRepository.findByDonatoreUtenteCodiceFiscale(codiceFiscale)).thenReturn(tesserino);

        Donatore donatore = new Donatore("ACNCZY45X38I793C", null, null, "test@gmai.com", null, null, null, null, null, null);
        when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscale)).thenReturn(donatore);

        gestioneTesserinoService.validaNome(nome);
        gestioneTesserinoService.validaCognome(cognome);
        gestioneTesserinoService.validaCodiceFiscale(codiceFiscale);
        //gestioneTesserinoService.validaImage(image);
        gestioneTesserinoService.validaDataDiNascita(dataDiNascita);
        gestioneTesserinoService.validaLuogoDiNascita(luogoDiNascita);
        gestioneTesserinoService.validaResidenza(residenza);
        gestioneTesserinoService.validaEmail(email, codiceFiscale);
        gestioneTesserinoService.validaGruppoSanguigno(gruppoSanguigno);
        gestioneTesserinoService.validaRh(rh);
        gestioneTesserinoService.validaAltreIndicazioni(altreIndicazioni);
        gestioneTesserinoService.validaNumeroMatricola(numeroMatricola);
        gestioneTesserinoService.validaNumeroTessera(numeroTessera);
        gestioneTesserinoService.validaDataRilascio(dataRilascio);
        gestioneTesserinoService.validaDataDonazione(dataDonazione);
        gestioneTesserinoService.validaTipoDonazione(tipoDonazione);
    }

    /**
     * Verifica creazioneTesserino nel caso in cui il tesserino è null
     */
    @Test
    public void VerificaCreazioneTesserinoNull() {
        tesserino = null;
        utente = null;
        donatore = null;

        final String message = "Errore, il tesserino è null";
        try {
            gestioneTesserinoService.creazioneTesserino(donatore, tesserino, donazione);

        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }

    }

    /**
     * Verifica creazioneTesserino nel caso in cui il tesserino è gia esistente
     */
    @Test
    public void VerificaCreazioneTesserinoGiaEsistente() {
        tesserino = new Tesserino();
        utente = new Utente();
        donatore = new Donatore();
        when(tesserinoRepository.findTesserinoByIdTessera(tesserino.getIdTessera())).thenReturn(tesserino);
        final String message = "Il tesserino già esiste";
        try {
            gestioneTesserinoService.creazioneTesserino(donatore, tesserino, donazione);

        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }

    }

    /**
     * Verifica creazioneTesserino nel caso in cui il tesserino viene creato
     */
    @Test
    public void VerificaCreazioneTesserinoSuccesso() {
        tesserino = new Tesserino();
        utente = new Utente();
        donatore = new Donatore();
        when(tesserinoRepository.findTesserinoByIdTessera(tesserino.getIdTessera())).thenReturn(null);
        when(mailSingletonSender.sendEmailCreazioneAccount(donatore)).thenReturn("123456");
        try {
            gestioneTesserinoService.creazioneTesserino(donatore, tesserino, donazione);
        } catch (CannotSaveDataRepositoryException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Verifica autodichiarazioneDiIndisponibilità nel caso in cui la data di prossima indisponibilità è null
     */
    @Test
    public void VerificaAutodichiarazioneIndisponibilitaDataProssimaIndisponibilitàNull() {
        Indisponibilita indisponibilita = new Indisponibilita(null, "Mal di testa", "Dr. peppe");
        final String message = "Errore, la data è null";
        try {
            gestioneTesserinoService.autodichiarazioneIndisponibilita(indisponibilita);

        } catch (CannotSaveDataRepositoryException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica autodichiarazioneDiIndisponibilità nel caso in cui ha successo
     */
    @Test
    public void VerificaAutodichiarazioneIndisponibilitaSuccesso() {
        Calendar dataIndisponibilita = new GregorianCalendar(2022, 5, 15);
        Indisponibilita indisponibilita = new Indisponibilita(dataIndisponibilita.getTime(), "Mal di testa", "Dr. peppe");
        indisponibilita.setCodiceFiscaleDonatore("ACNCZY45X38I793C");

        List<Donatore> listaDonatore = new ArrayList<>();
        Donatore donatore1 = new Donatore("ACNCZY45X38I793C", null, null, null, null, null, null, null, null, null);
        Donatore donatore2 = new Donatore("AFCLCW22Z02F362J", null, null, null, null, null, null, null, null, null);
        listaDonatore.add(donatore1);
        listaDonatore.add(donatore2);

        List<Seduta> listaSedute = new ArrayList<>();
        Calendar dataSeduta1 = new GregorianCalendar(2022, 4, 15);
        Calendar dataSeduta2 = new GregorianCalendar(2022, 6, 15);
        Seduta seduta1 = new Seduta(52l, dataSeduta1.getTime(), null, null, null, 0, null, null, null, null, listaDonatore);
        Seduta seduta2 = new Seduta(52l, dataSeduta2.getTime(), null, null, null, 0, null, null, null, null, listaDonatore);
        listaSedute.add(seduta1);
        listaSedute.add(seduta2);

        when(sedutaRepository.findAll()).thenReturn(listaSedute);
        try {
            gestioneTesserinoService.autodichiarazioneIndisponibilita(indisponibilita);
        } catch (CannotSaveDataRepositoryException exception) {
            fail("Errore Successo autodichiarazione di indisponibilita");
        }
    }


    /**
     * Verifica validaNome nel caso in cui il nome è null
     */
    @Test
    public void verificaValidaNomeNull() {
        image = null;
        nome = null;
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno (SA)";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il nome non rispetta il formato.  Inserire solo caratteri alfabetici.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaNome nel caso in cui il nome ha il formato sbagliato
     */
    @Test
    public void verificaValidaNomeRegex() {
        image = null;
        nome = "Marika333";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno (SA)";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il nome non rispetta il formato.  Inserire solo caratteri alfabetici.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaCognome nel caso in cui il cognome è null
     */
    @Test
    public void verificavalidaCognomeNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = null;
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il cognome non rispetta il formato. Inserire solo caratteri alfabetici.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaCognome nel caso in cui il cognome è nel formato diverso
     */
    @Test
    public void verificavalidaCognomeFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe333";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il cognome non rispetta il formato. Inserire solo caratteri alfabetici.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaCodiceFiscale nel caso in cui il coodiceFiscale è null
     */
    @Test
    public void verificavalidaCodiceFiscaleNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = null;
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il CF non rispetta il formato.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }


    /**
     * Verifica validaCodiceFiscale nel caso in cui il coodiceFiscale ha un formato errato
     */
    @Test
    public void verificavalidaCodiceFiscaleFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00000000000";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il CF non rispetta il formato.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }


    /**
     * Verifica validaCodiceFiscale nel caso in cui il coodiceFiscale appartiene ad un utente gia registrato
     */
    @Test
    public void verificavalidaCodiceFiscaleEsistente() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        Tesserino tesserino1 = new Tesserino();

        when(tesserinoRepository.findByDonatoreUtenteCodiceFiscale(codiceFiscale)).thenReturn(tesserino1);

        final String message = "Utente con questo codice fiscale gia esistente sul database";
        try {
            gestioneTesserinoService.validaCodiceFiscale(codiceFiscale);
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }


    /**
     * Verifica validaImage nel caso in cui il l'immagine è null
     */
    @Test
    public void verificaValidaImageNull() {
        image = null;
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il formato dell’immagine non è corretto. Inserire un’immagine che ha formato png o jpg.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaDataDiNascita nel caso in cui la data è null
     */
    @Test
    public void validaDataDiNascitaNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = null;
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "La data non rispetta il formato. Inserire solo dati numerici e del formato dd/mm/aaaa";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaDataDiNascita nel caso in cui il formato è null
     */
    @Test
    public void validaDataDiNascitaFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2021111111, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "La data non rispetta il formato. Inserire solo dati numerici e del formato dd/mm/aaaa";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaDataDiNascita nel caso in cui è minorenne
     */
    @Test
    public void validaDataDiNascitaMinorenne() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2010, 3, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "L’utente non è maggiorenne. Inserire una data corretta.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna eccezione lanciata");
    }

    /**
     * Verifica validaLuogoDiNascita nel caso in cui il luogo di nascita è null
     */
    @Test
    public void verificaValidaLuogoDiNascitaNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = null;
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il luogo di nascita non rispetta il formato. Inserire solo valori alfabetici.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }


    /**
     * Verifica validaLuogoDiNascita nel caso in cui il luogo di nascita non rispetta il formato
     */
    @Test
    public void verificaValidaLuogoDiNascitaFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "5555";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Il luogo di nascita non rispetta il formato. Inserire solo valori alfabetici.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaResidenza nel caso in cui il la residenza è null
     */
    @Test
    public void verificaValidaResidenzaNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = null;
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "La residenza non rispetta il formato. Inserire solo dati alfanumerici e segni di punteggiatura.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaResidenza nel caso in cui il la residenza non rispetta il formato
     */
    @Test
    public void verificaValidaResidenzaFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "000000000";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "La residenza non rispetta il formato. Inserire solo dati alfanumerici e segni di punteggiatura.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaEmail nel caso in cui l'email è null
     */
    @Test
    public void verificaValidaEmailNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = null;
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "L’email non rispetta il formato. Inserire email del formato: xxxx@xxx.xx";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaEmail nel caso in cui l'email non rispetta iol formato
     */
    @Test
    public void verificaValidaEmailFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "Maripepe.it";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "L’email non rispetta il formato. Inserire email del formato: xxxx@xxx.xx";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaEmail nel caso in cui l'email esiste gia
     */
    @Test
    public void verificaValidaEmailEsistente() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "L’email è già stata utilizzata. L’email è stata già registrata in qualche altro tesserino";

        Donatore donatore = new Donatore();
        donatore.setEmail(email);
        when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscale)).thenReturn(donatore);
        try {
            gestioneTesserinoService.validaEmail(email, codiceFiscale);
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaGruppoSanguigno nel caso in cui il gruppo sanguigno sia null
     */
    @Test
    public void verificaValidaGruppoSanguignoNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = null;
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il gruppo sanguigno non rispetta il formato. Il gruppo sanguigno può assumere solo valori  “0”, “A”, “B”, “AB”.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaGruppoSanguigno nel caso in cui il gruppo sanguigno non rispetti il formato
     */
    @Test
    public void verificaValidaGruppoSanguigno() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "D";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il gruppo sanguigno non rispetta il formato. Il gruppo sanguigno può assumere solo valori  “0”, “A”, “B”, “AB”.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }


    /**
     * Verifica validaRh nel caso in cui l'Rh sia null
     */
    @Test
    public void verificaValidaRhNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = null;
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il campo RH non rispetta il formato. Può assumere solo valori “POS” e “NEG”";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaRh nel caso in cui l'Rh non rispetti il formato
     */
    @Test
    public void verificaValidaRhFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "99";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il campo RH non rispetta il formato. Può assumere solo valori “POS” e “NEG”";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaAltreIndicazioni nel caso in cui le altre indiazioni siano null
     */
    @Test
    public void verificaValidaAltreIndicazioniNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = null;
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Altre indicazioni non rispetta il formato. Inserire caratteri alfanumerici e punteggiatura.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaRh nel caso in cui l'Rh non rispetti il formato
     */
    @Test
    public void verificaValidaAltreIndicazioniFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "@@@";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Altre indicazioni non rispetta il formato. Inserire caratteri alfanumerici e punteggiatura.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaNumeroMatricola nel caso in cui la matricola è uguale a 0
     */
    @Test
    public void verificaValidaNumeroMatricolaZero() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 0;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il numero di matricola non rispetta il formato. Inserire solo 7 caratteri numerici.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaNumeroMatricola nel caso in cui la matricola non rispetta il foramto
     */
    @Test
    public void verificaValidaNumeroMatricolaFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il numero di matricola non rispetta il formato. Inserire solo 7 caratteri numerici.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore! nessuna eccezione laciata");
    }

    /**
     * Verifica validaNumeroTessera nel caso in cui la Tessera è uguale a 0
     */
    @Test
    public void verificaValidaNumeroTesseraZero() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53453;
        numeroTessera = 0;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il numero di tesserino non rispetta il formato. Inserire solo 7 caratteri numerici.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }

    /**
     * Verifica validaNumeroTessera nel caso in cui la Tessera non rispetta il foramto
     */
    @Test
    public void verificaValidaNumeroTesseraFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53453;
        numeroTessera = 65;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        final String message = "Il numero di tesserino non rispetta il formato. Inserire solo 7 caratteri numerici.";

        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }

    /**
     * Verifica validaDataRilascio nel caso in cui la data è null
     */
    @Test
    public void validavalidaDataRilascioNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = null;
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Data rilascio tessera non rispetta il formato. La data deve avere solo valori numeri del formato: dd/mm/aaaa";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }

    /**
     * Verifica validaDataRilascio nel caso in cui la data non rispetta il formato
     */
    @Test
    public void validavalidaDataRilascioFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(20202020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Data rilascio tessera non rispetta il formato. La data deve avere solo valori numeri del formato: dd/mm/aaaa";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }


    /**
     * Verifica validaDataRilascio nel caso in cui la data è dopo oggi tipo
     */
    @Test
    public void validavalidaDataRilascioDopoOggi() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2030, 12, 11);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Data rilascio tessera non può superare la data attuale.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }


    /**
     * Verifica validaDataDonazione nel caso in cui la data è null
     */
    @Test
    public void validavalidavalidaDataDonazioneNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = null;
        tipoDonazione = "cito";

        final String message = "Data donazione non rispetta il formato. La data deve avere solo valori numeri del formato: dd/mm/aaaa";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }

    /**
     * Verifica validaDataDonazione nel caso in cui la data non rispetta il formato
     */
    @Test
    public void validavalidavalidaDataDonazioneFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021212121, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Data donazione non rispetta il formato. La data deve avere solo valori numeri del formato: dd/mm/aaaa";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }

    /**
     * Verifica validaDataDonazione nel caso in cui la data è dopo oggi tipo
     */
    @Test
    public void validavalidavalidaDataDonazioneDopoOggi() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2030, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        final String message = "Data donazione non può superare la data attuale.";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }

    /**
     * Verifica validaTipoDonazione nel caso in cui il tipo di donazione è null
     */
    @Test
    public void validavalidaValidaTipoDonazioneNull() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = null;

        final String message = "Il campo tipo donazione non rispetta il formato. Può assumere solo valori “plasma”,  “cito” e “sangue”";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }

    /**
     * Verifica validaTipoDonazione nel caso in cui il tipo di donazione non rispetta il formato
     */
    @Test
    public void validavalidaValidaTipoDonazioneFormato() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "|||";

        final String message = "Il campo tipo donazione non rispetta il formato. Può assumere solo valori “plasma”,  “cito” e “sangue”";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Nessuna Eccezione Lanciata!");
    }


    /**
     * Verifica validaDataProssimaDisponibilitaDonazione nel caso in cui la data è null
     */
    @Test
    public void verificaValidaDataProssimaDisponibilitaDonazioneNull() {
        Calendar data = new GregorianCalendar(2020, 2, 13);
        dataProssimaDisponibilita = null;

        final String message = "La Data Di Prossima Disponibilità non rispetta il formato: gg/mm/aaaa";
        try {
            gestioneTesserinoService.validaDataProssimaDisponibilitaDonazione(dataProssimaDisponibilita);
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica validaDataProssimaDisponibilitaDonazione nel caso in cui la data non rispetta il formato
     */
    @Test
    public void verificaValidaDataProssimaDisponibilitaDonazioneFormato() {
        Calendar data = new GregorianCalendar(20202020, 2, 13);
        dataProssimaDisponibilita = data.getTime();

        final String message = "La Data Di Prossima Disponibilità non rispetta il formato: gg/mm/aaaa";
        try {
            gestioneTesserinoService.validaDataProssimaDisponibilitaDonazione(dataProssimaDisponibilita);
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
        }
    }

    /**
     * Verifica validaDataDonazione nel caso in cui la data è dopo oggi tipo
     */
    @Test
    public void verificalidaDataProssimaDisponibilitaDonazionDopoOggi() {
        Calendar data = new GregorianCalendar(2020, 2, 13);
        dataProssimaDisponibilita = data.getTime();

        final String message = "La Data Di Prossima Disponibilità non può essere antecedente a quella attuale";
        try {
            gestioneTesserinoService.validaDataProssimaDisponibilitaDonazione(dataProssimaDisponibilita);
        } catch (TesserinoFormException exception) {
            assertEquals(message, exception.getMessage());
            return;
        }
        fail("Errore nessuna eccezione laciata!");
    }

    /**
     * Verifica validaDataDonazione nel caso in cui ha successo
     */
    @Test
    public void verificalidaDataProssimaDisponibilitaDonazionSuccesso() {
        Calendar data = new GregorianCalendar(2023, 2, 13);
        dataProssimaDisponibilita = data.getTime();

        final String message = "La Data Di Prossima Disponibilità non può essere antecedente a quella attuale";
        try {
            gestioneTesserinoService.validaDataProssimaDisponibilitaDonazione(dataProssimaDisponibilita);
        } catch (TesserinoFormException exception) {
            fail(exception.getMessage());
        }
    }


    /**
     * Verifica validaNome nel caso in cui il nome è corretto
     */
    @Test
    public void verificaValidaSuccessoTotale() {
        try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = "Marika";
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";
        try {
            validaCampi();
        } catch (TesserinoFormException exception) {
            fail(exception.getMessage());
        }
    }
}