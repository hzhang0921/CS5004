package hw2;

/**
 * Java class corresponding to Recipient for the mail locker
 */
public class Recipient {
  private String firstName;
  private String lastName;
  private String email;

  /**
   * Constructor for Recipient.
   * @param firstName the first name of the recipient
   * @param lastName the last name of the recipient
   * @param email the email address of the recipient
   * @throws IllegalArgumentException if any argument is null or empty
   */
  public Recipient(String firstName, String lastName, String email) {
    if (firstName == null || firstName.isEmpty() ||
        lastName == null || lastName.isEmpty() ||
        email == null || email.isEmpty()) {
      throw new IllegalArgumentException("First name, last name, and email must not be null or empty.");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  /**
   * Set a new email for the recipient.
   * @param email the new email address
   * @throws IllegalArgumentException if the email is null or empty
   */
  public void setEmail(String email) {
    if (email == null || email.isEmpty()) {
      throw new IllegalArgumentException("Email must not be null or empty.");
    }
    this.email = email;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Recipient recipient = (Recipient) obj;
    return firstName.equals(recipient.firstName) &&
        lastName.equals(recipient.lastName) &&
        email.equals(recipient.email);
  }

  @Override
  public String toString() {
    return firstName + " " + lastName + " Email:" + email;
  }

  public String getFirstName() {return this.firstName;}

  public String getLastName() {return this.lastName;}

  public String getEmail() {return this.email;}
}

