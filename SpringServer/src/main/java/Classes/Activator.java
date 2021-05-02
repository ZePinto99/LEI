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
    public double tempScore(List<String> templateKeywords, Map<String, Integer> newsKeywords){
        int score = 0;

        //set de priority keywords da notícia (todas as priority keywords diferentes de cada keyword da notícia final)
        Set priorityKeywordsSet =  priorityKeywordsSet(newsKeywords.keySet());

        //para cada keyword do template
        for(String templateKeyword :  templateKeywords){

            int occurrences = 0;
            //Vai ver quantas vezes a a templateKeyword aparece na notícia final
            if(newsKeywords.containsKey(templateKeyword))
                occurrences = newsKeywords.get(templateKeyword);
            //penaliza a keyword por cada repetição
            score -= constant*occurrences;

            //Vai ver se a templateKeyword é uma priority keyword
            //nota: cada priority keyword só é contabilizada 1 vez
            //aumenta o score caso se tarte de uma priority keyword
            score += priorityScore(templateKeyword, priorityKeywordsSet);
            }

        //devolve um score/probabilidade entre 0 e 1
        double activationValue = sigmoid(score);

        return activationValue;
    }

    //Função que cria um Set com todas as as priority keywords da notícia
    private Set<String> priorityKeywordsSet(Set<String> newsKeywords){
        //Escolhi um set para só contabilizarmos uma vez cada priority key
        Set<String> prioritykeys = new HashSet<>();

        for (String newsKeyword : newsKeywords){
            /////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ////////////////////////
            //Temos de ir buscar as priority keywords de cada keyword do template
            List<String> prioritykey = new ArrayList<>();
            /////////////////////// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ////////////////////////
            prioritykeys.addAll(prioritykey);
        }
        return prioritykeys;
    }

    //Dá zero se templateKeyword não for priority e constant se for
    public int priorityScore(String templateKeyword, Set<String> priorykeys){
        int occures = 0;
        if(priorykeys.contains(templateKeyword))
            occures = 1;
        return constant*occures;
    }

    //Função que dado um número retorna um valor entre 0 e 1
    public double sigmoid(int score){
        double x = (score)*0.1; // +feedBack -> + prob   +nrUses -> - prob
        return 1/(1 + Math.pow(this.e, -x));
    }
}

