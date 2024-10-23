package sports.basketball;

import java.util.Comparator;

public class BasketballPlayerComparator implements Comparator<BasketballPlayer> {

  @Override
  public int compare(BasketballPlayer player1, BasketballPlayer player2) {
    return Double.compare(player1.getStats().getPointsPerGame(), player2.getStats().getPointsPerGame());
  }
}