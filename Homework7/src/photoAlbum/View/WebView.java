package photoAlbum.View;

import photoAlbum.Model.IAlbumModel;
import photoAlbum.Model.IShape;
import photoAlbum.Model.ISnapshot;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * A view implementation that generates an HTML file with embedded SVG
 * to represent snapshots from the photo album.
 */
public class WebView {

  private final IAlbumModel model;
  private final String outputPath;
  private final int canvasWidth;
  private final int canvasHeight;

  /**
   * Constructor for the WebView.
   *
   * @param model        the photo album model
   * @param outputPath   the output HTML file path
   * @param canvasWidth  the width of the canvas
   * @param canvasHeight the height of the canvas
   */
  public WebView(IAlbumModel model, String outputPath, int canvasWidth, int canvasHeight) {
    this.model = model;
    this.outputPath = outputPath;
    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;
  }

  /**
   * Renders the photo album to an HTML file.
   */
  public void render() {
    StringBuilder html = new StringBuilder();

    // HTML boilerplate
    html.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Photo Album</title>\n")
        .append("<style>\n")
        .append("body { font-family: Arial, sans-serif; }\n")
        .append("h2 { margin-bottom: 0; }\n")
        .append("svg { border: 1px solid black; margin-bottom: 20px; }\n")
        .append("</style>\n</head>\n<body>\n")
        .append("<h1>Photo Album Snapshots</h1>\n");

    // Add each snapshot
    List<ISnapshot> snapshots = model.getSnapshots();
    for (ISnapshot snapshot : snapshots) {
      html.append("<div>\n")
          .append("<h2>Snapshot ID: ").append(snapshot.getId()).append("</h2>\n")
          .append("<p>Description: ").append(snapshot.getDescription() == null ? "No description" : snapshot.getDescription()).append("</p>\n")
          .append("<svg width=\"").append(canvasWidth).append("\" height=\"").append(canvasHeight).append("\" xmlns=\"http://www.w3.org/2000/svg\">\n");

      // Add shapes to SVG
      for (IShape shape : snapshot.getShapes()) {
        html.append(getSvgForShape(shape));
      }

      html.append("</svg>\n</div>\n");
    }

    // Close HTML
    html.append("</body>\n</html>");

    // Write to file
    try (FileWriter writer = new FileWriter(outputPath)) {
      writer.write(html.toString());
    } catch (IOException e) {
      throw new RuntimeException("Error writing to file: " + outputPath, e);
    }
  }

  /**
   * Converts a shape to its corresponding SVG representation.
   *
   * @param shape the shape to convert
   * @return the SVG string for the shape
   */
  private String getSvgForShape(IShape shape) {
    StringBuilder svg = new StringBuilder();
    String color = String.format("rgb(%d,%d,%d)",
        (int) (shape.getRed() * 255),
        (int) (shape.getGreen() * 255),
        (int) (shape.getBlue() * 255));

    switch (shape.getType().toLowerCase()) {
      case "rectangle":
        svg.append("<rect x=\"").append(shape.getX())
            .append("\" y=\"").append(shape.getY())
            .append("\" width=\"").append(shape.getWidth())
            .append("\" height=\"").append(shape.getHeight())
            .append("\" fill=\"").append(color).append("\" />\n");
        break;

      case "oval":
        svg.append("<ellipse cx=\"").append(shape.getX() + shape.getWidth() / 2)
            .append("\" cy=\"").append(shape.getY() + shape.getHeight() / 2)
            .append("\" rx=\"").append(shape.getWidth() / 2)
            .append("\" ry=\"").append(shape.getHeight() / 2)
            .append("\" fill=\"").append(color).append("\" />\n");
        break;

      default:
        throw new IllegalArgumentException("Unsupported shape type: " + shape.getType());
    }

    return svg.toString();
  }
}