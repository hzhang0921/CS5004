package chess;

/**
 * public Abstract Class ChessPiece that over arches different ChessPieces.
 * Implements IChessPiece.
 */
public abstract class ChessPiece implements IChessPiece {
  private int row;
  private int col;
  private Color color;

  /**
   * General Constructor for ChessPieces.
   * @param row represents Row
   * @param col represents column
   * @param color represents color (B or W)
   */
  public ChessPiece(int row, int col, Color color) {
    if (row < 0 || row > 7 || col < 0 || col > 7) {
      throw new IllegalArgumentException("Invalid position");
    }
    this.row = row;
    this.col = col;
    this.color = color;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.col;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * General Protected method to confirm that a chessPiece is not trying to move to the same position.
   * Method is protected just in case there was ever anything else that would be inherited from IChessPiece.
   * @param row
   * @param col
   * @return
   */
  protected boolean isNotSamePosition(int row, int col) {
    return !(this.row == row && this.col == col);
  }

  /**
   * General Protected method to check if a move is diagonal
   * @param newRow
   * @param newCol
   * @return
   */
  protected boolean isDiagonalMove(int newRow, int newCol) {
    return Math.abs(newRow - this.row) == Math.abs(newCol - this.col);
  }

  /**
   * General Protected method to check if a move is straight.
   * @param newRow
   * @param newCol
   * @return
   */
  protected boolean isStraightMove(int newRow, int newCol) {
    return this.row == newRow || this.col == newCol;
  }
}