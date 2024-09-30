package hw2;

/**
 * Class representing a Room in a hotel.
 */
public class Room {
  private RoomType roomType;
  private double price;
  private int numberOfGuests;

  /**
   * Constructor for Room.
   * @param roomType The type of room.
   * @param price    The price of the room per night.
   * @throws IllegalArgumentException if the price is negative.
   */
  public Room(RoomType roomType, double price) {
    if (price < 0) {
      throw new IllegalArgumentException("Price cannot be negative.");
    }
    this.roomType = roomType;
    this.price = price;
    this.numberOfGuests = 0;
  }

  /**
   * Checks if the room is available (If there are 0 guests currently in the room).
   * @return true if available, false otherwise.
   */
  public boolean isAvailable() {
    return numberOfGuests == 0;
  }

  /**
   * Books the room with the given number of guests, if valid.
   *
   * @param guests The number of guests to assign.
   * @throws IllegalArgumentException if the booking is invalid
   * (i.e., room not available or invalid number of guests).
   * I originally thought I was supposed to throw an illegalArgumentException below.
   */
//  public void bookRoom(int guests) {
//    if (!isAvailable()) {
//      throw new IllegalArgumentException("Room is currently occupied.");
//    }
//    if (guests <= 0 || guests > roomType.getMaxOccupancy()) {
//      throw new IllegalArgumentException("Invalid number of guests.");
//    }
//    this.numberOfGuests = guests;
//  }

  public void bookRoom(int guests) {
    if (!isAvailable()) {
      return;
    }
    if (guests <= 0 || guests > roomType.getMaxOccupancy()) {
      return;
    }
    this.numberOfGuests = guests;
  }

  /**
   * Gets the number of guests currently assigned to the room.
   *
   * @return The number of guests in the room.
   */
  public int getNumberOfGuests() {
    return numberOfGuests;
  }

  /**
   * Gets the price of the room.
   *
   * @return The price per night for the room.
   */
  public double getPrice() {
    return price;
  }
}