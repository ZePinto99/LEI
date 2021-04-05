package Classes;
import java.sql.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TemplateManager {

    public static List<String> getRelatedTemplates(String tipoNoticia){

        List<String> templateList = new ArrayList<>();

        try {
            System.out.println("tipo: " + tipoNoticia);
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT text FROM template WHERE type = " + tipoNoticia + ";";
            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templateList.add(rs.getString(1));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return templateList;
    }

    public static String selectTemplate(List<String> relatedScripts, Values values){

        /*
        List<String> finalTemplates = new ArrayList<>();

        algoritmo de seleção

        int usedScripts = 0;
        String finalScript = "";
        Random random = new Random();
        //Se tivermos mais que um script relacionado e se não tivermos usado até ao número de scripts -1
        while (relatedScripts.size()>1 && usedScripts < relatedScripts.size() -1){
            for (String script: relatedScripts){
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
        preencher templates

        if(relatedScripts.size() == 1)
            finalScript = fillScript(relatedScripts.get(0), values);
        else
            for (Template template : finalTemplates)
                finalScript = finalScript + fillScript(template, values) + " ";
        */

        System.out.println(relatedScripts.get(0));

        return fillScript(relatedScripts.get(0),values);
    }

    public static String fillScript(String template, Values values){
        try {
            noticiaLexer lexer = new noticiaLexer(CharStreams.fromString(template));
            System.out.println("error1");
            CommonTokenStream stream = new CommonTokenStream(lexer);
            System.out.println("error2");
            noticiaParser noticiasParser = new noticiaParser(stream);
            System.out.println("error3");
            StringBuilder noticia = new StringBuilder();
            System.out.println("error4");
            noticiasParser.noticias(values, noticia);
            System.out.println("Esta é a noticia: " + noticia.toString());
            return noticia.toString();
        }
        catch (Exception e){
            System.out.println("error");
            return e.toString();
        }

    }
}
