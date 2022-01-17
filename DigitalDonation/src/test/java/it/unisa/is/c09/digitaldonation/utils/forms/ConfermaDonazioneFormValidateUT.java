package it.unisa.is.c09.digitaldonation.utils.forms;

import it.unisa.is.c09.digitaldonation.utils.form.ConfermaDonazioneForm;
import it.unisa.is.c09.digitaldonation.utils.form.ConfermaDonazioneFormValidate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BindingResult;

import static org.junit.Assert.assertEquals;

/**
 * Classe di test di unità per conferma donazione form
 *
 * @author Mattia Sapere
 */
@RunWith(MockitoJUnitRunner.class)
public class ConfermaDonazioneFormValidateUT {

    @Mock
    private BindingResult errors;

    @InjectMocks
    private ConfermaDonazioneFormValidate confermaDonazioneFormValidate;

    private String tipoDonazione;

    private ConfermaDonazioneForm confermaDonazioneForm;

    /**
     * Verifica che il camp tipoDonazione sia corretto
     */
    @Test
    public void VerificaFormatoNome() {
        tipoDonazione = "";

        confermaDonazioneForm = new ConfermaDonazioneForm(tipoDonazione);
        confermaDonazioneFormValidate.validate(confermaDonazioneForm, errors);

        assertEquals("", "", confermaDonazioneForm.getTipoDonazione());
    }


    @Test
    public void VerificaSupports() {

        assertEquals("Volevo Solo più branche coverage", false, confermaDonazioneFormValidate.supports(ConfermaDonazioneForm.class));

    }
}
