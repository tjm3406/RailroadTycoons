package student;

import model.Baron;
import model.PlayerObserver;
import model.RailroadBarons;
import model.RailroadBaronsException;
import model.RailroadMap;
import model.Route;

/**
 * The class to represent an AI player
 */
public class ComputerPlayer extends Player{
  private RailroadBarons railroadBarons;


  /**
   * Construct an instance of the Payer class
   *
   * @param baron player's baron
   */
  public ComputerPlayer(Baron baron, RailroadBarons railroadBarons) {
    super(baron);
    this.railroadBarons = railroadBarons;
  }

  /**
   * How the AI player makes a move
   * @throws RailroadBaronsException
   */
  public void play() throws RailroadBaronsException {
    Route route = null;

    for (Route routeAvailable : railroadBarons.getRailroadMap().getRoutes()){
      if (super.canClaimRoute(routeAvailable)){
        route = routeAvailable;
        break;
      }
    }


    if (route != null) {
      railroadBarons.claimRoute(route.getTracks().get(0).getRow(),
          route.getTracks().get(0).getCol());
    }

    railroadBarons.endTurn();
  }

  /**
   * Starts the turn of an AI player
   * @param dealt a {@linkplain Pair pair of cards} to the player. Note that one or both of these
   * @throws RailroadBaronsException
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
    play();
  }
}
