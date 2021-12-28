package it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError;


/**
 * Eccezione lanciata quando il controllo sull'email di un utente fallisce
        * perché questo non rispetta il pattern oppure è nullo.
        *
        * @author Mattia Sapere, Fabio Siepe
        */
public class MailNonValidaException extends Exception {

    private static final long serialVersionUID = -8870973825876004160L;

    /**
     * Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
    private static final String MESSAGGIODEFAULT = "Email non valida";

    /**
     * Costruisce un'eccezione che ha come messaggio {@link #MESSAGGIODEFAULT}.
     */
    public MailNonValidaException() {
        super(MESSAGGIODEFAULT);
    }

}
