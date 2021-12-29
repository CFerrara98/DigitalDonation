package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kevin Pacifico, Elpidio Mazza
 * <p>
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persisteti relative ad un guest
 * @see Guest
 */

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {

    /**
     * Permette di salvare o aggiornare le informazioni di un guest nel database.
     *
     * @param guest Oggetto che rappresenta le informazioni di un utente.
     *
     * @return Oggetto {@link Guest} che rappresenta un guest. Può essere
     *         null se nel database non è possibile aggiornare le informazioni nel database.
     *
     * @pre guest != null
     */
    Guest save(Guest guest);


    /**
     * Permette di ottenere un guest a partire dal proprio codice fiscale.
     *
     * @param CodiceFiscaleGuest Stringa che rappresenta il codice fiscale dell'utente.
     *
     * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
     *         null se nel database non è presente un utente con codice fiscale come parametro.
     *
     * @pre codiceFiscale != null
     */
    Utente findByCodiceFiscaleGuest(String CodiceFiscaleGuest);

    boolean exists(String CodiceFiscaleGuest);



}
