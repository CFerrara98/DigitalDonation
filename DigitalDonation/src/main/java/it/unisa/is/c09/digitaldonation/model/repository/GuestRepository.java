package it.unisa.is.c09.digitaldonation.model.repository;

import it.unisa.is.c09.digitaldonation.model.entity.Guest;
import it.unisa.is.c09.digitaldonation.model.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persisteti relative ad un guest.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 * @see Guest
 */

@Repository
public interface GuestRepository extends JpaRepository<Guest, String> {

  /**
   * Permette di salvare o aggiornare le informazioni di un guest nel database.
   *
   * @param guest Oggetto che rappresenta le informazioni di un utente.
   * @return Oggetto {@link Guest} che rappresenta un guest. Può essere
   *        null se nel database non è possibile aggiornare le informazioni nel database.
   * @pre guest != null
   */
  Guest save(Guest guest);

  /**
   * Permette di ottenere un guest a partire dal proprio codice fiscale.
   *
   * @param codiceFiscaleGuest Stringa che rappresenta il codice fiscale dell'utente.
   * @return Oggetto {@link Utente} che rappresenta l'utente. Può essere
   *        null se nel database non è presente un utente con codice fiscale come parametro.
   * @pre codiceFiscale != null
   */
  Guest findByCodiceFiscaleGuest(String codiceFiscaleGuest);
}
