package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Indisponibilita {
    @ManyToOne
    @JoinColumn(name = "indisponibilita_codice_fiscale")
    private Donatore indisponibilita;

    public Donatore getIndisponibilita() {
        return indisponibilita;
    }

    public void setIndisponibilita(Donatore indisponibilita) {
        this.indisponibilita = indisponibilita;
    }
}
