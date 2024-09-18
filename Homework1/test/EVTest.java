import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** publicClass for testing out the methods of the class ElectricVehicle.
 *
 */
public class evTest {
  private ElectricVehicle Vehicle1;
  private ElectricVehicle Vehicle2;
  private ElectricVehicle Vehicle3;

  /** Initialization to set up test vehicles.
   * Sets up three vehicles to test the methods. Vehicle1 tests normal cases
   * Vehicle2 tests lower limit cases, Vehicle3 tests upper limit cases
   */
  @Before
  public void setUp() {
    Vehicle1 = new ElectricVehicle("Lucid Air Grand Touring", 112, 0.6, 3.0);
    Vehicle2 = new ElectricVehicle("Ford MachE", 6, 0.1, 0.3);
    Vehicle3 = new ElectricVehicle("Rivian R1S", 200,1.15, 5.0);
  }

  /** tests the batterySize method.
   * Should return exactly Vehicle one's value of 112.
   */
  @Test
  public void getBatterySizeTest() {
    Assert.assertEquals(112, Vehicle1.getBatterySize(), 0.0);
  }

  /** tests the getStateofCharge method.
   * Should return Vehicle one's value of 0.6.
   */
  @Test
  public void getStateofChargeTest() {
    Assert.assertEquals(0.6, Vehicle1.getStateOfCharge(), 0.0);
  }

  /** tests the getName method.
   * should return All vehicle's initialization value
   */
  @Test
  public void getNameTest() {
    Assert.assertEquals("Lucid Air Grand Touring", Vehicle1.getName());
    Assert.assertEquals("Ford MachE", Vehicle2.getName());
    Assert.assertEquals("Rivian R1S", Vehicle3.getName());
  }

  /** tests the getEfficiency method.
   * should return the efficiency of Vehicles.
   */
  @Test
  public void getEfficiencyTest() {
    Assert.assertEquals(3.0, Vehicle1.getEfficiency(), 0.0);
  }

  /** Tests the initial clamping.
   * Expecting battery size to be clamped to 10.0 and 150.0, expecting default Efficiency
   * to be clamped to 0.5 and 4.5 since it hasn't been altered yet.
   */
  @Test
  public void testInit() {
    Assert.assertEquals(Vehicle2.getBatterySize(), 10.0, 0.0);
    Assert.assertEquals(Vehicle3.getBatterySize(), 150.0, 0.0);
    Assert.assertEquals(Vehicle2.getEfficiency(), 0.5, 0.0);
    Assert.assertEquals(Vehicle3.getEfficiency(), 4.5, 0.0);
  }

  /** Tests Edge Case for Vehicle Name if it is null or empty.
   * Expecting results to unknown EV
   */
  @Test
  public void testInitEdge() {
    ElectricVehicle Vehicle4 = new ElectricVehicle("", 112, 0.6, 3.0);
    Assert.assertEquals(Vehicle4.getName(), "unknown EV");
  }

  /** Test functionality and edge cases of Efficiency
   *  Ranges of >77, 65 - 77, and <65 have different values and expected calculations.
   *  We'll use Vehicle1
   */
  @Test
  public void testEfficiency() {
    Vehicle1.updateEfficiency(78);
    Assert.assertEquals(2.55, Vehicle1.getEfficiency(), 0.1);
    Vehicle1.updateEfficiency(77);
    Assert.assertEquals(3.0, Vehicle1.getEfficiency(), 0.1);
    Vehicle1.updateEfficiency(60);
    Assert.assertEquals(2.85, Vehicle1.getEfficiency(), 0.1);
    Vehicle1.updateEfficiency(10);
    Assert.assertEquals(1.5, Vehicle1.getEfficiency(), 0.1);
  }

  /** Test range method.
   *  range is current efficiency * state of charge * batterySize
   */
  @Test
  public void testrange() {
    Assert.assertEquals(201.6, Vehicle1.range(), 0.1);
    Vehicle1.updateEfficiency(10);
    Assert.assertEquals(100.8, Vehicle1.range(), 0.1);
  }

  /** Test setStateOfCharge.
   *  tests to see if state of charge is etting correctly
   */
  @Test
  public void TestsetStateOfCharge() {
    Vehicle1.setStateOfCharge(0.7);
    Assert.assertEquals(0.7, Vehicle1.getStateOfCharge(), 0.0);
  }

  /** Test tostring.
   *  tests if the string is printing as expected
   */
  @Test
  public void TesttoString() {
    ElectricVehicle MachE = new ElectricVehicle("Ford MachE", 131.8, 0.5, 2.0);
    Assert.assertEquals("Ford MachE SOC: 50.0% Range (miles): 131.8", MachE.toString());
  }
}
