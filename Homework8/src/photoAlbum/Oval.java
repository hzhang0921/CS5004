package photoAlbum;

/**
 * describes an Oval
 */
public class Oval extends AbstractShape {
  private double xRadius, yRadius;

  /**
   * Constructor for Oval
   * @param name of the oval
   * @param x
   * @param y
   * @param xRadius
   * @param yRadius
   * @param red
   * @param green
   * @param blue
   */
  public Oval(String name, double x, double y, double xRadius, double yRadius, double red, double green, double blue) {
    super(name, x, y, red, green, blue);
    this.xRadius = xRadius;
    this.yRadius = yRadius;
  }

  /**
   * @return the type of the shape
   */
  @Override
  public String getType() {
    return "oval";
  }

  /**
   * @return the description of the oval shape
   */
  @Override
  public String getDescription() {
    return String.format("Name: %s, Type: %s, Center: (%.1f,%.1f), X radius: %.1f, Y radius: %.1f, Color: (%.1f,%.1f,%.1f)",
        name, getType(), x, y, xRadius, yRadius, red, green, blue);
  }

  /**
   * Scales the shape by the factor given
   * @param factorX is the factor for x
   * @param factorY is the factor for y
   */
  @Override
  public void scale(double factorX, double factorY) {
    if (factorX <= 0 || factorY <= 0) {
      throw new IllegalArgumentException("Scale factors must be greater than 0");
    }
    this.xRadius *= factorX;
    this.yRadius *= factorY;
  }

  /**
   * @return a copy of a shape.
   */
  @Override
  public IShape copy() {
    return new Oval(name, x, y, xRadius, yRadius, red, green, blue);
  }
}