package bank;

public interface IAccount {
  void deposit(double amount);
  boolean withdraw(double amount);
  double getBalance();
  void performMonthlyMaintenance();
}