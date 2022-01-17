package it.unisa.is.c09.digitaldonation.model.repository;

import it.unisa.is.c09.digitaldonation.model.entity.Operatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persistenti relative ad un operatore.
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 * @see Operatore
 */
@Repository
public interface OperatoreRepository extends JpaRepository<Operatore, String> {

  /**
   * Permette di ottenere un operatore a partire dal proprio codice fiscale.
   *
   * @param codiceFiscaleUtente Stringa che rappresenta il codice fiscale dell'operatore.
   * @return Oggetto {@link Operatore} che rappresenta l'operatore. Può essere
   *         null se nel database non è presente un operatore con codice fiscale come parametro.
   * @pre codiceFiscale != null
   */
  Operatore findOperatoreByCodiceFiscaleUtente(String codiceFiscaleUtente);
}
