package tree;

import data.AbstractSyntaxTreeNodeValue;

/**
 * Rappresenta un <i>Abstract Syntax Tree</i>, ossia un albero immutabile che descrive la struttura di un
 * espressione matematica.
 * <p>
 * Gli oggetti di questo tipo vanno costruiti in maniera bottom-up e trattati ricorsivamente, infatti dato
 * un albero è possibile solo ottenere informazioni sulla sua radice e ottenere i sottoalberi figli della
 * radice.
 * <p>
 * L'albero ha due tipi di nodi: quelli che rappresentano un operazione (i nodi interni) i cui
 * sottoalberi sono gli operandi, e quelli che rappresentano un numero (le foglie dell'albero).
 * <p>
 * Ogni nodo, indipendentemente dalla sua tipologia, possiede i seguenti attributi:
 * <ul>
 *     <li>
 *         <i>value</i>: indica il valore del nodo.<br>
 *         Se il nodo è un numero indica il valore del numero mentre se è un operazione indica la tipologia
 *         dell'operazione.
 *     </li>
 *     <li>
 *         <i>parensDepth</i>: indica il livello di parentesizzazione dell'operazione (se il nodo è
 *         un numero vale zero).<br>
 *         In particolare, se l'operazione non è racchiusa in nessuna parentesi vale uno, mentre se
 *         è racchiusa in {@code n} parentesi vale {@code n + 1}.
 *     </li>
 *     <li>
 *         <i>priority</i>: indica la priorità con la quale va svolta un'operazione rispetto ad un'altra
 *         (se il nodo è un numero vale zero).<br>
 *     </li>
 *     <li>
 *         <i>marked</i>: indica se il numero è quello marcato, ossia se è il risultato dell'operazione
 *         svolta al passo precedente (se il nodo è un'operazione vale {@code false}).
 *     </li>
 * </ul>
 * <p>
 * Gli attributi di un albero sono invece:
 * <ul>
 *     <li>
 *         <i>maxParensDepth</i>: indica il livello di parentesizzazione massimo presente nei
 *         sottoalberi figli della radice (se il nodo è un numero vale zero dato che non ha figli).<br>
 *     </li>
 *     <li>
 *         <i>maxPriority</i>: indica la priorità massima presente nei sottoalberi figli della radice
 *         (se il nodo è un numero vale zero dato che non ha figli).<br>
 *     </li>
 * </ul>
 * Da notare che in entrambi i casi il valore presente nella radice non viene considerato.
 *
 * @see AbstractSyntaxTreeNodeValue
 */
public class AbstractSyntaxTree {
    private final AbstractSyntaxTreeNodeValue root;
    private final AbstractSyntaxTree[] subtrees;
    private final int parensDepth;
    private final int maxParensDepth;
    private final int maxPriority;
    private final boolean marked;

    /**
     * Istanzia un <i>Abstract Syntax Tree</i>.
     *
     * @param parensDepth il livello di parentesizzazione della radice
     * @param marked      indica se la radice è marcata
     * @param root        il valore della radice
     * @param subtrees    alberi figli della radice
     * @throws IllegalArgumentException se {@code root} non è un numero e {@code marked} vale {@code true};
     *                                  se {@code root} non è un numero e la lunghezza di {@code subtrees}
     *                                  è zero; oppure se {@code root} è un numero e la lunghezza di
     *                                  {@code subtrees} è maggiore di zero
     */
    public AbstractSyntaxTree(int parensDepth,
                              boolean marked,
                              AbstractSyntaxTreeNodeValue root,
                              AbstractSyntaxTree[] subtrees) {
        this.parensDepth = parensDepth;
        this.marked = marked;
        this.root = root;
        this.subtrees = subtrees;

        if (!root.isErrorNode()
                && (!root.isNumber() && marked
                || !root.isNumber() && subtrees.length == 0
                || root.isNumber() && subtrees.length > 0))
            throw new IllegalArgumentException();

        // La maxPriority e il maxParensDepth di un nodo sono dati dal massimo del massimo tra
        // (rispettivamente) la priority e la maxPriority, e tra il parensDepth e il maxParensDepth
        // della radice di ogni sottoalbero.

        if (subtrees.length == 0) this.maxPriority = 0;
        else {
            int max = 0;
            for (AbstractSyntaxTree subtree : subtrees) {
                int v = Math.max(subtree.getRoot().getPriority(), subtree.maxPriority);
                if (v > max) max = v;
            }
            this.maxPriority = max;
        }

        if (subtrees.length == 0) this.maxParensDepth = 0;
        else {
            int max = 0;
            for (AbstractSyntaxTree subtree : subtrees) {
                int v = Math.max(subtree.parensDepth, subtree.maxParensDepth);
                if (v > max) max = v;
            }
            this.maxParensDepth = max;
        }
    }

    /**
     * Istanzia un <i>Abstract Syntax Tree</i>.
     *
     * @param parensDepth il livello di parentesizzazione della radice
     * @param root        il valore della radice
     * @param subtrees    alberi figli della radice
     * @throws IllegalArgumentException se {@code root} non è un numero e {@code marked} vale {@code true};
     *                                  se {@code root} non è un numero e la lunghezza di {@code subtrees}
     *                                  è zero; oppure se {@code root} è un numero e la lunghezza di
     *                                  {@code subtrees} è maggiore di zero
     */
    public AbstractSyntaxTree(int parensDepth,
                              AbstractSyntaxTreeNodeValue root,
                              AbstractSyntaxTree[] subtrees) {
        this(
                parensDepth,
                false,
                root,
                subtrees
        );
    }

    /**
     * Restituisce il valore della radice di questo albero.
     *
     * @return il valore della radice di questo albero
     */
    public AbstractSyntaxTreeNodeValue getRoot() {
        return root;
    }

    /**
     * Restituisce il numero di figli che possiede la radice di questo albero.
     *
     * @return il numero di figli che possiede la radice di questo albero
     */
    public int getChildrenCount() {
        return subtrees.length;
    }

    /**
     * Restituisce i sottoalberi figli della radice di questo albero.
     *
     * @return i sottoalberi figli della radice di questo albero
     */
    public AbstractSyntaxTree[] getSubTrees() {
        return subtrees.clone();
    }

    /**
     * Restituisce l'{@code i}-esimo sottoalbero figlio della radice di questo albero, dove il primo
     * figlio più a sinistra è {@code 0}.
     *
     * @param i la posizione del figlio che si vuole ottenere
     * @return l'{@code i}-esimo sottoalbero figlio della radice di questo albero
     */
    public AbstractSyntaxTree getSubTree(int i) {
        return subtrees[i];
    }

    /**
     * Restituisce il livello di parentesizzazione della radice di questo albero.
     *
     * @return il livello di parentesizzazione della radice di questo albero
     */
    public int getParensDepth() {
        return parensDepth;
    }

    /**
     * Restituisce la priorità massima presente nei sottoalberi figli della radice di questo albero.
     * <p>
     * Da notare che il valore presente nella radice non viene tenuto in considerazione nel calcolare il
     * massimo.
     *
     * @return la priorità massima presente nei sottoalberi figli della radice di questo albero
     */
    public int getMaxPriority() {
        return maxPriority;
    }

    /**
     * Restituisce il livello di parentesizzazione massimo presente nei sottoalberi figli della radice di
     * questo albero.
     * <p>
     * Da notare che il valore presente nella radice non viene tenuto in considerazione nel calcolare il
     * massimo.
     *
     * @return il livello di parentesizzazione massimo presente nei sottoalberi figli della radice
     *         di questo albero
     */
    public int getMaxParensDepth() {
        return maxParensDepth;
    }

    /**
     * Restituisce {@code true} se la radice di questo albero è marcata, altrimenti {@code false}.
     *
     * @return {@code true} se la radice di questo albero è marcata, altrimenti {@code false}
     */
    public boolean isMarked() {
        return marked;
    }

    /**
     * Restituisce la rappresentazione in formato <i>lol</i> di questa albero.
     *
     * @return la rappresentazione in formato <i>lol</i> di questa albero
     */
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder("(");
        ret
            .append("{'value': '")
            .append(root)
            .append("', 'depth': ")
            .append(parensDepth)
            .append(", 'max_depth': ")
            .append(maxParensDepth)
            .append(", 'priority': ")
            .append(root.getPriority())
            .append(", 'max_priority': '")
            .append(maxPriority)
            .append("', 'marked': '")
            .append(marked)
            .append("'}");
        if (subtrees.length == 0) ret.append(", ");
        for (AbstractSyntaxTree subtree : subtrees) ret.append(", ").append(subtree.toString());
        return ret + ")";
    }
}
