package chess;

/**
 * Represents a Queen ChessPiece.
 */
public class Queen extends ChessPiece {

  /**
   * Queen ChessPiece Constructor.
   * @param row of the queen chesspiece.
   * @param col of the queen chesspiece.
   * @param color of the queen chesspiece.
   */
  public Queen(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    return isNotSamePosition(row, col) && (isDiagonalMove(row, col) || isStraightMove(row, col));
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return piece.getColor() != this.getColor() && canMove(piece.getRow(), piece.getColumn());
  }
}