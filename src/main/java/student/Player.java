package student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import model.Baron;
import model.Card;
import model.PlayerObserver;
import model.RailroadBaronsException;

public class Player implements model.Player {

  private int trainPieces;
  private HashMap<Card, Integer> hand;
  private int score;
  private HashSet<Route> routes = new HashSet<>();
  private Pair lastTwo;
  private ArrayList<PlayerObserver> observers = new ArrayList<>();
  private Baron baron;


  /**
   * This is called at the start of every game to reset the player to its initial state: <ul>
   * <li>Number of train pieces reset to the starting number of 45.</li> <li>All remaining {@link
   * Card cards} cleared from hand.</li> <li>Score reset to 0.</li> <li>Claimed {@link Route routes}
   * cleared.</li> <li>Sets the most recently dealt {@link Pair} of cards to two {@link Card#NONE}
   * values.</li> </ul>
   *
   * @param dealt The hand of {@link Card cards} dealt to the player at the start of the game. By
   * default this will be 4 cards.
   */
  @Override
  public void reset(Card... dealt) {
    trainPieces = 45;
    hand.clear();
    score = 0;
    routes.clear();
    lastTwo = new Pair(Card.NONE, Card.NONE);

  }

  /**
   * Adds an {@linkplain PlayerObserver observer} that will be notified when the player changes in
   * some way.
   *
   * @param observer The new {@link PlayerObserver}.
   */
  @Override
  public void addPlayerObserver(PlayerObserver observer) {
    observers.add(observer);
    //
  }

  /**
   * Removes an {@linkplain PlayerObserver observer} so that it is no longer notified when the
   * player changes in some way.
   *
   * @param observer The {@link PlayerObserver} to remove.
   */
  @Override
  public void removePlayerObserver(PlayerObserver observer) {
    observers.remove(observer);
  }

  /**
   * The {@linkplain Baron baron} as which this player is playing the game.
   *
   * @return The {@link Baron} as which this player is playing.
   */
  @Override
  public Baron getBaron() {
    return baron;
  }

  /**
   * Used to start the player's next turn. A {@linkplain Pair pair of cards} is dealt to the player,
   * and the player is once again able to claim a {@linkplain Route route} on the {@linkplain
   * RailroadMap map}.
   *
   * @param dealt a {@linkplain Pair pair of cards} to the player. Note that one or both of these
   * cards may have a value of {@link Card#NONE}.
   */
  @Override
  public void startTurn(model.Pair dealt) {

  }

  /**
   * Returns the most recently dealt {@linkplain Pair pair of cards}. Note that one or both of the
   * {@linkplain Card cards} may have a value of {@link Card#NONE}.
   *
   * @return The most recently dealt {@link Pair} of {@link Card Cards}.
   */
  @Override
  public model.Pair getLastTwoCards() {
    return null;
  }

  /**
   * Returns the number of the specific kind of {@linkplain Card card} that the player currently has
   * in hand. Note that the number may be 0.
   *
   * @param card The {@link Card} of interest.
   * @return The number of the specified type of {@link Card} that the player currently has in hand.
   */
  @Override
  public int countCardsInHand(Card card) {
    return 0;
  }

  /**
   * Returns the number of game pieces that the player has remaining. Note that the number may be
   * 0.
   *
   * @return The number of game pieces that the player has remaining.
   */
  @Override
  public int getNumberOfPieces() {
    return 0;
  }

  /**
   * Returns true iff the following conditions are true:
   *
   * <ul> <li>The {@linkplain Route route} is not already claimed by this or some other {@linkplain
   * Baron baron}.</li> <li>The player has not already claimed a route this turn (players are
   * limited to one claim per turn).</li> <li>The player has enough {@linkplain Card cards}
   * (including ONE {@linkplain Card#WILD wild card, if necessary}) to claim the route.</li> <li>The
   * player has enough train pieces to claim the route.</li> </ul>
   *
   * @param route The {@link Route} being tested to determine whether or not the player is able to
   * claim it.
   * @return True if the player is able to claim the specified {@link Route}, and false otherwise.
   */
  @Override
  public boolean canClaimRoute(model.Route route) {
    return false;
  }

  /**
   * Claims the given {@linkplain Route route} on behalf of this player's {@linkplain Baron Railroad
   * Baron}. It is possible that the player has enough cards in hand to claim the route by using
   * different combinations of {@linkplain Card card}. It is up to the implementor to employ an
   * algorithm that determines which cards to use, but here are some suggestions: <ul> <li>Use the
   * color with the lowest number of cards necessary to match the length of the route.</li> <li>Do
   * not use a wild card unless absolutely necessary (i.e. the player has length-1 cards of some
   * color in hand and it is the most numerous card that the player holds).</li> </ul>
   *
   * @param route The {@link Route} to claim.
   * @throws RailroadBaronsException If the {@link Route} cannot be claimed, i.e. if the {@link
   //* #canClaimRoute(Route)} method returns false.
   */
  @Override
  public void claimRoute(model.Route route) throws RailroadBaronsException {

  }

  /**
   * Returns the {@linkplain Collection collection} of {@linkplain Route routes} claimed by this
   * player.
   *
   * @return The {@link Collection} of {@linkplain Route Routes} claimed by this player.
   */
  @Override
  public Collection<model.Route> getClaimedRoutes() {
    return null;
  }

  /**
   * Returns the players current score based on the {@linkplain Route#getPointValue() point value}
   * of each {@linkplain Route route} that the player has currently claimed.
   *
   * @return The player's current score.
   */
  @Override
  public int getScore() {
    return 0;
  }

  /**
   * Returns true iff the following conditions are true:
   *
   * <ul> <li>The player has enough {@linkplain Card cards} (including {@linkplain Card#WILD wild
   * cards}) to claim a {@linkplain Route route} of the specified length.</li> <li>The player has
   * enough train pieces to claim a {@linkplain Route route} of the specified length.</li> </ul>
   *
   * @param shortestUnclaimedRoute The length of the shortest unclaimed {@link Route} in the current
   * game.
   * @return True if the player can claim such a {@link Route route}, and false otherwise.
   */
  @Override
  public boolean canContinuePlaying(int shortestUnclaimedRoute) {
    return false;
  }
}
