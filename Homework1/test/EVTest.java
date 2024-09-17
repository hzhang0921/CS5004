import org.junit.Test;
import org.junit.Before;

public class EVTest {
  private ElectricVehicle Vehicle1;
  private ElectricVehicle Vehicle2;
  private ElectricVehicle Vehicle3;

  private String Car1 = "Lucid Air Grand Touring";
  private String Car2 = "Ford MachE";
  private String Car3 = "Rivian R1S";

  private double BatterySize1 = 112;
  private double BatterySize2 = 6;
  private double BatterySize3 = 200;

  private double stateOfCharge1 = 0.6;
  private double stateOfCharge2 = 0.1;
  private double stateOfCharge3 = 1.15;

  private double defaultEfficiency1 = 3.0;
  private double defaultEfficiency2 = 0.3;
  private double defaultEfficiency3 = 5.0;

  /**
   * Sets up three vehicles to test the methods. Vehicle1 tests normal cases,
   * Vehicle2 tests lower limit cases, Vehicle3 tests upper limit cases
   */
  @Before
  public void setUp() {
    Vehicle1 = new ElectricVehicle(Car1, BatterySize1, stateOfCharge1, defaultEfficiency1);
    Vehicle2 = new ElectricVehicle(Car2, BatterySize2, stateOfCharge2, defaultEfficiency2);
    Vehicle3 = new ElectricVehicle(Car3, BatterySize3, stateOfCharge3, defaultEfficiency3);
  }
}
