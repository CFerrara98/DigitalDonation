package it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror;


/**
 * Eccezione generata in caso di mail non valida.
 *
 * @author Mattia Sapere, Fabio Siepe
 */
public class MailNonValidaException extends Exception {

    private static final long serialVersionUID = -8870973825876004160L;

    /**
     * Stringa che definisce il messaggio di default utilizzato nell'eccezione. */
    private static final String MESSAGGIODEFAULT = "Email o password errati, per favore riprova.";

    /**
     * Costruisce un'eccezione che ha come messaggio {@link #MESSAGGIODEFAULT}.
     */
    public MailNonValidaException() {
        super(MESSAGGIODEFAULT);
    }

}
