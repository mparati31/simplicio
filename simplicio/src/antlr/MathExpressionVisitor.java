// Generated from MathExpression.g4 by ANTLR 4.9.3
package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MathExpressionParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MathExpressionVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(MathExpressionParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(MathExpressionParser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#sumDiff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSumDiff(MathExpressionParser.SumDiffContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#mulDiv}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulDiv(MathExpressionParser.MulDivContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(MathExpressionParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#fraction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFraction(MathExpressionParser.FractionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#pow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPow(MathExpressionParser.PowContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#parens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParens(MathExpressionParser.ParensContext ctx);
	/**
	 * Visit a parse tree produced by {@link MathExpressionParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(MathExpressionParser.NumberContext ctx);
}