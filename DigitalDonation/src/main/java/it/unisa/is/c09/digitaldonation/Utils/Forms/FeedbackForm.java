package it.unisa.is.c09.digitaldonation.Utils.Forms;

public class FeedbackForm {
    public FeedbackForm(boolean feedback){
        this.feedback = feedback;
    }

    public FeedbackForm(){}

    /**
     * Metodo che ritorna il feedback del donatore.
     *
     * @return codiceFiscale e' il feedback del donatore.
     */
    public boolean isFeedback() {
        return feedback;
    }

    /**
     * Metodo che setta il feedback del donatore.
     *
     * @param feedback è il feedback del donatore.
     */
    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }

    private boolean feedback;
}
