import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chess.Knight;
import chess.Pawn;
import chess.Color;

public class KnightTest {

  @Test
  void testKnightValidMove() {
    Knight knight = new Knight(4, 4, Color.WHITE);
    assertTrue(knight.canMove(6, 5)); // Should be valid L-shaped move
    assertTrue(knight.canMove(5, 6)); // Should be valid L-shaped move
    assertFalse(knight.canMove(4, 4)); // Same position so False
    assertFalse(knight.canMove(5, 5)); // Invalid move
  }

  @Test
  void testKnightCanKill() {
    Knight knight = new Knight(4, 4, Color.WHITE);
    Pawn enemyPawn = new Pawn(6, 5, Color.BLACK);
    Pawn friendlyPawn = new Pawn(6, 5, Color.WHITE);

    assertTrue(knight.canKill(enemyPawn)); // Can kill enemy pawn
    assertFalse(knight.canKill(friendlyPawn)); // Cannot kill same-color pawn
  }
}