package taxes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Person implements ITaxableEntity{
  private String name;
  private String taxID;
  private List<Address> addresses;
  private int dependents;

  public Person(String name, String taxID, Address address, int dependents) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name cannot be null or empty");
    }
    if (taxID == null || taxID.length() != 9) {
      throw new IllegalArgumentException("Tax ID must be exactly 9 characters long and cannot be null");
    }
    if (address == null) {
      throw new IllegalArgumentException("Address cannot be null");
    }
    if (dependents < 0 || dependents > 10) {
      throw new IllegalArgumentException("number of dependents has to be between 0 and 10");
    }
    this.name = name;
    this.taxID = taxID;
    this.addresses = new ArrayList<>();
    this.addresses.add(address);
    this.dependents = dependents;
  }

  public Person(Person other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot copy from a null person");
    }
    this.name = other.name;
    this.taxID = other.taxID;
    this.dependents = other.dependents;
    this.addresses = new ArrayList<>();
    if (other.addresses != null) {
      for (int i = 0; i < other.addresses.size(); i++) {
        Address address = other.addresses.get(i);
        this.addresses.add(new Address(address));
      }
    }
  }

  @Override
  public List<Address> getAddresses() {
    return Collections.unmodifiableList(addresses);
  }

  @Override
  public void addAddress(Address newAddress) {
    if (!addresses.contains(newAddress)) {
      addresses.add(newAddress);
    }
  }

  @Override
  public double getCurrentTaxLiability() {
    double owedTax = 0.0;
    for (int i = 0; i < addresses.size(); i++) {
      owedTax += addresses.get(i).getTaxes().getTaxLiability();
    }
    int divisor = Math.max(dependents, 1);
    return owedTax / divisor;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public String getTaxID() {
    return taxID;
  }

  @Override
  public void updateAddress(Address oldAddress, Address newAddress) {
    for (int i = 0; i < addresses.size(); i++) {
      if (addresses.get(i).equals(oldAddress)) {
        addresses.set(i, newAddress);
        return;
      }
    }
  }

  /**
   * 1. Checks if it's the same instance
   * 2. Checks to make sure that the two objects come from the same class.
   * 3. Typecast to Person (should work for company too since they are both Taxable Entities)
   * 4. Checks Name and TaxID.
   * @param other
   * @return
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || this.getClass() != other.getClass()) {
      return false;
    }
    Person person = (Person) other;
    return this.getName().equalsIgnoreCase(person.getName())
        && this.getTaxID().equalsIgnoreCase(person.getTaxID());
  }

  @Override
  public int hashCode() {
    return Objects.hash(name.toLowerCase(), taxID.toLowerCase());
  }

  @Override
  public String toString() {
    String result = "Person { name = '" + name + "', taxID = '" + taxID + "', dependents = " + dependents + ", addresses = [";
    for (int i = 0; i < addresses.size(); i++) {
      result += addresses.get(i).toString();
      if (i < addresses.size() - 1) {
        result += ", ";
      }
    }
    result += "]}";
    return result;
  }




}
