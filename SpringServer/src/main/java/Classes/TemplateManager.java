package Classes;
import java.sql.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.util.*;

public class TemplateManager {


    private List<Integer> usedIds                             = new ArrayList<>();
    private List<Integer> usedVersions                        = new ArrayList<>();
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
        Map<Integer, Integer> templateIdPlusKeywordsMap = new HashMap<>();
        Map<Integer, String> templateIdPlusTemplateMap       = new HashMap<>();
        Map<Integer, Integer> templateIdPlusSizeMap     = new HashMap<>();
        Map<Integer, Integer> templateIdPlusVersionMap     = new HashMap<>();

        try {
            System.out.println("tipo: " + tipoNoticia);
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT text, keywords, id_template, size, id_version FROM template, version WHERE template.version = id_version AND version.type = " + tipoNoticia + ";";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templateIdPlusKeywordsMap.put(rs.getInt(3), rs.getInt(2));
                templateIdPlusTemplateMap.put(rs.getInt(3), rs.getString(1));
                templateIdPlusSizeMap.put(rs.getInt(3), rs.getInt(4));
                templateIdPlusVersionMap.put(rs.getInt(3), rs.getInt(5));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //escolher 1o template
        int templateId = selectTemplate(templateIdPlusKeywordsMap,templateIdPlusTemplateMap, templateIdPlusSizeMap, templateIdPlusVersionMap);
        updateWithSelectedTemplate(templateId, templateIdPlusKeywordsMap,templateIdPlusTemplateMap, templateIdPlusSizeMap, templateIdPlusVersionMap);

        //chamar metodo para escolher e gerar proximos templates
        templateLoop();

        return fillScript(noticia);
    }


    public void templateLoop(){

        Map<Integer, Integer> templateIdPlusKeywordsMap = new HashMap<>();
        Map<Integer, String> templateIdPlusTemplateMap       = new HashMap<>();
        Map<Integer, Integer> templateIdPlusSizeMap     = new HashMap<>();
        Map<Integer, Integer> templateIdPlusVersionMap     = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            String usedIdsString        = listToSqlQuery(usedIds);
            String usedVersionsString   = listToSqlQuery(usedVersions);

            //adicionar filtros na query para ver keyWords relevantes

            String sql = "SELECT text, keywords, size, id_template, id_version FROM template WHERE id_template NOT IN (" + usedIds + ") and version NOT IN (" +usedVersionsString + ");";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                System.out.println("in");
                templateIdPlusKeywordsMap.put(rs.getInt(3), rs.getInt(2));
                templateIdPlusTemplateMap.put(rs.getInt(3), rs.getString(1));
                templateIdPlusSizeMap.put(rs.getInt(3), rs.getInt(4));
                templateIdPlusSizeMap.put(rs.getInt(3), rs.getInt(5));
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
        while (invalidTemplate && attempts<templateIdPlusSizeMap.keySet().size()){
            Integer templateId = selectTemplate(templateIdPlusKeywordsMap,templateIdPlusTemplateMap,templateIdPlusSizeMap, templateIdPlusVersionMap);
            if ((templateIdPlusSizeMap.get(templateId)+tamanho) < tamanhoMax)
                invalidTemplate = false;
                updateWithSelectedTemplate(templateId, templateIdPlusKeywordsMap, templateIdPlusTemplateMap, templateIdPlusSizeMap, templateIdPlusVersionMap);
        }

        //Temos de ver se passou do limite ou se está perto de passar
        if(tamanho<tamanhoMax && !invalidTemplate){
            templateLoop();
        }
    }

    public String listToSqlQuery(List<Integer> lst){

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
        return listIds;
    }

    public Integer selectTemplate(Map<Integer,Integer> templateIdPlusKeywordsMap, Map<Integer,String> templateIdPlusTemplateMap, Map<Integer,Integer> templateIdPlusSizeMap, Map<Integer,Integer> templateIdPlusVersionMap){
        //random.nextInt(max - min) + min;
        Random random = new Random();

        int size = templateIdPlusKeywordsMap.size();
        System.out.println("size:" + size);

        int randIndex;
        if(size == 1) randIndex = 0;
        else randIndex = random.nextInt(size - 1);

        List<Integer> templatesList = new ArrayList<Integer>(templateIdPlusTemplateMap.keySet());
        Integer template = templatesList.get(randIndex);

        return template;
    }


    public int updateWithSelectedTemplate(Integer template,Map<Integer,Integer> templateIdPlusKeywordsMap, Map<Integer,String> templateIdPlusTemplateMap,  Map<Integer,Integer> templateIdPlusSizeMap, Map<Integer,Integer> templateIdPlusVersionMap){

        usedIds.add(template);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT * FROM keywords WHERE id_keywords = " + template + ";";

            ResultSet rs = select.executeQuery(sql);

            List<Integer> keywordsCount = values.getKeywords();

            while (rs.next()) {
                for (int count = 0; count < 23; count++){

                    keywordsCount.set(count, rs.getInt(count+1)+keywordsCount.get(count));
                }
            }
            values.addToNumberOfTemplates();
            values.setKeywords(keywordsCount);
            noticia +=templateIdPlusTemplateMap.get(template) + " ";
            tamanho += templateIdPlusSizeMap.get(template);

            List<Integer> versions = values.getVersions();
            versions.add(templateIdPlusVersionMap.get(template));
            values.setVersions(versions);
            usedVersions = versions;

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return templateIdPlusSizeMap.get(template);
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
