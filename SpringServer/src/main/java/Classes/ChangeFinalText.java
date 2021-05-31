package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ChangeFinalText {

    String id;
    String noticiaAlterada;

    public ChangeFinalText(String id, String noticiaAlterada) {
        this.id = id;
        this.noticiaAlterada = noticiaAlterada;
    }

    public void changeText(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetoleibd.cpjxfbj4rl9p.eu-west-3.rds.amazonaws.com:3306/mydb?user=Grupo58&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement insert = conn.createStatement();

            //INSERT INTO history VALUES (DEFAULT, NOW(), titulo, text, final_text, asssinatura, used_templates, tamanho, 0, 0);
            String sql = "UPDATE history Set final_text = \""+ noticiaAlterada + "\" where id_noticia =" + id+ ";";
            System.out.println("SQL-> " + sql);
            insert.execute(sql);

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }

}
