package it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Repository.DonatoreRepository;
import it.unisa.is.c09.digitaldonation.Model.Repository.SedutaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrganizzazioneSeduteService implements OrganizzazioneSeduteServiceInterface {

    @Autowired
    private DonatoreRepository donatoreRepository;

    @Autowired
    private SedutaRepository sedutaRepository;


    /**
     * Questo metodo permette al donatore di comunicare la partecipazione o meno alla seduta di donazione.
     *
     * @param donatore Il donatore
     * @param feedback Il feedback del donatore
     * @param idSeduta La seudta alla quale
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void feedbackDonatore(Donatore donatore, boolean feedback, Long idSeduta) throws CannotRelaseFeedbackException {
        if (donatore.getCodiceFiscale() == null) {
            throw new CannotRelaseFeedbackException("feedbackError", "Il campo id non può essere null.");
        }
        if (feedback) {
            // Save(donatore) su Molto a molto Seduta_Donatore
        }
    }

    /**
     * Questo metodo permette di recuperare i dettagli della seduta
     *
     * @param idSeduta L'id della seduta che si vuole monitorare
     * @return Una seduta
     */

    @Override
    public Seduta monitoraggioSeduta(Long idSeduta) throws CannotLoadDataRepositoryException {

        if (idSeduta == null) {
            throw new CannotLoadDataRepositoryException("sedutaError", "Il campo id della seduta non può essere null.");
        }

        return sedutaRepository.findByIdSeduta(idSeduta);

    }

    /**
     * Questo metodo permette di aggiungere guest alla seduta di donazione.
     *
     * @param idSeduta id della seduta alla quale si vuole aggiungere il guest
     * @param guest    guest da aggiungere
     * @return Il guest
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Guest inserimentoGuest(Long idSeduta, Guest guest) throws CannotSaveDataRepositoryException {
        if (idSeduta == null) {
            throw new CannotSaveDataRepositoryException("sedutaError", "Il campo id della seduta non può essere null.");
        }
        if (guest.getcodiceFiscaleGuest() == null) {
            throw new CannotSaveDataRepositoryException("GuestError", "il campo CF del guest non può essere null");
        }

        //Controllo Guest fa parte della seduta (seduta_Guest) molti a molti

        // Save(donatore) su Molto a molto Seduta_Donatore

        return guest;

    }

    /**
     * Questo metodo permette di creare una nuova seduta.
     *
     * @param seduta Id della seduta
     * @return la seduta creata
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Seduta SchedulazioneSeduta(Seduta seduta) throws CannotSaveDataRepositoryException {
        if (seduta.getIdSeduta() == null) {
            throw new CannotSaveDataRepositoryException("sedutaError", "Il campo id della seduta non può essere null.");
        }

        sedutaRepository.save(seduta);
        return seduta;
    }

    /**
     * Questo metodo permette di modificare una seduta
     *
     * @param seduta id della seduta da modificare
     * @return la seduta modificata
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Seduta modificaSeduta(Seduta seduta, Long idSeduta) throws CannotUpdateDataRepositoryException {
        if (seduta == null) {
            throw new CannotUpdateDataRepositoryException("sedutaError", "La seduta non può essere null");
        }
        if (idSeduta == null) {
            throw new CannotUpdateDataRepositoryException("sedutaError", "La seduta da modificare non può essere null");
        }


        sedutaRepository.deleteSedutaByIdSeduta(idSeduta);
        seduta.setIdSeduta(idSeduta);
        sedutaRepository.save(seduta);
        return seduta;
    }

    /**
     * Questo metodo permette di eliminare una seduta
     *
     * @param idSeduta id della seduta da eliminare
     */
    @Override
    public void eliminaSeduta(Long idSeduta) throws CannotDeleteDataRepositoryException {
        if (sedutaRepository.findByIdSeduta(idSeduta) == null) {
            throw new CannotDeleteDataRepositoryException("eliminazioneSedutaError", "Errore durante l'eliminazione della seduta");
        }

        sedutaRepository.deleteSedutaByIdSeduta(idSeduta);
    }

    /**
     * Questo metodo permette di recuperare una seduta dato il suo id
     *
     * @param idSeduta id della seduta da recuperare
     * @return la seduta recuperata
     */

    @Override
    public Seduta visualizzaSeduta(Long idSeduta) throws CannotLoadDataRepositoryException {
        if (idSeduta == null) {
            throw new CannotLoadDataRepositoryException("erroreVisualizzazioneSeduta", "La seduta da visualizzare deve esistere");
        }

        return sedutaRepository.findByIdSeduta(idSeduta);
    }

    /**
     * Questo metodo permette di recuperare le sedute
     *
     * @return Un elenco delle sedute
     */

    @Override
    public List<Seduta> visualizzaElencoSedute() throws CannotLoadDataRepositoryException {
        return sedutaRepository.findAll();
    }
}
