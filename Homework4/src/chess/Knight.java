package chess;

/**
 * Represents a Knight Chess Piece.
 */
public class Knight extends ChessPiece {

  /**
   * Constructor class for Knight.
   * @param row of the knight piece.
   * @param col of the knight piece.
   * @param color of the knight piece.
   */
  public Knight(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    int dRow = Math.abs(this.getRow() - row);
    int dCol = Math.abs(this.getColumn() - col);
    return isNotSamePosition(row, col) && ((dRow == 2 && dCol == 1) || (dRow == 1 && dCol == 2));
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return piece.getColor() != this.getColor() && canMove(piece.getRow(), piece.getColumn());
  }
}