package exception;

/**
 * Rappresenta un'eccezione data da un errore sulla composizione del testo dell'espressione.
 * <p>
 * Più in generale, sono tutti quegli errori che capitano nel lexer o nel parser.
 * <p>
 * In eccezioni di questo tipo è possibile memorizzare tramite il costruttore
 * {@link SimplicioExpressionException#SimplicioExpressionException(int)} oppure il metodo
 * {@link SimplicioExpressionException#setPosition(int)} la posizione all'interno della stringa in cui
 * è capitato l'errore.
 **/
public abstract class SimplicioExpressionException extends SimplicioException {
    // Posizione del testo in cui è presente l'errore
    private int position;

    /** Istanzia un'eccezione di questo tipo. */
    public SimplicioExpressionException() {
        super();
    }

    /**
     * Istanzia un'eccezione che è stata causata dalla posizione {@code position} del testo.
     *
     * @param position la posizione che ha causato l'eccezione
     */
    public SimplicioExpressionException(int position) {
        super();
        this.position = position;
    }

    /**
     * Restituisce la posizione della stringa in cui è capitato l'errore.
     *
     * @return la posizione della stringa in cui è capitato l'errore
     */
    public int getPosition() {
        return position;
    }

    /**
     * Setta la posizione all'interno della stringa in cui è capitato l'errore.
     *
     * @param position la posizione che ha causato l'eccezione
     */
    public void setPosition(int position) {
        this.position = position;
    }
}
