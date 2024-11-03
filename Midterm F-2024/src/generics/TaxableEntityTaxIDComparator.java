package generics;

import taxes.ITaxableEntity;
import java.util.Comparator;

public class TaxableEntityTaxIDComparator implements Comparator<ITaxableEntity> {

  @Override
  public int compare(ITaxableEntity entity1, ITaxableEntity entity2) {
    return entity1.getTaxID().compareToIgnoreCase(entity2.getTaxID());
  }
}