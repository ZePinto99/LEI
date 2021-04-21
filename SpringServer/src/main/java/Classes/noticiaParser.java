// Generated from C:/Users/franc/Documents/LEI\noticia.g4 by ANTLR 4.8
package Classes;
    import java.io.*;
    import java.text.*;
    import java.util.*;
    import java.util.stream.*;
    import java.sql.Timestamp;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class noticiaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WORDS=1, KEY=2, ESTRELAS=3, ESTRELASponto=4, WS=5;
	public static final int
		RULE_noticias = 0, RULE_texto = 1, RULE_keyword = 2, RULE_string = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"noticias", "texto", "keyword", "string"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'**'", "'*?'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WORDS", "KEY", "ESTRELAS", "ESTRELASponto", "WS"
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
	public String getGrammarFileName() { return "noticia.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public noticiaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class NoticiasContext extends ParserRuleContext {
		public Values valores;
		public StringBuilder noticiaIn;
		public KeywordContext keyword;
		public TextoContext texto;
		public List<KeywordContext> keyword() {
			return getRuleContexts(KeywordContext.class);
		}
		public KeywordContext keyword(int i) {
			return getRuleContext(KeywordContext.class,i);
		}
		public List<TextoContext> texto() {
			return getRuleContexts(TextoContext.class);
		}
		public TextoContext texto(int i) {
			return getRuleContext(TextoContext.class,i);
		}
		public NoticiasContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public NoticiasContext(ParserRuleContext parent, int invokingState, Values valores, StringBuilder noticiaIn) {
			super(parent, invokingState);
			this.valores = valores;
			this.noticiaIn = noticiaIn;
		}
		@Override public int getRuleIndex() { return RULE_noticias; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).enterNoticias(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).exitNoticias(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof noticiaVisitor ) return ((noticiaVisitor<? extends T>)visitor).visitNoticias(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoticiasContext noticias(Values valores,StringBuilder noticiaIn) throws RecognitionException {
		NoticiasContext _localctx = new NoticiasContext(_ctx, getState(), valores, noticiaIn);
		enterRule(_localctx, 0, RULE_noticias);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(14); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(14);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case KEY:
					{
					setState(8);
					((NoticiasContext)_localctx).keyword = keyword(_localctx.valores);
					_localctx.noticiaIn.append(((NoticiasContext)_localctx).keyword.value);
					}
					break;
				case WORDS:
					{
					setState(11);
					((NoticiasContext)_localctx).texto = texto();
					_localctx.noticiaIn.append(((NoticiasContext)_localctx).texto.value.replace("\"",""));
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(16); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WORDS || _la==KEY );
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

	public static class TextoContext extends ParserRuleContext {
		public String value;
		public StringContext string;
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TextoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_texto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).enterTexto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).exitTexto(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof noticiaVisitor ) return ((noticiaVisitor<? extends T>)visitor).visitTexto(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TextoContext texto() throws RecognitionException {
		TextoContext _localctx = new TextoContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_texto);
		((TextoContext)_localctx).value = "";
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			((TextoContext)_localctx).string = string();
			((TextoContext)_localctx).value = (((TextoContext)_localctx).string!=null?_input.getText(((TextoContext)_localctx).string.start,((TextoContext)_localctx).string.stop):null);
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

	public static class KeywordContext extends ParserRuleContext {
		public Values valores;
		public String value;
		public Token KEY;
		public TerminalNode KEY() { return getToken(noticiaParser.KEY, 0); }
		public KeywordContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public KeywordContext(ParserRuleContext parent, int invokingState, Values valores) {
			super(parent, invokingState);
			this.valores = valores;
		}
		@Override public int getRuleIndex() { return RULE_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).enterKeyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).exitKeyword(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof noticiaVisitor ) return ((noticiaVisitor<? extends T>)visitor).visitKeyword(this);
			else return visitor.visitChildren(this);
		}
	}

	public final KeywordContext keyword(Values valores) throws RecognitionException {
		KeywordContext _localctx = new KeywordContext(_ctx, getState(), valores);
		enterRule(_localctx, 4, RULE_keyword);
		((KeywordContext)_localctx).value = "";
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			((KeywordContext)_localctx).KEY = match(KEY);
			((KeywordContext)_localctx).value = valores.getValue((((KeywordContext)_localctx).KEY!=null?((KeywordContext)_localctx).KEY.getText():null).replace("\"",""));
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

	public static class StringContext extends ParserRuleContext {
		public TerminalNode WORDS() { return getToken(noticiaParser.WORDS, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof noticiaListener ) ((noticiaListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof noticiaVisitor ) return ((noticiaVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(WORDS);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\7\35\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\3\2\3\2\6\2\21\n\2\r\2\16\2\22\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\2\2\6\2\4\6\b\2\2\2\32\2\20\3\2\2\2"+
		"\4\24\3\2\2\2\6\27\3\2\2\2\b\32\3\2\2\2\n\13\5\6\4\2\13\f\b\2\1\2\f\21"+
		"\3\2\2\2\r\16\5\4\3\2\16\17\b\2\1\2\17\21\3\2\2\2\20\n\3\2\2\2\20\r\3"+
		"\2\2\2\21\22\3\2\2\2\22\20\3\2\2\2\22\23\3\2\2\2\23\3\3\2\2\2\24\25\5"+
		"\b\5\2\25\26\b\3\1\2\26\5\3\2\2\2\27\30\7\4\2\2\30\31\b\4\1\2\31\7\3\2"+
		"\2\2\32\33\7\3\2\2\33\t\3\2\2\2\4\20\22";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}