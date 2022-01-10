package it.unisa.is.c09.digitaldonation.organizzazionesedutemanagement;


import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotRelaseFeedbackException;
import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Guest;
import it.unisa.is.c09.digitaldonation.model.entity.Seduta;
import it.unisa.is.c09.digitaldonation.model.repository.*;
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
public class OrganizzazioneSeduteServiceGuestIT {
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



    /**
     * Salva la lista di donazioni su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void setUp() {

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

        long idSeduta = 420l;
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
     * @result Il test è superato se l'inserimento di utenti guest viene correttamente effettuato.
     */
    @Test
    public void feedbackDonatore() {
        donatoreRepository.save(donatore);
        sedutaRepository.save(seduta);
        Donatore donatoreRitorno = null;
        try {
            organizzazioneSeduteService.feedbackDonatore(donatore, seduta.getIdSeduta());
        } catch (CannotRelaseFeedbackException e) {
            e.printStackTrace();
        }
        assertEquals(donatoreRitorno, donatore);

        sedutaRepository.deleteAll();
        guestRepository.deleteAll();
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
     * Testa il corretto funzionamento del metodo modificaSeduta
     *
     * @result Il test è superato se la modifica di una seduta viene correttamente effettuata.
     */
    @Test
    public void modificaSeduta() {
        sedutaRepository.save(seduta);
        Seduta sedutaRitorno = organizzazioneSeduteService.salvaSeduta(seduta);

        assertEquals(sedutaRitorno, seduta);

        sedutaRepository.deleteAll();
    }

}
