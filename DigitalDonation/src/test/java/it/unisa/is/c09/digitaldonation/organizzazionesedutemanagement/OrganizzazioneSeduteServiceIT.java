package it.unisa.is.c09.digitaldonation.organizzazionesedutemanagement;


import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.*;
import it.unisa.is.c09.digitaldonation.model.entity.*;
import it.unisa.is.c09.digitaldonation.model.repository.*;
import it.unisa.is.c09.digitaldonation.utils.form.SedutaForm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Test di integrazione fra la classe organizzazioneSeduteServiceGuest e la repository.
 * Metodologia: bottom-up.
 *
 * @author Mattia Sapere
 */


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class OrganizzazioneSeduteServiceIT {
    @Autowired
    private OrganizzazioneSeduteService organizzazioneSeduteService;

    @Autowired
    private IndisponibilitaRepository indisponibilitaRepository;

    @Autowired
    private OperatoreRepository operatoreRepository;

    @Autowired
    private SedutaRepository sedutaRepository;

    @Autowired
    private TesserinoRepository tesserinoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private DonatoreRepository donatoreRepository;

    @Autowired
    private SedeLocaleRepository sedeLocaleRepository;

    @Autowired
    private DonazioneRepository donazioneRepository;

    private String codiceFiscaleGuest;
    private String nome;
    private String cognome;
    private String telefono;
    private String patologie;
    private String gruppoSanguigno;

    private Guest guest;

    private Date dataSeduta;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String CAP;
    private Time orarioInizio;
    private Time orarioFine;
    private int numeroPartecipanti;
    private Date dataInizioPrenotazione;
    private Date dataFinePrenotazione;

    private Seduta seduta;

    private Donatore donatore;

    private Operatore operatore;

    private SedeLocale sedeLocale;

    private Utente utente;

    private Indisponibilita indisponibilita;

    /**
     * Salva la lista di donazioni su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void setUp() {

        indisponibilita = new Indisponibilita();
        Calendar myCalendar3 = new GregorianCalendar(2022, 3, 22);
        indisponibilita.setDataProssimaDisponibilita(myCalendar3.getTime());
        indisponibilita.setMotivazioni("Problemi di salute");
        indisponibilita.setNomeMedico("Elpidio");

        utente = new Utente();
        utente.setCodiceFiscale("FJNYQC47M70C283I");
        utente.setNome("Mario");
        utente.setCodiceFiscale("Rossi");
        utente.setEmail("mariorossi1@gmail.com");
        utente.setPassword("Mariorossi.1");

        sedeLocale = new SedeLocale();
        sedeLocale.setVia("Bubazza 11");
        sedeLocale.setCodiceIdentificativo(1L);

        operatore = new Operatore();
        operatore.setCodiceFiscale("FJNYQC47M70C283I");
        operatore.setNome("Giovanni");
        operatore.setCognome("Rana");
        operatore.setEmail("giovannirana81@gmail.com");
        operatore.setPassword("Giovanni.123");
        operatore.setSedeLocale(sedeLocale);


        donatore = new Donatore();
        donatore.setCodiceFiscale("FJNYQC47M70C283I");
        donatore.setNome("Mattia");
        donatore.setCognome("Sapere");
        donatore.setEmail("mattiasapere81@gmail.com");
        donatore.setPassword("Mattia.123");
        donatore.setResidenza("Via Garibaldi, 44");
        donatore.setDataDiNascita(null);
        donatore.setLuogoDiNascita("Salerno");
        donatore.setTesserino(null);
        donatore.setListaIndisponibilita(null);

        codiceFiscaleGuest = "MVYZZV65L56I556J";
        nome = "Angela";
        cognome = "De Martino";
        telefono = "3456789123";
        patologie = "Nessuna";
        gruppoSanguigno = "AB+";

        guest = new Guest(codiceFiscaleGuest, nome, cognome, telefono, patologie, gruppoSanguigno);

        long idSeduta = -1;
        Calendar myCalendar = new GregorianCalendar(202, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";
        numeroPartecipanti = 9999;
        orarioInizio = new Time(22,11,11);
        orarioFine = new Time(23,58,11);
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        List<Guest> listaGuest = new ArrayList<Guest>();
        List<Donatore> listaDonatore = new ArrayList<Donatore>();
        seduta = new Seduta(idSeduta, dataSeduta, indirizzo, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataInizioPrenotazione, 25l, listaGuest, listaDonatore);

    }

    /**
     * Testa il corretto funzionamento del metodo feedbackDonatore
     *
     * @test {@link OrganizzazioneSeduteService#feedbackDonatore(Donatore, Long)}
     * @result Il test è superato se l'inserimento di utenti donatori viene correttamente effettuato.
     */
    @Test
    public void feedbackDonatore() {
        donatoreRepository.save(donatore);
        Seduta seduta1 = sedutaRepository.save(seduta);
        Donatore donatoreRitorno = organizzazioneSeduteService.salvaDonatore(donatore);
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, seduta1.getIdSeduta());
        } catch (CannotRelaseFeedbackException e) {
            e.printStackTrace();
        }
        assertEquals(donatoreRitorno, donatore);

        sedutaRepository.deleteAll();
        donatoreRepository.deleteAll();
    }

    /**
     * Testa il corretto funzionamento del metodo monitoraggioSeduta
     *
     * @test {@link OrganizzazioneSeduteService#monitoraggioSeduta(Long)}
     * @result Il test è superato se il monitoraggio di una seduta viene effettuato correttamente.
     */
    @Test
    public void monitoraggioSeduta() {
        Seduta seduta1 = sedutaRepository.save(seduta);
        seduta.addPartecipante(guest);
        seduta.addPartecipante(donatore);
        Guest guestRitorno = organizzazioneSeduteService.salvaGuest(guest);
        Donatore donatoreRitorno = organizzazioneSeduteService.salvaDonatore(donatore);

        try {
            organizzazioneSeduteService.monitoraggioSeduta(seduta1.getIdSeduta());
        } catch (CannotLoadDataRepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(guestRitorno, guest);
        assertEquals(donatoreRitorno, donatore);

        sedutaRepository.deleteAll();
    }

    /**
     * Testa il corretto funzionamento del metodo inserimentoGuest
     *
     * @test {@link OrganizzazioneSeduteService#salvaGuest(Guest)}
     * @result Il test è superato se l'inserimento di utenti guest viene correttamente effettuato.
     */
    @Test
    public void inserimentoGuest() {
        sedutaRepository.save(seduta);
        Guest guestRitorno = organizzazioneSeduteService.salvaGuest(guest);

        assertEquals(guestRitorno, guest);

        sedutaRepository.deleteAll();
        guestRepository.deleteAll();
    }

    /**
     * Testa il corretto funzionamento del metodo schedulazioneSeduta
     *
     * @test {@link OrganizzazioneSeduteService#schedulazioneSeduta(Seduta)}
     * @result Il test è superato se la schedulazione di una seduta viene effettuata correttamente.
     */
    @Test
    public void schedulazioneSeduta() {
        sedutaRepository.save(seduta);
        Seduta sedutaRitorno = organizzazioneSeduteService.salvaSeduta(seduta);

        try {
            organizzazioneSeduteService.schedulazioneSeduta(seduta);
        } catch (CannotSaveDataRepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(sedutaRitorno, seduta);

        sedutaRepository.deleteAll();
    }

    /**
     * Testa il corretto funzionamento del metodo modificaSeduta
     *
     * @test {@link OrganizzazioneSeduteService#modificaSeduta(SedutaForm, Long, Utente)}
     *
     * @result Il test è superato se la modifica di una seduta viene correttamente effettuata.
     */
    @Test
    public void modificaSeduta() {

        Calendar myCalendar = new GregorianCalendar(202, 4, 22);
        dataSeduta = myCalendar.getTime();
        indirizzo = "Via cesare 68";
        citta = "Salerno";
        provincia = "SA";
        CAP = "82100";

        orarioInizio = new Time(22,11,11);
        orarioFine = new Time(23,58,11);
        numeroPartecipanti = 9999;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataInizioPrenotazione = myCalendar1.getTime();
        Calendar myCalendar2 = new GregorianCalendar(2022, 4, 17);
        dataFinePrenotazione = myCalendar2.getTime();
        SedutaForm sedutaForm = new SedutaForm(dataSeduta, indirizzo, citta, provincia, CAP, orarioInizio, orarioFine, numeroPartecipanti, dataInizioPrenotazione, dataFinePrenotazione);


        Seduta seduta1 =sedutaRepository.save(seduta);
        operatoreRepository.save(operatore);
        utenteRepository.save(utente);
        Seduta sedutaRitorno = organizzazioneSeduteService.salvaSeduta(seduta);
        try {
            organizzazioneSeduteService.modificaSeduta(sedutaForm,seduta1.getIdSeduta(),operatore);
        } catch (CannotUpdateDataRepositoryException e) {
            e.printStackTrace();
        }

        assertEquals(sedutaRitorno, seduta);

        sedutaRepository.deleteAll();
        operatoreRepository.deleteAll();
        utenteRepository.deleteAll();
    }

    /**
     * Testa il corretto funzionamento del metodo eliminaSeduta
     *
     * @test {@link OrganizzazioneSeduteService#eliminaSeduta(Long)}
     * @result Il test è superato se l'eliminazione di una seduta viene effettuata correttamente.
     */
    @Test
    public void eliminaSeduta() {
        List<Seduta> listaSedute = null;

        Seduta seduta1 = sedutaRepository.save(seduta);

        try {
            organizzazioneSeduteService.eliminaSeduta(seduta1.getIdSeduta());
        } catch (CannotDeleteDataRepositoryException e) {
            e.printStackTrace();
        }
        try {
            listaSedute = organizzazioneSeduteService.visualizzaElencoSedute();
        } catch (CannotLoadDataRepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(listaSedute.contains(seduta1), false);

        sedutaRepository.deleteAll();
    }

    /**
     * Testa il corretto funzionamento del metodo visualizzaElencoSeduteDisponibili
     *
     * @test {@link OrganizzazioneSeduteService#visualizzaElencoSeduteDisponibili(String)}
     * @result Il test è superato se la visualizzazione dell'elenco delle sedute viene effettuata correttamente.
     */
    @Test
    public void visualizzaElencoSeduteDisponibili() {
        donatore.setListaIndisponibilita(new ArrayList<>());
        Indisponibilita indisponibilita1 = indisponibilitaRepository.save(indisponibilita);
        donatore.addIndisponibilita(indisponibilita1);
        Donatore donatore1 = donatoreRepository.save(donatore);
        List<Seduta> lista = null;

        Seduta sedutaRitorno = organizzazioneSeduteService.salvaSeduta(seduta);

        try {
            lista = organizzazioneSeduteService.visualizzaElencoSeduteDisponibili(donatore1.getCodiceFiscale());
        } catch (CannotLoadDataRepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(lista.contains(sedutaRitorno), false);

        indisponibilitaRepository.deleteAll();
        sedutaRepository.deleteAll();
    }
}