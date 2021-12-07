package exception;

/**
 * Rappresenta un'eccezione data da un errore aritmetico.
 * <p>
 * In eccezioni di questo tipo è possibile memorizzare tramite il costruttore
 * {@link SimplicioArithmeticException#SimplicioArithmeticException(String)} oppure il metodo
 * {@link SimplicioArithmeticException#setExpr(String)} la rappresentazione latex dell'espressione che ha
 * causato l'errore aritmetico, che verrà poi utilizzato per produrre il messaggio di errore.
 */
public abstract class SimplicioArithmeticException extends SimplicioException {
    // Rappresentazione latex della parte di espressione che ha causato l'eccezione.
    private String expr;

    /** Istanzia un'eccezione di questo tipo. */
    public SimplicioArithmeticException() {
        super();
    }

    /**
     * Istanzia un eccezione che è stata causata da {@code expr}.
     *
     * @param expr latex dell'espressione che ha causato l'eccezione
     */
    public SimplicioArithmeticException(String expr) {
        this.expr = expr;
    }

    /**
     * Restituisce la rappresentazione latex dell'espressione che ha causato l'errore aritmetico.
     *
     * @return la rappresentazione latex dell'espressione che ha causato l'errore aritmetico
     */
    protected String getExpr() {
        return expr;
    }

    /**
     * Setta la rappresentazione latex dell'espressione che ha causato l'errore aritmetico.
     *
     * @param expr latex dell'espressione che ha causato l'eccezione
     */
    public void setExpr(String expr) {
        this.expr = expr;
    }
}
