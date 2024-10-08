package sensorcomp;

public class AtmosphericSensor implements ISensor {

  private double currentValue;
  private double lastValue;
  public AtmosphericSensor(double value) {

    this.currentValue = this.lastValue = value;

  }

  public AtmosphericSensor() {

    this(0);

  }

  @Override
  public double takeNewReading() {
    // Simulate a sensor reading
    this.lastValue = this.currentValue; // save previous
    this.currentValue = SensorData.currentReading();
    return this.currentValue;
  }

  @Override

  public double lastReading() {

    return this.lastValue;

  }

}