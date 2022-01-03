package it.unisa.is.c09.digitaldonation.GestioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotRelaseFeedbackException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;

public interface GestioneSeduteServiceInterface {

    public Donazione salvataggioDonazione(Donatore donatore, Seduta seduta);

    public Indisponibilita salvataggioIndisponibilita(Donatore donatore);

}
