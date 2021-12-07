package simplicio;

import exception.DivisionByZeroException;
import test.Test;
import tree.AbstractSyntaxTree;
import tree.AbstractSyntaxTreeStepCalculator;
import tree.AbstractSyntaxTreeVisitor;

import java.util.Iterator;

/**
 * Rappresenta un iteratore che itera sui vari passaggi di semplificazione di una data espressione.
 * <p>
 * Prende in input un {@link AbstractSyntaxTree} che rappresenta l'espressione da semplificare, e ad ogni
 * chiamata di {@link ExpressionStepIterator#next()} restituisce la rappresentazione latex dell'espressione
 * al successivo passaggio di semplificazione.
 * <p>
 * Nel caso in cui si verifica un eccezione (per esempio overflow o divisione per zero), è possibile
 * recuperare il latex del passaggio che ha causato il problema con il metodo
 * {@link ExpressionStepIterator#getCurrentLatex()}.
 */
public class ExpressionStepIterator implements Iterator<String> {

    // Albero da visitare al prossimo passaggio.
    // Vale null quando l'espressione è stata semplificata completamente.
    private AbstractSyntaxTree abstractSyntaxTree;

    private String currentLatex;

    private final AbstractSyntaxTreeStepCalculator calculator;

    /**
     * Istanzia un iteratore per l'espressione rappresentata dall'albero {@code abstractSyntaxTree}.
     *
     * @param abstractSyntaxTree l'espressione che si vuole semplificare
     */
    public ExpressionStepIterator(AbstractSyntaxTree abstractSyntaxTree) {
        this.abstractSyntaxTree = abstractSyntaxTree;
        calculator = new AbstractSyntaxTreeStepCalculator();
    }

    /**
     * Restituisce {@code true} se è possibile effettuare un altro passo di semplificazione, mentre {@code false}
     * se l'espressione è stata semplificata completamente (e quindi si è arrivati al risultato) oppure se nel
     * passaggio precedente si è verificato un errore.
     *
     * @return {@code true} se è possibile effettuare un altro passo di semplificazione, mentre {@code false}
     *         se l'espressione è stata semplificata completamente (e quindi si è arrivati al risultato)
     *         oppure se nel passaggio precedente si è verificato un errore
     */
    public boolean hasNext() {
        return abstractSyntaxTree != null;
    }

    /**
     * Effettua un passaggio di semplificazione e restituisce il latex dell'espressione ottenuta.
     * <p>
     * Con 'passo di semplificazione' si intende risolvere un'operazione dell'espressione.
     *
     * @return il latex dell'espressione ottenuta dopo un passo di semplificazione
     * @throws DivisionByZeroException   se capita una divisione per zero
     * @throws exception.OverflowException         se capita un overflow
     * @throws exception.IrrationalNumberException se nello svolgere un operazione capita che un passaggio
     *                                             intermedio oppure il risultato sono un numero irrazionale
     */
    @Override
    public String next() {
        if (!hasNext()) throw new IllegalCallerException();

        // Se l'AST è composto da un solo numero, ossia il risultato dell'operazione, allora
        // questa è l'ultima iterazione disponibile.
        if (abstractSyntaxTree.getRoot().isNumber()) {
            String latex = calculator.visit(
                    abstractSyntaxTree,
                    false,
                    false
            ).getLatex();
            abstractSyntaxTree = null;
            return latex;
        }

        AbstractSyntaxTreeVisitor.ReturnValues res = calculator.visit(
                abstractSyntaxTree,
                true,
                false
        );

        abstractSyntaxTree = res.getAbstractSyntaxTree();
        currentLatex = res.getLatex();

        Test.printAST(abstractSyntaxTree);

        // Controlla se questo passaggio ha causato un'eccezione.
        if (res.getException() != null) {
            abstractSyntaxTree = null;
            throw res.getException();
        }

        return currentLatex;
    }

    /**
     * Restituisce il latex che rappresenta l'espressione dopo l'ultimo passaggio di semplificazione
     * effettuato.
     *
     * @return il latex che rappresenta l'espressione dopo l'ultimo passaggio di semplificazione effettuato
     */
    public String getCurrentLatex() {
        return currentLatex;
    }
}
