package doubledispatch;

import java.util.ArrayList;
import java.util.List;

public class SimulationBuilder {
  private static List<String> log = new ArrayList<>();

  /**
   * Creates a planet
   * @param name
   * @return
   */
  public static IPlanet createPlanet(String name) {
    if (name.equalsIgnoreCase("mars")) {
      return new Mars();
    } else if (name.equalsIgnoreCase("mercury")) {
      return new Mercury();
    } else if (name.equalsIgnoreCase("venus")) {
      return new Venus();
    } else {
      return null;
    }
  }

  /**
   * Creates a spaceExplorer
   * @param name
   * @return
   */
  public static ISpaceExplorer createExplorer(String name) {
    if (name.equalsIgnoreCase("LifeExplorer")) {
      return new LifeExplorer();
    } else if (name.equalsIgnoreCase("TerrainExplorer")) {
      return new TerrainExplorer();
    } else {
      return null;
    }
  }

  /**
   * Method to add message to log
   * @param message
   */
  public static void addToLog(String message) {
    log.add(message);
  }

  /**
   * Method to return all the logs.
   * @return
   */
  public static List<String> getSimulationLog() {
    return new ArrayList<>(log);
  }
}