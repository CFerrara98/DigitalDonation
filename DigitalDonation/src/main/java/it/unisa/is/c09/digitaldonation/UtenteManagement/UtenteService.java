package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UtenteService implements UtenteServiceInterface{

    @Autowired
    UtenteRepository utenteRepository;

    /**
     * Permette di ottenerel'utente autenticato nel sistema
     *
     * @return l'utente autenticato nel sistema, <b>null</b> se non vi è alcun
     *         utente autenticato
     */
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

}
