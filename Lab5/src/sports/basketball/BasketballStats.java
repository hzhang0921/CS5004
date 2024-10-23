package sports.basketball;

import java.util.Objects;

public class BasketballStats {
  private double pointsPerGame;
  private double reboundsPerGame;
  private double assistsPerGame;

  public BasketballStats(double pointsPerGame, double reboundsPerGame,
                         double assistsPerGame) {
    this.pointsPerGame = pointsPerGame;
    this.reboundsPerGame = reboundsPerGame;
    this.assistsPerGame = assistsPerGame;
  }
  public double getPointsPerGame() {
    return this.pointsPerGame;
  }

  public double getReboundsPerGame() {
    return this.reboundsPerGame;
  }

  public double getAssistsPerGame() {
    return this.assistsPerGame;
  }

  public boolean equals(Object other) {
    if(this == other) return true;
    if(this.getClass() != other.getClass()) return false;
    BasketballStats o = (BasketballStats) other;
    if(this.assistsPerGame == o.assistsPerGame
        && this.reboundsPerGame == o.reboundsPerGame
        && this.pointsPerGame == o.pointsPerGame) {
      return true;
    }
    return false;
  }

  public int hashCode() {
    return Objects.hash(this.assistsPerGame, this.reboundsPerGame, this.pointsPerGame);
  }

}