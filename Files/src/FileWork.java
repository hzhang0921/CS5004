import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileWork {
  private File file;

  public FileWork(String fileName) {
    file = new File(fileName);
  }

  public FileWork() {
    file = new File("test.txt");
  }

  public void ReadFile(String fileName) throws FileNotFoundException{
    Scanner scanner = new Scanner(file);
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      System.out.println(line);
    }
    scanner.close();
  }
}