// Generated from MathExpression.g4 by ANTLR 4.9.3
package antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MathExpressionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, POW=6, FRACT=7, MUL=8, DIV=9, 
		ADD=10, SUB=11, INT=12, WS=13;
	public static final int
		RULE_start = 0, RULE_expr = 1, RULE_sumDiff = 2, RULE_mulDiv = 3, RULE_sign = 4, 
		RULE_fraction = 5, RULE_pow = 6, RULE_parens = 7, RULE_number = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "expr", "sumDiff", "mulDiv", "sign", "fraction", "pow", "parens", 
			"number"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'.'", "'E'", "'e'", "'^'", "'/'", "'\u00D7'", "':'", 
			"'+'", "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "POW", "FRACT", "MUL", "DIV", "ADD", 
			"SUB", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "MathExpression.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MathExpressionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode EOF() { return getToken(MathExpressionParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			expr();
			setState(19);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExprContext extends ParserRuleContext {
		public ParensContext parens() {
			return getRuleContext(ParensContext.class,0);
		}
		public PowContext pow() {
			return getRuleContext(PowContext.class,0);
		}
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public MulDivContext mulDiv() {
			return getRuleContext(MulDivContext.class,0);
		}
		public SumDiffContext sumDiff() {
			return getRuleContext(SumDiffContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_expr);
		try {
			setState(28);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				parens();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(22);
				pow();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(23);
				fraction(0);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(24);
				sign();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(25);
				mulDiv();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(26);
				sumDiff();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(27);
				number();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SumDiffContext extends ParserRuleContext {
		public Token op;
		public List<ParensContext> parens() {
			return getRuleContexts(ParensContext.class);
		}
		public ParensContext parens(int i) {
			return getRuleContext(ParensContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<PowContext> pow() {
			return getRuleContexts(PowContext.class);
		}
		public PowContext pow(int i) {
			return getRuleContext(PowContext.class,i);
		}
		public List<FractionContext> fraction() {
			return getRuleContexts(FractionContext.class);
		}
		public FractionContext fraction(int i) {
			return getRuleContext(FractionContext.class,i);
		}
		public List<SignContext> sign() {
			return getRuleContexts(SignContext.class);
		}
		public SignContext sign(int i) {
			return getRuleContext(SignContext.class,i);
		}
		public List<MulDivContext> mulDiv() {
			return getRuleContexts(MulDivContext.class);
		}
		public MulDivContext mulDiv(int i) {
			return getRuleContext(MulDivContext.class,i);
		}
		public List<TerminalNode> ADD() { return getTokens(MathExpressionParser.ADD); }
		public TerminalNode ADD(int i) {
			return getToken(MathExpressionParser.ADD, i);
		}
		public List<TerminalNode> SUB() { return getTokens(MathExpressionParser.SUB); }
		public TerminalNode SUB(int i) {
			return getToken(MathExpressionParser.SUB, i);
		}
		public SumDiffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sumDiff; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitSumDiff(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SumDiffContext sumDiff() throws RecognitionException {
		SumDiffContext _localctx = new SumDiffContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sumDiff);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(30);
				parens();
				}
				break;
			case 2:
				{
				setState(31);
				number();
				}
				break;
			case 3:
				{
				setState(32);
				pow();
				}
				break;
			case 4:
				{
				setState(33);
				fraction(0);
				}
				break;
			case 5:
				{
				setState(34);
				sign();
				}
				break;
			case 6:
				{
				setState(35);
				mulDiv();
				}
				break;
			}
			setState(47); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(38);
				((SumDiffContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==ADD || _la==SUB) ) {
					((SumDiffContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(45);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(39);
					parens();
					}
					break;
				case 2:
					{
					setState(40);
					number();
					}
					break;
				case 3:
					{
					setState(41);
					pow();
					}
					break;
				case 4:
					{
					setState(42);
					fraction(0);
					}
					break;
				case 5:
					{
					setState(43);
					sign();
					}
					break;
				case 6:
					{
					setState(44);
					mulDiv();
					}
					break;
				}
				}
				}
				setState(49); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ADD || _la==SUB );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MulDivContext extends ParserRuleContext {
		public Token op;
		public List<ParensContext> parens() {
			return getRuleContexts(ParensContext.class);
		}
		public ParensContext parens(int i) {
			return getRuleContext(ParensContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<PowContext> pow() {
			return getRuleContexts(PowContext.class);
		}
		public PowContext pow(int i) {
			return getRuleContext(PowContext.class,i);
		}
		public List<FractionContext> fraction() {
			return getRuleContexts(FractionContext.class);
		}
		public FractionContext fraction(int i) {
			return getRuleContext(FractionContext.class,i);
		}
		public List<SignContext> sign() {
			return getRuleContexts(SignContext.class);
		}
		public SignContext sign(int i) {
			return getRuleContext(SignContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(MathExpressionParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(MathExpressionParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(MathExpressionParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(MathExpressionParser.DIV, i);
		}
		public MulDivContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulDiv; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitMulDiv(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulDivContext mulDiv() throws RecognitionException {
		MulDivContext _localctx = new MulDivContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_mulDiv);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(51);
				parens();
				}
				break;
			case 2:
				{
				setState(52);
				number();
				}
				break;
			case 3:
				{
				setState(53);
				pow();
				}
				break;
			case 4:
				{
				setState(54);
				fraction(0);
				}
				break;
			case 5:
				{
				setState(55);
				sign();
				}
				break;
			}
			setState(66); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(58);
				((MulDivContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
					((MulDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(64);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(59);
					parens();
					}
					break;
				case 2:
					{
					setState(60);
					number();
					}
					break;
				case 3:
					{
					setState(61);
					pow();
					}
					break;
				case 4:
					{
					setState(62);
					fraction(0);
					}
					break;
				case 5:
					{
					setState(63);
					sign();
					}
					break;
				}
				}
				}
				setState(68); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MUL || _la==DIV );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignContext extends ParserRuleContext {
		public Token opsign;
		public TerminalNode ADD() { return getToken(MathExpressionParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(MathExpressionParser.SUB, 0); }
		public ParensContext parens() {
			return getRuleContext(ParensContext.class,0);
		}
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public PowContext pow() {
			return getRuleContext(PowContext.class,0);
		}
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public SignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sign; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignContext sign() throws RecognitionException {
		SignContext _localctx = new SignContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			((SignContext)_localctx).opsign = _input.LT(1);
			_la = _input.LA(1);
			if ( !(_la==ADD || _la==SUB) ) {
				((SignContext)_localctx).opsign = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(76);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(71);
				parens();
				}
				break;
			case 2:
				{
				setState(72);
				number();
				}
				break;
			case 3:
				{
				setState(73);
				pow();
				}
				break;
			case 4:
				{
				setState(74);
				fraction(0);
				}
				break;
			case 5:
				{
				setState(75);
				sign();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FractionContext extends ParserRuleContext {
		public TerminalNode FRACT() { return getToken(MathExpressionParser.FRACT, 0); }
		public List<ParensContext> parens() {
			return getRuleContexts(ParensContext.class);
		}
		public ParensContext parens(int i) {
			return getRuleContext(ParensContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public List<PowContext> pow() {
			return getRuleContexts(PowContext.class);
		}
		public PowContext pow(int i) {
			return getRuleContext(PowContext.class,i);
		}
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public FractionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fraction; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitFraction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FractionContext fraction() throws RecognitionException {
		return fraction(0);
	}

	private FractionContext fraction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FractionContext _localctx = new FractionContext(_ctx, _parentState);
		FractionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_fraction, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(82);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				setState(79);
				parens();
				}
				break;
			case 2:
				{
				setState(80);
				number();
				}
				break;
			case 3:
				{
				setState(81);
				pow();
				}
				break;
			}
			setState(84);
			match(FRACT);
			setState(88);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(85);
				parens();
				}
				break;
			case 2:
				{
				setState(86);
				number();
				}
				break;
			case 3:
				{
				setState(87);
				pow();
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(99);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new FractionContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_fraction);
					setState(90);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(91);
					match(FRACT);
					setState(95);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
					case 1:
						{
						setState(92);
						parens();
						}
						break;
					case 2:
						{
						setState(93);
						number();
						}
						break;
					case 3:
						{
						setState(94);
						pow();
						}
						break;
					}
					}
					} 
				}
				setState(101);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PowContext extends ParserRuleContext {
		public TerminalNode POW() { return getToken(MathExpressionParser.POW, 0); }
		public List<ParensContext> parens() {
			return getRuleContexts(ParensContext.class);
		}
		public ParensContext parens(int i) {
			return getRuleContext(ParensContext.class,i);
		}
		public List<NumberContext> number() {
			return getRuleContexts(NumberContext.class);
		}
		public NumberContext number(int i) {
			return getRuleContext(NumberContext.class,i);
		}
		public PowContext pow() {
			return getRuleContext(PowContext.class,0);
		}
		public PowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pow; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitPow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PowContext pow() throws RecognitionException {
		PowContext _localctx = new PowContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_pow);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(102);
				parens();
				}
				break;
			case INT:
				{
				setState(103);
				number();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(106);
			match(POW);
			setState(110);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				{
				setState(107);
				parens();
				}
				break;
			case 2:
				{
				setState(108);
				number();
				}
				break;
			case 3:
				{
				setState(109);
				pow();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParensContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ParensContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parens; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitParens(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParensContext parens() throws RecognitionException {
		ParensContext _localctx = new ParensContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_parens);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(112);
			match(T__0);
			setState(113);
			expr();
			setState(114);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public List<TerminalNode> INT() { return getTokens(MathExpressionParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(MathExpressionParser.INT, i);
		}
		public TerminalNode SUB() { return getToken(MathExpressionParser.SUB, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MathExpressionVisitor ) return ((MathExpressionVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(INT);
			setState(126);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(117);
				match(T__2);
				setState(118);
				match(INT);
				setState(124);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
				case 1:
					{
					setState(119);
					_la = _input.LA(1);
					if ( !(_la==T__3 || _la==T__4) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SUB) {
						{
						setState(120);
						match(SUB);
						}
					}

					setState(123);
					match(INT);
					}
					break;
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 5:
			return fraction_sempred((FractionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean fraction_sempred(FractionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\17\u0083\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\37\n\3\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\5\4\'\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\60\n\4\6\4\62\n\4\r\4\16\4"+
		"\63\3\5\3\5\3\5\3\5\3\5\5\5;\n\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5C\n\5\6\5"+
		"E\n\5\r\5\16\5F\3\6\3\6\3\6\3\6\3\6\3\6\5\6O\n\6\3\7\3\7\3\7\3\7\5\7U"+
		"\n\7\3\7\3\7\3\7\3\7\5\7[\n\7\3\7\3\7\3\7\3\7\3\7\5\7b\n\7\7\7d\n\7\f"+
		"\7\16\7g\13\7\3\b\3\b\5\bk\n\b\3\b\3\b\3\b\3\b\5\bq\n\b\3\t\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\5\n|\n\n\3\n\5\n\177\n\n\5\n\u0081\n\n\3\n\2\3"+
		"\f\13\2\4\6\b\n\f\16\20\22\2\5\3\2\f\r\3\2\n\13\3\2\6\7\2\u00a4\2\24\3"+
		"\2\2\2\4\36\3\2\2\2\6&\3\2\2\2\b:\3\2\2\2\nH\3\2\2\2\fP\3\2\2\2\16j\3"+
		"\2\2\2\20r\3\2\2\2\22v\3\2\2\2\24\25\5\4\3\2\25\26\7\2\2\3\26\3\3\2\2"+
		"\2\27\37\5\20\t\2\30\37\5\16\b\2\31\37\5\f\7\2\32\37\5\n\6\2\33\37\5\b"+
		"\5\2\34\37\5\6\4\2\35\37\5\22\n\2\36\27\3\2\2\2\36\30\3\2\2\2\36\31\3"+
		"\2\2\2\36\32\3\2\2\2\36\33\3\2\2\2\36\34\3\2\2\2\36\35\3\2\2\2\37\5\3"+
		"\2\2\2 \'\5\20\t\2!\'\5\22\n\2\"\'\5\16\b\2#\'\5\f\7\2$\'\5\n\6\2%\'\5"+
		"\b\5\2& \3\2\2\2&!\3\2\2\2&\"\3\2\2\2&#\3\2\2\2&$\3\2\2\2&%\3\2\2\2\'"+
		"\61\3\2\2\2(/\t\2\2\2)\60\5\20\t\2*\60\5\22\n\2+\60\5\16\b\2,\60\5\f\7"+
		"\2-\60\5\n\6\2.\60\5\b\5\2/)\3\2\2\2/*\3\2\2\2/+\3\2\2\2/,\3\2\2\2/-\3"+
		"\2\2\2/.\3\2\2\2\60\62\3\2\2\2\61(\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2"+
		"\63\64\3\2\2\2\64\7\3\2\2\2\65;\5\20\t\2\66;\5\22\n\2\67;\5\16\b\28;\5"+
		"\f\7\29;\5\n\6\2:\65\3\2\2\2:\66\3\2\2\2:\67\3\2\2\2:8\3\2\2\2:9\3\2\2"+
		"\2;D\3\2\2\2<B\t\3\2\2=C\5\20\t\2>C\5\22\n\2?C\5\16\b\2@C\5\f\7\2AC\5"+
		"\n\6\2B=\3\2\2\2B>\3\2\2\2B?\3\2\2\2B@\3\2\2\2BA\3\2\2\2CE\3\2\2\2D<\3"+
		"\2\2\2EF\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\t\3\2\2\2HN\t\2\2\2IO\5\20\t\2J"+
		"O\5\22\n\2KO\5\16\b\2LO\5\f\7\2MO\5\n\6\2NI\3\2\2\2NJ\3\2\2\2NK\3\2\2"+
		"\2NL\3\2\2\2NM\3\2\2\2O\13\3\2\2\2PT\b\7\1\2QU\5\20\t\2RU\5\22\n\2SU\5"+
		"\16\b\2TQ\3\2\2\2TR\3\2\2\2TS\3\2\2\2UV\3\2\2\2VZ\7\t\2\2W[\5\20\t\2X"+
		"[\5\22\n\2Y[\5\16\b\2ZW\3\2\2\2ZX\3\2\2\2ZY\3\2\2\2[e\3\2\2\2\\]\f\3\2"+
		"\2]a\7\t\2\2^b\5\20\t\2_b\5\22\n\2`b\5\16\b\2a^\3\2\2\2a_\3\2\2\2a`\3"+
		"\2\2\2bd\3\2\2\2c\\\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2f\r\3\2\2\2g"+
		"e\3\2\2\2hk\5\20\t\2ik\5\22\n\2jh\3\2\2\2ji\3\2\2\2kl\3\2\2\2lp\7\b\2"+
		"\2mq\5\20\t\2nq\5\22\n\2oq\5\16\b\2pm\3\2\2\2pn\3\2\2\2po\3\2\2\2q\17"+
		"\3\2\2\2rs\7\3\2\2st\5\4\3\2tu\7\4\2\2u\21\3\2\2\2v\u0080\7\16\2\2wx\7"+
		"\5\2\2x~\7\16\2\2y{\t\4\2\2z|\7\r\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2\2}\177"+
		"\7\16\2\2~y\3\2\2\2~\177\3\2\2\2\177\u0081\3\2\2\2\u0080w\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\23\3\2\2\2\23\36&/\63:BFNTZaejp{~\u0080";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}