package it.unisa.is.c09.digitaldonation.Utils.Forms;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneTesserinoError.TesserinoFormException;
import it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement.GestioneTesserinoService;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Classe di test di unità per il TesserinoForm
 *
 * @author Fabio Siepe
 */

@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class TesserinoFormValidateUT {
    @Mock
    private BindingResult errors;

    @Mock
    private DonatoreRepository donatoreRepository;

    @Mock
    private DonazioneRepository donazioneRepository;

    @Mock
    private GuestRepository guestRepository;

    @Mock
    private IndisponibilitaRepository indisponibilitaRepository;

    @Mock
    private OperatoreRepository operatoreRepository;

    @Mock
    private SedeLocaleRepository sedeLocaleRepository;

    @Mock
    private SedutaRepository sedutaRepository;

    @Autowired
    private TesserinoRepository tesserinoRepository;

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private SedutaFormValidate sedutaFormValidate;

    @Mock
    private GestioneTesserinoService gestioneTesserinoService;

    @InjectMocks
    private TesserinoFormValidate tesserinoFormValidate;

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Image image;
    private Date dataDiNascita;
    private String luogoDiNascita;
    private String residenza;
    private String email;
    private String gruppoSanguigno;
    private String rh;
    private String altreIndicazioni;
    private int numeroMatricola;
    private int numeroTessera;
    private Date dataRilascio;
    private Date dataDonazione;
    private String tipoDonazione;
    private Tesserino tesserino;
    private TesserinoForm tesserinoForm;

    private Donatore donatore;
    private Utente utente;
    private Donazione donazione;
    private Date dataProssimaDisponibilita;

    /**
     * Verifica che il campo dataSeduta rispetti il formato
     */

    @Test
    public void VerificaNome() {
      /*  try {
            image = ImageIO.read(new File("src/test/java/it/unisa/is/c09/digitaldonation/OrganizzazioneSeduteManagement/test.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        nome = null;
        cognome = "Pepe";
        codiceFiscale = "PPEMRK00A53H703Y";
        Calendar dataNascita = new GregorianCalendar(2000, 1, 13);
        dataDiNascita = dataNascita.getTime();
        luogoDiNascita = "Salerno";
        residenza = "Via Garibaldi, 44 Salerno SA";
        email = "maripepe@gmail.com";
        gruppoSanguigno = "A";
        rh = "POS";
        altreIndicazioni = "Nessuna";
        numeroMatricola = 53764;
        numeroTessera = 65424;
        Calendar dataRilascioT = new GregorianCalendar(2020, 2, 13);
        dataRilascio = dataRilascioT.getTime();
        Calendar datadonazione = new GregorianCalendar(2021, 7, 11);
        dataDonazione = datadonazione.getTime();
        tipoDonazione = "cito";

        tesserinoForm = new TesserinoForm(nome, cognome, codiceFiscale, image, dataDiNascita, luogoDiNascita, residenza, email, gruppoSanguigno, rh, altreIndicazioni, numeroMatricola, numeroTessera, dataRilascio, dataDonazione, tipoDonazione);
        tesserino = new Tesserino();


        try {
           // when(tesserinoRepository.findDonatoreBydonatoreUtenteCodiceFiscale(codiceFiscale)).thenReturn(tesserino);
           // when(donatoreRepository.findDonatoreByCodiceFiscaleUtente(tesserinoForm.getCodiceFiscale())).thenReturn(donatore);

            when(gestioneTesserinoService.validaCodiceFiscale(tesserinoForm.getCodiceFiscale())).thenReturn(codiceFiscale);
        } catch (TesserinoFormException e) {
            e.printStackTrace();
        }


        tesserinoFormValidate.validate(tesserinoForm, errors);

        assertEquals("Nome non valido", "", tesserinoForm.getNome());
*/
    }
}
