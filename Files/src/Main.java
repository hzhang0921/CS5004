import java.io.FileNotFoundException;

public class Main {

  public static void main(String[] args) throws FileNotFoundException {
    FileWork f = new FileWork("input.txt");
    try {
      f.ReadFile();
    } catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
    System.out.println("Continue with the rest of my code");
    System.out.println("Ready?");
  }
}
