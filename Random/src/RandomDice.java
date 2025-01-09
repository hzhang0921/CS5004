import java.util.Random;

public class RandomDice {
  public static void main(String[] args) {
    Random number = new Random();

    int dieRolls = 0;
    while (true) {
      int first = number.nextInt(6) + 1;
      int second = number.nextInt(6) + 1;
      dieRolls++;

      System.out.println("Roll " + dieRolls + ": Die 1 = " + first + ", Die 2 = " + second +
          " (Total: " + (first + second) + ")");

      if (first + second == 7) {
        break; // Exit the loop when the combined total is
      }
    }
  }
}

