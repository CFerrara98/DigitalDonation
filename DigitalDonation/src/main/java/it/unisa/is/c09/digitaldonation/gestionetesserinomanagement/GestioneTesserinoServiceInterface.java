package it.unisa.is.c09.digitaldonation.gestionetesserinomanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Donazione;
import it.unisa.is.c09.digitaldonation.model.entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.model.entity.Tesserino;
import it.unisa.is.c09.digitaldonation.model.entity.Utente;

/**
 * Interfaccia che fornisce i metodi per la logica di business della gestione del tesserino.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public interface GestioneTesserinoServiceInterface {

  Tesserino creazioneTesserino(Donatore donatore, Tesserino tesserino,
                     Donazione donazione) throws CannotSaveDataRepositoryException;

  Indisponibilita autodichiarazioneIndisponibilita(Indisponibilita indisponibilita)
                      throws CannotSaveDataRepositoryException;

  Tesserino aggiornaTesserino(Utente utente) throws CannotSaveDataRepositoryException;
}
