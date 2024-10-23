package chess;

/**
 * Public class Bishop that extends the Abstract Class ChessPiece.
 */
public class Bishop extends ChessPiece {

  /**
   * Constructor for Bishop class.
   * @param row represents the row.
   * @param col represents the column.
   * @param color represents the color (B or W)
   */
  public Bishop(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    return isNotSamePosition(row, col) && isDiagonalMove(row, col);
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return piece.getColor() != this.getColor() && canMove(piece.getRow(), piece.getColumn());
  }
}