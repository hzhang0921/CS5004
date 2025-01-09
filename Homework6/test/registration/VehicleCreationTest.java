package registration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleCreationTest {

  @Test
  public void testCreateMotorcycle() {
    IVehicle motorcycle = new Motorcycle("Harley Davidson", 1990, 12000.00);
    assertEquals("Harley Davidson", motorcycle.getMake());
    assertEquals(1990, motorcycle.getProductionYear());
    assertEquals(12000.00, motorcycle.getPurchasePrice());
    assertEquals(2, motorcycle.getMaxPassengers());
  }

  @Test
  public void testCreateAutomobile() {
    IVehicle auto = new Automobile("Toyota", 2018, 25000.00);
    assertEquals("Toyota", auto.getMake());
    assertEquals(2018, auto.getProductionYear());
    assertEquals(25000.00, auto.getPurchasePrice());
    assertEquals(5, auto.getMaxPassengers());
  }

  @Test
  public void testCreateBoat() {
    IVehicle boat = new Boat("Mercury", 2005, 35000.00);
    assertEquals("Mercury", boat.getMake());
    assertEquals(2005, boat.getProductionYear());
    assertEquals(35000.00, boat.getPurchasePrice());
    assertEquals(10, boat.getMaxPassengers());
  }

  @Test
  public void testInvalidVehicleCreation() {
    assertThrows(IllegalArgumentException.class, () -> new Motorcycle("", 1990, 12000.00));
    assertThrows(IllegalArgumentException.class, () -> new Automobile("Toyota", 1800, 25000.00));
    assertThrows(IllegalArgumentException.class, () -> new Boat("Mercury", 2025, -1000.00));
  }
}
