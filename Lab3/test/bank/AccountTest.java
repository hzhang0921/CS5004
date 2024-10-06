package bank;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
  private SavingsAccount savingsAccount;
  private CheckingAccount checkingAccount;

  @Before
  public void setUp() {
    savingsAccount = new SavingsAccount(100);
    checkingAccount = new CheckingAccount(100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidBalance() {
    new SavingsAccount(0); // Edge case: invalid starting balance
  }

  @Test
  public void testDepositPositiveAmount() {
    savingsAccount.deposit(50); // Simple test: valid deposit
    assertEquals(150, savingsAccount.getBalance(), 0.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testDepositNegativeAmount() {
    savingsAccount.deposit(-10); // Edge case: deposit negative amount
  }

  @Test
  public void testWithdrawSuccessful() {
    boolean result = savingsAccount.withdraw(50); // Simple test: valid withdrawal
    assertTrue(result);
    assertEquals(50, savingsAccount.getBalance(), 0.01);
  }

  @Test
  public void testWithdrawInsufficientFunds() {
    boolean result = savingsAccount.withdraw(200); // Edge case: withdrawing more than balance
    assertFalse(result);
    assertEquals(100, savingsAccount.getBalance(), 0.01);
  }

  @Test
  public void testToStringFormat() {
    assertEquals("$100.00", savingsAccount.toString()); // Simple test: check formatting of balance
  }

  @Test
  public void testPerformMonthlyMaintenance() {
    savingsAccount.performMonthlyMaintenance(); // Simple test: no fees due
    assertEquals(100, savingsAccount.getBalance(), 0.01);
  }
}
