package taxes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Company implements ICorporateTaxableEntity {
  private String name;
  private String taxID;
  private List<Address> addresses;
  private List<Person> employees;

  public Company(String name, String taxID, Address address) {
    this.name = name;
    this.taxID = taxID;
    this.addresses = new ArrayList<>();
    this.addresses.add(address);
    this.employees = new ArrayList<>();
  }

  public Company(Company other) {
    this.name = other.name;
    this.taxID = other.taxID;
    this.addresses = new ArrayList<>();
    for (int i = 0; i < other.addresses.size(); i++) {
      this.addresses.add(new Address(other.addresses.get(i)));
    }
    this.employees = new ArrayList<>();
    for (int i = 0; i < other.employees.size(); i++) {
      this.employees.add(new Person(other.employees.get(i)));
    }
  }

  @Override
  public List<Address> getAddresses() {
    return Collections.unmodifiableList(addresses); // Safe copy of addresses
  }

  @Override
  public void addAddress(Address anAddress) {
    if (!addresses.contains(anAddress)) {
      addresses.add(anAddress); // Only add if not a duplicate
    }
  }

  @Override
  public double getCurrentTaxLiability() {
    double totalTax = 0.0;
    for (int i = 0; i < addresses.size(); i++) {
      totalTax += addresses.get(i).getTaxes().getTaxLiability();
    }
    return totalTax; // No deduction for dependents
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
        return; // Exit once the address is updated
      }
    }
  }

  // Implementing ICorporateTaxableEntity methods

  @Override
  public List<Person> getEmployees() {
    return Collections.unmodifiableList(employees); // Safe copy of employees
  }

  @Override
  public void addEmployee(Person p) {
    if (!employees.contains(p)) {
      employees.add(p); // Only add if the person isn't already hired
    }
  }

  /**
   * Same logic as person.
   * @param obj
   * @return
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Company company = (Company) obj;
    return name.equalsIgnoreCase(company.name) &&
        taxID.equalsIgnoreCase(company.taxID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name.toLowerCase(), taxID.toLowerCase());
  }

  @Override
  public String toString() {
    String result = "Company: " + name + " \n";
    result += "Tax ID: " + taxID + " \n";
    result += "Addresses: \n";
    for (int i = 0; i < addresses.size(); i++) {
      result += " - " + addresses.get(i).toString() + "\n";
    }

    result += "Employees: \n";
    if (employees.isEmpty()) {
      result += " - No employees ";
    } else {
      for (int i = 0; i < employees.size(); i++) {
        result += " - " + employees.get(i).toString() + "\n";
      }
    }

    return result;
  }
}