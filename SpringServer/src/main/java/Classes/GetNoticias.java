package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class GetNoticias {

    String id;
    String lixo;

    public GetNoticias(String id, String lixo) {
        this.id = id;
    }


    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }




    public String getNoticiaString(){

        String noticia ="";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mydb?user=root&password="
                            + "root" + "&useTimezone=true&serverTimezone=UTC");


            Statement select = conn.createStatement();

            String sql = "SELECT text FROM history WHERE id_history = " + id + " limit 1;";

            ResultSet rs = select.executeQuery(sql);
            if (rs == null) return "";
            while (rs.next()) {
                noticia = rs.getString(1);
            }


            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return noticia;
    }
}
