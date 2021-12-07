package data.operation;

import data.Number;
import exception.IrrationalNumberException;
import exception.OverflowException;
import exception.SimplicioArithmeticException;

/** Rappresenta una potenza. */
public class Pow extends BinaryOperation {

    /**
     * Restituisce l'elevamento a potenza dei valori presenti in {@code operands}.
     * <p>
     * Più formalmente, restituisce {@code operands[0] ^ operands[1]}.
     *
     * @param operands gli operandi dell'operazione
     * @return l'elevamento a potenza dei valori presenti in {@code operands}
     * @throws OverflowException         se si verifica un overflow
     * @throws IrrationalNumberException se nel calcolare il risultato un risultato intermedio oppure il risultato
     *                                   finale è un numero irrazionale
     */
    @Override
    public Number calculate(Number[] operands) {
        try {
            return operands[0].pow(operands[1]);
        } catch (SimplicioArithmeticException e) {
            String[] latexOperands = new String[operands.length];
            for (int i = 0; i < operands.length; i++) latexOperands[i] = operands[i].getLatex(new String[]{});
            e.setExpr(this.getLatex(latexOperands));
            throw e;
        }
    }

    @Override
    public int getPriority() {
        return 5;
    }

    @Override
    public String getLatex(String[] s) {
        if (s.length != 2) throw new IllegalArgumentException();
        return "{" + s[0] + "}^{" + s[1] + "}";
    }

    @Override
    public String toString() {
        return "POW";
    }
}
