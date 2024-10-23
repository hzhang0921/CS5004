package chess;

/**
 * interface class that will be implemented for ChessPieces.
 */
public interface IChessPiece {
  /**
   * gets the Row of ChessPiece.
   * @return int.
   */
  int getRow();

  /**
   * gets the Column of ChessPiece.
   * @return int.
   */
  int getColumn();

  /**
   * gets the color of ChessPiece.
   * @return Color.
   */
  Color getColor();

  /**
   * will return a boolean of whether or not a chess piece can go there.
   * @param row represents target row
   * @param col represents target column
   * @return boolean
   */
  boolean canMove(int row, int col);

  /**
   * will return a boolean of whether or not a chess piece can kill another chessPiece.
   * @param piece represents the target ChessPiece.
   * @return boolean.
   */
  boolean canKill(ChessPiece piece);
}