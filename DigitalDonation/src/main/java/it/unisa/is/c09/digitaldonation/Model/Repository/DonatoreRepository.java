package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persistenti relative ad un utente registrato alla
 *         piattaforma.
 *
 * @see Donatore
 */
@Repository
public interface DonatoreRepository extends JpaRepository<Donatore, String> {

    /**
     * Permette di ottenere un donatore a partire dal proprio codice fiscale.
     *
     * @param codiceFiscaleUtente Stringa che rappresenta il codice fiscale del donatore.
     *
     * @return Oggetto {@link Donatore} che rappresenta il donatore. Può essere
     *         null se nel database non è presente un donatore con codice fiscale come parametro.
     *
     * @pre codiceFiscale != null
     */
    Donatore findDonatoreByCodiceFiscaleUtente(String codiceFiscaleUtente);

    /**
     * Permette di salvare o aggiornare le informazioni di un donatore nel database.
     *
     * @param donatore Oggetto che rappresenta le informazioni di un donatore.
     *
     * @return Oggetto {@link Donatore} che rappresenta il donatore. Può essere
     *         null se nel database non è possibile aggiornare le informazioni del donatore.
     *
     * @pre donatore != null
     */
    Donatore save(Donatore donatore);


    /**
     * Permette di restituire tutti i donatori.
     *
     * @return Oggetto {@link Donatore} che rappresenta il donatore. Può essere
     *         null se nel database non è possibile aggiornare le informazioni del donatore.
     */
    List<Donatore> findAll();

    /**
     * Permette di restituire tutti i donatori disponibili.
     *
     * @return Oggetto {@link Donatore} che rappresenta il donatore. Può essere
     *         null se nel database non è possibile aggiornare le informazioni del donatore.
     */
    @Query(value = "select * from donatore where (codice_fiscale_utente not in (select codice_fiscale_donatore from indisponibilita)) or (codice_fiscale_utente in (select codice_fiscale_utente from indisponibilita where data_prossima_disponibilita < current_date()))", nativeQuery = true)
    List<Donatore> findDonatoriDisponibili();
}