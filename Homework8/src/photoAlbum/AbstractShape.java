package photoAlbum;

public abstract class AbstractShape implements IShape {
  protected String name;
  protected double x, y; // Position
  protected double red, green, blue; // Color

  /**
   * Constructor for AbstractShape
   * @param name
   * @param x
   * @param y
   * @param red
   * @param green
   * @param blue
   */
  public AbstractShape(String name, double x, double y, double red, double green, double blue) {
    if (red < 0.0 || red > 1.0 || green < 0.0 || green > 1.0 || blue < 0.0 || blue > 1.0) {
      throw new IllegalArgumentException("Color values must be between 0.0 and 1.0");
    }
    this.name = name;
    this.x = x;
    this.y = y;
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  /**
   * @return the name of the shape
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * @param x is the x coordinate of where to move the shape
   * @param y is the y coordinate of where to move the shape
   */
  @Override
  public void move(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * changes the color of the shape
   * @param red is the double value of red (0.0 - 0.1)
   * @param green is the double value of green (0.0 - 0.1)
   * @param blue is the double value of blue (0.0 - 0.1)
   */
  @Override
  public void changeColor(double red, double green, double blue) {
    if (red < 0.0 || red > 1.0 || green < 0.0 || green > 1.0 || blue < 0.0 || blue > 1.0) {
      throw new IllegalArgumentException("Color values must be between 0.0 and 1.0");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public String toString() {
    return String.format("Name: %s, Type: %s, Position: (%.1f,%.1f), Color: (%.1f,%.1f,%.1f)",
        name, getType(), x, y, red, green, blue);
  }

  // Abstract method for subclasses to implement specific details
  @Override
  public abstract String getType();

  @Override
  public abstract String getDescription();

  @Override
  public abstract IShape copy();

  @Override
  public double getX() {
    return this.x;
  }

  @Override
  public double getY() {
    return this.y;
  }
}