package it.unisa.is.c09.digitaldonation.GestioneSeduteManagement;


import it.unisa.is.c09.digitaldonation.ErroreManagement.OrganizzazioneSeduteError.*;
import it.unisa.is.c09.digitaldonation.Model.Entity.*;
import it.unisa.is.c09.digitaldonation.Model.Repository.*;
import it.unisa.is.c09.digitaldonation.Utils.Forms.IndisponibilitaDonazioneForm;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/*Validare i campi di conferma donazione form e indisponibilita donazione form*/
@Service
public class GestioneSeduteService implements GestioneSeduteServiceInterface{

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
     * Questo metodo permette di registrare una nuova donazione da parte di un donatore e aggiorna il tesserino del donatore.
     *
     * @param codiceFiscaleDonatore Il codice fiscale del donatore
     * @param idSeduta L'id della seduta
     * @param tipoDonazione Il tipo d donazione
     * @return donazione La donazione
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Donazione salvataggioDonazione(String codiceFiscaleDonatore, Long idSeduta, String tipoDonazione) throws CannotSaveDataRepositoryException {
        Donazione donazione = new Donazione();
        Tesserino tesserino;
        List<Seduta> listaSedute;
        Indisponibilita indisponibilita = new Indisponibilita();

        Donatore donatore = donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore);
        Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);

        if (donatore == null){
            throw new CannotSaveDataRepositoryException("donatoreError", "Errore, il donatore è null");
        }
        else if(seduta == null){
            throw new CannotSaveDataRepositoryException("sedutaError", "Errore, la seduta è null");
        }

        else if(sedutaRepository.existsByIdSedutaAndListaDonatore_CodiceFiscaleUtente(seduta.getIdSeduta(), donatore.getCodiceFiscale())){
            donazione = new Donazione(seduta.getDataSeduta(), tipoDonazione);
            donazioneRepository.save(donazione);
            tesserino = tesserinoRepository.findDonatoreBydonatoreUtenteCodiceFiscale(donatore.getCodiceFiscale());
            tesserino.addDonazione(donazione);
            tesserinoRepository.save(tesserino);
        }

        indisponibilita.setCodiceFiscaleDonatore(codiceFiscaleDonatore);
        //La data deve essere calcolata nel segente modo: 5 mesi dopo la data attuale.
        indisponibilita.setDataProssimaDisponibilita(new Date());
        indisponibilita.setMotivazioni("donazione");
        indisponibilitaRepository.save(indisponibilita);

        listaSedute = sedutaRepository.findAll();
        for(Seduta s: listaSedute){
            if(s.getDataSeduta().before(indisponibilita.getDataProssimaDisponibilita())){
                for(int i = 0; i < s.getListaDonatore().size(); i++){
                    if(s.getListaDonatore().get(i).getCodiceFiscale().equals(codiceFiscaleDonatore)){
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
     * Questo metodo permette di registrare una indisponibilità per un donatore.
     *
     * @param codiceFiscaleDonatore è il codice fiscale del donatore
     * @param idSeduta è l'id della seduta
     * @param indisponibilitaDonazioneForm è il form dell'indisponibilità della donazione
     * @return indisponibilita L'indisponiblità
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Indisponibilita salvataggioIndisponibilita(String codiceFiscaleDonatore, Long idSeduta, IndisponibilitaDonazioneForm indisponibilitaDonazioneForm) throws CannotSaveDataRepositoryException{

        List<Seduta> listaSedute;
        Indisponibilita indisponibilita = new Indisponibilita();
        Donatore donatore = donatoreRepository.findDonatoreByCodiceFiscaleUtente(codiceFiscaleDonatore);
        Seduta seduta = sedutaRepository.findByIdSeduta(idSeduta);
        if (donatore== null){
            throw new CannotSaveDataRepositoryException("donatoreError", "Errore, il donatore è null");
        }
        else if(seduta == null){
            throw new CannotSaveDataRepositoryException("sedutaError", "Errore, la seduta è null");
        }

        indisponibilita.setCodiceFiscaleDonatore(codiceFiscaleDonatore);
        indisponibilita.setDataProssimaDisponibilita(indisponibilitaDonazioneForm.getDataProssimaDisponibilita());
        indisponibilita.setMotivazioni(indisponibilitaDonazioneForm.getMotivazioni());
        indisponibilita.setNomeMedico(indisponibilitaDonazioneForm.getNomeMedico());
        indisponibilitaRepository.save(indisponibilita);

        listaSedute = sedutaRepository.findAll();
        for(Seduta s: listaSedute){
            if(s.getDataSeduta().before(indisponibilita.getDataProssimaDisponibilita())){
                for(int i = 0; i < s.getListaDonatore().size(); i++){
                    if(s.getListaDonatore().get(i).getCodiceFiscale().equals(codiceFiscaleDonatore)){
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
     * @throws ConfermaDonazioneFormException se il nome non è specificato oppure se non
     *                            rispetta il formato {@link Donazione#TIPODONAZIONE_REGEX}
     */
    public String validaTipoDonazione(String tipoDonazione) throws ConfermaDonazioneFormException {
        if (tipoDonazione == null) {
            throw new ConfermaDonazioneFormException("TipoDonazioneError", "Il formato del tipo di donazione è errato.");
        } else {
            if (!tipoDonazione.matches(Donazione.TIPODONAZIONE_REGEX)) {
                throw new ConfermaDonazioneFormException("TipoDonazioneError", "Il formato del tipo di donazione è errato.");
            } else {
                return tipoDonazione;
            }
        }
    }

    /**
     * Controlla che le motivazioni dell'indisponibilità per una donazione siano specificate e che rispettino il formato
     * prestabilito.
     *
     * @param motivazioni Stringa che rappresenta le motivazioni da controllare
     * @return motivazioni La stringa che rappresenta le motivazioni da controllare validate
     * @throws IndisponibilitaDonazioneFormException se le motivazioni non sono specificate oppure se non
     *                            rispetta il formato {@link Indisponibilita#MOTIVAZIONI_REGEX}
     */
    public String validaMotivazioni(String motivazioni) throws IndisponibilitaDonazioneFormException {
        if (motivazioni == null) {
            throw new IndisponibilitaDonazioneFormException("IndisponibilitaMotivazioniError", "Il formato delle motivazioni è errato.");
        } else {
            if (!motivazioni.matches(Indisponibilita.MOTIVAZIONI_REGEX)) {
                throw new IndisponibilitaDonazioneFormException("IndisponibilitaMotivazioniError", "Il formato delle motivazioni è errato.");
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
     * @throws IndisponibilitaDonazioneFormException se il nome del medico non è specificato oppure se non
     *                            rispetta il formato {@link Indisponibilita#NOME_MEDICO_REGEX}
     */
    public String validaNomeMedico(String nomeMedico) throws IndisponibilitaDonazioneFormException {
        if (nomeMedico == null) {
            throw new IndisponibilitaDonazioneFormException("GuestNomeMedicoError", "Il formato del nome del medico è errato.");
        } else {
            if (!nomeMedico.matches(Indisponibilita.NOME_MEDICO_REGEX)) {
                throw new IndisponibilitaDonazioneFormException("GuestNomeMedicoError", "Il formato del nome del medico è errato.");
            } else {
                return nomeMedico;
            }
        }
    }

    /**
     * Controlla che la data di prossima disponibilità di partecipazione ad una donazione sia specificata e che rispetti il formato
     * prestabilito.
     *
     * @param dataProssimaDisponibilita data che rappresenta la data da controllare
     * @return dataProssimaDisponibilita Date che rappresenta la data di prossima disponibilità di partecipazione ad una donazione
     * @throws IndisponibilitaDonazioneFormException se la data non è specificata oppure se non
     *                             rispetta il formato
     *                             {@link Indisponibilita#DATA_PROSSIMA_DISPONIBILITA_REGEX}
     */
    public Date validaDataProssimaDisponibilitaDonazione(Date dataProssimaDisponibilita) throws IndisponibilitaDonazioneFormException {
        Date date = new Date();
        if (dataProssimaDisponibilita == null) {
            throw new IndisponibilitaDonazioneFormException("DataProssimaDisponibilitaError", "La data di prossima disponibilità inserita non rispetta il formato: gg/mm/aaaa.");
        } else {
            if (!(parsDateToString(dataProssimaDisponibilita).matches(Indisponibilita.DATA_PROSSIMA_DISPONIBILITA_REGEX))) {
                throw new IndisponibilitaDonazioneFormException("DataProssimaDisponibilitaError", "La data di prossima disponibilità inserita non rispetta il formato: gg/mm/aaaa.");
            } else if (dataProssimaDisponibilita.before(date)) {
                throw new IndisponibilitaDonazioneFormException("DataProssimaDisponibilitaError", "La data di prossima disponibilità inserita è minore della data corrente.");
            }

            return dataProssimaDisponibilita;
        }
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
