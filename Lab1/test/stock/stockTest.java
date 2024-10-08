

package stock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** stockTest class used to test Stock.
 * Creates two example stocks Apple, Nvidia to test with
 */
public class stockTest {
  private Stock Apple;
  private Stock Nvidia;

  /** setup to create APPL and NVIDIA.
   * using constructor class sets up two different classes
   */
  @org.junit.Before
  public void setup() {
    this.Apple = new Stock("APPL", "Apple Computers", 180.0);
    this.Nvidia = new Stock("NVDA", "Nvidia", 80.0);
  }

  /** tests getSymbol to make sure we are returning what is expected.
   * uses AssertEquals to confirm symbol is returning as expected
   */
  @org.junit.Test
  public void test_getSymbol() {
    Assert.assertEquals("APPL", this.Apple.getSymbol());
    Assert.assertEquals("NVDA", this.Nvidia.getSymbol());
  }

  /** tests getName.
   * uses AssertEquals to confirm Name
   */
  @org.junit.Test
  public void test_getName() {
    Assert.assertEquals("Apple Computers", this.Apple.getName());
    Assert.assertEquals("Nvidia", this.Nvidia.getName());
  }

  /** tests getCostBasis.
   * uses assertEquals to confirm costBasis
   */
  @org.junit.Test
  public void test_getCostBasis() {
    Assert.assertEquals(180.0, this.Apple.getCostBasis(), 0.0);
    Assert.assertEquals(80.0, this.Nvidia.getCostBasis(), 0.0);
  }

  /** tests setCostBasis then confirms with the getCostBasis.
   * uses assertEquals to confirm results
   */
  @org.junit.Test
  public void test_04() {
    this.Apple.setCostBasis(170.0);
    Assert.assertEquals(170.0, this.Apple.getCostBasis(), 0.0);
    this.Nvidia.setCostBasis(100.0);
    Assert.assertEquals(100.0, this.Nvidia.getCostBasis(), 0.0);
  }

  /** tests setCurrentPrice and getCurrentPrice.
   * uses AssertEquals to confirm results
   */
  @Test
  public void test_05() {
    this.Apple.setCurrentPrice(220.0);
    Assert.assertEquals(220.0, this.Apple.getCurrentPrice(), 0.0);
    this.Nvidia.setCurrentPrice(90.0);
    Assert.assertEquals(90.0, this.Nvidia.getCurrentPrice(), 0.0);
  }

  /** tests getChangePercent by setting up predetermined values for Cost basis and CurrentPrice.
   * Checks for both a positive and a negative ChangePercentage
   */
  @Test
  public void test_percentageChange() {
    this.Apple.setCostBasis(170.0);
    this.Nvidia.setCostBasis(100.0);
    this.Apple.setCurrentPrice(220.0);
    this.Nvidia.setCurrentPrice(90.0);
    Assert.assertEquals(0.29411764705882354, this.Apple.getChangePercent(), 0.1);
    Assert.assertEquals(-0.1, this.Nvidia.getChangePercent(), 0.1);
  }

  /** just checks to see if toString is working as expected.
   * uses predetermined values to confirm that the toString is working properly
   */
  @Test
  public void test_toString() {
    this.Apple.setCostBasis(170.0);
    this.Apple.setCurrentPrice(220.0);
    Assert.assertEquals("Apple Computers Current Price: $ 220.00\n "
        + "Gain/Loss: 29.41%", this.Apple.toString());
  }
}
