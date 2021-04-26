import java.sql.*;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class main {

    private List<Integer> usedIds = new ArrayList<>();
    String noticia;
    int tamanho;
    int tamanhoMax;


    public static void main (String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");

            try {
                Scanner scanner = new Scanner(new File("templates.txt"));

                while (scanner.hasNextLine()) {
                    // retirar texto
                    String text = scanner.nextLine();
                    // calcular size
                    int size = countWords(text);
                    // retirar keywords
                    int keywords = getKeywords(text);
                    // checar version
                    int version;

                    Statement insert = conn.createStatement();
                    // inserir na tabela version (se não existir)
                    String sqlVersion = "INSERT INTO version VALUES(" + ";";
                    insert.executeQuery(sqlVersion);
                    // inserir na tabela keywords
                    String sqlKeywords = "INSERT INTO template VALUES(" + ";";
                    insert.executeQuery(sqlKeywords);
                    // inserir na tabela template
                    String sqlTemplate = "INSERT INTO keywords VALUES(" + ";";
                    insert.executeQuery(sqlTemplate);
                }

                scanner.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            conn.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static int countWords(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        String[] words = input.split("\\s+");
        return words.length;
    }


    public static String getKeywords(String input) {
        int[] array = new int[23];

        switch (key){
            case "POS_JOG":
                ret = getV_POS_JOG();
                break;
            case "CLUBE":
                ret = getV_CLUBE();
                break;
            case "NR_JOGOS_JOG":
                ret = getV_NR_JOGOS_JOG();
                break;
            case "NOME_JOG":
                ret = getV_NOME_JOG();
                break;
            case "ESTREIA_JOG":
                ret = getV_ESTREIA_JOG();
                break;
            case "ADVERSARIO":
                ret = getV_ADVERSARIO();
                break;
            case "RESULTADO_ESTREIA":
                ret = getV_RESULTADO_ESTREIA();
                break;
            default:
                ret = "";
                break;
        }
        return  ret;

        if (input == null || input.isEmpty()) {
            return result;
        }
        String[] words = input.split("\\s+");

        return Arrays.toString(array);
    }

}
