package stock;

/**
 * This Class represents a stock. Stocks have Symbols, Full Names, and a Cost Basis.
 */

public class Stock {

  private String symbol;
  private String name;
  private double costBasis;
  private double currentPrice;

  public Stock(String symbol, String name, double costBasis) {
    this.symbol = symbol;
    this.name = name;
    this.costBasis = costBasis;
    this.currentPrice = 0;
  }

  /**
   * @return the Symbol of the stock
   */
  public String getSymbol() {
    return this.symbol;
  }


  /**
   * @return the name of the stock
   */
  public String getName() {
    return this.name;
  }

  /**
   * @return the costBasis of the stock
   */
  public double getCostBasis() {
    return this.costBasis;
  }

  /**
   * @return the currentPrice of the stock
   */
  public double getCurrentPrice() {
    return this.currentPrice;
  }

  /**
   * @param costBasis sets the costBasis of a stock to a new variable costBasis
   */
  public void setCostBasis(double costBasis) {
    this.costBasis = costBasis;
  }

  /**
   * @param currentPrice sets the currentPrice of a stock to a new variable currentPrice
   */
  public void setCurrentPrice(double currentPrice) {
    this.currentPrice = currentPrice;
  }

  /**
   * @return the percentage in change of the stock
   */
  public double getChangePercent() {
    return ((this.currentPrice - this.costBasis) / this.costBasis);
  }

  /**
   * @return a String, describing the stock's name, current price, and gain/loss
   */
  public String toString() {
    return String.format("%s Current Price: $ %.2f\n Gain/Loss: %.2f%%", this.name, this.currentPrice, this.getChangePercent() * 100);
  }
}
