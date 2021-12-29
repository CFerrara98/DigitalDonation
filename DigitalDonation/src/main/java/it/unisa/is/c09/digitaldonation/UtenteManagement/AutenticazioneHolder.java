package it.unisa.is.c09.digitaldonation.UtenteManagement;

    /**
     * Classe utilizzata per tenere traccia dell'utente autenticato all'interno del
     * sistema. In pratica, tramite questa classe è possibile specificare l'email
     * dell'utente autenticato in maniera da renderlo visibile a livello di thread.
     *
     * @author Mattia Sapere, Fabio Siepe
     */
    public class AutenticazioneHolder {

        private static ThreadLocal<String> utenteThreadLocal = new ThreadLocal<String>();

        /**
         * Permette di aggiungere la mail dell'utente autenticato in sessione al fine di
         * renderlo visibile a tutti i livelli.
         *
         * @param email Stringa che rappresenta l'email dell'utente autenticato nel
         *              sistema
         */
        static void setUtente(String email) {
            if (email != null) {
                utenteThreadLocal.set(email);
            } else {
                utenteThreadLocal.remove();
            }
        }

        /**
         * Permette di ottenere l'email dell'utente autenticato nel sistema.
         *
         * @return La stringa che rappresenta l'email dell'utente autenticato nel
         *         sistema, null se non vi è alcun utente in sessione
         */
        static String getUtente() {
            return utenteThreadLocal.get();
        }

    }
