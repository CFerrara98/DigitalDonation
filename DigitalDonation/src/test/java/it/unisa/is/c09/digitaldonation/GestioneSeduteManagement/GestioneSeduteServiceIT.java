package it.unisa.is.c09.digitaldonation.GestioneSeduteManagement;
/*

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class GestioneSeduteServiceIT {

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

    private Donatore donatore;*/

    /**
     * Salva la lista di donatori su database prima dell'esecuzione di ogni singolo
     * test.
     */
/*    @Before
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
}
*/