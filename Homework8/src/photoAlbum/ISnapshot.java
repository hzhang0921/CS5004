package photoAlbum;

import java.util.List;

/**
 * Interface for snapshots
 */
public interface ISnapshot {

  /**
   * Gets the unique ID of the snapshot.
   *
   * @return The ID of the snapshot.
   */
  String getId();

  /**
   * Gets the timestamp of when the snapshot was taken.
   *
   * @return timestamp of the snapshot
   */
  String getTimestamp();

  /**
   * Gets the description of the snapshot.
   *
   * @return A short note about the snapshot
   */
  String getDescription();

  /**
   * Gets all the shapes that were in the snapshot.
   *
   * @return A list of all the shapes as they were at the time of the snapshot.
   */
  List<IShape> getShapes();

  /**
   * Gives a string version of the snapshot.
   *
   * @return A detailed description of everything in the snapshot.
   */
  String toString();
}