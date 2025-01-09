package registration;

public class BlueJurisdiction implements IJurisdiction {
  @Override
  public double exciseTax(IVehicle vehicle) {
    double tax = vehicle.getPurchasePrice() * 0.03;
    if (vehicle.getProductionYear() < 2000) tax += 99;
    return tax;
  }

  @Override
  public String toString() { return "BlueJurisdiction"; }
}
