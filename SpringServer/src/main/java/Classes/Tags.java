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


    public String getName() {
        return this.tipoAlerta;
    }

    public String getRole() {
        return this.valorAlerta;
    }

    public void setName(String name) {
        this.tipoAlerta = name;
    }

    public void setRole(String role) {
        this.valorAlerta = role;
    }

}
