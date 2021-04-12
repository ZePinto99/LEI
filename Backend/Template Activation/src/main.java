import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args){
        Activator activator = new Activator();

        List<Template> tmps = new ArrayList<>();
        for(int i = 0; i<10; i++){
            Template t = new Template(i);
            tmps.add(t);
        }

        int value = 0;
        for (Template tmp : tmps){
            tmp.updateFeedback(value);
            value++;
        }

        Template chosenOne = activator.chooseTemp(tmps);

        System.out.println("Chosen One");
        System.out.println(chosenOne.getId());

        value = 0;
        for (Template tmp : tmps){
            tmp.updateFeedback(-2*value);
            value++;
        }

        chosenOne = activator.chooseTemp(tmps);

        System.out.println("Chosen One");
        System.out.println(chosenOne.getId());
    }
}
