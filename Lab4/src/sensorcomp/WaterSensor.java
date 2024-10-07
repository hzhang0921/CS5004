package sensorcomp;

public class WaterSensor implements IDiscreteSensor {
  private boolean isFlooding;
  private double currentValue;
  private double lastValue;
  /**
   * @return
   */
  @Override
  public boolean status() {
    return this.isFlooding;
  }

  /**
   *
   */
  @Override
  public void flipStatus() {
    this.isFlooding = !this.isFlooding;
  }

  /**
   * @param value
   */
  @Override
  public void setStatus(boolean value) {
    this.isFlooding = value;
  }

  /**
   * @return
   */
  @Override
  public double lastReading() {
    return this.lastValue;
  }

  /**
   * @return
   */
  @Override
  public double takeNewReading() {
    this.lastValue = this.currentValue;
    this.currentValue = SensorData.currentReading();
    this.isFlooding = this.currentValue > 0.5;
    return this.currentValue;
  }
}