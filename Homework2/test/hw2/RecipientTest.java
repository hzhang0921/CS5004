package hw2;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for the Recipient class.
 */
public class RecipientTest {

  /**
   * Tests the creation of a recipient to see if it's valid
   */
  @Test
  public void testRecipientCreation() {
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");
    assertNotNull(recipient);
    assertEquals("John Doe Email:john.doe@example.com", recipient.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRecipientCreationWithNullFirstName() {
    new Recipient(null, "Doe", "john.doe@example.com");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRecipientCreationWithEmptyLastName() {
    new Recipient("John", "", "john.doe@example.com");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRecipientCreationWithEmptyEmail() {
    new Recipient("John", "Doe", "");
  }

  @Test
  public void testSetEmail() {
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");
    recipient.setEmail("new.email@example.com");
    assertEquals("John Doe Email:new.email@example.com", recipient.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetEmailWithEmptyEmail() {
    Recipient recipient = new Recipient("John", "Doe", "john.doe@example.com");
    recipient.setEmail("");
  }

  @Test
  public void testRecipientEquality() {
    Recipient recipient1 = new Recipient("John", "Doe", "john.doe@example.com");
    Recipient recipient2 = new Recipient("John", "Doe", "john.doe@example.com");
    Recipient recipient3 = new Recipient("Jane", "Doe", "jane.doe@example.com");

    assertTrue(recipient1.equals(recipient2));
    assertFalse(recipient1.equals(recipient3));
  }
}