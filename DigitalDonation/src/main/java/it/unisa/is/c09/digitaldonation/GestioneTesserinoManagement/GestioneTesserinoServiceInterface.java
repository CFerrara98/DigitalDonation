package it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement;

import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.CannotUpdateDataRepositoryException;
import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;

import java.util.Date;

public interface GestioneTesserinoServiceInterface {

    public Tesserino creazioneTesserino(Tesserino tesserino) throws CannotSaveDataRepositoryException;

    public Indisponibilita autodichiarazioneIndisponibilita(Date data, String motivazione, String codiceFiscaleDonatore) throws CannotSaveDataRepositoryException;

    public Tesserino aggiornaTesserino(Tesserino tesserino) throws CannotUpdateDataRepositoryException;

    public String generaPassword(Tesserino tesserino) throws CannotSaveDataRepositoryException;
}
