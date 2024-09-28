package distance;

/** Class for doing physics calculations alongside point3D.
 *
 */
public class Physics {

  /** Calculates the velocity given two points and an elapsedTime.
   *
   * @param one point3D object to calculate starting
   * @param two point3D object to calculate ending
   * @param elapsedTime is a double that represents the amount of time passed.
   * @return a double that represents the velocity
   */
  public static double velocity(Point3D one, Point3D two, double elapsedTime) {
    if (elapsedTime <= 0) {
      throw new IllegalArgumentException("Elapsed time must be greater than 0");
    }
    return (one.distanceTo(two)) / elapsedTime;
  }

  /** main class.
   *
   * @param args default argument for main class
   */
  public static void main(String [] args) {
    try {
      Point3D one = new Point3D();
      Point3D two = new Point3D(1, 1, 1);
      System.out.println("Displacement = " + one.distanceTo(two));
      double velocity = Physics.velocity(one, two, 0);
      System.out.println("Prof. Keith is on the move! His velocity = " + velocity);
      velocity = Physics.velocity(one, two, 5);
      System.out.println("Velocity =" + velocity);
    } catch (IllegalArgumentException e) {
      System.out.println("Encountered an error: " + e.getMessage());
    }
  }
}
