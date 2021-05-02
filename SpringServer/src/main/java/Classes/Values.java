package Classes;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Values {
    String v_NOME_JOG;
    String v_POS_JOG;
    String v_CLUBE;
    String v_NR_JOGOS_JOG;
    String v_ESTREIA_JOG;
    String v_ADVERSARIO;
    String v_RESULTADO_ESTREIA;
    Map<String, Integer> keywords;  //Passou de List<String> para Map<keyword, nrº de repetições>
    Integer numberOfTemplates;
    List<Integer> versions;

    public Values(){
        v_POS_JOG = "Avançado";
        v_CLUBE = "Sporting Clube de Braga";
        v_NR_JOGOS_JOG = "150";
        v_NOME_JOG = "Ricardo Horta";
        v_ESTREIA_JOG = "30 de fevereiro";
        v_ADVERSARIO = "Real Madrid";
        v_RESULTADO_ESTREIA = "10-0";
        //Só para teste temos de ir buscar à bd  NOTA: O MAP DEVE ESTAR NA MESMA ORDEM QUE A BD!!!!
        List<String> keywordsList = Arrays.asList("NOME_JOG", "IDADE_JOG", "POS_JOG", "NAC_JOG", "NR_GOLOS_JOG_TOTAL", "NR_JOGOS_JOG_TOTAL", "CLUBE", "COMPETICAO", "TREINADOR", "ARBITRO", "JORNADA", "RESULTADO_JOGO", "CASA/FORA", "ADVERSARIO", "ESTADIO", "LOCALIZACAO", "MARCADOR_JORNADA", "ESTREIA_JOG", "RESULTADO_ESTREIA", "NR_GOLOS_JOG_JR", "NR_JOGOS_SGOLOS_JOG", "NOME_TOP", "NR_JOGOS_TOP", "NR_GOLOS_TOP", "NR_GOLOS_JOG_EPOCA", "NR_JOGOS_JOG_EPOCA", "NR_GOLOS_JOG_EPOCA_ANT", "NR_JOGOS_JOG_EPOCA_ANT");
        keywords = keywordsList.stream().collect(Collectors.toMap(Function.identity(), i -> 0));
        versions = new ArrayList<>();
        numberOfTemplates=0;
    }
    public void setVersions(List<Integer> ver){
        versions= ver;
    }

    public List<Integer> getVersions(){
        return versions;
    }

    public Map<String, Integer> getKeywords(){
        return keywords;
    }

    public String getValue(String key){
        String ret;
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
    }

    public void setKeywords(Map<String, Integer> keywords) {
        this.keywords = keywords;
    }

    public void addToNumberOfTemplates(){
        numberOfTemplates++;
    }

    public Integer getNumberOfTemplates(){
        return numberOfTemplates;
    }

    public String getV_POS_JOG() {
        return v_POS_JOG;
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


    public void setV_NOME_JOG(String v_NOME_JOG) {
        this.v_NOME_JOG = v_NOME_JOG;
    }

    public void setV_POS_JOG(String v_TITULO_JOG) {
        this.v_POS_JOG = v_TITULO_JOG;
    }

    public void setV_CLUBE(String v_CLUBE) {
        this.v_CLUBE = v_CLUBE;
    }

    public void setV_NR_JOGOS_JOG(String v_NR_JOGOS_JOG) {
        this.v_NR_JOGOS_JOG = v_NR_JOGOS_JOG;
    }

    public void setV_ESTREIA_JOG(String v_ESTREIA_JOG) {
        this.v_ESTREIA_JOG = v_ESTREIA_JOG;
    }

    public void setV_ADVERSARIO(String v_ADVERSARIO) {
        this.v_ADVERSARIO = v_ADVERSARIO;
    }

    public void setV_RESULTADO_ESTREIA(String v_RESULTADO_ESTREIA) {
        this.v_RESULTADO_ESTREIA = v_RESULTADO_ESTREIA;
    }
}
