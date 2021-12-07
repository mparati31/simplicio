package data;

/**
 * Rappresenta un nodo di errore.
 * <p>
 * Se un nodo di questo tipo viene visitato viene lanciata una {@link IllegalCallerException}.
 **/
public class ErrorNode implements AbstractSyntaxTreeNodeValue {
    @Override
    public boolean isErrorNode() {
        return true;
    }

    /**
     * Viene lanciata una {@link IllegalCallerException}
     *
     * @param operands operandi usati per calcolare il valore del nodo
     * @return -
     * @throws IllegalCallerException sempre
     */
    @Override
    public Number getValue(Number[] operands) {
        throw new IllegalCallerException();
    }

    @Override
    public int getPriority() {
        // Valore fittizio
        return -1;
    }

    @Override
    public String getLatex(String[] s) {
        return "\\textbf{Error}";
    }
}
