package distance;

import org.junit.Test;

import static org.junit.Assert.*;

public class Point3DTest {

  @Test
  public void testDefaultConstructor() {
    Point3D point = new Point3D();
    assertEquals(0, point.getX());
    assertEquals(0, point.getY());
    assertEquals(0, point.getZ());
  }

  @Test
  public void testOverloadedConstructor() {
    Point3D point = new Point3D(1, 2, 3);
    assertEquals(1, point.getX());
    assertEquals(2, point.getY());
    assertEquals(3, point.getZ());
  }

  @Test
  public void testCopyConstructor() {
    Point3D original = new Point3D(5, 10, 15);
    Point3D copy = new Point3D(original);
    assertEquals(5, copy.getX());
    assertEquals(10, copy.getY());
    assertEquals(15, copy.getZ());

    // Ensure that modifying the original doesn't affect the copy
    original = new Point3D(7, 8, 9);
    assertNotEquals(original.getX(), copy.getX());
    assertNotEquals(original.getY(), copy.getY());
    assertNotEquals(original.getZ(), copy.getZ());
  }

  @Test
  public void testDistanceTo() {
    Point3D point1 = new Point3D(0, 0, 0);
    Point3D point2 = new Point3D(3, 4, 0);
    double distance = point1.distanceTo(point2);
    assertEquals(5.0, distance, 0.0001);  // 3-4-5 triangle

    Point3D point3 = new Point3D(1, 2, 2);
    Point3D point4 = new Point3D(4, 6, 6);
    double distance2 = point3.distanceTo(point4);
    assertEquals(6.403, distance2, 0.01); // calculated by hand using the distance formula
  }

  @Test
  public void testEqualsSameObject() {
    Point3D point = new Point3D(1, 2, 3);
    assertEquals(point, point);  // Should be true when comparing the same Point3D
  }

  @Test
  public void testEqualsDifferentObjectSameValues() {
    Point3D point1 = new Point3D(1, 2, 3);
    Point3D point2 = new Point3D(1, 2, 3);
    assertEquals(point1, point2);  // Should be true since values are the same
  }

  @Test
  public void testEqualsDifferentValues() {
    Point3D point1 = new Point3D(1, 2, 3);
    Point3D point2 = new Point3D(4, 5, 6);
    assertNotEquals(point1, point2);  // Should be false since values are different
  }

  @Test
  public void testEqualsNull() {
    Point3D point = new Point3D(1, 2, 3);
    assertNotEquals(null, point);  // Should be false when compared with null
  }

  @Test
  public void testEqualsDifferentClass() {
    Point3D point = new Point3D(1, 2, 3);
    String notAPoint = "Not a Point";
    assertNotEquals(point, notAPoint);  // Should be false when compared to a different class
  }

  @Test
  public void testHashCode() {
    Point3D point1 = new Point3D(1, 2, 3);
    Point3D point2 = new Point3D(1, 2, 3);
    assertEquals(point1.hashCode(), point2.hashCode());  // Equal objects must have the same hashCode

    Point3D point3 = new Point3D(4, 5, 6);
    assertNotEquals(point1.hashCode(), point3.hashCode());  // Different objects should have different hashCodes
  }

  @Test
  public void testDistanceToItself() {
    Point3D point = new Point3D(1, 2, 3);
    assertEquals(0.0, point.distanceTo(point), 0.0001);  // Distance to itself should be 0
  }
}