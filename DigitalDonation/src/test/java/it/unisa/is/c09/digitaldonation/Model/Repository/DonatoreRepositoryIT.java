package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import static it.unisa.is.c09.digitaldonation.utilRand.BuildRandEntity.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Test di integrazione fra la classe DonatoreRepository e il Database.
 * Metodologia: bottom-up.
 * @author Mattia Sapere, Fabio Siepe
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DonatoreRepositoryIT {

    @Autowired
    private DonazioneRepository donazioneRepository;

    @Autowired
    private GuestRepository guestRepository;

    @Autowired
    private IndisponibilitaRepository indisponibilitaRepository;

    @Autowired
    private OperatoreRepository operatoreRepository;

    @Autowired
    private SedeLocaleRepository sedeLocaleRepository;

    @Autowired
    private SedutaRepository sedutaRepository;

    @Autowired
    private TesserinoRepository tesserinoRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private DonatoreRepository donatoreRepository;

    private Donatore donatore;

    //private Utente utente;

    /**
     * Salva la lista di studenti su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void salvaStudente() {



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

        /*codiceFiscale, nome, cognome, email, password*/

        donatoreRepository.save(donatore);
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca di uno
     * studente tramite matricola avvenga correttamente.
     *
     * @test {@link DonatoreRepository#findDonatoreByCodiceFiscaleUtente(String)}
     *
     * @result Il test e superato se la ricerca dei codici fiscali dei donatori
     *         presenti nella lista utilizzata per il test ha successo
     */
    @Test
    public void findDonatoreByCodiceFiscaleUtente(){

        // Controlla che ogni donatore inserito per il test sia presente su database
        // restituendolo in base alla matricola
        Donatore donatoreSalvato = donatoreRepository.findDonatoreByCodiceFiscaleUtente(donatore.getCodiceFiscale());
        assertNotEquals(donatore, donatoreSalvato);
        }
}

