package it.unisa.is.c09.digitaldonation.utilRand;

import it.unisa.is.c09.digitaldonation.Model.Entity.*;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.Random;

import static it.unisa.is.c09.digitaldonation.utilRand.RandChoiser.*;
import static it.unisa.is.c09.digitaldonation.utilRand.RandChoiser.generateDataInCuiAvveraLaSeduta;

public class BuildRandEntity {
    private static final int LUNGHEZZA_PW =12;
    private static final String END_IMG =".png";


    /**
     * MEtodo che crea un donatore con tesserino
     * @return Donatore senza donazioni e indisponibilita (vuote non null)!
     */
    public static Donatore createFullDonatore(){
        Donatore donatore = createRandDonatore();
        Tesserino tesserino = createRandTesserino();
        donatore.setTesserino(tesserino);
        return donatore;
    }

    /**
     * Metodo che crea una Donazione con campi randomizzati Attenzione Tesserino è null
     * @return Donazione con campi randomizzati, Tesserino è null
     */
    public static Donazione createRandDonazione(){
        Long idDonazione = new Long(randBetween(800,80000));
        Tesserino tesserino = null;
        Date dataDonazione = new Date();
        String tipoDonazione = generateNomeOCognome();

        Donazione donazione = new Donazione();
        donazione.setIdDonazione(idDonazione);
        donazione.setTesserino(null);
        donazione.setDataDonazione(dataDonazione);
        donazione.setTipoDonazione(tipoDonazione);

        return donazione;
    }

    /**
     * Metodo che crea una indisponibilità randomizzando i campi
     * Attenzione! Donatore è null
     * @return Indisponibilita con campi casuali, Donatore è null
     */
    public static Indisponibilita createRandIndisponbilita(){
        Long idIndisponibilita = new Long(randBetween(800,80000));
        Donatore donatore = null;
        Date dataProssimaDisponibilita = generateFutureDate(new Date());
        String motivazioni = generateNomeOCognome();
        String nomeMedico = generateNomeOCognome()+" "+generateNomeOCognome();

        Indisponibilita indisponibilita = new Indisponibilita();
        indisponibilita.setIdIndisponibilita(idIndisponibilita);
        indisponibilita.setDataProssimaDisponibilita(dataProssimaDisponibilita);
        indisponibilita.setMotivazioni(motivazioni);
        indisponibilita.setNomeMedico(nomeMedico);
        indisponibilita.setDonatore(null);

        return indisponibilita;
    }

    /**
     * Metodo che crea una seduta Attenzione SedeLocale è null
     * @return Seduta con campi randomizzati, SedeLocale è null
     */
    public static Seduta createRandSeduta(){
        Long idSeduta = new Long(randBetween(800,80000));
        Date date = new Date();
        String luogo = generateIndirizzo();
        Time oraInizio = getThisTime(randBetween(0,24),randBetween(0,59));
        Time oraFine = getThisTime(randBetween(0,24),randBetween(0,59));
        int numeroPartecipanti = 0;
        Date dataInizioPrenotazione = null;
        Date dataFinePrenotazione = null;
        try {
            dataInizioPrenotazione = parsStringToDate(generateDataInCuiAvveraLaSeduta());
            dataFinePrenotazione = generateFutureDate(dataInizioPrenotazione);
        }catch (ParseException e){
            e.printStackTrace();
        }
        Seduta seduta = new Seduta();
        seduta.setIdSeduta(idSeduta);
        seduta.setDataSeduta(date);
        seduta.setLuogo(luogo);
        seduta.setOraInizio(oraInizio);
        seduta.setOraFine(oraFine);
        seduta.setNumeroPartecipanti(numeroPartecipanti);
        seduta.setDataInizioPrenotazione(dataInizioPrenotazione);
        seduta.setDataFinePrenotazione(dataFinePrenotazione);
        seduta.setSedeLocale(null);

        return seduta;
    }

    /**
     * Metodo che crea un Tesserino con campi casuali
     * @return un tesserino con parametri casuali
     */
    public static Tesserino createRandTesserino(){
        Random random = new Random();
        Long idTessera = new Long(randBetween(800,80000));
        int numeroMatricola = Math.toIntExact(new Long(randBetween(800, 8000)));
        String donatoreUtenteCodiceFiscale = generateCoidceFiscale();
        Date dataRilascio = null;
        try {
            dataRilascio = parsStringToDate(generateDataDiNascita());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String gruppoSanguigno = generateGruppoSanguigno();
        String rh = generateRH();
        String imgSource = generateNomeOCognome()+END_IMG;

        Tesserino tesserino = new Tesserino();
        tesserino.setIdTessera(idTessera);
        tesserino.setNumeroMatricola(numeroMatricola);
        tesserino.setDonatoreUtenteCodiceFiscale(donatoreUtenteCodiceFiscale);
        tesserino.setDataRilascio(dataRilascio);
        tesserino.setGruppoSanguigno(gruppoSanguigno);
        tesserino.setRh(rh);
        tesserino.setImgSource(imgSource);
        return tesserino;
    }

    /**
     * Metodo che crea un Operatore Attenzione! SedeLocale è null
     * @return Donatore con tutti i campi eccetto SedeLocale che è null
     */
    public static Operatore createRandOperatore() {
        Random random = new Random();
        String codiceFiscaleUtente = generateCoidceFiscale();
        String nome = generateNomeOCognome();
        String cognome = generateNomeOCognome();
        String email = generateRandomEmail();
        String password = generateRandomPassword(LUNGHEZZA_PW);

        Operatore operatore = new Operatore();
        operatore.setCodiceFiscale(codiceFiscaleUtente);
        operatore.setCognome(cognome);
        operatore.setNome(nome);
        operatore.setEmail(email);
        operatore.setPassword(password);
        return operatore;
    }

    /**
     * Metodo che crea un donatore Attenzione! Tesserino è null
     * @return Donatore con tutti i campi eccetto Tesserino che è null
     */
    public static Donatore createRandDonatore() {
        Random random = new Random();
        String codiceFiscaleUtente = generateCoidceFiscale();
        String nome = generateNomeOCognome();
        String cognome = generateNomeOCognome();
        String email = generateRandomEmail();
        String password = generateRandomPassword(LUNGHEZZA_PW);

        String residenza = generateCitta();
        Date dataDiNascita = null;
        try {
            dataDiNascita = parsStringToDate(generateDataDiNascita());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String luogoDiNascita = generateLuogoDiNascita();

        Donatore donatore = new Donatore();
        donatore.setCodiceFiscale(codiceFiscaleUtente);
        donatore.setNome(nome);
        donatore.setCognome(cognome);
        donatore.setEmail(email);
        donatore.setPassword(password);
        donatore.setResidenza(residenza);
        donatore.setDataDiNascita(dataDiNascita);
        donatore.setLuogoDiNascita(luogoDiNascita);
        return donatore;
    }

    /**
     * Metodo che crea una sede locale con attributi randomizzati
     * @return SedeLocale con attributi randomizzati
     */
    public static SedeLocale createRandSedeLocale(){
        Random random = new Random();

        SedeLocale sedeLocale = new SedeLocale();
        sedeLocale.setVia(generateIndirizzo());
        sedeLocale.setCodiceIdentificativo(new Long(randBetween(800,80000)));
        return sedeLocale;
    }

    /**
     * Metodo che crea un guest con attributi random (gli attributi rispettano i validatori)
     * @return Guest con attributi randomizzati
     */
    public static Guest createRandGuest(){
        Random random = new Random();

        Guest newGuest = new Guest();
        newGuest.setcodiceFiscaleGueste(generateCoidceFiscale());
        newGuest.setNome(generateNomeOCognome());
        newGuest.setCognome(generateNomeOCognome());
        newGuest.setTelefono(generateNumeroDiTelefono());
        if(random.nextBoolean())
            newGuest.setPatologie(generatePatologie());
        else
            newGuest.setPatologie("//");
        newGuest.setGruppoSanguigno(generateGruppoSanguigno());
        return newGuest;
    }

}
