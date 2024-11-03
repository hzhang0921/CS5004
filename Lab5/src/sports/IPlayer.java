package sports;

public interface IPlayer <T> {

  String getName();

  int getAge();

  double getHeight();

  <T> T getStats(); // generic method returns status type T

}