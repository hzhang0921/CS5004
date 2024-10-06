package bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckingAccountTest {
  private CheckingAccount checkingAccount;

  @Before
  public void setUp() {
    checkingAccount = new CheckingAccount(200);
  }

  @Test
  public void testConstructorInitialBalance() {
    assertEquals(200, checkingAccount.getBalance(), 0.01); //valid initial balance
  }

  @Test
  public void testWithdrawBelowMinimumBalance() {
    checkingAccount.withdraw(150); // withdrawal brings balance below $100
    assertEquals(50, checkingAccount.getBalance(), 0.01);
    assertEquals(5, checkingAccount.feesDue, 0.01); // $5 fee applied
  }

  @Test
  public void testWithdrawWithoutFee() {
    checkingAccount.withdraw(50); // no fee since balance stays above $100
    assertEquals(150, checkingAccount.getBalance(), 0.01);
    assertEquals(0, checkingAccount.feesDue, 0.01); // No fee applied
  }

  @Test
  public void testPerformMonthlyMaintenanceWithFees() {
    checkingAccount.withdraw(150); // Bring the balance below 100
    checkingAccount.performMonthlyMaintenance(); // Fees should be deducted here
    assertEquals(45, checkingAccount.getBalance(), 0.01); // 50 - 5 (fee)
  }

  @Test
  public void testWithdrawNegativeAmount() {
    assertFalse(checkingAccount.withdraw(-10)); // Edge case: invalid negative withdrawal
    assertEquals(200, checkingAccount.getBalance(), 0.01); // Nothing should have changed
  }
}
