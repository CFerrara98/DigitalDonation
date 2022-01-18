package it.unisa.is.c09.digitaldonation.gestionesedutemanagement.web.filters;


import org.jboss.logging.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Classe che implementa la configurazione di Spring per la sicurezza.
 *
 * @author Elpidio Mazza
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private Logger logger = Logger.getLogger(String.valueOf(WebSecurityConfig.class));

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/", "/home", "/logout", "/error").permitAll();
  }

}