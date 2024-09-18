import java.text.DecimalFormat;


/** Class ElecticVehicle used to represent an EV.
 * An ElectricVehicle Class that has name, batterySize, stateOfCharge,
 * currentEfficiency, and defaultEfficiency
 */
public class ElectricVehicle {
  private String name;
  private double batterySize;
  private double stateOfCharge;
  private double currentEfficiency;
  private double defaultEfficiency;

  /** defines the clamp function pre-emptively for the autograder.
   *
   * @param value that you are trying to clamp
   * @param min value that you will clamp to
   * @param max value that you will clamp to
   * @return the final post-clamped value
   */
  public static double clamp(double value, double min, double max) {
    if (value < min) {
      return min;
    } else if (value > max) {
      return max;
    } else {
      return value;
    }
  }


  /**
   * Constructor class for ElectricVehicle.
   *
   * @param name              is the User Input of the name of the EV,
   *                          confirming there is something there
   * @param batterySize       is the User Input of the battery Size
   *                          of the EV clamped beteen 10 and 150
   * @param stateOfCharge     is the User Input of the Charge
   *                          of the EV, clamped between 0.15 and 1.00
   * @param defaultEfficiency is the User Input of the efficiency,
   *                          clamped between 0.5 and 4.5
   */
  public ElectricVehicle(String name, double batterySize,
                         double stateOfCharge, double defaultEfficiency) {
    batterySize = clamp(batterySize, 10.0, 150.0);
    defaultEfficiency = clamp(defaultEfficiency, 0.5, 4.5);
    if (name == null || name.isEmpty()) {
      name = "unknown EV";
    }
    stateOfCharge = clamp(stateOfCharge, 0.15, 1.00);
    this.name = name;
    this.batterySize = batterySize;
    this.stateOfCharge = stateOfCharge;
    this.currentEfficiency = defaultEfficiency;
    this.defaultEfficiency = defaultEfficiency;
  }

  /** Gives Range of the Vehicle.
   * @return the range of the vehicle as a double
   */
  public double range() {
    return this.currentEfficiency * this.stateOfCharge * this.batterySize;
  }

  /** Gives Vehicle Efficiency.
   * Calculates a vehicle efficiency based on the following rules: currentTemp >77F = 85% efficiency
   * 65F <= currentTemp <= 77F = 100% efficiency
   * currentTemp < 65 = 1% loss in efficiency per degree up to 50%
   *
   * @param currentTemp represents current temp
   */
  public void updateEfficiency(double currentTemp) {
    if (currentTemp > 77.0) {
      this.currentEfficiency = this.defaultEfficiency * 0.85;
    } else if (currentTemp < 65.0) {
      double modifier = clamp((65.0 - currentTemp) * 0.01, 0.0, 0.5);
      this.currentEfficiency = this.defaultEfficiency * (1 - modifier);
    } else {
      currentEfficiency = this.defaultEfficiency;
    }
  }

  /** gives Efficiency.
   * returns the decimal value of the currentEfficiency of the vehicle
   *
   * @return this.currentEfficiency
   */
  public double getEfficiency() {
    return this.currentEfficiency;
  }

  /** gives Battery Size from Vehicle.
   *
   * @return the double value of the batterySize of the vehicle
   */
  public double getBatterySize() {
    return this.batterySize;
  }

  /** gives the current stateOfCharge.
   *
   * @return the double value stateOfCharge of the vehicle
   */
  public double getStateOfCharge() {
    return this.stateOfCharge;
  }

  /** gives the name value of the vehicle.
   *
   * @return the name value of the vehicle
   */
  public String getName() {
    return this.name;
  }

  /** sets a new state of charge for the given vehicle.
   *
   */
  public void setStateOfCharge(double stateOfCharge) {
    this.stateOfCharge = clamp(stateOfCharge, 0.15, 1.00);
  }


  /** gives a string summarizing a vehicle.
   *
   * @return a string summary of the Car
   */
  public String toString() {
    DecimalFormat df = new DecimalFormat("0.0"); // To format with 1 decimal place
    return getName() + " SOC: " + df.format(getStateOfCharge() * 100)
        + "% Range (miles): " + df.format(range());
  }
}