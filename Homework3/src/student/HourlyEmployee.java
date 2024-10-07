package student;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Represents an hourly employee.
 */
public class HourlyEmployee implements IEmployee {

  /** The maximum number of hours an employee can work in a week. */
  public static final double MAX_HOURS = 80.0;

  /** The maximum hourly salary for an employee. */
  public static final double MAX_HOURLY_SALARY = 50.0;

  /** The standard full-time work week hours. */
  public static final double FULL_TIME_HOURS = 40.0;

  /** The overtime multiplier for employees working over full-time hours. */
  public static final double OVERTIME_MULTIPLIER = 1.5;

  /** The maximum allowable raise percentage. */
  public static final double MAX_RAISE_PERCENT = 10.0;

  /** Percentage divisor for calculating raises. */
  public static final double PERCENT_DIVISOR = 100.0;

  /** The name of the employee. */
  private String name;

  /** The ID of the employee. */
  private String id;

  /** The hourly salary of the employee. */
  private double hourlySalary;

  /** The normal number of hours worked per week. */
  private double normalHours;

  /** The special hours worked, overriding normal hours temporarily. */
  private double specialHours;

  /**
   * Constructor for creating an HourlyEmployee.
   *
   * @param name the name of the employee.
   * @param id the ID of the employee.
   * @param hourlySalary the hourly salary of the employee.
   * @param normalHours the normal number of hours the employee works in a week.
   */
  public HourlyEmployee(String name, String id,
                        double hourlySalary, double normalHours) {
    if (name == null || id == null
        || name.isEmpty() || id.isEmpty()) {
      throw new IllegalArgumentException("Name and ID cannot be null or empty.");
    }
    if (hourlySalary < 0 || hourlySalary > MAX_HOURLY_SALARY) {
      throw new IllegalArgumentException("Hourly salary must be between 0 and "
          + MAX_HOURLY_SALARY);
    }
    if (normalHours < 0 || normalHours > MAX_HOURS) {
      throw new IllegalArgumentException("Weekly hours must be between 0 and " + MAX_HOURS);
    }
    this.name = name;
    this.id = id;
    this.hourlySalary = hourlySalary;
    this.normalHours = normalHours;
  }

  /**
   * Copy constructor for HourlyEmployee.
   *
   * @param other is another hourlyEmployee
   */
  public HourlyEmployee(HourlyEmployee other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot copy from a null object.");
    }
    this.name = new String(other.name);
    this.id = new String(other.id);
    this.hourlySalary = other.hourlySalary;
    this.normalHours = other.normalHours;
    this.specialHours = other.specialHours;  // Copy the special hours too
  }

  /**
   * Sets special hours temporarily overriding the normal hours worked.
   *
   * @param hours the special number of hours worked.
   */
  public void setSpecialHours(double hours) {
    if (hours < 0 || hours > MAX_HOURS) {
      throw new IllegalArgumentException("Special hours must be between 0 and " + MAX_HOURS);
    }
    this.specialHours = hours;
  }

  @Override
  public double getPayForThisPeriod() {
    if (specialHours == 0 && normalHours == 0) {
      return 0.0;
    }

    double hours = (specialHours != 0) ? specialHours : normalHours;
    double overtime = (hours > FULL_TIME_HOURS) ? hours - FULL_TIME_HOURS : 0;

    double regularPay = hourlySalary * Math.min(hours, FULL_TIME_HOURS);
    double overtimePay = hourlySalary * OVERTIME_MULTIPLIER * overtime;

    specialHours = 0; // Reset special hours after pay is calculated

    BigDecimal totalPay = new BigDecimal(regularPay + overtimePay);
    totalPay = totalPay.setScale(2, RoundingMode.HALF_UP);
    return totalPay.doubleValue();
  }

  @Override
  public double getBaseSalary() {
    return this.hourlySalary;
  }

  @Override
  public void giveRaiseByPercent(double raisePercent) {
    if (raisePercent < 0 || raisePercent > MAX_RAISE_PERCENT) {
      throw new IllegalArgumentException("Raise percent must be between 0 and "
          + MAX_RAISE_PERCENT + ".");
    }
    hourlySalary *= (1 + raisePercent / PERCENT_DIVISOR);
    if (hourlySalary > MAX_HOURLY_SALARY) {
      hourlySalary = MAX_HOURLY_SALARY;  // Enforce maximum hourly salary
    }
  }

  @Override
  public String getID() {
    return this.id;
  }

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
    return String.format("Name: %s%nID: %s%nBase Salary: $%.2f",
        getName(),
        getID(),
        getBaseSalary());
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;  // If both references are the same
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;  // If the other object is null or not of the same class
    }
    HourlyEmployee other = (HourlyEmployee) obj;
    // Compare relevant fields to determine equality
    return Double.compare(hourlySalary, other.hourlySalary) == 0
        && Double.compare(normalHours, other.normalHours) == 0
        && name.equals(other.name)
        && id.equals(other.id);
  }
}
