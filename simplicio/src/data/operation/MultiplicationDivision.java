package data.operation;

import antlr.MathExpressionLexer;
import antlr.MathExpressionParser;
import data.Number;
import exception.SimplicioArithmeticException;

import java.util.Iterator;

/** Rappresenta un'operazione composta da moltiplicazioni e divisioni. */
public class MultiplicationDivision extends MultiOperation {

    /**
     * Istanzia un'operazione che ha {@code operators} come operatori.
     *
     * @param operators gli operatori di questa operazione
     * @throws IllegalArgumentException se in {@code operators} è presente un operatore che non è né una
     *                                  moltiplicazione né una divisione
     */
    public MultiplicationDivision(int... operators) {
        super(new int[] {MathExpressionParser.MUL, MathExpressionParser.DIV}, operators);
    }

    /**
     * Restituisce il risultato dell'operazione che ha {@code operands} come operandi e gli operatori di questa
     * operazione come operatori, tutti presi nel loro ordine.
     * <p>
     * Per esempio, se gli operatori di questa operazione sono {@code [*, /, *]}, verrà restituito il risultato
     * di {@code operands[0] * operands[1] / operands[2] * operands[3]}.
     *
     * @param operands gli operandi dell'operazione
     * @return il risultato dell'operazione che ha {@code operands} come operandi e gli operatori di questa
     *         operazione come operatori
     */
    @Override
    public Number calculate(Number[] operands) {
        Number result = operands[0];

        try {

            Iterator<Integer> iter = this.iterator();
            for (int i = 1; i < operands.length; i++) {
                int operation = iter.next();
                switch (operation) {
                    case MathExpressionParser.MUL:
                        result = result.mul(operands[i]);
                        break;
                    case MathExpressionParser.DIV:
                        result = result.div(operands[i]);
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }

        } catch (SimplicioArithmeticException e) {
            String[] latexOperands = new String[operands.length];
            for (int i = 0; i < operands.length; i++) latexOperands[i] = operands[i].getLatex(new String[]{});
            e.setExpr(this.getLatex(latexOperands));
            throw e;
        }

        return result;
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    protected String toLatex(int[] operators, String[] s) {
        if (operators.length != s.length - 1) throw new IllegalArgumentException();
        StringBuilder latex = new StringBuilder();
        for (int i = 0; i < operators.length; i++) {
            String symbol = MathExpressionLexer.VOCABULARY.getLiteralName(operators[i]);
            if (operators[i] == MathExpressionLexer.MUL) symbol = " \\times ";
            latex.append(s[i]).append(symbol, 1, symbol.length() - 1);
        }
        latex.append(s[s.length - 1]);
        return latex.toString();
    }
}
