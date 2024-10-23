package sports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;

import sports.basketball.BasketballPlayer;
import sports.basketball.BasketballPlayerComparator;

public class Roster <T, R> {
  private List<T> players;

  public Roster() {
    this.players = new ArrayList<>();
  }

  public Roster(List<T> players) {
    this.players = players;
  }

  public List<T> getPlayers() {
    return players;
  }

  public void addPlayer(T player) {
    players.add(player);
  }

  public void sortByStats() {
    this.players.sort((Comparator<? super T>) new BasketballPlayerComparator()); // use our comparator to sort
    // the previous generic is using a wildcard ? basically stating the type can be any T or a super type of T
    // this is Turkish Delights from Java "deep magic" - don't worry if you don't understand it - you can
    // use this method safely without editing it or knowing how it works under the hood. Asland.
  }

  // Bi-Function to combine individual stats the dream team has, into one value
  public R fold(BiFunction<T, R, R> combiner, R seedValue) {
    // given a bi-function and a seed-value (initial value), combine all of the values from each player
    // into a single value. The value we're combining will be dependent on the bi-function passed in as a parameter
    // T and R are "formal" parameters that may be bound to 2 different concrete types
  }

}