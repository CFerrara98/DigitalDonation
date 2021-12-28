package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella una donazione effettuata da un donatore.
 */
@Entity
public class Donazione {

    @Id
    //TODO Fix Foreign Key
    @ManyToOne
    @JoinColumn(name="tesserino_id", nullable=false)
    private Long idTesserino;
    @Id
    private Date dataDonazione;
    private String tipoDonazione;

    /**
     * Costruttore che crea un oggetto Donazione vuoto,
     * che verra' popolato con i metodi setters.
     */
    public Donazione() {
    }

    /**
     * Costruttore di una Donazione con parametri utili nei casi di test.
     * @param idTesserino è l'id del tesserino.
     * @param dataDonazione è la data della donazione effettuata.
     * @param tipoDonazione è il tipo di donazione effettuata.
     */
    public Donazione(Long idTesserino, Date dataDonazione, String tipoDonazione) {
        this.idTesserino = idTesserino;
        this.dataDonazione = dataDonazione;
        this.tipoDonazione = tipoDonazione;
    }

    /**
     * Metodo che ritorna l'id del tesserino.
     * @return idTesserino e' l'id del tesserino.
     */
    public Long getIdTesserino() {
        return idTesserino;
    }

    /**
     * Metodo che setta l'id del tesserino.
     * @param idTesserino e' l'id del tesserino.
     */
    public void setIdTesserino(Long idTesserino) {
        this.idTesserino = idTesserino;
    }

    /**
     * Metodo che ritorna la data della donazione.
     * @return dataDonazione e' la data della donazione.
     */
    public Date getDataDonazione() {
        return dataDonazione;
    }

    /**
     * Metodo che setta la data della donazione.
     * @param dataDonazione e' la data della donazione.
     */
    public void setDataDonazione(Date dataDonazione) {
        this.dataDonazione = dataDonazione;
    }

    /**
     * Metodo che ritorna il tipo di donazione.
     * @return tipoDonazione e' il tipo di donazione.
     */
    public String getTipoDonazione() {
        return tipoDonazione;
    }

    /**
     * Metodo che setta il tipo della donazione.
     * @param tipoDonazione e' il tipo della donazione.
     */
    public void setTipoDonazione(String tipoDonazione) {
        this.tipoDonazione = tipoDonazione;
    }

    /** Espressioni regolare che definisce il formato del campo tipo donazione */
    public static final String TIPODONAZIONE_REGEX = "^(plasma|cito|sangue)$ ";
}
