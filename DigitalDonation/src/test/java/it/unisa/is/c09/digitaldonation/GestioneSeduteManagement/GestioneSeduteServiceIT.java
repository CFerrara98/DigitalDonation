package it.unisa.is.c09.digitaldonation.GestioneSeduteManagement;


import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import it.unisa.is.c09.digitaldonation.Utils.Forms.IndisponibilitaDonazioneForm;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

/**
 * Test di integrazione tra la classe GestioneSeduteService e le repository.
 * Metodologia: bottom-up.
 * @author Mattia Sapere, Fabio Siepe
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional @Rollback
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

    @Autowired
    private GestioneSeduteService gestioneSeduteService;

    private IndisponibilitaDonazioneForm indisponibilitaDonazioneForm;
    private String codiceFiscale;
    private Long id;
    private String tipoDonazione;
    private String codiceFiscaleDonatore;
    private Long id1;

    private Donazione donazione;
    private Tesserino tesserino;
    private Seduta seduta;



    /**
     * Salva la lista di donatori su database prima dell'esecuzione di ogni singolo
     * test.
     */

    @Before
    public void setUp() {
        donazioneRepository.deleteAll();
        guestRepository.deleteAll();
        indisponibilitaRepository.deleteAll();
        operatoreRepository.deleteAll();
        sedeLocaleRepository.deleteAll();
        sedutaRepository.deleteAll();
        tesserinoRepository.deleteAll();
        donatoreRepository.deleteAll();
        utenteRepository.deleteAll();

        donazione = new Donazione();
        tesserino = new Tesserino();
        seduta = new Seduta();

        Calendar dataDonazione = new GregorianCalendar(2022, 4, 15);
        donazione.setDataDonazione(dataDonazione.getTime());
        donazione.setTipoDonazione("Plasma");

        tesserino.setDonatoreUtenteCodiceFiscale("AFCLCW23302F362J");
        tesserino.setNumeroMatricola(12345);
        tesserino.setGruppoSanguigno("AB");
        tesserino.setRh("POS");
        //tesserino.setListaDonazioni();
        //tesserino.setImgSource();

        /*
        this.sedeLocaleCodiceIdentificativo = sedeLocaleCodiceIdentificativo;
        this.listaGuest = listaGuest;
        this.listaDonatore = listaDonatore;*/
        seduta.setIdSeduta(1L);
        seduta.setLuogo("Avellino");
        //seduta.setOraInizio();
        //seduta.setOraFine();
        seduta.setNumeroPartecipanti(100);
        Calendar dataInizioSeduta = new GregorianCalendar(2022, 4, 15);
        seduta.setDataSeduta(dataDonazione.getTime());
        Calendar dataFineSeduta = new GregorianCalendar(2022, 5, 15);
        seduta.setDataSeduta(dataDonazione.getTime());
        seduta.setSedeLocale(1L);
        //seduta.setListaGuest();
        //seduta.setListaDonatore();

        codiceFiscale = "AFCLCW23302F362J";
        id = 689L;
        tipoDonazione = "Plasma";

        codiceFiscaleDonatore = "AFCLCW22AV2F362J";
        id1 = 2L;
    }

    @Test
    public void SalvataggioDonazione(){
        Donazione donazioneSalvata = null;
        try {
            assertThat(gestioneSeduteService.salvataggioDonazione(codiceFiscale, id, tipoDonazione), isNotNull());
            //donazioneSalvata = gestioneSeduteService.salvataggioDonazione(codiceFiscale, id, tipoDonazione);
        } catch (CannotSaveDataRepositoryException e) {
            e.printStackTrace();
        }
    }
}
