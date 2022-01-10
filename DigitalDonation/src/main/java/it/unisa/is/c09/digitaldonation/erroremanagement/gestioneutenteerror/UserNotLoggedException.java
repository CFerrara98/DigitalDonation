package it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror;

/**
 * Eccezione generata in caso di utente non loggato.
 *
 * @author Mattia Sapere, Fabio Siepe
 */
public class UserNotLoggedException extends Exception {

    public UserNotLoggedException(String login, String errore) {
        super(errore);
    }
}
