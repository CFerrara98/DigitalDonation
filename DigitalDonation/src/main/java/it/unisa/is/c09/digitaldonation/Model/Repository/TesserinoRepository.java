package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
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
public interface TesserinoRepository extends JpaRepository<Tesserino, Long> {

    /**
     * Permette di ottenere un tesserino a partire dal codice id.
     *
     * @param idTesserino Long che rappresenta l'id del tesserino.
     *
     * @return Oggetto {@link Tesserino} che rappresenta il tesserino. Può essere
     *         null se nel database non è presente un tesserino con id come parametro.
     *
     * @pre idTesserino != null
     */
    Tesserino findTesserinoById(Long idTesserino);

    /**
     * Permette di ottenere un tesserino a partire dal codice fiscale.
     *
     * @param codiceFiscaleUtente Stringa che rappresenta il codice fiscale del donatore.
     *
     * @return Oggetto {@link Tesserino} che rappresenta il tesserino. Può essere
     *         null se nel database non è presente un tesserino con codice fiscale come parametro.
     *
     * @pre codiceFiscaleUtente != null
     */
    Tesserino findDonatoreByCodiceFiscale(String codiceFiscaleUtente);

    /**
     * Permette di salvare o aggiornare le informazioni di un tesserino nel database.
     *
     * @param tesserino Oggetto che rappresenta le informazioni di un tesserino.
     *
     * @return Oggetto {@link Tesserino} che rappresenta il tesserino. Può essere
     *         null se nel database non è possibile aggiornare le informazioni del tesserino.
     *
     * @pre tesserino != null
     */
    Tesserino save(Tesserino tesserino);
}
