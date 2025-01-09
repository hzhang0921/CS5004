package registration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class RegistrationSystem {
  private static RegistrationSystem instance;
  private List<IRegistration> registrations;

  private RegistrationSystem() {
    registrations = new ArrayList<>();
  }

  public static synchronized RegistrationSystem getInstance() {
    if (instance == null) {
      instance = new RegistrationSystem();
    }
    return instance;
  }

  public IVehicle createVehicle(String kind, String make, int productionYear, double purchasePrice) {
    // Validate 'kind' to ensure it is non-null and non-empty
    if (kind == null || kind.isEmpty()) {
      throw new IllegalArgumentException("Vehicle kind cannot be null or empty");
    }

    // Validate 'make' to ensure it is non-null and non-empty
    if (make == null || make.isEmpty()) {
      throw new IllegalArgumentException("Vehicle make cannot be null or empty");
    }

    // Validate 'productionYear' within the allowed range
    if (productionYear < 1900 || productionYear > 2023) {
      throw new IllegalArgumentException("Production year must be between 1900 and 2023");
    }

    // Validate 'purchasePrice' to ensure it is non-negative
    if (purchasePrice < 0) {
      throw new IllegalArgumentException("Purchase price cannot be negative");
    }

    // Return the correct vehicle type if 'kind' matches; otherwise, return null
    String kindLower = kind.toLowerCase();
    if ("motorcycle".equals(kindLower)) {
      return new Motorcycle(make, productionYear, purchasePrice);
    } else if ("auto".equals(kindLower)) {
      return new Automobile(make, productionYear, purchasePrice);
    } else if ("boat".equals(kindLower)) {
      return new Boat(make, productionYear, purchasePrice);
    } else {
      return null;  // if vehicle doesnt exist, return null
    }
  }

  public void register(IVehicle vehicle, IJurisdiction jurisdiction, int registrationYear, List<Person> owners) {
    IRegistration newRegistration = new Registration(vehicle, jurisdiction, registrationYear, owners);
    if (!registrations.contains(newRegistration)) {
      registrations.add(newRegistration);
    }
  }

  public List<IRegistration> getAllRegistrations() {
    return Collections.unmodifiableList(registrations);
  }

  public List<IRegistration> getFilteredList(Predicate<IRegistration> query) {
    List<IRegistration> filteredList = new ArrayList<>();
    for (IRegistration registration : registrations) {
      if (query.test(registration)) {
        filteredList.add(registration);
      }
    }
    return Collections.unmodifiableList(filteredList);
  }

  public void reboot() {
    registrations.clear();
    instance = null;
  }
}
