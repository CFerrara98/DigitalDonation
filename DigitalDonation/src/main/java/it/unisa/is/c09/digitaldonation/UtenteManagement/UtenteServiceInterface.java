package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;

import java.security.NoSuchAlgorithmException;

public interface UtenteServiceInterface {

    public Utente login(String email, String password) throws UserNotLoggedException, NoSuchAlgorithmException;

    public void logout(Utente utente) throws AccessNotAuthorizedException;;

}
