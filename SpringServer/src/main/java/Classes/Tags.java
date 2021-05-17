package Classes;

public class Tags {

    private String tipoAlerta;
    private String valorAlerta;
    private String tipoComp;
    private String idJogador;


    public Tags(String tipoAlerta, String valorAlerta, String tipoComp, String idJogador) {
        this.tipoAlerta = tipoAlerta;
        this.valorAlerta = valorAlerta;
        this.tipoComp = tipoComp;
        this.idJogador = idJogador;
    }


    public String getTipoAlerta() {
        return this.tipoAlerta;
    }

    public String getValorAlerta() {
        return this.valorAlerta;
    }

    public String getTipoComp() {
        return this.tipoComp;
    }

    public String getIdJogador() {
        return this.idJogador;
    }

}
