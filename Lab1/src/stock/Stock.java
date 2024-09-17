package stock;

/**
 * This Class represents a stock. Stocks have Symbols, Full Names, and a Cost Basis.
 */

public class Stock {

  private String symbol;
  private String name;
  private double costBasis;
  private double currentPrice;

  /** Stock class that is used to present a stock.
   *
   * @param symbol is a string that is supposed to indicate the stock ticker
   * @param name is a string that represents the full name of the stock in question
   * @param costBasis is a double that represents the original cost of our stock
   */
  public Stock(String symbol, String name, double costBasis) {
    this.symbol = symbol;
    this.name = name;
    this.costBasis = costBasis;
    this.currentPrice = 0;
  }

  /** returns the short form symbol of the stock.
   * @return the Symbol of the stock
   */
  public String getSymbol() {
    return this.symbol;
  }


  /** returns the name string of the stock.
   * @return the name of the stock
   */
  public String getName() {
    return this.name;
  }

  /** returns the original decimal costBasis of the stock.
   * @return the costBasis of the stock
   */
  public double getCostBasis() {
    return this.costBasis;
  }

  /** returns the currentPrice decimal of the stock.
   * @return the currentPrice of the stock
   */
  public double getCurrentPrice() {
    return this.currentPrice;
  }

  /** updates the costBasis to the method variable.
   * @param costBasis sets the costBasis of a stock to a new variable costBasis
   */
  public void setCostBasis(double costBasis) {
    this.costBasis = costBasis;
  }

  /** updates the currentPrice to the method variable.
   * @param currentPrice sets the currentPrice of a stock to a new variable currentPrice
   */
  public void setCurrentPrice(double currentPrice) {
    this.currentPrice = currentPrice;
  }

  /** returns the fractional percentage of the change in stock.
   * @return the percentage in change of the stock
   */
  public double getChangePercent() {
    return ((this.currentPrice - this.costBasis) / this.costBasis);
  }

  /** returns a string that summarizes the stock.
   * @return a String, describing the stock's name, current price, and gain/loss
   */
  public String toString() {
    return String.format("%s Current Price: $ %.2f\n Gain/Loss: %.2f%%",
        this.name, this.currentPrice, this.getChangePercent() * 100);
  }
}
