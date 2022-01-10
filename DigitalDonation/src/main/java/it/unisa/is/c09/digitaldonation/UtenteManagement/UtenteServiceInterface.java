package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;

import java.security.NoSuchAlgorithmException;

/**
 * Interfaccia che fornisce i metodi per la logica di business della gestione dell'utenza
 * @author Fabio Siepe, Mattia Sapere
 */
public interface UtenteServiceInterface {

    Utente login(String email, String password) throws UserNotLoggedException, NoSuchAlgorithmException;
    void logout(Utente utente) throws AccessNotAuthorizedException;
}
