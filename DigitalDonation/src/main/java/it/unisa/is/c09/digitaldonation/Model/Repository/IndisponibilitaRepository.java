package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persistenti relative ad un'indisponibilità a donare.
 *
 * @see Indisponibilita
 */
@Repository
public interface IndisponibilitaRepository extends JpaRepository<Indisponibilita, String> {
    /**
     * Permette di salvare o aggiornare le informazioni di un'indisponibilità a donare.
     *
     * @param indisponibilita Oggetto che rappresenta le informazioni di un'indisponibilità a donare.
     *
     * @return Oggetto {@link Indisponibilita} che rappresenta un'indisponibilità a donare. Può essere
     *         null se nel database non è possibile aggiornare le informazioni di un'indisponibilità a donare.
     *
     * @pre indisponibilita != null
     */
    Indisponibilita save(Indisponibilita indisponibilita);
}