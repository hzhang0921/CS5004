package registration;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Registration implements IRegistration {
  private IVehicle vehicle;
  private IJurisdiction jurisdiction;
  private int registrationYear;
  private List<Person> owners;

  public Registration(IVehicle vehicle, IJurisdiction jurisdiction, int registrationYear, List<Person> owners) {
    this.vehicle = vehicle;
    this.jurisdiction = jurisdiction;
    this.registrationYear = registrationYear;
    this.owners = Collections.unmodifiableList(owners);
  }

  @Override
  public int getRegistrationYear() { return registrationYear; }

  @Override
  public int getProductionYear() { return vehicle.getProductionYear(); }

  @Override
  public IJurisdiction getJurisdiction() { return jurisdiction; }

  @Override
  public List<Person> getOwners() { return owners; }

  @Override
  public int getMaxPassengers() { return vehicle.getMaxPassengers(); }

  @Override
  public double calculateExciseTax() { return jurisdiction.exciseTax(vehicle); }

  @Override
  public String toString() {
    String ownersStr = String.join("; ", owners.stream().map(Person::toString).toArray(String[]::new));
    return String.format("Registration:\n%s: %s Year(%d) Price = $%.2f\nOwned By: %s\nYear: %d\nExcise Tax: $%.2f",
        vehicle.getClass().getSimpleName().toUpperCase(), vehicle.getMake(), vehicle.getProductionYear(),
        vehicle.getPurchasePrice(), ownersStr, registrationYear, calculateExciseTax());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Registration)) return false;
    Registration that = (Registration) o;
    return registrationYear == that.registrationYear &&
        Objects.equals(vehicle, that.vehicle) &&
        Objects.equals(owners, that.owners);
  }

  @Override
  public int hashCode() {
    return Objects.hash(vehicle, registrationYear, owners);
  }
}