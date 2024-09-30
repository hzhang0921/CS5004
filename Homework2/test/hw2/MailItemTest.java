package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the MailItem class.
 */
public class MailItemTest {

  /**
   * Tests to confirm the creation of mail item and the correct corresponding variables
   */
  @Test
  public void testMailItemCreation() {
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");
    MailItem mailItem = new MailItem(10, 5, 3, recipient);
    assertNotNull(mailItem);
    assertEquals(10, mailItem.getWidth());
    assertEquals(5, mailItem.getHeight());
    assertEquals(3, mailItem.getDepth());
    assertEquals(recipient, mailItem.getRecipient());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMailItemWithNegativeWidth() {
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");
    new MailItem(-1, 5, 3, recipient);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMailItemWithZeroHeight() {
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");
    new MailItem(10, 0, 3, recipient);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMailItemWithNullRecipient() {
    new MailItem(10, 5, 3, null);
  }
}
