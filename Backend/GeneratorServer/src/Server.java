import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {
    public static void main(String[] args) {

        /////////// Carregar Templates /////////////
        List<Script> scripts = new ArrayList<>();
        Values values = new Values();
        csvReader(scripts);
        /////////////////////////////////////////

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Insert tags (testar com a tag estreia)");
        String in;
        try {
            //Recebe uma tag pelo terminal --> é preciso trocar isto para o httprequest
            //Alterar para multiplas tags
            while ((in = input.readLine()) != null){

                //vai buscar os templates com a tag pretendida
                List<Script> relatedScripts = getRelatedTemplates(in, scripts);

                //Seleciona e preenche templates
                String finalScript = selectTemplate(relatedScripts, values);

                System.out.println("###News###");
                System.out.println(finalScript);
            }
        } catch (IOException e) {
            System.out.println("Input errror");
            e.printStackTrace();
        }
    }

    private static List<Script> getRelatedTemplates(String tag, List<Script> scripts){
        List<Script> realatedScripts = new ArrayList<>();

        for (Script script : scripts){
            if(script.hasTag(tag))
                realatedScripts.add(script);
        }
        return realatedScripts;
    }

    private static String selectTemplate(List<Script> relatedScripts, Values values){
        List<Script> finalTemplates = new ArrayList<>();

        int usedScripts = 0;
        String finalScript = "";
        Random random = new Random();
        //Se tivermos mais que um script relacionado e se não tivermos usado até ao número de scripts -1
        while (relatedScripts.size()>1 && usedScripts < relatedScripts.size() -1){
            for (Script script: relatedScripts){
                //Prob de 50% de o script ser escolhido
                //random.nextInt(max - min) + min;
                if((random.nextInt(10 - 1) + 1) < 5){
                    //adc scripts a uma lista de scripts selecionados
                    usedScripts ++;
                    finalTemplates.add(script);
                    relatedScripts.remove(script);
                }
            }
        }
        //Se tivermos apenas um script
        if(relatedScripts.size() == 1)
            finalScript = fillScript(relatedScripts.get(0), values);
        else
            for (Script template : finalTemplates)
                finalScript = finalScript + fillScript(template, values) + " ";

        return finalScript;
    }

    private static String fillScript(Script script, Values values){
            //usar replace  e não split
            String news = "";
            String[] words = script.script.split(" ");
            System.out.println(words.length);
            System.out.println(words.toString());
            for (String key: script.getKeyWords_Index().keySet()){
                for(Object index : script.keyIndexes(key)){
                    words[(int) index] = values.getValue(key);
                }
            }

            for (String word : words)
                news = news + " " + word;
            return news;
    }

    private static void csvReader(List scripts) {
        String csvFile = "C:/Users/asus/Desktop/Uni/4ªano/LEI/Backend/GeneratorServer/Scripts.csv";
        String cvsSplitBy = ";";
        List<String> linhas = lerFicheiro(csvFile);
        //removes the line with column names
        linhas.remove(0);
        for(String linha : linhas){
            String[] campos = linha.split(cvsSplitBy);
            //Criar um Script com ID, Tags e Mensagem
            Script toAdd = new Script(Integer.parseInt(campos[0]), campos[1].split(","),campos[2], campos[3]);
            scripts.add(toAdd);
        }
    }

    private static List<String> lerFicheiro(String nomeFich) {
        List<String> lines = new ArrayList<>();
        try { lines = Files.readAllLines(Paths.get(nomeFich)); }
        catch(IOException exc) { exc.printStackTrace(); }
        return lines;
    }
}
