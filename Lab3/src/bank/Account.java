package bank;

public abstract class Account implements IAccount{
  protected double balance;

  public Account(double starterAmount) {
    try {
      if (starterAmount < 0.01) {
        throw new IllegalArgumentException();
      }
      this.balance = starterAmount;
    } catch (IllegalArgumentException e){
        System.err.println("Starter balance cannot be below $0.01");
    }
  }

  public void deposit(double Amount) {
    try {
      if (Amount < 0) {
        throw new IllegalArgumentException();
      }
      this.balance += Amount;
    } catch (IllegalArgumentException e){
      System.err.println("Deposit amount cannot be negative");
    }
  }

  public boolean withdraw(double Amount) {
    if (Amount > this.balance || Amount < 0) {
      return false;
    }
    this.balance -= Amount;
    return true;
  }

  public double getBalance() {
    return this.balance;
  }

  public




}
