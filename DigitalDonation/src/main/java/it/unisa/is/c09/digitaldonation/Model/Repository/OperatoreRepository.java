package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
import org.springframework.data.jpa.repository.JpaRepository;
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
         * @param codiceFiscale Stringa che rappresenta il codice fiscale dell'operatore.
         *
         * @return Oggetto {@link Operatore} che rappresenta l'operatore. Può essere
         *         null se nel database non è presente un operatore con codice fiscale come parametro.
         *
         * @pre codiceFiscale != null
         */
        Operatore findOperatoreByCodiceFiscale(String codiceFiscale);
}
