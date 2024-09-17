import static java.lang.Math.clamp;

import java.text.DecimalFormat;


/**
 * An ElectricVehicle Class that has name, batterySize, stateOfCharge, currentEfficiency, and defaultEfficiency
 */
public class ElectricVehicle {
  private String name;
  private double batterySize;
  private double stateOfCharge;
  private double currentEfficiency;
  private double defaultEfficiency;

  /**
   * Constructor class for ElectricVehicle.
   *
   * @param name              is the User Input of the name of the EV, confirming there is something there
   * @param batterySize       is the User Input of the battery Size of the EV clamped beteen 10 and 150
   * @param stateOfCharge     is the User Input of the Charge of the EV, clamped between 0.15 and 1.00
   * @param defaultEfficiency is the User Input of the efficiency, clamped between 0.5 and 4.5
   */
  public ElectricVehicle(String name, double batterySize, double stateOfCharge, double defaultEfficiency) {
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

  /**
   * @return the range of the vehicle as a double
   */
  public double range() {
    return this.currentEfficiency * this.stateOfCharge * this.batterySize;
  }

  /**
   * Calculates a vehicle efficiency based on the following rules: currentTemp >77F = 85% efficiency
   * 65F <= currentTemp <= 77F = 100% efficiency
   * currentTemp < 65 = 1% loss in efficiency per degree up to 50%
   *
   * @param currentTemp
   */
  public void updateEfficiency(double currentTemp) {
    if (currentTemp > 77.0) {
      this.currentEfficiency = this.defaultEfficiency * 0.85;
    } else if (currentTemp < 65.0) {
      double modifier = clamp((65.0 - currentTemp) * 0.01, 0.0, 0.5);
      this.currentEfficiency = this.currentEfficiency * modifier;
    }
  }

  /**
   * returns the double value of the currentEfficiency of the vehicle
   *
   * @return
   */
  public double getEfficiency() {
    return this.currentEfficiency;
  }

  /**
   * returns the double value of the batterySize of the vehicle
   *
   * @return
   */
  public double getBatterySize() {
    return this.batterySize;
  }

  /**
   * returns the double value stateOfCharge of the vehicle
   *
   * @return
   */
  public double getStateOfCharge() {
    return this.stateOfCharge;
  }

  /**
   * returns the name value of the vehicle
   *
   * @return
   */
  public String getName() {
    return this.name;
  }

  public String toString() {
    DecimalFormat df = new DecimalFormat("0.0"); // To format with 1 decimal place
    return getName() + " SOC: " + df.format(getStateOfCharge() * 100) + "% Range (miles): " + df.format(range());
  }
}