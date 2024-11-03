package generics;

import taxes.ITaxableEntity;
import java.util.Comparator;

public class TaxableEntityTaxLiabilityComparator implements Comparator<ITaxableEntity> {

  @Override
  public int compare(ITaxableEntity entity1, ITaxableEntity entity2) {
    return Double.compare(entity1.getCurrentTaxLiability(), entity2.getCurrentTaxLiability());
  }
}