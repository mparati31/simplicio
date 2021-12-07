package data.operation;

import data.Number;
import exception.DivisionByZeroException;
import exception.SimplicioArithmeticException;

/** Rappresenta una frazione. */
public class Fraction extends BinaryOperation {

    /**
     *  Restituisce il rapporto tra i valori presenti in {@code operands}.
     *  <p>
     *  Più formalmente, restituisce il risultato di {@code operands[0] / operands[1]}.
     *
     * @param operands gli operandi dell'operazione
     * @return il rapporto tra i valori presenti in {@code operands}
     * @throws DivisionByZeroException se si verifica una divisione per zero, quindi se {@code operands[1]}
     *                                 vale zero
     */
    @Override
    public Number calculate(Number[] operands) {
        try {
            return operands[0].div(operands[1]);
        } catch (SimplicioArithmeticException e) {
            String[] latexOperands = new String[operands.length];
            for (int i = 0; i < operands.length; i++) latexOperands[i] = operands[i].getLatex(new String[]{});
            e.setExpr(this.getLatex(latexOperands));
            throw e;
        }
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public boolean solveInorder() {
        return true;
    }

    @Override
    public String getLatex(String[] s) {
        if (s.length != 2) throw new IllegalArgumentException();
        return "\\frac{" + s[0] + "}{" + s[1] + "}";
    }

    @Override
    public String toString() {
        return "FRACTION";
    }
}
