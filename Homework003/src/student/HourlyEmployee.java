package student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Represents an hourly employee in the system.
 */
public class HourlyEmployee implements IEmployee {

  /** The employee's name. */
  private final String name;

  /** The employee's ID. */
  private final String id;

  /** The hourly salary of the employee. */
  private double hourlySalary;

  /** The normal hours worked by the employee per week. */
  private final double normalHours;

  /** The special hours worked by the employee, used for overtime. */
  private double specialHours;

  /** Flag to indicate if special hours are used. */
  private boolean useSpecialHours;

  /** The maximum hours an employee can work. */
  public static final double MAX_HOURS = 80.0;

  /** The minimum hours an employee can work. */
  public static final double MIN_HOURS = 0.0;

  /** The maximum hourly salary for an employee. */
  public static final double MAX_HOURLY_SALARY = 50.0;

  /** The minimum hourly salary for an employee. */
  public static final double MIN_HOURLY_SALARY = 0.0;

  /** The multiplier for overtime pay. */
  public static final double OVERTIME_MULTIPLIER = 1.5;

  /** The divisor for calculating raise percentages. */
  public static final double PERCENT_DIVISOR = 100;

  /** The number of hours before overtime pay is applied. */
  public static final double HOURS_BEFORE_SPECIAL = 40;

  /** The maximum allowable percentage raise. */
  public static final double MAX_PERCENT_INCREASE = 10;

  /**
   * Constructs a new HourlyEmployee with the specified details.
   *
   * @param eName The name of the employee.
   * @param eId The unique identifier of the employee.
   * @param eHourlySalary The hourly rate of pay for the employee.
   * @param eNormalHours The normal number of hours worked per week.
   * @throws IllegalArgumentException if input parameters are invalid.
   */
  public HourlyEmployee(String eName, String eId, double eHourlySalary,
                        double eNormalHours) throws IllegalArgumentException {
    if (eName == null || eId == null || eName.trim().isEmpty()
        || eId.trim().isEmpty()) {
      throw new IllegalArgumentException(
          "Name and ID must not be null, empty, or whitespace.");
    }
    if (eHourlySalary > MAX_HOURLY_SALARY || eHourlySalary < MIN_HOURLY_SALARY
        || eNormalHours > MAX_HOURS || eNormalHours < MIN_HOURS) {
      throw new IllegalArgumentException("Invalid employee information.");
    }

    this.name = eName;
    this.id = eId;
    this.hourlySalary = new BigDecimal(eHourlySalary)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    this.normalHours = new BigDecimal(eNormalHours)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    this.useSpecialHours = false;
  }

  /**
   * Copy constructor for HourlyEmployee.
   * Creates a new HourlyEmployee with the same properties as the given
   * employee.
   *
   * @param other The HourlyEmployee to copy.
   */
  public HourlyEmployee(HourlyEmployee other) {
    this.name = other.name;
    this.id = other.id;
    this.hourlySalary = other.hourlySalary;
    this.normalHours = other.normalHours;
    this.specialHours = other.specialHours;
    this.useSpecialHours = other.useSpecialHours;
  }

  @Override
  public double getPayForThisPeriod() {
    double hoursToUse = useSpecialHours ? specialHours : normalHours;
    double regularHours = Math.min(hoursToUse, HOURS_BEFORE_SPECIAL);
    double overtimeHours = Math.max(0, hoursToUse - HOURS_BEFORE_SPECIAL);
    double regularPay = regularHours * hourlySalary;
    double overtimePay = overtimeHours * (OVERTIME_MULTIPLIER * hourlySalary);

    useSpecialHours = false; // Reset to normal hours after payment.
    return new BigDecimal(regularPay + overtimePay)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
  }

  @Override
  public double getBaseSalary() {
    return hourlySalary;
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
    double newSalary = new BigDecimal(hourlySalary
        * (1 + raisePercent / PERCENT_DIVISOR))
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    if (newSalary <= MAX_HOURLY_SALARY) {
      hourlySalary = newSalary;
    }
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

  /**
   * Sets special hours for the current pay period.
   * This overrides the normal hours for the next pay calculation.
   *
   * @param hours The number of special hours to set.
   * @throws IllegalArgumentException If the hours are outside the valid range.
   */
  public void setSpecialHours(double hours)
      throws IllegalArgumentException {
    if (hours < MIN_HOURS || hours > MAX_HOURS) {
      throw new IllegalArgumentException(
          "Special hours must be between 0 and 80.");
    }
    this.specialHours = new BigDecimal(hours)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    this.useSpecialHours = true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HourlyEmployee that = (HourlyEmployee) o;
    return Objects.equals(name, that.name)
        && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, id, hourlySalary, normalHours);
  }
}
