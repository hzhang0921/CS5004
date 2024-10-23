import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import inventory.BasicItem;
import inventory.IGeneralInventory;
import inventory.IGeneralItem;
import inventory.Inventory;
import inventory.ProduceItem;

import static org.junit.jupiter.api.Assertions.*;

class IGeneralInventoryTest {

  private IGeneralInventory inventory;

  @BeforeEach
  void setUp() {
    inventory = new Inventory();
  }

  @Test
  public void testGetAllItems() {
    assertEquals( 0, inventory.getAllItems().size(),"Wrong size. Expected 0");
    inventory.addItem(new BasicItem("Soup", 2));
    assertEquals(1, inventory.getAllItems().size());

    inventory.addItem(new BasicItem("Soup", 3)); // should not add duplicate item!!
    assertEquals(1, inventory.getAllItems().size());

    inventory.addItem(new BasicItem("Syrup", 12));
    assertEquals(2, inventory.getAllItems().size());

    inventory.addItem(new ProduceItem("Milk", 12, "12/12/2023"));
    assertEquals(3, inventory.getAllItems().size());

    List<IGeneralItem> list = new ArrayList<>();
    list.add(new BasicItem("Soup", 2));
    list.add(new BasicItem("Syrup", 12));
    list.add(new ProduceItem("Milk", 12, "12/12/2023"));

    // student may be using variety of underlying implementations, some sorted/hashed, others
    // unsorted. Verify list membership regardless of ordering, without altering original
    // collection
    assertTrue(list.size() == inventory.getAllItems().size()
            && list.containsAll(inventory.getAllItems())
            && inventory.getAllItems().containsAll(list), "lists NOT EQUAL");
  }

  @Test
  public void testRemoveAndAddItems() {
    inventory.addItem(new BasicItem("Soup", 2));
    inventory.addItem(new BasicItem("Syrup", 12));
    inventory.addItem(new ProduceItem("Milk", 12, "12/12/2023"));
    assertEquals(3, inventory.getAllItems().size());

    inventory.removeItem(new BasicItem("Turkey", 2)); // item not in inventory
    assertEquals(3, inventory.getAllItems().size()); // still 3 items

    inventory.removeItem(new BasicItem("Soup", 2)); // item not in inventory
    assertEquals(2, inventory.getAllItems().size()); // should be two items

    List<BasicItem> list = new ArrayList<>();
    list.add(new BasicItem("Syrup", 12));
    list.add(new ProduceItem("Milk", 12, "12/12/2023"));

    // student may be using variety of underlying implementations, some sorted/hashed, others
    // unsorted. Verify list membership regardless of ordering, without altering original
    // collection
    assertTrue(list.size() == inventory.getAllItems().size()
            && list.containsAll(inventory.getAllItems())
            && inventory.getAllItems().containsAll(list), "lists NOT EQUAL");

    inventory.addItem(new BasicItem("Soup", 2)); // add soup back in
    list.add(new BasicItem("Soup", 2));
    // student may be using variety of underlying implementations, some sorted/hashed, others
    // unsorted. Verify list membership regardless of ordering, without altering original
    // collection
    assertTrue(list.size() == inventory.getAllItems().size()
            && list.containsAll(inventory.getAllItems())
            && inventory.getAllItems().containsAll(list), "lists NOT EQUAL");

  }

  @Test
  public void testChangeQuantity() {
    inventory.addItem(new BasicItem("Soup", 2));
    inventory.addItem(new BasicItem("Syrup", 12));
    inventory.addItem(new ProduceItem("Milk", 12, "12/12/2023"));
    assertEquals(3, inventory.getAllItems().size());

    inventory.changeQuantity(new BasicItem("Soup", 2), 15);
    assertEquals(15,  inventory.getAllItems().get(0).getQuantity());

    // Non-existent item: should NOT cause an error
    try {
      inventory.changeQuantity(new BasicItem("Pepper", 2), 15);
    }
    catch (Exception e){
      fail("Exception thrown when trying to change non-existent item quantity. Should ignore");
    }

    try {
      inventory.changeQuantity(new BasicItem("Soup", 2), -1);
    }
    catch (Exception e){
      fail("Exception thrown when trying to change item quantity to negative value. Should ignore");
    }
  }


  @Test
  public void testGetAllItemsUnmodified() {
    Assertions.assertThrows(UnsupportedOperationException.class, () -> {
      assertEquals(0, inventory.getAllItems().size(), "Wrong size. Expected 0");
      inventory.addItem(new BasicItem("Soup", 2));
      assertEquals(1, inventory.getAllItems().size());

      inventory.addItem(new BasicItem("Soup", 3)); // should not add duplicate item!!
      assertEquals(1, inventory.getAllItems().size());

      inventory.addItem(new BasicItem("Syrup", 12));
      assertEquals(2, inventory.getAllItems().size());

      inventory.addItem(new ProduceItem("Milk", 12, "12/12/2023"));
      assertEquals(3, inventory.getAllItems().size());

      List<IGeneralItem> list = inventory.getAllItems();
      list.add(new BasicItem("Pie", 1)); // should raise an error
    });
  }
}