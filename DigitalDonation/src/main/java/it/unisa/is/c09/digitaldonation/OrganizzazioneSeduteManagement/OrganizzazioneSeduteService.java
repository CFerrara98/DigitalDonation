package it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.UtenteRepository;
import it.unisa.is.c09.digitaldonation.UtenteManagement.AutenticazioneHolder;
import it.unisa.is.c09.digitaldonation.UtenteManagement.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.UtenteManagement.MailNonValidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizzazioneSeduteService implements OrganizzazioneSeduteServiceInterface {


    /**
     * Questo metodo permette al donatore di comunicare la partecipazione o meno alla seduta di donazione.
     *
     * @param idDonatore l'id del donatore
     * @param feedback   Il feedback del donatore
     */
    @Override
    public void feedbackDonatore(Long idDonatore, boolean feedback) {

    }

    /**
     * Questo metodo permette di recuperare i dettagli della seduta
     *
     * @param idSeduta L'id della seduta che si vuole monitorare
     * @return Una lista di sedute
     */

    @Override
    public List<Object> monitoraggioSeduta(Long idSeduta) {
        return null;
    }

    /**
     * Questo metodo permette di aggiungere guest alla seduta di donazione.
     *
     * @param idSeduta id della seduta alla quale si vuole aggiungere il guest
     * @param guest guest da aggiungere
     * @return Il guest
     */
    @Override
    public Guest inserimentoGuest(Long idSeduta, Guest guest) {
        return null;
    }

    /**
     * Questo metodo permette di creare una nuova seduta.
     *
     * @param seduta Id della seduta
     * @return la seduta creata
     */
    @Override
    public Seduta SchedulazioneSeduta(Seduta seduta) {
        return null;
    }

    /**
     * Questo metodo permette di modificare una seduta
     *
     * @param idSeduta id della seduta da modificare
     * @return la seduta modificata
     */
    @Override
    public Seduta modificaSeduta(Long idSeduta) {
        return null;
    }

    /**
     * Questo metodo permette di eliminare una seduta
     *
     * @param idSeduta id della seduta da eliminare
     */
    @Override
    public void eliminaSeduta(Long idSeduta) {

    }

    /**
     * Questo metodo permette di recuperare una seduta dato il suo id
     *
     * @param idSeduta id della seduta da recuperare
     * @return la seduta recuperata
     */
    @Override
    public Seduta visualizzaSeduta(Long idSeduta) {
        return null;
    }

    /**
     * Questo metodo permette di recuperare le sedute
     *
     * @return Un elenco delle sedute
     */
    @Override
    public List<Seduta> visualizzaElencoSedute() {
        return null;
    }
}
