import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import inventory.IGeneralItem;
import inventory.ProduceItem;
import inventory.BasicItem;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
  private IGeneralItem cereal;
  private IGeneralItem coffee;
  private IGeneralItem seltzer;

  private ProduceItem milk;
  private ProduceItem eggs;


  @BeforeEach
  public void setUp() throws Exception {
    cereal = new BasicItem("Frosted Flakes", 1);
    coffee = new BasicItem("Peets", 10);
    seltzer = new BasicItem("Poland Springs", 22);
    milk = new ProduceItem("Milk", 2, "11/02/2024");
    eggs = new ProduceItem("Eggs", 18, "12/12/2024");
  }

  @Test
  public void testSetGetQuantity() {
    assertEquals(1, cereal.getQuantity());
    IGeneralItem item = new BasicItem("Caviar", 0);
    assertEquals(0, item.getQuantity());
    item.setQuantity(10);
    assertEquals(10, item.getQuantity());
    item.setQuantity(1000);
    assertEquals(1000, item.getQuantity());
  }

  @Test
  public void testGetName() {
    assertEquals("Frosted Flakes", cereal.getName());
    IGeneralItem item = new BasicItem("Caviar", 0);
    assertEquals("Caviar", item.getName());
  }

  @Test
  public void testEquals() {
    IGeneralItem item = new BasicItem("Peets", 10);
    assertEquals(item, coffee);

    item = new ProduceItem("Milk", 2, "11/02/2024");
    assertEquals(item, milk);
    assertEquals(eggs, eggs);

    assertNotEquals(milk, eggs);

    item = new BasicItem("Milk", 2); // should equal produce item of same name
    assertEquals(item, milk); // same hierarchy, compare names
    assertEquals(milk, item); // same hierarchy, compare names

    item = new ProduceItem("Milk", 2, "11/02/2144");
    assertNotEquals(item, milk); // different expiration date - not equal to each other
    assertNotEquals(milk, item); // different expiration date - not equal to each other
  }

  @Test
  public void testCopy() {
    BasicItem item = new BasicItem("Peets", 10);
    assertEquals(item, coffee);

    IGeneralItem otherItem = new BasicItem(item);
    assertEquals(item, otherItem);

    item = new ProduceItem(milk);
    assertEquals(item, milk);
  }

  @Test
  public void testToString() {
    assertEquals("Frosted Flakes:1", cereal.toString());
    IGeneralItem item = new BasicItem("Caviar", 0);
    assertEquals("Caviar:0", item.toString());
    IGeneralItem item2 = new ProduceItem("Caviar", 2, "xxxxxx");
    assertEquals("Caviar:2:xxxxxx", item2.toString());
  }

  @Test
  public void testBadQuantities() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new BasicItem("Evaporated Milk", -1);
    });

  }

  @Test
  public void testSetBadQuantity() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      ProduceItem p = new ProduceItem("Virtual Beef", 1, "12/12/2012");
      p.setQuantity(-10);
    });

  }
  @Test
  public void testSetBadName() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new ProduceItem("", 1, "12/12/2012");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new BasicItem("", 1);
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new BasicItem(null, 1);
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new ProduceItem(null, 1, "12/12/2012");
    });
  }
  @Test
  public void testBadExpirationDate() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new ProduceItem("Virtual Beef", 1, "");
    });
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      new ProduceItem("Virtual Beef", 1, null);
    });
  }
}