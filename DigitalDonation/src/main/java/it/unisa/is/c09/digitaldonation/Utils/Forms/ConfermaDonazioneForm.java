package it.unisa.is.c09.digitaldonation.Utils.Forms;

public class ConfermaDonazioneForm {

    public ConfermaDonazioneForm(String tipoDonazione) {
        this.tipoDonazione = tipoDonazione;
    }

    /**
     * Metodo che ritorna il tipo di donazione per la conferma della donazione.
     *
     * @return tipoDonazione e' il tipo di donazione per la conferma della donazione.
     */
    public String getTipoDonazione() {
        return tipoDonazione;
    }
    /**
     * Metodo che setta il tipo di donazione per la conferma della donazione.
     *
     * @param tipoDonazione è il tipo di donazione per la conferma della donazione.
     */
    public void setTipoDonazione(String tipoDonazione) {
        this.tipoDonazione = tipoDonazione;
    }

    private String tipoDonazione;
}
