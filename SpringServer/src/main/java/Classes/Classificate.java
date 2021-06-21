package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Classificate {
    String id;
    String rate;

    public Classificate(String id, String rate) {
        this.id = id;
        this.rate = rate;
    }



    public void classificate(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement insert = conn.createStatement();
            String likeOrDislike ="";
            if(rate.equals("1"))
                likeOrDislike = "history.like";
            else
                likeOrDislike = "history.dislike";
            //INSERT INTO history VALUES (DEFAULT, NOW(), titulo, text, final_text, asssinatura, used_templates, tamanho, 0, 0);
            String sql = "UPDATE history Set " + likeOrDislike +" = " +likeOrDislike + " + 1 where id_noticia =" + id+ ";";
            System.out.println("SQL-> " + sql);
            insert.execute(sql);

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

}
