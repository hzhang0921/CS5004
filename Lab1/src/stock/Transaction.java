package stock;

/**
 * This is a simple Transaction class to simulate equities transactions.
 */
public class Transaction {
  private StringBuffer transactionLog;
  private Stock stock;

  /**
   * This is the constructor for the Transaction class. We use a StringBuffer to log
   * transactions over the course of time.
   */
  public Transaction() {
    this.transactionLog = new StringBuffer();
    this.transactionLog.append("Transaction Ledger");
  }

  /**
   * This method initiates a stock transaction. The transaction is set up but not executed.
   * @param stock
   */
  public void request(Stock stock ) {
    this.stock = stock;
  }

  /**
   * This method cancels the transaction.
   */
  public void cancel() {
    this.stock = null;
  }

  /**
   * This method executes the transaction if there is a stock that has been "queued" by a previous
   * request.
   */
  public void execute() {
    if (this.stock != null ) {
      this.transactionLog.append( "\n-------\n" + "SELL " + this.stock.toString());
    }
  }

  /**
   * This is our override of the toString method.
   * @return String representation of the transaction log.
   */
  @Override
  public String toString() {
    return this.transactionLog.toString();
  }

  /**
   * Entry point for our stock transaction simulation.
   * @param args
   */

  public static void main(String [] args) {
    Transaction transaction = new Transaction();
    Stock stock = new Stock("AAPL", "Apple Computer", 192.20);
    stock.setCurrentPrice(202.12);
    transaction.request(stock);
    transaction.execute();

    stock.setCurrentPrice(252.12);
    transaction.request(stock);
    transaction.cancel();
    stock = new Stock("GOOG", "Alphabet", 3100.00);
    stock.setCurrentPrice(3012.12);
    transaction.request(stock);
    transaction.execute();

    System.out.println(transaction);
  }
}
