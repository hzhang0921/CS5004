import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chess.Rook;
import chess.Knight;
import chess.Color;

public class RookTest {

  @Test
  void testRookValidMove() {
    Rook rook = new Rook(0, 0, Color.BLACK);
    assertTrue(rook.canMove(0, 7)); // Horizontal move
    assertTrue(rook.canMove(7, 0)); // Vertical move
    assertFalse(rook.canMove(7, 7)); // Invalid diagonal move
  }

  @Test
  void testRookCanKill() {
    Rook rook = new Rook(0, 0, Color.BLACK);
    Knight enemyKnight = new Knight(0, 7, Color.WHITE);
    Knight friendlyKnight = new Knight(0, 7, Color.BLACK);

    assertTrue(rook.canKill(enemyKnight)); // Can kill enemy knight
    assertFalse(rook.canKill(friendlyKnight)); // Cannot kill friendly knight
  }
}