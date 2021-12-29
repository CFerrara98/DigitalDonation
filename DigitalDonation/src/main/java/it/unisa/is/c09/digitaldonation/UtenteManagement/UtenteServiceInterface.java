package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;

public interface UtenteServiceInterface {

    public Utente login(String email, String password) throws UserNotLoggedException;

    public void logout(Utente utente) throws AccessNotAuthorizedException;

    public void setUtenteAutenticato(String email);

    public Utente getUtenteAutenticato();

    public String validaMail(String email) throws MailNonValidaException, MailNonEsistenteException;
}
