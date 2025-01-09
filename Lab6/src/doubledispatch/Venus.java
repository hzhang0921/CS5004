package doubledispatch;

public class Venus implements IPlanet {
  @Override
  public void accept(ISpaceExplorer explorer) {
    explorer.visit(this);
  }
}