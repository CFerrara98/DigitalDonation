package it.unisa.is.c09.digitaldonation.GestioneTesserinoManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;

import java.util.Date;

public interface GestioneTesserinoServiceInterface {

    public Tesserino creazioneTesserino(Tesserino tesserino);

    public Indisponibilita autodichiarazioneIndisponibilita(Date data);

    public Tesserino aggiornaTesserino(Tesserino tesserino);

    public String generaPassword(Tesserino tesserino);
}
