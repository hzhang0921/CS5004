package bank;

/**
 * public concrete class SavingsAccount that extends Abstract class Account.
 */
public class SavingsAccount extends Account {
  /**
   * Constructor method for SavingsAccount.
   * @param Balance that you want to initalize SavingsAccount with.
   */
  public SavingsAccount(double Balance) {
    super(Balance);
  }

  @Override
  public void performMonthlyMaintenance() {
    if (NumOfTransactions > 6) {
      feesDue = 14;
      super.performMonthlyMaintenance();
    }
  }
}
