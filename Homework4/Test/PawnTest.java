import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chess.Pawn;
import chess.Color;

public class PawnTest {

  @Test
  void testPawnValidMove() {
    Pawn whitePawn = new Pawn(1, 1, Color.WHITE);
    Pawn blackPawn = new Pawn(6, 1, Color.BLACK);

    assertTrue(whitePawn.canMove(3, 1)); // Two-square move on first turn
    assertTrue(whitePawn.canMove(2, 1)); // One-square move
    assertFalse(whitePawn.canMove(2, 2)); // Diagonal move not allowed

    assertTrue(blackPawn.canMove(4, 1)); // Two-square move on first turn
    assertTrue(blackPawn.canMove(5, 1)); // One-square move
  }

  @Test
  void testPawnCanKill() {
    Pawn whitePawn = new Pawn(1, 1, Color.WHITE);
    Pawn enemyPawn = new Pawn(2, 2, Color.BLACK);
    Pawn friendlyPawn = new Pawn(2, 2, Color.WHITE);

    assertTrue(whitePawn.canKill(enemyPawn)); // Can kill enemy pawn diagonally
    assertFalse(whitePawn.canKill(friendlyPawn)); // Cannot kill friendly pawn
  }
}