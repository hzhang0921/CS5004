package student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents a salaried employee in the system.
 */
public class SalariedEmployee implements IEmployee {

  /** The name of the employee. */
  private final String name;

  /** The unique identifier for the employee. */
  private final String id;

  /** The yearly salary of the employee. */
  private double yearlySalary;

  /** The maximum yearly salary an employee can have. */
  public static final double MAX_YEARLY_SALARY = 1000000;

  /** Number of months in a year. */
  public static final double MONTHS_IN_YEAR = 12;

  /** The maximum percentage increase for a raise. */
  public static final double MAX_PERCENT_INCREASE = 10;

  /** The divisor used to calculate percentage raises. */
  public static final double PERCENT_DIVISOR = 100;

  /**
   * Constructs a new SalariedEmployee with the specified details.
   *
   * @param eName The name of the employee.
   * @param eId The unique identifier of the employee.
   * @param eYearlySalary The yearly salary of the employee.
   * @throws IllegalArgumentException If the yearly salary is invalid.
   */
  public SalariedEmployee(String eName, String eId, double eYearlySalary)
      throws IllegalArgumentException {
    if (eName == null || eId == null || eName.trim().isEmpty()
        || eId.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Name and ID must not be null, empty, or whitespace.");
    }
    if (eYearlySalary  < 0 || eYearlySalary  > MAX_YEARLY_SALARY) {
      throw new IllegalArgumentException("Invalid yearly salary.");
    }
    this.name = eName;
    this.id = eId;
    this.yearlySalary = new BigDecimal(eYearlySalary)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
  }

  /**
   * Copy constructor for SalariedEmployee.
   * Creates a new SalariedEmployee with the same properties as the given
   * employee.
   *
   * @param other The SalariedEmployee to copy.
   */
  public SalariedEmployee(SalariedEmployee other) {
    this.name = other.name;
    this.id = other.id;
    this.yearlySalary = other.yearlySalary;
  }

  @Override
  public double getPayForThisPeriod() {
    return new BigDecimal(yearlySalary / MONTHS_IN_YEAR)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
  }

  @Override
  public double getBaseSalary() {
    return yearlySalary;
  }

  /**
   * Gives a raise to the employee by a percentage of their current salary.
   *
   * @param raisePercent The percentage to increase the salary.
   * @throws IllegalArgumentException If the raise percent is invalid.
   */
  @Override
  public void giveRaiseByPercent(double raisePercent)
      throws IllegalArgumentException {
    if (raisePercent < 0 || raisePercent > MAX_PERCENT_INCREASE) {
      throw new IllegalArgumentException(
          "Raise percent must be between 0 and 10.");
    }
    double newSalary = new BigDecimal(yearlySalary
        * (1 + raisePercent / PERCENT_DIVISOR))
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    yearlySalary = Math.min(newSalary, MAX_YEARLY_SALARY);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getID() {
    return id;
  }

  @Override
  public String toString() {
    return String.format("Name: %s%nID: %s%nBase Salary: $%.2f",
        this.getName(), this.getID(), this.getBaseSalary());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SalariedEmployee that = (SalariedEmployee) o;
    return Objects.equals(name, that.name)
        && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, yearlySalary);
  }
}
