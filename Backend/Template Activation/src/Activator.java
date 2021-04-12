import java.lang.Math;
import java.util.List;
import java.util.Random;

public class Activator {
    private double e;

    public Activator() {
        e = 2.2204460492503130808472633361816;
    }

    public double sigmoid(Template t){
        double x = (t.getFeedback() - t.getNrUses())*0.1; // +feedBack -> + prob   +nrUses -> - prob
        return 1/(1 + Math.pow(this.e, -x));
    }

    public Template chooseTemp(List<Template> tmps){
        Random r = new Random();
        int rangeMin = 0;
        int rangeMax = 1;
        double max = 0;
        Template maxTmp = null;

        //try to choose one random template
        for(Template tmp : tmps){
            double randomValue = rangeMin + (rangeMax - rangeMin) * r.nextDouble();
            double activationValue = sigmoid(tmp);

            //if activationValue>randomValue template is selected
            if (activationValue > randomValue){
                System.out.println("Found one");
                return tmp;
            }

            if (activationValue > max){
                max     = activationValue;
                maxTmp = tmp;
            }
        }
        //If we iterate between all the templates and none are selected we choose the best one
        System.out.println("Let's pick the max");
        return maxTmp;
    }
}
