import java.io.*;
import java.util.Scanner;


public class S22275 {
    private static final String FILE_NAME = "containers.txt";
    private static final int ROW = 6;
    private static final int COLUMN = 6;
    private static final int HEIGHT = 4;
//    private static final int ROW = 30;
//    private static final int COLUMN = 50;
//    private static final int HEIGHT = 10;
    private static Container[] containers = new Container[ROW * COLUMN * HEIGHT];

    private static final ContainerShip containerShip = new ContainerShip(ROW, COLUMN, HEIGHT);

    public static void main(String[] args) {
        containers = createContainers();
        writeContainersToFile(containers);
        containers = readContainersFromFile();
        containers = sortContainersByWeight();
        printContainers();
        double[][][] loadedContainers = containerShip.load(containers);
    }

    private static Container[] sortContainersByWeight() {
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
        return containers;
    }

    private static void printContainers() {
        for (Container container : containers) {
            System.out.printf("%-6d %-6.2f %-12s%n",
                    container.getId(), container.getWeight(), container.getContent());
        }
    }

    private static Container[] readContainersFromFile() {
        int index = 0;
        Container container;
        try (Scanner scanner = new Scanner(new File(FILE_NAME))) {
            while (scanner.hasNextLine()) {
                container = createContainerFromLine(scanner.nextLine());
                containers[index] = container;
                index++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nie znaleziono pliku " + FILE_NAME);
        }
        return containers;
    }

    private static Container createContainerFromLine(String line) {
        String[] data = line.split(";");
        int id = Integer.parseInt(data[0]);
        double weight = Double.parseDouble(data[1]);
        String content = data[2];
        return new Container(id, weight, content);
    }

    private static void writeContainersToFile(Container[] containers) {
        try (
                FileWriter fileWriter = new FileWriter(FILE_NAME);
                BufferedWriter writer = new BufferedWriter(fileWriter)
        ) {
            for (Container container : containers) {
                writer.write(String.valueOf((container)));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku " + FILE_NAME);
        }
    }

    private static Container[] createContainers() {
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
        return containers;
    }
}
