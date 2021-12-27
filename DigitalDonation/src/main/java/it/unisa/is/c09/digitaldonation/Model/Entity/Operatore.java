package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella un operatore.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Operatore extends Utente{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sedeLocale")
    private Long sedeLocaleCodiceRif;

    /**
     * Costruttore che crea un oggetto Operatore vuoto,
     * che verra' popolato con i metodi setters.
     */
    public Operatore() {
    }

    /**
     * Costruttore di un operatore con parametri utili nei casi di test.
     * @param codiceFiscale è il codice fiscale dell'operatore.
     * @param nome è il nome dell'operatore.
     * @param cognome è il cognome dell'operatore.
     * @param email è l'email dell'operatore.
     * @param password è la password dell'operatore.
     * @param sedeLocaleCodiceRif è il codice di riferimento della sede locale.
     */
    public Operatore(String codiceFiscale, String nome, String cognome, String email, String password, Long sedeLocaleCodiceRif) {
        super(codiceFiscale, nome, cognome, email, password);
        this.sedeLocaleCodiceRif = sedeLocaleCodiceRif;
    }

    /**
     * Metodo che ritorna il codice di riferimento della sede locale.
     * @return sedeLocaleCodiceRif e' il codice di riferimento della sede locale.
     */
    public Long getSedeLocaleCodiceRif() { return sedeLocaleCodiceRif; }

    /**
     * Metodo che setta il codice di riferimento della sede locale.
     * @param sedeLocaleCodiceRif e' il codice di riferimento della sede locale.
     */
    public void setSedeLocaleCodiceRif(Long sedeLocaleCodiceRif) {
        this.sedeLocaleCodiceRif = sedeLocaleCodiceRif;
    }
}
