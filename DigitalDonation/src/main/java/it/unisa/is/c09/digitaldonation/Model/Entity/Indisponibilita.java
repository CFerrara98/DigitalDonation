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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idIndisponibilita;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Donatore donatore;
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
     * @param donatore è l'oggetto donatore.
     * @param dataProssimaDisponibilita è la data di fine indisponibilità.
     * @param motivazioni sono le motivazioni di indisponibilità.
     * @param nomeMedico è il nome del medico che ha effettuato la visita.
     */
    public Indisponibilita(Donatore donatore, Date dataProssimaDisponibilita, String motivazioni, String nomeMedico) {
        this.donatore = donatore;
        this.dataProssimaDisponibilita = dataProssimaDisponibilita;
        this.motivazioni = motivazioni;
        this.nomeMedico = nomeMedico;
    }

    /**
     * Metodo che ritorna l'id dell'indisponibilità.
     * @return idIndisponibilita e' l'id dell'indisponibilità.
     */
    public Long getIdIndisponibilita() {
        return idIndisponibilita;
    }

    /**
     * Metodo che setta l'id dell'indisponibilità.
     * @param idIndisponibilita e' l'id dell'indisponibilità.
     */
    public void setIdIndisponibilita(Long idIndisponibilita) {
        this.idIndisponibilita = idIndisponibilita;
    }

    /**
     * Metodo che ritorna l'oggetto donatore.
     * @return donatore e' l'oggetto donatore.
     */
    public Donatore getDonatore() {
        return donatore;
    }

    /**
     * Metodo che setta l'oggetto donatore.
     * @param donatore e' l'oggetto donatore.
     */
    public void setDonatore(Donatore donatore) {
        this.donatore = donatore;
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