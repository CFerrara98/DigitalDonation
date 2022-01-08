package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.security.NoSuchAlgorithmException;

import static it.unisa.is.c09.digitaldonation.UtenteManagement.cryptoPassword.CryptoByMD5.getMD5;

/**
 * Utente
 */

@Service
public class UtenteService implements UtenteServiceInterface {

    @Autowired
    private UtenteRepository utenteRepository;

    /**
     * Permette l'autenticazione di un utente nel sistema.
     *
     * @param email Stringa che rappresenta l'email dell'utente
     *
     * @param password Stringa che rappresenta la password dell'utente
     *
     * @throws UserNotLoggedException se la coppia (email, password) non è presente nel sistema
     *
     * @return utente
     */
    @Override
    public Utente login(String email, String password) throws UserNotLoggedException, NoSuchAlgorithmException {

        if (password == null) {
            throw new UserNotLoggedException("login", "La password non è valida.");
        }
        if (email == null) {
            throw new UserNotLoggedException("login", "L'email non può essere nulla.");
        }
        String newpass = getMD5(password);
        Utente utente = utenteRepository.findByEmailAndPassword(email, getMD5(password));
        if (utente != null) {
            return utente;
        }
        throw new UserNotLoggedException("login", "Email o password errati.");
    }

    /**
     * Permette la rimozione dell'utente dalla sessione.
     */
    public void logout(Utente utente) throws AccessNotAuthorizedException {
        if(utente == null) {
            throw new AccessNotAuthorizedException("logout","Errore durante il logout.");
        }
    }

    /**
     * Controlla che l'email di un utente sia specificata e che rispetti il formato
     * prestabilito.
     *
     * @param email Stringa che rappresenta l'email da controllare
     * @return email La stringa che rappresenta l'email da controllare validata
     * @throws MailNonValidaException se l'email non è specificata oppure se non
     *                                   rispetta il formato
     *                                   {@link Utente#EMAIL_REGEX}
     * @throws MailNonEsistenteException se l'email specificata non è presente nel
     *                                   sistema
     */
    public String validaMail(String email) throws MailNonValidaException, MailNonEsistenteException {
        if (email == null) {
            throw new MailNonValidaException();
        } else {
            if (!email.matches(Utente.EMAIL_REGEX)) {
                throw new MailNonValidaException();
            } else if (!utenteRepository.existsUtenteByEmail(email)) {
                throw new MailNonEsistenteException();
            } else {
                return email;
            }
        }
    }


}
