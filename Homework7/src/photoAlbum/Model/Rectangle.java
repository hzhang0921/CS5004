package photoAlbum.Model;

public class Rectangle extends AbstractShape {
  private double width, height;

  public Rectangle(String name, double x, double y, double width, double height, double red, double green, double blue) {
    super(name, x, y, red, green, blue);
    this.width = width;
    this.height = height;
  }

  @Override
  public String getType() {
    return "rectangle";
  }

  @Override
  public String getDescription() {
    return String.format("Name: %s, Type: %s, Min corner: (%.1f,%.1f), Width: %.1f, Height: %.1f, Color: (%.1f,%.1f,%.1f)",
        name, getType(), x, y, width, height, red, green, blue);
  }

  @Override
  public void scale(double factorX, double factorY) {
    if (factorX <= 0 || factorY <= 0) {
      throw new IllegalArgumentException("Scale factors must be greater than 0");
    }
    this.width *= factorX;
    this.height *= factorY;
  }

  @Override
  public IShape copy() {
    return new Rectangle(name, x, y, width, height, red, green, blue);
  }

  public double getWidth() {
    return this.width;
  }

  public double getHeight() {
    return this.height;
  }
}