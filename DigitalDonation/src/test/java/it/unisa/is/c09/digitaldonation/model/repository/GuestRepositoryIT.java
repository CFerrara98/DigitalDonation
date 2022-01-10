
package it.unisa.is.c09.digitaldonation.model.repository;

import it.unisa.is.c09.digitaldonation.model.entity.Guest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

/**
 * Test di integrazione fra la classe GuestRepository e il Database.
 * Metodologia: bottom-up.
 *
 * @author Mattia Sapere
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GuestRepositoryIT {

    @Autowired
    private DonazioneRepository donazioneRepository;

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

    @Autowired
    private GuestRepository guestRepository;

    private Guest guest;

    /**
     * Salva la lista di guest su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void salvaGuest() {

        guest = new Guest();
        guest.setcodiceFiscaleGueste("SNHVLT82L10F188P");
        guest.setNome("Mattia");
        guest.setCognome("Sapere");
        guest.setTelefono("3208146934");
        guest.setPatologie("Morbillo");
        guest.setGruppoSanguigno("AB+");

        guestRepository.save(guest);
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca di un guest
     * tramite codice fiscale avvenga correttamente.
     *
     * @test {@link GuestRepository#findByCodiceFiscaleGuest(String)}
     * @result Il test e superato se la ricerca dei codici fiscali dei guest
     * presenti nella lista utilizzata per il test ha successo
     */
    @Test
    public void findGuestByCodiceFiscaleUtente() {

        // Controlla che ogni guest inserito per il test sia presente su database
        // restituendolo in base alla matricola
        Guest guestSalvato = guestRepository.findByCodiceFiscaleGuest(guest.getcodiceFiscaleGuest());
        assertThat(guest.getcodiceFiscaleGuest(), is(equalTo(guestSalvato.getcodiceFiscaleGuest())));
    }

    /**
     * Testa il corretto funzionamento del metodo di salvataggio di un guest.
     *
     * @test {@link GuestRepository#save(Guest)}
     * @result Il test è superato se il guest viene correttamente
     * salvato nel database
     */
    @Test
    public void saveGuest() {
        Guest guestSalvato = guestRepository.save(guest);
        assertThat(guest.getcodiceFiscaleGuest(), is(equalTo(guest.getcodiceFiscaleGuest())));
    }
}