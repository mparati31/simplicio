package data.operation;

import data.Number;

/** Rappresenta un'operazione binaria. */
public abstract class BinaryOperation implements Operation {

    /**
     * Restituisce il risultato di questa operazione calcolata con {@code operands} come operandi, presi
     * nell'ordine in cui sono.
     *
     * @throws IllegalArgumentException se gli operandi in {@code operands} non sono due
     * @param operands gli operandi di questa operazione
     */
    @Override
    final public Number getValue(Number[] operands) {
        if (operands.length != 2) throw new IllegalArgumentException();
        return calculate(operands);
    }
}
