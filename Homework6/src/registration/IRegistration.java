package registration;

import java.util.List;

public interface IRegistration {
  int getRegistrationYear();
  int getProductionYear();
  IJurisdiction getJurisdiction();
  List<Person> getOwners();
  int getMaxPassengers();
  double calculateExciseTax();
}
