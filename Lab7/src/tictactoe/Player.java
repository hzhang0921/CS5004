package tictactoe;

public enum Player {
  X, O;

  @Override
  public String toString() {
    return name();
  }
}
