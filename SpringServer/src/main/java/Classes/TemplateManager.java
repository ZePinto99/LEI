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
    int tamanho;
    int tamanhoMax;


    public TemplateManager(){
        values = new Values();
        tamanhoMax = 100;
    }


    public String getFirstTemplate(String tipoNoticia){

        noticia="";
        tamanho = 0;
        Map<String, Integer> templatePlusKeywordsMap = new HashMap<>();
        Map<String, Integer> templatePlusIdMap       = new HashMap<>();
        Map<String, Integer> templatePlusSizeMap     = new HashMap<>();

        try {
            System.out.println("tipo: " + tipoNoticia);
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT text, keywords, id_template, size FROM template, version WHERE template.version = id_version AND version.type = " + tipoNoticia + ";";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templatePlusKeywordsMap.put(rs.getString(1), rs.getInt(2));
                templatePlusIdMap.put(rs.getString(1), rs.getInt(3));
                templatePlusSizeMap.put(rs.getString(1), rs.getInt(4));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //escolher 1o template
        String template = selectTemplate(templatePlusKeywordsMap);
        updateWithSelectedTemplate(template, templatePlusKeywordsMap,templatePlusIdMap, templatePlusSizeMap);

        //chamar metodo para escolher e gerar proximos templates
        templateLoop();

        return fillScript(noticia);
    }


    public void templateLoop(){

        Map<String, Integer> templatePlusKeywordsMap = new HashMap<>();
        Map<String, Integer> templatePlusIdMap       = new HashMap<>();
        Map<String, Integer> templatePlusSizeMap     = new HashMap<>();

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

            String sql = "SELECT text, keywords, size, id_template FROM template WHERE id_template NOT IN (" + listIds + ") ;";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templatePlusKeywordsMap.put(rs.getString(1), rs.getInt(2));
                templatePlusIdMap.put(rs.getString(1), rs.getInt(3));
                templatePlusSizeMap.put(rs.getString(1), rs.getInt(4));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //escolher template da lista selecionada
        //escolhe template o template se este não passar do limite
        boolean invalidTemplate = true;
        int attempts = 0;
        //tenta adicionar x vezes
        while (invalidTemplate && attempts<templatePlusSizeMap.keySet().size()){
            String template = selectTemplate(templatePlusKeywordsMap);
            if ((templatePlusSizeMap.get(template)+tamanho) < tamanhoMax)
                invalidTemplate = false;
                updateWithSelectedTemplate(template, templatePlusKeywordsMap, templatePlusIdMap, templatePlusSizeMap);
        }

        //Temos de ver se passou do limite ou se está perto de passar
        if(tamanho<tamanhoMax && !invalidTemplate){
            templateLoop();
        }
    }


    public String selectTemplate(Map<String,Integer> templatePlusKeywordsMap){
        //random.nextInt(max - min) + min;
        Random random = new Random();

        int size = templatePlusKeywordsMap.size();
        System.out.println("size:" + size);

        int randIndex;
        if(size == 1) randIndex = 0;
        else randIndex = random.nextInt(size - 1);

        List<String> templatesList = new ArrayList<String>(templatePlusKeywordsMap.keySet());
        String template = templatesList.get(randIndex);

        return  template;
    }


    public int updateWithSelectedTemplate(String template,Map<String,Integer> templatePlusKeywordsMap, Map<String,Integer> templatePlusIdMap,  Map<String,Integer> templatePlusSizeMap){

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

            List<Integer> keywordsCount = values.getKeywords();

            while (rs.next()) {
                for (int count = 0; count < 23; count++){

                    keywordsCount.set(count, rs.getInt(count+1)+keywordsCount.get(count));
                }
            }
            values.addToNumberOfTemplates();
            values.setKeywords(keywordsCount);
            noticia +=template + " ";
            tamanho += templatePlusSizeMap.get(template);
            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return templatePlusSizeMap.get(template);
    }


    public String fillScript(String template){
        try {
            noticiaLexer lexer = new noticiaLexer(CharStreams.fromString(template));
            System.out.println("checkpoint1");
            CommonTokenStream stream = new CommonTokenStream(lexer);
            System.out.println("checkpoint2");
            noticiaParser noticiasParser = new noticiaParser(stream);
            System.out.println("checkpoint3");
            StringBuilder noticia = new StringBuilder();
            System.out.println("checkpoint4");
            noticiasParser.noticias(values, noticia);
            System.out.println("Esta é a noticia: " + noticia.toString());
            return noticia.toString();
        }
        catch (Exception e){
            return e.toString();
        }

    }

}
