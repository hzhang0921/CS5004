package distance;

/** Point 3D is a class that handles 3 integer values - x, y, and z.
 *
 */
public class Point3D {

  private int x;
  private int y;
  private int z;


  /** basic constructor for point3D, using no method variables.
   *
   */
  public Point3D() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  /** overloaded constructor for point3D, initializes with 3 method variabels.
   *
   * @param x x variable
   * @param y y variable
   * @param z z variable
   */
  public Point3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /** method for copying one point into another. Used for defensive proramming.
   *
   * @param copy - secondary point to copy
   */
  public Point3D(Point3D copy) {
    this.x = copy.x;
    this.y = copy.y;
    this.z = copy.z;
  }

  /** gets the x variable.
   *
   * @return x value from point3D
   */
  public int getX() {
    return this.x;
  }

  /** gets the y variable.
   *
   * @return y value from point3D
   */
  public int getY() {
    return this.y;
  }

  /** gets the z variable.
   *
   * @return z value from point3D
   */
  public int getZ() {
    return this.z;
  }

  /** calculates the distance between two point3D objects.
   *
   * @param other is another 3Dpoint to use to calculate the distance between the two
   * @return a double that is the distance
   */
  public double distanceTo(Point3D other) {
    return Math.sqrt(Math.pow((other.getX() - this.getX()), 2)
        + Math.pow((other.getY() - this.getY()), 2)
        + Math.pow((other.getZ() - this.getZ()), 2));
  }

  /** Equals function checks if two points are equal to each other.
   *
   * @param obj refers to any object
   * @return a boolean stating whether true or false that the point3D objects are the same
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    Point3D other = (Point3D) obj;
    return this.x == other.x && this.y == other.y && this.z == other.z;
  }

  /** hashcode function used to generate hashcodes.
   *
   * @return an integer based on the private variables within point3D
   */
  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + this.x;
    result = 31 * result + this.y;
    result = 31 * result + this.z;
    return result;
  }


}
