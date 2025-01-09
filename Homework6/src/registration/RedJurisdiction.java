package registration;

public class RedJurisdiction implements IJurisdiction {
  @Override
  public double exciseTax(IVehicle vehicle) {
    return vehicle.getPurchasePrice() * 0.05;
  }

  @Override
  public String toString() { return "RedJurisdiction"; }
}
