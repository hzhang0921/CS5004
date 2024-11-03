package taxes;

import java.util.Objects;

public class Address {
  private String street;
  private String city;
  private String state;
  private String zipCode;
  private TaxLien taxes;

  public Address(String street, String city, String state, String zipCode, TaxLien taxes) {
    this.state = state;
    this.street = street;
    this.city = city;
    this.zipCode = zipCode;
    if (taxes != null) {
      this.taxes = new TaxLien(taxes);
    } else {
      this.taxes = null;
    }
  }

  public Address(Address that) {
    if (that == null) {
      throw new IllegalArgumentException("Cannot copy from a null address");
    }
    this.street = that.street;
    this.city = that.city;
    this.zipCode = that.zipCode;
    this.state = that.state;
    if (that.taxes != null) {
      this.taxes = new TaxLien(that.taxes);
    } else {
      this.taxes = null;
    }
  }

  public TaxLien getTaxes() {
    return this.taxes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(street, address.street)
            && Objects.equals(city, address.city)
            && Objects.equals(state, address.state)
            && Objects.equals(zipCode, address.zipCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(street, city, state, zipCode);
  }

  public String toString() {
    return this.street + "\n" + this.city + " " + this.state + " " + this.zipCode;
  }
}

