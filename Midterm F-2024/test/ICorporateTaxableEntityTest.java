import taxes.Person;
import taxes.Company;
import taxes.TaxLien;
import taxes.Address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CorporateTaxableEntityTest {

  private Company company;
  private Person employee1;
  private Person employee2;
  private Address address1;
  private Address address2;

  @BeforeEach
  void setUp() {
    TaxLien taxLien1 = new TaxLien("Springfield", 100.0, true);  // Half-year tax
    TaxLien taxLien2 = new TaxLien("Shelbyville", 200.0, false); // Full-year tax

    address1 = new Address("123 Main St", "Springfield", "MA", "01101", taxLien1);
    address2 = new Address("456 Oak St", "Shelbyville", "MA", "01101", taxLien2);

    employee1 = new Person("John Doe", "123456789", address1, 2);
    employee2 = new Person("Jane Smith", "987654321", address2, 3);

    company = new Company("Tech Corp", "987654321", address1);
  }

  @Test
  void testAddEmployee() {
    // Add employees
    company.addEmployee(employee1);
    company.addEmployee(employee2);

    // Check if employees were added correctly
    List<Person> employees = company.getEmployees();
    assertEquals(2, employees.size(), "Number of employees should be 2");
    assertTrue(employees.contains(employee1), "Employee 1 should be in the list");
    assertTrue(employees.contains(employee2), "Employee 2 should be in the list");
  }

  @Test
  void testAddDuplicateEmployee() {
    // Add an employee twice
    company.addEmployee(employee1);
    company.addEmployee(employee1);

    // Check that the employee was added only once
    List<Person> employees = company.getEmployees();
    assertEquals(1, employees.size(), "Duplicate employee should not be added");
  }

  @Test
  void testGetEmployeesSafeCopy() {
    // Add an employee
    company.addEmployee(employee1);

    // Get employees list and try to modify it
    List<Person> employees = company.getEmployees();
    assertThrows(UnsupportedOperationException.class, () -> employees.add(employee2),
        "getEmployees() should return an unmodifiable list");
  }

  @Test
  void testGetCurrentTaxLiability() {
    // Add multiple addresses to the company
    company.addAddress(address1);
    company.addAddress(address2);

    // Check total tax liability for the company (no deductions for employees or dependents)
    double expectedTaxLiability = address1.getTaxes().getTaxLiability() + address2.getTaxes().getTaxLiability();
    assertEquals(expectedTaxLiability, company.getCurrentTaxLiability(),
        "Tax liability should match the sum of all address tax liens");
  }

  @Test
  void testUpdateAddress() {
    // Update an address
    TaxLien newTaxLien = new TaxLien("NewCity", 150.0, false); // Full-year tax
    Address newAddress = new Address("789 Pine St", "NewCity", "MA", "01101", newTaxLien);
    company.updateAddress(address1, newAddress);

    // Ensure the address was updated
    List<Address> addresses = company.getAddresses();
    assertTrue(addresses.contains(newAddress), "New address should be in the list");
    assertFalse(addresses.contains(address1), "Old address should be replaced");
  }

  @Test
  void testSwitchTaxPaymentPlan() {
    // Switch tax payment plan for address1's tax lien (should switch from half-year to full-year)
    TaxLien taxLien1 = address1.getTaxes();
    double initialTaxLiability = taxLien1.getTaxLiability();

    taxLien1.switchPaymentPlan();
    double updatedTaxLiability = taxLien1.getTaxLiability();

    assertNotEquals(initialTaxLiability, updatedTaxLiability, "Tax liability should change after switching payment plan");
    assertEquals(100.0, updatedTaxLiability, "Tax liability should be the full amount after switching to full-year payment");
  }
}