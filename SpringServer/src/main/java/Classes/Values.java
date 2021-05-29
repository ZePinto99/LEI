package Classes;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Values {
    String id;
    Map<String, Integer> keywords;  //Passou de List<String> para Map<keyword, nrº de repetições>
    Map<String, String> values;
    Integer numberOfTemplates;
    List<Integer> versions;
    List<String> keywordsList;

    public Values(String id){
        this.id = id;
        //Tags tags
        //Só para teste temos de ir buscar à bd  NOTA: O MAP DEVE ESTAR NA MESMA ORDEM QUE A BD!!!!
        keywordsList = Arrays.asList("NOME_JOG", "IDADE_JOG", "POS_JOG", "NAC_JOG", "NR_GOLOS_JOG_TOTAL", "NR_JOGOS_JOG_TOTAL", "CLUBE", "COMPETICAO", "TREINADOR", "ARBITRO", "JORNADA", "RESULTADO_JOGO", "CASA/FORA", "ADVERSARIO", "ESTADIO", "LOCALIZACAO", "MARCADOR_JORNADA", "ESTREIA_JOG", "RESULTADO_ESTREIA", "NR_GOLOS_JOG_JR", "NR_JOGOS_SGOLOS_JOG", "NOME_TOP", "NR_JOGOS_TOP", "NR_GOLOS_TOP", "NR_GOLOS_JOG_EPOCA", "NR_JOGOS_JOG_EPOCA", "NR_GOLOS_JOG_EPOCA_ANT", "NR_JOGOS_JOG_EPOCA_ANT");
        keywords = keywordsList.stream().collect(Collectors.toMap(Function.identity(), i -> 0));
        values   = new HashMap<>();
        versions = new ArrayList<>();
        numberOfTemplates=0;
        fillValuesMap();
    }

    public void fillValuesMap(){
        values.put("NOME_JOG", "Candido Faisca");
        values.put("IDADE_JOG", "76");
        values.put("POS_JOG", "ponta de banco esquerdo");
        values.put("NAC_JOG", "Quatar");
        values.put("NR_GOLOS_JOG_TOTAL", "21112");
        values.put("NR_JOGOS_JOG_TOTAL", "-1");
        values.put("CLUBE", "Sporting Clube de Braga");
        values.put("COMPETICAO", "na Liga da esquina");
        values.put("TREINADOR", "Ninguem de jeito");
        values.put("ARBITRO", "Artur Soares Dias");
        values.put("JORNADA", "11111");
        values.put("RESULTADO_JOGO", "perdeu");
        values.put("CASA/FORA", "fora");
        values.put("ADVERSARIO", "Maritimo");
        values.put("ESTADIO", "Estádio dos Barreiros");
        values.put("LOCALIZACAO", "Madeira");
        values.put("MARCADOR_JORNADA", "1-0");
        values.put("ESTREIA_JOG", "5/9/2017");
        values.put("RESULTADO_ESTREIA", "1-0");
        values.put("NR_GOLOS_JOG_JR", "-1");
        values.put("NR_JOGOS_SGOLOS_JOG", "-2");
        values.put("NOME_TOP", "Alan");
        values.put("NR_JOGOS_TOP", "559");
        values.put("NR_GOLOS_TOP", "74");
        values.put("NR_GOLOS_JOG_EPOCA", "-11");
        values.put("NR_JOGOS_JOG_EPOCA", "-130");
        values.put("NR_GOLOS_JOG_EPOCA_ANT", "-115");
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void putValueInMap(String key, String value){
        values.put(key, value);
    }

    public void setVersions(List<Integer> ver){
        versions = ver;
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
