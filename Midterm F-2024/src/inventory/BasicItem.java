package inventory;

import java.util.Objects;

public class BasicItem implements IGeneralItem{
  private String itemName;
  private int itemQuantity;


  public BasicItem(String name, int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("quantity has to be equal to or greater than 0.");
    }
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("name cannot be null or an empty string.");
    }
    this.itemName = name;
    this.itemQuantity = quantity;
  }

  public BasicItem(BasicItem other) {
    this(other.itemName, other.itemQuantity);
  }

  @Override
  public void setQuantity(int quantity) {
    if (quantity < 0) {
      throw new IllegalArgumentException("quantity has to be equal to or greater than 0.");
    }
    this.itemQuantity = quantity;
  }

  @Override
  public int getQuantity() {
    return this.itemQuantity;
  }

  /**
   * 1. Checks if they are the same instance
   * 2. Checks if the comparison object is null
   * 3. Checks if the other object is an instance of IGeneralItem
   *    If so, compares only the names.
   * 4. if all else fails, returns false.
   * @param other
   * @return
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null) {
      return false;
    }

    if (other instanceof IGeneralItem) {
      IGeneralItem other_copy = (IGeneralItem) other;
      return this.getName().equalsIgnoreCase(other_copy.getName());
    }
    return false;
  }

  @Override
  public String toString() {
    return this.itemName + ":" + this.itemQuantity;
  }

  @Override
  public int hashCode() {
      return Objects.hash(this.itemName.toLowerCase());
  }

  @Override
  public String getName() {
    return this.itemName;
  }

  public static void main(String[] args) {
    return;
  }
}
