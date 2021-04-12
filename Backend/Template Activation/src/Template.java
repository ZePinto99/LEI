public class Template {
    private int id;
    private int nrUses;
    private double feedback;

    public Template(int id) {
        this.id = id;
        this.nrUses = 0;
        this.feedback = 0;
    }

    public void updateFeedback(double value){
        this.feedback += value;
    }

    public void updateNrUses(){
        this.nrUses++;
    }

    private void setNrUses(int nrUses) {
        this.nrUses = nrUses;
    }

    private void setFeedback(double feedback) {
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public int getNrUses() {
        return nrUses;
    }

    public double getFeedback() {
        return feedback;
    }
}
