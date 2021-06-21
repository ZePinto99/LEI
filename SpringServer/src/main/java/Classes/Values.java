package Classes;

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
        values.put("NOME_JOG", "_Erro_Nome_");
        values.put("IDADE_JOG", "_Erro_Idade_");
        values.put("POS_JOG", "_Erro_Pos_");
        values.put("NAC_JOG", "_Erro_Pais_");
        values.put("NR_GOLOS_JOG_TOTAL", "_Erro_Jogos_");
        values.put("NR_JOGOS_JOG_TOTAL", "_Erro_NR_JOGOS_JOG_TOTAL");
        values.put("CLUBE", "Sporting Clube de Braga");
        values.put("COMPETICAO", "Erro_COMPETICAO_Não_Mapeado");
        values.put("TREINADOR", "Erro_TREINADOR_Não_Mapeado");
        values.put("ARBITRO", "Erro_ARBITRO_Não_Mapeado");
        values.put("JORNADA", "Erro_Jornada");
        values.put("RESULTADO_JOGO", "Erro_RESULTADO_JOGO");
        values.put("CASA/FORA", "Erro_CASAFORA_Não_Mapeado");
        values.put("ADVERSARIO", "Erro_Adversario");
        values.put("ESTADIO", "Erro_Estadio");
        values.put("LOCALIZACAO", "Erro_Localizacao_Não_Mapeado");
        values.put("MARCADOR_JORNADA", "Erro_MARCADOR_JORNADA");
        values.put("ESTREIA_JOG", "Erro_ESTREIA_JOG");
        values.put("RESULTADO_ESTREIA", "Erro_RESULTADO_ESTREIA");
        values.put("NR_GOLOS_JOG_JR", "Erro_NR_GOLOS_JOG_JR_Não_Mapeado");
        values.put("NR_JOGOS_SGOLOS_JOG", "Erro_NR_JOGOS_SGOLOS_JOG_Não_Mapeado");
        values.put("NOME_TOP", "Erro_Nome_Top_Não_Mapeado");
        values.put("NR_JOGOS_TOP", "Erro_NR_JOGOS_TOP_Não_Mapeado");
        values.put("NR_GOLOS_TOP", "Erro_NR_GOLOS_TOP_Não_Mapeado");
        values.put("NR_GOLOS_JOG_EPOCA", "Erro_NR_GOLOS_JOG_EPOCA");
        values.put("NR_JOGOS_JOG_EPOCA", "Erro_NR_JOGOS_JOG_EPOCA_Não_Mapeado");
        values.put("NR_GOLOS_JOG_EPOCA_ANT", "Erro_NR_GOLOS_JOG_EPOCA_ANT_Não_Mapeado");
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

    public void subToNumberOfTemplates(){
        numberOfTemplates++;
    }

    public Integer getNumberOfTemplates(){
        return numberOfTemplates;
    }
}
