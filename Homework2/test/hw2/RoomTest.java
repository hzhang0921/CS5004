package hw2;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Room class.
 */
public class RoomTest {

  private Room singleRoom;
  private Room doubleRoom;
  private Room suiteRoom;

  @Before
  public void setUp() {
    singleRoom = new Room(RoomType.SINGLE, 100.0);
    doubleRoom = new Room(RoomType.DOUBLE, 150.0);
    suiteRoom = new Room(RoomType.FAMILY, 200.0);
  }

  /**
   * Tests to ensure the room is created correctly.
   */
  @Test
  public void testRoomCreation() {
    assertEquals(100.0, singleRoom.getPrice(), 0.01);
    assertTrue(singleRoom.isAvailable());
    assertEquals(0, singleRoom.getNumberOfGuests());
  }

  /**
   * Test that a room cannot be booked if there are too many guests
   */
  @Test
  public void testBookingInvalidGuests() {
    singleRoom.bookRoom(2);  // Exceeds max occupancy for SINGLE room
    assertTrue(singleRoom.isAvailable());  // Booking should fail, room remains available
    assertEquals(0, singleRoom.getNumberOfGuests());
  }

  /**
   * Tests booking a room with a valid number of guests.
   */
  @Test
  public void testValidBooking() {
    doubleRoom.bookRoom(2);  // Valid booking for DOUBLE room
    assertFalse(doubleRoom.isAvailable());
    assertEquals(2, doubleRoom.getNumberOfGuests());
  }

  /**
   * Test thats booking with zero or negative guests doesn't modify the room's status.
   */
  @Test
  public void testBookingZeroOrNegativeGuests() {
    singleRoom.bookRoom(0);  // Invalid booking
    assertTrue(singleRoom.isAvailable());
    assertEquals(0, singleRoom.getNumberOfGuests());

    singleRoom.bookRoom(-1);  // Invalid booking
    assertTrue(singleRoom.isAvailable());
    assertEquals(0, singleRoom.getNumberOfGuests());
  }

  /**
   * Tests that you can't book an occupied room.
   */
  @Test
  public void testBookingOccupiedRoom() {
    suiteRoom.bookRoom(4);  // Valid booking for SUITE room
    assertFalse(suiteRoom.isAvailable());  // Room is now occupied

    suiteRoom.bookRoom(3);  // Try to book again, should be ignored
    assertEquals(4, suiteRoom.getNumberOfGuests());  // Guests should remain 4
  }

  /**
   * Test that an exception is thrown when a room is created with a negative price.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNegativePrice() {
    new Room(RoomType.SINGLE, -50.0);
  }
}