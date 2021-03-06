package Classes;

import java.sql.*;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class TemplateManager {


    String temaNoticia;
    String idjog;
    String valoralerta;
    String tipoComp;
    String comp;

    private final List<Integer> usedIds = new ArrayList<>();
    private final List<Integer> usedVersions = new ArrayList<>();
    private final Values values;
    String titulo;
    String fimNoticia;
    String noticia;
    String noticiaGeral;
    boolean competicao = true;
    int compSum = 0;

    int tamanho;
    int tamanhoMax;
    int size;
    Map<Integer, Integer> templateIdPlusKeywordsMap;
    Map<Integer, String> templateIdPlusTemplateMap;
    Map<Integer, Integer> templateIdPlusSizeMap;
    Map<Integer, Integer> templateIdPlusVersionMap;
    Map<Integer, Map<Integer, Integer>> templateClassification;


    public TemplateManager(String id, int tamanhoMax) {
        values = new Values(id);
        values.fillValuesMap();
        this.tamanhoMax = tamanhoMax;
    }

    /*
     	Método responsável por aceder à Base de Dados e escolher um template inicial adequado, consoante o tipo da notícia e os atributos.
        Após isso, este método invoca o método templateLoop e, aquando do término deste, irá inserir a notícia gerada no histórico, gerar o link para a mesma e, por fim, devolver a notícia gerada.
     */
    public Noticia getFirstTemplate(Tags tg) {
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
        fillScoresMap();

        try {


            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
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

        //escolher 1º template
        titulo = getTitulo() + " " + eDBA.getName(idjog);
        int templateId = selectTemplate();
        if (templateId != -1) updateWithSelectedTemplate(templateId);

        //chamar metodo para escolher e gerar proximos templates
        templateLoop();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        fimNoticia = ("Notícia da autoria do gerador automático de notícias do Sporting Clube de Braga em " + formatter.format(date) + " - Tamanho aproximado: " + tamanho + " palavras.");
        Integer noticiaId = -1;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement insert = conn.createStatement();

            String sql = "INSERT INTO history VALUES(DEFAULT, NOW(), '" + titulo + "','" + noticiaGeral + "','" + noticiaGeral + "','" + fimNoticia + "','" + listToSqlQuery(usedIds) + "'," + tamanho + ",0,0);";

            insert.execute(sql);

            sql = "SELECT id_noticia FROM history ORDER By id_noticia DESC limit 1";

            ResultSet rs = insert.executeQuery(sql);

            while (rs.next()) {
                noticiaId = rs.getInt(1);
            }


        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }

        //Gera link
        String link = link_generator(noticiaId); //PASSAR O ID DA NOTÍCIA


        Noticia noticia = new Noticia(noticiaGeral, titulo, fimNoticia, String.valueOf(noticiaId), link); //PASSAR O ID DA NOTÍCIA

        return noticia;
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

    /*
    	Método recursivo, que seleciona os diversos templates que são elegíveis para seleção e, de seguida, utiliza o método updateWithSelectedTemplate para seleção do template correto.
        Enquanto o tamanho da notícia for inferior ao desejado, este método executa recursivamente.
     */
    public void templateLoop() {

        System.out.println("Entrou templateLoop");

        templateIdPlusKeywordsMap = new HashMap<>();
        templateIdPlusTemplateMap = new HashMap<>();
        templateIdPlusSizeMap = new HashMap<>();
        templateIdPlusVersionMap = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
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


        if (not_to_use_1_2_3_4.contains(key)) {
            return false;
        }

        if (valueOfKey == 0)
            return true;

        double valueD = valueOfKey + 1;
        //&& (temaNoticia.equals("1") && t1.contains(key) || temaNoticia.equals("2") && t2.contains(key) || temaNoticia.equals("3") && t3.contains(key) || temaNoticia.equals("4") && t4.contains(key)))
        return (size) / valueD >= 1;
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
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Random random = new Random();
        double randProb = random.nextInt(1);

        //Criamos o Activator com o valor da constante
        Activator activator = new Activator(500, templateClassification);

        double max = -1;
        int maxTmpId = -1;
        int limitOfTries = templateIdPlusKeywordsMap.keySet().size(); //É preciso ver que número podemos meter aqui
        int iteration = 0;

        while (iteration < limitOfTries + 5) {
            //Selecionamos um template aleatório
            int templateIndex = selectTemplate();
            if (templateIndex == -1) return templateIndex;
            iteration++;

            List<String> templateKeywords = new ArrayList<>(); //!!!É PRECISO IR BUSCAR AS KEYWORDS DO TEMPLATE!!!//

            //Calculamos o score para o template ser selecionado com base nas suas keywords
            //recebe a lista de keywords do template e keywords já usadas na notícia
            double templateProb = activator.tempScore(templateKeywords, keywordsAlreadyUsed, usedIds, templateIndex);
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

    /*
    	Método chamado sempre que é selecionado um template e que é responsável por atualizar a notícia e todos os seus dados, como número de templates, keywords usadas e tamanho com o novo template.
     */
    public int updateWithSelectedTemplate(int template) {


        System.out.println("In updateWithSelectedTemplate");
        usedIds.add(template);

        Map<String, Integer> keywordsTemplate = new HashMap<>();
        Map<String, Integer> keywordsTemplateIter = new HashMap<>();
        Map<String, Integer> keywordsCountStore = new HashMap<String, Integer>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            String sql = "SELECT * FROM keywords WHERE id_keywords = " + templateIdPlusKeywordsMap.get(template) + ";";

            ResultSet rs = select.executeQuery(sql);
            if (rs == null) return -1;
            Map<String, Integer> keywordsCount = values.getKeywords();
            keywordsCountStore = values.getKeywords();

            keywordsTemplate = values.keywordsList.stream().collect(Collectors.toMap(Function.identity(), i -> 0));

            while (rs.next()) {
                int i = 1;
                for (String key : values.keywordsList) {
                    keywordsCount.put(key, rs.getInt(i + 1) + keywordsCount.get(key));
                    keywordsTemplate.put(key, rs.getInt(i + 1));
                    keywordsTemplateIter.put(key,rs.getInt(i + 1));
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

        String result = fillScript(noticia, keywordsTemplateIter);
        if(!result.equals("erro")){
            noticiaGeral += result;
        }else{
            tamanho -= templateIdPlusSizeMap.get(template);
            usedVersions.remove(templateIdPlusVersionMap.get(template));
            values.setKeywords(keywordsCountStore);
            values.subToNumberOfTemplates();
        }



        return templateIdPlusSizeMap.get(template);
    }

    /*
    	Método responsável pelo preenchimento dos valores com informação da base de dados do grupo 6, tirando partido da classe ExternalDBAccess. De seguida, este método invoca diversos métodos da gramática, que passam os valores para a notícia.
     */
    public String fillScript(String template, Map<String, Integer> keywordsTemplate) {
        try {

            //codigo para ir buscar a bd das babes
            System.out.println("In fillScript");
            ExternalDBAccess eDBA = new ExternalDBAccess();

            Values templateValues = eDBA.getValues(keywordsTemplate, idjog, comp, tipoComp);
            if (keywordsTemplate.get("COMPETICAO") >= 1 && competicao) {
                compSum++;
                if (compSum >= 2) {
                    if (tipoComp.equals("1"))
                        tipoComp = "2";
                    else
                        tipoComp = "1";
                    compSum = 0;
                    comp = eDBA.getComp(tipoComp);
                }


            }
            noticiaLexer lexer = new noticiaLexer(CharStreams.fromString(template));
            CommonTokenStream stream = new CommonTokenStream(lexer);
            noticiaParser noticiasParser = new noticiaParser(stream);
            StringBuilder noticia = new StringBuilder();
            noticiasParser.noticias(templateValues, noticia);

            for (String key : keywordsTemplate.keySet()){
                if (templateValues.getValue(key) != null &&  templateValues.getValue(key).contains("Erro") && keywordsTemplate.get(key) != 0)
                    return "erro";
            }


            return noticia.toString();
        } catch (Exception e) {

            return e.toString();
        }

    }


    public void fillScoresMap() {

        //Get template Scores
        templateClassification = new HashMap<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            String sql_score = "SELECT used_templates, history.like - history.dislike AS score FROM history";
            ResultSet rs = select.executeQuery(sql_score);
            if (rs == null) return;

            while (rs.next()) {
                String used_temps_stg = rs.getString(1);
                List<String> used_temps = new ArrayList<>(Arrays.asList(used_temps_stg.split(",")));

                //Preencher map exterior
                for (String used_temp : used_temps) {
                    Map<Integer, Integer> scoreRelation = new HashMap<>();

                    //3
                    if (templateClassification.get(Integer.parseInt(used_temp)) != null) {
                        scoreRelation = templateClassification.get(Integer.parseInt(used_temp));
                    } else {
                        scoreRelation = new HashMap<>();
                    }

                    for (String used_temp2 : used_temps) {
                        if (used_temp2 == used_temp) continue;
                        int score = rs.getInt(2);
                        //prob vou estar a adicionar o próprio template
                        if (scoreRelation.get(Integer.parseInt(used_temp2)) != null) {
                            int score_atual = scoreRelation.get(Integer.parseInt(used_temp2));
                            score += score_atual;
                        }
                        int insert = Integer.parseInt(used_temp2);
                        scoreRelation.put(insert, score);
                    }
                    templateClassification.put(Integer.parseInt(used_temp), scoreRelation);
                }
            }
                conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

    private String link_generator(int id){
        String link = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256("GeradorDeNoticias");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("id", id) //id da notícia
                    .sign(algorithm);
            link = "http://ec2-15-188-60-29.eu-west-3.compute.amazonaws.com:3000/noticia?token=" + token;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }
        return link;
    }


}
