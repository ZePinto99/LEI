package Classes;
import java.sql.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.*;

public class TemplateManager {

    public static Map<String, Integer> getFirstTemplate(String tipoNoticia){

        Map<String, Integer> templateMap = new HashMap<>();

        try {
            System.out.println("tipo: " + tipoNoticia);
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT text, keywords FROM template WHERE type = " + tipoNoticia + ";";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templateMap.put(rs.getString(1), rs.getInt(2));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return templateMap;
    }

    public static String selectTemplate(Map<String, Integer> relatedScripts, Values values){

        //random.nextInt(max - min) + min;
        Random random = new Random();
        int randIndex = random.nextInt(relatedScripts.size() - 1) + 1;

        List<String> templatesList = new ArrayList<String>(relatedScripts.keySet());
        String firstTemplate = templatesList.get(randIndex);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT * FROM keywords WHERE id_keywords = " + relatedScripts.get(firstTemplate) + ";";

            ResultSet rs = select.executeQuery(sql);

            List<Integer> keywordsCount = new ArrayList<>();

            while (rs.next()) {
                for (int count = 0; count <5; count++){
                    keywordsCount.set(count, rs.getInt(count+1));
                }
            }

            values.setKeywords(keywordsCount);

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Chamar métoda para seleção segundo e terceiro template. Usar values para ver keywords usadas. chamar fillScript com os 3 templates

        return fillScript(firstTemplate,values);
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
