package chess;

/**
 * Represents a Rook ChessPiece.
 */
public class Rook extends ChessPiece {

  /**
   * Constructor for Rook ChessPiece.
   * @param row of where the Rook is.
   * @param col of where the Rook is.
   * @param color of where the Rook is.
   */
  public Rook(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    return isNotSamePosition(row, col) && (isStraightMove(row, col));
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return piece.getColor() != this.getColor() && canMove(piece.getRow(), piece.getColumn());
  }
}
