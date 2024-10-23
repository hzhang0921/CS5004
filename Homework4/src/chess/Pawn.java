package chess;

/**
 * Represents a Pawn Chess Piece.
 */
public class Pawn extends ChessPiece {

  /**
   * Constructor for Pawn. Confirms that Pawn cannot be in the royal row.
   * @param row of the Pawn piece.
   * @param col of the Pawn piece.
   * @param color of the pawn Piece.
   */
  public Pawn(int row, int col, Color color) {
    super(row, col, color);
    if ((color == Color.WHITE && row == 0) || (color == Color.BLACK && row == 7)) {
      throw new IllegalArgumentException("Pawn cannot be in the royal row");
    }
  }

  @Override
  public boolean canMove(int row, int col) {
    if (getColor() == Color.WHITE) {
      return isNotSamePosition(row, col) && ((col == getColumn() && row == getRow() + 1)
          || (getRow() == 1 && col == getColumn() && row == 3)); // Initial two-square move
    } else {
      return isNotSamePosition(row, col) && ((col == getColumn() && row == getRow() - 1)
          || (getRow() == 6 && col == getColumn() && row == 4)); // Initial two-square move
    }
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    if (piece.getColor() == this.getColor()) {
      return false;
    }
    if (getColor() == Color.WHITE) {
      return piece.getRow() == getRow() + 1 && Math.abs(piece.getColumn() - getColumn()) == 1;
    } else {
      return piece.getRow() == getRow() - 1 && Math.abs(piece.getColumn() - getColumn()) == 1;
    }
  }
}