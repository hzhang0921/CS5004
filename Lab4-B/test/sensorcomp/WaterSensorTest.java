package sensorcomp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
  public void testTakeNewReading() {
    // Mock the atmospheric sensor's readings using a fixed sequence
    // First reading (mocked to be 0.1, below threshold)
    double newReading = waterSensor.takeNewReading();
    assertFalse(waterSensor.status(), "Flooding status should be false as reading is under 0.5");

    // Second reading (mocked to be 0.4, below threshold)
    newReading = waterSensor.takeNewReading();
    assertEquals(newReading, 0.4); //
    assertFalse(waterSensor.status(), "Flooding status should be false as reading is below 0.5");
  }

  @Test
  public void testLastReading() {
    // Assuming the atmospheric sensor's initial last reading is 0.0
    assertEquals(0.0, waterSensor.lastReading(), "Initial last reading should be 0.0");

    // Take a new reading and check the last reading updates
    waterSensor.takeNewReading();
    assertEquals(0.0, waterSensor.lastReading(), "Last reading should be updated to 0.0");
  }
}