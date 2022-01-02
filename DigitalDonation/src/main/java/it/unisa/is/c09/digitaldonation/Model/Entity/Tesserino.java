package it.unisa.is.c09.digitaldonation.Model.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella un tesserino.
 */

@Data
@Entity
@Table(name = "tesserino")
public class Tesserino implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "codice_fiscale_donatore", nullable = false)
    private String codiceFiscaleDonatore;

    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTessera;
    @Column(name = "data_rilascio")
    private Date dataRilascio;
    @Column(name = "gruppo_sanguigno")
    private String gruppoSanguigno;
    @Column(name = "img_source")
    private String imgSource;
    @Column(name = "numero_matricola")
    private Integer numeroMatricola;
    @Column(name = "rh")
    private String rh;

    @OneToMany(cascade = {CascadeType.ALL, CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.DETACH}, orphanRemoval = true)
    @JoinColumn(name = "cf_tessera" , referencedColumnName = "codice_fiscale_donatore")
    private List<Donazione> listaDonazioni = new ArrayList<>();

    /**
     * Costruttore che crea un oggetto Tesserino vuoto,
     * che verra' popolato con i metodi setters.
     */
    public Tesserino() {
        listaDonazioni = new ArrayList<Donazione>();
    }

    /**
     * Costruttore di un tesserino con parametri utili nei casi di test.
     * @param numeroMatricola è il numero di matricola del tesserino.
     * @param donatoreUtenteCodiceFiscale è il codice fiscale del donatore proprietario del tesserino.
     * @param dataRilascio è la data di rilascio del tesserino.
     * @param gruppoSanguigno è il gruppo sanguigno sul tesserino.
     * @param rh è l'rh sul tesserino.
     * @param listaDonazioni è la lista si donazioni sul tesserino.
     * @param imgSource è il path della immagine del donatore.
     */
    public Tesserino(int numeroMatricola, String donatoreUtenteCodiceFiscale,
                     Date dataRilascio, String gruppoSanguigno, String rh,
                     List<Donazione> listaDonazioni, String imgSource) {
        this.codiceFiscaleDonatore = donatoreUtenteCodiceFiscale;
        this.numeroMatricola = numeroMatricola;
        this.dataRilascio = dataRilascio;
        this.gruppoSanguigno = gruppoSanguigno;
        this.rh = rh;
        this.listaDonazioni = listaDonazioni;
        this.imgSource = imgSource;
    }

    /**
     * Metodo per aggiungere una donazione al tesserino
     * @param donazione la donzaione
     */
    public void addDonazione(Donazione donazione){
        listaDonazioni.add(donazione);
    }

    /**
     * Metodo che ritorna il path della immagine del donatore.
     * @return imgSource è il path della immagine del donatore.
     */
    public String getImgSource() {
        return imgSource;
    }

    /**
     * Metodo che setta il path della immagine del donatore.
     * @param imgSource è il path della immagine del donatore.
     */
    public void setImgSource(String imgSource) {
        this.imgSource = imgSource;
    }

    /**
     * Metodo che ritorna l'id del tesserino.
     * @return idTessera e' l'id del tesserino.
     */
    public Long getIdTessera() {
        return idTessera;
    }

    /**
     * Metodo che setta l'id del tesserino.
     * @param idTessera e' l'id del tesserino.
     */
    public void setIdTessera(Long idTessera) {
        this.idTessera = idTessera;
    }

    /**
     * Metodo che ritorna il numero di matricola del tesserino.
     * @return numeroMatricola e' il numero di matricola del tesserino.
     */
    public int getNumeroMatricola() {
        return numeroMatricola;
    }

    /**
     * Metodo che setta il numero di matricola del tesserino.
     * @param numeroMatricola e' il numero di matricola del tesserino.
     */
    public void setNumeroMatricola(int numeroMatricola) {
        this.numeroMatricola = numeroMatricola;
    }

    /**
     * Metodo che ritorna il codice fiscale del proprietario del tesserino.
     * @return donatoreUtenteCodiceFiscale e' il codice fiscale del proprietario del tesserino.
     */
    public String getDonatoreUtenteCodiceFiscale() {
        return codiceFiscaleDonatore;
    }

    /**
     * Metodo che setta il codice fiscale del proprietario del tesserino.
     * @param donatoreUtenteCodiceFiscale e' il codice fiscale del proprietario del tesserino.
     */
    public void setDonatoreUtenteCodiceFiscale(String donatoreUtenteCodiceFiscale) {
        this.codiceFiscaleDonatore = donatoreUtenteCodiceFiscale;
    }

    /**
     * Metodo che ritorna la data di rilascio del tesserino.
     * @return dataRilascio e' la data di rilascio del tesserino.
     */
    public Date getDataRilascio() {
        return dataRilascio;
    }

    /**
     * Metodo che setta la data di rilascio del tesserino.
     * @param dataRilascio e' la data di rilascio del tesserino.
     */
    public void setDataRilascio(Date dataRilascio) {
        this.dataRilascio = dataRilascio;
    }

    /**
     * Metodo che ritorna il gruppo sanguigno sul tesserino.
     * @return gruppoSanguigno e' il gruppo sanguigno sul tesserino.
     */
    public String getGruppoSanguigno() {
        return gruppoSanguigno;
    }

    /**
     * Metodo che setta il gruppo sanguigno sul tesserino.
     * @param gruppoSanguigno e' il gruppo sanguigno sul tesserino.
     */
    public void setGruppoSanguigno(String gruppoSanguigno) {
        this.gruppoSanguigno = gruppoSanguigno;
    }

    /**
     * Metodo che ritorna l'rh sul tesserino.
     * @return rh e' l'rh sul tesserino.
     */
    public String getRh() {
        return rh;
    }

    /**
     * Metodo che setta l'rh sul tesserino.
     * @param rh e' l'rh sul tesserino.
     */
    public void setRh(String rh) {
        this.rh = rh;
    }

    /**
     * Metodo che ritorna la lista di donazioni sul tesserino.
     * @return listaDonazioni e' la lista di donazioni sul tesserino.
     */
    public List<Donazione> getListaDonazioni() {
        return listaDonazioni;
    }

    /**
     * Metodo che setta la lista di donazioni sul tesserino.
     * @param listaDonazioni e' la lista di donazioni sul tesserino.
     */
    public void setListaDonazioni(List<Donazione> listaDonazioni) {
        this.listaDonazioni = listaDonazioni;
    }

    /** Espressione regolare che definisce il formato del campo residenza. */
    public static final String CODICEFISCALE_REGEX = "^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    /** Espressione regolare che definisce il formato del campo di data di nascita. */
    public static final String NUMEROMATRICOLA_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-][12]{1}\\d{3}$";

    /** Espressione regolare che definisce il formato del campo luogo di nascita. */
    public static final String DATARILASCIO_REGEX = "^(0?[1-9]|[12][0-9]|3[01])[\\/\\-](0?[1-9]|1[012])[\\/\\-]\\d{4}$";

    /** Espressione regolare che definisce il formato del campo luogo di nascita. */
    public static final String RH_REGEX = "^(POS|NEG)";

    /** Espressione regolare che definisce il formato del campo luogo di nascita. */
    public static final String GRUPPOSANGUIGNO_REGEX = "^(0-|0\\+|A-|A\\+|B-|B\\+|AB-|AB\\+)";
}
