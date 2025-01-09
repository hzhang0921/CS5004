package registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class RegistrationSystemTest {

  private RegistrationSystem system;

  @BeforeEach
  public void setUp() {
    system = RegistrationSystem.getInstance();
    system.reboot(); // Reset system for each test
  }

  @Test
  public void testSingletonInstance() {
    RegistrationSystem instance1 = RegistrationSystem.getInstance();
    RegistrationSystem instance2 = RegistrationSystem.getInstance();
    assertSame(instance1, instance2);
  }

  @Test
  public void testCreateVehicle() {
    IVehicle motorcycle = system.createVehicle("motorcycle", "Yamaha", 2020, 15000.00);
    assertNotNull(motorcycle);
    assertEquals("Yamaha", motorcycle.getMake());

    IVehicle invalidVehicle = system.createVehicle("plane", "Boeing", 2020, 1000000.00);
    assertNull(invalidVehicle);
  }

  @Test
  public void testRegisterVehicle() {
    IVehicle auto = system.createVehicle("auto", "Honda", 2015, 22000.00);
    IJurisdiction blue = new BlueJurisdiction();
    Person owner1 = new Person("Bruce Wayne", "Gotham City");

    system.register(auto, blue, 2021, List.of(owner1));
    List<IRegistration> registrations = system.getAllRegistrations();
    assertEquals(1, registrations.size());

    IRegistration registration = registrations.get(0);
    assertEquals(2021, registration.getRegistrationYear());
    assertEquals(auto.getProductionYear(), registration.getProductionYear());
    assertEquals("BlueJurisdiction", registration.getJurisdiction().toString());
    assertEquals(1, registration.getOwners().size());
  }

  @Test
  public void testDuplicateRegistration() {
    IVehicle boat = system.createVehicle("boat", "Yamaha", 2018, 30000.00);
    IJurisdiction red = new RedJurisdiction();
    Person owner1 = new Person("Clark Kent", "Metropolis");

    system.register(boat, red, 2021, List.of(owner1));
    system.register(boat, red, 2021, List.of(owner1)); // Attempt duplicate

    List<IRegistration> registrations = system.getAllRegistrations();
    assertEquals(1, registrations.size()); // Only one registration should be present
  }

  @Test
  public void testRebootSystem() {
    IVehicle motorcycle = system.createVehicle("motorcycle", "Harley", 2010, 8000.00);
    IJurisdiction green = new GreenJurisdiction();
    Person owner1 = new Person("Tony Stark", "Malibu");

    system.register(motorcycle, green, 2021, List.of(owner1));
    assertEquals(1, system.getAllRegistrations().size());

    system.reboot();
    assertEquals(0, system.getAllRegistrations().size());
  }

  @Test
  public void testFilteredList() {
    IVehicle motorcycle = system.createVehicle("motorcycle", "Kawasaki", 2018, 10000.00);
    IVehicle boat = system.createVehicle("boat", "Skidoo", 2019, 50000.00);
    IJurisdiction blue = new BlueJurisdiction();
    IJurisdiction green = new GreenJurisdiction();

    Person owner1 = new Person("Arthur Curry", "Atlantis");
    Person owner2 = new Person("Diana Prince", "Themyscira");

    system.register(motorcycle, blue, 2021, List.of(owner1));
    system.register(boat, green, 2021, Arrays.asList(owner1, owner2));

    List<IRegistration> filteredList = system.getFilteredList(reg -> reg.getOwners().size() > 1);
    assertEquals(1, filteredList.size()); // Only one registration with more than one owner
    assertEquals("GreenJurisdiction", filteredList.get(0).getJurisdiction().toString());
  }
}
