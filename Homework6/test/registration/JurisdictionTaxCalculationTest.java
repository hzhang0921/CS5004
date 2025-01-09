package registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JurisdictionTaxCalculationTest {

  @Test
  public void testBlueJurisdictionTax() {
    IJurisdiction blue = new BlueJurisdiction();
    IVehicle oldAuto = new Automobile("Bagley", 1999, 10000.00);
    IVehicle newAuto = new Automobile("Bagley", 2020, 10000.00);

    assertEquals(399.00, blue.exciseTax(oldAuto), 0.01);
    assertEquals(300.00, blue.exciseTax(newAuto), 0.01);
  }

  @Test
  public void testRedJurisdictionTax() {
    IJurisdiction red = new RedJurisdiction();
    IVehicle motorcycle = new Motorcycle("Bagley-Davidson", 2010, 5000.00);

    assertEquals(250.00, red.exciseTax(motorcycle), 0.01);
  }

  @Test
  public void testGreenJurisdictionTax() {
    IJurisdiction green = new GreenJurisdiction();
    IVehicle boat = new Boat("Bagley-Marine-Majik", 2020, 60000.00);

    assertEquals(3400.00, green.exciseTax(boat), 0.01);
  }
}
