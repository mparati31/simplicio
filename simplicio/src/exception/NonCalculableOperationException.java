package exception;

/**
 * Questa eccezione viene lanciata quando si tenta di risolvere un'operazione che non può essere risolta,
 * come per esempio una radice pari con argomento negativo.
 */
public class NonCalculableOperationException extends SimplicioArithmeticException {
    /** Istanzia un'eccezione di questo tipo. */
    public NonCalculableOperationException() {
        super();
    }

    /**
     * Istanzia un eccezione che è stata causata da {@code expr}.
     *
     * @param expr latex dell'espressione che ha causato l'eccezione
     */
    public NonCalculableOperationException(String expr) {
        super(expr);
    }

    @Override
    public String getMessage() {
        return "L'operazione " + getExpr() + " non può essere calcolata";
    }
}
