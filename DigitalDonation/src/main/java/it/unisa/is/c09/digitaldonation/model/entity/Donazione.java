package it.unisa.is.c09.digitaldonation.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

/**
 * Classe che modella una donazione effettuata da un donatore.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 *
 */
@Data
@Entity
@Table(name = "donazione")
public class Donazione implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id_donazione")
  private Long idDonazione;
  @Column(name = "data_donazione")
  private Date dataDonazione;
  @Column(name = "tipo_donazione")
  private String tipoDonazione;

  @ManyToOne()
  @JoinColumn(name = "cf_tessera",
          referencedColumnName = "codice_fiscale_donatore", nullable = true)
  private Tesserino tesserino;


  /**
   * Costruttore che crea un oggetto Donazione vuoto,
   * che verra' popolato con i metodi setters.
   */
  public Donazione() {
  }

  /**
   * Costruttore di una Donazione con parametri utili nei casi di test.
   *
   * @param dataDonazione è la data della donazione effettuata.
   * @param tipoDonazione è il tipo di donazione effettuata.
   */
  public Donazione(Date dataDonazione, String tipoDonazione) {
    this.dataDonazione = dataDonazione;
    this.tipoDonazione = tipoDonazione;
  }

  /**
   * Metodo che ritorna l'id della donazione.
   *
   * @return idDonazione e' l'id della donazione.
   */
  public Long getIdDonazione() {
    return idDonazione;
  }

  /**
   * Metodo che setta l'id della donazione.
   *
   * @param idDonazione e' l'id della donazione.
   */
  public void setIdDonazione(Long idDonazione) {
    this.idDonazione = idDonazione;
  }

  /**
   * Metodo che ritorna la data della donazione.
   *
   * @return dataDonazione e' la data della donazione.
   */
  public Date getDataDonazione() {
    return dataDonazione;
  }

  /**
   * Metodo che setta la data della donazione.
   *
   * @param dataDonazione e' la data della donazione.
   */
  public void setDataDonazione(Date dataDonazione) {
    this.dataDonazione = dataDonazione;
  }

  /**
   * Metodo che ritorna il tipo di donazione.
   *
   * @return tipoDonazione e' il tipo di donazione.
   */
  public String getTipoDonazione() {
    return tipoDonazione;
  }

  /**
   * Metodo che setta il tipo della donazione.
   *
   * @param tipoDonazione e' il tipo della donazione.
   */
  public void setTipoDonazione(String tipoDonazione) {
    this.tipoDonazione = tipoDonazione;
  }

  /**
   * Espressioni regolare che definisce il formato del campo tipo donazione.
   */
  public static final String TIPODONAZIONE_REGEX = "^(plasma|cito|sangue)$";

  public Tesserino getTesserino() {
    return tesserino;
  }

  public void setTesserino(Tesserino tesserino) {
    this.tesserino = tesserino;
  }

}
