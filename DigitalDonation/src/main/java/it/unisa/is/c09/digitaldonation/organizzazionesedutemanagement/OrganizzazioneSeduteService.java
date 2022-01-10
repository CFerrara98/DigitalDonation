package it.unisa.is.c09.digitaldonation.organizzazionesedutemanagement;

import it.unisa.is.c09.digitaldonation.erroremanagement.organizzazioneseduteerror.*;
import it.unisa.is.c09.digitaldonation.model.entity.*;
import it.unisa.is.c09.digitaldonation.model.repository.DonatoreRepository;
import it.unisa.is.c09.digitaldonation.model.repository.GuestRepository;
import it.unisa.is.c09.digitaldonation.model.repository.IndisponibilitaRepository;
import it.unisa.is.c09.digitaldonation.model.repository.SedutaRepository;
import it.unisa.is.c09.digitaldonation.utentemanagement.MailSingletonSender;
import it.unisa.is.c09.digitaldonation.utils.form.SedutaForm;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.DateFormat;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * La classe fornisce i metodi per la logica di business dell'organizzazione delle sedute.
 *
 * @author Mattia Sapere, Fabio Siepe
 */
@Service
public class OrganizzazioneSeduteService implements OrganizzazioneSeduteServiceInterface {
  private static Logger logger = Logger.getLogger(OrganizzazioneSeduteService.class);

  @Autowired
  private MailSingletonSender mailSingletonSender;

  @Autowired
  private IndisponibilitaRepository indisponibilitaRepository;

  @Autowired
  private SedutaRepository sedutaRepository;

  @Autowired
  private DonatoreRepository donatoreRepository;

  @Autowired
  private GuestRepository guestRepository;

  /**
   * Questo metodo permette al donatore di comunicare la partecipazione o meno alla seduta di donazione.
   *
   * @param donatore oggetto donatore.
   * @param idSeduta id della seduta.
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void feedbackDonatore(Donatore donatore, Long idSeduta) throws CannotRelaseFeedbackException {
    if (donatore.getCodiceFiscale() == null) {
      throw new CannotRelaseFeedbackException("feedbackError", "Il campo id non può essere null.");
    }
    Seduta seduta;
    seduta = sedutaRepository.findByIdSeduta(idSeduta);
    if (seduta == null) {
      throw new CannotRelaseFeedbackException("feedbackError", "Errore! Seduta non trovata.");
    }
    seduta.addPartecipante(donatore);
    sedutaRepository.save(seduta);
    mailSingletonSender.sendEmailPrenotazione(donatore, seduta);
  }

  /**
   * Questo metodo permette di recuperare i donatori appartenenti alla seduta partendo dall'id
   *
   * @param idSeduta L'id della seduta che si vuole monitorare
   * @return Una lista di Utenti che appartengono a quella seduta
   */
  @Override
  public ArrayList<Object> monitoraggioSeduta(Long idSeduta) throws CannotLoadDataRepositoryException {

    if (idSeduta == null) {
      throw new CannotLoadDataRepositoryException("sedutaError", "Il campo id della seduta non può essere null.");
    }
    Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);

    if (seduta == null) {
      throw new CannotLoadDataRepositoryException("sedutaError", "Non è stata trovata nessuna seduta con questo id.");
    }
    ArrayList<Object> lista = new ArrayList<Object>();
    lista.addAll(seduta.getListaDonatore());
    lista.addAll(seduta.getListaGuest());
    return lista;
  }

  /**
   * Questo metodo permette di aggiungere guest alla seduta di donazione.
   *
   * @param idSeduta id della seduta alla quale si vuole aggiungere il guest
   * @param guest    guest da aggiungere
   * @return Il guest
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Guest inserimentoGuest(Long idSeduta, Guest guest) throws CannotSaveDataRepositoryException {
    if (idSeduta == null) {
      throw new CannotSaveDataRepositoryException("SedutaError", "Il campo id della seduta non può essere null.");
    }

    if (guest.getcodiceFiscaleGuest() == null) {
      throw new CannotSaveDataRepositoryException("GuestError", "il campo CF del guest non può essere null");
    }

    if (sedutaRepository.existsByIdSedutaAndListaGuest_CodiceFiscaleGuest(idSeduta, guest.getcodiceFiscaleGuest())) {
      throw new CannotSaveDataRepositoryException("SedutaError", "Il guest è gia presente nella seduta");
    }
    if (guest.getPatologie() == "") guest.setPatologie("Nessuna");
    Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
    seduta.addPartecipante(guest);
    sedutaRepository.save(seduta);
    return guest;
  }


  /**
   * Questo metodo permette di creare una nuova seduta.
   *
   * @param seduta Id della seduta
   * @return la seduta creata
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Seduta schedulazioneSeduta(Seduta seduta) throws CannotSaveDataRepositoryException {
    if (seduta.getIdSeduta() == null) {
      throw new CannotSaveDataRepositoryException("SedutaError", "Il campo id della seduta non può essere null.");
    }
    if (seduta.getIdSeduta() == -1) {
      seduta.setIdSeduta(null);
    }
    sedutaRepository.save(seduta);
    List<Donatore> donatori = donatoreRepository.findAll();
    for (int i = 0; i < donatori.size(); i++) {
      List<Indisponibilita> indisponibilitaLista = indisponibilitaRepository.findIndisponibilitaByCodiceFiscaleDonatoreAndDataProssimaDisponibilitaAfter(donatori.get(i).getCodiceFiscale(), seduta.getDataSeduta());
      if (indisponibilitaLista.isEmpty()) {
        mailSingletonSender.sendEmailSchedulazioneSeduta(seduta, donatori.get(i));
      }
    }
    return seduta;
  }

  /**
   * Questo metodo permette di modificare una seduta
   *
   * @param idSeduta id della seduta da modificare
   * @return la seduta modificata
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public Seduta modificaSeduta(SedutaForm sedutaForm, Long idSeduta, Utente utente) throws CannotUpdateDataRepositoryException {
    DateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
    Operatore operatore = (Operatore) utente;
    SedeLocale sedeLocale = operatore.getSedeLocale();

    Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
    List<Donatore> listaDonatori = seduta.getListaDonatore();
    seduta.setDataFinePrenotazione(sedutaForm.getDataFinePrenotazione());
    seduta.setDataSeduta(sedutaForm.getDataSeduta());
    seduta.setDataInizioPrenotazione(sedutaForm.getDataInizioPrenotazione());
    seduta.setNumeroPartecipanti(sedutaForm.getNumeroPartecipanti());
    seduta.setOraInizio(Time.valueOf(sedutaForm.getOrarioInizio()));
    seduta.setOraFine(Time.valueOf(sedutaForm.getOrarioFine()));
    seduta.setSedeLocale(sedeLocale.getCodiceIdentificativo());
    String luogo = Seduta.parseToLuogo(sedutaForm.getIndirizzo(), sedutaForm.getCitta(), sedutaForm.getCap(), sedutaForm.getProvincia());
    seduta.setLuogo(luogo);
    if (seduta == null) {
      throw new CannotUpdateDataRepositoryException("sedutaError", "La seduta non può essere null");
    }
    if (idSeduta == null) {
      throw new CannotUpdateDataRepositoryException("sedutaError", "La seduta da modificare non può essere null");
    }
    for (int i = 0; i < listaDonatori.size(); i++) {
      mailSingletonSender.sendEmailModificaSeduta(seduta, listaDonatori.get(i));
    }
    sedutaRepository.save(seduta);
    return seduta;
  }

  /**
   * Questo metodo permette di eliminare una seduta
   *
   * @param idSeduta id della seduta da eliminare
   */
  @Override
  @Transactional(rollbackFor = Exception.class)
  public void eliminaSeduta(Long idSeduta) throws CannotDeleteDataRepositoryException {
    if (sedutaRepository.findByIdSeduta(idSeduta) == null) {
      throw new CannotDeleteDataRepositoryException("eliminazioneSedutaError", "Errore durante l'eliminazione della seduta");
    }
    Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
    List<Donatore> listaDonatori = seduta.getListaDonatore();
    for (int i = 0; i < listaDonatori.size(); i++) {
      mailSingletonSender.sendEmailEliminaSeduta(seduta, listaDonatori.get(i));
    }
    seduta.setListaDonatore(null);
    seduta.setListaGuest(null);
    sedutaRepository.save(seduta);
    sedutaRepository.deleteSedutaByIdSeduta(idSeduta);
  }

  /**
   * Questo metodo permette di recuperare una seduta dato il suo id
   *
   * @param idSeduta id della seduta da recuperare
   * @return la seduta recuperata
   */
  @Override
  public Seduta visualizzaSeduta(Long idSeduta) throws CannotLoadDataRepositoryException {
    if (idSeduta == null) {
      throw new CannotLoadDataRepositoryException("erroreVisualizzazioneSeduta", "La seduta da visualizzare deve esistere");
    }
    return sedutaRepository.findByIdSeduta(idSeduta);
  }

  /**
   * Questo metodo permette di recuperare la lista delle sedute per l'operatore.
   *
   * @return Un elenco delle sedute
   */
  @Override
  public List<Seduta> visualizzaElencoSedute() throws CannotLoadDataRepositoryException {
    return sedutaRepository.findAll();
  }

  /**
   * Questo metodo permette di recuperare la lista delle sedute disponibili per il donatore.
   *
   * @param codiceFiscale String che rappresenta il codice fiscale del donatore.
   * @return la lista delle sedute disponibili.
   */
  public List<Seduta> visualizzaElencoSeduteDisponibili(String codiceFiscale) throws CannotLoadDataRepositoryException {
    List<Indisponibilita> listaIndisponibilitaDonatore = indisponibilitaRepository.findIndisponibilitaByCodiceFiscaleDonatore(codiceFiscale);
    List<Seduta> seduteDisponibili = new ArrayList<>();
    if (listaIndisponibilitaDonatore.isEmpty()) {
      seduteDisponibili = sedutaRepository.findAll();
    } else {
      for (int j = 0; j < listaIndisponibilitaDonatore.size(); j++) {
        Indisponibilita i = listaIndisponibilitaDonatore.get(j);
        Date dataFineIndisponibilita = i.getDataProssimaDisponibilita();
        seduteDisponibili = sedutaRepository.findAllByDataSedutaAfter(dataFineIndisponibilita);
        logger.info("Size lista sedute" + seduteDisponibili.size());
      }
    }

    for (int i = 0; i < seduteDisponibili.size(); i++) {
      Seduta s = seduteDisponibili.get(i);
      List<Donatore> listaDonatori = s.getListaDonatore();
      for (Donatore d : listaDonatori) {
        if (d.getCodiceFiscale().equals(codiceFiscale)) {
          seduteDisponibili.remove(i);
          i--;
        }
      }
    }

    return seduteDisponibili;
  }

  /**
   * Controlla che il nome di un guest sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param nome Stringa che rappresenta il nome da controllare
   * @return nome La stringa che rappresenta il nome da controllare validato
   * @throws GuestFormException se il nome non è specificato oppure se non
   *                            rispetta il formato {@link Guest#NOME_COGNOME_REGEX}
   */
  public String validaNome(String nome) throws GuestFormException {
    if (nome == null) {
      throw new GuestFormException("GuestNomeError", "Il formato del nome è errato: bisogna inserire solo caratteri alfabetici.");
    } else {
      if (!nome.matches(Guest.NOME_COGNOME_REGEX)) {
        throw new GuestFormException("GuestNomeError", "Il formato del nome è errato: bisogna inserire solo caratteri alfabetici.");
      } else {
        return nome;
      }
    }
  }

  /**
   * Controlla che il cognome di un guest sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param cognome Stringa che rappresenta il cognome da controllare
   * @return cognome La stringa che rappresenta il cognome da controllare validato
   * @throws GuestFormException se il cognome non è specificato oppure se non
   *                            rispetta il formato {@link Guest#NOME_COGNOME_REGEX}
   */
  public String validaCognome(String cognome) throws GuestFormException {
    if (cognome == null) {
      throw new GuestFormException("GuestCognomeError", "Il formato del cognome è errato: bisogna inserire solo caratteri alfabetici.");
    } else {
      if (!cognome.matches(Guest.NOME_COGNOME_REGEX)) {
        throw new GuestFormException("GuestCognomeError", "Il formato del cognome è errato: bisogna inserire solo caratteri alfabetici.");
      } else {
        return cognome;
      }
    }
  }

  /**
   * Controlla che il numero di telefono di un guest sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param telefono Stringa che rappresenta il telefono da controllare
   * @return telefono La stringa che rappresenta il telefono da controllare validato
   * @throws GuestFormException se il telefono non è specificato oppure se non
   *                            rispetta il formato {@link Guest#NUMERO_TELEFONO}
   */
  public String validaTelefono(String telefono) throws GuestFormException {
    if (telefono == null) {
      throw new GuestFormException("GuestTelefonoError", "Il formato del numero di telefono è errato: bisogna inserire solo caratteri numerici e simboli.");
    } else {
      if (!telefono.matches(Guest.NUMERO_TELEFONO)) {
        throw new GuestFormException("GuestTelefonoError", "Il formato del numero di telefono è errato: bisogna inserire solo caratteri numerici e simboli.");
      } else {
        return telefono;
      }
    }

  }

  /**
   * Controlla che il codice fiscale di un guest sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param codiceFiscale Stringa che rappresenta il codice fiscale da controllare
   * @return codiceFiscale La stringa che rappresenta il codice fiscale da controllare validato
   * @throws GuestFormException se il codice fiscale non è specificato oppure se non
   *                            rispetta il formato {@link Guest#CF_REGEX}
   */
  public String validaCodiceFiscaleGuest(String codiceFiscale) throws GuestFormException {
    if (codiceFiscale == null) {
      throw new GuestFormException("GuestCodiceFiscaleError", "Il formato del codice fiscale è errato: bisogna inserire solo caratteri alfanumerici.");
    } else {
      if (!codiceFiscale.matches(Guest.CF_REGEX)) {
        throw new GuestFormException("GuestCodiceFiscaleError", "Il formato del codice fiscale è errato: bisogna inserire solo caratteri alfanumerici.");
      } else {
        return codiceFiscale;
      }
    }
  }

  /**
   * Controlla che le patologie di un guest siano specificate e che rispettino il formato
   * prestabilito.
   *
   * @param patologie Stringa che rappresenta le patologie da controllare
   * @return patologie La stringa che rappresentano le patologie da controllare validate
   * @throws GuestFormException se le patologie non sono specificate oppure se non
   *                            rispettano il formato {@link Guest#REG_PATOLOGIE}
   */
  public String validaPatologie(String patologie) throws GuestFormException {
    if (patologie == null) {
      throw new GuestFormException("GuestPatologieError", "Il formato delle patologie è errato: bisogna inserire solo caratteri alfanumerici e simboli composti da almeno 2 caratteri.");
    } else {
      if (!patologie.matches(Guest.REG_PATOLOGIE)) {
        throw new GuestFormException("GuestPatologieError", "Il formato delle patologie è errato: bisogna inserire solo caratteri alfanumerici e simboli composti da almeno 2 caratteri.");
      } else {
        return patologie;
      }
    }
  }

  /**
   * Controlla che il gruppo sanguigno di un guest sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param gruppoSanguigno Stringa che rappresenta il gruppo sanguigno da controllare
   * @return gruppoSanguigno La stringa che rappresentano il gruppo sanguigno da controllare validato
   * @throws GuestFormException se il gruppo sanguigno non è specificate oppure se non
   *                            rispetta il formato {@link Guest#REG_GRUPPOSANGUIGNO}
   */
  public String validaGruppoSanguigno(String gruppoSanguigno) throws GuestFormException {
    if (gruppoSanguigno == null) {
      throw new GuestFormException("GuestGruppoSanguignoError", "Il formato del gruppo sanguigno è errato, è possibile inserire solo le seguenti combinazioni: 0-, 0+, A-, A+, B-, B+, AB-, AB+");
    } else {
      if (!gruppoSanguigno.matches(Guest.REG_GRUPPOSANGUIGNO)) {
        throw new GuestFormException("GuestGruppoSanguignoError", "Il formato del gruppo sanguigno è errato, è possibile inserire solo le seguenti combinazioni: 0-, 0+, A-, A+, B-, B+, AB-, AB+");
      } else {
        return gruppoSanguigno;
      }
    }
  }

  /**
   * Controlla che la data di una seduta sia specificata e che rispetti il formato
   * prestabilito.
   *
   * @param dataSeduta Date che rappresenta la data della seduta
   * @return dataSeduta Date che rappresenta la data della seduta
   * @throws SedutaFormException se la data non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#DATA_SEDUTA_REGEX}
   */
  public Date validaDataSeduta(Date dataSeduta) throws SedutaFormException {
    Date date = new Date();
    if (dataSeduta == null) {
      throw new SedutaFormException("SedutaDataError", "La data seduta inserita non ispetta il formato: gg/mm/aaaa");
    } else {
      if (!(parsDateToString(dataSeduta).matches(Seduta.DATA_SEDUTA_REGEX))) {
        throw new SedutaFormException("SedutaDataError", "La data seduta inserita non ispetta il formato: gg/mm/aaaa");
      } else if (dataSeduta.before(date)) {
        throw new SedutaFormException("SedutaDataError", "La data seduta inserita è minore della data corrente.");
      }
      return dataSeduta;
    }
  }

  /**
   * Controlla che l'indirizzo di una seduta sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param indirizzo String che rappresenta l'indirizzo della seduta
   * @return indirizzo String che rappresenta l'indirizzo della seduta
   * @throws SedutaFormException se l'indirizzo non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#DATA_SEDUTA_REGEX}
   */
  public String validaIndirizzo(String indirizzo) throws SedutaFormException {
    if (indirizzo == null) {
      throw new SedutaFormException("SedutaIndirizzoError", "L’indirizzo inserito non è corretto.");
    } else {
      if (!indirizzo.matches(Seduta.INDIRIZZO_REGEX)) {
        throw new SedutaFormException("SedutaIndirizzoError", "L’indirizzo inserito non è corretto.");
      }
      return indirizzo;
    }
  }

  /**
   * Controlla che la città di una seduta sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param citta String che rappresenta la città della seduta
   * @return citta String che rappresenta la città della seduta
   * @throws SedutaFormException se la citta non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#CITTA_REGEX}
   */
  public String validaCitta(String citta) throws SedutaFormException {
    if (citta == null) {
      throw new SedutaFormException("SedutaCittaError", "La città inserita non è corretta: non ammette caratteri numeri.");
    } else {
      if (!citta.matches(Seduta.CITTA_REGEX)) {
        throw new SedutaFormException("SedutaCittaError", "La città inserita non è corretta: non ammette caratteri numeri.");
      }
      return citta;
    }
  }

  /**
   * Controlla che la provincia di una seduta sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param provincia String che rappresenta la provincia della seduta
   * @return provincia String che provincia la città della seduta
   * @throws SedutaFormException se la provincia non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#PROVINCIA_REGEX}
   */
  public String validaProvincia(String provincia) throws SedutaFormException {
    if (provincia == null) {
      throw new SedutaFormException("SedutaProvinciaError", "La provincia inserita non è corretta: ammette solo due caratteri.");
    } else {
      if (!provincia.matches(Seduta.PROVINCIA_REGEX)) {
        throw new SedutaFormException("SedutaProvinciaError", "La provincia inserita non è corretta: ammette solo due caratteri.");
      }
      return provincia;
    }
  }

  /**
   * Controlla che il CAP di una seduta sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param CAP String che rappresenta il CAP  della seduta
   * @return CAP String che provincia il CAP  della seduta
   * @throws SedutaFormException se la data non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#CAP_REGEX}
   */
  public String validaCAP(String CAP) throws SedutaFormException {
    if (CAP == null) {
      throw new SedutaFormException("SedutaCAPError", "Il CAP inserito non è corretto: ammette solo 5 caratteri numerici.");
    } else {
      if (!CAP.matches(Seduta.CAP_REGEX)) {
        throw new SedutaFormException("SedutaCAPError", "Il CAP inserito non è corretto: ammette solo 5 caratteri numerici.");
      }
      return CAP;
    }
  }

  /**
   * Controlla che il numero dei partecipanti ad una seduta sia specificato e che rispetti il formato
   * prestabilito.
   *
   * @param numeroPartecipanti int che rappresenta il numero dei partecipanti
   * @return numeroPartecipanti int che rappresenta il numero dei partecipanti
   * @throws SedutaFormException se la data non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#NUMERO_PARTECIPANTI_REGEX}
   */
  public int validaNumeroPartecipanti(int numeroPartecipanti) throws SedutaFormException {
    if (numeroPartecipanti < 0) {
      throw new SedutaFormException("SedutaPartecipantiError", "Il numero di Partecipanti inserito non è corretto: il limite massimo è 9999");
    }
    return numeroPartecipanti;
  }

  /**
   * Controlla che la data di inizio di partecipazione ad una seduta sia specificata e che rispetti il formato
   * prestabilito.
   *
   * @param sedutaForm sedutaForm da cui prendere le date da verificare
   * @return dataInizioPrenotazioni Date che rappresenta la data dell'inizio delle prenotazioni
   * @throws SedutaFormException se la data non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#DATA_INIZIO_PARTECIPAZIONE_REGEX}
   */
  public Date validaDataInizioPrenotazioni(SedutaForm sedutaForm) throws SedutaFormException {
    Date date = new Date();
    if (sedutaForm.getDataInizioPrenotazione() == null) {
      throw new SedutaFormException("SedutaDataInizioError", "La data inizio partecipazione inserita non rispetta il formato: gg/mm/aaaa.");
    } else {
      if (!(parsDateToString(sedutaForm.getDataInizioPrenotazione()).matches(Seduta.DATA_INIZIO_PARTECIPAZIONE_REGEX))) {
        throw new SedutaFormException("SedutaDataInizioError", "La data inizio partecipazione inserita non rispetta il formato: gg/mm/aaaa.");
      } else if (sedutaForm.getDataInizioPrenotazione().before(date)) {
        throw new SedutaFormException("SedutaDataInizioError", "La data inizio partecipazione inserita è minore della data corrente");
      } else if (sedutaForm.getDataInizioPrenotazione().after(sedutaForm.getDataSeduta())) {
        throw new SedutaFormException("SedutaDataInizioError", "La data inizio partecipazione inserita è maggiore della data seduta.");
      }

      return sedutaForm.getDataInizioPrenotazione();
    }
  }

  /**
   * Controlla che la data di fine di partecipazione ad una seduta sia specificata e che rispetti il formato
   * prestabilito.
   *
   * @param sedutaForm sedutaForm da cui prendere le date da verificare
   * @return dataInizioPrenotazioni Date che rappresenta la data di fine delle prenotazioni
   * @throws SedutaFormException se la data non è specificata oppure se non
   *                             rispetta il formato
   *                             {@link Seduta#DATA_FINE_PARTECIPAZIONE_REGEX}
   */
  public Date validaDataFinePrenotazioni(SedutaForm sedutaForm) throws SedutaFormException {
    Date date = new Date();
    if (sedutaForm.getDataFinePrenotazione() == null) {
      throw new SedutaFormException("SedutaDataInizioError", "La data fine partecipazione inserita non rispetta il formato: gg/mm/aaaa.");
    } else {
      if (!(parsDateToString(sedutaForm.getDataFinePrenotazione()).matches((Seduta.DATA_FINE_PARTECIPAZIONE_REGEX)))) {
        throw new SedutaFormException("SedutaDataInizioError", "La data fine partecipazione inserita non rispetta il formato: gg/mm/aaaa.");
      } else if (sedutaForm.getDataFinePrenotazione().before(date)) {
        throw new SedutaFormException("SedutaDataInizioError", "La data fine partecipazione inserita è minore della data corrente.");
      } else if (sedutaForm.getDataFinePrenotazione().after(sedutaForm.getDataSeduta())) {
        throw new SedutaFormException("SedutaDataInizioError", "La data fine partecipazione inserita è maggiore della data seduta.");
      } else if (sedutaForm.getDataFinePrenotazione().before(sedutaForm.getDataInizioPrenotazione())) {
        throw new SedutaFormException("SedutaDataInizioError", "La data fine partecipazione inserita è minore della data inizio partecipazione.");
      }
      return sedutaForm.getDataFinePrenotazione();
    }
  }

  public Guest salvaGuest(Guest guest) {
    guestRepository.save(guest);
    return guest;
  }
  public Seduta salvaSeduta(Seduta seduta) {
    sedutaRepository.save(seduta);
    return seduta;
  }

  /**
   * Metodo che fa parsing dalla (Date) date alla Stringa gg-mm-aaaa
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
