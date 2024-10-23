import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chess.Queen;
import chess.Rook;
import chess.Color;

public class QueenTest {

  @Test
  void testQueenValidMove() {
    Queen queen = new Queen(4, 4, Color.BLACK);
    assertTrue(queen.canMove(7, 7)); // should be valid Diagonal move
    assertTrue(queen.canMove(4, 7)); // Should be valid Horizontal move
    assertTrue(queen.canMove(7, 4)); // Should be valid Vertical move
    assertFalse(queen.canMove(5, 6)); // Should be invalid move as L shape
  }

  @Test
  void testQueenCanKill() {
    Queen queen = new Queen(4, 4, Color.BLACK);
    Rook enemyRook = new Rook(7, 7, Color.WHITE);
    Rook friendlyRook = new Rook(7, 7, Color.BLACK);

    assertTrue(queen.canKill(enemyRook)); // Can kill enemy rook
    assertFalse(queen.canKill(friendlyRook)); // Cannot kill friendly rook
  }
}