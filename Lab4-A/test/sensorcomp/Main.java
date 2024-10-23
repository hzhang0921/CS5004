package sensorcomp;

public class Main {

  public static void main(String[] args) {
    ISensor sensor = new WaterSensor();
    for(int i=0; i< 10; i++) {
      System.out.println("Water reading = " + sensor.takeNewReading() + " inches");
      System.out.println("Our basement is flooding (True/False): " + ((IDiscreteSensor) sensor).status());
    }
  }
}