package Classes;

public class Noticia {

    String noticia;
    String titulo;
    String assinatura;
    String finalText;
    String id_noticia;
    String link;

    public Noticia(String noticia, String titulo, String assinatura, String id_noticia, String link) {
        this.noticia = noticia;
        this.titulo = titulo;
        this.assinatura = assinatura;
        this.id_noticia = id_noticia;
        this.link = link;
    }

    public Noticia() {
    }

    @Override
    public String toString() {
        return  noticia + "\n" + assinatura + "\n";
    }
}
