package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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

            return "Ze";



        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return "Ze";
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
            //System.out.println(sql);
            ResultSet rs = select.executeQuery(sql);
            String[] result = keys.split(",");
            int x = 0;
            while (rs.next()) {
                return rs.getString(x + 1);
            }

            conn.close();

            return "Ze";



        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return "Ze";
    }


    public Values getValues(Map<String, Integer> keywords, String idjog, String comp, String idComp){
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



            checkTabelasJogardorAndComp("jogador");

            String sql;
            String[] result;
            int x;
            ResultSet rs;
            if(!tabelas.equals("") && !keys.equals("")) {
                sql = "SELECT " + keys + " FROM  " + tabelas + " where jogador.id_jogador = " + idjog + whereclause + ";";
                System.out.println("Jog and Comp +" + sql);
                rs = select.executeQuery(sql);
                if (rs == null) return values;
                result = keys.split(",");
                x = 0;
                while (rs.next()) {
                    while (x < result.length) {
                        System.out.println(result[x] + " --- " + rs.getString(x + 1));
                        values.putValueInMap(keyWordChange(result[x]), rs.getString(x + 1));
                        x++;
                    }
                }
            }

            keys ="";
            tabelas = "";
            whereclause = "";
            checkTabelasJogardorAndComp("jogo");
            if(!tabelas.equals("") && !keys.equals("") ) {

                sql = "SELECT " + keys + " FROM " + tabelas + " where 1 = 1 " + whereclause + ";";
                System.out.println("Jog and Comp +" + sql);
                rs = select.executeQuery(sql);
                if (rs == null) return values;
                result = keys.split(",");
                x = 0;
                while (rs.next()) {
                    while (x < result.length) {
                        System.out.println(result[x] + " --- " + rs.getString(x + 1));
                        values.putValueInMap(keyWordChange(result[x]), rs.getString(x + 1));
                        x++;
                    }
                }
            }

            keys ="";
            tabelas = "";
            whereclause = "";
            checkTabelasJogardorAndComp("adversario");
            if(!tabelas.equals("") && !keys.equals("")) {

                sql = "SELECT " + keys + " FROM " + tabelas + " where 1 = 1 " + whereclause + ";";
                System.out.println("Jog and Comp +" + sql);
                rs = select.executeQuery(sql);
                if (rs == null) return values;
                result = keys.split(",");
                x = 0;
                while (rs.next()) {
                    while (x < result.length) {
                        System.out.println(result[x] + " --- " + rs.getString(x + 1));
                        values.putValueInMap(keyWordChange(result[x]), rs.getString(x + 1));
                        x++;
                    }
                }

            }


            conn.close();
            /////////////////////////////////////s

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }




        return values;
    }

    private String checkTabelasJogardorAndComp(String addtable){

        String tablesString = "";
        List<String> tables = new ArrayList<>();
        if(!addtable.equals("adversario"))
            tables.add(addtable);
        usedkeys= new ArrayList<>();
        List<String> usedkeystemp = new ArrayList<>();

        boolean competicao = false;

        for(String keyword : keywords.keySet()) {
            if (keywords.get(keyword) > 0)
                usedkeystemp.add(keyword);
        }

        for(String keyword : usedkeystemp){ System.out.println(keyword);
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
            if(addtable.equals("jogo") && ( keyword.equals("NR_JOGOS_JOG_EPOCA") || keyword.equals("NR_GOLOS_JOG_TOTAL")  || keyword.equals("JORNADA"))){

                if(!tables.contains(keyword)) {
                    switch (keyword){
                        case "NR_GOLOS_JOG_TOTAL":
                            usedkeys.add("count(golo.idGolo)");
                            if(!tables.contains("golo")) {
                                tables.add("golo");
                                tables.add("jogador_has_jogo");

                            }
                            whereclause += " and Jogador_has_Jogo_id_jogador = " + idJog + " and jogador_has_jogo.id_jogador = Jogador_has_Jogo_id_jogador and Jogo.idCompeticao =" + idComp   ;
                            break;
                        case "NR_JOGOS_JOG_EPOCA":
                            if(!usedkeys.contains("count(distinct jogo.idJogo)"))
                                usedkeys.add("count(distinct jogo.idJogo)");
                            if(!tables.contains("golo")) {
                                tables.add("golo");
                                tables.add("jogador_has_jogo");

                            }
                            whereclause += " and jogador_has_jogo.id_jogador = " + idJog + " and Jogo.idJogo = jogador_has_jogo.id_jogador and Jogo.idCompeticao =" + idComp   ;
                        case "JORNADA":
                            if(!usedkeys.contains("count(distinct jogo.idJogo )"))
                                usedkeys.add("count(distinct jogo.idJogo )");
                            if(!tables.contains("epoca")) {
                                tables.add("epoca");

                            }
                            whereclause += " and jogo.idEpoca = epoca.idEpoca and anos = \'2020-2021\'"   ;


                    }


                }
            }
            if(addtable.equals("adversario") && ( keyword.equals("ADVERSARIO"))){
                switch (keyword){
                    case "ADVERSARIO":

                        tables.add("jogo");
                        tables.add("clube");
                        tables.add("jogador_has_jogo");
                        if(usedkeystemp.contains("ESTREIA_JOG")){
                            if(!usedkeys.contains("clube.nome,jogo.resultado "))
                                usedkeys.add("clube.nome,jogo.resultado ");
                            whereclause += "and jogador_has_jogo.id_jogador = " + idJog + " and jogo.idClube = clube.idClube and jogo.idJogo = jogador_has_jogo.idJogo order by jogador_has_jogo.idJogo ASC limit 1;";
                        }
                        else {
                            if(!usedkeys.contains("clube.nome,jogo.resultado"))
                                usedkeys.add("clube.nome,jogo.resultado");
                            whereclause += " and jogador_has_jogo.id_jogador =" + idJog + " and jogo.idClube = clube.idClube and jogo.idJogo = jogador_has_jogo.idJogo order by jogo.idJogo DESC limit 1;";

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

    private String keyWordChange(String key){
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
        }
        return "";
    }
}
