package data;

/** Rappresenta il valore di un nodo dell'{@link tree.AbstractSyntaxTree}. */
public interface AbstractSyntaxTreeNodeValue {

    /**
     * Restituisce {@code true} se questo nodo è un numero, altrimenti {@code false}.
     *
     * @return {@code true} se questo nodo è un numero, altrimenti {@code false}
     */
    default boolean isNumber() {
        return false;
    }

    /**
     * Restituisce {@code true} se questo è un nodo di errore, altrimenti {@code false}.
     *
     * @return {@code true} se questo è un nodo di errore, altrimenti {@code false}
     */
    default boolean isErrorNode() {
        return false;
    }

    /**
     * Restituisce {@code true} se questa operazione va svolta seguendo l'ordine (da sinistra a destra),
     * {@code false} se va invece svolta osservando la priorità.
     * <p>
     * Svolgere un'operazione seguendo l'ordine significa che, data un'operazione che come operandi non ha
     * dei numeri ma altre operazioni, viene svolta per prima l'operazione che nell'albero si trova più a
     * sinistra; mentre usare la priorità significa scegliere l'operazione più a sinistra che ha priorità
     * maggiore.
     *
     * @return {@code true} se questa operazione va svolta seguendo l'ordine (da sinistra a destra),
     *         {@code false} se va invece svolta osservando la priorità
     */
    default boolean solveInorder() {
        return false;
    }

    /**
     * Restituisce il valore di questo nodo.
     * 
     * @param operands operandi usati per calcolare il valore del nodo
     * @return il valore di questo nodo
     */
    Number getValue(Number[] operands);

    /**
     * Restituisce la priorità di questa operazione.
     * <p>
     * La priorità indica la precedenza con la quale va svolta una determinata operazione rispetto a un'altra:
     * date due operazioni viene svolta prima quella con priorità maggiore.
     * <p>
     * Se questo nodo non è un'operazione viene lanciata un'eccezione.
     *
     * @return la priorità di questa operazione
     */
    int getPriority();

    /**
     * Restituisce la rappresentazione latex del valore di questo nodo.
     *
     * @param s valori utilizzati per generare il latex
     * @return la rappresentazione latex del valore di questo nodo
     */
    String getLatex(String[] s);
}
