package tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Implementation of the TicTacToe interface, representing the Model of the game.
 */
public class TicTacToeModel implements TicTacToe {

  private final Player[][] board;
  private Player currentPlayer;
  private Player winner;
  private boolean isGameOver;
  private int moves;

  /**
   * Creates a new game of Tic Tac Toe.
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.currentPlayer = Player.X;
    this.isGameOver = false;
    this.moves = 0;
    this.winner = null;
  }

  @Override
  public void move(int r, int c) {
    if (isGameOver) {
      throw new IllegalStateException("The game is already over.");
    }
    if (r < 0 || r >= 3 || c < 0 || c >= 3) {
      throw new IllegalArgumentException("Invalid move - out of bounds!");
    }
    if (board[r][c] != null) {
      throw new IllegalArgumentException("Invalid move - space is already taken!");
    }

    board[r][c] = currentPlayer;
    moves++;

    if (checkWin(r, c)) {
      isGameOver = true;
      winner = currentPlayer;
    } else if (moves == 9) { // All spaces filled, game ends in a draw
      isGameOver = true;
    } else {
      currentPlayer = (currentPlayer == Player.X) ? Player.O : Player.X;
    }
  }

  @Override
  public Player getTurn() {
    return currentPlayer;
  }

  @Override
  public boolean isGameOver() {
    return isGameOver;
  }

  @Override
  public Player getWinner() {
    return winner;
  }

  @Override
  public Player[][] getBoard() {
    Player[][] copy = new Player[3][3];
    for (int i = 0; i < 3; i++) {
      System.arraycopy(board[i], 0, copy[i], 0, 3);
    }
    return copy;
  }

  @Override
  public Player getMarkAt(int r, int c) {
    if (r < 0 || r >= 3 || c < 0 || c >= 3) {
      throw new IllegalArgumentException("Invalid position.");
    }
    return board[r][c];
  }

  /**
   * Checks if the current player has won after placing a mark at (r, c).
   *
   * @param r the row of the last move
   * @param c the column of the last move
   * @return true if the current player has won, false otherwise
   */
  private boolean checkWin(int r, int c) {
    // Check the row
    if (board[r][0] == currentPlayer && board[r][1] == currentPlayer
        && board[r][2] == currentPlayer) {
      return true;
    }

    // Check the column
    if (board[0][c] == currentPlayer && board[1][c] == currentPlayer
        && board[2][c] == currentPlayer) {
      return true;
    }

    // Check the diagonals
    if (r == c && board[0][0] == currentPlayer && board[1][1] == currentPlayer
        && board[2][2] == currentPlayer) {
      return true;
    }

    if (r + c == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer
        && board[2][0] == currentPlayer) {
      return true;
    }

    return false;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(getBoard()).map(
            row -> " " + Arrays.stream(row).map(
                p -> p == null ? " " : p.toString()).collect(Collectors.joining(" | ")))
        .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
  }
}