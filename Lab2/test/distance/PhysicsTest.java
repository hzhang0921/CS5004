package distance;

import org.junit.Test;
import static org.junit.Assert.*;

public class PhysicsTest {

  @Test
  public void testVelocityCalculation() {
    // Create two points
    Point3D point1 = new Point3D(0, 0, 0);
    Point3D point2 = new Point3D(3, 4, 0);  // Distance should be 5.0

    // Call the velocity method with elapsed time 2 seconds
    double elapsedTime = 2.0;
    double expectedVelocity = 5.0 / 2.0;  // distance = 5, time = 2 -> velocity = 2.5

    // Assert that the velocity is correctly calculated
    assertEquals(expectedVelocity, Physics.velocity(point1, point2, elapsedTime), 0.0001);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVelocityWithZeroElapsedTime() {
    // Create two points
    Point3D point1 = new Point3D(0, 0, 0);
    Point3D point2 = new Point3D(3, 4, 0);

    // Call the velocity method with elapsed time 0 seconds (should throw an exception)
    Physics.velocity(point1, point2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVelocityWithNegativeElapsedTime() {
    // Create two points
    Point3D point1 = new Point3D(0, 0, 0);
    Point3D point2 = new Point3D(3, 4, 0);

    // Call the velocity method with negative elapsed time (should throw an exception)
    Physics.velocity(point1, point2, -5);
  }
}