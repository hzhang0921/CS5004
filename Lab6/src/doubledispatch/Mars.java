package doubledispatch;

public class Mars implements IPlanet {
  @Override
  public void accept(ISpaceExplorer explorer) {
    explorer.visit(this);
  }
}