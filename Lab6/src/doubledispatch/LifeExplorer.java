package doubledispatch;

public class LifeExplorer implements ISpaceExplorer {
  @Override
  public void visit(Mercury mercury) {
    SimulationBuilder.addToLog("Landing: Mercury -> exploring life");
  }

  @Override
  public void visit(Mars mars) {
    SimulationBuilder.addToLog("Landing: Mars -> exploring life");
  }

  @Override
  public void visit(Venus venus) {
    SimulationBuilder.addToLog("Landing: Venus -> exploring life");
  }
}