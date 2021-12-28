package it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface OrganizzazioneSeduteServiceInterface {

    public void feedbackDonatore(Donatore donatore, boolean feedback,Long idSeduta) throws CannotRelaseFeedbackException;

    public Seduta monitoraggioSeduta(Long idSeduta) throws CannotLoadDataRepositoryException;

    public Guest inserimentoGuest(Long idSeduta, Guest guest) throws CannotSaveDataRepositoryException;

    public Seduta SchedulazioneSeduta(Seduta seduta) throws CannotSaveDataRepositoryException;

    public Seduta modificaSeduta(Seduta seduta, Long idSeduta) throws CannotUpdateDataRepositoryException;

    public void eliminaSeduta(Long idSeduta) throws CannotDeleteDataRepositoryException;

    public Seduta visualizzaSeduta(Long idSeduta) throws CannotLoadDataRepositoryException;

    public List<Seduta> visualizzaElencoSedute() throws CannotLoadDataRepositoryException;

    public String validaNome(String nome) throws GuestFormException;

    public String validaCognome(String cognome) throws GuestFormException;

    public String validaTelefono(String telefono) throws GuestFormException;

    public String validaCodiceFiscaleGuest(String codiceFiscale) throws GuestFormException;

    public String validaPatologie(String patologie) throws GuestFormException;

    public String validaGruppoSanguigno(String gruppoSanguigno) throws GuestFormException;

    public Date validaDataSeduta(Date dataSeduta) throws SedutaFormException;

    public String validaIndirizzo(String indirizzo) throws SedutaFormException;

    public String validaCitta(String citta) throws SedutaFormException;

    public String validaProvincia(String provincia) throws SedutaFormException;

    public String validaCAP(String CAP) throws SedutaFormException;

    public int validaNumeroPartecipanti(int numeroPartecipanti) throws SedutaFormException;

    public Date validaDataInizioPrenotazioni(Date dataInizioPrenotazioni) throws SedutaFormException;

    public Date validaDataFinePrenotazioni(Date dataFinePrenotazioni) throws SedutaFormException;

}
