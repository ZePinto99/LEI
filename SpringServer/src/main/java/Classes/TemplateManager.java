package Classes;
import java.sql.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.*;

public class TemplateManager {


    private List<Integer> usedIds                        = new ArrayList<>();
    private List<String> templatesToUse                  = new ArrayList<>();
    private Values values;
    String noticia;

    public TemplateManager(){
        values = new Values();
    }

    public String getFirstTemplate(String tipoNoticia){

        noticia="";
        Map<String, Integer> templatePlusKeywordsMap = new HashMap<>();
        Map<String, Integer> templatePlusIdMap       = new HashMap<>();

        try {
            System.out.println("tipo: " + tipoNoticia);
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT text, keywords, id_template FROM template WHERE type = " + tipoNoticia + ";";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templatePlusKeywordsMap.put(rs.getString(1), rs.getInt(2));
                templatePlusIdMap.put(rs.getString(1), rs.getInt(3));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //escolher 1o template
        selectTemplate(templatePlusKeywordsMap,templatePlusIdMap);

        //chamar metodo para escolher e gerar proximos templates
        templateLoop();

        return fillScript(noticia);
    }


    public void templateLoop(){

        Map<String, Integer> templatePlusKeywordsMap = new HashMap<>();
        Map<String, Integer> templatePlusIdMap       = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            String listIds = "";
            boolean firstTime = true;
            for(Integer id : usedIds){
                if(firstTime) {
                    listIds += id;
                    firstTime = false;
                }
                else
                    listIds +="," + id;
            }

            //adicionar filtros na query para ver keyWords relevantes

            String sql = "SELECT text, keywords, id_template FROM template WHERE id_template NOT IN (" + listIds + ") ;";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templatePlusKeywordsMap.put(rs.getString(1), rs.getInt(2));
                templatePlusIdMap.put(rs.getString(1), rs.getInt(3));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //escolher template da lista selecionada
        selectTemplate(templatePlusKeywordsMap,templatePlusIdMap);


        //adicionar validaçao do numero de palavras neste if
        if(values.getNumberOfTemplates()<3){
            templateLoop();
        }
        return;
    }


    public void selectTemplate(Map<String,Integer> templatePlusKeywordsMap, Map<String,Integer> templatePlusIdMap){

        //random.nextInt(max - min) + min;
        Random random = new Random();
        int randIndex = random.nextInt(templatePlusKeywordsMap.size() - 1) + 1;

        List<String> templatesList = new ArrayList<String>(templatePlusKeywordsMap.keySet());
        String template = templatesList.get(randIndex);
        usedIds.add(templatePlusIdMap.get(template));
        templatesToUse.add(template);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT * FROM keywords WHERE id_keywords = " + templatePlusKeywordsMap.get(template) + ";";

            ResultSet rs = select.executeQuery(sql);

            List<Integer> keywordsCount = new ArrayList<>();

            while (rs.next()) {
                for (int count = 0; count <5; count++){
                    keywordsCount.set(count, rs.getInt(count+1));
                }
            }
            values.addToNumberOfTemplates();
            values.setKeywords(keywordsCount);
            noticia +=template + " ";
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


    public String fillScript(String template){
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
