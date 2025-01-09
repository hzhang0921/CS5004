package doubledispatch;

public interface ISpaceExplorer {
  void visit(Mercury mercury);
  void visit(Mars mars);
  void visit(Venus venus);

  default void visit(IPlanet aPlanet) {
    SimulationBuilder.addToLog("Visiting an unknown planet");
  }
}
