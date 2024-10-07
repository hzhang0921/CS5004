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

  /** The maximum allowable raise percentage. */
  public static final double MAX_RAISE_PERCENT = 10.0;

  /** Percentage divisor for calculating raises. */
  public static final double PERCENT_DIVISOR = 100.0;

  /** The name of the employee. */
  private String name;

  /** The ID of the employee. */
  private String id;

  /** The yearly salary of the employee. */
  private double yearlySalary;

  /**
   * Constructor for creating a SalariedEmployee.
   *
   * @param name the name of the employee.
   * @param id the ID of the employee.
   * @param yearlySalary the yearly salary of the employee.
   */
  public SalariedEmployee(String name, String id, double yearlySalary) {
    if (name == null || id == null
        || name.isEmpty() || id.isEmpty()) {
      throw new IllegalArgumentException("Name and ID cannot be null or empty.");
    }
    if (yearlySalary < 0 || yearlySalary > MAX_YEARLY_SALARY) {
      throw new IllegalArgumentException("Yearly salary must be between 0 and "
          + MAX_YEARLY_SALARY);
    }
    this.name = name;
    this.id = id;
    this.yearlySalary = yearlySalary;
  }

  /**
   * Copy constructor for salaried employee.
   *
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
    return this.yearlySalary;
  }

  /**
   * Gives the employee a raise by a certain percentage.
   *
   * @param raisePercent the raise percentage (must be between 0 and 10).
   */
  @Override
  public void giveRaiseByPercent(double raisePercent) {
    if (raisePercent < 0 || raisePercent > MAX_RAISE_PERCENT) {
      throw new IllegalArgumentException("Raise percent must be between 0 and "
          + MAX_RAISE_PERCENT + ".");
    }
    yearlySalary *= (1 + raisePercent / PERCENT_DIVISOR);
    if (yearlySalary > MAX_YEARLY_SALARY) {
      yearlySalary = MAX_YEARLY_SALARY;  // Enforce maximum salary
    }
  }

  /**
   * Gets the ID of the employee.
   *
   * @return the employee ID.
   */
  @Override
  public String getID() {
    return this.id;
  }

  /**
   * Gets the name of the employee.
   *
   * @return the employee name.
   */
  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    System.out.println(String.format("Name: %s%nID: %s%nBase Salary: $%.2f",
        getName(),
        getID(),
        getBaseSalary()));
    return String.format("Name: %s %nID: %s %nBase Salary: $%.2f",
        getName(),
        getID(),
        getBaseSalary());
  }
}
