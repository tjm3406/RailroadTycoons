package student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import model.Baron;
import model.Card;
import model.RailroadBaronsException;
import model.RailroadBaronsObserver;


/**
 * Concrete implementation of the RailroadBarons class
 *
 * @Author Tyler Miller and Pedro Breton
 */

public class RailroadBarons implements model.RailroadBarons {
  private ArrayList<RailroadBaronsObserver> observers;
  private model.RailroadMap railroadMap;
  private model.Deck deck;
  private ArrayList<model.Player> players;
  private int currentPlayer;

  public RailroadBarons() {
    this.observers = new ArrayList<>();
    this.players = new ArrayList<>();
    model.Player player1 = new Player(Baron.RED);
    model.Player player2 = new Player(Baron.BLUE);
    model.Player player3 = new Player(Baron.GREEN);
    model.Player player4 = new Player(Baron.YELLOW);
    players.add(player1);
    players.add(player2);
    players.add(player3);
    players.add(player4);


  }

  /**
   * Adds a new {@linkplain RailroadBaronsObserver observer} to the {@linkplain Collection
   * collection} of observers that will be notified when the state of the game changes. Game state
   * changes include: <ul> <li>A player's turn begins.</li> <li>A player's turn ends.</li> <li>The
   * game is over.</li> </ul>
   *
   * @param observer The {@link RailroadBaronsObserver} to add to the {@link Collection} of
   * observers.
   */
  @Override
  public void addRailroadBaronsObserver(RailroadBaronsObserver observer) {
    observers.add(observer);

  }

  /**
   * Removes the {@linkplain RailroadBaronsObserver observer} from the collection of observers that
   * will be notified when the state of the game changes.
   *
   * @param observer The {@link RailroadBaronsObserver} to remove.
   */
  @Override
  public void removeRailroadBaronsObserver(RailroadBaronsObserver observer) {
    observers.remove(observer);

  }

  /**
   * Starts a new {@linkplain RailroadBarons Railroad Barons} game with the specified {@linkplain
   * RailroadMap map} and a default {@linkplain Deck deck of cards}. If a game is currently in
   * progress, the progress is lost. There is no warning!
   *
   * By default, a new game begins with: <ul> <li>A default deck that contains 20 of each color of
   * card and 20 wild cards.</li> <li>4 players, each of which has 45 train pieces.</li> <li>An
   * initial hand of 4 cards dealt from the deck to each player</li> </ul>
   *
   * @param map The {@link RailroadMap} on which the game will be played.
   */
  @Override
  public void startAGameWith(model.RailroadMap map) {
    railroadMap = map;
    deck = new Deck();
    model.RailroadBarons railroadBarons = new RailroadBarons();


    deck.reset();



    for (model.Player player : players){
      player.reset(deck.drawACard(),deck.drawACard(),deck.drawACard(),deck.drawACard());
    }

    players.get(currentPlayer).startTurn(new Pair(deck.drawACard(), deck.drawACard()));
    for (model.RailroadBaronsObserver observer : observers){
      observer.turnStarted(this, players.get(currentPlayer));
    }



  }

  /**
   * Starts a new {@linkplain RailroadBarons Railroad Barons} game with the specified {@linkplain
   * RailroadMap map} and {@linkplain Deck deck of cards}. This means that the game should work with
   * any implementation of the {@link Deck} interface (not just a specific implementation)!
   * Otherwise, the starting state of the game is the same as a {startAGameWIth normal map)
   *
   * @param map The {@link RailroadMap} on which the game will be played.
   * @param deck The {@link Deck} of cards used to play the game. This may be ANY implementation of
   * the {@link Deck} interface, meaning that a valid implementation of the {@link RailroadBarons}
   * interface should use only the {@link Deck} interface and not a specific implementation.
   */
  @Override
  public void startAGameWith(model.RailroadMap map, model.Deck deck) {
    railroadMap = map;
    this.deck = deck;
    model.RailroadBarons railroadBarons = new RailroadBarons();
    deck.reset();


    for (model.Player player : players){
      player.reset(deck.drawACard(),deck.drawACard(),deck.drawACard(),deck.drawACard());
    }

    players.get(currentPlayer).startTurn(new Pair(deck.drawACard(), deck.drawACard()));
    for (model.RailroadBaronsObserver observer : observers){
      observer.turnStarted(this, players.get(currentPlayer));
    }
  }

  /**
   * Returns the {@linkplain RailroadMap map} currently being used for play. If a game is not in
   * progress, this may be null!
   *
   * @return The {@link RailroadMap} being used for play.
   */
  @Override
  public model.RailroadMap getRailroadMap() {
    return railroadMap;
  }

  /**
   * Returns the number of {@linkplain Card cards} that remain to be dealt in the current game's
   * {@linkplain Deck deck}.
   *
   * @return The number of cards that have not yet been dealt in the game's {@link Deck}.
   */
  //
  @Override
  public int numberOfCardsRemaining() {
    return deck.numberOfCardsRemaining();
  }

  /**
   * Returns true iff the current {@linkplain Player player} can claim the {@linkplain Route route}
   * at the specified location, i.e. the player has enough cards and pieces, and the route is not
   * currently claimed by another player. Should delegate to the
   * method on the current player.
   *
   * @param row The row of a {@link Track} in the {@link Route} to check.
   * @param col The column of a {@link Track} in the {@link Route} to check.
   * @return True iff the {@link Route} can be claimed by the current player.
   */
  @Override
  public boolean canCurrentPlayerClaimRoute(int row, int col) {
    model.Route currRoute = railroadMap.getRoute(row,col);
    return players.get(currentPlayer).canClaimRoute(currRoute);
  }

  /**
   * Attempts to claim the {@linkplain Route route} at the specified location on behalf of the
   * current {@linkplain Player player}.
   *
   * @param row The row of a {@link Track} in the {@link Route} to claim.
   * @param col The column of a {@link Track} in the {@link Route} to claim.
   * @throws RailroadBaronsException If the {@link Route} cannot be claimed by the current player.
   */
  @Override
  public void claimRoute(int row, int col) throws RailroadBaronsException {
    model.Route currRoute = railroadMap.getRoute(row,col);
    players.get(currentPlayer).claimRoute(currRoute);
    railroadMap.routeClaimed(currRoute);


  }

  /**
   * Called when the current {@linkplain Player player} ends their turn.
   */
  @Override
  public void endTurn() {
    if (!gameIsOver()) {
      for (RailroadBaronsObserver observer : observers) {
        observer.turnEnded(this, players.get(currentPlayer));
      }

      currentPlayer = (currentPlayer + 1) % 4;

      players.get(currentPlayer).startTurn(new Pair(deck.drawACard(), deck.drawACard()));
      for (RailroadBaronsObserver observer : observers) {
        observer.turnStarted(this, players.get(currentPlayer));
      }
    } else {
      for (RailroadBaronsObserver observer : observers){
        observer.gameOver(this, highestScore(players));
      }
    }


  }

  /**
   * Returns the {@linkplain Player player} whose turn it is.
   *
   * @return The {@link Player} that is currently taking a turn.
   */
  @Override
  public model.Player getCurrentPlayer() {
    return players.get(currentPlayer);
  }

  /**
   * Returns all of the {@linkplain Player players} currently playing the game.
   *
   * @return The {@link Player Players} currently playing the game.
   */
  @Override
  public Collection<model.Player> getPlayers() {
    return players;
  }

  /**
   * Indicates whether or not the game is over. This occurs when no more plays can be made. Reasons
   * include: <ul> <li>No one player has enough pieces to claim a route.</li> <li>No one player has
   * enough cards to claim a route.</li> <li>All routes have been claimed.</li> </ul>
   *
   * @return True if the game is over, false otherwise.
   */
  @Override
  public boolean gameIsOver() {
    int shortestRoute = railroadMap.getLengthOfShortestUnclaimedRoute();
    if(shortestRoute == 0) {
      return true;
    }

    for(model.Player player : players) {
      if(player.canContinuePlaying(shortestRoute-deck.numberOfCardsRemaining())) {
        return false;
      }
    }
    return true;
  }

  public model.Player highestScore(ArrayList<model.Player> players){
    return players.stream().max(Comparator.comparingInt(player -> player.getScore())).get();
  }
}
