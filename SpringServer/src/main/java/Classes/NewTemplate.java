package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class NewTemplate {
    String newTemplate;
    String bugFixer;

    public NewTemplate(String newTemplate,String bugFixer) {
        this.newTemplate = newTemplate;
    }

    public void addText(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");

            Statement insert = conn.createStatement();

            //INSERT INTO history VALUES (DEFAULT, NOW(), titulo, text, final_text, asssinatura, used_templates, tamanho, 0, 0);
            String sql = "INSERT INTO new_templates VALUES(DEFAULT, '" + newTemplate + "');";
            System.out.println("SQL-> " + sql);
            insert.execute(sql);

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
    }
}
