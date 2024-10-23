import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chess.Bishop;
import chess.Pawn;
import chess.Color;

public class BishopTest {

  @Test
  void testBishopValidMove() {
    Bishop bishop = new Bishop(2, 0, Color.WHITE);
    assertTrue(bishop.canMove(4, 2)); // Diagonal move
    assertFalse(bishop.canMove(3, 1)); // Invalid move
  }

  @Test
  void testBishopCanKill() {
    Bishop bishop = new Bishop(2, 0, Color.WHITE);
    Pawn enemyPawn = new Pawn(4, 2, Color.BLACK);
    Pawn friendlyPawn = new Pawn(4, 2, Color.WHITE);

    assertTrue(bishop.canKill(enemyPawn)); // Can kill enemy pawn
    assertFalse(bishop.canKill(friendlyPawn)); // Cannot kill friendly pawn
  }
}