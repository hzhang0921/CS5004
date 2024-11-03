package inventory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Inventory implements IGeneralInventory{
  private List<IGeneralItem> items;

  public Inventory() {
    this.items = new ArrayList<>();
  }

  @Override
  public List<IGeneralItem> getAllItems() {
    return Collections.unmodifiableList(items);
  }

  @Override
  public void addItem(IGeneralItem Item) {
    if (items.contains(Item)) {
      return;
    }
    items.add(Item);
  }

  @Override
  public void removeItem(IGeneralItem item) {
    if (items.contains(item)) {
      items.remove(item);
    }
  }

  @Override
  public void changeQuantity(IGeneralItem item, int newQuantity) {
    if (newQuantity < 0) {
      return;
    }
    for (int i = 0; i < items.size(); i++) {
      IGeneralItem currentItem = items.get(i);
      if (currentItem.equals(item)) {
        currentItem.setQuantity(newQuantity);
        break;
      }
    }
  }
}
