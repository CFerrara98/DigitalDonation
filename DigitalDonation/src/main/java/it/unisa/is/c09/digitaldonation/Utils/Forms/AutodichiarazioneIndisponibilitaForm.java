package it.unisa.is.c09.digitaldonation.Utils.Forms;

import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class AutodichiarazioneIndisponibilitaForm {

    public AutodichiarazioneIndisponibilitaForm(Date dataProssimaDisponibilita, String motivazioni){
        this.dataProssimaDisponibilita = dataProssimaDisponibilita;
        this.motivazioni = motivazioni;
    }

    /**
     * Metodo che ritorna la data di disponbilità dell'autodichiarazione di indisponibilità.
     *
     * @return dataProssimaDisponibilita e' la data di disponbilità dell'autodichiarazione di indisponibilità.
     */
    public Date getDataProssimaDisponibilita() {
        return dataProssimaDisponibilita;
    }
    /**
     * Metodo che setta la data di disponbilità dell'autodichiarazione di indisponibilità.
     *
     * @param dataProssimaDisponibilita e' la data di disponbilità dell'autodichiarazione di indisponibilità.
     */
    public void setDataProssimaDisponibilita(Date dataProssimaDisponibilita) {
        this.dataProssimaDisponibilita = dataProssimaDisponibilita;
    }

    /**
     * Metodo che ritorna le motivazioni dell'autodichiarazione di indisponibilità.
     *
     * @return motivazioni sono le motivazioni dell'autodichiarazione di indisponibilità.
     */
    public String getMotivazioni() {
        return motivazioni;
    }
    /**
     * Metodo che setta le motivazioni dell'autodichiarazione di indisponibilità.
     *
     * @param motivazioni sono le motivazioni dell'autodichiarazione di indisponibilità.
     */
    public void setMotivazioni(String motivazioni) {
        this.motivazioni = motivazioni;
    }

    /**
     * Metodo che ritorna il nome del medico dell'autodichiarazione di indisponibilità.
     *
     * @return nomeMedico è il nome del medico dell'autodichiarazione di indisponibilità.
     */

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataProssimaDisponibilita;
    private String motivazioni;
}
