package it.unisa.is.c09.digitaldonation.utils.form;

/**
 * Classe che rappresenta l'oggetto Form di login.
 *
 * @author Fabio Siepe, Mattia Sapere
 */
public class LoginForm {

    /**
     * Costruttore che crea un oggeto LoginForm vuoto,
     * che verra' popolato con i metodi setters.
     */
    public LoginForm() {}

    /**
     * Costruttore di un form di login con parametri utili nei casi di test.
     * @param email e' l'email inserita nel form.
     * @param password e' la password inserita nel form.
     */
    public LoginForm(String email, String password) {
        this.email=email.toLowerCase();
        this.password=password;
    }

    /**
     * Metodo che ritorna l'email dell'utente.
     * @return email e' l'email dell'utente.
     */
    public String getEmail() {
        return email;
    }
    /**
     * Metodo che setta l'email dell'utente.
     * @param email e' l'email dell'utente.
     */
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    /**
     * Metodo che ritorna la password dell'utente.
     * @return password e' la password dell'utente.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Metodo che setta la password dell'utente.
     * @param password e' la password dell'utente.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    private String email;
    private String password;
}
