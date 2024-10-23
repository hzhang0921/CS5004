package taxes;

import java.util.List;

/**
 * Corporate Taxable Entities are organizations (non-profits, corporations, etc.) that have employees.
 * This is a sub-type of Taxable Entities.
 * We can add employees (and never fire them! :-) )
 */
public interface ICorporateTaxableEntity extends ITaxableEntity {
  /**
  This method answers/returns a safe copy of the list of people employed by the entity.
   */
  List<Person> getEmployees();

  /**
   * This method adds a Person to the entity as an employee. We're effectively "hiring" a person.
   *    Note: a Person CANNOT be hired more than once!
   * @param p
   */
  void addEmployee(Person p);
}
