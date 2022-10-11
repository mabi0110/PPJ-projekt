import java.io.*;
import java.util.Scanner;


public class S22275 {
    private static final String FILE_NAME = "containers.txt";
    private static final int ROW = 4;
    private static final int COLUMN = 6;
    private static final int HEIGHT = 4;
//    private static final int ROW = 30;
//    private static final int COLUMN = 50;
//    private static final int HEIGHT = 10;
    private static final Container[] containers = new Container[ROW * COLUMN * HEIGHT];
    private static final ContainerShip containerShip = new ContainerShip(ROW, COLUMN, HEIGHT);

    public static void main(String[] args) {
        createContainers();
        writeContainersToFile();
        readContainersFromFile();
        sortContainersByWeight();
//        printContainers();
        Container[][][] loadedContainerShip = containerShip.load(containers);
        printLoadedContainerShip(loadedContainerShip);


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
          while (n > 1){
              for (int i = 0; i < n - 1 ; i++) {
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
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                container = createContainerFromLine(scanner.nextLine());
                containers[index] = container;
                index++;
            }
            System.out.println("Wczytano kontenery z pliku " + FILE_NAME);
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku " + FILE_NAME);
        }
    }

    private static Container createContainerFromLine(String line) {
        String[] data = line.split(";");
        int id = Integer.parseInt(data[0]);
        double weight = Double.parseDouble(data[1]);
        String content = data[2];
        return new Container(id, weight, content);
    }

    private static void writeContainersToFile() {
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            for (Container container : containers) {
                writer.write(String.valueOf((container)));
                writer.newLine();
            }
            System.out.println("Zapisano kontenery w pliku " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku " + FILE_NAME);
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
