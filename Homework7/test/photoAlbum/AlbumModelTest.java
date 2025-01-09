package photoAlbum;

import org.junit.Before;
import org.junit.Test;
import photoAlbum.Model.AlbumModel;
import photoAlbum.Model.IAlbumModel;
import photoAlbum.Model.IShape;
import photoAlbum.Model.ISnapshot;

import java.util.List;

import static org.junit.Assert.*;

public class AlbumModelTest {
  private IAlbumModel album;

  @Before
  public void setUp() {
    album = new AlbumModel();
  }

  @Test
  public void testAddAndRetrieveShape() {
    album.addShape("R", "rectangle");
    IShape rectangle = album.getShape("R");

    assertEquals("R", rectangle.getName());
    assertEquals("rectangle", rectangle.getType());
    assertEquals("Name: R, Type: rectangle, Min corner: (0.0,0.0), Width: 50.0, Height: 50.0, Color: (1.0,0.0,0.0)", rectangle.getDescription());
  }

  @Test
  public void testModifyRectangle() {
    album.addShape("R", "rectangle");
    IShape rectangle = album.getShape("R");

    rectangle.move(200, 200);
    rectangle.changeColor(0.0, 1.0, 0.0);
    rectangle.scale(0.5, 2.0);

    assertEquals("Name: R, Type: rectangle, Min corner: (200.0,200.0), Width: 25.0, Height: 100.0, Color: (0.0,1.0,0.0)", rectangle.getDescription());
  }

  @Test
  public void testAddOvalAndMove() {
    album.addShape("O", "oval");
    IShape oval = album.getShape("O");

    assertEquals("Name: O, Type: oval, Center: (0.0,0.0), X radius: 25.0, Y radius: 25.0, Color: (1.0,0.0,0.0)", oval.getDescription());

    oval.move(500, 100);
    assertEquals("Name: O, Type: oval, Center: (500.0,100.0), X radius: 25.0, Y radius: 25.0, Color: (1.0,0.0,0.0)", oval.getDescription());
  }

  @Test
  public void testTakeSnapshots() {
    album.addShape("R", "rectangle");
    album.addShape("O", "oval");

    IShape rectangle = album.getShape("R");
    rectangle.move(200, 200);
    rectangle.changeColor(1.0, 0.0, 0.0);
    rectangle.scale(1.0, 2.0);

    IShape oval = album.getShape("O");
    oval.move(500, 100);
    oval.changeColor(0.0, 0.0, 1.0);

    // Take first snapshot
    String firstSnapshotId = album.takeSnapshot("After first selfie");
    assertNotNull(firstSnapshotId);

    rectangle.move(100, 300);
    rectangle.changeColor(0.0, 1.0, 0.0);
    rectangle.scale(0.5, 1.0);

    // Take second snapshot
    String secondSnapshotId = album.takeSnapshot("2nd selfie");
    assertNotNull(secondSnapshotId);

    oval.move(500, 400);

    // Take third snapshot
    String thirdSnapshotId = album.takeSnapshot("");

    // Remove rectangle and take fourth snapshot
    album.removeShape("R");
    String fourthSnapshotId = album.takeSnapshot("Selfie after removing the rectangle from the picture");

    // Verify snapshots
    List<ISnapshot> snapshots = album.getSnapshots();
    assertEquals(4, snapshots.size());

    assertEquals("After first selfie", snapshots.get(0).getDescription());
    assertEquals("2nd selfie", snapshots.get(1).getDescription());
    assertEquals("", snapshots.get(2).getDescription());
    assertEquals("Selfie after removing the rectangle from the picture", snapshots.get(3).getDescription());

    // Print snapshots
    String printedSnapshots = album.printSnapshots();
    System.out.println(printedSnapshots);
  }

  @Test
  public void testResetAlbum() {
    album.addShape("R", "rectangle");
    album.addShape("O", "oval");

    album.takeSnapshot("Before reset");
    assertEquals(1, album.getSnapshots().size());

    album.reset();
    assertEquals(0, album.getSnapshots().size());
    assertThrows(IllegalArgumentException.class, () -> album.getShape("R"));
    assertThrows(IllegalArgumentException.class, () -> album.getShape("O"));
  }

  @Test
  public void testAddInvalidShape() {
    // Test adding a shape with a duplicate name
    album.addShape("R", "rectangle");
    assertThrows(IllegalArgumentException.class, () -> album.addShape("R", "rectangle"));

    // Test adding a shape with an invalid type
    assertThrows(IllegalArgumentException.class, () -> album.addShape("Invalid", "circle"));
  }

  @Test
  public void testChangeColorOutOfBounds() {
    album.addShape("R", "rectangle");
    IShape rectangle = album.getShape("R");

    // Valid color change
    rectangle.changeColor(0.5, 0.5, 0.5);
    assertEquals("Name: R, Type: rectangle, Min corner: (0.0,0.0), Width: 50.0, Height: 50.0, Color: (0.5,0.5,0.5)", rectangle.getDescription());

    // Invalid color change ( less than 0 or outside 1.0 )
    assertThrows(IllegalArgumentException.class, () -> rectangle.changeColor(-0.1, 0.5, 0.5));
    assertThrows(IllegalArgumentException.class, () -> rectangle.changeColor(1.1, 0.5, 0.5));
  }

  @Test
  public void testMoveInvalidCoordinates() {
    album.addShape("O", "oval");
    IShape oval = album.getShape("O");

    // Valid move
    oval.move(100.0, 100.0);
    assertEquals("Name: O, Type: oval, Center: (100.0,100.0), X radius: 25.0, Y radius: 25.0, Color: (1.0,0.0,0.0)", oval.getDescription());

    //(negative coordinates are allowed, so no exception expected)
    oval.move(-100.0, -50.0);
    assertEquals("Name: O, Type: oval, Center: (-100.0,-50.0), X radius: 25.0, Y radius: 25.0, Color: (1.0,0.0,0.0)", oval.getDescription());
  }

  @Test
  public void testScaleInvalidFactors() {
    album.addShape("R", "rectangle");
    IShape rectangle = album.getShape("R");

    // Valid scaling ( greater than 0 )
    rectangle.scale(2.0, 0.5);
    assertEquals("Name: R, Type: rectangle, Min corner: (0.0,0.0), Width: 100.0, Height: 25.0, Color: (1.0,0.0,0.0)", rectangle.getDescription());

    // Invalid scaling
    assertThrows(IllegalArgumentException.class, () -> rectangle.scale(-1.0, 2.0));
    assertThrows(IllegalArgumentException.class, () -> rectangle.scale(0.0, 0.5));
  }

  @Test
  public void testGetShapeInvalidName() {
    album.addShape("R", "rectangle");

    // Valid shape retrieval
    IShape rectangle = album.getShape("R");
    assertNotNull(rectangle);

    assertThrows(IllegalArgumentException.class, () -> album.getShape("Invalid"));
  }

  @Test
  public void testRemoveShapeInvalidName() {
    album.addShape("R", "rectangle");

    // Valid removal
    album.removeShape("R");

    // Invalid removal
    assertThrows(IllegalArgumentException.class, () -> album.removeShape("R"));
  }

  @Test
  public void testTakeSnapshotWithEmptyAlbum() {
    String snapshotId = album.takeSnapshot("Empty snapshot");
    assertNotNull(snapshotId);

    ISnapshot snapshot = album.getSnapshot(snapshotId);
    assertTrue(snapshot.getShapes().isEmpty());
    assertEquals("Empty snapshot", snapshot.getDescription());
  }

  @Test
  public void testSnapshotIntegrity() {
    album.addShape("R", "rectangle");
    IShape rectangle = album.getShape("R");
    rectangle.move(200, 200);
    rectangle.changeColor(1.0, 0.0, 0.0);

    String snapshotId = album.takeSnapshot("First snapshot");
    assertNotNull(snapshotId);

    rectangle.move(300, 300);

    ISnapshot snapshot = album.getSnapshot(snapshotId);
    IShape snapshotRectangle = snapshot.getShapes().stream().filter(shape -> shape.getName().equals("R")).findFirst().orElse(null);

    assertNotNull(snapshotRectangle);
    assertEquals("Name: R, Type: rectangle, Min corner: (200.0,200.0), Width: 50.0, Height: 50.0, Color: (1.0,0.0,0.0)", snapshotRectangle.getDescription());
    assertNotEquals(rectangle.getDescription(), snapshotRectangle.getDescription());
  }
}