package inventory;

import java.util.List;

public interface IGeneralInventory {
  /**
   * Answers/Returns the list of items held by the inventory as an UNMODIFIABLE list
   * See the Java collections documentation online if you need clarification on this
   * @return Unmodifiable List - IGeneralItems
   */
  List<IGeneralItem> getAllItems();

  /**
   * Removes an item from the inventory list if (and only if) the list currently contains the item
   * @param item : IGeneralItem
   */
  void removeItem(IGeneralItem item);

  /**
   * Adds an item to the inventory list if (and only if) the list does NOT currently contain the item.
   * No duplicate adds are allowed. If the inventory already contains the item, no modifications are
   * processed and the request is ignored.
   * @param item
   */
  void addItem(IGeneralItem item);

  /**
   * This method changes the quantity of an item in the inventory if (and only if) the inventory
   * currently contains the item specified. If the item parameter passed to this method does not
   * exist in the inventory, this request is ignored.
   * This method does NOT throw an exception. Instead, it clamps the quantity values such that the
   * quantity will never be less than zero.
   * NB: Zero quantity items already in the inventory are NOT removed from the inventory. The entries
   * remain in our inventory system, but the quantity for the item is allowed to be set to zero.
   * @param item : IGeneralItem - item to update quantity, if it exists in inventory
   * @param newQuantity : int - new quantity value (non-negative, clamped)
   */
  void changeQuantity(IGeneralItem item, int newQuantity);

}
