package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persisteti relative ad una seduta.
 *
 * @see Seduta
 */


@Repository
public interface SedutaRepository extends JpaRepository<Seduta, Long> {

    /**
     * Permette di ottenere un utente a partire dal proprio codice fiscale.
     *
     * @param idSeduta Long che rappresenta il codice identificativo di una seduta.
     *
     * @return Oggetto {@link Seduta} che rappresenta la seduta. Può essere
     *         null se nel database non è presente una seduta con codice identificativo come parametro.
     *
     * @pre codiceFiscale != null
     */
    Seduta findByIdSeduta(Long idSeduta);

    /**
     * Permette di salvare o aggiornare le informazioni di una seduta nel database.
     *
     * @param seduta Oggetto che rappresenta le informazioni di un utente.
     *
     * @return Oggetto {@link Seduta} che rappresenta la seduta. Può essere
     *         null se nel database non è possibile aggiornare le informazioni nel database.
     *
     * @pre seduta != null
     */
    Seduta save(Seduta seduta);

    /**
     * Permette di cancellare le informazioni di una seduta nel database.
     *
     * @param idSeduta Long che rappresenta il codice identificativo di una seduta.
     *
     * @pre idSeduta != null
     */
    void deleteSedutaById(Long idSeduta);


}
