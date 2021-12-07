package exception;

/** Questa eccezione viene lanciata quando è presente un errore di sintassi nell'espressione in input. */
public class SyntaxErrorException extends SimplicioExpressionException {
    /** Istanzia un'eccezione di questo tipo. */
    public SyntaxErrorException() {
        super();
    }

    /**
     * Istanzia un'eccezione che è stata causata dalla posizione {@code position} del testo.
     *
     * @param position la posizione che ha causato l'eccezione
     */
    public SyntaxErrorException(int position) {
        super(position);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Nel caso in cui la posizione è oltre l'ultimo elemento della stringa, significa che sono attesi altri
     * caratteri oltre a quelli inseriti.
     *
     * @return la posizione della stringa in cui è capitato l'errore
     */
    @Override
    public int getPosition() {
        return super.getPosition();
    }

    @Override
    public String getMessage() {
        return "L'espressione non è ben formata.";
    }
}
