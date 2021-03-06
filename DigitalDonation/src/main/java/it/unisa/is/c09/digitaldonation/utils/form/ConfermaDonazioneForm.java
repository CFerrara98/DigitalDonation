package it.unisa.is.c09.digitaldonation.utils.form;

/**
 * Classe che rappresenta l'oggetto Form di conferma donazione.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public class ConfermaDonazioneForm {

  /**
   * Costruttore con parametri utili nei casi di test.
   *
   * @param tipoDonazione e' il tipo di donazione inserita nel form.
   */
  public ConfermaDonazioneForm(String tipoDonazione) {
    this.tipoDonazione = tipoDonazione;
  }

  /**
   * Costruttore che crea un oggetto vuoto,
   * che verra' popolato con i metodi setters.
   */
  public ConfermaDonazioneForm() {
  }

  /**
   * Metodo che ritorna il tipo di donazione per la conferma della donazione.
   *
   * @return tipoDonazione è il tipo di donazione per la conferma della donazione.
   */
  public String getTipoDonazione() {
    return tipoDonazione;
  }

  /**
   * Metodo che setta il tipo di donazione per la conferma della donazione.
   *
   * @param tipoDonazione è il tipo di donazione per la conferma della donazione.
   */
  public void setTipoDonazione(String tipoDonazione) {
    this.tipoDonazione = tipoDonazione;
  }

  private String tipoDonazione;
}
