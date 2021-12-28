package it.unisa.is.c09.digitaldonation.OrganizzazioneSeduteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Guest;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;

import java.util.List;

public interface OrganizzazioneSeduteServiceInterface {

    public void feedbackDonatore(Long idDonatore, boolean feedback);

    public List<Object> monitoraggioSeduta(Long idSeduta);

    public Guest inserimentoGuest(Long idSeduta, Guest guest);

    public Seduta SchedulazioneSeduta(Seduta seduta);

    public Seduta modificaSeduta(Long idSeduta);

    public void eliminaSeduta(Long idSeduta);

    public Seduta visualizzaSeduta(Long idSeduta);

    public List<Seduta> visualizzaElencoSedute();

}
