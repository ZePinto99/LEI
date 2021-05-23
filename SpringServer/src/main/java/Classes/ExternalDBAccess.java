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


    public Values getValues(Map<String, Integer> keywords, String idjog){
        this.keywords = keywords;
        values = new Values(idjog);

        values.fillValuesMap();
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql:// lei.mysql.database.azure.com:3306/scbraga?user=lei&password="
                            + "Uminho2021" + "&useTimezone=true&serverTimezone=UTC");

            Statement select = conn.createStatement();
            checkTabelas();

            if(tabelas.equals("") || keys.equals(""))
                return values;
            String sql = "SELECT " + keys   +" FROM  " + tabelas +  " where id_jogador = " +  idjog +";";
            System.out.println(sql);
            ResultSet rs = select.executeQuery(sql);
            if (rs == null) return values;
            String[] result = keys.split(",");
            int x =0;
            while (rs.next()) {
                values.putValueInMap(keyWordChange(result[x]),rs.getString(x+1));
                x++;
            }


            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }




        return values;
    }

    private String checkTabelas(){

        String tablesString = "";
        List<String> tables = new ArrayList<>();
        usedkeys= new ArrayList<>();
        List<String> usedkeystemp = new ArrayList<>();
        for(String keyword : keywords.keySet()){
            if(keywords.get(keyword)>0)
                usedkeystemp.add(keyword);
        }

        //"NR_GOLOS_JOG_TOTAL", "NR_JOGOS_JOG_TOTAL", "CLUBE", "COMPETICAO", "TREINADOR", "ARBITRO", "JORNADA", "RESULTADO_JOGO", "CASA/FORA", "ADVERSARIO", "ESTADIO", "LOCALIZACAO", "MARCADOR_JORNADA", "ESTREIA_JOG", "RESULTADO_ESTREIA", "NR_GOLOS_JOG_JR", "NR_JOGOS_SGOLOS_JOG", "NOME_TOP", "NR_JOGOS_TOP", "NR_GOLOS_TOP", "NR_GOLOS_JOG_EPOCA", "NR_JOGOS_JOG_EPOCA", "NR_GOLOS_JOG_EPOCA_ANT", "NR_JOGOS_JOG_EPOCA_ANT");


        for(String keyword : usedkeystemp){
            if(keyword.equals("NOME_JOG") || keyword.equals("IDADE_JOG") ||  keyword.equals("POS_JOG") || keyword.equals("NAC_JOG")){

                if(!tables.contains(keyword)) {
                    switch (keyword){
                        case "NOME_JOG":
                            usedkeys.add("nome");
                            break;
                        case "IDADE_JOG":
                            usedkeys.add("idade");
                            break;
                        case "POS_JOG":
                            usedkeys.add("posicao");
                            break;
                        case "NAC_JOG":
                            usedkeys.add("pais  ");
                            break;
                    }
                    tables.add("jogador");
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
            case "nome":
                return "NOME_JOG";
            case "idade":
                return "IDADE_JOG";
            case "posicao":
                return "POS_JOG";
            case "pais":
                return "NAC_JOG";
        }
        return "";
    }
}
