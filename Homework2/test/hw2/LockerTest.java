package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the locker class
 */
public class LockerTest {

  @Test
  public void testLockerCreation() {
    Locker locker = new Locker(10, 10, 10);
    assertNotNull(locker);
    assertNull(locker.getMailItem()); // Initially empty
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLockerCreation() {
    new Locker(-1, 10, 10); // Negative dimension should throw exception
  }

  @Test
  public void testAddMailSuccess() {
    Locker locker = new Locker(10, 10, 10);
    MailItem mailItem = new MailItem(5, 5, 5, new Recipient("John", "Doe", "john.doe@example.com"));

    locker.addMail(mailItem);
    assertEquals(mailItem, locker.getMailItem()); // Mail item should be added successfully
  }

  @Test
  public void testAddMailTooBig() {
    Locker locker = new Locker(5, 5, 5);
    MailItem mailItem = new MailItem(10, 5, 5, new Recipient("John", "Doe", "john.doe@example.com"));

    locker.addMail(mailItem);
    assertNull(locker.getMailItem()); // Mail item doesn't fit, locker remains empty
  }

  @Test
  public void testAddMailOccupiedLocker() {
    Locker locker = new Locker(10, 10, 10);
    MailItem mailItem1 = new MailItem(5, 5, 5, new Recipient("John", "Doe", "john.doe@example.com"));
    MailItem mailItem2 = new MailItem(3, 3, 3, new Recipient("Jane", "Doe", "jane.doe@example.com"));

    locker.addMail(mailItem1); // Add first mail item
    locker.addMail(mailItem2); // Try to add second one while locker is occupied

    assertEquals(mailItem1, locker.getMailItem()); // The first mail item remains
  }

  @Test
  public void testPickupMailSuccess() {
    Locker locker = new Locker(10, 10, 10);
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");
    MailItem mailItem = new MailItem(5, 5, 5, recipient);

    locker.addMail(mailItem);
    assertEquals(mailItem, locker.pickupMail(recipient)); // Successful pickup
    assertNull(locker.getMailItem()); // Locker should be empty after pickup
  }

  @Test
  public void testPickupMailWrongRecipient() {
    Locker locker = new Locker(10, 10, 10);
    Recipient recipient1 = new Recipient("John", "Doe", "john.doe@example.com");
    Recipient recipient2 = new Recipient("Jane", "Doe", "jane.doe@example.com");
    MailItem mailItem = new MailItem(5, 5, 5, recipient1);

    locker.addMail(mailItem);
    assertNull(locker.pickupMail(recipient2)); // Wrong recipient, pickup fails
    assertEquals(mailItem, locker.getMailItem()); // Mail item is still in locker
  }

  @Test
  public void testPickupMailEmptyLocker() {
    Locker locker = new Locker(10, 10, 10);
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");

    assertNull(locker.pickupMail(recipient)); // Locker is empty, pickup returns null
  }
}