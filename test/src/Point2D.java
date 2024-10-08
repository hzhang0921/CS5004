public class Point2D {
  private int x, y;
  public Point2D(int x, int y) {
    this.x = x;
    this.y = y;
  }
  public int getX() { return this.x;}
  public int getY() { return this.y;}

  public void setX(int x) {this.x = x;}
  public void setY(int y) {this.y = y;}

  public static void main(String [] args) {
    Point2D p1 = new Point2D(1,2);
    Point2D p2 = p1;
    p2.setY(5);
    p2.setX(10);
    System.out.println(p1.getX());
  }
}