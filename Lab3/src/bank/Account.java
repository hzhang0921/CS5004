package bank;

/**
 * public abstract class Account that uses the interface IAccount.
 */
public abstract class Account implements IAccount {
  double balance;
  double feesDue;
  int NumOfTransactions;

  /**
   * constructor for Account class.
   * @param starterAmount describes how much money you are initalizing with.
   */
  public Account(double starterAmount) {
    if (starterAmount < 0.01) {
      throw new IllegalArgumentException("Starter balance cannot be below $0.01");
    }
    NumOfTransactions = 0;
    feesDue = 0;
    this.balance = starterAmount;
  }

  /**
   * deposit method that adds money to the account.
   * @param Amount describes the amount of money you are depositing.
   */
  public void deposit(double Amount) {
    if (Amount < 0) {
      throw new IllegalArgumentException("Deposit amount cannot be negative");
    }
    this.balance += Amount;
  }

  /**
   * Withdraw method that removes money from your account.
   * @param Amount describes the amount of money you are withdrawing.
   * @return a boolean true or false describing if the method is successful.
   */
  public boolean withdraw(double Amount) {
    if (Amount > this.balance || Amount < 0) {
      return false;
    }
    this.balance -= Amount;
    NumOfTransactions += 1;
    return true;
  }

  public double getBalance() {
    return this.balance;
  }

  /**
   * Performs the monthly maintenance function by deducting fees and setting numtransctions to 0
   */
  public void performMonthlyMaintenance() {
    balance = balance - feesDue;
    NumOfTransactions = 0;
  }

  @Override
  public String toString() {
    return String.format("$%.2f", balance);
  }
}
