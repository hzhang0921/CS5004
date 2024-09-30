package hw2;

/** MailItem Class represents any single mail item from
 *
 */
public class MailItem {
  private final int width;
  private final int height;
  private final int depth;
  private final Recipient recipient;

  /**
   * Constructor for MailItem.
   * @param width the width of the mail item
   * @param height the height of the mail item
   * @param depth the depth of the mail item
   * @param recipient the recipient of the mail item
   * @throws IllegalArgumentException if any dimension is less than 1 or recipient is null
   */
  public MailItem(int width, int height, int depth, Recipient recipient) {
    if (width < 1 || height < 1 || depth < 1) {
      throw new IllegalArgumentException("Dimensions must be greater than or equal to 1.");
    }
    if (recipient == null) {
      throw new IllegalArgumentException("Recipient must not be null.");
    }
    this.width = width;
    this.height = height;
    this.depth = depth;
    this.recipient = new Recipient(recipient.getFirstName(), recipient.getLastName(), recipient.getEmail());
  }

  /**
   * Get the recipient of the mail item.
   * @return the recipient of the mail item
   */
  public Recipient getRecipient() {
    return recipient;
  }

  /**
   * Gets the width of the MailItem.
   * @return integer width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Gets the height of the MailItem.
   * @return integer height
   */
  public int getHeight() {
    return height;
  }

  /**
   * Gets the depth of the MailItem.
   * @return integer depth
   */
  public int getDepth() {
    return depth;
  }
}