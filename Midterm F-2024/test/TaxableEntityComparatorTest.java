import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generics.TaxableEntityTaxIDComparator;
import generics.TaxableEntityTaxLiabilityComparator;
import taxes.Address;
import taxes.Company;
import taxes.ITaxableEntity;
import taxes.Person;
import taxes.TaxLien;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaxableEntityComparatorTest {

  ITaxableEntity richieRich;
  ITaxableEntity georgeJetson;
  ITaxableEntity yogiBear;

  ITaxableEntity scroogeMcDuckInc;
  ITaxableEntity bordMotors;

  Address mansion;
  Address forest;
  Address future;
  Address buenaVista;
  Address bordHQ;

  TaxLien honey;
  TaxLien money;
  TaxLien funny;
  TaxLien greedy;
  TaxLien bordTax;


  @BeforeEach
  void setUp() {
    money = new TaxLien("Hollywood", 150000, false);
    mansion = new Address("10 Dollar Lane", "Richville", "RI", "$$$-$$", money);
    richieRich = new Person("Richie Rich", "111-11-11", mansion, 0);

    greedy = new TaxLien("Golden Hills", 30000, false);
    buenaVista = new Address("88 Megabucks Way", "Buena Vista", "FL", "DUCK-BUCKS", greedy);
    scroogeMcDuckInc = new Company("ScroogeMcDuck Incoporated", "Scrooge-U", buenaVista);

    honey = new TaxLien("Jellystone", 1, true);
    forest = new Address("7 Bee Stung Dr.", "Jellystone Park", "WY", "82000-7371", honey);
    yogiBear = new Person("Yogi Bear", "HeyBooboo", forest, 1);

    funny = new TaxLien("Hollywood", 200000, false);
    future = new Address("Skypad Apartments, Apt 0.212", "Orbit City", "LI", "555-55", funny);
    georgeJetson = new Person("George Jetson", "2.pow(20)", future, 4);

    bordTax = new TaxLien("Indy 500", 500000, false);
    bordHQ = new Address("88 Vroom vroom way", "Indianapolis", "IN", "CATCH-ME", bordTax);
    bordMotors = new Company("Bord Motor Company", "Vroooooom", bordHQ);
  }


  @Test
  void testTaxIDComparator() {
    List<ITaxableEntity> sortedByTaxID = Arrays.asList(richieRich, georgeJetson, yogiBear, scroogeMcDuckInc, bordMotors);

    List<ITaxableEntity> initiallyUnsorted = Arrays.asList(scroogeMcDuckInc, bordMotors, richieRich, georgeJetson, yogiBear);
    initiallyUnsorted.sort(new TaxableEntityTaxIDComparator());
    assertEquals(sortedByTaxID, initiallyUnsorted);
  }

  @Test
  void testTaxLiabilityComparator() {
    List<ITaxableEntity> sortedByTaxID = Arrays.asList( yogiBear, scroogeMcDuckInc,georgeJetson, richieRich, bordMotors);
    // remember: George has 4 dependents so that lowers his tax liability
    List<ITaxableEntity> initiallyUnsorted = Arrays.asList(scroogeMcDuckInc, bordMotors, richieRich, georgeJetson, yogiBear);
    initiallyUnsorted.sort(new TaxableEntityTaxLiabilityComparator());
    assertEquals(sortedByTaxID, initiallyUnsorted);
  }
}