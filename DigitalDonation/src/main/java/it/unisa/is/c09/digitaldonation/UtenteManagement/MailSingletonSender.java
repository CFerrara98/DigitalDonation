package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.Model.Entity.Donatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Seduta;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

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

    /**
     * Metodo che permette di inviare un email a tutti i donatori disponibili quando una seduta viene schedulata.
     *
     * @param donatore Oggetto donatore disponibile.
     * @param seduta   Oggetto seduta a cui il donatore si è prenotato.
     */
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

    public String sendEmailCreazioneAccount(Donatore donatore){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(donatore.getEmail());
        msg.setSubject("Account creato");
        String lettere = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        String numeri = "0123456789";
        String speciali = "@!#$%&";
        String pwdLettere = RandomStringUtils.random(5, lettere);
        String pwdNumeri = RandomStringUtils.random(2, numeri);
        String pwdSpeciali = RandomStringUtils.random(1, speciali);
        String password = pwdLettere+pwdNumeri+pwdSpeciali;

        String messaggio = ("Gentile "+donatore.getNome()+" "+donatore.getCognome()+",\n" +
                "le comunichiamo che il suo account sulla piattaforma Digital Donation è stato creato, per accedere alla piattaforma inserisca le credenziali qui sotto\n" +
                "email: "+donatore.getEmail()+"\npassword: "+password+"\n\nCordiali saluti");
        msg.setText(messaggio);
        javaMailSender.send(msg);
        return password;
    }
}

