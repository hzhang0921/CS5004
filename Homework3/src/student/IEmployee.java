package student;

/**
 * An interface representing the concept of Employees.
 */
public interface IEmployee {

  /** method for getting paid.
   * @return the employee's pay for the given period.
   */
  double getPayForThisPeriod();

  /** method for getting base salary.
   * @return the employee's base salary.
   */
  double getBaseSalary();

  /** method for raising percent.
   * @param raisePercent raises the employee's base salary from 0% (minimum) to 10% maximum.
   */
  void giveRaiseByPercent(double raisePercent);

  /** method for getting ID.
   * @return Returns employee ID.
   */
  String getID();

  /** method for getting name.
   * @return Returns employee name.
   */
  String getName();
}