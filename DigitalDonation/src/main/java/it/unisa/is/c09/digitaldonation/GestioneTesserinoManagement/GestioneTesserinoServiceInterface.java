package it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotUpdateDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;

import java.util.Date;

public interface GestioneTesserinoServiceInterface {

    public Tesserino creazioneTesserino(Utente utente, Donatore donatore, Tesserino tesserino, Donazione donazione) throws CannotSaveDataRepositoryException;

    public Indisponibilita autodichiarazioneIndisponibilita(Indisponibilita indisponibilita) throws CannotSaveDataRepositoryException;

    public Tesserino aggiornaTesserino(Tesserino tesserino) throws CannotUpdateDataRepositoryException;

    public String generaPassword(Tesserino tesserino) throws CannotSaveDataRepositoryException;
}
