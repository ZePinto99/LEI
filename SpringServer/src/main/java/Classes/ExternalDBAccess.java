package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExternalDBAccess {

    private Values values;
    private Map<String, Integer> keywords;
    List<String> usedkeys = new ArrayList<>();
    String keys="";
    String tabelas="";
    String whereclause="";
    String idComp;
    String idJog;

    public ExternalDBAccess(){
    }


    public String getName( String idjog) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql:// lei.mysql.database.azure.com:3306/scbraga?user=lei&password="
                            + "Uminho2021" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            String sql = "SELECT nome_completo FROM jogador where id_jogador = " + idjog + ";";
            //System.out.println(sql);
            ResultSet rs = select.executeQuery(sql);
            String[] result = keys.split(",");
            int x = 0;
            while (rs.next()) {
                return rs.getString(x + 1);
            }

            conn.close();

            return null;

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return null;
    }

    public String getComp( String idComp) {
        try {

            this.idComp = idComp;
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql:// lei.mysql.database.azure.com:3306/scbraga?user=lei&password="
                            + "Uminho2021" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            String sql = "SELECT nome FROM CompeticaoNome where idCompeticaoNome = " + idComp + ";";
            System.out.println(sql);
            ResultSet rs = select.executeQuery(sql);
            int x = 0;
            while (rs.next()) {
                return rs.getString(x + 1);
            }

            conn.close();

            return null;



        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return null;
    }


    public Values getValues(Map<String, Integer> keywords, String idjog, String comp, String idComp){
        System.out.println("Begin Integration");
        this.keywords = keywords;
        values = new Values(idjog);
        this.idComp = idComp;
        this.idJog = idjog;
        values.fillValuesMap();
        values.putValueInMap("COMPETICAO", comp);
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql:// lei.mysql.database.azure.com:3306/scbraga?user=lei&password="
                            + "Uminho2021" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();

            //checkTabelasJogardorAndComp("jogador");
            executeQuery(select,"jogador");

            executeQuery(select,"jogo");

            executeQuery(select,"adversario");


            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }




        return values;
    }


    private void executeQuery(Statement select, String table){

        keys ="";
        tabelas = "";
        whereclause = "";
        String  isJogo = "";
        if(table.equals("jogador"))
            isJogo = " and jogador.id_jogador = " + idJog;
        checkTabelasJogardorAndComp(table);
        try {
            String sql;
            String[] result;
            int x;
            ResultSet rs;
            if (!tabelas.equals("") && !keys.equals("")) {
                sql = "SELECT " + keys + " FROM  " + tabelas + " where 1 = 1 " + isJogo + whereclause + ";";
                System.out.println(sql);

                rs = select.executeQuery(sql);
                if (rs == null) return;
                result = keys.split(",");
                x = 0;
                while (rs.next()) {
                    while (x < result.length) {
                        if(result[x].equals("data.ano")){
                            String ano = rs.getString(x + 1);
                            String mes = rs.getString(x + 2);
                            String dia = rs.getString(x + 3);
                            x += 2;

                            String dataText = ano + "/" + mes + "/" + dia;
                            values.putValueInMap("ESTREIA_JOG", dataText);
                        }else {

                            values.putValueInMap(keyWordMapping(result[x]), rs.getString(x + 1));
                            x++;
                        }
                    }
                }
            }
        }

        catch (Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private String checkTabelasJogardorAndComp(String addtable){

        String tablesString = "";
        List<String> tables = new ArrayList<>();
        if(!addtable.equals("adversario"))
            tables.add(addtable);
        usedkeys= new ArrayList<>();
        List<String> usedkeystemp = new ArrayList<>();


        for(String keyword : keywords.keySet()) {
            if (keywords.get(keyword) > 0)
                usedkeystemp.add(keyword);
        }

        for(String keyword : usedkeystemp){

            if( addtable.equals("jogador") &&
                    (keyword.equals("NOME_JOG") ||
                            keyword.equals("IDADE_JOG") ||  keyword.equals("POS_JOG") || keyword.equals("NAC_JOG"))){

                if(!tables.contains(keyword)) {
                    switch (keyword){
                        case "NOME_JOG":
                            usedkeys.add("jogador.nome");
                            if(!tables.contains("jogador"))
                                tables.add("jogador");
                            break;
                        case "IDADE_JOG":
                            usedkeys.add("jogador.idade");
                            if(!tables.contains("jogador"))
                                tables.add("jogador");
                            break;
                        case "POS_JOG":
                            usedkeys.add("jogador.posicao");
                            if(!tables.contains("jogador"))
                                tables.add("jogador");
                            break;
                        case "NAC_JOG":
                            usedkeys.add("pais.nome");
                            if(!tables.contains("jogador"))
                                tables.add("jogador");
                            whereclause += " and jogador.pais = pais.idPais";
                            tables.add("pais");
                            break;

                    }

                }

            }
            if(addtable.equals("jogo") && ( keyword.equals("NR_JOGOS_JOG_EPOCA") || keyword.equals("NR_GOLOS_JOG_TOTAL")  || keyword.equals("JORNADA") || keyword.equals("NR_JOGOS_JOG_TOTAL") || keyword.equals("NR_GOLOS_JOG_EPOCA"))){

                if(!tables.contains(keyword)) {
                    switch (keyword){
                        case "NR_GOLOS_JOG_TOTAL":
                            usedkeys.add("count(golo.idGolo)");
                            if(!tables.contains("golo")) {
                                tables.add("golo");
                                tables.add("jogador_has_jogo");

                            }
                            whereclause += "and Jogador_has_Jogo_id_jogador = "  + idJog +" and Jogador_has_Jogo_id_jogador = jogador_has_jogo.id_jogador and Jogador_has_Jogo_idJogo = jogador_has_jogo.idJogo and Jogador_has_Jogo_idJogo = jogo.idJogo and jogador_has_jogo.idJogo = jogo.idJogo and jogo.idcompeticao = " + idComp+  " ";
                            break;
                        case "NR_GOLOS_JOG_EPOCA":
                            usedkeys.add("count(golo.idGolo) ");
                            if(!tables.contains("golo")) {
                                tables.add("golo");
                                tables.add("jogador_has_jogo");

                            }
                            whereclause += "and Jogador_has_Jogo_id_jogador = "  + idJog +" and Jogador_has_Jogo_id_jogador = jogador_has_jogo.id_jogador and Jogador_has_Jogo_idJogo = jogador_has_jogo.idJogo and Jogador_has_Jogo_idJogo = jogo.idJogo and jogador_has_jogo.idJogo = jogo.idJogo and jogo.idcompeticao = " + idComp+  " ";
                            break;
                        case "NR_JOGOS_JOG_EPOCA":
                            if(!usedkeys.contains("count(distinct jogo.idJogo)"))
                                usedkeys.add("count(distinct jogo.idJogo)");
                            if(!tables.contains("golo")) {
                                tables.add("golo");
                                tables.add("jogador_has_jogo");

                            }
                            whereclause += " and jogador_has_jogo.id_jogador = " + idJog + " and Jogo.idJogo = jogador_has_jogo.idJogo and Jogo.idCompeticao =" + idComp   ;
                        case "NR_JOGOS_JOG_TOTAL":
                            if(!usedkeys.contains("count(distinct jogo.idJogo) as jogos"))
                                usedkeys.add("count(distinct jogo.idJogo) as jogos");
                            if(!tables.contains("golo")) {
                                tables.add("golo");
                                tables.add("jogador_has_jogo");

                            }
                            whereclause += " and jogador_has_jogo.id_jogador = " + idJog + " and Jogo.idJogo = jogador_has_jogo.idJogo and Jogo.idCompeticao =" + idComp   ;
                        case "JORNADA":
                            if(!usedkeys.contains("count(distinct jogo.idJogo )"))
                                usedkeys.add("count(distinct jogo.idJogo )");
                            if(!tables.contains("epoca")) {
                                tables.add("epoca");
                            }
                            whereclause += " and jogo.idEpoca = epoca.idEpoca and anos = \'2020-2021\' and jogo.idCompeticao = " + idComp + " "   ;


                    }


                }
            }
            if(addtable.equals("adversario") && ( keyword.equals("ADVERSARIO"))){
                switch (keyword){
                    case "ADVERSARIO":

                        tables.add("jogo");
                        tables.add("clube");
                        tables.add("jogador_has_jogo");
                        tables.add("estadio");
                        tables.add("data");
                        if(usedkeystemp.contains("ESTREIA_JOG")){
                            if(!usedkeys.contains("clube.nome,jogo.resultado ,estadio.nome,data.ano,data.mes,data.dia"))
                                usedkeys.add("clube.nome,jogo.resultado ,estadio.nome,data.ano,data.mes,data.dia");
                            whereclause += "and jogador_has_jogo.id_jogador = " + idJog + " and jogo.idClube = clube.idClube and jogo.idJogo = jogador_has_jogo.idJogo and jogo.idEstadio = estadio.idEstadio and jogo.id_data = Data.id_data and jogo.idCompeticao = " + idComp + " order by jogador_has_jogo.idJogo ASC limit 1";
                        }
                        else {
                            if(!usedkeys.contains("clube.nome,jogo.resultado,estadio.nome"))
                                usedkeys.add("clube.nome,jogo.resultado,estadio.nome");
                            whereclause += " and jogador_has_jogo.id_jogador =" + idJog + " and jogo.idClube = clube.idClube and jogo.idJogo = jogador_has_jogo.idJogo and jogo.idEstadio = estadio.idEstadio and jogo.idCompeticao = " + idComp +   " order by jogo.idJogo  DESC limit 1";

                        }
                }
            }

            }

        tabelas="";
        boolean first = true;
        for(String table : tables){
            if(!first)
                tabelas+=",";
            else
                first= false;
            tabelas += table;
        }

        usedkeystostring();

        return tablesString;
    }

    private void usedkeystostring(){
        keys = "";

        boolean first = true;
        for(String table : usedkeys){
            if(!first)
                keys+=",";
            else
                first= false;
            keys += table;
        }
    }

    private String keyWordMapping(String key){
        switch (key){
            case "jogador.nome":
                return "NOME_JOG";
            case "jogador.idade":
                return "IDADE_JOG";
            case "jogador.posicao":
                return "POS_JOG";
            case "jogador.pais":
                return "NAC_JOG";
            case "CompeticaoNome.nome":
                return "COMPETICAO";
            case "count(golo.idGolo)":
                return "NR_GOLOS_JOG_TOTAL";
            case "count(distinct jogo.idJogo)":
                return "NR_JOGOS_JOG_EPOCA";
            case "count(distinct jogo.idJogo )":
                return "JORNADA";
            case "clube.nome":
                return "ADVERSARIO";
            case "jogo.resultado":
                return "MARCADOR_JORNADA";
            case "jogo.resultado ":
                return "RESULTADO_ESTREIA";
            case "pais.nome":
                return "NAC_JOG";
            case "estadio.nome":
                return "ESTADIO";
            case "data.ano,data.mes,data.dia":
                return "ESTREIA_JOG";
            case "count(distinct jogo.idJogo) as jogos":
                return "NR_JOGOS_JOG_TOTAL";
            case "count(golo.idGolo) ":
                return "NR_GOLOS_JOG_EPOCA";
        }
        return "";
    }
}
