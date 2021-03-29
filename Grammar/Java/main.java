import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class main {

    public static void main(String[] args){
        try {
            String template = "O \"TITULO_JOG\" do \"CLUBE\" assinalou no ultimo jogo a sua \"NR_JOGOS_JOG\". \"NOME_JOG\" estreou-se a \"ESTREIA_JOG\", numa partida onde o \"CLUBE\" enfrentou o \"ADVERSARIO\", com um resultado final de \"RESULTADO_ESTREIA\".";
            noticiaLexer lexer = new noticiaLexer(CharStreams.fromString(template));
            CommonTokenStream stream = new CommonTokenStream(lexer);
            noticiaParser noticiasParser = new noticiaParser(stream);
            StringBuilder noticia = new StringBuilder();
            noticiasParser.noticias(new Values(), noticia);
            System.out.println("Esta é a noticia: "+noticia.toString());
        }
        catch (Exception e){System.out.println(e);}
    }
}
