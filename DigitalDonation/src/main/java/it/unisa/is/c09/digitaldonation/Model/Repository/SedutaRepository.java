package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    void deleteSedutaByIdSeduta(Long idSeduta);

    /*@Query(value = "SELECT idSeduta, data_fine_prenotazione, data_inizio_prenotazione, dataSeduta, luogo, numero_partecipanti, sede_locale_codice_identificativo from seduta where dataSeduta > current_date", nativeQuery = true)
    List<Seduta> findSedutaPrenotabili();*/


    /**
     * Metodo che controlla se esiste il guest all'interno della seduta in base al codice fiscale
     * @param idSeduta id della seduta
     * @param codiceFiscaleGuest codice fiscale del guest
     * @return se il guest è gia presente nella seduta
     */
    boolean existsByIdSedutaAndListaGuest_CodiceFiscaleGuest(Long idSeduta, String codiceFiscaleGuest);

    /**
     * Metodo che controlla se esiste il donaotre all'interno della seduta in base al codice fiscale
     * @param idSeduta id della seduta
     * @param codiceFiscaleUtente codice fiscale dell ' utente
     * @return se il guest è gia presente nella seduta
     */
    boolean existsByIdSedutaAndListaDonatore_CodiceFiscaleUtente(Long idSeduta, String codiceFiscaleUtente);

    /**
     * Metodo che che ritorna solo le sedute a cui si puo fare la prenotazione
     * @return lista sedute prenotabili ATTENZIONE le liste sono null!
     */
    @Query(value = "SELECT idSeduta, data_fine_prenotazione, data_inizio_prenotazione, dataSeduta, luogo, numero_partecipanti, sede_locale_codice_identificativo from seduta where dataSeduta > current_date", nativeQuery = true)
    List<Seduta> findSedutePrenotabiliNoList();
}
