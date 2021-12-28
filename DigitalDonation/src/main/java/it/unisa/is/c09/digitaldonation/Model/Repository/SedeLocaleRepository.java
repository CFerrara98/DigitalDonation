package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.SedeLocale;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Kevin Pacifico, Elpidio Mazza
 * <p>
 * Classe che definisce le operazioni per la modellazione e l'accesso
 * alle informazioni persisteti relative ad una sede locale
 * @see SedeLocale
 */

@Repository
public interface SedeLocaleRepository extends JpaRepository<SedeLocale, Long> {

    /**
     * Permette di ottenere una sede locale a partire dal proprio codice identificativo.
     *
     * @param codiceIdentificativo Long che rappresenta il codice identificativo.
     *
     * @return Oggetto {@link SedeLocale} che rappresenta una sede locale. Può essere
     *         null se nel database non è presente una sede locale con codice identificativo passato come parametro.
     *
     * @pre codiceIdentificativo != null
     */
    SedeLocale findSedeLocaleByCodiceIdentificativo(Long codiceIdentificativo);

}
