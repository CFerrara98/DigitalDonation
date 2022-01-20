package it.unisa.is.c09.digitaldonation;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Classe che definisce il caricamento delle configurazioni nel caso di build
 * war non eseguibile. Garantisce il corretto funzionamento dell'applicazione
 * anche per il tradizionale deploy su applications server esterni.
 *
 * @author Kevin Pacifico
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

  /**
   * Metodo per il caricamento delle configurazioni. Visto l'utilizzo di Spring
   * Boot, non è necessario che il
   * programmatore vi apporti modifiche.
   */
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(DigitalDonationApplication.class);
  }
}
