package hw2;

/**
 * Enum representing the type of room.
 */
public enum RoomType {
  SINGLE(1), DOUBLE(2), FAMILY(4);

  private final int maxOccupancy;

  /**
   * Constructor for RoomType.
   * @param maxOccupancy The maximum occupancy for the room type.
   */
  RoomType(int maxOccupancy) {
    this.maxOccupancy = maxOccupancy;
  }

  /**
   * Get the maximum occupancy of the room type.
   * @return The maximum number of guests allowed.
   */
  public int getMaxOccupancy() {
    return maxOccupancy;
  }
}