public class Container {

    private double weight;
    private String content;
    private int id;
    public static int counter = 1;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Container() {
        this.id = counter++;
        this.weight = getRandomWeight();
    }

    public Container(int id, double weight, String content) {
        this.id = id;
        this.weight = weight;
        this.content = content;
    }

    protected String getRandomContent() {
        return null;
    }

    protected double getRandomWeight() {
        return 100 + (Math.random() * ((500 - 100) + 1));
    }

    @Override
    public String toString() {
        return id + ";" + weight + ";" + content;
    }
}
