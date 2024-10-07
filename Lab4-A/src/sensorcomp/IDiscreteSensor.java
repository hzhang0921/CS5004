package sensorcomp;
// Your WaterSensor should implement THIS interface!

public interface IDiscreteSensor extends ISensor {
  boolean status(); // discrete values like "on"/ off modeled here
  void flipStatus();
  void setStatus(boolean value);

  default double lastReading() { return 0;}

  @Override
  default double takeNewReading() {return 0;}
}