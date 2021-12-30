package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * <p>
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persistenti relative ad un utente registrato alla
 * piattaforma.
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
     * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
     * null se nel database non è presente un utente con email e
     * password passati come parametro.
     * @pre email != null and password != null
     */
    Utente findByEmailAndPassword(String email, String password);

    /**
     * Permette di ottenere un utente a partire dal proprio codice fiscale.
     *
     * @param codiceFiscaleUtente Stringa che rappresenta il codice fiscale dell'utente.
     * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
     * null se nel database non è presente un utente con codice fiscale come parametro.
     * @pre codiceFiscale != null
     */
    Utente findByCodiceFiscaleUtente(String codiceFiscaleUtente);

    /**
     * Permette di salvare o aggiornare le informazioni di un utente nel database.
     *
     * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
     * null se nel database non è possibile aggiornare le informazioni nel database.
     * @pre utente != null
     */
    @Query(value="insert into Utente (codice_fiscale_utente, cognome, email, nome, password) values (:codice_fiscale_utente, :cognome, :email, :nome, MD5(:password))"
            , nativeQuery = true)
    Utente save(@Param("codice_fiscale_utente") String codiceFiscale, @Param("cognome") String cognome,
                @Param("email") String email, @Param("nome") String nome, @Param("password") String password);

    /**
     * Permette di cancellare le informazioni di un utente nel database.
     *
     * @param CodiceFiscaleUtente Stringa che rappresenta il codice fiscale di un utente.
     * @pre codiceFiscale != null
     */
    void deleteByCodiceFiscaleUtente(String CodiceFiscaleUtente);

    /**
     * Permette di verificare se un'utente esiste nel database attraverso la propria
     * email.
     *
     * @param email Stringa che rappresenta l'email di un utente
     * @return true se l'utente esiste, false se l'utente non esiste
     * @pre email != null
     */
    boolean existsUtenteByEmail(String email);

    /**
     * Permette di ottenere un utente a partire dalla proria mail.
     *
     * @param email Stringa che rappresenta l'email dell'utente
     * @return Oggetto {@link Utente} che rappresenta l'utente. <b>Può essere
     * null</b> se nel database non è presente un utente con email come
     * parametro
     * @pre email != null
     */
    Utente findByEmail(String email);

    boolean existsUtenteByEmailAndPassword(String email, String password);
}


