package photoAlbum.Controller;

import photoAlbum.Model.IAlbumModel;
import photoAlbum.View.WebView;
import photoAlbum.View.GraphicalView;

public class Controller {

  private final IAlbumModel model;

  /**
   * Constructor for the Controller.
   *
   * @param model the photo album model
   */
  public Controller(IAlbumModel model) {
    this.model = model;
  }

  /**
   * Executes the WebView and generates the HTML file.
   *
   * @param outputPath   the output path for the HTML file
   * @param canvasWidth  the width of the canvas
   * @param canvasHeight the height of the canvas
   */
  public void executeWebView(String outputPath, int canvasWidth, int canvasHeight) {
    WebView webView = new WebView(model, outputPath, canvasWidth, canvasHeight);
    webView.render();
    System.out.println("Web view rendered to: " + outputPath);
  }

  /**
   * Executes the GraphicalView for interactive user operations.
   *
   * @param canvasWidth  the width of the graphical window
   * @param canvasHeight the height of the graphical window
   */
  public void executeGraphicalView(int canvasWidth, int canvasHeight) {
    GraphicalView graphicalView = new GraphicalView(model, canvasWidth, canvasHeight);
    graphicalView.display();
  }
}