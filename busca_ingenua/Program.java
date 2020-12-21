package busca_ingenua;

import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Arrays;
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors

public class Program {
  public static void main(String[] args) throws IOException {
    try {
      File executedTasksFolder = new File("./tarefas_impressora");
      File tasksToVerifyFolder = new File("./verificar");

      File[] executedTasksFiles = executedTasksFolder.listFiles();
      File[] tasksToVerifyFiles = tasksToVerifyFolder.listFiles();

      Arrays.sort(executedTasksFiles);
      Arrays.sort(tasksToVerifyFiles);

      FileWriter executionTime = new FileWriter("./tempo_das_buscas/busca_ingenua.txt");

      for (int i = 0; i < executedTasksFiles.length; i++) {
        Scanner executedScanner = new Scanner(executedTasksFiles[i]);
        Scanner toVerifyScanner = new Scanner(tasksToVerifyFiles[i]);

        FileWriter verifiedOutput = new FileWriter(
            "./tarefas_processadas_busca_ingenua/tarefas_processadas_" + tasksToVerifyFiles[i].getName());

        BuscaIngenua buscaIngenua = new BuscaIngenua();

        while (executedScanner.hasNextLine()) {
          String data = executedScanner.nextLine();

          buscaIngenua.insert(data);
        }

        long initTime = System.currentTimeMillis();
        int lineCount = 0;

        while (toVerifyScanner.hasNextLine()) {
          String data = toVerifyScanner.nextLine();

          if (buscaIngenua.find(data)) {
            verifiedOutput.write(data + "\n");
          }

          lineCount++;
        }

        long finalTime = System.currentTimeMillis();

        executionTime.write(tasksToVerifyFiles[i].getName().replace("verificar", "").replace(".txt", "") + ", "
            + lineCount + ", " + (finalTime - initTime) + "\n");

        verifiedOutput.close();
        executedScanner.close();
        toVerifyScanner.close();
      }

      executionTime.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");

      e.printStackTrace();
    }
  }
}
