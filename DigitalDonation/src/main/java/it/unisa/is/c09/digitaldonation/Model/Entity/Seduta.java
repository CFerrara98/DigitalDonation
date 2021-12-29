package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella una seduta di donazione.
 */

@Entity
public class Seduta {
    @Id
    @GeneratedValue
    private Long idSeduta;
    private Date date;
    private String luogo;
    private Time oraInizio;
    private Time oraFine;
    private int numeroPartecipanti;
    private Date dataInizioPrenotazione;
    private Date dataFinePrenotazione;
    @ManyToOne
    @JoinColumn(name = "sede_locale_codice_identificativo")
    private SedeLocale sedeLocale;
    @ManyToMany
    @JoinTable(
            name = "Seduta_Guest",
            joinColumns = @JoinColumn(name = "codiceFiscaleGuest"),
            inverseJoinColumns = @JoinColumn(name = "idSeduta")
    )
    private List<Guest> listaGuest;
    @ManyToMany @JoinTable(
            name = "Seduta_Donatore",
            joinColumns = @JoinColumn(name = "codiceFiscaleUtente"),
            inverseJoinColumns = @JoinColumn(name = "idSeduta")
    )
    private List<Donatore> listaDonatore;

    /**
     * Aggiunge il guest alla seduta di donazione.
     * @param guest il donatore non registrato alla piattaforma.
     */
    public void addPartecipante(Guest guest){
        if(guest!= null){
            numeroPartecipanti++;
            listaGuest.add(guest);
        }
    }
    /**
     * Aggiunge il donatore alla seduta di donazione.
     * @param donatore il donatore alla seduta di donazione.
     */
    public void addPartecipante(Donatore donatore){
        if(donatore!= null){
            numeroPartecipanti++;
            listaDonatore.add(donatore);
        }
    }

    /**
     * Costruttore che crea un ogetto Seduta vuoto,
     * che verrà popolato con i metodi setters.
     */
    public Seduta(){
        listaDonatore = new ArrayList<Donatore>();
        listaGuest = new ArrayList<Guest>();
        numeroPartecipanti = 0;
    }

    /**
     * Costruttore di Seduta con parametri utili nei casi di test.
     * @param idSeduta id univoco di una seduta
     * @param date data di quando la seduta di donazione si svolgerà
     * @param luogo luogo in cui verrà svolta la seduta di donazione
     * @param oraInizio ora dell'inizio della seduta
     * @param oraFine ora della fine della seduta
     * @param numeroPartecipanti numero di partecipanti alla seduta
     * @param dataInizioPrenotazione data di inizio per le prenotazioni alla seduta
     * @param dataFinePrenotazione data di fine delle prenotazioni alla seduta
     * @param sedeLocale sede che organizza la seduta di donazione
     * @param listaGuest lista dei guet
     * @param listaDonatore lista dei donatori
     */
    public Seduta(Long idSeduta, Date date, String luogo,
                     Time oraInizio, Time oraFine, int numeroPartecipanti,
                     Date dataInizioPrenotazione, Date dataFinePrenotazione,
                     SedeLocale sedeLocale, List<Guest> listaGuest,
                     List<Donatore> listaDonatore) {

        this.idSeduta = idSeduta;
        this.date = date;
        this.luogo = luogo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.numeroPartecipanti = numeroPartecipanti;
        this.dataInizioPrenotazione = dataInizioPrenotazione;
        this.dataFinePrenotazione = dataFinePrenotazione;
        this.sedeLocale = sedeLocale;
        this.listaGuest = listaGuest;
        this.listaDonatore = listaDonatore;
    }

    /**
     * Metodo che ritorna il codice identificativo della seduta.
     * @return codiceIdentificativo della seduta.
     */
    public Long getIdSeduta() {
        return idSeduta;
    }

    /**
     * Metodo che setta il codice identificativo della seduta.
     * @param idSeduta e' il codice identificativo della seduta.
     */
    public void setIdSeduta(Long idSeduta) {
        this.idSeduta = idSeduta;
    }

    /**
     * Metodo che ritorna la data della seduta di donazione.
     * @return date data di quando la seduta di donazione si svolgerà.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Metodo che setta il codice identificativo della seduta.
     * @param date data di quando la seduta di donazione si svolgerà.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Metodo che ritorna il luogo di svolgimento della seduta.
     * @return luogo e' il luogo in cui verrà svolta la seduta di donazione.
     */
    public String getLuogo() {
        return luogo;
    }

    /**
     * Metodo che setta il luogo della seduta di donazione.
     * @param luogo luogo in cui verrà svolta la seduta di donazione.
     */
    public void setLuogo(String luogo) {
        this.luogo = luogo;
    }

    /**
     * Metodo che ritorna la l'ora di inzio della seduta di donazione.
     * @return  oraInizio la l'ora di inzio della seduta di donazione.
     */
    public Time getOraInizio() {
        return oraInizio;
    }

    /**
     * Metodo che setta l'ora di inzio della seduta di donazione.
     * @param oraInizio la l'ora di inzio della seduta di donazione.
     */
    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    /**
     * Metodo che ritorna la l'ora di fine della seduta di donazione.
     * @return  oraFine  l'ora di fine della seduta di donazione.
     */
    public Time getOraFine() {
        return oraFine;
    }

    /**
     * Metodo che setta l'ora di fine della seduta di donazione.
     * @param oraFine la l'ora di fine della seduta di donazione.
     */
    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    /**
     * Metodo che ritorna il numero il numero di partecipanti alla seduta.
     * @return codiceIdentificativo della sede locale.
     */
    public int getNumeroPartecipanti() {
        return numeroPartecipanti;
    }
    /**
     * Metodo che ritorna la via della sede locale.
     * @return via della sede locale.
     */
    public void setNumeroPartecipanti(int numeroPartecipanti) {
        this.numeroPartecipanti = numeroPartecipanti;
    }

    /**
     * Metodo che ritorna la data d'inizio alle prenotazioni.
     * @return dataInizioPrenotazione la data di inizio alle prenotazioni.
     */
    public Date getDataInizioPrenotazione() {
        return dataInizioPrenotazione;
    }

    /**
     * Metodo che setta la data d'inizio alle prenotazioni.
     * @param  dataInizioPrenotazione la data di inizio alle prenotazioni.
     */
    public void setDataInizioPrenotazione(Date dataInizioPrenotazione) {
        this.dataInizioPrenotazione = dataInizioPrenotazione;
    }

    /**
     * Metodo che ritorna la data di fin alle prenotazioni.
     * @return dataFInePrenotazione la data di inizio alle prenotazioni.
     */
    public Date getDataFinePrenotazione() {
        return dataFinePrenotazione;
    }

    /**
     * Metodo che setta la data di fine alle prenotazioni.
     * @param  dataFinePrenotazione la data di fine alle prenotazioni.
     */
    public void setDataFinePrenotazione(Date dataFinePrenotazione) {
        this.dataFinePrenotazione = dataFinePrenotazione;
    }

    /**
     * Metodo che ritorna la sede locale che organizza la seduta.
     * @return sedeLocale la sede locale che organizza la seduta.
     */
    public SedeLocale getSedeLocale() {
        return sedeLocale;
    }

    /**
     * Metodo che setta la sede locale che organizza la seduta.
     * @param  sedeLocale la sede locale che organizza la seduta.
     */
    public void setSedeLocale(SedeLocale sedeLocale) {
        this.sedeLocale = sedeLocale;
    }

    /**
     * Metodo che ritorna la lista di guest che partecipano alla seduta.
     * @return listaGuest la lista di guest che partecipano alla seduta.
     */
    public List<Guest> getListaGuest() {
        return listaGuest;
    }

    /**
     * Metodo che setta la lista di guest che partecipano alla seduta.
     * @param  listaGuest la lista di guest che partecipano alla seduta.
     */
    public void setListaGuest(List<Guest> listaGuest) {
        this.listaGuest = listaGuest;
    }

    /**
     * Metodo che ritorna la lista di donatori che partecipano alla seduta.
     * @return listaDonatore la lista di donatori che partecipano alla seduta.
     */
    public List<Donatore> getListaDonatore() {
        return listaDonatore;
    }

    /**
     * Metodo che setta la lista di donatori che partecipano alla seduta.
     * @param  listaDonatore la lista di donatori che partecipano alla seduta.
     */
    public void setListaDonatore(List<Donatore> listaDonatore) {
        this.listaDonatore = listaDonatore;
    }


    public static final String DATA_SEDUTA_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][2]{1}\\d{3}$";
    public static final String INDIRIZZO_REGEX = "[A-Za-z]+(['\\/.-]{0,1}[ ]{0,1}[A-Za-zà-ù]+)*[a-zà-ù]+([ ]{1}([ ]{0,1}[XIV]{1})+){0,1}([,]{0,1}[ ]{1}[0-9]{0,5}([\\/]([A-Za-z]|[0-9]{0,5})){0,1}){0,1}";
    public static final String CITTA_REGEX = "^[a-zA-Zàòùèéìçê' -]{2,35}+$";
    public static final String CAP_REGEX = "^[0-9]{5}";
    public static final String PROVINCIA_REGEX = "^[A-Z]{2,2}+$";
    public static final String DATA_INIZIO_PARTECIPAZIONE_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][2]{1}\\d{3}$";
    public static final String DATA_FINE_PARTECIPAZIONE_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][2]{1}\\d{3}$";
    public static final String NUMERO_PARTECIPANTI_REGEX = "^(?:[0-9][0-9]{3}|[0-9][0-9]{2}|[1-9][1-9]|[0-9])$";


    @Override
    public String toString() {
        return "Seduta{" +
                "idSeduta=" + idSeduta +
                ", date=" + date +
                ", luogo='" + luogo + '\'' +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", numeroPartecipanti=" + numeroPartecipanti +
                ", dataInizioPrenotazione=" + dataInizioPrenotazione +
                ", dataFinePrenotazione=" + dataFinePrenotazione +
                ", sedeLocale=" + sedeLocale +
                ", listaGuest=" + listaGuest +
                ", listaDonatore=" + listaDonatore +
                '}';
    }
}
