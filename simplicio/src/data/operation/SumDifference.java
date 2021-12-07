package data.operation;

import antlr.MathExpressionLexer;
import antlr.MathExpressionParser;
import data.Number;
import exception.SimplicioArithmeticException;

import java.util.Iterator;

/**
 * Rappresenta un'operazione composta da somme e differenze.
 */
public class SumDifference extends MultiOperation {

    /**
     * Istanzia un'operazione che ha {@code operators} come operatori.
     *
     * @param operators gli operatori di questa operazione
     * @throws IllegalArgumentException se in {@code operators} è presente un operatore che non
     *                                  è né una somma né una sottrazione
     */
    public SumDifference(int... operators) {
        super(new int[] {MathExpressionParser.ADD, MathExpressionParser.SUB}, operators);
    }

    /**
     * Restituisce il risultato dell'operazione che ha {@code operands} come operandi e gli operatori di questa
     * operazione come operatori, tutti presi nel loro ordine.
     * <p>
     * Per esempio, se gli operatori di questa operazione sono {@code [+, -, +]}, verrà restituito il risultato
     * di {@code operands[0] + operands[1] - operands[2] + operands[3]}.
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
                    case MathExpressionParser.ADD:
                        result = result.add(operands[i]);
                        break;
                    case MathExpressionParser.SUB:
                        result = result.sub(operands[i]);
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
        return 1;
    }

    @Override
    protected String toLatex(int[] operators, String[] s) {
        if (operators.length != s.length - 1) throw new IllegalArgumentException();
        StringBuilder latex = new StringBuilder();
        for (int i = 0; i < operators.length; i++) {
            String symbol = MathExpressionLexer.VOCABULARY.getLiteralName(operators[i]);
            latex.append(s[i]).append(symbol, 1, symbol.length() - 1);
        }
        latex.append(s[s.length - 1]);
        return latex.toString();
    }
}
