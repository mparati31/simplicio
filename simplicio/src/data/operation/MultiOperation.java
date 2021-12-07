package data.operation;

import antlr.MathExpressionLexer;
import data.Number;

import java.util.Arrays;
import java.util.Iterator;

/** Rappresenta un'operazione che ha almeno due operandi. */
public abstract class MultiOperation implements Operation, Iterable<Integer> {
    // Stessi valori del lexer
    private final int[] operators;

    /**
     * Istanzia un'operazione che ha {@code operators} come operatori.
     *
     * @param accepted  indica gli {@code operators} validi
     * @param operators operatori di questa operazione
     * @throws IllegalArgumentException se {@code accepted} o {@code operators} sono vuoti, oppure se in
     *                                  {@code operators} è presente un valore che non è in {@code accepted}
     */
    public MultiOperation(int[] accepted, int[] operators) {
        if (operators.length == 0 || accepted.length == 0) throw new IllegalArgumentException();

        // Controlla che tutti gli operatori siano validi.
        a:for (int operator : operators) {
            for (int acceptedOperator : accepted) {
                if (operator == acceptedOperator) continue a;
            }
            throw new IllegalArgumentException();
        }

        this.operators = operators;
    }

    /**
     * Restituisce il latex che rappresenta questa operazione.
     *
     * @param operators gli operatori di questa operazione
     * @param s         la rappresentazione latex degli operandi o delle operazioni che genereranno gli operandi
     * @return il latex che rappresenta questa operazione
     */
    abstract protected String toLatex(int[] operators, String[] s);

    /**
     * Restituisce il risultato di questa operazione calcolata con {@code operands} come operandi, presi
     * nell'ordine in cui sono.
     *
     * @throws IllegalArgumentException se il numero di {@code operands} non è maggiore di uno rispetto a quello
     *                                  degli operatori di questa operazione
     * @param operands
     */
    @Override
    final public Number getValue(Number[] operands) {
        // Il numero di operandi deve essere maggiore di uno rispetto a quello degli operatori
        if (operands.length - 1 != operators.length) throw new IllegalArgumentException();
        return calculate(operands);
    }

    @Override
    final public String getLatex(String[] s) {
        return toLatex(operators, s);
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(operators).iterator();
    }

    @Override
    public String toString() {
        String[] literals = new String[operators.length];
        for (int i = 0; i < operators.length; i++) {
            literals[i] = MathExpressionLexer.VOCABULARY.getLiteralName(operators[i]);
            literals[i] = literals[i].substring(1, literals[i].length() - 1);
        }
        return Arrays.toString(literals);
    }
}
