package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.SedeLocale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

    /**
     *
     * @author Kevin Pacifico, Elpidio Mazza
     *
     *         Classe che definisce le operazioni per la modellazione e l'accesso
     *         alle informazioni persistenti relative ad un operatore.
     *
     * @see Operatore
     */
    @Repository
    public interface OperatoreRepository extends JpaRepository<Operatore, String> {

        /**
         * Permette di ottenere un operatore a partire dal proprio codice fiscale.
         *
         * @param CodiceFiscaleUtente Stringa che rappresenta il codice fiscale dell'operatore.
         *
         * @return Oggetto {@link Operatore} che rappresenta l'operatore. Può essere
         *         null se nel database non è presente un operatore con codice fiscale come parametro.
         *
         * @pre codiceFiscale != null
         */
        Operatore findOperatoreByCodiceFiscaleUtente(String CodiceFiscaleUtente);

        /**
         * Permette di ottenere la sede locale dell'operatore in base all'email.
         *
         * @param email Stringa che rappresenta l'email dell'operatore.
         *
         * @return Oggetto {@link SedeLocale} che rappresenta la sede locale. Può essere
         *         null se nel database non è presente una sede locale con email come parametro.
         *
         * @pre email != null
         */
        @Query("select s from SedeLocale s")
        SedeLocale findByEmail(String email);
}
