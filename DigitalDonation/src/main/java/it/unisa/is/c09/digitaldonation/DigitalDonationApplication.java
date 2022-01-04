package it.unisa.is.c09.digitaldonation;

import it.unisa.is.c09.digitaldonation.UtenteManagement.MailSingletonSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class DigitalDonationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigitalDonationApplication.class, args);}
}
