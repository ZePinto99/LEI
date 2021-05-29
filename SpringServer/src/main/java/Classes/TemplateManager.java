package Classes;

import java.sql.*;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TemplateManager {


    String temaNoticia;
    String idjog;
    String valoralerta;
    String tipoComp;
    String comp;

    private List<Integer> usedIds = new ArrayList<>();
    private List<Integer> usedVersions = new ArrayList<>();
    private Values values;
    String noticia;
    String noticiaGeral;
    boolean competicao = true;

    int tamanho;
    int tamanhoMax;
    int size;
    Map<Integer, Integer> templateIdPlusKeywordsMap;
    Map<Integer, String> templateIdPlusTemplateMap;
    Map<Integer, Integer> templateIdPlusSizeMap;
    Map<Integer, Integer> templateIdPlusVersionMap;


    public TemplateManager(String id) {
        values = new Values(id);
        values.fillValuesMap();
        tamanhoMax = 80;
    }


    public String getFirstTemplate(Tags tg) {
        noticia = "";
        temaNoticia = tg.getTipoAlerta();
        idjog = tg.getIdJogador();
        tipoComp = tg.getTipoComp();
        valoralerta = tg.getValorAlerta();
        noticiaGeral = "";
        tamanho = 0;
        templateIdPlusKeywordsMap = new HashMap<>();
        templateIdPlusTemplateMap = new HashMap<>();
        templateIdPlusSizeMap = new HashMap<>();
        templateIdPlusVersionMap = new HashMap<>();
        size = 1;
        try {


            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetoleibd.cpjxfbj4rl9p.eu-west-3.rds.amazonaws.com:3306/mydb?user=Grupo58&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            String sql = "SELECT text, keywords, id_template, size, version FROM template, version WHERE template.version = id_version AND template.primary = 1 AND version.type = " + temaNoticia + ";";

            ResultSet rs = select.executeQuery(sql);

            while (rs.next()) {
                templateIdPlusKeywordsMap.put(rs.getInt(3), rs.getInt(2));
                templateIdPlusTemplateMap.put(rs.getInt(3), rs.getString(1));
                templateIdPlusSizeMap.put(rs.getInt(3), rs.getInt(4));
                templateIdPlusVersionMap.put(rs.getInt(3), rs.getInt(5));
            }

            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        ExternalDBAccess eDBA = new ExternalDBAccess();
        comp = eDBA.getComp(tipoComp);
        //escolher 1o template
        noticiaGeral = "Titulo: " + getTitulo() + " " + eDBA.getName(idjog) + " \n\n" ;
        int templateId = selectTemplate();
        if (templateId != -1) updateWithSelectedTemplate(templateId);

        //chamar metodo para escolher e gerar proximos templates
        templateLoop();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        noticiaGeral += ("\n\nNotícia da autoria do gerador automático de notícias do Sporting Clube de Braga em " + formatter.format(date));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetoleibd.cpjxfbj4rl9p.eu-west-3.rds.amazonaws.com:3306/mydb?user=Grupo58&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement insert = conn.createStatement();


            String sql = "INSERT INTO history VALUES(DEFAULT, NOW(), '" + noticiaGeral + "','" + listToSqlQuery(usedIds) + "');";

            insert.execute(sql);

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }


        return noticiaGeral;
    }

    public String getTitulo() {
        switch (temaNoticia) {
            case "1":
                return "Golos marcados nesta época";
            case "2":
                return "Golos marcados (total)";
            case "3":
                return "Número jogos (esta época)";
            case "4":
                return "Número jogos (total)";
        }
        return "";
    }

    public void templateLoop() {

        System.out.println("Entrou templateLoop");

        templateIdPlusKeywordsMap = new HashMap<>();
        templateIdPlusTemplateMap = new HashMap<>();
        templateIdPlusSizeMap = new HashMap<>();
        templateIdPlusVersionMap = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetoleibd.cpjxfbj4rl9p.eu-west-3.rds.amazonaws.com:3306/mydb?user=Grupo58&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");


            Statement select = conn.createStatement();
            String usedIdsString = listToSqlQuery(usedIds);
            String usedVersionsString = listToSqlQuery(usedVersions);

            //adicionar filtros na query para ver keyWords relevantes



            String sql = "SELECT text, keywords, id_template, size, version FROM template, keywords WHERE template.primary = 0 and id_template NOT IN (" + usedIdsString + ") and version NOT IN (" + usedVersionsString + ") and template.keywords = id_keywords " + keywordsSqlString(size) + ";";

            ResultSet rs = select.executeQuery(sql);
            if (rs == null) return;
            while (rs.next()) {
                templateIdPlusKeywordsMap.put(rs.getInt(3), rs.getInt(2));
                templateIdPlusTemplateMap.put(rs.getInt(3), rs.getString(1));
                templateIdPlusSizeMap.put(rs.getInt(3), rs.getInt(4));
                templateIdPlusVersionMap.put(rs.getInt(3), rs.getInt(5));
            }


            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }

        //vai buscar um map com as keywords e o número de vezes que foram utilizadas
        Map<String, Integer> keywordsAlreadyUsed = values.keywords;

        int templateId = selectTemplateWithScore(keywordsAlreadyUsed);
        if (templateId == -1) return;
        updateWithSelectedTemplate(templateId);

        if (tamanho < tamanhoMax) {

            size++;
            templateLoop();
        }
    }

    /*
     * método para passar lista de Integer para formato SQL
     * */
    public String listToSqlQuery(List<Integer> lst) {

        String listIds = "";
        boolean firstTime = true;
        for (Integer id : lst) {
            if (firstTime) {
                listIds += id;
                firstTime = false;
            } else
                listIds += "," + id;
        }
        return listIds;
    }

    /*
     * método que deve retornar, em formato de sql, os campos da tabela keywords que NÃO devem fazer parte do
     * próximo template a ser selecionado
     * */
    public String keywordsSqlString(int size) {
        String keywordsSql = "";
        Map<String, Integer> keywordsMap = values.getKeywords();

        for (String key : keywordsMap.keySet()) {
            /*
             * se for para não usar, metodo retorna false
             * */
            if (checkIfUsable(key, size, keywordsMap.get(key)))
                continue;//passa ao proximo registo

            keywordsSql += "and ";

            keywordsSql += key + " = 0 ";

        }
        return keywordsSql;
    }

    /*
     * método que deve verificar se uma keyword deve ou não ser usada.
     * Deve ter em conta o tipo da noticia e o numero de vezes usada.
     * */
    //Está a receber a keyword, o size que é passado como input em keywordsSqlString e o número de vezes que a keyword se repete
    public boolean checkIfUsable(String key, int size, int valueOfKey) {

        List<String> not_to_use_1_2_3_4 = Arrays.asList("TREINADOR", "ARBITRO", "NR_JOGOS_INV", "NR_JOGOS_SGOLOS_JOG", "EX_TREINADOR");


        List<String> t1 = Arrays.asList("Nome_JOG", "POS_JOG", "NR_GOLOS_JOG_JR", "NR_JOGOS_JOG");

        List<String> t2 = Arrays.asList("Nome_JOG", "POS_JOG", "NR_GOLOS_JOG_JR", "NR_JOGOS_JOG_TOTAL", "NR_GOLOS_TOP");

        List<String> t3 = Arrays.asList("Nome_JOG", "POS_JOG", "NR_GOLOS_JOG");

        List<String> t4 = Arrays.asList("Nome_JOG", "POS_JOG", "ESTREIA_JOG");


        if (not_to_use_1_2_3_4.contains(key)) {
            return false;
        }

        if (valueOfKey == 0)
            return true;

        double sizeD = size;
        double valueD = valueOfKey + 1;
        if ((sizeD) / valueD >= 1 )//&& (temaNoticia.equals("1") && t1.contains(key) || temaNoticia.equals("2") && t2.contains(key) || temaNoticia.equals("3") && t3.contains(key) || temaNoticia.equals("4") && t4.contains(key)))
            return true;

        return false;
    }

    /*
     * método que atribui uma probabilidade aleatória a um template de ser escolhido
     * */
    public Integer selectTemplate() {
        //random.nextInt(max - min) + min;
        Random random = new Random();

        int size = templateIdPlusKeywordsMap.size();
        int template;

        int randIndex;
        if (size == 1 || size == 0) return -1;
        else {
            randIndex = random.nextInt(size - 1);
            List<Integer> templatesList = new ArrayList<Integer>(templateIdPlusKeywordsMap.keySet());
            template = templatesList.get(randIndex);
        }

        return template;
    }

    /*
     * método que seleciona um template com base nas keywords já usadas, classificação e nrº de utiliziações
     * */
    public Integer selectTemplateWithScore(Map<String, Integer> keywordsAlreadyUsed) {

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //random.nextInt(max - min) + min;
        //Score/Probabilidade que os templates têm de bater (o facto de ser aleatória significa que todos os templates podem aparecer
        //                                                   mas quanto maior for o score do template maior a probabilidade de ser escolhido)
        // nota: Existe um problema com esta abordagem. Se tivermos um randProb muito alto podemos não conseguir escolher nenhum template.
        //       Para resolver isto temos um max e maxTmpId que vão armazenado o template com melhor score
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Random random = new Random();
        double randProb = random.nextInt(1);

        //Criamos o Activator com o valor da constante
        Activator activator = new Activator(500);

        double max = -1;
        int maxTmpId = -1;
        int limitOfTries = templateIdPlusKeywordsMap.keySet().size(); //É preciso ver que número podemos meter aqui
        int iteration = 0;

        while (iteration < limitOfTries + 5) {
            //Selecionamos um template aleatório
            int templateIndex = selectTemplate();
            if (templateIndex == -1) return templateIndex;
            iteration++;
            /////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ////////////////////////
            /////////////////////// É preciso ver o que templateIdPlusKeywordsMap devolve ////////////////////////
            /////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ////////////////////////
            List<String> templateKeywords = new ArrayList<>(); //!!!É PRECISO IR BUSCAR AS KEYWORDS DO TEMPLATE!!!//

            //Calculamos o score para o template ser selecionado com base nas suas keywords
            //recebe a lista de keywords do template e keywords já usadas na notícia
            double templateProb = activator.tempScore(templateKeywords, keywordsAlreadyUsed);
            //Se o score do template bater o randProb e o tamanho do template for válido o template é logo selecionado

            if (templateProb > randProb && (templateIdPlusSizeMap.get(templateIndex) + tamanho) < tamanhoMax)
                return templateIndex;

            //Se o score não chegar para bater o randProb, mas se for o template com score mais alto é guardado
            if (templateProb > max && (templateIdPlusSizeMap.get(templateIndex) + tamanho) < tamanhoMax) {
                max = templateProb;
                maxTmpId = templateIndex;
            }

        }
        //retornamos o template com maior score
        return maxTmpId;
    }

    public int updateWithSelectedTemplate(int template) {



        System.out.println("In updateWithSelectedTemplate");
        usedIds.add(template);

        Map<String, Integer> keywordsTemplate = new HashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetoleibd.cpjxfbj4rl9p.eu-west-3.rds.amazonaws.com:3306/mydb?user=Grupo58&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT * FROM keywords WHERE id_keywords = " + templateIdPlusKeywordsMap.get(template) + ";";

            ResultSet rs = select.executeQuery(sql);
            if (rs == null) return -1;
            Map<String, Integer> keywordsCount = values.getKeywords();

            keywordsTemplate = values.keywordsList.stream().collect(Collectors.toMap(Function.identity(), i -> 0));

            while (rs.next()) {
                int i = 1;
                for (String key : values.keywordsList) {
                    keywordsCount.put(key, rs.getInt(i + 1) + keywordsCount.get(key));
                    keywordsTemplate.put(key, rs.getInt(i + 1));
                    i++;
                }
            }
            values.addToNumberOfTemplates();
            values.setKeywords(keywordsCount);
            noticia = templateIdPlusTemplateMap.get(template) + " ";
            tamanho += templateIdPlusSizeMap.get(template);


            usedVersions.add(templateIdPlusVersionMap.get(template));


            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        noticiaGeral += fillScript(noticia,keywordsTemplate);


        if (values.getKeywords().get("COMPETICAO") > 0 && competicao) {
            competicao = false;
            if(values.getValue("COMPETICAO").equals("na Liga NOS"))
                values.putValueInMap("COMPETICAO","em todas as competicoes");

        }
        return templateIdPlusSizeMap.get(template);
    }


    public String fillScript(String template, Map<String, Integer> keywordsTemplate) {
        try {

            //codigo para ir buscar a bd das babes
            System.out.println("In fillScript");
            ExternalDBAccess eDBA = new ExternalDBAccess();

            Values templateValues =  eDBA.getValues(keywordsTemplate, idjog, comp, tipoComp);

            noticiaLexer lexer = new noticiaLexer(CharStreams.fromString(template));
            CommonTokenStream stream = new CommonTokenStream(lexer);
            noticiaParser noticiasParser = new noticiaParser(stream);
            StringBuilder noticia = new StringBuilder();
            noticiasParser.noticias(templateValues, noticia);


            //System.out.println("título: " + titulo + "noticia: " + noticia);
            return noticia.toString();
        } catch (Exception e) {

            return e.toString();
        }

    }

}
