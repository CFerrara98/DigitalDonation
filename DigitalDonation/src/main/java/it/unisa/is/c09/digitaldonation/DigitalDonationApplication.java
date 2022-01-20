package it.unisa.is.c09.digitaldonation;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

/**
 * Classe che definisce il caricamento delle configurazioni nel caso di build
 * war non eseguibile. Garantisce il corretto funzionamento dell'applicazione
 * anche per il tradizionale deploy su applications server esterni.
 *
 * @author Kevin Pacifico
 */
@SpringBootApplication
public class DigitalDonationApplication {

  /**
   * Metodo necessario a Spring Boot per l'avvio dell'applicazione nel caso in cui
   * si costruisca un jar/war eseguibile con server embedded.
   *
   * @param args classici parametri di inizializzazione
   */
  public static void main(String[] args) {
    SpringApplication.run(DigitalDonationApplication.class, args);
  }

  /**
   * Metodo per la configurazione delle properties.
   */
  @Bean
  @Primary
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer =
            new PropertySourcesPlaceholderConfigurer();
    YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
    yaml.setResources(new ClassPathResource("application.yml"));
    propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
    return propertySourcesPlaceholderConfigurer;
  }
}
