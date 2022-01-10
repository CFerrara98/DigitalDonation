package it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.*;
import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Interfaccia che fornisce i metodi per la logica di business dell'organizzazione delle sedute.
 * @author Fabio Siepe, Mattia Sapere
 */
public interface OrganizzazioneSeduteServiceInterface {

    void feedbackDonatore(Donatore donatore, Long idSeduta) throws CannotRelaseFeedbackException;
    ArrayList<Object> monitoraggioSeduta(Long idSeduta) throws CannotLoadDataRepositoryException;
    Guest inserimentoGuest(Long idSeduta, Guest guest) throws CannotSaveDataRepositoryException;
    Seduta schedulazioneSeduta(Seduta seduta) throws CannotSaveDataRepositoryException;
    Seduta modificaSeduta(SedutaForm sedutaForm, Long idSeduta, Utente utente) throws CannotUpdateDataRepositoryException;
    void eliminaSeduta(Long idSeduta) throws CannotDeleteDataRepositoryException;
    Seduta visualizzaSeduta(Long idSeduta) throws CannotLoadDataRepositoryException;
    List<Seduta> visualizzaElencoSedute() throws CannotLoadDataRepositoryException;
}
