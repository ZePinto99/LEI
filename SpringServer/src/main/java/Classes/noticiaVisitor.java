package Classes;// Generated from C:/Users/franc/Documents/LEI\noticia.g4 by ANTLR 4.8

    import java.io.*;
    import java.text.*;
    import java.util.*;
    import java.util.stream.*;
    import java.sql.Timestamp;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link noticiaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface noticiaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link noticiaParser#noticias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoticias(noticiaParser.NoticiasContext ctx);
	/**
	 * Visit a parse tree produced by {@link noticiaParser#texto}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTexto(noticiaParser.TextoContext ctx);
	/**
	 * Visit a parse tree produced by {@link noticiaParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(noticiaParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link noticiaParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(noticiaParser.StringContext ctx);
}