package inventory;

import java.util.Objects;

public class ProduceItem extends BasicItem {
  private String expirationDate;

  public ProduceItem(String itemName, int quantity, String expirationDate) {
    super(itemName, quantity);
    if (expirationDate == null || expirationDate.isEmpty()) {
      throw new IllegalArgumentException("expiration Date cannot be null or an empty string.");
    }
    this.expirationDate = expirationDate;
  }

  public ProduceItem(ProduceItem other) {
    this(other.getName(), other.getQuantity(), other.getExpirationDate());
  }

  public String getExpirationDate() {
    return this.expirationDate;
  }

  @Override
  public String toString() {
    return this.getName() + ":" + this.getQuantity() + ":" + this.getExpirationDate();
  }

  /**
   * 1. Checks if the objects are the same instance
   * 2. Checks if the object is null
   * 3. Checks if the objects are the same class
   *    if so, typecasts the object into a ProduceItem and compares name and expiration date
   * 4. If not the same class, uses BasicItem's equals to evaluate only name.
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
    if (getClass() == other.getClass()) {
      ProduceItem other_copy = (ProduceItem) other;
      return this.getName().equalsIgnoreCase(other_copy.getName())
          && this.getExpirationDate().equalsIgnoreCase(other_copy.getExpirationDate());
    }
    return super.equals(other);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.getExpirationDate().toLowerCase());
  }
}
