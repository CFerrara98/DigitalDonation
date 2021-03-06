package it.unisa.is.c09.digitaldonation.model.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * Classe che modella una sede locale della piattaforma.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 */
@Data
@Entity
@Table(name = "sede_locale")
public class SedeLocale implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @Column(name = "id_sede", nullable = false)
  private Long codiceIdentificativo;
  @Column(name = "via")
  private String via;

  /**
   * Costruttore che crea un oggetto SedeLocale vuoto,
   * che verrà popolato con i metodi setters.
   */
  public SedeLocale() {

  }

  /**
   * Costruttore di SedeLocale con parametri utili nei casi di test.
   *
   * @param codiceIdentificativo è il codice identificativo della sede locale
   * @param via                  è la via in cui è ubicata la sede locale.
   */
  public SedeLocale(Long codiceIdentificativo, String via) {
    this.codiceIdentificativo = codiceIdentificativo;
    this.via = via;
  }

  /**
   * Metodo che ritorna il codice identificativo della sede locale.
   *
   * @return codiceIdentificativo della sede locale.
   */
  public Long getCodiceIdentificativo() {
    return codiceIdentificativo;
  }

  /**
   * Metodo che setta il codice identificativo della sede locale.
   *
   * @param codiceIdentificativo e' il codice identificativo della sede locale.
   */
  public void setCodiceIdentificativo(Long codiceIdentificativo) {
    this.codiceIdentificativo = codiceIdentificativo;
  }

  /**
   * Metodo che ritorna la via della sede locale.
   *
   * @return via della sede locale.
   */
  public String getVia() {
    return via;
  }

  /**
   * Metodo che setta la via della sede locale.
   *
   * @param via e' la via della sede locale.
   */
  public void setVia(String via) {
    this.via = via;
  }
}
