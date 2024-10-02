package bank;

/**
 * public concrete class CheckingsAccount that extends Abstract class Account.
 */
public class CheckingAccount extends Account {
  /**
   * Constructor method for SavingsAccount.
   * @param Balance that you want to initalize CheckingAccount with.
   */
  public CheckingAccount(double Balance) {
    super(Balance);
  }

  @Override
  public boolean withdraw(double Amount) {
    boolean result = super.withdraw(Amount);
    if (balance < 100) {
      feesDue += 5;
    }
    return result;
  }

}
