package composition;

public class Address {
  private String street;
  private String city;
  private String state;
  private String zipCode;
  private TaxLien taxes;

  public Address(String street, String city, String state, String zipCode, TaxLien taxes) {
    this.street = street;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.taxes = taxes;
  }

  /**
   * Copy Constructor
   * @param other
   */
  public Address(Address other) {
    this.street = other.street;
    this.city = other.city;
    this.state = other.state;
    this.zipCode = other.zipCode;
    // deep copy, if you did this.taxes = other.taxes it would point to the same memory. Because you are inheriting this, it's important to deep copy.
    this.taxes = new TaxLien(other.taxes);
  }

  public TaxLien getTaxes() {
    return taxes;
  }

  @Override
  public String toString() {
    return this.street + ", " + this.city + " " + this.state + " " + this.zipCode;
  }
}
