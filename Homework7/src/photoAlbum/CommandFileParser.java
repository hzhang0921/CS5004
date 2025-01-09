package photoAlbum;

import photoAlbum.Model.IAlbumModel;
import photoAlbum.Model.IShape;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * A parser to translate a .txt file into commands that can be executed
 * by the PhotoAlbum model.
 */
public class CommandFileParser {

  private final IAlbumModel model;

  /**
   * Constructor for the CommandFileParser.
   *
   * @param model the photo album model to apply commands to
   */
  public CommandFileParser(IAlbumModel model) {
    this.model = model;
  }

  /**
   * Parses the given file and executes commands on the model.
   *
   * @param filePath the path to the command file
   */
  public void parseFile(String filePath) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;
      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty() || line.startsWith("#")) {
          // Skip comments and empty lines
          continue;
        }
        processCommand(line);
      }
    } catch (IOException e) {
      throw new RuntimeException("Error reading file: " + filePath, e);
    }
  }

  /**
   * Processes a single command line and updates the model.
   *
   * @param line the command line
   */
  private void processCommand(String line) {
    String[] tokens = line.split("\\s+");
    String command = tokens[0].toLowerCase(); // Convert the command to lowercase

    switch (command) {
      case "canvas":
        // Optional: Handle canvas initialization here if needed
        break;

      case "shape":
        processShapeCommand(tokens);
        break;

      case "move":
        processMoveCommand(tokens);
        break;

      case "color":
        processColorCommand(tokens);
        break;

      case "resize":
        processResizeCommand(tokens);
        break;

      case "remove":
        processRemoveCommand(tokens);
        break;

      case "snapshot":
        processSnapshotCommand(line);
        break;

      default:
        throw new IllegalArgumentException("Unknown command: " + tokens[0]);
    }
  }

  private void processShapeCommand(String[] tokens) {
    String name = tokens[1]; // Shape name (case preserved)
    String type = tokens[2].toLowerCase(); // Shape type converted to lowercase
    double x = Double.parseDouble(tokens[3]);
    double y = Double.parseDouble(tokens[4]);
    double dim1 = Double.parseDouble(tokens[5]);
    double dim2 = Double.parseDouble(tokens[6]);
    double red = Double.parseDouble(tokens[7]) / 255.0;
    double green = Double.parseDouble(tokens[8]) / 255.0;
    double blue = Double.parseDouble(tokens[9]) / 255.0;

    model.addShape(name, type);
    IShape shape = model.getShape(name);
    shape.move(x, y);
    shape.scale(dim1 / shape.getWidth(), dim2 / shape.getHeight());
    shape.changeColor(red, green, blue);
  }

  private void processMoveCommand(String[] tokens) {
    String shapeToMove = tokens[1]; // Shape name (case preserved)
    double newX = Double.parseDouble(tokens[2]);
    double newY = Double.parseDouble(tokens[3]);
    IShape shape = model.getShape(shapeToMove);
    shape.move(newX, newY);
  }

  private void processColorCommand(String[] tokens) {
    String shapeToColor = tokens[1]; // Shape name (case preserved)
    double red = Double.parseDouble(tokens[2]) / 255.0;
    double green = Double.parseDouble(tokens[3]) / 255.0;
    double blue = Double.parseDouble(tokens[4]) / 255.0;
    IShape shape = model.getShape(shapeToColor);
    shape.changeColor(red, green, blue);
  }

  private void processResizeCommand(String[] tokens) {
    String shapeToResize = tokens[1]; // Shape name (case preserved)
    double newWidth = Double.parseDouble(tokens[2]);
    double newHeight = Double.parseDouble(tokens[3]);
    IShape shape = model.getShape(shapeToResize);
    shape.scale(newWidth / shape.getWidth(), newHeight / shape.getHeight());
  }

  private void processRemoveCommand(String[] tokens) {
    String shapeToRemove = tokens[1]; // Shape name (case preserved)
    model.removeShape(shapeToRemove);
  }

  private void processSnapshotCommand(String line) {
    String description = line.length() > 8 ? line.substring(9).trim() : ""; // Preserve case for description
    model.takeSnapshot(description);
  }
}