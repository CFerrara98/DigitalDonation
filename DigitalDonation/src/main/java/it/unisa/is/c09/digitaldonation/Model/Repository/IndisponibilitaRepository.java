package it.unisa.is.c09.digitaldonation.Model.Repository;

import it.unisa.is.c09.digitaldonation.Model.Entity.Indisponibilita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Kevin Pacifico, Elpidio Mazza
 *
 *         Classe che definisce le operazioni per la modellazione e l'accesso
 *         alle informazioni persistenti relative ad un'indisponibilità a donare.
 *
 * @see Indisponibilita
 */
@Repository
public interface IndisponibilitaRepository extends JpaRepository<Indisponibilita, String> {
    /**
     * Permette di salvare o aggiornare le informazioni di un'indisponibilità a donare.
     *
     * @param indisponibilita Oggetto che rappresenta le informazioni di un'indisponibilità a donare.
     *
     * @return Oggetto {@link Indisponibilita} che rappresenta un'indisponibilità a donare. Può essere
     *         null se nel database non è possibile aggiornare le informazioni di un'indisponibilità a donare.
     *
     * @pre indisponibilita != null
     */
    Indisponibilita save(Indisponibilita indisponibilita);

    /**
     * Permette di salvare le indisponibilità appartenenti ad uno specifico donatore.
     *
     * @param codiceFiscaleDonatore String è il codice fiscale del donatore.
     * @return List<Indisponibilita> è la lista delle indisponibilità.
     */
    @Query("select i from Indisponibilita i where i.codiceFiscaleDonatore = ?1")
    List<Indisponibilita> findIndisponibilitaByCodiceFiscaleDonatore(String codiceFiscaleDonatore);

    /**
     * Metodo che ritorna solo le indisponibilita che scadono prima della data.
     * @return lista indisponibilita scadute.
     */
    @Query("select i from Indisponibilita i where i.codiceFiscaleDonatore = ?1 and i.dataProssimaDisponibilita > ?2")
    List<Indisponibilita> findIndisponibilitaByCodiceFiscaleDonatoreAndDataProssimaDisponibilitaAfter(String codiceFiscale, Date data);
}