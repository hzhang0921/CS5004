package student;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * This class contains JUnit test cases to test the behavior of IEmployee implementations
 * (HourlyEmployee and SalariedEmployee).
 */
public class IEmployeeTest {

  // Test objects for HourlyEmployee and SalariedEmployee
  HourlyEmployee snoopyHours = new HourlyEmployee("Snoopy", "111-CHLY-BRWN", 17.50, 20);
  SalariedEmployee lucy = new SalariedEmployee("Lucy", "222-22-2222", 70000.00);
  IEmployee woodStock = new SalariedEmployee("Woodstock", "33-CHIRP", 180000.50);

  /**
   * Tests that the constructor throws an IllegalArgumentException when necessary.
   */
  @Test
  public void testGetErrorWhenCreatingEmployee() {
    assertThrows(IllegalArgumentException.class, () -> {
      IEmployee e = new HourlyEmployee(null, null, 15.51, 30);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      IEmployee e = new HourlyEmployee("Chris", null, 15.51, 30);
    });
    assertThrows(IllegalArgumentException.class, () -> {
      IEmployee e = new HourlyEmployee("Part-timer", "PT-TIME", -1, 30);
    });
  }

  /**
   * Tests that valid HourlyEmployee and SalariedEmployee objects are created without exceptions.
   */
  @Test
  public void testGetHappyDayEmployee() {
    assertDoesNotThrow(() -> new HourlyEmployee("John", "123", 25, 40));
    assertDoesNotThrow(() -> new SalariedEmployee("Jane", "456", 90000));
  }

  /**
   * Tests the getPayForThisPeriod method for both hourly and salaried employees.
   * Checks the correct pay calculations for normal, special, and overtime hours.
   */
  @Test
  public void testGetPayForThisPeriod() {
    // Test pay for normal hours
    assertEquals(350, snoopyHours.getPayForThisPeriod());

    // Test pay for overtime hours (45 hours with overtime)
    snoopyHours.setSpecialHours(45);
    assertEquals(831.25, snoopyHours.getPayForThisPeriod());

    // Test pay for salaried employee (Lucy)
    assertEquals(5833.33, lucy.getPayForThisPeriod(), 0.01);
  }

  /**
   * Tests the getBaseSalary method for both HourlyEmployee and SalariedEmployee.
   */
  @Test
  public void testGetBaseSalary() {
    assertEquals(17.50, snoopyHours.getBaseSalary());
    assertEquals(70000.00, lucy.getBaseSalary());
  }

  /**
   * Tests the getID method for both HourlyEmployee and SalariedEmployee.
   */
  @Test
  public void testGetID() {
    assertEquals("111-CHLY-BRWN", snoopyHours.getID());
    assertEquals("222-22-2222", lucy.getID());
  }

  /**
   * Tests the getName method for both HourlyEmployee and SalariedEmployee.
   */
  @Test
  public void testGetName() {
    assertEquals("Snoopy", snoopyHours.getName());
    assertEquals("Lucy", lucy.getName());
  }

  /**
   * Tests the giveRaiseByPercent method for HourlyEmployee.
   * Verifies raises within and outside the allowable percentage range.
   */
  @Test
  public void testRaiseByPercent() {
    // Test a valid raise (5%)
    snoopyHours.giveRaiseByPercent(5);
    assertEquals(18.375, snoopyHours.getBaseSalary(), 0.01);

    // Test max raise (10%)
    snoopyHours.giveRaiseByPercent(10);
    assertEquals(20.2125, snoopyHours.getBaseSalary(), 0.01);

    // Test exceeding raise limit (>10%)
    assertThrows(IllegalArgumentException.class, () -> {
      snoopyHours.giveRaiseByPercent(15);
    });

    // Test edge case raise of 0%
    snoopyHours.giveRaiseByPercent(0);
    assertEquals(20.2125, snoopyHours.getBaseSalary(), 0.01);  // Ensure no change
  }

  /**
   * Tests that the pay for 0 hours worked is correctly calculated as 0 for an HourlyEmployee.
   * Also ensures that a SalariedEmployee still receives their monthly pay for 0 hours worked.
   */
  @Test
  public void testZeroHoursPay() {
    // Test for 0 hours worked for HourlyEmployee
    snoopyHours.setSpecialHours(0);
    snoopyHours = new HourlyEmployee("Snoopy", "111-CHLY-BRWN", 17.50, 0);
    assertEquals(0, snoopyHours.getPayForThisPeriod());

    // Test for 0 hours worked for SalariedEmployee (still gets their monthly salary)
    assertEquals(5833.33, lucy.getPayForThisPeriod(), 0.01);
  }

  /**
   * Tests the pay calculation for an HourlyEmployee working the maximum allowed hours (80 hours).
   * Verifies overtime pay is correctly calculated.
   */
  @Test
  public void testMaxHoursPay() {
    // Test for 80 hours worked (max limit)
    snoopyHours.setSpecialHours(80);
    double expectedPay = (17.50 * 40) + (17.50 * 1.5 * 40);  // Regular + overtime
    assertEquals(expectedPay, snoopyHours.getPayForThisPeriod(), 0.01);
  }

  /**
   * Tests that the special hours reset to 0 after pay is calculated for an HourlyEmployee.
   */
  @Test
  public void testResetSpecialHoursAfterPay() {
    // Set special hours
    snoopyHours.setSpecialHours(50);
    snoopyHours.getPayForThisPeriod();  // Trigger pay calculation

    // Special hours should reset to 0 after calculation
    assertEquals(350, snoopyHours.getPayForThisPeriod());  // Reverts back to normal hours of 20
  }

  /**
   * Tests that a SalariedEmployee's salary is capped at the maximum allowed salary after raise.
   */
  @Test
  public void testMaxSalaryForSalariedEmployee() {
    SalariedEmployee highEarner = new SalariedEmployee("Rich", "999", 999999);
    highEarner.giveRaiseByPercent(5);  // Should not exceed max salary of 1000000
    assertEquals(1000000, highEarner.getBaseSalary());  // Capped at max salary
  }

  /**
   * Tests the copy constructor for both SalariedEmployee and HourlyEmployee.
   */
  @Test
  public void testCopyConstructor() {
    // Test for SalariedEmployee
    SalariedEmployee originalSalaried = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    SalariedEmployee copySalaried = new SalariedEmployee(originalSalaried);
    assertEquals(originalSalaried.getName(), copySalaried.getName(), "Name should be the same");
    assertEquals(originalSalaried.getID(), copySalaried.getID(), "ID should be the same");
    assertEquals(originalSalaried.getBaseSalary(), copySalaried.getBaseSalary(), 0.001,
        "Yearly salary should be the same");
    assertNotSame(originalSalaried, copySalaried, "The copied object should not be the same instance as the original");

    // Test for HourlyEmployee
    HourlyEmployee originalHourly = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    HourlyEmployee copyHourly = new HourlyEmployee(originalHourly);
    assertEquals(originalHourly.getName(), copyHourly.getName(), "Name should be the same");
    assertEquals(originalHourly.getID(), copyHourly.getID(), "ID should be the same");
    assertEquals(originalHourly.getBaseSalary(), copyHourly.getBaseSalary(), 0.001,
        "Hourly salary should be the same");
    assertNotSame(originalHourly, copyHourly, "The copied object should not be the same instance as the original");
  }

  /**
   * Tests the toString method for SalariedEmployee.
   */
  @Test
  public void testToStringSalariedEmployee() {
    SalariedEmployee salariedEmployee = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    String expected = "Name: Jane Doe\nID: 54321\nBase Salary: $60000.00";
    assertEquals(expected, salariedEmployee.toString(), "toString() method output is incorrect for SalariedEmployee");
  }

  /**
   * Tests the toString method for HourlyEmployee.
   */
  @Test
  public void testToStringHourlyEmployee() {
    HourlyEmployee hourlyEmployee = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    String expected = "Name: John Doe\nID: 12345\nBase Salary: $20.00";
    assertEquals(expected, hourlyEmployee.toString(), "toString() method output is incorrect for HourlyEmployee");
  }

  /**
   * Tests the equals method for comparing the same object.
   * Verifies that an object is equal to itself.
   */
  @Test
  public void testEqualsSameObject() {
    // Test for SalariedEmployee
    SalariedEmployee salariedEmployee = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    assertEquals(salariedEmployee, salariedEmployee, "A SalariedEmployee object should be equal to itself");

    // Test for HourlyEmployee
    HourlyEmployee hourlyEmployee = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    assertEquals(hourlyEmployee, hourlyEmployee, "An HourlyEmployee object should be equal to itself");
  }

  /**
   * Tests the equals method for comparing two identical objects.
   * Verifies that two objects with the same fields are equal.
   */
  @Test
  public void testEqualsIdenticalObject() {
    // Test for SalariedEmployee
    SalariedEmployee salariedEmployee1 = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    SalariedEmployee salariedEmployee2 = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    assertEquals(salariedEmployee1, salariedEmployee2, "Two identical SalariedEmployee objects should be equal");

    // Test for HourlyEmployee
    HourlyEmployee hourlyEmployee1 = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    HourlyEmployee hourlyEmployee2 = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    assertEquals(hourlyEmployee1, hourlyEmployee2, "Two identical HourlyEmployee objects should be equal");
  }

  /**
   * Tests the equals method for comparing an object with null.
   * Verifies that no object is equal to null.
   */
  @Test
  public void testEqualsNullObject() {
    // Test for SalariedEmployee
    SalariedEmployee salariedEmployee = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    assertNotEquals(salariedEmployee, null, "A SalariedEmployee object should not be equal to null");

    // Test for HourlyEmployee
    HourlyEmployee hourlyEmployee = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    assertNotEquals(hourlyEmployee, null, "An HourlyEmployee object should not be equal to null");
  }

  /**
   * Tests the equals method for comparing objects of different classes.
   * Verifies that objects of different types are not equal.
   */
  @Test
  public void testEqualsDifferentClass() {
    // Test for SalariedEmployee
    SalariedEmployee salariedEmployee = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    assertNotEquals(salariedEmployee, "Some String", "A SalariedEmployee object should not be equal to a different class");

    // Test for HourlyEmployee
    HourlyEmployee hourlyEmployee = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    assertNotEquals(hourlyEmployee, "Some String", "An HourlyEmployee object should not be equal to a different class");
  }

  /**
   * Tests the equals method for comparing objects with different field values.
   * Verifies that objects with different values are not equal.
   */
  @Test
  public void testEqualsDifferentValues() {
    // Test for SalariedEmployee
    SalariedEmployee salariedEmployee1 = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    SalariedEmployee salariedEmployee2 = new SalariedEmployee("John Doe", "98765", 70000.00);
    assertNotEquals(salariedEmployee1, salariedEmployee2, "SalariedEmployee objects with different values should not be equal");

    // Test for HourlyEmployee
    HourlyEmployee hourlyEmployee1 = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    HourlyEmployee hourlyEmployee2 = new HourlyEmployee("Jane Doe", "98765", 25.00, 40);
    assertNotEquals(hourlyEmployee1, hourlyEmployee2, "HourlyEmployee objects with different values should not be equal");
  }

  /**
   * Tests the hashCode method for two objects with the same field values.
   * Verifies that objects with the same values have the same hash code.
   */
  @Test
  public void testHashCodeSameValues() {
    // Test for SalariedEmployee
    SalariedEmployee salariedEmployee1 = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    SalariedEmployee salariedEmployee2 = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    assertEquals(salariedEmployee1.hashCode(), salariedEmployee2.hashCode(),
        "Equal SalariedEmployee objects should have the same hash code");

    // Test for HourlyEmployee
    HourlyEmployee hourlyEmployee1 = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    HourlyEmployee hourlyEmployee2 = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    assertEquals(hourlyEmployee1.hashCode(), hourlyEmployee2.hashCode(),
        "Equal HourlyEmployee objects should have the same hash code");
  }

  /**
   * Tests the hashCode method for two objects with different field values.
   * Verifies that objects with different values have different hash codes.
   */
  @Test
  public void testHashCodeDifferentValues() {
    // Test for SalariedEmployee
    SalariedEmployee salariedEmployee1 = new SalariedEmployee("Jane Doe", "54321", 60000.00);
    SalariedEmployee salariedEmployee2 = new SalariedEmployee("John Doe", "98765", 70000.00);
    assertNotEquals(salariedEmployee1.hashCode(), salariedEmployee2.hashCode(),
        "SalariedEmployee objects with different values should have different hash codes");

    // Test for HourlyEmployee
    HourlyEmployee hourlyEmployee1 = new HourlyEmployee("John Doe", "12345", 20.00, 40);
    HourlyEmployee hourlyEmployee2 = new HourlyEmployee("Jane Doe", "98765", 25.00, 40);
    assertNotEquals(hourlyEmployee1.hashCode(), hourlyEmployee2.hashCode(),
        "HourlyEmployee objects with different values should have different hash codes");
  }
}