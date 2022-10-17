import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class S22275 {
    private static final String FILE_NAME_TXT = "containers.txt";
    private static final String FILE_NAME_XML = "containers.xml";
    private static final int ROW = 30; //30
    private static final int COLUMN = 50; //50
    private static final int HEIGHT = 10; //10
    private static final Container[] containers = new Container[ROW * COLUMN * HEIGHT];
    private static final ContainerShip containerShip = new ContainerShip(ROW, COLUMN, HEIGHT);

    public static void main(String[] args) {
        createContainers();
        writeContainersToTxtFile();
        readContainersFromFile();
        sortContainersByWeight();
//        printContainers();
        Container[][][] loadedContainerShip = containerShip.load(containers);
        checkWeight(loadedContainerShip);
        printLoadedContainerShip(loadedContainerShip);
        writeContainersToXmlFile();
    }

    private static void writeContainersToXmlFile() {
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME_XML);
                BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            String stringFormat = ("%-10s %-14s %-15s %-16s %n");
            String dataFormat = ("%-10d %-14s %-15f %-16s %n");

            writer.write(stringFormat.formatted("id", "[x][y][z]", "weight", "content"));

            for (Container container : containers) {
                int id = container.getId();
                int[] position = container.getPosition();
                double weight = container.getWeight();
                String content = container.getContent();
                writer.write(dataFormat.formatted(id, Arrays.toString(position), weight, content));
            }
            System.out.println("Zapisano kontenery w pliku " + FILE_NAME_XML);
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku " + FILE_NAME_XML);
        }
    }

    private static void checkWeight(Container[][][] loadedContainerShip) {
        double sumUpperWeight = 0, sumLowerWeight = 0, sumLeftWeight = 0, sumRightWeight = 0;

        for (int z = 0; z < loadedContainerShip[0][0].length; z++) {
            for (int x = 0; x < loadedContainerShip.length; x++) {
                for (int y = 0; y < loadedContainerShip[0].length; y++) {
                    if (x < loadedContainerShip.length / 2) {
                        if (y < loadedContainerShip[0].length / 2) {
                            sumLeftWeight += loadedContainerShip[x][y][z].getWeight();
                        } else {
                            sumRightWeight += loadedContainerShip[x][y][z].getWeight();
                        }
                        sumUpperWeight += loadedContainerShip[x][y][z].getWeight();
                    } else {
                        if (y < loadedContainerShip[0].length / 2) {
                            sumLeftWeight += loadedContainerShip[x][y][z].getWeight();
                        } else {
                            sumRightWeight += loadedContainerShip[x][y][z].getWeight();
                        }
                        sumLowerWeight += loadedContainerShip[x][y][z].getWeight();
                    }
                }
            }
        }
        System.out.println("Weight check");
        System.out.println("sum of upper weight: " + sumUpperWeight);
        System.out.println("sum of lower weight: " + sumLowerWeight);
        System.out.println("sum of left weight: " + sumLeftWeight);
        System.out.println("sum of right weight: " + sumRightWeight);
        System.out.println();
    }

    private static void printLoadedContainerShip(Container[][][] loadedContainerShip) {
        for (int z = 0; z < loadedContainerShip[0][0].length; z++) {
            for (int x = 0; x < loadedContainerShip.length; x++) {
                for (int y = 0; y < loadedContainerShip[0].length; y++) {
                    System.out.printf("%.2f ", loadedContainerShip[x][y][z].getWeight());
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private static void sortContainersByWeight() {
        int n = containers.length;
        while (n > 1) {
            for (int i = 0; i < n - 1; i++) {
                if (containers[i].getWeight() < containers[i + 1].getWeight()) {
                    Container tempContainer = containers[i];
                    containers[i] = containers[i + 1];
                    containers[i + 1] = tempContainer;
                }
            }
            n = n - 1;
        }
    }

    private static void printContainers() {
        for (Container container : containers) {
            System.out.printf("%-6d %-6.2f %-12s%n",
                    container.getId(), container.getWeight(), container.getContent());
        }
    }

    private static void readContainersFromFile() {
        int index = 0;
        Container container;
        try (Scanner scanner = new Scanner(new File(FILE_NAME_TXT))) {
            while (scanner.hasNextLine()) {
                container = createContainerFromLine(scanner.nextLine());
                containers[index] = container;
                index++;
            }
            System.out.println("Wczytano kontenery z pliku " + FILE_NAME_TXT);
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku " + FILE_NAME_TXT);
        }
    }

    private static Container createContainerFromLine(String line) {
        String[] data = line.split(";");
        int id = Integer.parseInt(data[0]);
        double weight = Double.parseDouble(data[1]);
        String content = data[2];
        return new Container(id, weight, content);
    }

    private static void writeContainersToTxtFile() {
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME_TXT);
                BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            for (Container container : containers) {
                writer.write(String.valueOf((container)));
                writer.newLine();
            }
            System.out.println("Zapisano kontenery w pliku " + FILE_NAME_TXT);
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku " + FILE_NAME_TXT);
        }
    }

    private static void createContainers() {
        Container container = null;
        for (int i = 0; i < containers.length; i++) {
            int randomInt = (int) (Math.random() * 3);
            switch (randomInt) {
                case 0 -> container = new TankContainer();
                case 1 -> container = new RefrigeratedContainer();
                case 2 -> container = new OpenTopContainer();
            }
            containers[i] = container;
        }
    }
}
