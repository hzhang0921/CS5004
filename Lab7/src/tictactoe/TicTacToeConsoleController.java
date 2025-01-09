package tictactoe;

import java.io.IOException;
import java.util.Scanner;

/**
 * A console-based controller for Tic Tac Toe, implementing TicTacToeController.
 */
public class TicTacToeConsoleController implements TicTacToeController {

  private final Scanner scanner;
  private final Appendable out;

  /**
   * Constructs a TicTacToeConsoleController with the given input and output.
   *
   * @param in  the input source as a Readable
   * @param out the output destination as an Appendable
   */
  public TicTacToeConsoleController(Readable in, Appendable out) {
    if (in == null || out == null) {
      throw new IllegalArgumentException("Readable and Appendable cannot be null.");
    }
    this.scanner = new Scanner(in);
    this.out = out;
  }

  @Override
  public void playGame(TicTacToe model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null.");
    }

    try {
      while (!model.isGameOver()) {
        // Display the current game state and prompt once per turn
        out.append(model.toString()).append("\n");
        out.append("Enter a move for ").append(model.getTurn().toString()).append(":\n");

        int row = -1, col = -1; // Variables to store validated inputs
        boolean validInput = false;

        while (!validInput) {
          if (!scanner.hasNext()) {
            // Handle abrupt input termination
            out.append("Game quit! Ending game state:\n").append(model.toString()).append("\n");
            return;
          }

          String input = scanner.next();

          // Handle quitting the game
          if (input.equalsIgnoreCase("q")) {
            out.append("Game quit! Ending game state:\n").append(model.toString()).append("\n");
            return;
          }

          try {
            // Parse the input for row
            row = Integer.parseInt(input) - 1; // Convert to 0-based index

            if (!scanner.hasNext()) {
              out.append("Invalid input. Row provided without column.\n");
              scanner.nextLine(); // Clear remaining invalid input
              continue;
            }

            // Parse the input for column
            col = Integer.parseInt(scanner.next()) - 1; // Convert to 0-based index
            validInput = true; // Exit the input validation loop if both inputs are valid
          } catch (NumberFormatException e) {
            // Append error message for invalid input
            out.append("Invalid input. Please enter two integers between 1 and 3, or 'q' to quit.\n");
            scanner.nextLine(); // Clear remaining invalid input
          }
        }

        try {
          // Attempt to make the move
          model.move(row, col);
        } catch (IllegalArgumentException e) {
          out.append("Invalid move: ").append(e.getMessage()).append("\n");
          continue; // Skip to the next turn
        }
      }

      // Output the final game state and result
      out.append(model.toString()).append("\n");
      if (model.getWinner() != null) {
        out.append("Game is over! ").append(model.getWinner().toString()).append(" wins.\n");
      } else {
        out.append("Game is over! Tie game.\n");
      }
    } catch (IOException e) {
      throw new IllegalStateException("An IOException occurred during the game.", e);
    }
  }

  /**
   * Appends a message to the output with a newline.
   *
   * @param message the message to append
   * @throws IOException if an error occurs while appending
   */
  private void appendMessage(String message) throws IOException {
    out.append(message).append("\n");
  }
}