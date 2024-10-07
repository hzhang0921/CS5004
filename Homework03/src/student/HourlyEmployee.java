package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee in the system.
 * This class extends AbstractEmployeeClass and implements specific behavior for hourly workers.
 */
public class HourlyEmployee extends AbstractEmployee{
  private double hourlySalary;
  private final double normalHours;
  private double specialHours;
  private boolean useSpecialHours;

  public static final double MAX_HOURS = 80.0;
  public static final double MIN_HOURS = 0.0;
  public static final double MAX_HOURLY_SALARY = 50.0;
  public static final double MIN_HOURLY_SALARY = 0.0;
  public static final double OVERTIME_MULTIPLIER = 1.5;
  public static final double PERCENT_DIVISOR = 100;


  /**
   * Constructs a new HourlyEmployee with the specified details.
   *
   * @param name The name of the employee.
   * @param id The unique identifier of the employee.
   * @param hourlySalary The hourly rate of pay for the employee.
   * @param normalHours The normal number of hours worked per week.
   * @throws IllegalArgumentException If any of the input parameters are invalid.
   */
  public HourlyEmployee(String name, String id, double hourlySalary, double normalHours)
      throws IllegalArgumentException {
    super(name, id);
    if (hourlySalary > MAX_HOURLY_SALARY
        || hourlySalary < MIN_HOURLY_SALARY
        || normalHours > MAX_HOURS
        || normalHours < MIN_HOURS) {
      throw new IllegalArgumentException("Invalid Employee information");
    }

    this.hourlySalary = new BigDecimal(hourlySalary).setScale(2, RoundingMode.HALF_UP).doubleValue();
    this.normalHours = new BigDecimal(normalHours).setScale(2, RoundingMode.HALF_UP).doubleValue();
    this.useSpecialHours = false;
  }

  /**
   * Copy constructor for HourlyEmployee.
   * Creates a new HourlyEmployee with the same properties as the given employee.
   *
   * @param other The HourlyEmployee to copy.
   */
  public HourlyEmployee(HourlyEmployee other) {
    super(other);
    this.hourlySalary = other.hourlySalary;
    this.normalHours = other.normalHours;
    this.specialHours = 0.0; // Reset special hours
    this.useSpecialHours = false; // Do not use special hours by default
  }

  /**
   * Calculates and returns the pay for the current period.
   * This method takes into account regular hours, overtime, and any special hours set.
   *
   * @return The total pay for the period.
   */
  @Override
  public double getPayForThisPeriod() {
    double hoursToUse = useSpecialHours ? specialHours : normalHours;
    double regularHours = Math.min(hoursToUse, 40.00);
    double overtimeHours = Math.max(0, hoursToUse - 40.00);

    double regularPay = regularHours * hourlySalary;
    double overtimePay = overtimeHours * (OVERTIME_MULTIPLIER * hourlySalary);

    useSpecialHours = false; // Reset to normal hours after payment if it is used above.
    return new BigDecimal(regularPay + overtimePay)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
  }

  /**
   * Returns the base hourly salary of the employee.
   *
   * @return The hourly salary rate.
   */
  @Override
  public double getBaseSalary() {
    return hourlySalary;
  }

  /**
   * Gives a percentage-based raise to the employee's hourly salary.
   *
   * @param raisePercent The percentage of the raise, between 0 and 10.
   * @throws IllegalArgumentException If the raise percentage is outside the valid range.
   */
  @Override
  public void giveRaiseByPercent(double raisePercent) throws IllegalArgumentException {
    if (raisePercent < 0 || raisePercent > 10) {
      throw new IllegalArgumentException("Raise percent must be between 0 and 10");
    }
    double newSalary = new BigDecimal(hourlySalary * (1 + raisePercent / PERCENT_DIVISOR))
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    if (newSalary <= MAX_HOURLY_SALARY) {
      hourlySalary = newSalary;
    }
  }

  /**
   * Sets special hours for the current pay period.
   * This overrides the normal hours for the next pay calculation.
   *
   * @param hours The number of special hours to set.
   * @throws IllegalArgumentException If the hours are outside the valid range.
   */
  public void setSpecialHours(double hours) throws IllegalArgumentException {
    if (hours < MIN_HOURS
        || hours > MAX_HOURS) {
      throw new IllegalArgumentException("Special hours must be between 0 and 80");
    }
    this.specialHours = new BigDecimal(hours)
        .setScale(2, RoundingMode.HALF_UP)
        .doubleValue();
    // allows us to set new hours worked.
    this.useSpecialHours = true; // this is the flag we will use to see if we will use normal hours
    // or special hours.
  }
}