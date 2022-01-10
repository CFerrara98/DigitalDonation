
package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
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
 * Test di integrazione fra la classe IndisponibilitaRepository e il Database.
 * Metodologia: bottom-up.
 *
 * @author Mattia Sapere
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IndisponibilitaRepositoryIT {

    @Autowired
    private GuestRepository guestRepository;

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
    private IndisponibilitaRepository indisponibilitaRepository;

    private Indisponibilita indisponibilita;

    /**
     * Salva la lista delle indisponibilità su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void salvaDonazione() {
        indisponibilita = new Indisponibilita();
        indisponibilita.setIdIndisponibilita(2l);
        //indisponibilita.setDonatore(new Donatore());
        //indisponibilita.setDataProssimaDisponibilita(new Date());
        //indisponibilita.setDonatore(null);
        indisponibilita.setDataProssimaDisponibilita(null);
        indisponibilita.setMotivazioni("Febbre");
        indisponibilita.setNomeMedico("Fabio");

        indisponibilitaRepository.save(indisponibilita);
    }

    /**
     * Testa il corretto funzionamento del metodo di salvataggio di un'indisponibilità.
     *
     * @test {@link IndisponibilitaRepository#save(Indisponibilita)}
     * @result Il test è superato se l'indisponibilità viene correttamente
     * salvata nel database
     */
    @Test
    public void saveIndisponibilita() {
        Indisponibilita indisponibilitaSalvata = indisponibilitaRepository.save(indisponibilita);
        assertThat(indisponibilita.getIdIndisponibilita(), is(equalTo(indisponibilita.getIdIndisponibilita())));
    }
}