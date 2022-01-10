package it.unisa.is.c09.digitaldonation.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 * Classe che modella una seduta di donazione.
 */
@Entity
@Table(name = "seduta")
public class Seduta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_seduta", nullable = false)
    private Long idSeduta;
    @Column(name = "data_fine_prenotazione")
    private Date dataFinePrenotazione;
    @Column(name = "data_inizio_prenotazione")
    private Date dataInizioPrenotazione;
    @Column(name = "data_seduta")
    private Date dataSeduta;
    @Column(name = "luogo")
    private String luogo;
    @Column(name = "numero_partecipanti", nullable = false)
    private Integer numeroPartecipanti;
    @Column(name = "ora_fine")
    private Time oraFine;
    @Column(name = "ora_inizio")
    private Time oraInizio;
    @Column(name = "id_sedeLocale")
    private Long sedeLocaleCodiceIdentificativo;

    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "seduta_guest",
            joinColumns = @JoinColumn(name = "id_seduta"),
            inverseJoinColumns = @JoinColumn(name = "codice_fiscale_guest"))
    private List<Guest> listaGuest = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(name = "seduta_donatore",
            joinColumns = @JoinColumn(name = "id_seduta"),
            inverseJoinColumns = @JoinColumn(name = "codice_fiscale_donatore"))
    private List<Donatore> listaDonatore = new ArrayList<>();


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
        idSeduta = -1L;
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
     * @param sedeLocaleCodiceIdentificativo sede che organizza la seduta di donazione
     * @param listaGuest lista dei guet
     * @param listaDonatore lista dei donatori
     */
    public Seduta(Long idSeduta, Date date, String luogo,
                     Time oraInizio, Time oraFine, int numeroPartecipanti,
                     Date dataInizioPrenotazione, Date dataFinePrenotazione,
                     Long sedeLocaleCodiceIdentificativo, List<Guest> listaGuest,
                     List<Donatore> listaDonatore) {

        this.idSeduta = idSeduta;
        this.dataSeduta = date;
        this.luogo = luogo;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.numeroPartecipanti = numeroPartecipanti;
        this.dataInizioPrenotazione = dataInizioPrenotazione;
        this.dataFinePrenotazione = dataFinePrenotazione;
        this.sedeLocaleCodiceIdentificativo = sedeLocaleCodiceIdentificativo;
        this.listaGuest = listaGuest;
        this.listaDonatore = listaDonatore;
    }

    /**
     * Metodo che trasforma i parametri in un unico luogo.
     * @param indirizzo è l'indirizzo della seduta.
     * @param citta è la citta della seduta.
     * @param CAP è il CAP della seduta.
     * @param provincia è la provincia della seduta.
     */
    public static String parseToLuogo(String indirizzo, String citta, String CAP, String provincia){
        return indirizzo+" "+citta+" "+CAP+" "+provincia;
    }

    /**
     * Metodo che trasforma il luogo in più parametri: indirizzo, citta, CAP, provincia.
     * @param luogo è il luogo della seduta.
     * @return String[] indirizzo, citta, CAP, provincia.
     */
    public static String[] parseFromLuogo(String luogo){
        return luogo.split("&");
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
    public Date getDataSeduta() {
        return dataSeduta;
    }

    /**
     * Metodo che setta il codice identificativo della seduta.
     * @param data data di quando la seduta di donazione si svolgerà.
     */
    public void setDataSeduta(Date data) {
        this.dataSeduta = data;
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
     * @return sedeLocaleCodiceIdentificativo la sede locale che organizza la seduta.
     */
    public Long getSedeLocale() {
        return sedeLocaleCodiceIdentificativo;
    }

    /**
     * Metodo che setta la sede locale che organizza la seduta.
     * @param  sedeLocaleCodiceIdentificativo la sede locale che organizza la seduta.
     */
    public void setSedeLocale(Long sedeLocaleCodiceIdentificativo) {
        this.sedeLocaleCodiceIdentificativo = sedeLocaleCodiceIdentificativo;
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

    /**
     * Regex dei campi della seduta.
     */
    public static final String DATA_SEDUTA_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][2]{1}\\d{3}$";
    public static final String INDIRIZZO_REGEX = "[A-Za-z]+(['\\/.-]{0,1}[ ]{0,1}[A-Za-zà-ù]+)*[a-zà-ù]+([ ]{1}([ ]{0,1}[XIV]{1})+){0,1}([,]{0,1}[ ]{1}[0-9]{0,5}([\\/]([A-Za-z]|[0-9]{0,5})){0,1}){0,1}";
    public static final String CITTA_REGEX = "^[a-zA-Zàòùèéìçê' -]{2,35}+$";
    public static final String CAP_REGEX = "^[0-9]{5}";
    public static final String PROVINCIA_REGEX = "^[A-Z]{2,2}+$";
    public static final String DATA_INIZIO_PARTECIPAZIONE_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][2]{1}\\d{3}$";
    public static final String DATA_FINE_PARTECIPAZIONE_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][2]{1}\\d{3}$";
    public static final String NUMERO_PARTECIPANTI_REGEX = "^(?:[0-9][0-9]{3}|[0-9][0-9]{2}|[1-9][1-9]|[0-9])$";
}
