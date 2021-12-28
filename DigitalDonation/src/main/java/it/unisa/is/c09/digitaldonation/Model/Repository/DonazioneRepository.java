package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donazione;
import it.unisa.is.c09.digitaldonation.Model.Entity.Tesserino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persistenti relative ad un tesserino.
 *
 * @see Tesserino
 */
@Repository
public interface DonazioneRepository extends JpaRepository<Donazione, Long> {
    /**
     * Permette di salvare o aggiornare le informazioni di una donazione nel database.
     *
     * @param donazione Oggetto che rappresenta le informazioni di una donazione.
     *
     * @return Oggetto {@link Donazione} che rappresenta la donazione. Può essere
     *         null se nel database non è possibile aggiornare le informazioni della donazione.
     *
     * @pre donazione != null
     */
    Donazione save(Donazione donazione);
}
