package taxes;

import java.util.Objects;

public class TaxLien {
  private String jurisdiction;
  private double totalTax;
  private boolean useHalfYear; // if taxes are half year, true

  public TaxLien(String jurisdiction, double totalTax, boolean useHalfYear) {
    this.jurisdiction = jurisdiction;
    this.totalTax = totalTax;
    this.useHalfYear = useHalfYear;
  }

  public TaxLien(TaxLien that) {
    this.jurisdiction = that.jurisdiction;
    this.totalTax = that.totalTax;
    this.useHalfYear = that.useHalfYear;
  }

  public double getTaxLiability() {
    if(this.useHalfYear) {
      return this.totalTax / 2;
    }
    return this.totalTax;
  }

  public void switchPaymentPlan() {
    this.useHalfYear = ! this.useHalfYear; // switch from full to half or vice versa
  }
  public String toString() {
    return "Taxes: " + this.jurisdiction + " " + this.totalTax;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    TaxLien taxLien = (TaxLien) o;
    return Double.compare(totalTax, taxLien.totalTax) == 0 && Objects.equals(jurisdiction, taxLien.jurisdiction);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jurisdiction, totalTax);
  }
}
