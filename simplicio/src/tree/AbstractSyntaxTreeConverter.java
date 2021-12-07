package tree;

import antlr.MathExpressionBaseVisitor;
import antlr.MathExpressionParser;
import data.AbstractSyntaxTreeNodeValue;
import data.Number;
import data.operation.*;
import exception.DivisionByZeroException;
import exception.OverflowException;
import exception.SimplicioArithmeticException;
import utils.MathUtils;

/** Converte un <i>Parse Tree</i> nel rispettivo <i>Abstract Syntax Tree</i>. */
public class AbstractSyntaxTreeConverter extends MathExpressionBaseVisitor<AbstractSyntaxTree> {
    private int parensDepth;
    private boolean exponent;

    public AbstractSyntaxTreeConverter() {
        parensDepth = 1;
    }

    @Override
    public AbstractSyntaxTree visitStart(MathExpressionParser.StartContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public AbstractSyntaxTree visitExpr(MathExpressionParser.ExprContext ctx) {
        return visit(ctx.children.get(0));
    }

    @Override
    public AbstractSyntaxTree visitSumDiff(MathExpressionParser.SumDiffContext ctx) {
        AbstractSyntaxTree[] operands = new AbstractSyntaxTree[(ctx.children.size() + 1) / 2];
        int[] operators = new int[(ctx.children.size() - 1) / 2];

        // I figli in posizione pari sono gli operandi, quelli in posizione dispari gli operatori.
        for (int i = 0; i < ctx.children.size(); i++) {
            if (i % 2 == 0) operands[i / 2] = visit(ctx.children.get(i));
            else operators[(i - 1) / 2] = ctx.children.get(i).getText().equals("+")
                    ?   MathExpressionParser.ADD
                    :   MathExpressionParser.SUB
                    ;
        }

        return new AbstractSyntaxTree(
                parensDepth,
                new SumDifference(operators),
                operands
        );
    }

    @Override
    public AbstractSyntaxTree visitMulDiv(MathExpressionParser.MulDivContext ctx) {
        AbstractSyntaxTree[] operands = new AbstractSyntaxTree[(ctx.children.size() + 1) / 2];
        int[] operators = new int[(ctx.children.size() - 1) / 2];

        // I figli in posizione pari sono gli operandi, quelli in posizione dispari gli operatori.
        for (int i = 0; i < ctx.children.size(); i++) {
            if (i % 2 == 0) operands[i / 2] = visit(ctx.children.get(i));
            else operators[(i - 1) / 2] = ctx.children.get(i).getText().equals("×")
                    ?   MathExpressionParser.MUL
                    :   MathExpressionParser.DIV
                    ;
        }

        return new AbstractSyntaxTree(
                parensDepth,
                new MultiplicationDivision(operators),
                operands
        );
    }

    @Override
    public AbstractSyntaxTree visitSign(MathExpressionParser.SignContext ctx) {
        AbstractSyntaxTree expr = visit(ctx.children.get(1));

        AbstractSyntaxTreeNodeValue value;

        switch (ctx.opsign.getType()) {
            case MathExpressionParser.ADD:
                if (expr.getRoot().isNumber()) return expr;
                value = new PositiveSign();
                break;
            case MathExpressionParser.SUB:
                if (expr.getRoot().isNumber())
                    return new AbstractSyntaxTree(
                            0,
                            ((Number) expr.getRoot()).opposite(),
                            new AbstractSyntaxTree[]{}
                    );
                value = new NegativeSign();
                break;
            default:
                throw new IllegalStateException();
        }

        return new AbstractSyntaxTree(
                parensDepth,
                value,
                new AbstractSyntaxTree[]{expr}
        );
    }

    /**
     * {@inheritDoc}
     *
     * @throws DivisionByZeroException se si verifica una divisione per zero
     */
    @Override
    public AbstractSyntaxTree visitFraction(MathExpressionParser.FractionContext ctx) {
        AbstractSyntaxTree numerator = visit(ctx.children.get(0));
        AbstractSyntaxTree denominator = visit(ctx.children.get(2));

        try {

            // Se il numeratore e il denominatore della frazione sono entrambi numeri in Z e sono co-primi tra
            // loro, allora questa non va considerata come una frazione ma come un numero razionale.
            // Per poter chiamare gcd() bisogna assicurarsi che sia il numeratore che il denominatore siano
            // non negativi.
            if (numerator.getRoot().isNumber() && denominator.getRoot().isNumber()) {
                Number num = (Number) numerator.getRoot();
                Number den = (Number) denominator.getRoot();
                if (num.isInZ()
                        && den.isInZ()
                        && (num.isPositive() || num.isZero())
                        && den.isPositive()
                        && MathUtils.gcd(num.getZNumber(), den.getZNumber()) == 1) {
                    return new AbstractSyntaxTree(
                            0,
                            new Number(num, den),
                            new AbstractSyntaxTree[]{}
                    );
                }
            }

            return new AbstractSyntaxTree(
                    parensDepth,
                    new Fraction(),
                    new AbstractSyntaxTree[]{numerator, denominator}
            );

        } catch (SimplicioArithmeticException e) {
            e.setExpr(ctx.getText());
            throw e;
        }
    }

    @Override
    public AbstractSyntaxTree visitPow(MathExpressionParser.PowContext ctx) {
        AbstractSyntaxTree base = visit(ctx.children.get(0));

        exponent = true;
        AbstractSyntaxTree exponent = visit(ctx.children.get(2));

        return new AbstractSyntaxTree(
                parensDepth,
                new Pow(),
                new AbstractSyntaxTree[]{base, exponent}
        );
    }

    @Override
    public AbstractSyntaxTree visitParens(MathExpressionParser.ParensContext ctx) {
        // Se sono presenti più livelli di parentesizzazione che racchiudono la stessa espressione, viene
        // considerato un singolo livello.
        if (ctx.parent.parent.getRuleIndex() == MathExpressionParser.RULE_parens) return this.visit(ctx.children.get(1));

        // Nel caso in cui il padre di questo nodo è una frazione, significa che questo rappresenta il
        // numeratore o il denominatore di quella funzione e quindi che le parentesi sono state utilizzate per
        // poterli raggruppare.
        // In questo caso vanno rimosse in modo tale da avere il numeratore e il denominatore allo stesso livello
        // di parentesizzazione della frazione.
        if (ctx.parent.getRuleIndex() == MathExpressionParser.RULE_fraction) return this.visit(ctx.children.get(1));

        // Significa che ci si trova nell'esponente di una potenza e che l'esponente è un'espressione.
        // In questo caso la parentesizzazione è stata fatta per raggruppare l'esponente e quindi non va
        // considerata.
        if (exponent) {
            exponent = false;
            return this.visit(ctx.children.get(1));
        }

        parensDepth++;
        AbstractSyntaxTree tree = this.visit(ctx.children.get(1));
        parensDepth--;

        return tree;
    }

    /**
     * {@inheritDoc}
     *
     * @throws OverflowException se si verifica un overflow
     */
    @Override
    public AbstractSyntaxTree visitNumber(MathExpressionParser.NumberContext ctx) {
        // È necessario mettere a false il flag anche qui dato che nel caso in cui l'esponente di una potenza
        // sia un numero, non verrebbe messo a false in visitParens e graverebbe quindi su una ipotetica
        // futura potenza.
        exponent = false;

        Number number;
        try {
            number = new Number(Double.parseDouble(ctx.getText()));
        } catch (SimplicioArithmeticException e) {
            e.setExpr(ctx.getText());
            throw e;
        }

        return new AbstractSyntaxTree(
                0,
                number,
                new AbstractSyntaxTree[]{}
        );
    }
}
