package it.unisa.is.c09.digitaldonation.gestionesedutemanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.CannotSaveDataRepositoryException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.ConfermaDonazioneFormException;
import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.IndisponibilitaDonazioneFormException;
import it.unisa.is.c09.digitaldonation.model.entity.Donatore;
import it.unisa.is.c09.digitaldonation.model.entity.Donazione;
import it.unisa.is.c09.digitaldonation.model.entity.Indisponibilita;
import it.unisa.is.c09.digitaldonation.model.entity.Seduta;
import it.unisa.is.c09.digitaldonation.model.entity.Tesserino;
import it.unisa.is.c09.digitaldonation.model.repository.DonatoreRepository;
import it.unisa.is.c09.digitaldonation.model.repository.DonazioneRepository;
import it.unisa.is.c09.digitaldonation.model.repository.IndisponibilitaRepository;
import it.unisa.is.c09.digitaldonation.model.repository.SedutaRepository;
import it.unisa.is.c09.digitaldonation.model.repository.TesserinoRepository;
import it.unisa.is.c09.digitaldonation.utils.form.IndisponibilitaDonazioneForm;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * La classe fornisce i metodi per la logica di business della gestione delle sedute.
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@Service
public class GestioneSeduteService implements GestioneSeduteServiceInterface {

  @Autowired
  private DonatoreRepository donatoreRepository;

  @Autowired
  private DonazioneRepository donazioneRepository;

  @Autowired
  private SedutaRepository sedutaRepository;

  @Autowired
  private TesserinoRepository tesserinoRepository;

  @Autowired
  private IndisponibilitaRepository indisponibilitaRepository;

  /**
   * Questo metodo permette di registrare una nuova donazione da parte
   * di un donatore ed aggiorna il tesserino del donatore.
   *
   * @param codiceFiscaleDonatore Il codice fiscale del donatore
   * @param idSeduta              L'id della seduta
   * @param tipoDonazione         Il tipo d donazione
   * @return donazione La donazione
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Donazione salvataggioDonazione(String codiceFiscaleDonatore, Long idSeduta,
                                        String tipoDonazione)
          throws CannotSaveDataRepositoryException {
    Donazione donazione = new Donazione();
    Tesserino tesserino;
    Indisponibilita indisponibilita = new Indisponibilita();
    Donatore donatore = donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore);
    Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
    if (donatore == null) {
      throw new CannotSaveDataRepositoryException("donatoreError", "Errore, il donatore ?? null");
    } else if (seduta == null) {
      throw new CannotSaveDataRepositoryException("sedutaError", "Errore, la seduta ?? null");
    } else if (sedutaRepository.existsByIdSedutaAndListaDonatore_CodiceFiscaleUtente(
            seduta.getIdSeduta(), donatore.getCodiceFiscale())) {
      donazione = new Donazione(seduta.getDataSeduta(), tipoDonazione);

      tesserino = tesserinoRepository.findByDonatoreUtenteCodiceFiscale(
              donatore.getCodiceFiscale());
      donazione.setTesserino(tesserino);
      donazioneRepository.save(donazione);
      tesserino.addDonazione(donazione);
      tesserinoRepository.save(tesserino);
    }

    indisponibilita.setCodiceFiscaleDonatore(codiceFiscaleDonatore);
    //La data deve essere calcolata nel seguente modo: 5 mesi dopo la data attuale.
    Date data = new Date();
    data.setMonth(data.getMonth() + 5);

    indisponibilita.setDataProssimaDisponibilita(data);
    indisponibilita.setMotivazioni("donazione");
    indisponibilitaRepository.save(indisponibilita);

    List<Seduta> listaSedute = sedutaRepository.findAll();
    for (Seduta s : listaSedute) {
      if (s.getDataSeduta().before(indisponibilita.getDataProssimaDisponibilita())) {
        for (int i = 0; i < s.getListaDonatore().size(); i++) {
          if (s.getListaDonatore().get(i).getCodiceFiscale().equals(codiceFiscaleDonatore)) {
            s.getListaDonatore().remove(i);
            i--;
          }
        }
        sedutaRepository.save(s);
      }
    }
    return donazione;
  }

  /**
   * Questo metodo permette di registrare una indisponibilit?? per un donatore.
   *
   * @param codiceFiscaleDonatore        ?? il codice fiscale del donatore
   * @param idSeduta                     ?? l'id della seduta
   * @param indisponibilitaDonazioneForm ?? il form dell'indisponibilit?? della donazione
   * @return indisponibilita L'indisponiblit??
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Indisponibilita salvataggioIndisponibilita(String codiceFiscaleDonatore, Long idSeduta,
                       IndisponibilitaDonazioneForm indisponibilitaDonazioneForm)
          throws CannotSaveDataRepositoryException {
    Indisponibilita indisponibilita = new Indisponibilita();
    Donatore donatore = donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore);
    Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
    if (donatore == null) {
      throw new CannotSaveDataRepositoryException("donatoreError", "Errore, il donatore ?? null");
    } else if (seduta == null) {
      throw new CannotSaveDataRepositoryException("sedutaError", "Errore, la seduta ?? null");
    }

    indisponibilita.setCodiceFiscaleDonatore(codiceFiscaleDonatore);
    indisponibilita.setDataProssimaDisponibilita(indisponibilitaDonazioneForm
            .getDataProssimaDisponibilita());
    indisponibilita.setMotivazioni(indisponibilitaDonazioneForm.getMotivazioni());
    indisponibilita.setNomeMedico(indisponibilitaDonazioneForm.getNomeMedico());
    indisponibilitaRepository.save(indisponibilita);

    List<Seduta> listaSedute;
    listaSedute = sedutaRepository.findAll();
    for (Seduta s : listaSedute) {
      if (s.getDataSeduta().before(indisponibilita.getDataProssimaDisponibilita())) {
        for (int i = 0; i < s.getListaDonatore().size(); i++) {
          if (s.getListaDonatore().get(i).getCodiceFiscale().equals(codiceFiscaleDonatore)) {
            s.getListaDonatore().remove(i);
            i--;
          }
        }
        sedutaRepository.save(s);
      }
    }
    return indisponibilita;
  }

  /**
   * Controlla che il tipo di donazione sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param tipoDonazione Stringa che rappresenta il tipo di donazione da controllare
   * @return tipoDonazione La stringa che rappresenta il tipo di donazione da controllare validato
   * @throws ConfermaDonazioneFormException se il nome non ?? specificato oppure se non
   *          rispetta il formato {@link Donazione#TIPODONAZIONE_REGEX}
   */
  public String validaTipoDonazione(String tipoDonazione) throws ConfermaDonazioneFormException {

    if (tipoDonazione == null) {
      throw new ConfermaDonazioneFormException("TipoDonazioneError",
              "Il formato del tipo di donazione ?? errato.");
    } else {
      if (!tipoDonazione.matches(Donazione.TIPODONAZIONE_REGEX)) {
        throw new ConfermaDonazioneFormException("TipoDonazioneError",
                "Il formato del tipo di donazione ?? errato.");
      } else {
        return tipoDonazione;
      }
    }
  }

  /**
   * Controlla che le motivazioni dell'indisponibilit?? per
   * una donazione siano specificate e che rispettino il formato prestabilito.
   *
   * @param motivazioni Stringa che rappresenta le motivazioni da controllare
   * @return motivazioni La stringa che rappresenta le motivazioni da controllare validate
   * @throws IndisponibilitaDonazioneFormException se le motivazioni non sono
   *          specificate oppure se non rispetta il formato
   *          {@link Indisponibilita#MOTIVAZIONI_REGEX}
   */
  public String validaMotivazioni(String motivazioni) throws IndisponibilitaDonazioneFormException {
    if (motivazioni == null) {
      throw new IndisponibilitaDonazioneFormException("IndisponibilitaMotivazioniError",
              "Il formato delle motivazioni ?? errato.");
    } else {
      if (!motivazioni.matches(Indisponibilita.MOTIVAZIONI_REGEX)) {
        throw new IndisponibilitaDonazioneFormException("IndisponibilitaMotivazioniError",
                "Il formato delle motivazioni ?? errato.");
      } else {
        return motivazioni;
      }
    }
  }

  /**
   * Controlla che il nome del medico sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param nomeMedico Stringa che rappresenta il nome da controllare
   * @return nomeMedico La stringa che rappresenta il nome da controllare validato
   * @throws IndisponibilitaDonazioneFormException se il nome
   *         del medico non ?? specificato oppure se non
   *         rispetta il formato {@link Indisponibilita#NOME_MEDICO_REGEX}
   */
  public String validaNomeMedico(String nomeMedico)
          throws IndisponibilitaDonazioneFormException {
    if (nomeMedico == null) {
      throw new IndisponibilitaDonazioneFormException(
              "GuestNomeMedicoError", "Il formato del nome del medico ?? errato.");
    } else {
      if (!nomeMedico.matches(Indisponibilita.NOME_MEDICO_REGEX)) {
        throw new IndisponibilitaDonazioneFormException("GuestNomeMedicoError",
                "Il formato del nome del medico ?? errato.");
      } else {
        return nomeMedico;
      }
    }
  }

  /**
   * Controlla che la data di prossima disponibilit?? di partecipazione ad
   * una donazione sia specificata e che rispetti il formato prestabilito.
   *
   * @param dataProssimaDisponibilita data che rappresenta la data da controllare
   * @return dataProssimaDisponibilita Date che rappresenta la data di prossima
   *         disponibilit?? di partecipazione ad una donazione
   * @throws IndisponibilitaDonazioneFormException se la data non ?? specificata oppure se non
   *          rispetta il formato
   *        {@link Indisponibilita#DATA_PROSSIMA_DISPONIBILITA_REGEX}
   */
  public Date validaDataProssimaDisponibilitaDonazione(Date dataProssimaDisponibilita)
          throws IndisponibilitaDonazioneFormException {
    Date date = new Date();
    if (dataProssimaDisponibilita == null) {
      throw new IndisponibilitaDonazioneFormException("DataProssimaDisponibilitaError",
              "La data di prossima disponibilit?? inserita non rispetta il formato: gg/mm/aaaa.");
    } else {
      if (!(parsDateToString(dataProssimaDisponibilita).matches(
              Indisponibilita.DATA_PROSSIMA_DISPONIBILITA_REGEX))) {
        throw new IndisponibilitaDonazioneFormException(
                "DataProssimaDisponibilitaError", "La data di prossima disponibilit?? "
                + "inserita non rispetta il formato: gg/mm/aaaa.");
      } else if (dataProssimaDisponibilita.before(date)) {
        throw new IndisponibilitaDonazioneFormException(
                "DataProssimaDisponibilitaError", "La data di prossima disponibilit?? "
                + "inserita ?? minore della data corrente.");
      }
      return dataProssimaDisponibilita;
    }
  }

  public Indisponibilita salvaIndisponibilita(Indisponibilita indisponibilita) {
    indisponibilitaRepository.save(indisponibilita);
    return indisponibilita;
  }

  /**
   * Metodo che fa parsing dalla (Date) date alla Stringa gg-mm-aaaa.
   *
   * @param date data in input
   * @return stringa gg-mm-aaaa
   */
  public static String parsDateToString(Date date) {
    SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
    String date1 = format1.format(date);
    return date1;
  }
}
