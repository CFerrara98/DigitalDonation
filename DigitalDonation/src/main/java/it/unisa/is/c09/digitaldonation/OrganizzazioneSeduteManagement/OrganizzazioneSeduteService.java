package it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Model.Repository.DonatoreRepository;
import it.unisa.is.c09.digitaldonation.Model.Repository.GuestRepository;
import it.unisa.is.c09.digitaldonation.Model.Repository.SedutaRepository;

import it.unisa.is.c09.digitaldonation.Model.Repository.UtenteRepository;
import it.unisa.is.c09.digitaldonation.UtenteManagement.MailNonEsistenteException;
import it.unisa.is.c09.digitaldonation.UtenteManagement.MailNonValidaException;
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

    @Autowired
    private GuestRepository guestRepository;

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


    /**
     * Controlla che il nome di un guest sia specificato e che rispetti il formato
     * prestabilito.
     *
     * @param nome Stringa che rappresenta il nome da controllare
     * @return nome La stringa che rappresenta il nome da controllare validato
     * @throws GuestFormException se il nome non è specificato oppure se non
     * rispetta il formato {@link Guest#NOME_COGNOME_REGEX}
     */
    @Override
    public String validaNome(String nome) throws GuestFormException{
        if (nome == null) {
            throw new GuestFormException("GuestNomeError","Il nome non rispetta il formato");
        } else {
            if (!nome.matches(Guest.NOME_COGNOME_REGEX)) {
                throw new  GuestFormException("GuestNomeError","Il nome non rispetta il formato");
            }else {
                return nome;
            }
        }
    }

    /**
     * Controlla che il cognome di un guest sia specificato e che rispetti il formato
     * prestabilito.
     *
     * @param cognome Stringa che rappresenta il cognome da controllare
     * @return cognome La stringa che rappresenta il cognome da controllare validato
     * @throws GuestFormException se il cognome non è specificato oppure se non
     * rispetta il formato {@link Guest#NOME_COGNOME_REGEX}
     */
    @Override
    public String validaCognome(String cognome) throws GuestFormException{
        if (cognome == null) {
            throw new GuestFormException("GuestCognomeError","Il cognome non rispetta il formato");
        } else {
            if (!cognome.matches(Guest.NOME_COGNOME_REGEX)) {
                throw new GuestFormException("GuestCognomeError","Il cognome non rispetta il formato");
            }else {
                return cognome;
            }
        }
    }

    /**
     * Controlla che il numero di telefono di un guest sia specificato e che rispetti il formato
     * prestabilito.
     *
     * @param telefono Stringa che rappresenta il telefono da controllare
     * @return telefono La stringa che rappresenta il telefono da controllare validato
     * @throws GuestFormException se il telefono non è specificato oppure se non
     * rispetta il formato {@link Guest#NUMERO_TELEFONO}
     */
    @Override
    public String validaTelefono(String telefono) throws GuestFormException{
        if (telefono == null) {
            throw new GuestFormException("GuestTelefonoError","Il numero di telefono non rispetta il formato");
        } else {
            if (!telefono.matches(Guest.NUMERO_TELEFONO)) {
                throw new GuestFormException("GuestTelefonoError","Il numero di telefono non rispetta il formato");
            }else {
                return telefono;
            }
        }

    }

    /**
     * Controlla che il codice fiscale di un guest sia specificato e che rispetti il formato
     * prestabilito.
     *
     * @param codiceFiscale Stringa che rappresenta il codice fiscale da controllare
     * @return codiceFiscale La stringa che rappresenta il codice fiscale da controllare validato
     * @throws GuestFormException se il codice fiscale non è specificato oppure se non
     * rispetta il formato {@link Guest#CF_REGEX}
     */
    @Override
    public String validaCodiceFiscaleGuest(String codiceFiscale) throws GuestFormException{
        if (codiceFiscale == null) {
            throw new GuestFormException("GuestCodiceFiscaleError","Il codice fiscale non rispetta il formato");
        } else {
            if (!codiceFiscale.matches(Guest.CF_REGEX)) {
                throw new GuestFormException("GuestCodiceFiscaleError","Il codice fiscale non rispetta il formato");
            }else {
                return codiceFiscale;
            }
        }
    }

    /**
     * Controlla che le patologie di un guest siano specificate e che rispettino il formato
     * prestabilito.
     *
     * @param patologie Stringa che rappresenta le patologie da controllare
     * @return patologie La stringa che rappresentano le patologie da controllare validate
     * @throws GuestFormException se le patologie non sono specificate oppure se non
     * rispettano il formato {@link Guest#REG_PATOLOGIE}
     */
    @Override
    public String validaPatologie(String patologie) throws GuestFormException{
        if (patologie == null) {
            throw new GuestFormException("GuestPatologieError","La patologia non rispetta il formato");
        } else {
            if (!patologie.matches(Guest.REG_PATOLOGIE)) {
                throw new GuestFormException("GuestPatologieError","La patologia non rispetta il formato");
            }else {
                return patologie;
            }
        }
    }

    /**
     * Controlla che il gruppo sanguigno di un guest sia specificato e che rispetti il formato
     * prestabilito.
     *
     * @param gruppoSanguigno Stringa che rappresenta il gruppo sanguigno da controllare
     * @return gruppoSanguigno La stringa che rappresentano il gruppo sanguigno da controllare validato
     * @throws GuestFormException se il gruppo sanguigno non è specificate oppure se non
     * rispetta il formato {@link Guest#REG_GRUPPOSANGUIGNO}
     */
    @Override
    public String validaGruppoSanguigno(String gruppoSanguigno) throws GuestFormException{
        if (gruppoSanguigno == null) {
            throw new GuestFormException("GuestGruppoSanguignoError","Il gruppo sanguigno non rispetta il formato");
        } else {
            if (!gruppoSanguigno.matches(Guest.REG_GRUPPOSANGUIGNO)) {
                throw new GuestFormException("GuestGruppoSanguignoError","Il gruppo sanguigno non rispetta il formato");
            }else {
                return gruppoSanguigno;
            }
        }
    }

}