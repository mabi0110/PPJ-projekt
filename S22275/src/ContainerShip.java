public class ContainerShip {
    private final int x;
    private final int y;
    private final int z;

    public ContainerShip(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double[][][] load(Container[] containers){
        return new double[x][y][z];
    }
}
