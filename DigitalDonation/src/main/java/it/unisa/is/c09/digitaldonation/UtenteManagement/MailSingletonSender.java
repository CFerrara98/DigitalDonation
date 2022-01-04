package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Classe singleton che permette di inviare una email informativa all'utente
 * quando si è verificato un cambiamento di stato.
 *
 * @author Kevin Pacifico
 */
@Component
@Scope("singleton")
public class MailSingletonSender {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Metodo che permette di inviare un email per avvisare un donatore che si è prenotato.
     *
     * @param donatore Oggetto donatore che si è prenotato.
     * @param seduta   Oggetto seduta a cui il donatore si è prenotato.
     */
    public void sendEmailPrenotazione(Donatore donatore, Seduta seduta) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(donatore.getEmail());
        msg.setSubject("Prenotazione ad una nuova seduta di donazione");
        String messaggio = ("Gentile " + donatore.getNome() + " " + donatore.getCognome() + " utente delle piattaforma Digital Donation,\n" +
                "le comunichiamo che la prenotazione alla seduta di donazione in via: " + seduta.getLuogo() + " in data: " +
                seduta.getDataSeduta() + " dalle ore: " + seduta.getOraInizio() + " alle ore: " + seduta.getOraFine() +
                "\nè avvenuta con successo, la attendiamo.\nCordiali saluti da Digital Donation");

        msg.setText(messaggio);
        javaMailSender.send(msg);
    }

    public void sendEmailSchedulazioneSeduta(Seduta seduta, Donatore donatore) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(donatore.getEmail());
        msg.setSubject("Nuova seduta disponibile");
        String messaggio = ("Gentile " + donatore.getNome() + " " + donatore.getCognome() + " utente delle piattaforma Digital Donation,\n" +
                "le comunichiamo che è disponibile una nuova seduta di donazione in via: " + seduta.getLuogo() + " in data: " +
                seduta.getDataSeduta() + " dalle ore: " + seduta.getOraInizio() + " alle ore: " + seduta.getOraFine() +
                "\nse intende prenotarsi esegua l'apposita procedura sulla piattaforma.\nCordiali saluti da Digital Donation");
        msg.setText(messaggio);
        javaMailSender.send(msg);
    }

    /*public void spammaCarmine(){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("abatefrancesco98@gmail.com");
        msg.setSubject("Piattaforma Digital Donation");
        String messaggio = "\nGentile Francesco Abate,\n siamo della piattaforma di Digital Donation, a seguito della sua richiesta di utilizzare Node.js " +
                "volevamo informarla che le tecnologie di back-end sono cambiare.\nLA ASPETTIAMO SULLA NOSTRA PIATTAFORMA\nCordiali Saluti, Digital Donation";
        msg.setText(messaggio);
        for(int i=0; i<75; i++){
            javaMailSender.send(msg);
        }
    }*/
}

