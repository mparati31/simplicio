package tree;

import data.AbstractSyntaxTreeNodeValue;
import data.ErrorNode;
import data.Number;
import exception.SimplicioArithmeticException;

/**
 * Rappresenta un visitatore di {@link AbstractSyntaxTree}.
 * <p>
 * I metodi di questa classe permettono di effettuare la visita di un AST, nella quale è possibile svolgere
 * un passo di semplificazione (quindi risolvere un'operazione).
 * Ogni visita restituisce una stringa contenente il latex che rappresenta l'espressione dell'AST visitato
 * ed un AST che, nel caso in cui è stata svolta una semplificazione rappresenta l'espressione semplificata,
 * altrimenti coincide con quello in ingresso.
 * <p>
 * Dato un AST, in una visita viene svolta una sola operazione.
 * In particolare viene svolta l'operazione che:
 * <ul>
 *     <li>ha come figli solo numeri (e non altre operazioni)</li>
 *     <li>si trova nella parentesi più a sinistra tra quelle con annidamento maggiore</li>
 *     <li>è la più a sinistra tra tutte le operazioni che hanno il suo livello di priorità all'interno di
 *     quella parentesi</li>
 * </ul>
 */
public class AbstractSyntaxTreeStepCalculator implements AbstractSyntaxTreeVisitor {

    @Override
    public ReturnValues visitTransitionNode(AbstractSyntaxTree operation,
                                            boolean solve,
                                            boolean parens) {
        RuntimeException exception = null;

        AbstractSyntaxTree[] subtrees = operation.getSubTrees();

        String[] latex = new String[subtrees.length];
        AbstractSyntaxTree[] newSubTrees = new AbstractSyntaxTree[subtrees.length];

        // Se solve = false si ottiene ricorsivamente il latex che rappresenta questo albero.
        if (!solve) {
            for (int i = 0; i < subtrees.length; i++) {
                ReturnValues visitResult = visit(
                        subtrees[i],
                        false,
                        subtrees[i].getParensDepth() > operation.getParensDepth()
                );
                latex[i] = visitResult.getLatex();
                newSubTrees[i] = visitResult.getAbstractSyntaxTree();
                if (exception == null) exception = visitResult.getException();
            }
        }

        else {

            // Se parensDepth è uguale a maxParensDepth significa che ci si trova all'interno della parentesi
            // che contiene l'operazione da svolgere in questo passaggio.
            // Bisogna quindi fare lo scarico ricorsivo verso il nodo che rappresenta quell'operazione.
            // Per sapere in quale dei sottoalbero si trova bisogna usare la priorità: l'operazione da svolgere
            // è quella che ha come priority il valore di maxPriority di questo albero, quindi sarà all'interno
            // del sottoalbero che ha quel valore come priority (se è direttamente lei) o come maxPriority.
            // Se più di un sottoalbero rispetta questa condizione si sceglie quello più a sinistra.
            if (operation.getParensDepth() == operation.getMaxParensDepth()) {
                int indexSubtreeToSolve = -1;

                // Se l'operazione in cui ci si trova è quella con priorità massima tra lei e i suoi figli, bisogna
                // controllare se va eseguita in ordine o usando le priorità.

                // Se va eseguita seguendo l'ordine, il sottoalbero contenete l'operazione da risolvere è il primo,
                // partendo da sinistra, che non è un numero.
                if (operation.getRoot().solveInorder()) {
                    for (int i = 0; i < subtrees.length; i++) {
                        if (!subtrees[i].getRoot().isNumber()) {
                            indexSubtreeToSolve = i;
                            break;
                        }
                    }

                }

                // Se invece va eseguita utilizzando le priorità, l'operazione si trova nel sottoalbero più a
                // sinistra che contiene l'operazione con la precedenza più alta tra tutte quelle dei sottoalberi.
                else {
                    for (int i = 0; i < subtrees.length; i++) {
                        if (operation.getMaxPriority() == subtrees[i].getRoot().getPriority()
                                || operation.getMaxPriority() == subtrees[i].getMaxPriority()) {
                            indexSubtreeToSolve = i;
                            break;
                        }
                    }

                }

                if (indexSubtreeToSolve == -1) throw new IllegalStateException();

                for (int i = 0; i < subtrees.length; i++) {
                    ReturnValues visitResult = visit(
                            subtrees[i],
                            i == indexSubtreeToSolve,
                            subtrees[i].getParensDepth() > operation.getParensDepth()
                    );

                    newSubTrees[i] = visitResult.getAbstractSyntaxTree();
                    latex[i] = visitResult.getLatex();
                    if (exception == null) exception = visitResult.getException();
                }
            }

            // Se parensDepth è minore di maxParensDepth significa che non si è ancora arrivati nella parentesi
            // che contiene l'operazione da risolvere.
            // Il sottoalbero che rappresenta la parentesi in cui è contenuta l'operazione da risolvere è quella
            // che ha il parensDepth massimo, ossia uguale a maxParensDepth di questo albero.
            // Bisogna quindi fare lo scarico ricorsivo verso il sottoalbero più a sinistra che ha il valore di
            // maxParensDepth di questo albero come parensDepth o come maxParensDepth.
            else if (operation.getParensDepth() < operation.getMaxParensDepth()) {
                boolean found = false;

                for (int i = 0; i < subtrees.length; i++) {
                    boolean solveSubtree = false;
                    boolean blue = false;

                    if ((operation.getMaxParensDepth() == subtrees[i].getMaxParensDepth()
                            || operation.getMaxParensDepth() == subtrees[i].getParensDepth())
                            && !found) {
                        solveSubtree = true;
                        found = true;

                        if (operation.getMaxParensDepth() == subtrees[i].getParensDepth()) blue = true;
                    }

                    ReturnValues visitResult = visit(
                            subtrees[i],
                            solveSubtree,
                            subtrees[i].getParensDepth() > operation.getParensDepth()
                    );

                    latex[i] = visitResult.getLatex();
                    if (blue) latex[i] = "{\\color{blue}{" + latex[i] + "}}";

                    newSubTrees[i] = visitResult.getAbstractSyntaxTree();
                    if (exception == null) exception = visitResult.getException();
                }

                if (!found) throw new IllegalStateException();
            }
        }

        return new ReturnValues(
                new AbstractSyntaxTree(
                        operation.getParensDepth(),
                        operation.getRoot(),
                        newSubTrees
                ),
                operation.getRoot().getLatex(latex),
                parens,
                exception
        );
    }

    @Override
    public ReturnValues visitNumber(AbstractSyntaxTree number,
                                    boolean parens) {
        if (!number.getRoot().isNumber()) throw new IllegalArgumentException();

        String latex = number.getRoot().getLatex(new String[]{});

        if (number.isMarked()) {
            latex = "{\\color{green}{\\boxed{" + latex + "}}}";
            number = new AbstractSyntaxTree(
                    number.getParensDepth(),
                    false,
                    number.getRoot(),
                    new AbstractSyntaxTree[]{}
            );
        }

        return new ReturnValues(
                number,
                latex,
                parens);
    }

    @Override
    public ReturnValues visitOperationToSolve(AbstractSyntaxTree operation,
                                              boolean parens) {
        if (operation.getRoot().isNumber()) throw new IllegalArgumentException();

        AbstractSyntaxTree[] subtrees = operation.getSubTrees();
        Number[] operands = new Number[subtrees.length];
        String[] subtreesLatex = new String[subtrees.length];

        for (int i = 0; i < subtrees.length; i++) {
            ReturnValues visitResult = visitNumber(
                    subtrees[i],
                    subtrees[i].getParensDepth() > operation.getParensDepth()
            );

            AbstractSyntaxTree astResult = visitResult.getAbstractSyntaxTree();
            String latexResult = visitResult.getLatex();

            if (astResult.getRoot().isNumber()) {
                operands[i] = (Number) astResult.getRoot();
                subtreesLatex[i] = latexResult;
            }
            else throw new IllegalStateException();
        }

        boolean marked = true;

        // Quando capita un eccezione durante lo svolgimento di un operazione, questa viene catturata e salvata
        // in modo tale da poter concludere la visita dell'albero ed avere quindi il latex che rappresenta
        // l'albero operation.
        // Se capita più di una eccezione viene salvata solo la prima.

        SimplicioArithmeticException exception = null;
        AbstractSyntaxTreeNodeValue result = new ErrorNode();

        try {
            result = operation.getRoot().getValue(operands);
        } catch (SimplicioArithmeticException e) {
            exception = e;
            // Va messo a false altrimenti si verificherebbe un errore nella creazione dell Abstract Syntax Tree.
            marked = false;
        } catch (Exception e) {
            throw new IllegalStateException();
        }

        String latex = operation.getRoot().getLatex(subtreesLatex);

        return new ReturnValues(
                new AbstractSyntaxTree(
                        0,
                        marked,
                        result,
                        new AbstractSyntaxTree[]{}
                ),
                "{\\color{red}{\\boxed{" + latex + "}}}",
                parens,
                exception
        );
    }

    @Override
    public ReturnValues visitOperationNotToSolve(AbstractSyntaxTree operation,
                                                 boolean parens) {
        AbstractSyntaxTree[] subtrees = operation.getSubTrees();
        AbstractSyntaxTree[] newSubtrees = new AbstractSyntaxTree[subtrees.length];
        String[] latex = new String[subtrees.length];
        for (int i = 0; i < subtrees.length; i++) {
            ReturnValues visitResult = visitNumber(
                    subtrees[i],
                    subtrees[i].getParensDepth() > operation.getParensDepth()
            );
            newSubtrees[i] = visitResult.getAbstractSyntaxTree();
            latex[i] = visitResult.getLatex();
        }

        return new ReturnValues(
                new AbstractSyntaxTree(
                        operation.getParensDepth(),
                        operation.getRoot(),
                        newSubtrees
                ),
                operation.getRoot().getLatex(latex),
                parens
        );
    }
}
