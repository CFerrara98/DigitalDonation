package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
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
 * Test di integrazione fra la classe OperatoreRepository e il Database.
 * Metodologia: bottom-up.
 * @author Mattia Sapere
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OperatoreRepositoryIT {

    @Autowired
    private DonazioneRepository donazioneRepository;

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
    private DonatoreRepository donatoreRepository;

    @Autowired
    private IndisponibilitaRepository indisponibilitaRepository;

    private Operatore operatore;

    /**
     * Salva la lista degli operatori su database prima dell'esecuzione di ogni singolo
     * test.
     */
    @Before
    public void salvaDonatore() {

        operatore = new Operatore();
        operatore.setCodiceFiscale("FJNYQC47M70C283I");
        operatore.setNome("Mattia");
        operatore.setCognome("Sapere");
        operatore.setEmail("mattiasapere1@gmail.com");
        operatore.setPassword("Mattiasapere.123");
        operatore.setSedeLocale(null);

        operatoreRepository.save(operatore);
    }

    /**
     * Testa l'interazione con il database per determinare se la ricerca di un operatore
     * tramite codice fiscale avvenga correttamente.
     *
     * @test {@link OperatoreRepository#findOperatoreByCodiceFiscaleUtente(String)}
     *
     * @result Il test e superato se la ricerca dei codici fiscali degli operatori
     *         presenti nella lista utilizzata per il test ha successo
     */
    @Test
    public void findOperatoreByCodiceFiscaleUtente(){

        // Controlla che ogni operatore inserito per il test sia presente su database
        // restituendolo in base al codice fiscale
        Operatore operatoreSalvato = operatoreRepository.findOperatoreByCodiceFiscaleUtente(operatore.getCodiceFiscale());
        assertNotEquals(operatore, operatoreSalvato);
    }
}
