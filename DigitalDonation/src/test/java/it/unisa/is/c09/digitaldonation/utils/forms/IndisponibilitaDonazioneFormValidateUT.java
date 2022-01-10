package it.unisa.is.c09.digitaldonation.utils.forms;

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
 * Classe di test di unità per indisponibilità donazione form
 *
 * @author Mattia Sapere
 */
@RunWith(MockitoJUnitRunner.class)
public class IndisponibilitaDonazioneFormValidateUT {

    @Mock
    private BindingResult errors;

    @InjectMocks
    private IndisponibilitaDonazioneFormValidate indisponibilitaDonazioneFormValidate;

    private String motivazioni;
    private String nomeMedico;
    private Date dataProssimaDisponibilita;


    private IndisponibilitaDonazioneForm indisponibilitaDonazioneForm;

    /**
     * Verifica che il campo motivazioni sia corretto
     */
    @Test
    public void VerificaFormatoMotivazioni() {
        motivazioni = null;
        nomeMedico = "Mattia";
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataProssimaDisponibilita = myCalendar1.getTime();

        indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm(dataProssimaDisponibilita, motivazioni, nomeMedico);
        indisponibilitaDonazioneFormValidate.validate(indisponibilitaDonazioneForm, errors);

        assertEquals("", "", indisponibilitaDonazioneForm.getMotivazioni());
    }

    /**
     * Verifica che il campo motivazioni sia corretto
     */
    @Test
    public void VerificaFormatoMotivazioni1() {
        motivazioni = "";
        nomeMedico = "Mattia";
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataProssimaDisponibilita = myCalendar1.getTime();

        indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm(dataProssimaDisponibilita, motivazioni, nomeMedico);
        indisponibilitaDonazioneFormValidate.validate(indisponibilitaDonazioneForm, errors);

        assertEquals("", "", indisponibilitaDonazioneForm.getMotivazioni());
    }

    /**
     * Verifica che il campo nomeMedico sia corretto
     */
    @Test
    public void VerificaFormatoNomeMedico() {
        motivazioni = "Allergia";
        nomeMedico = null;
        Calendar myCalendar1 = new GregorianCalendar(2022, 4, 15);
        dataProssimaDisponibilita = myCalendar1.getTime();

        indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm(dataProssimaDisponibilita, motivazioni, nomeMedico);
        indisponibilitaDonazioneFormValidate.validate(indisponibilitaDonazioneForm, errors);

        assertEquals("", "", indisponibilitaDonazioneForm.getNomeMedico());
    }

    /**
     * Verifica che il campo dataProssimaDisponibilita sia corretto
     */
    @Test
    public void VerificaFormatoDataProssimaDisponibilita() {
        motivazioni = "Allergia";
        nomeMedico = "Mattia";
        Calendar myCalendar = new GregorianCalendar(2022, 1, 1);
        dataProssimaDisponibilita = null;

        indisponibilitaDonazioneForm = new IndisponibilitaDonazioneForm(dataProssimaDisponibilita, motivazioni, nomeMedico);
        indisponibilitaDonazioneFormValidate.validate(indisponibilitaDonazioneForm, errors);

        assertEquals("", myCalendar.getTime(), indisponibilitaDonazioneForm.getDataProssimaDisponibilita());
    }

    @Test
    public void VerificaSupports() {

        assertEquals("Volevo Solo più branche coverage", false, indisponibilitaDonazioneFormValidate.supports(IndisponibilitaDonazioneFormValidate.class));

    }
}
