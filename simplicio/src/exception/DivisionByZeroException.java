package exception;

/** Questa eccezione viene lanciata quando di verifica una divisione per zero. */
public class DivisionByZeroException extends SimplicioArithmeticException {
    /** Istanzia un'eccezione di questo tipo. */
    public DivisionByZeroException() {
        super();
    }

    /**
     * Istanzia un eccezione che è stata causata da {@code expr}.
     *
     * @param expr latex dell'espressione che ha causato l'eccezione
     */
    public DivisionByZeroException(String expr) {
        super(expr);
    }

    @Override
    public String getMessage() {
        return "Divisione per zero: $" + getExpr() + "$";
    }
}
