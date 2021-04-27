package Classes;
import java.lang.Math;
import java.util.*;

public class Activator {
    private double e;
    private int constant;

    public Activator(int constant) {
        this.constant = constant;
        e = 2.2204460492503130808472633361816;
    }

    //O que retorna?
    public double chooseTemp(List<String> templateKeywords, Map<String, Integer> newsKeywords){
        int score = 0;

        for(String templateKeyword :  templateKeywords){

            int occurrences = Collections.frequency(newsKeywords.keySet(), templateKeyword);
            score -= constant*occurrences;

            score += priorityScore(templateKeyword, newsKeywords.keySet());
            }

        double activationValue = sigmoid(score);

        return activationValue;
    }

    public int priorityScore(String templateKeyword, Set<String> newsKeywords){
        //acede a um ficheiro com as prioritykeys de cada keyword
        //Ver quais são as prioridades da lista keywordsUsed
        List<String> priorykeys = new ArrayList<>(); //Temos de ir buscar as priority keywords de todas as keywords do template

        int occurrences = Collections.frequency(newsKeywords, templateKeyword);
        return constant*occurrences;
    }

    public double sigmoid(int score){
        double x = (score)*0.1; // +feedBack -> + prob   +nrUses -> - prob
        return 1/(1 + Math.pow(this.e, -x));
    }
}

