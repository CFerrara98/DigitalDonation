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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idDonazione;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Tesserino tesserino;
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
     * @param tesserino è l'oggetto tesserino.
     * @param dataDonazione è la data della donazione effettuata.
     * @param tipoDonazione è il tipo di donazione effettuata.
     */
    public Donazione(Tesserino tesserino, Date dataDonazione, String tipoDonazione) {
        this.tesserino = tesserino;
        this.dataDonazione = dataDonazione;
        this.tipoDonazione = tipoDonazione;
    }

    /**
     * Metodo che ritorna l'id della donazione.
     * @return idDonazione e' l'id della donazione.
     */
    public Long getIdDonazione() {
        return idDonazione;
    }

    /**
     * Metodo che setta l'id della donazione.
     * @param idDonazione e' l'id della donazione.
     */
    public void setIdDonazione(Long idDonazione) {
        this.idDonazione = idDonazione;
    }

    /**
     * Metodo che ritorna l'oggetto tesserino.
     * @return tesserino e' l'oggetto tesserino.
     */
    public Tesserino getTesserino() {
        return tesserino;
    }

    /**
     * Metodo che setta l'oggetto tesserino.
     * @param tesserino e' l'oggetto tesserino.
     */
    public void setTesserino(Tesserino tesserino) {
        this.tesserino = tesserino;
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

    @Override
    public String toString() {
        return "Donazione{" +
                "idDonazione=" + idDonazione +
                ", tesserino=" + tesserino +
                ", dataDonazione=" + dataDonazione +
                ", tipoDonazione='" + tipoDonazione + '\'' +
                '}';
    }
}
