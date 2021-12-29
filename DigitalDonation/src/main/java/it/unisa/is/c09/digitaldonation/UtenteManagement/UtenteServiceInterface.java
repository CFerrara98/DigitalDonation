package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;

public interface UtenteServiceInterface {

    public Utente login(String email, String password) throws UserNotLoggedException;

    public void logout(Utente utente) throws AccessNotAuthorizedException;;

}
