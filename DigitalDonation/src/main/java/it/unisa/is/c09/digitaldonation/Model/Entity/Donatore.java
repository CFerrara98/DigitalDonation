package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella un donatore.
 */
@Entity
public class Donatore extends Utente{

    private String residenza;
    private Date dataDiNascita;
    private String luogoDiNascita;
    @OneToOne(cascade = CascadeType.ALL)     @JoinColumn(name = "tesserino", referencedColumnName = "idTessera")
    private Tesserino tesserino;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "donatore")
    private List<Indisponibilita> listaIndisponibilita;

    /**
     * Costruttore che crea un oggetto Donatore vuoto,
     * che verra' popolato con i metodi setters.
     */
    public Donatore() {
        listaIndisponibilita = new ArrayList<Indisponibilita>();
    }

    /**
     * Costruttore di un utente con parametri utili nei casi di test.
     * @param codiceFiscale è il codice fiscale dell'utente.
     * @param nome è il nome dell'utente.
     * @param cognome è il cognome dell'utente.
     * @param email è l'email dell'utente.
     * @param password è la password dell'utente.
     * @param residenza è la residenza dell'utente.
     * @param dataDiNascita è la data di nascita dell'utente.
     * @param luogoDiNascita è il luogo di nascita dell'utente.
     * @param tesserino è il tesserino dell'utente.
     * @param listaIndisponibilita è la lista di indisponibilità dell'utente.
     */
    public Donatore(String codiceFiscale, String nome, String cognome, String email, String password, String residenza, Date dataDiNascita, String luogoDiNascita, Tesserino tesserino, List<Indisponibilita> listaIndisponibilita) {
        super(codiceFiscale, nome, cognome, email, password);
        this.residenza = residenza;
        this.dataDiNascita = dataDiNascita;
        this.luogoDiNascita = luogoDiNascita;
        this.tesserino = tesserino;
        this.listaIndisponibilita = new ArrayList<Indisponibilita>();
    }

    /**
     * Metodo che ritorna la residenza dell'utente.
     * @return residenza e' la residenza dell'utente.
     */
    public String getResidenza() {
        return residenza;
    }

    /**
     * Metodo che setta la residenza dell'utente.
     * @param residenza e' la residenza dell'utente.
     */
    public void setResidenza(String residenza) {
        this.residenza = residenza;
    }

    /**
     * Metodo che ritorna la data di nascita dell'utente.
     * @return dataDiNascita e' la data di nascita dell'utente.
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * Metodo che setta la data di nascita dell'utente.
     * @param dataDiNascita e' la data di nascita dell'utente.
     */
    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * Metodo che ritorna il luogo di nascita dell'utente.
     * @return luogoDiNascita e' il luogo di nascita dell'utente.
     */
    public String getLuogoDiNascita() {
        return luogoDiNascita;
    }

    /**
     * Metodo che setta il luogo di nascita dell'utente.
     * @param luogoDiNascita e' il luogo di nascita dell'utente.
     */
    public void setLuogoDiNascita(String luogoDiNascita) {
        this.luogoDiNascita = luogoDiNascita;
    }

    /**
     * Metodo che ritorna il tesserino dell'utente.
     * @return tesserino e' il tesserino dell'utente.
     */
    public Tesserino getTesserino() {
        return tesserino;
    }

    /**
     * Metodo che setta il tesserino dell'utente.
     * @param tesserino è il tesserino dell'utente.
     */
    public void setTesserino(Tesserino tesserino) {
        this.tesserino = tesserino;
    }

    /**
     * Metodo che ritorna la lista di indisponibilità dell'utente.
     * @return listaIndisponibilita e' la lista di indisponibilità dell'utente.
     */
    public List<Indisponibilita> getListaIndisponibilita() {
        return listaIndisponibilita;
    }

    /**
     * Metodo che setta la password dell'utente.
     * @param listaIndisponibilita è la password dell'utente.
     */
    public void setListaIndisponibilita(List<Indisponibilita> listaIndisponibilita) {
        this.listaIndisponibilita = listaIndisponibilita;
    }

    /** Espressione regolare che definisce il formato del campo residenza. */
    public static final String RESIDENZA_REGEX = "[A-Za-z,]+(['\\/.-]{0,1}[ ]{0,1}[A-Za-zà-ù,]+)*[a-zà-ù,]+([ ]{1}([ ]{0,1}[XIV]{1})+){0,1}([,]{0,1}[ ]{1}[0-9]{0,5}([\\/]([A-Za-z]|[0-9]{0,5})){0,1}){0,1}[,]{0,1}([ ]{1}[A-Za-zà-ù' çÇæÆñÑü,]{2,35}[ ]{1}[A-Z]{1,2})";

    /** Espressione regolare che definisce il formato del campo di data di nascita. */
    public static final String DATANASCITA_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][12]{1}\\d{3}$";

    /** Espressione regolare che definisce il formato del campo luogo di nascita. */
    public static final String LUOGONASCITA_REGEX = "^[a-zA-Zàòùèéìçê' -]{2,35}+$";

}
