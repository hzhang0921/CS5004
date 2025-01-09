package photoAlbum.View;

import photoAlbum.Model.IAlbumModel;
import photoAlbum.Model.ISnapshot;
import photoAlbum.Model.IShape;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A graphical view for the Photo Album using Swing.
 * Displays snapshots of shapes and provides navigation between snapshots.
 */
public class GraphicalView extends JFrame {

  private final IAlbumModel model;
  private final int width;
  private final int height;

  private int currentSnapshotIndex = 0;
  private final DrawingPanel drawingPanel;
  private final JLabel snapshotInfo;

  /**
   * Constructs a new GraphicalView.
   *
   * @param model  The album model containing shapes and snapshots.
   * @param width  The width of the window.
   * @param height The height of the window.
   */
  public GraphicalView(IAlbumModel model, int width, int height) {
    this.model = model;
    this.width = width;
    this.height = height;

    setTitle("Graphical Photo Album");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    drawingPanel = new DrawingPanel();
    snapshotInfo = new JLabel("", SwingConstants.CENTER);

    setupUI();
  }

  /**
   * Sets up the user interface components.
   */
  private void setupUI() {
    JPanel controlPanel = new JPanel();
    JButton prevButton = new JButton("Previous");
    JButton nextButton = new JButton("Next");

    prevButton.addActionListener(e -> navigateSnapshot(-1));
    nextButton.addActionListener(e -> navigateSnapshot(1));

    controlPanel.add(prevButton);
    controlPanel.add(nextButton);

    add(drawingPanel, BorderLayout.CENTER);
    add(snapshotInfo, BorderLayout.NORTH);
    add(controlPanel, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(null);
  }

  /**
   * Updates the view to display the current snapshot.
   */
  private void updateView() {
    List<ISnapshot> snapshots = model.getSnapshots();
    if (snapshots.isEmpty()) {
      snapshotInfo.setText("No snapshots available.");
    } else {
      ISnapshot snapshot = snapshots.get(currentSnapshotIndex);
      snapshotInfo.setText(String.format("Snapshot ID: %s | Description: %s",
          snapshot.getId(), snapshot.getDescription()));
    }
    drawingPanel.repaint();
  }

  /**
   * Navigates between snapshots.
   *
   * @param direction -1 for previous, 1 for next.
   */
  private void navigateSnapshot(int direction) {
    int newIndex = currentSnapshotIndex + direction;
    List<ISnapshot> snapshots = model.getSnapshots();
    if (newIndex >= 0 && newIndex < snapshots.size()) {
      currentSnapshotIndex = newIndex;
      updateView();
    } else {
      JOptionPane.showMessageDialog(this, "No more snapshots in this direction.");
    }
  }

  /**
   * Displays the graphical view.
   */
  public void display() {
    setVisible(true);
    if (!model.getSnapshots().isEmpty()) {
      updateView();
    }
  }

  /**
   * A custom JPanel for rendering shapes.
   */
  private class DrawingPanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      List<ISnapshot> snapshots = model.getSnapshots();
      if (!snapshots.isEmpty() && currentSnapshotIndex < snapshots.size()) {
        ISnapshot snapshot = snapshots.get(currentSnapshotIndex);
        List<IShape> shapes = snapshot.getShapes();

        for (IShape shape : shapes) {
          drawShape(g2d, shape);
        }
      }
    }

    /**
     * Draws a shape onto the panel.
     *
     * @param g2d   The Graphics2D context.
     * @param shape The shape to draw.
     */
    private void drawShape(Graphics2D g2d, IShape shape) {
      g2d.setColor(new Color(
          (float) shape.getRed(),
          (float) shape.getGreen(),
          (float) shape.getBlue()
      ));

      if ("rectangle".equalsIgnoreCase(shape.getType())) {
        g2d.fillRect(
            (int) shape.getX(),
            (int) shape.getY(),
            (int) shape.getWidth(),
            (int) shape.getHeight()
        );
      } else if ("oval".equalsIgnoreCase(shape.getType())) {
        g2d.fillOval(
            (int) (shape.getX() - shape.getWidth() / 2),
            (int) (shape.getY() - shape.getHeight() / 2),
            (int) shape.getWidth(),
            (int) shape.getHeight()
        );
      }
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(width, height);
    }
  }
}