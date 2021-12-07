package data.operation;

import data.Number;

/** Rappresenta un segno negativo. */
public class NegativeSign extends UnaryOperation {

    /**
     * Restituisce il valore presente in {@code operands} cambiato di segno.
     * <p>
     * Più formalmente, restituisce {@code -operands[0]}.
     *
     * @param operands gli operandi dell'operazione
     * @return il valore presente in {@code operands} cambiato di segno
     */
    @Override
    public Number calculate(Number[] operands) {
        return operands[0].opposite();
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public String getLatex(String[] s) {
        if (s.length != 1) throw new IllegalArgumentException();
        return "-" + s[0];
    }

    @Override
    public String toString() {
        return "-";
    }
}
