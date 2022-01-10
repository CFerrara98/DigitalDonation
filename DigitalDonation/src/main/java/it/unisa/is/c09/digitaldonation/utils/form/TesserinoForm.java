package it.unisa.is.c09.digitaldonation.utils.form;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

/**
 * Classe che rappresenta l'oggetto Form del tesserino.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public class TesserinoForm {

  /**
   * Costruttore con parametri utili nei casi di test.
   *
   * @param nome             e' il nome inserito nel form.
   * @param cognome          e' il nome inserito nel form.
   * @param codiceFiscale    e' il codice fiscale inserito nel form.
   * @param image            e' l'immagine inserita nel form.
   * @param dataNascita      e' la data di nascita inserita nel form.
   * @param luogoNascita     e' il luogo di nascita inserito nel form.
   * @param residenza        e' la residenza inserita nel form.
   * @param email            e' la mail inserita nel form.
   * @param gruppoSanguigno  e' il gruppo sanguigno inserito nel form.
   * @param rh               e' l'rh inserito nel form.
   * @param altreIndicazioni sono le indicazioni inserite nel form.
   * @param numeroMatricola  e' il numero di matricola inserito nel form.
   * @param numeroTessera    e' il numero di tessera inserito nel form.
   * @param dataRilascio     e' la data di rilascio inserita nel form.
   * @param dataDonazione    e' la data della donazione inserita nel form.
   * @param tipoDonazione    e' il tipo di donazione inserito nel form.
   */
  public TesserinoForm(String nome, String cognome, String codiceFiscale, MultipartFile image,
                       Date dataNascita, String luogoNascita, String residenza, String email,
                       String gruppoSanguigno, String rh, String altreIndicazioni,
                       int numeroMatricola, int numeroTessera, Date dataRilascio,
                       Date dataDonazione, String tipoDonazione) {
    this.nome = nome;
    this.cognome = cognome;
    this.codiceFiscale = codiceFiscale;
    this.image = image;
    this.dataNascita = dataNascita;
    this.luogoNascita = luogoNascita;
    this.residenza = residenza;
    this.email = email;
    this.gruppoSanguigno = gruppoSanguigno;
    this.rh = rh;
    this.altreIndicazioni = altreIndicazioni;
    this.numeroMatricola = numeroMatricola;
    this.numeroTessera = numeroTessera;
    this.dataRilascio = dataRilascio;
    this.dataDonazione = dataDonazione;
    this.tipoDonazione = tipoDonazione;
  }

  /**
   * Costruttore che crea un oggetto vuoto,
   * che verra' popolato con i metodi setters.
   */
  public TesserinoForm() {
  }

  /**
   * Metodo che ritorna il nome del tesserino.
   *
   * @return nome è il nome che compare sul tesserino.
   */
  public String getNome() {
    return nome;
  }

  /**
   * Metodo che setta il nome del tesserino.
   *
   * @param nome è il nome che compare sul tesserino.
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * Metodo che ritorna il cognome del tesserino.
   *
   * @return cognome è il cognome che compare sul tesserino.
   */
  public String getCognome() {
    return cognome;
  }

  /**
   * Metodo che setta il cognome del tesserino.
   *
   * @param cognome è il cognome che compare sul tesserino.
   */
  public void setCognome(String cognome) {
    this.cognome = cognome;
  }

  /**
   * Metodo che ritorna il codiceFiscale del tesserino.
   *
   * @return codiceFiscale è il codiceFiscale che compare sul tesserino.
   */
  public String getCodiceFiscale() {
    return codiceFiscale;
  }

  /**
   * Metodo che setta il codiceFiscale del tesserino.
   *
   * @param codiceFiscale è il codiceFiscale che compare sul tesserino.
   */
  public void setCodiceFiscale(String codiceFiscale) {
    this.codiceFiscale = codiceFiscale;
  }

  /**
   * Metodo che ritorna l'immagine del tesserino.
   *
   * @return image è l'immagine che compare sul tesserino.
   */
  public MultipartFile getImage() {
    return image;
  }

  /**
   * Metodo che setta l'immagine del tesserino.
   *
   * @param image è il image che compare sul tesserino.
   */
  public void setImage(MultipartFile image) {
    this.image = image;
  }

  /**
   * Metodo che ritorna la data di nascita del tesserino.
   *
   * @return dataNascita è la data di nascita che compare sul tesserino.
   */
  public Date getDataNascita() {
    return dataNascita;
  }

  /**
   * Metodo che setta la data di nascita del tesserino.
   *
   * @param dataNascita è la data di nascita del tesserino.
   */
  public void setDataNascita(Date dataNascita) {
    this.dataNascita = dataNascita;
  }

  /**
   * Metodo che ritorna il luogo di nascita del tesserino.
   *
   * @return luogoNascita è il luogo di nascita che compare sul tesserino.
   */
  public String getLuogoNascita() {
    return luogoNascita;
  }

  /**
   * Metodo che setta il luogo di nascita del tesserino.
   *
   * @param luogoNascita è il luogo di nascita del tesserino.
   */
  public void setLuogoNascita(String luogoNascita) {
    this.luogoNascita = luogoNascita;
  }

  /**
   * Metodo che ritorna la residenza del tesserino.
   *
   * @return residenza è la residenza che compare sul tesserino.
   */
  public String getResidenza() {
    return residenza;
  }

  /**
   * Metodo che setta la residenza del tesserino.
   *
   * @param residenza è la residenza del tesserino.
   */
  public void setResidenza(String residenza) {
    this.residenza = residenza;
  }

  /**
   * Metodo che ritorna la mail del tesserino.
   *
   * @return residenza è la mail che compare sul tesserino.
   */
  public String getEmail() {
    return email;
  }

  /**
   * Metodo che setta la mail del tesserino.
   *
   * @param email è la email del tesserino.
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * Metodo che ritorna il gruppo sanguigno del tesserino.
   *
   * @return gruppoSanguigno è il gruppo sanguigno che compare sul tesserino.
   */
  public String getGruppoSanguigno() {
    return gruppoSanguigno;
  }

  /**
   * Metodo che setta il gruppo sanguigno del tesserino.
   *
   * @param gruppoSanguigno è il gruppo sanguigno del tesserino.
   */
  public void setGruppoSanguigno(String gruppoSanguigno) {
    this.gruppoSanguigno = gruppoSanguigno;
  }

  /**
   * Metodo che ritorna l'rh del tesserino.
   *
   * @return rh è l'rh che compare sul tesserino.
   */
  public String getRh() {
    return rh;
  }

  /**
   * Metodo che setta l'rh del tesserino.
   *
   * @param rh è l'rh del tesserino.
   */
  public void setRh(String rh) {
    this.rh = rh;
  }

  /**
   * Metodo che ritorna altre indicazioni del tesserino.
   *
   * @return altreIndicazioni sono altre possibili indicazioni che compaiono sul tesserino.
   */
  public String getAltreIndicazioni() {
    return altreIndicazioni;
  }

  /**
   * Metodo che setta altre indicazioni del tesserino.
   *
   * @param altreIndicazioni sono altre indicazioni del tesserino.
   */
  public void setAltreIndicazioni(String altreIndicazioni) {
    this.altreIndicazioni = altreIndicazioni;
  }

  /**
   * Metodo che ritorna il numero di matricola del tesserino.
   *
   * @return numeroMatricola è il numero di matricola che compare sul tesserino.
   */
  public int getNumeroMatricola() {
    return numeroMatricola;
  }

  /**
   * Metodo che setta il numero di matricola del tesserino.
   *
   * @param numeroMatricola è il numero di matricola del tesserino.
   */
  public void setNumeroMatricola(int numeroMatricola) {
    this.numeroMatricola = numeroMatricola;
  }

  /**
   * Metodo che ritorna il numero di tessera del tesserino.
   *
   * @return numeroTessera è il numero di tessera che compare sul tesserino.
   */
  public int getNumeroTessera() {
    return numeroTessera;
  }

  /**
   * Metodo che setta il numero di tessera del tesserino.
   *
   * @param numeroTessera è il numero di tessera del tesserino.
   */
  public void setNumeroTessera(int numeroTessera) {
    this.numeroTessera = numeroTessera;
  }

  /**
   * Metodo che ritorna la data di rilascio del tesserino.
   *
   * @return dataRilascio la data di rilascio del tesserino.
   */
  public Date getDataRilascio() {
    return dataRilascio;
  }

  /**
   * Metodo che setta la data di rilascio del tesserino.
   *
   * @param dataRilascio è la data di rilascio del tesserino.
   */
  public void setDataRilascio(Date dataRilascio) {
    this.dataRilascio = dataRilascio;
  }

  /**
   * Metodo che ritorna la data di una donazione.
   *
   * @return dataDonazione la data di una donazione
   */
  public Date getDataDonazione() {
    return dataDonazione;
  }

  /**
   * Metodo che setta la data di una donazione.
   *
   * @param dataDonazione la data di una donazione.
   */
  public void setDataDonazione(Date dataDonazione) {
    this.dataDonazione = dataDonazione;
  }

  /**
   * Metodo che ritorna il tipo di donazione.
   *
   * @return tipoDonazione il tipo di donazione.
   */
  public String getTipoDonazione() {
    return tipoDonazione;
  }

  /**
   * Metodo che setta la data di una donazione.
   *
   * @param tipoDonazione il tipo di donazione.
   */
  public void setTipoDonazione(String tipoDonazione) {
    this.tipoDonazione = tipoDonazione;
  }

  private String nome;
  private String cognome;
  private String codiceFiscale;

  private MultipartFile image;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date dataNascita;
  private String luogoNascita;
  private String residenza;
  private String email;
  private String gruppoSanguigno;
  private String rh;
  private String altreIndicazioni;
  private int numeroMatricola;
  private int numeroTessera;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date dataRilascio;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private Date dataDonazione;
  private String tipoDonazione;

}
