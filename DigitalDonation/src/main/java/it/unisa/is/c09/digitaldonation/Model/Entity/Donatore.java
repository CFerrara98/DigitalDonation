package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe astratta che modella un donatore.
 */
@Entity
public class Donatore extends Utente {

    private String residenza;
    private Date dataDiNascita;
    private String luogoDiNascita;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tesserino")
    private Tesserino tesserino;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "indisponibilita")
    private List<Indisponibilita> listaIndisponibilita;

    @ManyToMany
    @JoinTable(name = "donatore_donatore",
            joinColumns = @JoinColumn(name = "donatore_codice_fiscale", referencedColumnName = "donatore_id_seduta"))
    private List<Operatore> donatore = new ArrayList<>();

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
     * @param residenza e' la residenza fiscale dell'utente.
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
     * @param password è la password dell'utente.
     */
    public void setListaIndisponibilita(List<Indisponibilita> listaIndisponibilita) {
        this.listaIndisponibilita = listaIndisponibilita;
    }

    /** Espressione regolare che definisce il formato del campo codice fiscale. */
    public static final String CF_REGEX = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    /** Espressione regolare che definisce il formato dell'email. */
    public static final String EMAIL_REGEX = "/([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)/gi";

    /** Espressione regolare che definisce il formato del campo password. */
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#$%'-\\/=^\\_`~+&])(?=.*[^0-9a-zA-Z]).{8,16}$";

    /** Espressione regolare che definisce il formato dei campi nome e cognome. */
    public static final String NOME_COGNOME_REGEX = "^[a-zA-Zàòùèéìê' -]{3,20}+$";

    public List<Operatore> getDonatore() {
        return donatore;
    }

    public void setDonatore(List<Operatore> donatore) {
        this.donatore = donatore;
    }
}
