package it.unisa.is.c09.digitaldonation.Model.Entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella un guest che partecipèa a una seduta.
 */

@Entity
public class Guest {
    @Id
    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String telefono;
    private String patologie;
    private String gruppoSanguigno;

    @ManyToMany
    @JoinTable(name = "guest_guest",
            joinColumns = @JoinColumn(name = "guest_codice_fiscale", referencedColumnName = "guest_id_seduta"))
    private List<Operatore> guest = new ArrayList<>();

    /**
     * Costruttore che crea un oggeto Guest vuoto,
     * che verra' popolato con i metodi setters.
     */
    public Guest() {
    }

    /**
     * Costruttore di un Guest con parametri utili nei casi di test.
     * @param codiceFiscale è il codice fiscale dell guest.
     * @param nome è il nome del guest.
     * @param cognome è il cognome del guest.
     * @param telefono è il numero di telefono dl guest.
     * @param patologie sono le patologie del guest.
     * @param gruppoSanguigno è il gruppo sanguigno del guest.
     */
    public Guest(String codiceFiscale, String nome, String cognome, String telefono, String patologie, String gruppoSanguigno) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.patologie = patologie;
        this.gruppoSanguigno = gruppoSanguigno;
    }


    /**
     * Metodo che ritorna il codice fiscale dell'utente.
     * @return codiceFiscale e' il codiceFiscale dell'utente.
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Metodo che setta il codice fiscale dell'utente.
     * @param codiceFiscale e' il codice fiscale dell'utente.
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Metodo che ritorna il nome dell'utente.
     * @return nome e' il nome dell'utente.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che setta il nome dell'utente.
     * @param nome e' il nome dell'utente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo che ritorna il cognome dell'utente.
     * @return nome e' il cognome dell'utente.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo che setta il cognome dell'utente.
     * @param cognome e' il cognome dell'utente.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    /**
     * Metodo che ritorna il cognome dell'utente.
     * @return telefono e' il numero di telefono dell guest.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo che setta il cognome dell guest.
     * @param telefono e' il numero di telefono dell guest.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Metodo che ritorna il cognome dell guest.
     * @return patologie sono le patologie dell' guest.
     */
    public String getPatologie() {
        return patologie;
    }

    /**
     * Metodo che setta le patologie del guest.
     * @param patologie sono le patologie del guest
     */
    public void setPatologie(String patologie) {
        this.patologie = patologie;
    }

    /**
     * Metodo che ritorna il gruppo sanguigno del guest.
     * @return gruppoSanguigno è il gruppo sanguigno del guest.
     */
    public String getGruppoSanguigno() {
        return gruppoSanguigno;
    }

    /**
     * Metodo che setta il gruppo sanguigno del guest.
     * @param gruppoSanguigno è il gruppo sanguigno del guest.
     */
    public void setGruppoSanguigno(String gruppoSanguigno) {
        this.gruppoSanguigno = gruppoSanguigno;
    }

    /** Espressione regolare che definisce il formato dei campi nome e cognome. */
    public static final String NOME_COGNOME_REGEX = "^[a-zA-Zàòùèéìê' -]{3,20}+$";

    /** Espressioni regolare che definisce il formato del numero di telefono*/
    public static final String NUMERO_TELEFONO = "^\\+?(?:[0-9] ?){8,12}[0-9]$";

    /** Espressione regolare che definisce il formato del campo codice fiscale. */
    public static final String CF_REGEX = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    /** Espressioni regolare che definisce il formato dele patologie */
    public static final String REG_PATOLOGIE = "[A-Za-z0-9 _.,!\"'\\/$\\n]{2,500}";

    /** Espressione regolare che definisce il formato del gruppo sanguigno */
    public static final String REG_GRUPPOSANGUIGNO = "^^(0-|0\\+|A-|A\\+|B-|B\\+|AB-|AB\\+)";

    public List<Operatore> getGuest() {
        return guest;
    }

    public void setGuest(List<Operatore> guest) {
        this.guest = guest;
    }

}
