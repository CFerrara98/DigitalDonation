package it.unisa.is.c09.digitaldonation.Utils.Forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class IndisponibilitaDonazioneForm {

    public IndisponibilitaDonazioneForm(Date dataProssimaDisponibilita, String motivazioni, String nomeMedico) {
        this.dataProssimaDisponibilita = dataProssimaDisponibilita;
        this.motivazioni = motivazioni;
        this.nomeMedico = nomeMedico;
    }

    /**
     * Metodo che ritorna il nome del medico.
     *
     * @return nomeMedico e' il nome del medico.
     */
    public String getNomeMedico() {
        return nomeMedico;
    }
    /**
     * Metodo che setta il nome del medico.
     *
     * @param nomeMedico è il nome del medico.
     */
    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    /**
     * Metodo che ritorna la data di prossima disponibilità.
     *
     * @return dataProssimaDisponibilita e' la data di prossima disponibilità.
     */
    public Date getDataProssimaDisponibilita() {
        return dataProssimaDisponibilita;
    }
    /**
     * Metodo che setta la data di prossima disponibilità.
     *
     * @param dataProssimaDisponibilita è la data di prossima disponibilità.
     */
    public void setDataProssimaDisponibilita(Date dataProssimaDisponibilita) {
        this.dataProssimaDisponibilita = dataProssimaDisponibilita;
    }

    /**
     * Metodo che ritorna le motivazioni di prossima disponibilità.
     *
     * @return motivazioni sono le motivazioni di prossima disponibilità.
     */
    public String getMotivazioni() {
        return motivazioni;
    }
    /**
     * Metodo che setta le motivazioni di prossima disponibilità.
     *
     * @param motivazioni sono le motivazioni di prossima disponibilità.
     */
    public void setMotivazioni(String motivazioni) {
        this.motivazioni = motivazioni;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dataProssimaDisponibilita;
    private String motivazioni;
    private String nomeMedico;
}
