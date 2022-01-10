package it.unisa.is.c09.digitaldonation.model.entity;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Classe che modella un operatore.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 */
@Entity
@Table(name = "operatore")
public class Operatore extends Utente implements Serializable {
  private static final long serialVersionUID = 1L;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "sede_locale_codice_identificativo",
          referencedColumnName = "id_sede", nullable = true)
  private SedeLocale sedeLocale;

  /**
   * Costruttore che crea un oggetto Operatore vuoto,
   * che verra' popolato con i metodi setters.
   */
  public Operatore() {
  }

  /**
   * Costruttore di un operatore con parametri utili nei casi di test.
   *
   * @param codiceFiscale è il codice fiscale dell'operatore.
   * @param nome          è il nome dell'operatore.
   * @param cognome       è il cognome dell'operatore.
   * @param email         è l'email dell'operatore.
   * @param password      è la password dell'operatore.
   * @param sedeLocale    è l'oggetto della sede locale.
   */
  public Operatore(String codiceFiscale, String nome, String cognome,
                   String email, String password, SedeLocale sedeLocale) {
    super(codiceFiscale, nome, cognome, email, password);
    this.sedeLocale = sedeLocale;
  }

  /**
   * Metodo che ritorna l'oggetto SedeLocale.
   *
   * @return sedeLocale e' l'oggetto SedeLocale.
   */
  public SedeLocale getSedeLocale() {
    return sedeLocale;
  }

  /**
   * Metodo che setta l'oggetto SedeLocale.
   *
   * @param sedeLocale e' l'oggetto SedeLocale.
   */
  public void setSedeLocale(SedeLocale sedeLocale) {
    this.sedeLocale = sedeLocale;
  }
}
