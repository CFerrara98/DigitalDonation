package it.unisa.is.c09.digitaldonation.Utils.Forms;

import java.sql.Time;
import java.util.Date;

public class SedutaForm {

    public SedutaForm(Date dataSeduta, String indirizzo, String citta, String provincia, String CAP, Time orarioInizio, Time orarioFine, int numeroPartecipanti, Date dataInizioPrenotazione, Date dataFinePrenotazione) {
        this.dataSeduta = dataSeduta;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.provincia = provincia;
        this.CAP = CAP;
        this.orarioInizio = orarioInizio;
        this.orarioFine = orarioFine;
        this.numeroPartecipanti = numeroPartecipanti;
        this.dataInizioPrenotazione = dataInizioPrenotazione;
        this.dataFinePrenotazione = dataFinePrenotazione;
    }

    /**
     * Metodo che ritorna la data della seduta.
     *
     * @return dataSeduta e' la data della seduta.
     */
    public Date getDataSeduta() {
        return dataSeduta;
    }

    /**
     * Metodo che setta la data della seduta.
     *
     * @param dataSeduta e' la data della seduta.
     */
    public void setDataSeduta(Date dataSeduta) {
        this.dataSeduta = dataSeduta;
    }

    /**
     * Metodo che ritorna l'indirizzo della seduta.
     *
     * @return indirizzo e' l'indirizzo della seduta.
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Metodo che setta l'indirizzo della seduta.
     *
     * @param indirizzo e' l'indirizzo della seduta.
     */
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    /**
     * Metodo che ritorna la città della seduta.
     *
     * @return citta e' la città della seduta.
     */
    public String getCitta() {
        return citta;
    }

    /**
     * Metodo che setta la città della seduta.
     *
     * @param citta e' la città della seduta.
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /**
     * Metodo che ritorna la provincia della seduta.
     *
     * @return provincia e' la provincia della seduta.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Metodo che setta la provincia della seduta.
     *
     * @param provincia e' la provincia della seduta.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Metodo che ritorna il CAP della seduta.
     *
     * @return CAP e' il CAP della seduta.
     */
    public String getCAP() {
        return CAP;
    }

    /**
     * Metodo che setta il CAP della seduta.
     *
     * @param CAP e' il CAP della seduta.
     */
    public void setCAP(String CAP) {
        this.CAP = CAP;
    }

    /**
     * Metodo che ritorna l'ora di inizio della seduta.
     *
     * @return orarioInizio e' l'ora di inizio della seduta.
     */
    public Time getOrarioInizio() {
        return orarioInizio;
    }

    /**
     * Metodo che setta l'ora di inizio della seduta.
     *
     * @param orarioInizio e' l'ora di inizio della seduta.
     */
    public void setOrarioInizio(Time orarioInizio) {
        this.orarioInizio = orarioInizio;
    }

    /**
     * Metodo che ritorna l'ora di fine della seduta.
     *
     * @return orarioFine e' l'ora di fine della seduta.
     */
    public Time getOrarioFine() {
        return orarioFine;
    }

    /**
     * Metodo che setta l'ora di fine della seduta.
     *
     * @param orarioFine e' l'ora di fine della seduta.
     */
    public void setOrarioFine(Time orarioFine) {
        this.orarioFine = orarioFine;
    }

    /**
     * Metodo che ritorna il numero di partecipanti della seduta.
     *
     * @return numeroPartecipanti e' il numero di partecipanti della seduta.
     */
    public int getNumeroPartecipanti() {
        return numeroPartecipanti;
    }

    /**
     * Metodo che setta il numero di partecipanti della seduta.
     *
     * @param numeroPartecipanti e' il numero di partecipanti della seduta.
     */
    public void setNumeroPartecipanti(int numeroPartecipanti) {
        this.numeroPartecipanti = numeroPartecipanti;
    }

    /**
     * Metodo che ritorna la data di inizio prenotazione della seduta.
     *
     * @return dataInizioPrenotazione e' la data di inizio prenotazione della seduta.
     */
    public Date getDataInizioPrenotazione() {
        return dataInizioPrenotazione;
    }

    /**
     * Metodo che setta la data di inizio prenotazione della seduta.
     *
     * @param dataInizioPrenotazione e' la data di inizio prenotazione della seduta.
     */
    public void setDataInizioPrenotazione(Date dataInizioPrenotazione) {
        this.dataInizioPrenotazione = dataInizioPrenotazione;
    }

    /**
     * Metodo che ritorna la data di fine prenotazione della seduta.
     *
     * @return dataFinePrenotazione e' la data di fine prenotazione della seduta.
     */
    public Date getDataFinePrenotazione() {
        return dataFinePrenotazione;
    }

    /**
     * Metodo che setta la data di fine prenotazione della seduta.
     *
     * @param dataFinePrenotazione e' la data di fine prenotazione della seduta.
     */
    public void setDataFinePrenotazione(Date dataFinePrenotazione) {
        this.dataFinePrenotazione = dataFinePrenotazione;
    }

    private Date dataSeduta;
    private String indirizzo;
    private String citta;
    private String provincia;
    private String CAP;
    private Time orarioInizio;
    private Time orarioFine;
    private int numeroPartecipanti;
    private Date dataInizioPrenotazione;
    private Date dataFinePrenotazione;
}
