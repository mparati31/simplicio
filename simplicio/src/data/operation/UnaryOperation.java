package data.operation;

import data.Number;

/** Rappresenta un'operazione unaria. */
public abstract class UnaryOperation implements Operation {

    /**
     * Restituisce il risultato di questa operazione calcolata con {@code operands} come operandi, presi
     * nell'ordine in cui sono.
     *
     * @throws IllegalArgumentException se in {@code operands} è presente più di un operando
     * @param operands
     */
    @Override
    final public Number getValue(Number[] operands) {
        if (operands.length != 1) throw new IllegalArgumentException();
        return calculate(operands);
    }
}
