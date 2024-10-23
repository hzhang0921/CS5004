package taxes;

import java.util.List;

/**
 * TaxableEntity Interface/Protocol/Contract
 * For this exam, you must provide the following constructors for the concrete classes:
 * public Person(String name, String taxID, Address address, int dependents )
 * public Company(String name, String taxID, Address address)
 *
 * Note that for EQUALITY: any taxable entity (of either type provided to you) is equal-to another
 * taxable entity (of either type provided to you) if their name is the same (case-insensitive)
 * and their taxID is the same (case-insensitive).
 */
public interface ITaxableEntity {
  /**
   * Answer/Return a safe copy of a list Addresses.
   * See the Collections class documentation online for ideas on this, if needed
   * @return  list of addresses.
   */
  List<Address> getAddresses();

  /**
   * Add an address to the collection of addresses a taxable entity has. Duplicate addresses
   * are NOT allowed. The Address class has been implemented and checks for equality already.
   * Your job is to ensure no duplicates are added to the entity's collection via this method
   * @param anAddress : Address
   */
  void addAddress(Address anAddress);

  /**
   * Answers/Returns the current total sum of taxes for ALL of the addresses owned by
   * the taxable entity. This method returns the raw floating point number, unmodified.
   * It does not round the value in any way, nor does it force a particular monetary
   * format.
   * Note that some taxes are assess for full year, others for half-year. This method does
   * NOT arbitrate or resolve those differences. It simply returns the "point in time" ask of
   * "what is owed RIGHT NOW?" if each property is assessed at its agreed-upon payment schedule
   * (be it full-year or half-year).
   *
   * Note subtype variations:
   * For PERSONS and other Taxable entities that are not CorporateTaxableEntity: There is the
   * concept of DEPENDENTS. A non-corporate taxable entity may have a maximum of 10 dependents
   * (minimum 0). Non-corporate taxable entities have a reduced tax liability by dividing the
   * total tax liability for the period by the number of dependents (zero dependents is treated as 1).
   * For example: if George Jetson owes 100 and has 4 dependents, his liability is 25. If George has
   * 2 dependents, his liability is 50. If George has zero or 1 dependent, his liability is 100.
   *
   * For COMPANIES and other CorporateTaxableEntities, there are no such deductions so the total
   * tax liability is never altered from the raw summation of taxes from addresses.
   * @return summation of all current taxes owed : double
   */
  double getCurrentTaxLiability();

  /**
   * Answers/Returns the tax identification for a taxable entity.
   * This value is a case-insensitive string with a length of exactly 9.
   * For this exam, those are the only constraints you need to worry about
   * (in other words, don't be concerned about the actual string data as long
   * as it meets the length == 9 criteria)
   * @return aString : String (must be length 9)
   */
  String getTaxID();

  /**
   * This method replaces a given (old) address - if it exists in the Entity's address collection -
   * with a new address. The old address is discarded and superseded by the new address.
   * If the old address does not exist, this method does nothing. No exceptions are thrown.
   *
   * @param oldAddress
   * @param newAddress
   */
  void updateAddress(Address oldAddress, Address newAddress);
}
