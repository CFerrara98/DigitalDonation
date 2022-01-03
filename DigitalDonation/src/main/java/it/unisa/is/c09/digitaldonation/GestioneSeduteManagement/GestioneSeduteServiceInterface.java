package it.unisa.is.c09.digitaldonation.GestioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotRelaseFeedbackException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Utils.Forms.IndisponibilitaDonazioneForm;

import java.util.Date;

public interface GestioneSeduteServiceInterface {

    public Donazione salvataggioDonazione(Donatore donatore, Seduta seduta, String tipoDonazione) throws CannotSaveDataRepositoryException;

    public Indisponibilita salvataggioIndisponibilita(String codiceFiscaleDonatore, Long idSeduta, IndisponibilitaDonazioneForm indisponibilitaDonazioneForm) throws CannotSaveDataRepositoryException;

}
