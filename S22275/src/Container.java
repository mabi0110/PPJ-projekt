public class Container {
    private double weight;
    private String content;
    private int id;
    public static int counter = 1;

    private int x;

    private int y;

    private int z;
    private ContainerShip containerShip;

    public Container() {
        this.id = counter++;
        this.weight = getRandomWeight();
    }

    public Container(int id, double weight, String content) {
        this.id = id;
        this.weight = weight;
        this.content = content;
    }
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int[] getPosition() {
        return new int[]{getX(), getY(), getZ()};
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
