package photoAlbum;

import photoAlbum.Controller.Controller;
import photoAlbum.Model.AlbumModel;
import photoAlbum.Model.IAlbumModel;

public class PhotoAlbumMain {

  public static void main(String[] args) {
    // Default parameters
    String inputFilePath = null;
    String viewType = null;
    String outputPath = null;
    int canvasWidth = 1000; // Default canvas width
    int canvasHeight = 1000; // Default canvas height

    // Parse command-line arguments
    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          inputFilePath = args[++i];
          break;
        case "-view":
        case "-v":
          viewType = args[++i];
          break;
        case "-out":
          outputPath = args[++i];
          break;
        default:
          try {
            int value = Integer.parseInt(args[i]);
            if (canvasWidth == 1000) {
              canvasWidth = value;
            } else {
              canvasHeight = value;
            }
          } catch (NumberFormatException e) {
            System.out.println("Unknown argument: " + args[i]);
          }
      }
    }

    // Validate required arguments
    if (inputFilePath == null || viewType == null) {
      System.out.println("Error: Input file and view type must be specified.");
      return;
    }

    // Create and populate the model
    IAlbumModel model = new AlbumModel();
    CommandFileParser parser = new CommandFileParser(model);
    try {
      parser.parseFile(inputFilePath);
    } catch (RuntimeException e) {
      System.out.println("Error parsing the file: " + e.getMessage());
      return;
    }

    // Create the controller
    Controller controller = new Controller(model);

    // Execute the chosen view
    if (viewType.equalsIgnoreCase("web")) {
      if (outputPath == null) {
        System.out.println("Error: Output path must be specified for the web view.");
        return;
      }
      controller.executeWebView(outputPath, canvasWidth, canvasHeight);
    } else if (viewType.equalsIgnoreCase("graphical")) {
      controller.executeGraphicalView(canvasWidth, canvasHeight);
    } else {
      System.out.println("Error: Unsupported view type: " + viewType);
    }
  }
}