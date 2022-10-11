public class ContainerShip {
    private final int x;
    private final int y;
    private final int z;
    private int index = 0;

    public ContainerShip(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Container[][][] load(Container[] containers) {
        Container[][][] containerShip = new Container[x][y][z];
        for (int z = 0; z < containerShip[0][0].length; z++) {
            if (z % 2 == 0) {
                for (int x = 0; x < containerShip.length; x++) {
                    if (x % 2 == 0) {
                        for (int y = 0; y < containerShip[0].length; y++) {
                            containerShip[x][y][z] = containers[index];
                            index++;
                        }
                    } else {
                        for (int y = containerShip[0].length - 1; y >= 0; y--) {
                            containerShip[x][y][z] = containers[index];
                            index++;
                        }
                    }
                }
            } else {
                for (int x = containerShip.length - 1; x >= 0; x--) {
                    if (x % 2 == 0) {
                        for (int y = 0; y < containerShip[0].length; y++) {
                            containerShip[x][y][z] = containers[index];
                            index++;
                        }
                    } else {
                        for (int y = containerShip[0].length - 1; y >= 0; y--) {
                            containerShip[x][y][z] = containers[index];
                            index++;
                        }
                    }
                }
            }
        }
        return containerShip;
    }

}

