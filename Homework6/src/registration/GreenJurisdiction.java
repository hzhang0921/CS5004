package registration;

public class GreenJurisdiction implements IJurisdiction {
  @Override
  public double exciseTax(IVehicle vehicle) {
    return (vehicle.getPurchasePrice() * 0.04) + (100 * vehicle.getMaxPassengers());
  }

  @Override
  public String toString() { return "GreenJurisdiction"; }
}
