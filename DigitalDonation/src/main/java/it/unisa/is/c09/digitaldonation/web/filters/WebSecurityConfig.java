package it.unisa.is.c09.digitaldonation.web.filters;


import it.unisa.is.c09.digitaldonation.Model.Entity.Operatore;
import it.unisa.is.c09.digitaldonation.Model.Entity.Utente;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
                .antMatchers("/", "/home", "/logout","/error").permitAll();
//                .anyRequest()
//                .and()
//                .formLogin()
//                .loginPage("/goLogin")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll()
//                .and()
//                .httpBasic();
    }

}