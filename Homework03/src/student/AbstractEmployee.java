package student;

abstract class AbstractEmployee implements IEmployee {
  protected final String name;
  protected final String id;

  protected AbstractEmployee(String name, String id) throws IllegalArgumentException {
    if (name == null || id == null || name.trim().isEmpty() || id.trim().isEmpty()) {
      throw new IllegalArgumentException("Name and ID must not be null, empty, or whitespace");
    }
    this.name = name;
    this.id = id;
  }

  // copy constructor
  protected AbstractEmployee(AbstractEmployee other) {
    this.name = new String(other.name);
    this.id = new String(other.id);
  }

  @Override
  public abstract double getPayForThisPeriod();

  @Override
  public abstract double getBaseSalary();

  @Override
  public abstract void giveRaiseByPercent(double raisePercent);

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getID() {
    return id;
  }

  @Override
  public String toString() {
    return String.format("Name: %s%nID: %s%nBase Salary: $%.2f",
        this.getName(),
        this.getID(),
        this.getBaseSalary());
  }
}
