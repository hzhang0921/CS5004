package student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

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

  private String name;
  private String id;
  private double hourlySalary;
  private double normalHours;
  private double specialHours;

  /**
   * Constructor for creating an HourlyEmployee.
   *
   * @param employeeName the name of the employee.
   * @param employeeId the ID of the employee.
   * @param baseHourlySalary the hourly salary of the employee.
   * @param weeklyNormalHours the normal number of hours the employee works in a week.
   */
  public HourlyEmployee(String employeeName, String employeeId,
                        double baseHourlySalary, double weeklyNormalHours) {
    if (employeeName == null || employeeId == null
        || employeeName.isEmpty() || employeeId.isEmpty()) {
      throw new IllegalArgumentException("Name and ID cannot be null or empty.");
    }
    if (baseHourlySalary < 0
        || baseHourlySalary > MAX_HOURLY_SALARY) {
      throw new IllegalArgumentException("Hourly salary must be between 0 and "
          + MAX_HOURLY_SALARY);
    }
    if (weeklyNormalHours < 0 || weeklyNormalHours > MAX_HOURS) {
      throw new IllegalArgumentException("Weekly hours must be between 0 and " + MAX_HOURS);
    }
    this.name = employeeName;
    this.id = employeeId;
    this.hourlySalary = baseHourlySalary;
    this.normalHours = weeklyNormalHours;
  }

  /**
   * Copy constructor for HourlyEmployee.
   * @param other is another hourlyEmployee
   */
  public HourlyEmployee(HourlyEmployee other) {
    if (other == null) {
      throw new IllegalArgumentException("Cannot copy from a null object.");
    }
    this.name = other.name;
    this.id = other.id;
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
    // If both specialHours and normalHours are 0, return 0 pay
    if (specialHours == 0 && normalHours == 0) {
      return 0.0;
    }

    // Default to normal hours if specialHours is 0
    double hours = normalHours;

    // Use special hours if they are set
    if (specialHours != 0) {
      hours = specialHours;
    }

    double overtime = 0;
    if (hours > FULL_TIME_HOURS) {
      overtime = hours - FULL_TIME_HOURS;
    }

    double regularPay = hourlySalary * Math.min(hours, FULL_TIME_HOURS);
    double overtimePay = hourlySalary * OVERTIME_MULTIPLIER * overtime;

    specialHours = 0; // Reset special hours after pay is calculated

    BigDecimal totalPay = new BigDecimal(regularPay + overtimePay);
    totalPay = totalPay.setScale(2, RoundingMode.HALF_UP);
    return totalPay.doubleValue();
  }

  @Override
  public double getBaseSalary() {
    return hourlySalary;
  }

  @Override
  public void giveRaiseByPercent(double raisePercent) {
    if (raisePercent < 0 || raisePercent > 10) {
      throw new IllegalArgumentException("Raise percent must be between 0 and 10.");
    }
    hourlySalary *= (1 + raisePercent / 100);
    if (hourlySalary > MAX_HOURLY_SALARY) {
      hourlySalary = MAX_HOURLY_SALARY;  // Enforce maximum hourly salary
    }
  }

  @Override
  public String getID() {
    return id;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Name: " + name + "\nID: " + id + "\nBase Salary: $" + hourlySalary;
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

  @Override
  public int hashCode() {
    // Generate a hash code based on the fields used in equals()
    return Objects.hash(name, id, hourlySalary, normalHours);
  }
}