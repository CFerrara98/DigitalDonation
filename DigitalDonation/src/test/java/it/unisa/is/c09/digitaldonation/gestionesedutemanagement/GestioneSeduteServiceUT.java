package it.unisa.is.c09.digitaldonation.gestionesedutemanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.*;
import it.unisa.is.c09.digitaldonation.model.entity.*;
import it.unisa.is.c09.digitaldonation.model.repository.*;
import it.unisa.is.c09.digitaldonation.utils.forms.IndisponibilitaDonazioneForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Classe di test di unità per il Service Gestione Sedute
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@RunWith(MockitoJUnitRunner.class)
public class GestioneSeduteServiceUT {

    @Mock
    private IndisponibilitaRepository indisponibilitaRepository;

    @Mock
    private TesserinoRepository tesserinoRepository;

    @Mock
    private DonatoreRepository donatoreRepository;

    @Mock
    private DonazioneRepository donazioneRepository;

    @Mock
    private SedutaRepository sedutaRepository;

    @InjectMocks
    private GestioneSeduteService gestioneSeduteService;

    /**
     * Verifica salvataggioDonazione nel caso in cui il donatore è null
     */
    @Test
    public void VerificaGestioneSeduteSalvataggioDonatoreNull() {
        Donatore donatore = new Donatore();

        final String message = "Errore, il donatore è null";
        when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(donatore.getCodiceFiscale())).thenReturn(null);
        try {
            gestioneSeduteService.salvataggioDonazione(donatore.getCodiceFiscale(), null, null);
        } catch (CannotSaveDataRepositoryException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica salvataggioDonazione nel caso in cui la seduta è null
     */
    @Test
    public void VerificaGestioneSeduteSalvataggioSedutaNull() {
        Seduta seduta = new Seduta();
        Donatore donatore = new Donatore();

        final String message = "Errore, la seduta è null";
        when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(donatore.getCodiceFiscaleUtente())).thenReturn(donatore);
        when(sedutaRepository.findByIdSeduta(seduta.getIdSeduta())).thenReturn(null);
        try {
            gestioneSeduteService.salvataggioDonazione(donatore.getCodiceFiscale(), seduta.getIdSeduta(), null);
        } catch (CannotSaveDataRepositoryException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica salvataggioDonazione nel caso in cui la seduta è null
     */
    @Test
    public void VerificaGestioneSeduteExistTrue() {
        Seduta seduta = new Seduta();
        Donatore donatore = new Donatore();
        donatore.setCodiceFiscale("ACNCZY45X38I793C");
        Donazione donazione = new Donazione();
        Tesserino tesserino = new Tesserino();
        Indisponibilita indisponibilita = new Indisponibilita();

        final String message = "Errore, la seduta è null";
        when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(donatore.getCodiceFiscaleUtente())).thenReturn(donatore);
        when(sedutaRepository.findByIdSeduta(seduta.getIdSeduta())).thenReturn(seduta);
        when(sedutaRepository.existsByIdSedutaAndListaDonatore_CodiceFiscaleUtente(seduta.getIdSeduta(), donatore.getCodiceFiscale())).thenReturn(true);
        when(tesserinoRepository.findByDonatoreUtenteCodiceFiscale(donatore.getCodiceFiscale())).thenReturn(tesserino);
        //when(donazioneRepository.save(donazione)).thenReturn(donazione);
        when(tesserinoRepository.save(tesserino)).thenReturn(tesserino);
        //when(indisponibilitaRepository.save(indisponibilita)).thenReturn(indisponibilita);

        Calendar dataIndisponibilita = new GregorianCalendar(2022, 5, 15);
        indisponibilita = new Indisponibilita(dataIndisponibilita.getTime(), "Mal di testa",  "Dr. peppe");
        indisponibilita.setCodiceFiscaleDonatore("ACNCZY45X38I793C");

        List<Donatore> listaDonatore = new ArrayList<>();
        Donatore donatore1 = new Donatore("ACNCZY45X38I793C", null, null, null, null, null, null, null, null, null);
        Donatore donatore2 = new Donatore("AFCLCW22Z02F362J", null, null, null, null, null, null, null, null, null);
        listaDonatore.add(donatore1);
        listaDonatore.add(donatore2);

        List<Seduta> listaSedute = new ArrayList<>();
        Calendar dataSeduta1 = new GregorianCalendar(2022, 4, 15);
        Calendar dataSeduta2 = new GregorianCalendar(2022, 6, 15);
        Seduta seduta1 = new Seduta(52l, dataSeduta1.getTime(), null, null, null, 0, null, null, null, null, listaDonatore);
        Seduta seduta2 = new Seduta(52l, dataSeduta2.getTime(), null, null, null, 0, null, null, null, null, listaDonatore);
        listaSedute.add(seduta1);
        listaSedute.add(seduta2);
        when(sedutaRepository.findAll()).thenReturn(listaSedute);

        try {
            gestioneSeduteService.salvataggioDonazione(donatore.getCodiceFiscale(), seduta.getIdSeduta(), null);
        } catch (CannotSaveDataRepositoryException e) {
            assertEquals(message, e.getMessage());
        }

    }

    /**
     * Verifica ValidaTipoDonazione nel caso in cui il donatore è null
     */
    @Test
    public void VerificaGestioneSeduteDonatoreNull() {
        Donatore donatore = new Donatore();
        Seduta seduta = new Seduta();
        Long idSeduta = 1L;
        String codiceFiscaleDonatore = "ACNCZY45X38I793C";
        Calendar c = new GregorianCalendar(2022, 05, 01);

        IndisponibilitaDonazioneForm indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm();
        indisponibilitaDonazioneForm.setDataProssimaDisponibilita(c.getTime());
        indisponibilitaDonazioneForm.setMotivazioni(null);
        indisponibilitaDonazioneForm.setNomeMedico(null);

        //when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore)).thenReturn(donatore);
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(seduta);

        final String message = "Errore, il donatore è null";
        try {
            gestioneSeduteService.salvataggioIndisponibilita(donatore.getCodiceFiscale(), idSeduta,indisponibilitaDonazioneForm);
        } catch (CannotSaveDataRepositoryException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica ValidaTipoDonazione nel caso in cui la seduta è null
     */
    @Test
    public void VerificaGestioneSeduteSedutaNull(){
        Donatore donatore = new Donatore();
        Seduta seduta = new Seduta();
        Long idSeduta = 1L;
        String codiceFiscaleDonatore = "ACNCZY45X38I793C";
        Calendar c = new GregorianCalendar(2022, 05, 01);

        IndisponibilitaDonazioneForm indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm();
        indisponibilitaDonazioneForm.setDataProssimaDisponibilita(c.getTime());
        indisponibilitaDonazioneForm.setMotivazioni(null);
        indisponibilitaDonazioneForm.setNomeMedico(null);

        when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore)).thenReturn(donatore);
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(null);

        final String message = "Errore, la seduta è null";
        try {
            gestioneSeduteService.salvataggioIndisponibilita(codiceFiscaleDonatore, idSeduta, indisponibilitaDonazioneForm);
        } catch (CannotSaveDataRepositoryException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica ValidaTipoDonazione nel caso in cui ha successo
     */
    @Test
    public void VerificaGestioneSeduteSuccesso(){
        Indisponibilita indisponibilita = new Indisponibilita();
        Donatore donatore = new Donatore();
        Seduta seduta = new Seduta();
        String codiceFiscaleDonatore = "ACNCZY45X38I793C";
        Long idSeduta = 1l;
        Calendar c = new GregorianCalendar(2022, 05, 01);
        Calendar dataIndisponibilita = new GregorianCalendar(2022, 5, 15);

        IndisponibilitaDonazioneForm indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm();
        indisponibilitaDonazioneForm.setDataProssimaDisponibilita(c.getTime());
        indisponibilitaDonazioneForm.setMotivazioni(null);
        indisponibilitaDonazioneForm.setNomeMedico(null);

        //when(indisponibilitaRepository.save(indisponibilita)).thenReturn(indisponibilita);
        when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore)).thenReturn(donatore);
        when(sedutaRepository.findByIdSeduta(idSeduta)).thenReturn(seduta);

        indisponibilita = new Indisponibilita(dataIndisponibilita.getTime(), "Mal di testa",  "Dr. peppe");
        indisponibilita.setCodiceFiscaleDonatore("ACNCZY45X38I793C");

        List<Donatore> listaDonatore = new ArrayList<>();
        Donatore donatore1 = new Donatore("ACNCZY45X38I793C", null, null, null, null, null, null, null, null, null);
        Donatore donatore2 = new Donatore("AFCLCW22Z02F362J", null, null, null, null, null, null, null, null, null);
        listaDonatore.add(donatore1);
        listaDonatore.add(donatore2);

        List<Seduta> listaSedute = new ArrayList<>();
        Calendar dataSeduta1 = new GregorianCalendar(2022, 4, 15);
        Calendar dataSeduta2 = new GregorianCalendar(2022, 6, 15);
        Seduta seduta1 = new Seduta(52l, dataSeduta1.getTime(), null, null, null, 0, null, null, null, null, listaDonatore);
        Seduta seduta2 = new Seduta(52l, dataSeduta2.getTime(), null, null, null, 0, null, null, null, null, listaDonatore);
        listaSedute.add(seduta1);
        listaSedute.add(seduta2);
        when(sedutaRepository.findAll()).thenReturn(listaSedute);

        try {
            gestioneSeduteService.salvataggioIndisponibilita(codiceFiscaleDonatore, idSeduta, indisponibilitaDonazioneForm);
        } catch (CannotSaveDataRepositoryException e) {
            fail("errore!");
        }
    }

    /**
     * Verifica ValidaTipoDonazione nel caso in cui il tipo di donazione è null
     */
    @Test
    public void VerificaGestioneSeduteTipoDonazioneNull() {
        String tipoDonazione = null;
        final String message = "Il formato del tipo di donazione è errato.";
        try {
            gestioneSeduteService.validaTipoDonazione(tipoDonazione);
        } catch (ConfermaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica ValidaTipoDonazione nel caso in cui il tipo di donazione non rispetta la regex
     */
    @Test
    public void VerificaGestioneSeduteTipoDonazioneRegex() {
        String tipoDonazione = "Mattia";
        final String message = "Il formato del tipo di donazione è errato.";
        try {
            gestioneSeduteService.validaTipoDonazione(tipoDonazione);
        } catch (ConfermaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica ValidaTipoDonazione nel caso in cui ha successo
     */
    @Test
    public void VerificaGestioneSeduteTipoDonazioneSuccesso() {
        String tipoDonazione = "plasma";
        try {
            gestioneSeduteService.validaTipoDonazione(tipoDonazione);
        } catch (ConfermaDonazioneFormException e) {
            fail("errore!");
        }
    }

    /**
     * Verifica validaMotivazioni nel caso in cui le motivazioni siano null
     */
    @Test
    public void VerificaGestioneSeduteMotivazioniNull() {
        String motivazioni = null;
        final String message = "Il formato delle motivazioni è errato.";
        try {
            gestioneSeduteService.validaMotivazioni(motivazioni);
        } catch (IndisponibilitaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica validaMotivazioni nel caso in cui le motivazioni non rispettano la regex
     */
    @Test
    public void VerificaGestioneSeduteMotivazioniRegex() {
        String motivazioni = "prova1234567++";
        final String message = "Il formato delle motivazioni è errato.";
        try {
            gestioneSeduteService.validaMotivazioni(motivazioni);
        } catch (IndisponibilitaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica validaMotivazioni nel caso in cui ha successo
     */
    @Test
    public void VerificaGestioneSeduteMotivazioniSucesso() {
        String motivazioni = "Malori vari";
        try {
            gestioneSeduteService.validaMotivazioni(motivazioni);
        } catch (IndisponibilitaDonazioneFormException e) {
            fail("errore!");
        }
    }

    /**
     * Verifica validaNomeMedico nel caso in cui il nome del medico è null
     */
    @Test
    public void VerificaGestioneSeduteNomeMedicoNull() {
        String nomeMedico = null;
        final String message = "Il formato del nome del medico è errato.";
        try {
            gestioneSeduteService.validaNomeMedico(nomeMedico);
        } catch (IndisponibilitaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica validaNomeMedico nel caso in cui il nome del medico non rispetta la regex
     */
    @Test
    public void VerificaGestioneSeduteNomeMedicoRegex() {
        String nomeMedico = "M1att554ia";
        final String message = "Il formato del nome del medico è errato.";
        try {
            gestioneSeduteService.validaNomeMedico(nomeMedico);
        } catch (IndisponibilitaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica validaNomeMedico nel caso in cui il nome del medico è corretto
     */
    @Test
    public void VerificaGestioneSeduteNomeMedicoSucesso() {
        String nomeMedico = "Mattia";
        try {
            gestioneSeduteService.validaNomeMedico(nomeMedico);
        } catch (IndisponibilitaDonazioneFormException e) {
            fail("errore!");
        }
    }

    /**
     * Verifica validaDataProssimaDisponibilitaDonazione nel caso in cui la data della prossima disponibilità sia null
     */
    @Test
    public void VerificaMonitoraggioSeddutaDataProssimaDisponibilitaNull() {
        Date data = null;
        final String message = "La data di prossima disponibilità inserita non rispetta il formato: gg/mm/aaaa.";
        try {
            gestioneSeduteService.validaDataProssimaDisponibilitaDonazione(data);
        } catch (IndisponibilitaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica MonitoraggioSeduta nel caso in cui la data della prossima disponibilità non rispetta la regex
     */
    @Test
    public void VerificaMonitoraggioSeddutaDataProssimaDisponibilitaRegex() {
        Calendar c = new GregorianCalendar(1222222, 12, 12);

        final String message = "La data di prossima disponibilità inserita non rispetta il formato: gg/mm/aaaa.";
        try {
            gestioneSeduteService.validaDataProssimaDisponibilitaDonazione(c.getTime());
        } catch (IndisponibilitaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica MonitoraggioSeduta nel caso in cui la data della prossima disponibilità è minore di quella attuale
     */
    @Test
    public void VerificaMonitoraggioSeddutaDataProssimaDisponibilitaBefore() {
        Calendar c = new GregorianCalendar(2020, 01, 02);

        final String message = "La data di prossima disponibilità inserita è minore della data corrente.";
        try {
            gestioneSeduteService.validaDataProssimaDisponibilitaDonazione(c.getTime());
        } catch (IndisponibilitaDonazioneFormException e) {
            assertEquals(message, e.getMessage());
        }
    }

    /**
     * Verifica MonitoraggioSeduta nel caso in cui la data della prossima disponibilità ha successo
     */
    @Test
    public void VerificaMonitoraggioSeddutaDataProssimaDisponibilitaSuccesso() {
        Calendar c = new GregorianCalendar(2022, 07, 02);

        try {
            gestioneSeduteService.validaDataProssimaDisponibilitaDonazione(c.getTime());
        } catch (IndisponibilitaDonazioneFormException e) {
            fail("errore!");
        }
    }
}