package it.unisa.is.c09.digitaldonation.Model.Repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;
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
 * Test di integrazione fra la classe DonazioneRepository e il Database.
 * Metodologia: bottom-up.
 *
 * @author Mattia Sapere
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DonazioneRepositoryIT {

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
    private DonazioneRepository donazioneRepository;

    private Tesserino tesserino;
    private Donazione donazione;

    /**
     * Salva la lista di donazioni su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void salvaDonazione() {

        tesserino = new Tesserino();
        tesserino.setNumeroMatricola(12345);
        tesserino.setIdTessera(12345l);
        tesserino.setDonatoreUtenteCodiceFiscale("CNGJWF51U60J369B");
        tesserino.setDataRilascio(new Date());
        tesserino.setGruppoSanguigno("AB");
        tesserino.setRh("POS");
        tesserino.setListaDonazioni(null);
        tesserino.setImgSource(null);

        donazione = new Donazione();
        donazione.setTesserino(tesserino);
        donazione.setDataDonazione(new Date());
        donazione.setTipoDonazione("Tradizionale");


        donazioneRepository.save(donazione);
    }

    /**
     * Testa il corretto funzionamento del metodo di salvataggio di una donazione.
     *
     * @test {@link DonazioneRepository#save(Donazione)}
     * @result Il test è superato se la donazione viene correttamente
     * salvata nel database
     */
    @Test
    public void saveDonazione() {
        Donazione donazioneSalvata = donazioneRepository.save(donazione);
        //assertNotEquals(donazione, donazioneSalvata);
        assertThat(donazione.getIdDonazione(), is(equalTo(donazioneSalvata.getIdDonazione())));
    }

}