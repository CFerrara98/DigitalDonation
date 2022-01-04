package it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotUpdateDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.Model.Repository.IndisponibilitaRepository;
import it.unisa.is.c09.digitaldonation.Model.Repository.SedutaRepository;
import it.unisa.is.c09.digitaldonation.Model.Repository.TesserinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class GestioneTesserinoService implements GestioneTesserinoServiceInterface {



    @Autowired
    private TesserinoRepository tesserinoRepository;

    @Autowired
    private IndisponibilitaRepository indisponibilitaRepository;

    @Autowired
    private SedutaRepository sedutaRepository;


    /**
     * Questo metodo permette all'operatore di creare un nuovo tesserino.
     *
     * @param tesserino Il tesserino
     * @return il tesserino creato
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tesserino creazioneTesserino(Tesserino tesserino) throws CannotSaveDataRepositoryException {
        if(tesserino == null){
            throw new CannotSaveDataRepositoryException("tesserinoError", "Errore, il tesserino è null");
        }else if(tesserinoRepository.findTesserinoByIdTessera(tesserino.getIdTessera())!=null){
            throw new CannotSaveDataRepositoryException("tesserinoError", "Il tesserino già esiste");
        }
        tesserinoRepository.save(tesserino);
        return tesserino;
    }


    /**
     * Questo metodo permette di dichiarare l'indisponibilità di un donatore a donare
     *
     * @param data la data di disponibilità
     * @param motivazione motivazione di indisponibilità
     * @return l'indisponibilità
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Indisponibilita autodichiarazioneIndisponibilita(Date data, String motivazione, String codiceFiscaleDonatore) throws CannotSaveDataRepositoryException {

        List<Seduta> listaSedute;
        Indisponibilita indisponibilita = new Indisponibilita();
        if (data == null){
            throw new CannotSaveDataRepositoryException("autodichiarazioneError", "Errore, la data è null");
        }
        else if(motivazione == null){
            throw new CannotSaveDataRepositoryException("autodichiarazioneError", "Errore, la motivazione è null");
        }

        indisponibilita.setDataProssimaDisponibilita(data);
        indisponibilita.setMotivazioni(motivazione);
        indisponibilita.setCodiceFiscaleDonatore(codiceFiscaleDonatore);
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


    /**
     * Questo metodo permette di aggiornare il tesserino
     *
     * @param tesserino Il tesserino
     * @return il tesserino aggiornato
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tesserino aggiornaTesserino(Tesserino tesserino) throws CannotUpdateDataRepositoryException {



    }






    /**
     * Questo metodo permette di generare una password in seguito alla creazione del tesserino
     *
     * @param tesserino Il tesserino
     * @return la password generata
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String generaPassword(Tesserino tesserino) throws CannotSaveDataRepositoryException {

        if(tesserino == null){
            throw new CannotSaveDataRepositoryException("tesserinoError", "Errore, il tesserino è null");
        }

    }




}
