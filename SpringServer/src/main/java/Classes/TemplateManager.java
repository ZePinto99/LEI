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

        noticia = "";
        tamanho = 0;
        Map<Integer, Integer> templateIdPlusKeywordsMap         = new HashMap<>();
        Map<Integer, String> templateIdPlusTemplateMap          = new HashMap<>();
        Map<Integer, Integer> templateIdPlusSizeMap             = new HashMap<>();
        Map<Integer, Integer> templateIdPlusVersionMap          = new HashMap<>();

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
        int templateId = selectTemplate(templateIdPlusKeywordsMap);
        updateWithSelectedTemplate(templateId, templateIdPlusKeywordsMap,templateIdPlusTemplateMap, templateIdPlusSizeMap, templateIdPlusVersionMap);

        //chamar metodo para escolher e gerar proximos templates
        templateLoop();

        return fillScript(noticia);
    }


    public void templateLoop(){

        Map<Integer, Integer> templateIdPlusKeywordsMap = new HashMap<>();
        Map<Integer, String> templateIdPlusTemplateMap  = new HashMap<>();
        Map<Integer, Integer> templateIdPlusSizeMap     = new HashMap<>();
        Map<Integer, Integer> templateIdPlusVersionMap  = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            String usedIdsString        = listToSqlQuery(usedIds);
            String usedVersionsString   = listToSqlQuery(usedVersions);

            //adicionar filtros na query para ver keyWords relevantes

            int size = templateIdPlusTemplateMap.size();

            String sql = "SELECT text, keywords, size, id_template, id_version FROM template, keywords WHERE primary = 0 and id_template NOT IN (" + usedIdsString + ") and version NOT IN (" +usedVersionsString + ") and template.keywords = id_keywords " +keywordsSqlString(size) + ";";

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
            Integer templateId = selectTemplate(templateIdPlusKeywordsMap);
            if ((templateIdPlusSizeMap.get(templateId)+tamanho) < tamanhoMax)
                invalidTemplate = false;
                updateWithSelectedTemplate(templateId, templateIdPlusKeywordsMap, templateIdPlusTemplateMap, templateIdPlusSizeMap, templateIdPlusVersionMap);
        }

        //Temos de ver se passou do limite ou se está perto de passar
        if(tamanho<tamanhoMax && !invalidTemplate){
            templateLoop();
        }
    }

    /*
    * método para passar lista de Integer para formato SQL
    * */
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

    /*
    * método que deve retornar, em formato de sql, os campos da tabela keywords que NÃO devem fazer parte do
    * próximo template a ser selecionado
    * */
    public String keywordsSqlString(int size){
        String keywordsSql = "";
        List<Integer> keySize = values.getKeywords();
        Map<Integer,Integer> keywordWithUsedTimes = new HashMap<Integer, Integer>();
        int num = 0;
        for(Integer key : keySize) {
            keywordWithUsedTimes.put(num,key);
            num++;
        }
        for(Integer key : keywordWithUsedTimes.keySet()){
            /*
            * se for para não usar, metodo retorna false
            * */
            if(checkIfUsable(key,size, keywordWithUsedTimes.get(key)))
                continue;//passa ao proximo registo




            keywordsSql+= "and ";


            switch (key){
                case 0:
                    keywordsSql += "Nome_JOG = 0 ";
                    break;
                case 1:
                    keywordsSql += "IDADE_JOG = 0 ";
                    break;
                case 2:
                    keywordsSql += "POS_JOG = 0 ";
                    break;
                case 3:
                    keywordsSql += "NR_GOLOS_JOG = 0 ";
                    break;
                case 4:
                    keywordsSql += "NR_JOGOS_JOG = 0 ";
                    break;
                case 5:
                    keywordsSql += "CLUBE = 0 ";
                    break;
                case 6:
                    keywordsSql += "COMPETICAO = 0 ";
                    break;
                case 7:
                    keywordsSql += "TREINADOR = 0 ";
                    break;
                case 8:
                    keywordsSql += "ARBITRO = 0 ";
                    break;
                case 9:
                    keywordsSql += "JORNADA = 0 ";
                    break;
                case 10:
                    keywordsSql += "RESULTADO_JORNADA = 0 ";
                    break;
                case 11:
                    keywordsSql += "CASA/FORA = 0 ";
                    break;
                case 12:
                    keywordsSql += "ADVERSARIO = 0 ";
                    break;
                case 13:
                    keywordsSql += "MARCADOR_JORNADA = 0 ";
                    break;
                case 14:
                    keywordsSql += "ESTREIA_JOG = 0 ";
                    break;
                case 15:
                    keywordsSql += "NR_JOGOS_INV = 0 ";
                    break;
                case 16:
                    keywordsSql += "NR_GOLOS_JOG_JR = 0 ";
                    break;
                case 17:
                    keywordsSql += "NR_JOGOS_SGOLOS_JOG = 0 ";
                    break;
                case 18:
                    keywordsSql += "EX_TREINADOR = 0 ";
                    break;
                case 19:
                    keywordsSql += "NOME_TOP = 0 ";
                    break;
                case 20:
                    keywordsSql += "NR_JOGOS_TOP = 0 ";
                    break;
                case 21:
                    keywordsSql += "NAC_TOP_JOG = 0 ";
                    break;
                case 22:
                    keywordsSql += "NR_GOLOS_TOP = 0 ";
                    break;
                case 23:
                    keywordsSql += "NR_GOLOS_JOG_TOTAL = 0 ";
                    break;
                case 24:
                    keywordsSql += "NR_JOGOS_JOG_TOTAL = 0 ";
                    break;
            }

        }
        return keywordsSql;
    }

    /*
    * método que deve verificar se uma keyword deve ou não ser usada.
    * Deve ter em conta o tipo da noticia e o numero de vezes usada.
    * */
    public boolean checkIfUsable(Integer key, Integer size, Integer valueOfKey){
        if(valueOfKey<size)
            return true;
        return false;
    }

    /*
    * método que atribui uma probabilidade aleatória a um template de ser escolhido
    * */
    public Integer selectTemplate(Map<Integer,Integer> templateIdPlusKeywordsMap){
        //random.nextInt(max - min) + min;
        Random random = new Random();

        int size = templateIdPlusKeywordsMap.size();
        System.out.println("size:" + size);

        int randIndex;
        if(size == 1) randIndex = 0;
        else randIndex = random.nextInt(size - 1);

        List<Integer> templatesList = new ArrayList<Integer>(templateIdPlusKeywordsMap.keySet());
        Integer template = templatesList.get(randIndex);

        return template;
    }

    /*
     * método que atribui uma probabilidade a um template ser escolhido com base nas keywords já usadas, classificação e nrº de utiliziações
     * */
    //Estou a assumir que recebo um map com o as keywords e as vezes que aparecem na notícia
    public Integer selectTemplateWithScore(Map<Integer,Integer> templateIdPlusKeywordsMap, Map<String, Integer> keywordsAlreadyUsed){

        //random.nextInt(max - min) + min;
        //Probabilidade que os templates têm de bater
        /////////Temos de ver qual prob funciona melhor aqui!!!//////////
        Random random = new Random();
        double randProb = random.nextInt(1);

        //Criamos o Activator com o valor da constante
        Activator activator = new Activator(100);

        double max = 0;
        int maxTmpId = 0;
        int limitOfTries = templateIdPlusKeywordsMap.keySet().size(); //É preciso ver que número podemos meter aqui
        int iteration = 0;

        while (iteration < limitOfTries) {
            //Selecionamos um template aleatório
            int templateIndex = selectTemplate(templateIdPlusKeywordsMap);
            List<String> templateKeywords = new ArrayList<>(); //É PRECISO IR BUSCAR AS KEYWORDS DO TEMPLATE//

            //Calculamos a probabilidade do template ser selecionado com base nas suas keywords
            //recebe a lista de keywords do template e keywords já usadas na notícia
            double templateProb = activator.chooseTemp(templateKeywords ,keywordsAlreadyUsed);

            if (templateProb> randProb)
                return templateIndex;

            if (templateProb > max){
                max     = templateProb;
                maxTmpId = templateIndex;
            }
        }

        return maxTmpId;
    }

    public int updateWithSelectedTemplate(Integer template,Map<Integer,Integer> templateIdPlusKeywordsMap, Map<Integer,String> templateIdPlusTemplateMap,  Map<Integer,Integer> templateIdPlusSizeMap, Map<Integer,Integer> templateIdPlusVersionMap){

        usedIds.add(template);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT * FROM keywords WHERE id_keywords = " + templateIdPlusKeywordsMap.get(template) + ";";

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

            // proximas 3 linhas, são necessárias?
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
