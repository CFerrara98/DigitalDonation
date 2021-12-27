package it.unisa.is.c09.digitaldonation.web;

import it.unisa.is.c09.digitaldonation.UtenteManagement.UtenteService;
import it.unisa.is.c09.digitaldonation.Utils.Forms.LoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Interceptor che si occupa di trasferire l'email dell'utente autenticato dalla
 *
 * @author Mattia Sapere
 */
public class AutenticazioneInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UtenteService utenzaService;

    /**
     * Eseguito prima dell'esecuzione di un controller, determina se l'utente è in
     * sessione, quindi istanzia un oggetto contenente
     * le informazioni circa l'utente autenticato nel sistema (null altrimenti).
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Ottieni la sessione del server
        HttpSession session = request.getSession();

        // Ottieni l'email dell'utente autenticato e propaga l'informazione anche a
        // livello più basso
        String email = (String) session.getAttribute("email");

        if (email != null) {
            System.out.println("Interceptor: " + email);
            utenzaService.setUtenteAutenticato(email);
        }

        return true;
    }

    /**
     * Metodo eseguito dopo l'esecuzione di un controller ma prima del rendering
     * della vista.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // Fornisci ad ogni vista delegata un form per il login e l'oggetto utente che
        // rappresenta
        // l'utente autenticato
        request.setAttribute("loginForm", new LoginForm());
        request.setAttribute("utente", utenzaService.getUtenteAutenticato());
    }

}
