package it.unisa.is.c09.digitaldonation.organizzazionesedutemanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.*;
import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Guest;
import it.unisa.is.c09.digitaldonation.model.entity.Seduta;
import it.unisa.is.c09.digitaldonation.model.entity.Utente;
import it.unisa.is.c09.digitaldonation.utils.form.SedutaForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Interfaccia che fornisce i metodi per la logica di business dell'organizzazione delle sedute.
 *
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
