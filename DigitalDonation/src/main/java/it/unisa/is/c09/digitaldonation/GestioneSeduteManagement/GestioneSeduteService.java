package it.unisa.is.c09.digitaldonation.GestioneSeduteManagement;


import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotRelaseFeedbackException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.GuestFormException;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import it.unisa.is.c09.digitaldonation.Utils.Forms.IndisponibilitaDonazioneForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GestioneSeduteService implements GestioneSeduteServiceInterface{

    @Autowired
    private DonatoreRepository donatoreRepository;

    @Autowired
    private DonazioneRepository donazioneRepository;

    @Autowired
    private SedutaRepository sedutaRepository;

    @Autowired
    private TesserinoRepository tesserinoRepository;

    @Autowired
    private IndisponibilitaRepository indisponibilitaRepository;

    /**
     * Questo metodo permette di registrare una nuova donazione da parte di un donatore e aggiorna il tesserino del donatore.
     *
     * @param donatore Il donatore
     * @param seduta La seduta
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Donazione salvataggioDonazione(Donatore donatore, Seduta seduta, String tipoDonazione) throws CannotSaveDataRepositoryException {
        Donazione donazione = new Donazione();
        Tesserino tesserino;
        if (donatore == null){
            throw new CannotSaveDataRepositoryException("donatoreError", "Errore, il donatore è null");
        }
        else if(seduta == null){
            throw new CannotSaveDataRepositoryException("sedutaError", "Errore, la seduta è null");
        }
        else if(sedutaRepository.existsByIdSedutaAndListaDonatore_CodiceFiscaleUtente(seduta.getIdSeduta(), donatore.getCodiceFiscale())){
            donazione = new Donazione(seduta.getDataSeduta(), tipoDonazione);
            donazioneRepository.save(donazione);
            tesserino = tesserinoRepository.findDonatoreBydonatoreUtenteCodiceFiscale(donatore.getCodiceFiscale());
            tesserino.addDonazione(donazione);
            tesserinoRepository.save(tesserino);
        }
        return donazione;
    }

    /**
     * Questo metodo permette di registrare una indisponibilità per un donatore.
     *
     * @param codiceFiscaleDonatore è il codice fiscale del donatore
     * @param idSeduta è l'id della seduta
     * @param indisponibilitaDonazioneForm è il form dell'indisponibilità della donazione
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Indisponibilita salvataggioIndisponibilita(String codiceFiscaleDonatore, Long idSeduta, IndisponibilitaDonazioneForm indisponibilitaDonazioneForm) throws CannotSaveDataRepositoryException{

        List<Seduta> listaSedute;
        Indisponibilita indisponibilita = new Indisponibilita();
        Donatore donatore = donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore);
        Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
        if (donatore== null){
            throw new CannotSaveDataRepositoryException("donatoreError", "Errore, il donatore è null");
        }
        else if(seduta == null){
            throw new CannotSaveDataRepositoryException("sedutaError", "Errore, la seduta è null");
        }

        indisponibilita.setCodiceFiscaleDonatore(codiceFiscaleDonatore);
        indisponibilita.setDataProssimaDisponibilita(indisponibilitaDonazioneForm.getDataProssimaDisponibilita());
        indisponibilita.setMotivazioni(indisponibilitaDonazioneForm.getMotivazioni());
        indisponibilita.setNomeMedico(indisponibilitaDonazioneForm.getNomeMedico());
        indisponibilitaRepository.save(indisponibilita);

        listaSedute = sedutaRepository.findAll();
        for(Seduta s: listaSedute){
            if(s.getDataSeduta().before(indisponibilita.getDataProssimaDisponibilita())){
                for(int i = 0; i < s.getListaDonatore().size(); i++){
                    if(s.getListaDonatore().get(i).getCodiceFiscale().equals(codiceFiscaleDonatore)){
                        s.getListaDonatore().remove(i);
                        i--;
                    }
                }
                sedutaRepository.save(s);
            }
        }
        return indisponibilita;
    }

}
