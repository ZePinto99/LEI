package Classes;

public class Values {
    String v_TITULO_JOG;
    String v_CLUBE;
    String v_NR_JOGOS_JOG;
    String v_NOME_JOG;
    String v_ESTREIA_JOG;
    String v_ADVERSARIO;
    String v_RESULTADO_ESTREIA;

    public Values(){
        v_TITULO_JOG = "Capitão";
        v_CLUBE = "Sporting CLube de Braga";
        v_NR_JOGOS_JOG = "150";
        v_NOME_JOG = "Ricardo Horta";
        v_ESTREIA_JOG = "30 de fevereiro";
        v_ADVERSARIO = "Real Madrid";
        v_RESULTADO_ESTREIA = "10-0";
    }

    public String getValue(String key){
        String ret;
        switch (key){
            case "TITULO_JOG":
                ret = getV_TITULO_JOG();
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
    }

    public String getV_TITULO_JOG() {
        return v_TITULO_JOG;
    }

    public String getV_CLUBE() {
        return v_CLUBE;
    }

    public String getV_NR_JOGOS_JOG() {
        return v_NR_JOGOS_JOG;
    }

    public String getV_NOME_JOG() {
        return v_NOME_JOG;
    }

    public String getV_ESTREIA_JOG() {
        return v_ESTREIA_JOG;
    }

    public String getV_ADVERSARIO() {
        return v_ADVERSARIO;
    }

    public String getV_RESULTADO_ESTREIA() {
        return v_RESULTADO_ESTREIA;
    }
}
