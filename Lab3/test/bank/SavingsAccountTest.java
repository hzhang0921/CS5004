package bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SavingsAccountTest {
  private SavingsAccount savingsAccount;

  @Before
  public void setUp() {
    savingsAccount = new SavingsAccount(200);
  }

  @Test
  public void testConstructorInitialBalance() {
    assertEquals(200, savingsAccount.getBalance(), 0.01); // Simple test: valid initial balance
  }

  @Test
  public void testMonthlyMaintenanceNoFee() {
    savingsAccount.performMonthlyMaintenance(); // Simple test: no fees due
    assertEquals(200, savingsAccount.getBalance(), 0.01);
  }

  @Test
  public void testMonthlyMaintenanceWithFee() {
    for (int i = 0; i < 7; i++) { // Perform 7 transactions
      savingsAccount.withdraw(10);
    }
    savingsAccount.performMonthlyMaintenance(); // Edge case: more than 6 transactions, fee applies
    assertEquals(116, savingsAccount.getBalance(), 0.01); // 200 - 70 (withdrawals) - 14 (fee)
  }
}
