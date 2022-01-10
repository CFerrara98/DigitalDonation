
package it.unisa.is.c09.digitaldonation.Model.Repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
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
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Test di integrazione fra la classe SedutaRepository e il Database.
 * Metodologia: bottom-up.
 *
 * @author Mattia Sapere
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SedutaRepositoryIT {

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

    private Seduta seduta;

    /**
     * Salva la lista delle sedute su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void salvaSeduta() {

        seduta = new Seduta();
        //seduta.setIdSeduta(null);

        Calendar myCalendar = new GregorianCalendar(2022, 1, 2);
        seduta.setDataSeduta(myCalendar.getTime());
        seduta.setOraInizio(null);

        seduta.setOraFine(null);
        seduta.setNumeroPartecipanti(100);

        Calendar myCalendar1 = new GregorianCalendar(2021, 12, 20);
        seduta.setDataInizioPrenotazione(myCalendar1.getTime());

        Calendar myCalendar2 = new GregorianCalendar(2021, 12, 28);
        seduta.setDataFinePrenotazione(myCalendar2.getTime());

        seduta.setSedeLocale(null);
        seduta.setListaGuest(null);
        seduta.setListaDonatore(null);

        sedutaRepository.save(seduta);
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca
     * della seduta tramite codice fiscale avvenga correttamente.
     *
     * @test {@link SedutaRepository#findByIdSeduta(Long)}
     * @result Il test è superato se la ricerca delle id della seduta
     * presente nella lista utilizzata per il test ha successo
     */
    @Test
    public void findByIdSeduta() {

        // Controlla che ogni seduta inserita per il test sia presente su database
        // restituendolo in base all id
        seduta = new Seduta();
        seduta.setIdSeduta(1l);
        Seduta sedutaSalvata = sedutaRepository.findByIdSeduta(seduta.getIdSeduta());
        assertThat(seduta.getIdSeduta(), is(equalTo(sedutaSalvata.getIdSeduta())));
    }

    /**
     * Testa il corretto funzionamento del metodo di salvataggio di una seduta.
     *
     * @test {@link SedutaRepository#save(Seduta)}
     * @result Il test è superato se la seduta viene correttamente
     * salvata nel database
     */
    @Test
    public void saveDonatore() {
        seduta = new Seduta();
        seduta.setIdSeduta(1l);
        Seduta sedutaSalvata = sedutaRepository.save(seduta);
        assertThat(seduta.getIdSeduta(), is(equalTo(sedutaSalvata.getIdSeduta())));
    }


}