package it.unisa.is.c09.digitaldonation.GestioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.Utils.Forms.IndisponibilitaDonazioneForm;

/**
 * Interfaccia che fornisce i metodi per la logica di business della gestione delle sedute.
 * @author Fabio Siepe, Mattia Sapere
 */
public interface GestioneSeduteServiceInterface {

    Donazione salvataggioDonazione(String codiceFiscaleDonatore, Long idSeduta, String tipoDonazione) throws CannotSaveDataRepositoryException;
    Indisponibilita salvataggioIndisponibilita(String codiceFiscaleDonatore, Long idSeduta, IndisponibilitaDonazioneForm indisponibilitaDonazioneForm) throws CannotSaveDataRepositoryException;
}
