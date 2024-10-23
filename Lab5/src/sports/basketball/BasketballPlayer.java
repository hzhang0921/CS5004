package sports.basketball;
import sports.IPlayer;

public class BasketballPlayer implements IPlayer<BasketballStats> {
  private String name;
  private int age;
  private double height;
  private BasketballStats stats;

  public BasketballPlayer(String name, int age, double height, BasketballStats stats) {
    this.name = name;
    this.age = age;
    this.height = height;
    this.stats = new BasketballStats(stats.getPointsPerGame(), stats.getReboundsPerGame(), stats.getAssistsPerGame());
  }

  public BasketballPlayer(BasketballPlayer other) {
    this(other.getName(), other.getAge(), other.getHeight(), new BasketballStats(other.stats.getPointsPerGame(), other.stats.getReboundsPerGame(), other.stats.getAssistsPerGame()));
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getAge() {
    return this.age;
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public BasketballStats getStats() {
    return this.stats;
  }

}