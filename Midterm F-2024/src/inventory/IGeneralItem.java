package inventory;

/**
 * Interface for Items
 * Notes: BasicItem constructor takes form: BasicItem(String name, int quantity)
 * Notes: ProduceItem constructor takes form: ProduceItem(String name, int quantity, String expDate)
 * Your equals() method must be able to handle anything that is-a-kind-of IGeneralItem.
 * If the two classes being compared are not the same, the rules for the BasicItem
 * apply (simply match by name alone).
 */
public interface IGeneralItem {
  /**
   * Set the quantity value for the item.
   * @throws IllegalArgumentException if quantity is less than zero
   * @param quantity
   */
  void setQuantity(int quantity);

  /**
   * Answer/Return the quantity of the item
   * @return int
   */
  int getQuantity();

  /**
   * Answer/Return the name of the item
   * @return String
   */
  String getName();
}
