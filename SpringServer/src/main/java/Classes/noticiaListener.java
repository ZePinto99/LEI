// Generated from C:/Users/franc/Documents/LEI\noticia.g4 by ANTLR 4.8
package Classes;
    import java.io.*;
    import java.text.*;
    import java.util.*;
    import java.util.stream.*;
    import java.sql.Timestamp;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link noticiaParser}.
 */
public interface noticiaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link noticiaParser#noticias}.
	 * @param ctx the parse tree
	 */
	void enterNoticias(noticiaParser.NoticiasContext ctx);
	/**
	 * Exit a parse tree produced by {@link noticiaParser#noticias}.
	 * @param ctx the parse tree
	 */
	void exitNoticias(noticiaParser.NoticiasContext ctx);
	/**
	 * Enter a parse tree produced by {@link noticiaParser#texto}.
	 * @param ctx the parse tree
	 */
	void enterTexto(noticiaParser.TextoContext ctx);
	/**
	 * Exit a parse tree produced by {@link noticiaParser#texto}.
	 * @param ctx the parse tree
	 */
	void exitTexto(noticiaParser.TextoContext ctx);
	/**
	 * Enter a parse tree produced by {@link noticiaParser#keyword}.
	 * @param ctx the parse tree
	 */
	void enterKeyword(noticiaParser.KeywordContext ctx);
	/**
	 * Exit a parse tree produced by {@link noticiaParser#keyword}.
	 * @param ctx the parse tree
	 */
	void exitKeyword(noticiaParser.KeywordContext ctx);
	/**
	 * Enter a parse tree produced by {@link noticiaParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(noticiaParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link noticiaParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(noticiaParser.StringContext ctx);
}