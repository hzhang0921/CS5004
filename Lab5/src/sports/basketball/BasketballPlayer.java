package sports.basketball;
import sports.IPlayer;

import java.util.Objects;

/**
 * Represents a basketball player with a name, age, height, and stats.
 */
public class BasketballPlayer implements IPlayer<BasketballStats> {
  private String name;
  private int age;
  private double height;
  private BasketballStats stats;

  /**
   * Constructor for the specified name, age, height, and stats.
   *
   * @param name   the player's name
   * @param age    the player's age
   * @param height the player's height
   * @param stats  the player's basketball stats
   */
  public BasketballPlayer(String name, int age, double height, BasketballStats stats) {
    this.name = name;
    this.age = age;
    this.height = height;
    this.stats = new BasketballStats(stats.getPointsPerGame(), stats.getReboundsPerGame(), stats.getAssistsPerGame());
  }

  /**
   * Copy constructor for creating a basketball object based on another player.
   *
   * @param other the player to copy
   */
  public BasketballPlayer(BasketballPlayer other) {
    this(other.getName(), other.getAge(), other.getHeight(), new BasketballStats(other.stats.getPointsPerGame(), other.stats.getReboundsPerGame(), other.stats.getAssistsPerGame()));
  }

  /**
   * Returns the player's name.
   *
   * @return the name
   */
  @Override
  public String getName() {
    return this.name;
  }

  /**
   * Returns the player's age.
   *
   * @return the age
   */
  @Override
  public int getAge() {
    return this.age;
  }

  /**
   * Returns the player's height.
   *
   * @return the height
   */
  @Override
  public double getHeight() {
    return this.height;
  }

  /**
   * Returns the player's stats.
   *
   * @return the stats
   */
  @Override
  public BasketballStats getStats() {
    return this.stats;
  }

  /**
   * Checks if this BasketballPlayer is equal to another by comparing name, age, height, and stats.
   *
   * @param obj the object to compare with
   * @returns true if true, false if false
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    BasketballPlayer other = (BasketballPlayer) obj;
    return name.equals(other.name) &&
        age == other.age &&
        Double.compare(height, other.height) == 0 &&
        stats.equals(other.stats);
  }

  /**
   * Returns a hash code for this BasketballPlayer, based on name, age, height, and stats.
   *
   * @return the hash code value
   */
  @Override
  public int hashCode() {
    return Objects.hash(name, age, height, stats);
  }
}