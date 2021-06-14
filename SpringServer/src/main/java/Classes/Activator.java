package Classes;
import java.lang.Math;
import java.util.*;

public class Activator {
    private double e;
    private int constant;

    //Possivelmente pode receber um map com as priority keywords de cada keyword
    public Activator(int constant) {
        this.constant = constant;
        e = 2.2204460492503130808472633361816;
    }

    //função que calcula o score de um template
    //o templates score será a junção de todos da relação co todos os templates
    //isto vai implicar ao inserirmos cada template termos um de fazer puts às relações com templates que não existiam e atualizar os valore
    //de registos que já existem
    public double tempScore(List<String> templateKeywords, Map<String, Integer> newsKeywords){//List<Integer> templatesUsed, Map <Integer, Integer> templatesScores){
        int score = 0;

        //Não permite fazer escolher templates com certas keywords
        if(nonRepeatableKeywords(templateKeywords, newsKeywords))
            return 0;

        //para cada keyword do template
        for(String templateKeyword :  templateKeywords){

            int occurrences = 0;

            //keywords que não se podem repetir

            //Vai ver quantas vezes a a templateKeyword aparece na notícia final
            if(newsKeywords.containsKey(templateKeyword))
                occurrences = newsKeywords.get(templateKeyword);
            //penaliza a keyword por cada repetição
            score -= constant*occurrences;
        }

        //percorre o mapa de templateScores e vê a relação do template com os templates que já pertencem à notícia
        /*
        for (int usedTemplate : templatesUsed) {
            try{
                score += priorityScore(templateId, templatesScores);
            }catch (Exception e){
                score += 0;
            }
        }
        */

        //devolve um score/probabilidade entre 0 e 1
        double activationValue = sigmoid(score);

        return activationValue;
    }

    //Dá zero se templateId não tiver no mapa de classificações
    public int priorityScore(String templateId, Map <Integer, Integer> templatesScores){
        int occures;
        try {
            occures = templatesScores.get(templateId);
        }catch (Exception e){
            occures = 0;
        }
        return constant*occures;
    }

    //Função que dado um número retorna um valor entre 0 e 1
    public double sigmoid(int score){
        double x = (score)*0.1; // +feedBack -> + prob   +nrUses -> - prob
        return 1/(1 + Math.pow(this.e, -x));
    }

    //Check for non Repeatable Keywords
    public boolean nonRepeatableKeywords(List<String> templateKeywords, Map<String, Integer> newsKeywords){

        Set<String> nonRepeatableKeywords = new HashSet();
        List<String> objs = Arrays.asList("IDADE_JOG", "NAC_JOG", "CLUBE", "TREINADOR", "ARBITRO", "JORNADA", "RESULTADO_JOGO", "CASA/FORA", "ADVERSARIO", "ESTADIO", "LOCALIZACAO", "MARCADOR_JORNADA", "ESTREIA_JOG", "RESULTADO_ESTREIA");
        nonRepeatableKeywords.addAll(objs);
        Set<String> templateNonRepeatableKeywords = new HashSet<>();
        for (String templateKeyword : templateKeywords){
            if (nonRepeatableKeywords.contains(templateKeyword))
                templateNonRepeatableKeywords.add(templateKeyword);
        }

        return  newsKeywords.keySet().stream().anyMatch(templateNonRepeatableKeywords::contains);
    }
}

