package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella l'indisponibilità a donare di un donatore.
 */
@Entity
public class Indisponibilita {

    @Id
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "donatore")
    private String codiceFiscaleDonatore;
    private Date dataProssimaDisponibilita;
    private String motivazioni;
    private String nomeMedico;

    /**
     * Costruttore che crea un oggetto Donazione vuoto,
     * che verra' popolato con i metodi setters.
     */
    public Indisponibilita(){}

    /**
     * Costruttore di un'Indisponibilita' con parametri utili nei casi di test.
     * @param codiceFiscaleDonatore è il codice fiscale del donatore.
     * @param dataProssimaDisponibilita è la data di fine indisponibilità.
     * @param motivazioni sono le motivazioni di indisponibilità.
     * @param nomeMedico è il nome del medico che ha effettuato la visita.
     */
    public Indisponibilita(String codiceFiscaleDonatore, Date dataProssimaDisponibilita, String motivazioni, String nomeMedico) {
        this.codiceFiscaleDonatore = codiceFiscaleDonatore;
        this.dataProssimaDisponibilita = dataProssimaDisponibilita;
        this.motivazioni = motivazioni;
        this.nomeMedico = nomeMedico;
    }

    /**
     * Metodo che ritorna il codice fiscale del donatore.
     * @return codiceFiscaleDonatore e' il codice fiscale del donatore.
     */
    public String getCodiceFiscaleDonatore() {
        return codiceFiscaleDonatore;
    }

    /**
     * Metodo che setta il codice fiscale del donatore.
     * @param codiceFiscaleDonatore e' il codice fiscale del donatore.
     */
    public void setCodiceFiscaleDonatore(String codiceFiscaleDonatore) {
        this.codiceFiscaleDonatore = codiceFiscaleDonatore;
    }

    /**
     * Metodo che ritorna la data della prossima disponibilità a donare.
     * @return dataProssimaDisponibilita e' la data della prossima disponibilità a donare.
     */
    public Date getDataProssimaDisponibilita() {
        return dataProssimaDisponibilita;
    }

    /**
     * Metodo che setta la data della prossima disponibilità a donare.
     * @param dataProssimaDisponibilita e' la data della prossima disponibilità a donare.
     */
    public void setDataProssimaDisponibilita(Date dataProssimaDisponibilita) {
        this.dataProssimaDisponibilita = dataProssimaDisponibilita;
    }

    /**
     * Metodo che ritorna le motivazioni dell'indisponibilità a donare.
     * @return motivazioni sono le motivazioni dell'indisponibilità a donare.
     */
    public String getMotivazioni() {
        return motivazioni;
    }

    /**
     * Metodo che setta le motivazioni dell'indisponibilità a donare.
     * @param motivazioni sono le motivazioni dell'indisponibilità a donare.
     */
    public void setMotivazioni(String motivazioni) {
        this.motivazioni = motivazioni;
    }

    /**
     * Metodo che ritorna il nome del medico che ha effettuato la visita.
     * @return nomeMedico e' il nome del medico che ha effettuato la visita.
     */
    public String getNomeMedico() {
        return nomeMedico;
    }

    /**
     * Metodo che setta il nome del medico che ha effettuato la visita.
     * @param nomeMedico è il nome del medico che ha effettuato la visita.
     */
    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }
}