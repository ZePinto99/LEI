package Classes;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Values {
    Map<String, Integer> keywords;  //Passou de List<String> para Map<keyword, nrº de repetições>
    Map<String, String> values;
    Integer numberOfTemplates;
    List<Integer> versions;

    public Values(){
        //Tags tags
        //Só para teste temos de ir buscar à bd  NOTA: O MAP DEVE ESTAR NA MESMA ORDEM QUE A BD!!!!
        List<String> keywordsList = Arrays.asList("NOME_JOG", "IDADE_JOG", "POS_JOG", "NAC_JOG", "NR_GOLOS_JOG_TOTAL", "NR_JOGOS_JOG_TOTAL", "CLUBE", "COMPETICAO", "TREINADOR", "ARBITRO", "JORNADA", "RESULTADO_JOGO", "CASA/FORA", "ADVERSARIO", "ESTADIO", "LOCALIZACAO", "MARCADOR_JORNADA", "ESTREIA_JOG", "RESULTADO_ESTREIA", "NR_GOLOS_JOG_JR", "NR_JOGOS_SGOLOS_JOG", "NOME_TOP", "NR_JOGOS_TOP", "NR_GOLOS_TOP", "NR_GOLOS_JOG_EPOCA", "NR_JOGOS_JOG_EPOCA", "NR_GOLOS_JOG_EPOCA_ANT", "NR_JOGOS_JOG_EPOCA_ANT");
        keywords = keywordsList.stream().collect(Collectors.toMap(Function.identity(), i -> 0));
        values   = new HashMap<>();
        versions = new ArrayList<>();
        numberOfTemplates=0;
    }

    public void fillValuesMap(){
        values.put("NOME_JOG", "Ricardo Horta");
        values.put("IDADE_JOG", "26");
        values.put("POS_JOG", "extremo esquerdo");
        values.put("NAC_JOG", "português");
        values.put("NR_GOLOS_JOG_TOTAL", "63");
        values.put("NR_JOGOS_JOG_TOTAL", "155");
        values.put("CLUBE", "Braga");
        values.put("COMPETICAO", "Liga NOS");
        values.put("TREINADOR", "Carlos Carvalhal");
        values.put("ARBITRO", "Artur Soares Dias");
        values.put("JORNADA", "25");
        values.put("RESULTADO_JOGO", "perdeu");
        values.put("CASA/FORA", "fora");
        values.put("ADVERSARIO", "Maritimo");
        values.put("ESTADIO", "Estádio dos Barreiros");
        values.put("LOCALIZACAO", "Madeira");
        values.put("MARCADOR_JORNADA", "1-0");
        values.put("ESTREIA_JOG", "5/9/2017");
        values.put("RESULTADO_ESTREIA", "venceu");
        values.put("NR_GOLOS_JOG_JR", "0");
        values.put("NR_JOGOS_SGOLOS_JOG", "0");
        values.put("NOME_TOP", "Alan");
        values.put("NR_JOGOS_TOP", "559");
        values.put("NR_GOLOS_TOP", "74");
        values.put("NR_GOLOS_JOG_EPOCA", "8");
        values.put("NR_JOGOS_JOG_EPOCA", "30");
        values.put("NR_GOLOS_JOG_EPOCA_ANT", "15");
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void putValueInMap(String key, String value){
        values.put(key, value);
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
        return  values.get(key);
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
}
