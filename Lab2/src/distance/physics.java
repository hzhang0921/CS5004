package distance;

public class physics {

  public static double velocity(point3D one, point3D two, double elapsedTime) {
    if (elapsedTime <= 0) {
      throw new IllegalArgumentException("Elapsed time must be greater than 0");
    }
    return (one.distanceTo(two)) / elapsedTime;
  }

  public static void main(String [] args) {
    try {
      point3D one = new point3D();
      point3D two = new point3D(1, 1, 1);
      System.out.println("Displacement = " + one.distanceTo(two));
      double velocity = physics.velocity(one, two, 5);
      System.out.println("Prof. Keith is on the move! His velocity =" + velocity);
      velocity = physics.velocity(one, two, 0);
      System.out.println("Velocity =" + velocity);
    }
    catch(IllegalArgumentException e) {
      System.out.println("Encountered an error: " + e.getMessage());
    }
  }
}
