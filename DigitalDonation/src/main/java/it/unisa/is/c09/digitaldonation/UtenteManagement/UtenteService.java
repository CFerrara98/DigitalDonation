package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.AccessNotAuthorizedException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.MailNonValidaException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.GestioneUtenteError.UserNotLoggedException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService implements UtenteServiceInterface {

    @Autowired
    UtenteRepository utenteRepository;

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
    public Utente login(String email, String password) throws UserNotLoggedException {
        Utente utente;
        // Controlla se le credenziali corrispondono a quelle di uno studente e, nel
        // caso, controlla
        // che la richiesta d'iscrizione associatagli sia stata accettata
        utente = utenteRepository.findByEmailAndPassword(email, password);
        if (utente != null) {
            AutenticazioneHolder.setUtente(email);
            return utente;
        }
        else if(password == null){
            throw new UserNotLoggedException("login","La password non è valida.");
        }else if(email == null){
            throw new UserNotLoggedException("login","L'email non può essere nulla.");
        }else {
            throw new UserNotLoggedException("login","Email o password errati.");
        }
    }

    /**
     * Permette la rimozione dell'utente dalla sessione.
     */
    public void logout(Utente utente) throws AccessNotAuthorizedException {
        if(utente == null)
        {
            throw new AccessNotAuthorizedException("logout","Errore durante il logout.");
        }
        AutenticazioneHolder.setUtente(null);
    }


    /**
     * Permette di ottenerel'utente autenticato nel sistema
     *
     * @return l'utente autenticato nel sistema, <b>null</b> se non vi è alcun
     * utente autenticato
     */
    @Override
    public Utente getUtenteAutenticato() {
        // Ottieni l'username dell'utente autenticato e restituisci null se non è
        // presente alcun utente
        // in sessione
        String email = (AutenticazioneHolder.getUtente());
        if (email == null) {
            return null;
        }

        Utente utente;

        // Controlla se l'username è associato ad uno studente
        utente = utenteRepository.findByEmail(email);
        if (utente != null) {
            return utente;
        }

        return null;
    }

    /**
     * Permette di specificare l'utente autenticato nel sistema, tramite l'email, in
     * una variabile visibile a livello di thread così da condividere l'informazione
     * con tutti gli altri livelli. Questo metodo può essere utilizzato per
     * iniettare automaticamente l'utente nel thread associato alla richiesta a
     * partire dall'attributo di sessione del server.
     *
     * @param email Stringa relativa all'email dell'utente che si vuole autenticare
     *              nel sistema
     */
    @Override
    public void setUtenteAutenticato(String email) {
        // Se username è null, rimuovi la variabile di thread per prevenire memory leak
        if (email == null) {
            AutenticazioneHolder.setUtente(null);
            return;
        }

        if (utenteRepository.existsUtenteByEmail(email)) {
            AutenticazioneHolder.setUtente(email);
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
    @Override
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
