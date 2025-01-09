package doubledispatch;

public class TerrainExplorer implements ISpaceExplorer {
  @Override
  public void visit(Mercury mercury) {
    SimulationBuilder.addToLog("Mapping: Mercury");
  }

  @Override
  public void visit(Mars mars) {
    SimulationBuilder.addToLog("Mapping: Mars");
  }

  @Override
  public void visit(Venus venus) {
    SimulationBuilder.addToLog("Mapping: Venus");
  }
}