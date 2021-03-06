package student;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import model.Baron;
import model.Card;
import model.PlayerObserver;
import model.RailroadBaronsException;

/**
 * Concrete implementation of Player Interface
 *
 * @author PedroBreton and Tyler Miller
 */
public class Player implements model.Player {

  private int trainPieces;
  protected HashMap<Card, Integer> hand;
  private int score;
  private HashSet<model.Route> routes;
  protected model.Pair lastTwo;
  protected ArrayList<PlayerObserver> observers;
  private Baron baron;
  protected boolean hasClaimedRoute;

  private Graph<model.Station> mapGraph;
  private boolean northSouthBonus;
  private boolean eastWestBonus;

  private int northenmost;
  private int southernmost;
  private int easternmost;
  private int westernmost;

  private Station stationNorth;
  private Station stationSouth;
  private Station stationEast;
  private Station stationWest;

  /**
   * Construct an instance of the Payer class
   *
   * @param baron player's baron
   */
  public Player(Baron baron) {
    this.baron = baron;
    hand = new HashMap<>();
    for (Card card : Arrays.stream(Card.values())
        .filter(card -> !card.equals(Card.NONE) && !card.equals(Card.BACK)).collect(
            Collectors.toList())) {
      hand.put(card, 0);
    }
    hand.put(Card.NONE, Integer.MIN_VALUE);
    routes = new HashSet<>();
    observers = new ArrayList<>();
    score = 0;
    hasClaimedRoute = false;

    stationNorth = new Station("North", Integer.MIN_VALUE, Integer.MIN_VALUE);
    stationEast = new Station("East", Integer.MIN_VALUE, Integer.MAX_VALUE);
    stationSouth = new Station("South", Integer.MAX_VALUE, Integer.MAX_VALUE);
    stationWest = new Station("West", Integer.MAX_VALUE, Integer.MIN_VALUE);
    mapGraph = new Graph<>(stationNorth,stationSouth,stationEast,stationWest);

    mapGraph.addVertex(stationNorth);
    mapGraph.addVertex(stationEast);
    mapGraph.addVertex(stationSouth);
    mapGraph.addVertex(stationWest);
  }

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
    for (Card card : hand.keySet()) {
      hand.put(card, 0);
    }
    score = 0;
    routes.clear();
    lastTwo = new student.Pair(Card.NONE, Card.NONE);

    for (Card card : dealt) {
      hand.put(card, hand.get(card) + 1);
    }

    for (PlayerObserver observer: observers){
      observer.playerChanged(this);
    }

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
  public void startTurn(model.Pair dealt) throws RailroadBaronsException {
    lastTwo = dealt;
    hand.put(dealt.getFirstCard(), hand.get(dealt.getFirstCard()) + 1);
    hand.put(dealt.getSecondCard(), hand.get(dealt.getSecondCard()) + 1);
    hasClaimedRoute = false;
    for (PlayerObserver observer: observers){
      observer.playerChanged(this);
    }
  }

  /**
   * Returns the most recently dealt {@linkplain Pair pair of cards}. Note that one or both of the
   * {@linkplain Card cards} may have a value of {@link Card#NONE}.
   *
   * @return The most recently dealt {@link Pair} of {@link Card Cards}.
   */
  @Override
  public model.Pair getLastTwoCards() {
    return lastTwo;
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
    return hand.get(card);
  }

  /**
   * Returns the number of game pieces that the player has remaining. Note that the number may be
   * 0.
   *
   * @return The number of game pieces that the player has remaining.
   */
  @Override
  public int getNumberOfPieces() {
    return trainPieces;
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
    int routeLength = route.getLength();

    if ((route.getBaron() != Baron.UNCLAIMED) || hasClaimedRoute || (route.getLength()
        > trainPieces)) {
      return false;
    } else {
      for (Card card : hand.keySet()) {
        if (card == Card.WILD) {
          continue;
        } else if (hand.get(Card.WILD) > 0 && (hand.get(card) + 1 >= routeLength) && !(
            hand.get(card) == 0)) {
          return true;
        } else if (hand.get(card) >= routeLength) {
          return true;
        }
      }
    }
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
   * @throws RailroadBaronsException If the {@link Route} cannot be claimed, i.e. if the {@link //*
   * #canClaimRoute(Route)} method returns false.
   */
  @Override
  public void claimRoute(model.Route route) throws RailroadBaronsException {
    if (!canClaimRoute(route)) {
      throw new RailroadBaronsException("Route can't be claimed!");
    } else if (hasClaimedRoute) {
      throw new RailroadBaronsException("Already claimed a route this turn!");
    } else {
      int length = route.getLength();
      connect(route);
      routes.add(route);
      route.claim(baron);
      trainPieces -= length;
      score += route.getPointValue();



      for (PlayerObserver observer: observers){
        observer.playerChanged(this);
      }
      boolean usingWild = false;

      Card cardToUse = Card.NONE;
      int currRightNumber = Integer.MAX_VALUE;

      for (Card card : hand.keySet()) {
        int currCardAmount = hand.get(card);

        if (currCardAmount >= length && currCardAmount < currRightNumber) {
          currRightNumber = currCardAmount;
          cardToUse = card;
          hasClaimedRoute = true;
        }
      }

      if (cardToUse == Card.NONE && hand.get(Card.WILD) > 0) {
        for (Card card : hand.keySet()) {
          int currCardAmount = hand.get(card);

          if (currCardAmount >= length - 1 && currCardAmount < currRightNumber) {
            currRightNumber = currCardAmount;
            cardToUse = card;
            usingWild = true;
            hasClaimedRoute = true;
          }
        }
      }

      if (usingWild) {
        hand.put(cardToUse, hand.get(cardToUse) - currRightNumber);
        hand.put(Card.WILD, hand.get(cardToUse) - 1);
      } else {
        hand.put(cardToUse, hand.get(cardToUse) - currRightNumber);
      }

      for (PlayerObserver observer: observers){
        observer.playerChanged(this);
      }

    }
  }

  /**
   * Returns the {@linkplain Collection collection} of {@linkplain Route routes} claimed by this
   * player.
   *
   * @return The {@link Collection} of {@linkplain Route Routes} claimed by this player.
   */
  @Override
  public Collection<model.Route> getClaimedRoutes() {
    return routes;
  }

  /**
   * Returns the players current score based on the {@linkplain Route#getPointValue() point value}
   * of each {@linkplain Route route} that the player has currently claimed.
   *
   * @return The player's current score.
   */
  @Override
  public int getScore() {
    return score;
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
    boolean canContinue = false;

    if (shortestUnclaimedRoute > trainPieces) {
      return false;
    }

    for (Card card : hand.keySet()) {

      if (card == Card.WILD) {
        continue;
      }

      if (hand.get(Card.WILD) > 0 && hand.get(card) >= shortestUnclaimedRoute - 1
          && hand.get(card) != 0) {
        canContinue = true;
      }
      if (hand.get(card) >= shortestUnclaimedRoute) {
        canContinue = true;
      }
    }

    return canContinue;
  }

  /**
   * Getter for the hand. Used for testing
   * @return hashmap representing hand.
   */
  public HashMap<Card, Integer> getHand() {
    return hand;
  }

  /**
   * Getter for whether or not the player has claimed a route this turn. Used for testing.
   * @return true if has claimed a route, false otherwise
   */
  public boolean getRoutesClaimedThisTurn() {
    return hasClaimedRoute;
  }

  /**
   * Set the number of train pieces a player has left. Used for testing purposes.
   * @param trainPieces number of train pieces to set it to
   */
  public void setTrainPieces(int trainPieces) {
    this.trainPieces = trainPieces;
  }

  /**
   * Getter for the players observers
   * @return list of the players observers.
   */
  public ArrayList<PlayerObserver> getObservers() {
    return observers;
  }

  /**
   * Getter for whether or not the player has claimed a route this turn. Used for testing.
   * @return true if has claimed a route, false otherwise
   */
  public boolean isHasClaimedRoute() {
    return hasClaimedRoute;
  }

  /**
   * Sets whether the player has claimed a route before. Used for testing.
   * @param hasClaimedRoute the boolean to check if the player has claimed a route.
   */
  public void setHasClaimedRoute(boolean hasClaimedRoute) {
    this.hasClaimedRoute = hasClaimedRoute;
  }

  /**
   * String representation of a player
   * @return the player color
   */
  @Override
  public String toString(){
    return getBaron().toString();
  }

  /**
   * Sets the location of the northernmost route owned
   * @param northenmost the location of the northernmost route
   */
  public void setNorthenmost(int northenmost) {
    this.northenmost = northenmost;
  }

  /**
   * Sets the location of the southernmost route owned
   * @param southernmost the location of the southernmost route
   */
  public void setSouthernmost(int southernmost) {
    this.southernmost = southernmost;
  }

  /**
   * Sets the location of the easternmost route owned
   * @param easternmost the location of the easternmost route
   */
  public void setEasternmost(int easternmost) {
    this.easternmost = easternmost;
  }

  /**
   * Sets the location of the westernmost route owned
   * @param westernmost the location of the westernmost route
   */
  public void setWesternmost(int westernmost) {
    this.westernmost = westernmost;
  }

  /**
   * Attempts to connect two routes that are owned by this player
   * @param route the route attempting to be connected
   */
  private void connect(model.Route route){
    model.Station origin = route.getOrigin();
    model.Station destination = route.getDestination();
    mapGraph.addVertex(origin);
    mapGraph.addVertex(destination);
    mapGraph.connect(origin, destination);

    if (origin.getRow() == northenmost){
      mapGraph.connect(stationNorth, origin);
    }

    if (destination.getRow() == northenmost){
      mapGraph.connect(stationNorth,destination);
    }

    if (destination.getRow() == southernmost){
      mapGraph.connect(stationSouth, destination);
    }

    if (origin.getRow() == southernmost){
      mapGraph.connect(stationSouth, origin);
    }

    if (origin.getCol() == westernmost ){
      mapGraph.connect(stationWest, origin);
    }

    if (destination.getCol() == westernmost){
      mapGraph.connect(stationWest, destination);
    }

    if (destination.getCol() == easternmost){
      mapGraph.connect(stationEast, destination);
    }

    if (origin.getCol() == easternmost){
      mapGraph.connect(stationEast, origin);
    }


    if (!northSouthBonus  && mapGraph.depthFirstSearch(stationNorth,stationSouth)){
      northSouthBonus = true;
      score += 5 * (easternmost+1);
    }

    if (!eastWestBonus && mapGraph.depthFirstSearch(stationEast, stationWest)){
      eastWestBonus = true;
      score += 5*(southernmost+1);
    }

  }
}

