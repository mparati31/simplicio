package simplicio;

import antlr.*;
import exception.DivisionByZeroException;
import listeners.LexerErrorListener;
import listeners.ParserErrorListener;
import org.antlr.v4.runtime.*;
import test.Test;
import tree.AbstractSyntaxTree;
import tree.AbstractSyntaxTreeConverter;

public class Simplicio {

    /**
     * Restituisce un {@link ExpressionStepIterator} per l'espressione {@code expression}.
     *
     * @param expression l'espressione che si vuole valutare
     * @return un {@link ExpressionStepIterator} per l'espressione {@code expression}
     * @throws exception.UnknownTokenException   se in {@code expression} è presente un carattere sconosciuto
     * @throws exception.SyntaxErrorException    se in {@code expression} è presente un errore di sintassi
     * @throws DivisionByZeroException           se in {@code expression} è presente una frazione con zero
     *                                           come denominatore
     * @throws exception.OverflowException       se in {@code expression} è presente un numero che causa
     *                                           overflow
     */
    static public ExpressionStepIterator solve(String expression) {
        Lexer lexer = new MathExpressionLexer(CharStreams.fromString(expression));
        lexer.removeErrorListeners();
        lexer.addErrorListener(new LexerErrorListener());

        MathExpressionParser parser = new MathExpressionParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new ParserErrorListener());

        AbstractSyntaxTreeConverter converter = new AbstractSyntaxTreeConverter();

        AbstractSyntaxTree ast = converter.visit(parser.start());

        Test.printAST(ast);

        return new ExpressionStepIterator(ast);
    }
}
