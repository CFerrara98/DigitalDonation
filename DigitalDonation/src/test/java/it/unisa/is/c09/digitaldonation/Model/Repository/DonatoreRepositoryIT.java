package it.unisa.is.c09.digitaldonation.Model.Repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;


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
    private DonatoreRepository donatoreRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    private Donatore donatore;

    /**
     * Salva la lista di donatori su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void salvaDonatore() {

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

        donatoreRepository.save(donatore);
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca
     * di un operatore tramite codice fiscale avvenga correttamente.
     *
     * @test {@link DonatoreRepository#findDonatoreByCodiceFiscaleUtente(String)}
     *
     * @result Il test e superato se la ricerca dei codici fiscali dei donatori
     *         presenti nella lista utilizzata per il test ha successo
     */
    @Test
    public void findDonatoreByCodiceFiscaleUtente(){

        // Controlla che ogni donatore inserito per il test sia presente su database
        // restituendolo in base al codice fiscale
        Donatore donatoreSalvato = donatoreRepository.findDonatoreByCodiceFiscaleUtente(donatore.getCodiceFiscale());
        assertThat(donatore.getCodiceFiscale(), is(equalTo(donatoreSalvato.getCodiceFiscale())));
    }

    /**
     * Testa il corretto funzionamento del metodo di salvataggio di un donatore.
     *
     * @test {@link DonatoreRepository#save(Donatore)}
     *
     * @result Il test è superato se il donatore viene correttamente
     * salvato nel database
     */
    @Test
    public void saveDonatore() {
        Donatore donatoreSalvato= donatoreRepository.save(donatore);
        assertThat(donatore.getCodiceFiscale(), is(equalTo(donatoreSalvato.getCodiceFiscale())));
    }
}