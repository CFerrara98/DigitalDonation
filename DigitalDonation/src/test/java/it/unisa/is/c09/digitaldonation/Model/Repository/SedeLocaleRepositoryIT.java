package it.unisa.is.c09.digitaldonation.Model.Repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.SedeLocale;
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
import static org.junit.Assert.*;

/**
 * Test di integrazione fra la classe SedeLocaleRepository e il Database.
 * Metodologia: bottom-up.
 * @author Mattia Sapere
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SedeLocaleRepositoryIT {

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

    private SedeLocale sedeLocale;

    /**
     * Salva la lista delle sedi locali su database prima
     * dell'esecuzione di ogni singolo test.
     */
    @Before
    public void salvaSedeLocale() {

        sedeLocale = new SedeLocale();
        sedeLocale.setCodiceIdentificativo(2l);
        sedeLocale.setVia("Via Garibaldi, 44");

        sedeLocaleRepository.save(sedeLocale);
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca
     * di una sede locale tramite codice identificativo avvenga correttamente.
     *
     * @test {@link SedeLocaleRepository#findSedeLocaleByCodiceIdentificativo(Long)}
     * @result Il test è superato se la ricerca del codice identificativo della sede locale
     * presente nella lista utilizzata per il test ha successo
     */
    @Test
    public void findSedeLocaleByCodiceIdentificativo() {

        // Controlla che ogni sede locale inserita per il test sia presente su database
        // restituendolo in base al codice identificativo
        SedeLocale sedeSalvata = sedeLocaleRepository.save(sedeLocale);
        assertThat(sedeLocale.getCodiceIdentificativo(), is(equalTo(sedeSalvata.getCodiceIdentificativo())));
    }
}