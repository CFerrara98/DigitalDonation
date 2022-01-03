package it.unisa.is.c09.digitaldonation.Utils.Forms;

public class CambioPasswordForm {

    public CambioPasswordForm(String email, String password, String confermaPassword) {
        this.email = email;
        this.password = password;
        this.confermaPassword = confermaPassword;
    }

    /**
     * Metodo che ritorna la mail per il cambio password.
     *
     * @return email e' la mail per il cambio password;
     */
    public String getEmail() {
        return email;
    }
    /**
     * Metodo che setta la mail per il cambio password.
     *
     * @param email e' la mail per il cambio password.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo che ritorna la password per il cambio password.
     *
     * @return email e' la password per il cambio password;
     */
    public String getPassword() {
        return password;
    }
    /**
     * Metodo che setta la password per il cambio password.
     *
     * @param password e' la password per il cambio password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Metodo che ritorna il conferma password per il cambio della password.
     *
     * @return confermaPassword e' la conferma password per il cambio della password.
     */
    public String getConfermaPassword() {
        return confermaPassword;
    }
    /**
     * Metodo che setta il conferma password per il cambio della password.
     *
     * @param confermaPassword e' il conferma password per il cambio della password.
     */
    public void setConfermaPassword(String confermaPassword) {
        this.confermaPassword = confermaPassword;
    }

    private String email;
    private String password;
    private String confermaPassword;
}
