package it.unisa.is.c09.digitaldonation.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import lombok.Data;

/**
 * Classe astratta che modella un utente generico registrato alla piattaforma.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 */
@Data
@Entity
@Table(name = "utente")
@Inheritance(strategy = InheritanceType.JOINED)
public class Utente implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "codice_fiscale_utente")
  private String codiceFiscaleUtente;
  @Column(name = "cognome")
  private String cognome;
  @Column(name = "email", unique = true)
  private String email;
  @Column(name = "nome")
  private String nome;
  @Column(name = "password")
  private String password;

  /**
   * Costruttore che crea un oggetto Utente vuoto,
   * che verra' popolato con i metodi setters.
   */
  public Utente() {
  }

  /**
   * Costruttore di un utente con parametri utili nei casi di test.
   *
   * @param codiceFiscale è il codice fiscale dell'utente.
   * @param nome          è il nome dell'utente.
   * @param cognome       è il cognome dell'utente.
   * @param email         è l'email dell'utente.
   * @param password      è la password dell'utente.
   */
  public Utente(String codiceFiscale, String nome, String cognome, String email, String password) {
    this.codiceFiscaleUtente = codiceFiscale;
    this.nome = nome;
    this.cognome = cognome;
    this.email = email;
    this.password = password;
  }

  /**
   * Metodo che ritorna il codice fiscale dell'utente.
   *
   * @return codiceFiscale e' il codiceFiscale dell'utente.
   */
  public String getCodiceFiscale() {
    return codiceFiscaleUtente;
  }

  /**
   * Metodo che setta il codice fiscale dell'utente.
   *
   * @param codiceFiscale e' il codice fiscale dell'utente.
   */
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscaleUtente = codiceFiscale;
  }

  /**
   * Metodo che ritorna il nome dell'utente.
   *
   * @return nome e' il nome dell'utente.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Metodo che setta il nome dell'utente.
   *
   * @param nome e' il nome dell'utente.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo che ritorna il cognome dell'utente.
   *
   * @return nome e' il cognome dell'utente.
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Metodo che setta il cognome dell'utente.
   *
   * @param cognome e' il cognome dell'utente.
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Metodo che ritorna l'email dell'utente.
   *
   * @return email e' l'email dell'utente.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Metodo che setta l'email dell'utente.
   *
   * @param email è l'email dell'utente.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Metodo che ritorna la password dell'utente.
   *
   * @return password e' la password dell'utente.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Metodo che setta la password dell'utente.
   *
   * @param password è la password dell'utente.
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Espressione regolare che definisce il formato del campo codice fiscale.
   */
  public static final String CF_REGEX = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

  /**
   * Espressione regolare che definisce il formato dell'email.
   */
  public static final String EMAIL_REGEX = "^([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x"
          + "3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x"
          + "5c[\\x00-\\x7f])*\\x22)(\\x2e([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\"
          + "x40\\x5b-\\x5d\\x7f-\\xff]+|\\x22([^\\x0d\\x22\\x5c\\x80-\\xff]|\\x5c[\\x00-\\x7f]"
          + ")*\\x22))*\\x40([^\\x00-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d"
          + "\\x7f-\\xff]|\\x5b([^\\\\x5b\\x5d\\x80-\\xff]|\\x5c[\\x00-\\x7f])*\\x5d)(\\x2e([^\\x00"
          + "-\\x20\\x22\\x28\\x29\\x2c\\x2e\\x3a-\\x3c\\x3e\\x40\\x5b-\\x5d\\x7f-\\xff]+|\\x5b(["
          + "^\\x0d\\x5b-\\x5d\\x80-\\xff]|\\x5"
          + "c[\\x00-\\x7f])*\\x5d))*$";

  /**
   * Espressione regolare che definisce il formato del campo password.
   */
  public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@!#"
          + "$%'-\\/=^\\_`~+&])(?=.*[^0-9a-zA-Z]).{8,16}$";

  /**
   * Espressione regolare che definisce il formato dei campi nome e cognome.
   */
  public static final String NOME_COGNOME_REGEX = "^[a-zA-Zàòùèéìê' -]{3,20}+$";
}

