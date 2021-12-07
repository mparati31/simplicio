package exception;

/** Questa eccezione viene lanciata quando nell'espressione in input è presente un carattere sconosciuto. */
public class UnknownTokenException extends SimplicioExpressionException {
    /** Istanzia un'eccezione di questo tipo. */
    public UnknownTokenException() {
        super();
    }

    /**
     * Istanzia un'eccezione che è stata causata dalla posizione {@code position} del testo.
     *
     * @param position la posizione che ha causato l'eccezione
     */
    public UnknownTokenException(int position) {
        super(position);
    }

    @Override
    public String getMessage() {
        return "Carattere sconosciuto";
    }
}
