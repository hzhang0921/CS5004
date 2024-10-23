import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import taxes.Address;
import taxes.Company;
import taxes.Person;
import taxes.TaxLien;
import taxes.ITaxableEntity;

import static org.junit.jupiter.api.Assertions.*;

class ITaxableEntityTest {
  ITaxableEntity richieRich;
  ITaxableEntity georgeJetson;
  ITaxableEntity yogiBear;

  ITaxableEntity scroogeMcDuckInc;

  Address mansion;
  Address forest;
  Address future;
  Address buenaVista;

  TaxLien honey;
  TaxLien money;
  TaxLien funny;
  TaxLien greedy;

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
  }

  @Test
  void testAddGetAddresses() {
    assertEquals(mansion, richieRich.getAddresses().get(0) );
    Address copyMansion = new Address("10 Dollar Lane", "Richville", "RI", "$$$-$$", money);
    assertEquals(mansion, copyMansion);

    Address moon = new Address("20 Lunar Way", "Moonbase-1", "Moon", "11111",
            new TaxLien("Moon", 1000.00, true));
    georgeJetson.addAddress(moon);
    List<Address> test = new ArrayList<>();
    test.add(future);
    test.add(moon);

    assertTrue(test.size() == georgeJetson.getAddresses().size()
            && test.containsAll(georgeJetson.getAddresses())
            && georgeJetson.getAddresses().containsAll(test));

    // No duplicate addresses allowed
    georgeJetson.addAddress(moon);

    assertTrue(test.size() == georgeJetson.getAddresses().size()
            && test.containsAll(georgeJetson.getAddresses())
            && georgeJetson.getAddresses().containsAll(test));

    Address corpHQ = new Address("88 Megabucks Way", "Buena Vista", "FL", "DUCK-BUCKS", greedy);
    assertEquals(corpHQ, scroogeMcDuckInc.getAddresses().get(0));
  }


  @Test
  void testUpdateAddresses() {
    assertEquals(mansion, richieRich.getAddresses().get(0) );

    Address moon = new Address("20 Lunar Way", "Moonbase-1", "Moon", "11111",
            new TaxLien("Moon", 1000.00, true));
    richieRich.updateAddress(mansion, moon);

    List<Address> test = new ArrayList<>();
    Address lunar = new Address("20 Lunar Way", "Moonbase-1", "Moon", "11111",
            new TaxLien("Moon", 1000.00, true));
    test.add(lunar);

    assertTrue(test.size() == richieRich.getAddresses().size()
            && test.containsAll(richieRich.getAddresses())
            && richieRich.getAddresses().containsAll(test));

  }
  @Test
  void testSafeCopyEntityAddresses() {
    TaxLien t = new TaxLien("Recurrence", 1111.1111, false);
    Address bitByte = new Address("10 Infinite Loop Way", "Byte Falls", "RI", "0xABCD", t);

    georgeJetson.addAddress(bitByte);

    // ensure safe copies are used. Pass in new address then change the base version and ensure not mutated on other side
    Person janeJetson = new Person("Jane Jetson", "123456789", future, 4);
    TaxLien second = new TaxLien("Recurrence", 1111.1111, true); // use half year
    Address bit  = new Address("10 Infinite Loop Way", "Byte Falls", "RI", "0xABCD", second);
    janeJetson.addAddress(bit);

    t.switchPaymentPlan(); // switch payment plan to half year. George was based on this object. Ensure a safe copy was given
    assertNotEquals( janeJetson.getCurrentTaxLiability(),
            georgeJetson.getCurrentTaxLiability(), 0.001, "Non safe copy supplied for tax lien. Mutation");
  }

  @Test
  void testGetCurrentTaxLiability() {
    assertEquals(150000, richieRich.getCurrentTaxLiability(), 0.001, "Wrong tax liability");
    assertEquals(0.5, yogiBear.getCurrentTaxLiability(), 0.001, "Wrong tax liability");
    Address moon = new Address("20 Lunar Way", "Moonbase-1", "Moon", "11111",
            new TaxLien("Moon", 1000.00, true));
    georgeJetson.addAddress(moon);
    georgeJetson.addAddress(new Address("Mars Landing", "Martian City", "MM&M", "12345",
            new TaxLien("Outer Space", 0, true)));
    assertEquals(200500/4, georgeJetson.getCurrentTaxLiability(), 0.001); // George has 4 dependents, Mars is half-year; Hollywood is full year

    TaxLien freeVille = new TaxLien("Freeville", 0, false);
    Address nowhere = new Address("Taco Libre!", "FreeCity", "Nowhere", "7777777", freeVille);
    Person freeWillie = new Person("George Jetson", "2.pow(20)", nowhere, 10);
    assertEquals(0, freeWillie.getCurrentTaxLiability(), 0.001, "Wrong tax liability for FREE");

    TaxLien ocean = new TaxLien("Jellystone", 1, false);
    Address sevenSeas = new Address("7 Bee Stung Dr.", "Jellystone Park", "WY", "82000-7371", ocean);
    freeWillie.addAddress(sevenSeas);
    assertEquals(0.1, freeWillie.getCurrentTaxLiability(), 0.001, "Wrong tax liability for almost-free Willie");

    scroogeMcDuckInc.addAddress(new Address("Mars Landing", "Martian City", "MM&M", "12345",
            new TaxLien("Outer Space", 0, true)));
    assertEquals(30000, scroogeMcDuckInc.getCurrentTaxLiability(), 0.001, "Wrong tax liability for Scrooge Inc.");

    scroogeMcDuckInc.addAddress(new Address("Mo Money", "Mo Money", "MM", "12345",
            new TaxLien("Da Bank Vault", 2000, true)));
    assertEquals(31000,  // da bank vault total tax 2000, but uses half-year assessment
            scroogeMcDuckInc.getCurrentTaxLiability(), 0.001, "Wrong tax liability for Scrooge Inc.");

  }

  @Test
  void testGetTaxID() {
    assertEquals("HeyBooboo", yogiBear.getTaxID() );
  }

  @Test
  void testEqualPeople() {
    TaxLien h = new TaxLien("Jellystone", 1, true);
    Address f  = new Address("7 Bee Stung Dr.", "Jellystone Park", "WY", "82000-7371", h);
    Person bogieBear = new Person("Yogi Bear", "HeyBooboo", f, 1);

    assertEquals(yogiBear, bogieBear, "Yogi object is not equal to Bogie object");
    assertEquals(yogiBear, yogiBear, "Yogi object not equal to itself");

    assertNotEquals(yogiBear, richieRich); // not the same person

    assertNotEquals(yogiBear, scroogeMcDuckInc); // corporations aren't people, are they???
    assertNotEquals(scroogeMcDuckInc, yogiBear); // corporations aren't people, are they???

  }

  @Test
  void testSomeExceptions() { // not exhaustive, but hey...it's an exam, not a homework!
    Assertions.assertThrows(IllegalArgumentException.class, ()-> {
      new Person(null, "", null, 5);
    });
    Assertions.assertThrows(IllegalArgumentException.class, ()-> {
      new Person("Hello World", "", forest, 5);
    });
    Assertions.assertThrows(IllegalArgumentException.class, ()-> {
      new Person("Hello World", "12345678", forest, 5);
    });
    Assertions.assertThrows(IllegalArgumentException.class, ()-> {
      new Person("Hello World", "1234567890", forest, 5);
    });
    Assertions.assertThrows(IllegalArgumentException.class, ()-> {
      new Person("Hello", "123456789", forest, 11);
    });
    Assertions.assertThrows(IllegalArgumentException.class, ()-> {
      new Person("Hello", "123456789", forest, -1);
    });

  }

  @Test
  void testToString() {
    System.out.println("We're not really testing anything here. Just ensuring you provided a toString() :-)");
    System.out.println(georgeJetson);
    System.out.println(richieRich);
    System.out.println(yogiBear);
    System.out.println(scroogeMcDuckInc);
  }
}