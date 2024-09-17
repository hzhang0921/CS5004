

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the Person class.
 */
public class PersonTest {

  private Person john;

  @org.junit.Before
  public void setUp() {

    john = new Person("John", "Doe", 1945);
  }

  @org.junit.Test
  public void testFirst() {
    assertEquals("John", john.getFirstName());

  }

  @org.junit.Test
  public void testSecond() {
    assertEquals("Doe", john.getLastName());
  }

  @org.junit.Test
  public void testYearOfBirth() {
    assertEquals(1945, john.getYearOfBirth());
  }

}
