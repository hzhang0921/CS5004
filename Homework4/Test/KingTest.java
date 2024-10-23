import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chess.King;
import chess.Pawn;
import chess.Color;

public class KingTest {

  @Test
  void testKingValidMove() {
    King king = new King(4, 4, Color.WHITE);
    assertTrue(king.canMove(5, 5)); // Diagonal move
    assertTrue(king.canMove(4, 5)); // Horizontal move
    assertTrue(king.canMove(5, 4)); // Vertical move
    assertFalse(king.canMove(6, 6)); // Too far move
  }

  @Test
  void testKingCanKill() {
    King king = new King(4, 4, Color.WHITE);
    Pawn enemyPawn = new Pawn(5, 5, Color.BLACK);
    Pawn friendlyPawn = new Pawn(5, 5, Color.WHITE);

    assertTrue(king.canKill(enemyPawn)); // Can kill enemy pawn
    assertFalse(king.canKill(friendlyPawn)); // Cannot kill same-color pawn
  }
}