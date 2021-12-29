package it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError;

/**
        * Eccezione lanciata quando il controllo sull'email di un utente fallisce
        * perché questo è già presente nel sistema.
        *
        * @author Mattia Sapere, Fabio Siepe
        */
public class MailNonEsistenteException extends Exception {

    private static final long serialVersionUID = 6746818670147635153L;

    /** Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
    private static final String MESSAGGIODEFAULT = "Email o password errati, per favore riprova.";

    /**
     * Costruisce un'eccezione che ha come messaggio {@link #MESSAGGIODEFAULT}.
     */
    public MailNonEsistenteException() {
        super(MESSAGGIODEFAULT);
    }
}