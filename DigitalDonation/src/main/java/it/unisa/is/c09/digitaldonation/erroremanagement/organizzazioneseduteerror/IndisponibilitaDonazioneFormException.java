package it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror;


/**
 * Eccezione generata in caso di parametri non validi durante l'inserimento
 * dei campi inerenti il form di indisponibilit√† per una donazione.
 *
 * @author Mattia Sapere
 */
public class IndisponibilitaDonazioneFormException extends Exception {

  private static final long serialVersionUID = 2441773366582183446L;

  /**
   * Parametro indicante il tipo di errore avvenuto.
   */
  private String target;

  /**
   * Stringa che definisce il messaggio di default utilizzato nell'eccezione.
   */
  private static final String MESSAGGIO_DEFAULT =
          "Form di indisponibilita per una donazione non valido";

  /**
   * Genera un'eccezione che riporta come messaggio il messaggio di default:
   * {@link #MESSAGGIO_DEFAULT}.
   */
  public IndisponibilitaDonazioneFormException() {
    super(MESSAGGIO_DEFAULT);
  }

  /**
   * Genera un'eccezione che riporta come messaggio un messaggio passato come
   * parametro.
   *
   * @param messaggio Stringa che rappresenta il messaggio da mostrare
   *                  nell'output dell'eccezione
   */
  public IndisponibilitaDonazioneFormException(String messaggio) {
    super(messaggio);
  }

  /**
   * Genera un'eccezione etichettata con parametro di discriminazione
   * che riporta come messaggio, un messaggio passato come parametro.
   *
   * @param target    Stringa che rappresenta il tipo di errore generato
   * @param messaggio Stringa che rappresenta il messaggio da mostrare nell'output dell'eccezione
   */
  public IndisponibilitaDonazioneFormException(String target, String messaggio) {
    super(messaggio);
    this.target = target;
  }


  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }
}
