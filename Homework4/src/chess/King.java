package chess;

/**
 * represents a King ChessPiece.
 */
public class King extends ChessPiece {

  /**
   * Constructor for King Chess Piece.
   * @param row of the King Chess Piece.
   * @param col of the King Chess Piece.
   * @param color of the King Chess Piece.
   */
  public King(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    int dRow = Math.abs(this.getRow() - row);
    int dCol = Math.abs(this.getColumn() - col);
    return isNotSamePosition(row, col) && (dRow <= 1 && dCol <= 1);
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return piece.getColor() != this.getColor() && canMove(piece.getRow(), piece.getColumn());
  }
}