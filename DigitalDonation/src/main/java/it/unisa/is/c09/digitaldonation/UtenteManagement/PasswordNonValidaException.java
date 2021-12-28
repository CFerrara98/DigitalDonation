package it.unisa.is.c09.digitaldonation.UtenteManagement;

public class PasswordNonValidaException extends Exception {

    public PasswordNonValidaException(String login, String la_password_non_è_valida) {
        super(la_password_non_è_valida);
    }
}
