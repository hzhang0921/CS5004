package photoAlbum;

/**
 * Interfacae for shapes
 */
public interface IShape {

  /**
   * Gets the name of the shape.
   *
   * @return The name of the shape.
   */
  String getName();

  /**
   * Gets the type of the shape.
   *
   * @return The type of shape, like "oval" or "rectangle" or now
   */
  String getType();

  /**
   * Gives a detailed description of the shape.
   *
   * @return A description for the shape
   */
  String getDescription();

  /**
   * Moves the shape to a new spot.
   *
   * @param x The new x-coordinate
   * @param y The new y-coordinate
   */
  void move(double x, double y);

  /**
   * Changes the color of the shape.
   *
   * @param red   red the shape should be (from 0.0 to 1.0).
   * @param green green the shape should be (from 0.0 to 1.0).
   * @param blue  blue the shape should be (from 0.0 to 1.0).
   *
   */
  void changeColor(double red, double green, double blue);

  /**
   * Makes the shape bigger or smaller.
   *
   * @param factorX How much to stretch or shrink it horizontally.
   * @param factorY How much to stretch or shrink it vertically.
   */
  void scale(double factorX, double factorY);

  /**
   * Makes an exact copy of the shape.
   *
   * @return a copy of the shape
   */
  IShape copy();

  /**
   * Gets the x-coordinate of the shape's position.
   *
   * @return The x-coordinate of where the shape is.
   */
  double getX();

  /**
   * Gets the y-coordinate of the shape's position.
   *
   * @return The y-coordinate of where the shape is.
   */
  double getY();
}