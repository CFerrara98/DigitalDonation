package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * Classe che modella l'indisponibilità a donare di un donatore.
 */
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
