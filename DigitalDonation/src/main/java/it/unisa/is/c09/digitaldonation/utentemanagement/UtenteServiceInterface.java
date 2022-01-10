package it.unisa.is.c09.digitaldonation.utentemanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.erroremanagement.gestioneutenteerror.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.model.entity.Utente;
import java.security.NoSuchAlgorithmException;

/**
 * Interfaccia che fornisce i metodi per la logica di business della gestione dell'utenza.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public interface UtenteServiceInterface {

  Utente login(String email, String password) throws UserNotLoggedException,
          NoSuchAlgorithmException;

  void logout(Utente utente) throws AccessNotAuthorizedException;
}
