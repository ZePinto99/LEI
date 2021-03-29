package Classes;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateManager {

    public static List<Template> getRelatedTemplates(String tipoNoticia){

        /*

        Vai ter de ir à bd buscar templates relevantes

         */
        List<Template> realatedScripts = new ArrayList<>(); //preencher com templates


        return realatedScripts;
    }

    public static String selectTemplate(List<Template> relatedScripts, Values values){

        List<Template> finalTemplates = new ArrayList<>();

        /*
        algoritmo de seleção
         */
        int usedScripts = 0;
        String finalScript = "";
        Random random = new Random();
        //Se tivermos mais que um script relacionado e se não tivermos usado até ao número de scripts -1
        while (relatedScripts.size()>1 && usedScripts < relatedScripts.size() -1){
            for (Template script: relatedScripts){
                //Prob de 50% de o script ser escolhido
                //random.nextInt(max - min) + min;
                if((random.nextInt(10 - 1) + 1) < 5){
                    //adc scripts a uma lista de scripts selecionados
                    usedScripts ++;
                    finalTemplates.add(script);
                    relatedScripts.remove(script);
                }
            }
        }


        /*
        preencher templates
         */
        if(relatedScripts.size() == 1)
            finalScript = fillScript(relatedScripts.get(0), values);
        else
            for (Template template : finalTemplates)
                finalScript = finalScript + fillScript(template, values) + " ";

        return finalScript;
    }

    public static String fillScript(Template script, Values values){
        try {
            String template = "O \"TITULO_JOG\" do \"CLUBE\" assinalou no ultimo jogo a sua \"NR_JOGOS_JOG\". \"NOME_JOG\" estreou-se a \"ESTREIA_JOG\", numa partida onde o \"CLUBE\" enfrentou o \"ADVERSARIO\", com um resultado final de \"RESULTADO_ESTREIA\".";
            noticiaLexer lexer = new noticiaLexer(CharStreams.fromString(template));
            CommonTokenStream stream = new CommonTokenStream(lexer);
            noticiaParser noticiasParser = new noticiaParser(stream);
            StringBuilder noticia = new StringBuilder();
            noticiasParser.noticias(new Values(), noticia);
            System.out.println("Esta é a noticia: "+noticia.toString());
            return noticia.toString();
        }
        catch (Exception e){return e.toString();}

    }
}
