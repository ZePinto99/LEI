package Classes;
// Generated from C:/Users/franc/Documents/LEI\noticia.g4 by ANTLR 4.8

import java.io.*;
    import java.text.*;
    import java.util.*;
    import java.util.stream.*;
    import java.sql.Timestamp;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class noticiaLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WORDS=1, KEY=2, ESTRELAS=3, ESTRELASponto=4, WS=5;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"WORDS", "KEY", "ESTRELAS", "ESTRELASponto", "WS"
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


	public noticiaLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "noticia.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\7,\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\6\2\17\n\2\r\2\16\2\20\3\3\3\3\7\3"+
		"\25\n\3\f\3\16\3\30\13\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\5\6#\n\6"+
		"\3\6\3\6\6\6\'\n\6\r\6\16\6(\3\6\3\6\2\2\7\3\3\5\4\7\5\t\6\13\7\3\2\5"+
		"\7\2\"\".\60C\\c|\u00c2\u0101\3\2$$\4\2\13\13\"\"\2\60\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\3\16\3\2\2\2\5\22\3\2\2"+
		"\2\7\33\3\2\2\2\t\36\3\2\2\2\13&\3\2\2\2\r\17\t\2\2\2\16\r\3\2\2\2\17"+
		"\20\3\2\2\2\20\16\3\2\2\2\20\21\3\2\2\2\21\4\3\2\2\2\22\26\7$\2\2\23\25"+
		"\n\3\2\2\24\23\3\2\2\2\25\30\3\2\2\2\26\24\3\2\2\2\26\27\3\2\2\2\27\31"+
		"\3\2\2\2\30\26\3\2\2\2\31\32\7$\2\2\32\6\3\2\2\2\33\34\7,\2\2\34\35\7"+
		",\2\2\35\b\3\2\2\2\36\37\7,\2\2\37 \7A\2\2 \n\3\2\2\2!#\7\17\2\2\"!\3"+
		"\2\2\2\"#\3\2\2\2#$\3\2\2\2$\'\7\f\2\2%\'\t\4\2\2&\"\3\2\2\2&%\3\2\2\2"+
		"\'(\3\2\2\2(&\3\2\2\2()\3\2\2\2)*\3\2\2\2*+\b\6\2\2+\f\3\2\2\2\b\2\20"+
		"\26\"&(\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}