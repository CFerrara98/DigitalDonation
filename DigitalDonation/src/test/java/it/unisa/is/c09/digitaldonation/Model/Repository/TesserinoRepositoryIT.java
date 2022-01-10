
package it.unisa.is.c09.digitaldonation.Model.Repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Test di integrazione fra la classe TesserinoRepository e il Database.
 * Metodologia: bottom-up.
 *
 * @author Mattia Sapere
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TesserinoRepositoryIT {

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

    private Tesserino tesserino;
    private Donatore donatore;

    /**
     * Salva la lista di tesserini su database
     * prima dell'esecuzione di ogni singolo test.
     */
    @Before
    public void salvaTesserino() {

        tesserino = new Tesserino();
        tesserino.setNumeroMatricola(12345);
        tesserino.setIdTessera(12345l);
        tesserino.setDonatoreUtenteCodiceFiscale("CNGJWF51U60J369B");
        tesserino.setDataRilascio(new Date());
        tesserino.setGruppoSanguigno("AB");
        tesserino.setRh("POS");
        tesserino.setListaDonazioni(null);
        tesserino.setImgSource(null);

        donatore = new Donatore();
        donatore.setCodiceFiscale("CNGJWF51U60J369B");

        donatoreRepository.save(donatore);
        tesserinoRepository.save(tesserino);
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca
     * di un tesserino tramite l'id della tessera avvenga correttamente.
     *
     * @test {@link TesserinoRepository#findTesserinoByIdTessera(Long)}
     * @result Il test e superato se la ricerca degli id dei tesserini
     * presenti nella lista utilizzata per il test ha successo
     */
    @Test
    public void findTesserinoByIdTessera() {

        // Controlla che ogni tesserino inserito per il test sia presente su database
        // restituendolo in base all' id del tesserino
        Tesserino tesserinoSalvato = tesserinoRepository.findTesserinoByIdTessera(tesserino.getIdTessera());
        assertThat(tesserino.getIdTessera(), is(equalTo(tesserinoSalvato.getIdTessera())));
    }

    /**
     * Testa il corretto funzionamento del metodo di salvataggio di un tesserino.
     *
     * @test {@link TesserinoRepository#save(Tesserino)}
     * @result Il test è superato se il tesserino viene correttamente
     * salvato nel database
     */
    @Test
    public void saveTesserino() {
        Tesserino tesserinoSalvato = tesserinoRepository.save(tesserino);
        assertThat(tesserino.getIdTessera(), is(equalTo(tesserinoSalvato.getIdTessera())));
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca
     * di un tesserino tramite il codice fiscale dell'utente avvenga correttamente.
     *
     * @test {@link TesserinoRepository#findByDonatoreUtenteCodiceFiscale(String)}
     * @result Il test e superato se la ricerca degli id di un tesserino tramite il codice fiscale dell'utente
     * presente nella lista utilizzata per il test ha successo
     */
    @Test
    public void findByDonatoreUtenteCodiceFiscale() {

        // Controlla che ogni tesserino inserito per il test sia presente su database
        // restituendolo in base al codice fiscale dell'utente

        Tesserino tesserinoSalvato = tesserinoRepository.findByDonatoreUtenteCodiceFiscale(tesserino.getDonatoreUtenteCodiceFiscale());
        assertThat(tesserino.getDonatoreUtenteCodiceFiscale(), is(equalTo(tesserinoSalvato.getDonatoreUtenteCodiceFiscale())));
    }
}