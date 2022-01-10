package it.unisa.is.c09.digitaldonation.Utils.Forms;

/**
 * Classe che rappresenta l'oggetto Form per l'inserimento di un utente guest.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public class GuestForm {

    /**
     * Costruttore con parametri utili nei casi di test.
     * @param codiceFiscale e' il codice fiscale inserito nel form.
     * @param nome è il nome inserito nel form.
     * @param cognome è il cognome inserito nel form.
     * @param telefono è il telefono inserito nel form.
     * @param patologie sono le patologie inserite nel form.
     * @param gruppoSanguigno è il gruppo sanguigno inserito nel form.
     */
    public GuestForm(String codiceFiscale, String nome, String cognome, String telefono, String patologie, String gruppoSanguigno) {
        this.codiceFiscale = codiceFiscale;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.patologie = patologie;
        this.gruppoSanguigno = gruppoSanguigno;
    }

    /**
     * Metodo toString.
     * @return String rappresenta l'oggetto in formato stringa.
     */
    @Override
    public String toString() {
        return "GuestForm{" +
                "codiceFiscale='" + codiceFiscale + '\'' +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", telefono='" + telefono + '\'' +
                ", patologie='" + patologie + '\'' +
                ", gruppoSanguigno='" + gruppoSanguigno + '\'' +
                '}';
    }

    /**
     * Metodo che ritorna il codice fiscale del guest.
     *
     * @return codiceFiscale e' il codice fiscale del guest.
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Metodo che setta il codice fiscale del guest.
     *
     * @param codiceFiscale ee' il codice fiscale del guest.
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * Metodo che ritorna il nome del guest.
     *
     * @return nome e' il nome del guest.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo che setta il nome del guest.
     *
     * @param nome e' il nome del guest.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }


    /**
     * Metodo che ritorna il cognome del guest.
     *
     * @return cognome e' il cognome del guest.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Metodo che setta il cognome del guest.
     *
     * @param cognome e' il cognome del guest.
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    /**
     * Metodo che ritorna il numero di telefono del guest.
     *
     * @return telefono e' il numero di telefono del guest.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Metodo che setta il numero di telefono del guest.
     *
     * @param telefono e' il numero di telefono del guest.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }


    /**
     * Metodo che ritorna le patologie del guest.
     *
     * @return patologie sono le patologie del guest.
     */
    public String getPatologie() {
        return patologie;
    }

    /**
     * Metodo che setta e patologie del guest.
     *
     * @param patologie sono le patologie del guest.
     */
    public void setPatologie(String patologie) {
        this.patologie = patologie;
    }

    /**
     * Metodo che ritorna il gruppo sanguigno del guest.
     *
     * @return gruppoSanguigno è il gruppo sanguigno del guest.
     */
    public String getGruppoSanguigno() {
        return gruppoSanguigno;
    }

    /**
     * Metodo che setta il gruppo sanguigno del guest.
     *
     * @param gruppoSanguigno è il gruppo sanguigno del guest.
     */
    public void setGruppoSanguigno(String gruppoSanguigno) {
        this.gruppoSanguigno = gruppoSanguigno;
    }

    private String codiceFiscale;
    private String nome;
    private String cognome;
    private String telefono;
    private String patologie;
    private String gruppoSanguigno;
}
