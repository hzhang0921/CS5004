package sensorcomp;

public class WaterSensor implements IDiscreteSensor {

  // Composition: WaterSensor has an AtmosphericSensor object
  private AtmosphericSensor AtmosphericSensor;
  private boolean isFlooding;

  // Constructor initializes the AtmosphericSensor
  public WaterSensor() {
    this.AtmosphericSensor = new AtmosphericSensor();
    this.isFlooding = false; // Default flooding status
  }

  @Override
  public boolean status() {
    // Returns the flooding status (true if flooding)
    return this.isFlooding;
  }

  @Override
  public void flipStatus() {
    // Flips the flooding status
    this.isFlooding = !this.isFlooding;
  }

  @Override
  public void setStatus(boolean value) {
    // Explicitly sets the flooding status
    this.isFlooding = value;
  }

  @Override
  public double takeNewReading() {
    // Delegate the reading to the AtmosphericSensor
    double currentReading = AtmosphericSensor.takeNewReading();

    this.isFlooding = currentReading > 0.5;  // Flooding if reading > 0.5

    return currentReading;
  }

  @Override
  public double lastReading() {
    // Delegate to AtmosphericSensor's lastReading() method
    return AtmosphericSensor.lastReading();
  }
}