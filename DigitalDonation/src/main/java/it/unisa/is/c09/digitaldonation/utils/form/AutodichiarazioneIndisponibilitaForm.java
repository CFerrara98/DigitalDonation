package it.unisa.is.c09.digitaldonation.utils.form;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Classe che rappresenta l'oggetto Form di autodichiarazione indisponibilità.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public class AutodichiarazioneIndisponibilitaForm {

  /**
   * Costruttore con parametri utili nei casi di test.
   *
   * @param dataProssimaDisponibilita e' la data della prossima disponibilità inserita nel form.
   * @param motivazioni               sone le motivazioni inserite nel form.
   */
  public AutodichiarazioneIndisponibilitaForm(Date dataProssimaDisponibilita, String motivazioni) {
    this.dataProssimaDisponibilita = dataProssimaDisponibilita;
    this.motivazioni = motivazioni;
  }

  /**
   * Costruttore che crea un oggetto vuoto,
   * che verra' popolato con i metodi setters.
   */
  public AutodichiarazioneIndisponibilitaForm() {
  }

  /**
   * Metodo che ritorna la data di disponbilità dell'autodichiarazione di indisponibilità.
   *
   * @return dataProssimaDisponibilita e' la data di disponbilità
   * dell'autodichiarazione di indisponibilità.
   */
  public Date getDataProssimaDisponibilita() {
    return dataProssimaDisponibilita;
  }

  /**
   * Metodo che setta la data di disponbilità dell'autodichiarazione di indisponibilità.
   *
   * @param dataProssimaDisponibilita e' la data di disponbilità
   *                                  dell'autodichiarazione di indisponibilità.
   */
  public void setDataProssimaDisponibilita(Date dataProssimaDisponibilita) {
    this.dataProssimaDisponibilita = dataProssimaDisponibilita;
  }

  /**
   * Metodo che ritorna le motivazioni dell'autodichiarazione di indisponibilità.
   *
   * @return motivazioni sono le motivazioni dell'autodichiarazione di indisponibilità.
   */
  public String getMotivazioni() {
    return motivazioni;
  }

  /**
   * Metodo che setta le motivazioni dell'autodichiarazione di indisponibilità.
   *
   * @param motivazioni sono le motivazioni dell'autodichiarazione di indisponibilità.
   */
  public void setMotivazioni(String motivazioni) {
    this.motivazioni = motivazioni;
  }

  /**
   * Metodo che ritorna il nome del medico dell'autodichiarazione di indisponibilità.
   *
   * @return nomeMedico è il nome del medico dell'autodichiarazione di indisponibilità.
   */

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date dataProssimaDisponibilita;
  private String motivazioni;
}
