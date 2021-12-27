package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persisteti relative ad un utente registrato alla
 *         piattaforma.
 *
 * @see Utente
 */
@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {

    /**
     * Permette di ottenere un utente a partire dalle proprie credenziali di
     * accesso.
     *
     * @param password Stringa che rappresenta la password dell'utente
     * @param email    Stringa che rappresenta l'email dell'utente
     *
     * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
     *         null se nel database non è presente un utente con email e
     *         password passati come parametro.
     *
     * @pre email != null and password != null
     */
    Utente findByEmailAndPassword(String email, String password);

    /**
     * Permette di ottenere un utente a partire dal proprio codice fiscale.
     *
     * @param codiceFiscale Stringa che rappresenta il codice fiscale dell'utente.
     *
     * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
     *         null se nel database non è presente un utente con codice fiscale come parametro.
     *
     * @pre codiceFiscale != null
     */
    Utente findByCodiceFiscale(String codiceFiscale);

    /**
     * Permette di salvare o aggiornare le informazioni di un utente nel database.
     *
     * @param utente Oggetto che rappresenta le informazioni di un utente.
     *
     * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
     *         null se nel database non è possibile aggiornare le informazioni nel database.
     *
     * @pre utent != null
     */
    Utente save(Utente utente);

    /**
     * Permette di cancellare le informazioni di un utente nel database.
     *
     * @param codiceFiscale Stringa che rappresenta il codice fiscale di un utente.
     *
     * @pre codiceFiscale != null
     */
    void deleteByCodiceFiscale(String codiceFiscale);
}


