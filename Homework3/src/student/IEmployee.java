package student;

/**
 * An interface representing the concept of Employees.
 */
public interface IEmployee {

  /** interface function to return pay period.
   * @return the employee's pay for the given period.
   */
  double getPayForThisPeriod();

  /** interface function to return base salary.
   * @return the employee's base salary.
   */
  double getBaseSalary();

  /** interface function to increase salary.
   * @param raisePercent raises the employee's base salary from 0% (minimum) to 10% maximum.
   */
  void giveRaiseByPercent(double raisePercent);

  /** interface function to return ID.
   * @return Returns employee ID.
   */
  String getID();

  /** interface function to return name.
   * @return Returns employee name.
   */
  String getName();
}