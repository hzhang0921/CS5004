package registration;

public abstract class AbstractVehicle implements IVehicle {
  protected String make;
  protected int productionYear;
  protected double purchasePrice;
  protected int maxPassengers;

  public AbstractVehicle(String make, int productionYear, double purchasePrice, int maxPassengers) {
    if (make == null || make.isEmpty()) throw new IllegalArgumentException("Invalid make");
    if (productionYear < 1900 || productionYear > 2023) throw new IllegalArgumentException("Invalid production year");
    if (purchasePrice < 0) throw new IllegalArgumentException("Invalid purchase price");

    this.make = make;
    this.productionYear = productionYear;
    this.purchasePrice = purchasePrice;
    this.maxPassengers = maxPassengers;
  }

  @Override
  public String getMake() { return make; }

  @Override
  public int getProductionYear() { return productionYear; }

  @Override
  public double getPurchasePrice() { return purchasePrice; }

  @Override
  public int getMaxPassengers() { return maxPassengers; }
}