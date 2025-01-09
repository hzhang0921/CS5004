package doubledispatch;

public class Mercury implements IPlanet {
  @Override
  public void accept(ISpaceExplorer explorer) {
    explorer.visit(this);
  }
}