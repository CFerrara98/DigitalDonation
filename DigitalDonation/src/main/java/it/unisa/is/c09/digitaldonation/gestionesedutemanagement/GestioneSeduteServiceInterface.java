package it.unisa.is.c09.digitaldonation.gestionesedutemanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.model.entity.Donazione;
import it.unisa.is.c09.digitaldonation.model.entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.utils.forms.IndisponibilitaDonazioneForm;

/**
 * Interfaccia che fornisce i metodi per la logica di business della gestione delle sedute.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public interface GestioneSeduteServiceInterface {

    Donazione salvataggioDonazione(String codiceFiscaleDonatore, Long idSeduta, String tipoDonazione) throws CannotSaveDataRepositoryException;
    Indisponibilita salvataggioIndisponibilita(String codiceFiscaleDonatore, Long idSeduta, IndisponibilitaDonazioneForm indisponibilitaDonazioneForm) throws CannotSaveDataRepositoryException;
}
