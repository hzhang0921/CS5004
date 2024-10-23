import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nodes.GenericBinaryNode;
import taxes.Address;
import taxes.Company;
import taxes.ICorporateTaxableEntity;
import taxes.ITaxableEntity;
import taxes.TaxLien;

import static org.junit.jupiter.api.Assertions.*;

class GenericBinaryNodeTest {
  GenericBinaryNode<String> head;
  GenericBinaryNode<Integer> top;

  @BeforeEach
  void setUp() {
    head = new GenericBinaryNode<>("Hello World",
            new GenericBinaryNode<>("Left World"), new GenericBinaryNode<>("Right World"));
    top = new GenericBinaryNode<>(1);
  }

  @Test
  void testGetData() {
    assertEquals("Hello World", head.getData(), "Wrong data");
    assertEquals("Left World", head.getLeft().getData(), "Wrong data for left child");
    assertEquals("Right World", head.getRight().getData(), "Wrong data for right child");

    assertEquals(1, top.getData());
    top.addLeft(new GenericBinaryNode<>(2, new GenericBinaryNode<>(3), new GenericBinaryNode<>(4)));
    top.addRight(new GenericBinaryNode<>(5));
    assertEquals(3, top.getLeft().getLeft().getData());
    assertEquals(4, top.getLeft().getRight().getData());
    assertEquals(2, top.getLeft().getData());

  }
  @Test
  void testString() {
    System.out.println("No real test, but ensuring your wrote the toString() method. Let's see it!");

    GenericBinaryNode right = new GenericBinaryNode<>(5);

    top.addLeft(new GenericBinaryNode<>(2, new GenericBinaryNode<>(3), new GenericBinaryNode<>(4)));
    top.addRight(right);
    top.getRight().addLeft(new GenericBinaryNode<>(6));

    String s = top.toString();
    assertTrue(s.contains("1") && s.contains("2") && s.contains("3")
        && s.contains("4") && s.contains("5") && s.contains("6"));

    s = right.toString();
    assertTrue( s.contains("5") && s.contains("6"));
    
    System.out.println(top.toString());
    System.out.println(right.toString());

    ITaxableEntity scroogeMcDuckInc;
    ICorporateTaxableEntity northSouthUniversity;
    ICorporateTaxableEntity bordMotors;

    Address bordHQ;
    Address northSouthPlace;
    Address buenaVista;

    TaxLien north;
    TaxLien bordTax;
    TaxLien greedy;

    greedy = new TaxLien("Golden Hills", 30000, false);
    buenaVista = new Address("88 Megabucks Way", "Buena Vista", "FL", "DUCK-BUCKS", greedy);
    scroogeMcDuckInc = new Company("ScroogeMcDuck Incoporated", "Scrooge-U", buenaVista);

    north = new TaxLien("Worldwide", 100000, true);
    northSouthPlace = new Address("West Village H", "Boston", "MA", "01808", north);
    northSouthUniversity = new Company("NorthernSouthern University", "GoHuskies", northSouthPlace);

    bordTax = new TaxLien("Indy 500", 500000, false);
    bordHQ = new Address("88 Vroom vroom way", "Indianapolis", "IN", "CATCH-ME", bordTax);
    bordMotors = new Company("Bord Motor Company", "Vroooooom", bordHQ);
    GenericBinaryNode<ITaxableEntity> org = new GenericBinaryNode<>(scroogeMcDuckInc);
    org.addLeft(new GenericBinaryNode<>(northSouthUniversity));
    org.addRight(new GenericBinaryNode<>(bordMotors));

    s = org.toString();
    assertTrue(s.contains("ScroogeMcDuck Incoporated")
            && s.contains("NorthernSouthern University")
            && s.contains("Bord Motor Company"));
    System.out.println(org.toString());

  }
}