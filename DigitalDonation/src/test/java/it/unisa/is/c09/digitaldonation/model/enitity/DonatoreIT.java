package it.unisa.is.c09.digitaldonation.model.enitity;


import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.model.entity.Tesserino;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.*;

import java.util.*;

/**
 * Classe di test di unità per la entity Donatore
 *
 * @author Fabio Siepe
 */
@RunWith(MockitoJUnitRunner.class)
public class DonatoreIT {

    private String residenza;
    private Date dataDiNascita;
    private String luogoDiNascita;
    private Tesserino tesserino;
    private List<Indisponibilita> listaIndisponibilita;

    private Donatore donatore;

    /**
     * Verifica che set e get di Residenza funzionano
     */
    @Test
    public void validaResidenza()
    {

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

       assertNotEquals("", residenza, donatore.getResidenza());
    }

    /**
     * Verifica che set e get di data di nascita funzionano
     */
    @Test
    public void validagetDataDINascita()
    {

        donatore = new Donatore();
        donatore.setCodiceFiscale("FJNYQC47M70C283I");
        donatore.setNome("Mattia");
        donatore.setCognome("Sapere");
        donatore.setEmail("mattiasapere81@gmail.com");
        donatore.setPassword("Mattia.123");
        donatore.setResidenza("Via Garibaldi, 44");
        Calendar myCalendar = new GregorianCalendar(202, 4, 22);
        donatore.setDataDiNascita(myCalendar.getTime());
        donatore.setLuogoDiNascita("Salerno");
        donatore.setTesserino(null);
        donatore.setListaIndisponibilita(null);

        assertNotEquals("", dataDiNascita, donatore.getDataDiNascita());
    }
    /**
     * Verifica che set e get di data di nascita funzionano
     */
    @Test
    public void validagetLuofoDiNascita()
    {

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

        assertNotEquals("", luogoDiNascita, donatore.getLuogoDiNascita());
    }

    /**
     * Verifica che set, get e addInidiponibilia di Idisponibilità funzionano
     */
    @Test
    public void validagetIndiponibilita()
    {

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

        Indisponibilita indisponibilita =  new Indisponibilita();
        donatore.addIndisponibilita(indisponibilita);

        assertNotEquals("", indisponibilita, donatore.getListaIndisponibilita());
    }
}
