package it.unisa.is.c09.digitaldonation.gestionetesserinomanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Donazione;
import it.unisa.is.c09.digitaldonation.model.entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.model.entity.Tesserino;
import it.unisa.is.c09.digitaldonation.model.repository.*;
import it.unisa.is.c09.digitaldonation.utentemanagement.MailSingletonSender;
import org.apache.catalina.valves.JsonErrorReportValve;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Test di integrazione fra la classe GestioneSeduteService e la repository.
 * Metodologia: bottom-up.
 *
 * @author Mattia Sapere, Elpidio Mazza
 */

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback
public class GestioneTesserinoServiceIT {

    @Autowired
    private GestioneTesserinoService gestioneTesserinoService;

    //Riferimento GestioneTesserinoService
    @Autowired
    private MailSingletonSender mailSingletonSender;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private DonazioneRepository donazioneRepository;
    @Autowired
    private TesserinoRepository tesserinoRepository;
    @Autowired
    private DonatoreRepository donatoreRepository;
    @Autowired
    private IndisponibilitaRepository indisponibilitaRepository;
    @Autowired
    private SedutaRepository sedutaRepository;

    private Donatore donatore;
    private Tesserino tesserino;
    private Donazione donazione;
    private Indisponibilita indisponibilita;


    @Before
    public void setUp(){
        donatore = new Donatore();
        donatore.setCodiceFiscale("FJNYQC47M70C283I");
        donatore.setNome("Mattia");
        donatore.setCognome("Sapere");
        donatore.setEmail("mattiasapere81@gmail.com");
        donatore.setPassword("Mattia.123");
        donatore.setResidenza("Via Garibaldi, 44");
        donatore.setDataDiNascita(new GregorianCalendar(2022, 4, 15).getTime());
        donatore.setLuogoDiNascita("Salerno");
        donatore.setTesserino(null);
        donatore.setListaIndisponibilita(new ArrayList<>());


        tesserino = new Tesserino();
        tesserino.setImgSource("Foto.png");
        tesserino.setDonatoreUtenteCodiceFiscale(donatore.getCodiceFiscale());
        tesserino.setGruppoSanguigno("A");
        tesserino.setRh("POS");
        tesserino.setNumeroMatricola(53453);
        tesserino.setIdTessera(null);
        tesserino.setDataRilascio(new GregorianCalendar(2020, 10, 13).getTime());
        tesserino.setListaDonazioni(new ArrayList<>());


        donazione =  new Donazione();
        donazione.setIdDonazione(null);
        donazione.setTesserino(tesserino);
        donazione.setDataDonazione(new GregorianCalendar(2020, 2, 13).getTime());
        donazione.setTipoDonazione("cito");

        indisponibilita = new Indisponibilita();
        indisponibilita.setCodiceFiscaleDonatore(donatore.getCodiceFiscaleUtente());
        indisponibilita.setMotivazioni("Abuso di alcol");
        indisponibilita.setNomeMedico("Mario Rossi");
        indisponibilita.setDataProssimaDisponibilita(new GregorianCalendar(2021, 10, 13).getTime());

    }



    /**
     * Testa il corretto funzionamento del metodo creazioneTesserino
     * @Test {@Link GestioneTesserinoService#creazioneTesserino(Donatore, Tesserino, Donazione)}
     * @result Il test e' superato se la creazione del tesserino avviene.
     */
    @Test
    public void creazioneTesserino(){
        Tesserino tesserino2 =null;
        try {
            tesserino.setIdTessera(250L);
            tesserino2 = gestioneTesserinoService.creazioneTesserino(donatore,tesserino,donazione);
        } catch (CannotSaveDataRepositoryException e) {
            e.printStackTrace();
        }
        assertEquals(tesserinoRepository.findTesserinoByIdTessera(tesserino2.getIdTessera()),tesserino2);
    }


    /**
     * Testa il corretto funzionamento del metodo autodichiarazioneIndisponibilita l'indisponibilità di un donatore a donare
     *
     * @Test {@Link GestioneTesserinoService#autodichiarazioneIndisponibilita(Indisponibilita indisponibilita)}
     * @result Il test e' superato se l'indisponibilita' e' inserita con successo.
     */
    @Test
    public void autodichiarazioneIndisponibilita(){
        donatore.addIndisponibilita(indisponibilita);
        Donatore donatore2 = donatoreRepository.save(donatore);
        Indisponibilita indisponibilita2= null;
        try {
            indisponibilita2 = gestioneTesserinoService.autodichiarazioneIndisponibilita(indisponibilita);
        } catch (CannotSaveDataRepositoryException e) {
            e.printStackTrace();
        }
        assertNotNull(indisponibilita2);
    }

    /**
     * Testa il corretto funzionamento del metodo aggiornaTesserino
     *
     * @Test {@Link GestioneTesserinoService#aggiornaTesserino(Utente utente)}
     * @result Il test e' superato se le viene restituito un tesserino
     */
    @Test
    public void aggiornaTesserino(){
        donatoreRepository.save(donatore);
        tesserinoRepository.save(tesserino);
        Tesserino tesserino2 = null;
        try {
            tesserino2 = gestioneTesserinoService.aggiornaTesserino(donatore);
        } catch (CannotSaveDataRepositoryException e) {
            e.printStackTrace();
        }
        assertNotNull(tesserino2);
    }




}
