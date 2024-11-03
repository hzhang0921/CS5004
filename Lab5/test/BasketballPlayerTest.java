import org.junit.jupiter.api.Test;
import sports.basketball.BasketballPlayer;
import sports.basketball.BasketballStats;

import static org.junit.jupiter.api.Assertions.*;

class BasketballPlayerTest {

  @Test
  void testConstructorAndGetters() {
    BasketballStats stats = new BasketballStats(25.3, 10.1, 5.7);
    BasketballPlayer player = new BasketballPlayer("John Doe", 28, 6.5, stats);

    assertEquals("John Doe", player.getName());
    assertEquals(28, player.getAge());
    assertEquals(6.5, player.getHeight());
    assertEquals(stats, player.getStats());
  }

  @Test
  void testCopyConstructor() {
    BasketballStats stats = new BasketballStats(20.0, 8.0, 4.0);
    BasketballPlayer original = new BasketballPlayer("Jane Smith", 30, 6.2, stats);
    BasketballPlayer copy = new BasketballPlayer(original);

    assertEquals(original, copy);
    assertNotSame(original, copy); // Ensure it’s a deep copy
    assertNotSame(original.getStats(), copy.getStats()); // Ensure stats are deeply copied
  }

  @Test
  void testEqualsSameObject() {
    BasketballStats stats = new BasketballStats(15.0, 7.0, 3.0);
    BasketballPlayer player = new BasketballPlayer("Alice", 25, 5.9, stats);

    assertEquals(player, player); // Should be equal to itself
  }

  @Test
  void testEqualsIdenticalPlayers() {
    BasketballStats stats1 = new BasketballStats(22.0, 9.5, 6.1);
    BasketballStats stats2 = new BasketballStats(22.0, 9.5, 6.1);

    BasketballPlayer player1 = new BasketballPlayer("Bob", 27, 6.0, stats1);
    BasketballPlayer player2 = new BasketballPlayer("Bob", 27, 6.0, stats2);

    assertEquals(player1, player2); // Identical players
  }

  @Test
  void testEqualsDifferentNames() {
    BasketballStats stats = new BasketballStats(10.0, 5.0, 2.0);
    BasketballPlayer player1 = new BasketballPlayer("Charlie", 22, 6.3, stats);
    BasketballPlayer player2 = new BasketballPlayer("Chuck", 22, 6.3, stats);

    assertNotEquals(player1, player2);
  }

  @Test
  void testEqualsDifferentAges() {
    BasketballStats stats = new BasketballStats(12.0, 6.5, 4.0);
    BasketballPlayer player1 = new BasketballPlayer("David", 20, 6.1, stats);
    BasketballPlayer player2 = new BasketballPlayer("David", 25, 6.1, stats);

    assertNotEquals(player1, player2);
  }

  @Test
  void testEqualsDifferentHeights() {
    BasketballStats stats = new BasketballStats(18.5, 8.2, 5.3);
    BasketballPlayer player1 = new BasketballPlayer("Eve", 24, 5.8, stats);
    BasketballPlayer player2 = new BasketballPlayer("Eve", 24, 6.0, stats);

    assertNotEquals(player1, player2);
  }

  @Test
  void testEqualsDifferentStats() {
    BasketballStats stats1 = new BasketballStats(25.0, 10.0, 5.0);
    BasketballStats stats2 = new BasketballStats(20.0, 8.0, 4.0);

    BasketballPlayer player1 = new BasketballPlayer("Frank", 29, 6.4, stats1);
    BasketballPlayer player2 = new BasketballPlayer("Frank", 29, 6.4, stats2);

    assertNotEquals(player1, player2);
  }

  @Test
  void testEqualsNullObject() {
    BasketballStats stats = new BasketballStats(30.0, 12.0, 6.0);
    BasketballPlayer player = new BasketballPlayer("Grace", 32, 6.6, stats);

    assertNotEquals(player, null);
  }

  @Test
  void testEqualsDifferentClass() {
    BasketballStats stats = new BasketballStats(15.0, 7.0, 3.5);
    BasketballPlayer player = new BasketballPlayer("Henry", 28, 6.1, stats);

    assertNotEquals(player, "Not a BasketballPlayer");
  }

  @Test
  void testHashCodeConsistency() {
    BasketballStats stats = new BasketballStats(20.5, 9.5, 5.2);
    BasketballPlayer player = new BasketballPlayer("Isaac", 26, 6.3, stats);

    int initialHash = player.hashCode();
    assertEquals(initialHash, player.hashCode());
  }

  @Test
  void testHashCodeEqualityForEqualObjects() {
    BasketballStats stats1 = new BasketballStats(23.0, 8.5, 4.5);
    BasketballStats stats2 = new BasketballStats(23.0, 8.5, 4.5);

    BasketballPlayer player1 = new BasketballPlayer("Jake", 25, 6.2, stats1);
    BasketballPlayer player2 = new BasketballPlayer("Jake", 25, 6.2, stats2);

    assertEquals(player1.hashCode(), player2.hashCode());
  }

  @Test
  void testHashCodeInequalityForUnequalObjects() {
    BasketballStats stats1 = new BasketballStats(20.0, 10.0, 5.0);
    BasketballStats stats2 = new BasketballStats(15.0, 7.0, 3.0);

    BasketballPlayer player1 = new BasketballPlayer("Leo", 27, 6.5, stats1);
    BasketballPlayer player2 = new BasketballPlayer("Leo", 27, 6.5, stats2);

    assertNotEquals(player1.hashCode(), player2.hashCode());
  }
}