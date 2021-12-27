package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Utente {
    @Id
    private String codiceFiscale;
    private String email;

    public String getCodiceFiscale() {
        return codiceFiscale;
    }
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
