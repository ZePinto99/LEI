package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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


    public Noticia getNoticiaString() {

        Noticia noticia = new Noticia();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=admin&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");


            Statement select = conn.createStatement();

            String sql = "SELECT text, titulo, assinatura, final_text, id_noticia FROM history WHERE id_noticia = " + id + " limit 1;";

            ResultSet rs = select.executeQuery(sql);
            if (rs == null) return noticia;
            while (rs.next()) {
                noticia.noticia    = rs.getString(1);
                noticia.titulo     = rs.getString(2);
                noticia.assinatura = rs.getString(3);
                noticia.finalText  = rs.getString(4);
                noticia.id_noticia = rs.getString(5);
            }


            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return noticia;
    }

    public List<Noticia> getNoticias(){

        List<Noticia> noticias = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.
                    getConnection("jdbc:mysql://projetolei.cbtwyelra1do.eu-west-3.rds.amazonaws.com:3306/projetoLei?user=Grupo58&password="
                            + "password" + "&useTimezone=true&serverTimezone=UTC");


            Statement select = conn.createStatement();

            String sql = "SELECT text, titulo, assinatura, final_text, id_noticia FROM history order by id_noticia desc limit 8;";


            int tt=0;
            ResultSet rs = select.executeQuery(sql);
            if (rs == null) return noticias;
            while (rs.next() && tt<8) {
                Noticia noticia = new Noticia();
                noticia.noticia    = rs.getString(1);
                noticia.titulo     = rs.getString(2);
                noticia.assinatura = rs.getString(3);
                noticia.finalText  = rs.getString(4);
                noticia.id_noticia = rs.getString(5);
                noticias.add(noticia);
            }


            conn.close();

        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
        }
        return noticias;

    }

}


