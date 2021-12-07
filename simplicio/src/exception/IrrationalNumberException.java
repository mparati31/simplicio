package exception;

/** Questa eccezione viene lanciata quando ci si trova davanti ad un numero irrazionale. */
public class IrrationalNumberException extends SimplicioArithmeticException {
    /** Istanzia un'eccezione di questo tipo. */
    public IrrationalNumberException() {
        super();
    }

    /**
     * Istanzia un eccezione che è stata causata da {@code expr}.
     *
     * @param expr latex dell'espressione che ha causato l'eccezione
     */
    public IrrationalNumberException(String expr) {
        super(expr);
    }

    @Override
    public String getMessage() {
        return "L'operazione $" + getExpr() + "$ genera un numero irrazionale.";
    }
}
