package it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotUpdateDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;

import java.util.Date;

/**
 * Interfaccia che fornisce i metodi per la logica di business della gestione del tesserino.
 * @author Fabio Siepe, Mattia Sapere
 */
public interface GestioneTesserinoServiceInterface {

    Tesserino creazioneTesserino(Donatore donatore, Tesserino tesserino, Donazione donazione) throws CannotSaveDataRepositoryException;
    Indisponibilita autodichiarazioneIndisponibilita(Indisponibilita indisponibilita) throws CannotSaveDataRepositoryException;
    Tesserino aggiornaTesserino(Utente utente) throws CannotSaveDataRepositoryException;
}
