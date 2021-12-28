package it.unisa.is.c09.digitaldonation.Model.Entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;

@Entity
public class Tesserino {
    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "tesserino_tesserino",
            joinColumns = @JoinColumn(name = "tesserino_id"),
            inverseJoinColumns = @JoinColumn(name = "tesserino_codice_fiscale"))
    private Donatore tesserino;

    public Donatore getTesserino() {
        return tesserino;
    }

    public void setTesserino(Donatore tesserino) {
        this.tesserino = tesserino;
    }
}
