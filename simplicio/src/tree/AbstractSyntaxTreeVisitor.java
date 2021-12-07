package tree;

/**
 * Rappresenta un visitatore di {@link AbstractSyntaxTree}.
 * <p>
 * I metodi di questa classe permettono di effettuare la visita di un AST, nella quale è possibile
 * svolgere un passo di semplificazione (quindi risolvere un'operazione).
 * Ogni visita restituisce una stringa contenente il latex che rappresenta l'espressione dell'AST
 * visitato ed un AST che, nel caso in cui è stata svolta una semplificazione rappresenta
 * l'espressione semplificata, altrimenti coincide con quello in ingresso.
 */
public interface AbstractSyntaxTreeVisitor {
    /**
     * Visita l'AST {@code ast}.
     * <p>
     * Dettagli implementativi: questo metodo rappresenta una visita ad un albero senza che venga specificata
     * la tipologia del nodo della radice, la sua implementazione deve identificare la tipologia della radice
     * dell'albero e successivamente chiamare il metodo adatto per visitare gli alberi che hanno quella
     * tipologia di nodo come radice.
     *
     * @param ast    l'AST da visitare
     * @param solve  indica se bisogna svolgere un'operazione presente in {@code ast}
     * @param parens indica se il latex ricevuto come output debba essere racchiuso tra due parentesi
     * @return il risultato della visita
     */
    default ReturnValues visit(AbstractSyntaxTree ast,
                               boolean solve,
                               boolean parens) {
        if (ast.getRoot().isNumber()) return visitNumber(ast, parens);
        else if (ast.getMaxParensDepth() == 0) {
            if (solve) return visitOperationToSolve(ast, parens);
            return visitOperationNotToSolve(ast, parens);
        }
        return visitTransitionNode(ast, solve, parens);
    }

    /**
     * Visita un AST che ha come radice un'operazione che ha come figli altre operazioni.
     * <p>
     * Dettagli implementativi: questo metodo indica l'ordine con cui viene fatta la visita in quanto
     * ha il compito di definire, tra i sottoalberi figli della radice, qual è quello che contiene
     * l'operazione da valutare.
     *
     * @param operation    l'AST da visitare
     * @param solve  indica se bisogna svolgere un'operazione presente in {@code ast}
     * @param parens indica se il latex ricevuto come output debba essere racchiuso tra due parentesi
     * @return il risultato della visita
     */
    ReturnValues visitTransitionNode(AbstractSyntaxTree operation, boolean solve, boolean parens);

    /**
     * Visita un AST che ha come radice un numero.
     *
     * @param number l'AST da visitare
     * @param parens indica se il latex ricevuto come output debba essere racchiuso tra due parentesi
     * @return il risultato della visita
     */
    ReturnValues visitNumber(AbstractSyntaxTree number, boolean parens);

    /**
     * Visita un AST che ha come radice l'operazione da svolgere.
     * <p>
     * Dovendo risolvere l'operazione presente nella radice, è necessario che i suoi figli siano tutti
     * dei numeri.
     *
     * @param operation l'AST da visitare
     * @param parens    indica se il latex ricevuto come output debba essere racchiuso tra due parentesi
     * @return il risultato della visita
     */
    ReturnValues visitOperationToSolve(AbstractSyntaxTree operation, boolean parens);

    /**
     * Visita un AST che ha come radice un'operazione che ha solo numeri come figli, ma non va svolta.
     *
     * @param operation l'AST da semplificare
     * @param parens    indica se il latex ottenuto in output debba essere racchiuso tra parentesi
     * @return il risultato della visita
     */
    ReturnValues visitOperationNotToSolve(AbstractSyntaxTree operation, boolean parens);

    /**
     * Le istanze di questa classe sono immutabili e vengono utilizzate per memorizzare i valori di ritorno
     * dei metodi di questa classe.
     * <p>
     * In particolare vengono memorizzati:
     * <ul>
     *     <li>un <i>Abstract Syntax Tree</i>, che rappresenta lo step successivo dell'espressione
     *     rappresentata dall'albero appena visitato oppure una copia di quello in input.
     *     <li>una rappresentazione latex dell'albero appena visitato.</li>
     *     <li>un'eccezione, che se non è {@code null} rappresenta un errore avvenuto nello svolgimento
     *         di un operazione presente nell'albero visitato.</li>
     * </ul>
     */
    class ReturnValues {
        private final AbstractSyntaxTree abstractSyntaxTree;
        private final String latex;
        private final RuntimeException exception;

        /**
         * Istanzia un {@link ReturnValues}.
         *
         * @param abstractSyntaxTree l'albero che rappresenta l'espressione "generata" dalla visita
         * @param latex              la rappresentazione latex dell'albero appena visitato
         * @param parenthesizes      indica se il {@code latex} va inserito all'interno di una
         *                           parentesizzazione
         * @param exception          eccezione che è capitata durante la visita, se non ne è capitata
         *                           nessuna {@code null}
         */
        public ReturnValues(AbstractSyntaxTree abstractSyntaxTree,
                            String latex,
                            boolean parenthesizes,
                            RuntimeException exception) {
            this.abstractSyntaxTree = abstractSyntaxTree;
            this.latex = parenthesizes
                    ?   "\\left(" + latex + "\\right)"
                    :   latex
            ;
            this.exception = exception;
        }

        /**
         * Istanzia un {@link ReturnValues}.
         *
         * @param abstractSyntaxTree l'albero che rappresenta l'espressione "generata" dalla visita
         * @param latex              la rappresentazione latex dell'albero appena visitato
         * @param parenthesizes      indica se il {@code latex} va inserito all'interno di una parentesizzazione
         */
        public ReturnValues(AbstractSyntaxTree abstractSyntaxTree,
                            String latex,
                            boolean parenthesizes) {
            this(
                    abstractSyntaxTree,
                    latex,
                    parenthesizes,
                    null
            );
        }

        public AbstractSyntaxTree getAbstractSyntaxTree() {
            return abstractSyntaxTree;
        }

        public String getLatex() {
            return latex;
        }

        public RuntimeException getException() {
            return exception;
        }
    }
}
