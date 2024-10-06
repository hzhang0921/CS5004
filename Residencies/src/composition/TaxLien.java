package composition;

public class TaxLien {
  private double totalTax;
  private boolean useHalfYear;

  public TaxLien(double totalTax, boolean useHalfYear) {
    this.totalTax = totalTax;
    this.useHalfYear = useHalfYear;
  }

  public TaxLien(TaxLien other) {
    this.totalTax = other.totalTax;
    this.useHalfYear = other.useHalfYear;
  }

  public double getTaxLiability() {
    if (this.useHalfYear) {
      return this.totalTax/2;
    }
    return this.totalTax;
  }

  public void switchPaymentPlan() {
    this.useHalfYear = !this.useHalfYear;
  }

  @Override
  public String toString() {
    return "Taxes: " + this.totalTax;
  }
}

