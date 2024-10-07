package student;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
   * Tests that the constructor throws an IllegalArgumentException when neccessary.
   */
  @Test
  public void testGetErrorWhenCreatingEmployee() {
    assertThrows(IllegalArgumentException.class, () -> {
      IEmployee e = new HourlyEmployee(null, null, 15.51, 30);
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
    assertEquals(20.2125, snoopyHours.getBaseSalary(), 0.01);  // Ensure salary is updated correctly

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
    snoopyHours = new HourlyEmployee("Snoopy",
        "111-CHLY-BRWN", 17.50, 0);  // Reset snoopyHours with 0 normal hours
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
    // Test for edge case where salary is close to the max
    SalariedEmployee highEarner = new SalariedEmployee("Rich", "999", 999999);
    highEarner.giveRaiseByPercent(5);  // Should not exceed max salary of 1000000
    assertEquals(1000000, highEarner.getBaseSalary());  // Capped at max salary
  }
}
