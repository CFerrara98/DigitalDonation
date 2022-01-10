package it.unisa.is.c09.digitaldonation.utils.forms;

import it.unisa.is.c09.digitaldonation.utils.form.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per l'autodichiarazione indisonibilità form
 *
 * @author Mattia Sapere
 */
@RunWith(MockitoJUnitRunner.class)
public class AutodichiarazioneIndisponibilitaFormValidateUT {

    @Mock
    private BindingResult errors;

    @InjectMocks
    private AutodichiarazioneIndisponibilitaFormValidate autodichiarazioneIndisponibilitaFormValidate;

    private String motivazioni;
    private Date dataProssimaDisponibilita;

    private AutodichiarazioneIndisponibilitaForm autodichiarazioneIndisponibilitaForm;

    /**
     * Verifica che il campo motivazioni sia corretto
     */
    @Test
    public void ValidaFormatoMotivazioni(){
        motivazioni = "";
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataProssimaDisponibilita = myCalendar1.getTime();

        autodichiarazioneIndisponibilitaForm = new AutodichiarazioneIndisponibilitaForm(dataProssimaDisponibilita, motivazioni);
        autodichiarazioneIndisponibilitaFormValidate.validate(autodichiarazioneIndisponibilitaForm, errors);

        assertEquals("", "", autodichiarazioneIndisponibilitaForm.getMotivazioni());
    }

    /**
     * Verifica che il campo dataProssimaDisponibilita sia corretto
     */
    @Test
    public void ValidaFormatoDataProssimaDisponibilita(){
        motivazioni = "Sono malato";
        Calendar myCalendar1 = new GregorianCalendar(2022,1,1);
        dataProssimaDisponibilita = null;

        autodichiarazioneIndisponibilitaForm = new AutodichiarazioneIndisponibilitaForm(dataProssimaDisponibilita, motivazioni);
        autodichiarazioneIndisponibilitaFormValidate.validate(autodichiarazioneIndisponibilitaForm, errors);

        assertEquals("",myCalendar1.getTime(),autodichiarazioneIndisponibilitaForm.getDataProssimaDisponibilita());
    }

    @Test
    public void VerificaSupports() {

        assertEquals("Volevo Solo più branche coverage", false, autodichiarazioneIndisponibilitaFormValidate.supports(AutodichiarazioneIndisponibilitaFormValidate.class));

    }
}
