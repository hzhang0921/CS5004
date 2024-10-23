package sensorcomp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Test class for WaterSensor
public class WaterSensorTest {

  private WaterSensor waterSensor;

  @BeforeEach
  public void setUp() {
    waterSensor = new WaterSensor();
  }

  @Test
  public void testInitialStatus() {
    // Initially, the flooding status should be false (not flooding)
    assertFalse(waterSensor.status(), "Initial flooding status should be false");
  }

  @Test
  public void testFlipStatus() {
    // Flipping the status should change the flooding status
    waterSensor.flipStatus();
    assertTrue(waterSensor.status(), "Flooding status should be true after flipping");

    // Flip again to return to the initial state
    waterSensor.flipStatus();
    assertFalse(waterSensor.status(), "Flooding status should be false after flipping again");
  }

  @Test
  public void testSetStatus() {
    // Set the flooding status to true
    waterSensor.setStatus(true);
    assertTrue(waterSensor.status(), "Flooding status should be true after setting it to true");

    // Set the flooding status to false
    waterSensor.setStatus(false);
    assertFalse(waterSensor.status(), "Flooding status should be false after setting it to false");
  }

  @Test
  public void testLastReading() {
    // Java dictates all empty double as 0.0
    assertEquals(0.0, waterSensor.lastReading(), "Last reading should be 0.0");
  }

  @Test
  public void testTakeNewReading() {
    // Take a new reading and check the current value and status
    double newReading = waterSensor.takeNewReading();
    assertEquals(0.1, newReading, "The new reading should be 0.1 as the first value in SensorData");
    assertFalse(waterSensor.status(), "Flooding status should be False as 0.1 < 0.5");

    // Take another reading with a value below the threshold
    newReading = waterSensor.takeNewReading();
    assertEquals(0.4, newReading, "The new reading should be 0.4 as the second value");
    assertFalse(waterSensor.status(), "Flooding status should be false as 0.1 < 0.4");

    // Check that the last value is now the previous reading (0.4)
    waterSensor.takeNewReading();
    assertEquals(0.4, waterSensor.lastReading(), "The LAST reading should be 0.4 after a new reading");
  }
}