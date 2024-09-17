package distance;

import java.lang.Math;

public class point3D {

  private int x;
  private int y;
  private int z;


  public int hashCode() {
    return 1;
  }

  public point3D() {
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }

  public point3D(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public point3D(point3D copy) {
    this.x = copy.x;
    this.y = copy.y;
    this.z = copy.z;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getZ() {
    return this.z;
  }

  public double distanceTo(point3D other) {
    return Math.sqrt(Math.pow((other.getX() - this.getX()), 2) + Math.pow((other.getY() - this.getY()), 2) + Math.pow((other.getZ() - this.getZ()), 2));
  }

  public boolean equals(point3D other) {
    return this.getX() == other.getX() && this.getY() == other.getY() && this.getZ() == other.getZ();
  }


}
