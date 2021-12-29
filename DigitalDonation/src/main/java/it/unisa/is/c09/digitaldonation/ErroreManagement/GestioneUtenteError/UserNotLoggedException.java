package it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError;

public class UserNotLoggedException extends Exception {

    public UserNotLoggedException(String login, String errore) {
        super(errore);
    }
}
