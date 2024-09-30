package hw2;

public class Locker {
  private final int maxWidth;
  private final int maxHeight;
  private final int maxDepth;
  private MailItem mailItem;

  /**
   * Constructor for Locker.
   * @param maxWidth the maximum width of the locker
   * @param maxHeight the maximum height of the locker
   * @param maxDepth the maximum depth of the locker
   * @throws IllegalArgumentException if any dimension is less than 1
   */
  public Locker(int maxWidth, int maxHeight, int maxDepth) {
    if (maxWidth < 1 || maxHeight < 1 || maxDepth < 1) {
      throw new IllegalArgumentException("Locker dimensions must be greater than or equal to 1.");
    }
    this.maxWidth = maxWidth;
    this.maxHeight = maxHeight;
    this.maxDepth = maxDepth;
    this.mailItem = null;  // Shouldn't be anything in the locker by default
  }

  /**
   * Adds a mail item to the locker if it fits and the locker is not occupied.
   * @param mailItem the mail item to be added
   */
  public void addMail(MailItem mailItem) {
    if (this.mailItem != null) {
      return; // Checks if there is a valid mailitem
    }
    if (mailItem.getWidth() > maxWidth || mailItem.getHeight() > maxHeight || mailItem.getDepth() > maxDepth) {
      return; // Checks if mailItem is too big for the locker
    }
    this.mailItem = mailItem;
  }

  /**
   * Picks up and removes the mail item from the locker if the recipient matches.
   * @param recipient the recipient who is picking up the mail
   * @return the mail item if successfully picked up, otherwise null
   */
  public MailItem pickupMail(Recipient recipient) {
    if (mailItem == null || !mailItem.getRecipient().equals(recipient)) {
      return null;  // Either no mail or recipient doesn't match
    }
    MailItem itemToReturn = this.mailItem;
    this.mailItem = null;  // Empty the locker, there's nothing in there.
    return itemToReturn;
  }

  public MailItem getMailItem() {
    return mailItem;
  }
}