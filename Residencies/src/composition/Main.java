package composition;

public class Main {
  public static void main(String[] args) {
    Address a = new Address("400 Washington st", "Hoboken", "NJ", "07030",
        new TaxLien(1000, true));
    System.out.println("A's original tax liability: ");
    System.out.println(a.getTaxes().getTaxLiability()); // Composition allows you to use something inside the TaxLien Class

    Address b = new Address(a);
    b.getTaxes().switchPaymentPlan();
    System.out.println("B's original tax liability: ");
    System.out.println(b.getTaxes().getTaxLiability());
    System.out.println(b);
  }
}
