package photoAlbum;

import java.util.List;

/**
 * Interface for adding and removing shapes, taking snapshots, and resetting everything.
 */
public interface IAlbumModel {

  /**
   * Adds a new shape to the album.
   *
   * @param name The name of the shape.
   * @param type The type of shape, like "rectangle" or "oval".
   */
  void addShape(String name, String type);

  /**
   * Removes a shape from the album by its name.
   *
   * @param name The name of the shape you want to remove.
   */
  void removeShape(String name);

  /**
   * Gets a shape by its name.
   *
   * @param name The name of the shape you want to find.
   * @return The shape with that name, or it throws an error if it doesn’t exist.
   */
  IShape getShape(String name);

  /**
   * Takes a snapshot of the current state of the album.
   *
   * @param description is the description
   * @return The ID of the snapshot you just took.
   */
  String takeSnapshot(String description);

  /**
   * Gets a specific snapshot by its ID.
   *
   * @param id The unique ID of the snapshot you want.
   * @return The snapshot with that ID.
   */
  ISnapshot getSnapshot(String id);

  /**
   * Gives you a list of all the snapshots taken so far.
   *
   * @return A list of all the snapshots.
   */
  List<ISnapshot> getSnapshots();

  /**
   * Gets the IDs of all snapshots as a string.
   *
   * @return A string of snapshot IDs, formatted like a list.
   */
  String getSnapshotIDs();

  /**
   * Prints all the snapshots in a nice, readable way.
   *
   * @return A string showing all the snapshots, their details, and the shapes they include.
   */
  String printSnapshots();

  /**
   * Clears everything from the album—no shapes.
   */
  void reset();
}