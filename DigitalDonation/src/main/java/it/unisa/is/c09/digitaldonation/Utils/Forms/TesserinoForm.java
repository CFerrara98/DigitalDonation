package it.unisa.is.c09.digitaldonation.Utils.Forms;

import org.springframework.format.annotation.DateTimeFormat;

import java.awt.*;
import java.util.Date;
import java.util.List;

public class TesserinoForm {

    public TesserinoForm(String nome, String cognome, String codiceFiscale, Image image, Date dataNascita, String luogoNascita, String residenza, String email, String gruppoSanguigno, String rh, String altreIndicazioni, int numeroMatricola, int numeroTessera, Date dataRilascio, List<it.unisa.is.c09.digitaldonation.Utils.Forms.ConfermaDonazioneForm> confermaDonazioneForm) {
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
        ConfermaDonazioneForm = confermaDonazioneForm;
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
    public Image getImage() {
        return image;
    }
    /**
     * Metodo che setta l'immagine del tesserino.
     *
     * @param image è il image che compare sul tesserino.
     */
    public void setImage(Image image) {
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
     * Metodo che ritorna la lista delle donazioni confermate con successo.
     *
     * @return ConfermaDonazioneForm è la lista delle donazioni confermate con successo.
     */
    public List<it.unisa.is.c09.digitaldonation.Utils.Forms.ConfermaDonazioneForm> getConfermaDonazioneForm() {
        return ConfermaDonazioneForm;
    }
    /**
     * Metodo che setta la lista delle donazioni confermate con successo.
     *
     * @param confermaDonazioneForm è la lista delle donazioni confermate con successo.
     */
    public void setConfermaDonazioneForm(List<it.unisa.is.c09.digitaldonation.Utils.Forms.ConfermaDonazioneForm> confermaDonazioneForm) {
        ConfermaDonazioneForm = confermaDonazioneForm;
    }

    private String nome;
    private String cognome;
    private String codiceFiscale;
    private Image image;
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
    private List<it.unisa.is.c09.digitaldonation.Utils.Forms.ConfermaDonazioneForm> ConfermaDonazioneForm;

}
