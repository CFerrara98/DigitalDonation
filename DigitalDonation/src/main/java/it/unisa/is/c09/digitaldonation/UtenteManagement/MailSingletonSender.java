package it.unisa.is.c09.digitaldonation.UtenteManagement;

import it.unisa.is.c09.digitaldonation.Utils.Forms.SedutaForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

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
     * Metodo che permette di inviare una email
     *
     * @param object       Object che rappresenta l'oggetto coinvolto ai cambiamenti
     * @param destinatario String che rappresenta l'email del destinatario
     */
    public void sendEmail(Object object, String destinatario){
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(destinatario);

        msg.setSubject("Piattaforma Digital Donation");
        String messaggio = messaggio(object);
        msg.setText(messaggio);

        javaMailSender.send(msg);
    }

    /**
     * Metodo che ritorna la stringa contenente il messaggio informativo destinato
     * all'utente
     *
     * @param object Object che rappresenta l'oggetto coinvolto ai cambiamenti
     * @return String contenente il messaggio
     */
    private String messaggio(Object object){
        if(object instanceof SedutaForm){
            SedutaForm sedutaForm = (SedutaForm) object;

        }
        return "ciao";
    }
}


