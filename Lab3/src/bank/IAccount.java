package bank;

/**
 * Interface for the Account Abstract Class.
 */
public interface IAccount {
  /**
   * Setup for IAccount method deposit.
   * @param amount describes the amount of money you are depositing.
   */
  void deposit(double amount);

  /**
   * Setup for Iaccount method withdrawal.
   * @param amount describes the amount of money you are withdrawing.
   * @return a boolean saying if the withdrawal is successful or not.
   */
  boolean withdraw(double amount);

  /**
   * Setup for the Iaccount method geBalance.
   * @return the amount on the account.
   */
  double getBalance();

  /**
   * Setup for the Iaccount performMonthlyMaintenance method.
   */
  void performMonthlyMaintenance();
}