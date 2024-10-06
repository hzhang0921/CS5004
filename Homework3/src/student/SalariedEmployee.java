package student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents a salaried employee.
 */
public class SalariedEmployee implements IEmployee {

  /** The maximum yearly salary allowed for a salaried employee. */
  public static final double MAX_YEARLY_SALARY = 1000000.0;

  /** The number of pay periods per year for salaried employees (12 months). */
  public static final int PAY_PERIODS = 12;

  private String name;
  private String id;
  private double yearlySalary;

  /**
   * Constructor for creating a SalariedEmployee.
   *
   * @param employeeName the name of the employee.
   * @param employeeId the ID of the employee.
   * @param baseYearlySalary the yearly salary of the employee.
   */
  public SalariedEmployee(String employeeName, String employeeId, double baseYearlySalary) {
    if (employeeName == null || employeeId == null
        || employeeName.isEmpty() || employeeId.isEmpty()) {
      throw new IllegalArgumentException("Name and ID cannot be null or empty.");
    }
    if (baseYearlySalary < 0
        || baseYearlySalary > MAX_YEARLY_SALARY) {
      throw new IllegalArgumentException("Yearly salary must be between 0 and "
          + MAX_YEARLY_SALARY);
    }
    this.name = employeeName;
    this.id = employeeId;
    this.yearlySalary = baseYearlySalary;
  }

  /**
   * Copy constructor for salaried employee.
   * @param other salaried employee that is to be copied.
   */
  public SalariedEmployee(SalariedEmployee other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot copy from a null object.");
    }
    this.name = other.name;
    this.id = other.id;
    this.yearlySalary = other.yearlySalary;
  }

  /**
   * Gets the pay for the current period (monthly).
   *
   * @return the employee's pay for this period.
   */
  @Override
  public double getPayForThisPeriod() {
    BigDecimal monthlyPay = new BigDecimal(yearlySalary / PAY_PERIODS);
    monthlyPay = monthlyPay.setScale(2, RoundingMode.HALF_UP);
    return monthlyPay.doubleValue();
  }

  /**
   * Gets the base salary for the employee.
   *
   * @return the yearly salary.
   */
  @Override
  public double getBaseSalary() {
    return yearlySalary;
  }

  /**
   * Gives the employee a raise by a certain percentage.
   *
   * @param raisePercent the raise percentage (must be between 0 and 10).
   */
  @Override
  public void giveRaiseByPercent(double raisePercent) {
    if (raisePercent < 0 || raisePercent > 10) {
      throw new IllegalArgumentException("Raise percent must be between 0 and 10.");
    }
    yearlySalary *= (1 + raisePercent / 100);
    if (yearlySalary > MAX_YEARLY_SALARY) {
      yearlySalary = MAX_YEARLY_SALARY; // Enforce maximum salary
    }
  }

  /**
   * Gets the ID of the employee.
   *
   * @return the employee ID.
   */
  @Override
  public String getID() {
    return id;
  }

  /**
   * Gets the name of the employee.
   *
   * @return the employee name.
   */
  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Name: " + name + "\nID: " + id + "\nBase Salary: $" + yearlySalary;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;  // If both references are the same
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;  // If the other object is null or not of the same class
    }
    SalariedEmployee other = (SalariedEmployee) obj;
    // Compare relevant fields to determine equality
    return Double.compare(yearlySalary, other.yearlySalary) == 0
        && name.equals(other.name)
        && id.equals(other.id);
  }

  @Override
  public int hashCode() {
    // Generate a hash code based on the fields used in equals()
    return Objects.hash(name, id, yearlySalary);
  }
}