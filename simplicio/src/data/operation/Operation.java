package data.operation;

import data.AbstractSyntaxTreeNodeValue;
import data.Number;

/** Rappresenta un'operazione aritmetica. */
public interface Operation extends AbstractSyntaxTreeNodeValue {

    // In questa interfaccia è presente il metodo calculate (che ha lo stesso scopo di getValue) perché
    // così facendo è possibile far si che il controllo del numero di operandi dell'operazione, che è
    // comune ad ogni classe di operazioni che ha lo stesso numero di operandi, non venga fatto al livello
    // della singola operazione ma al livello della generica operazione con quel numero di operandi.

    /**
     * Restituisce il risultato di questa operazione calcolata con {@code operands} come operandi, presi
     * nell'ordine in cui sono.
     *
     * @param operands gli operandi dell'operazione
     * @return il risultato di questa operazione calcolata con {@code operands} come operandi
     */
    Number calculate(Number[] operands);

    /**
     * Restituisce il risultato di questa operazione calcolata con {@code operands} come operandi, presi
     * nell'ordine in cui sono.
     * <p>
     * Dettagli implementativi: l'implementazione di questo metodo deve controllare se il parametro
     * {@code operands} è valido e successivamente chiamare {@code return calculate(operands)}.
     *
     * @param operands gli operandi dell'operazione
     * @return il risultato di questa operazione calcolata con {@code operands} come operandi
     */
    @Override
    default Number getValue(Number[] operands) {
        return calculate(operands);
    }
}
