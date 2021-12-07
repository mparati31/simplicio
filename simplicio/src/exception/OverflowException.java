package exception;

/** Questa eccezione viene lanciata quando si verifica un overflow. */
public class OverflowException extends SimplicioArithmeticException {
    /** Istanzia un'eccezione di questo tipo. */
    public OverflowException() {
        super();
    }

    /**
     * Istanzia un eccezione che è stata causata da {@code expr}.
     *
     * @param expr latex dell'espressione che ha causato l'eccezione
     */
    public OverflowException(String expr) {
        super(expr);
    }

    @Override
    public String getMessage() {
        return "L'operazione $" + getExpr() + "$ genera overflow.";
    }
}
