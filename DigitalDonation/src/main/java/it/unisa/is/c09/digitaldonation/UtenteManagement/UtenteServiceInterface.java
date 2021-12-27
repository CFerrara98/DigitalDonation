package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.web.Model.Entity.Utente;

public interface UtenteServiceInterface {
    public void setUtenteAutenticato(String email);
    public Utente getUtenteAutenticato();
}
