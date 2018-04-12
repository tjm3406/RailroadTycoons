package student;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;
import model.Baron;
import model.Card;
import model.Orientation;
import model.PlayerObserver;
import model.RailroadBaronsException;
import model.Route;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  private Player player1;
  private Player player2;
  private model.Pair dummyPair;
  private model.RailroadMap dummyRailroadMap;
  private PlayerObserver fakeObserver;

  @Before
  public void setUp() throws Exception {
    player1 = new student.Player(Baron.RED);
    player2 = new student.Player(Baron.BLUE);
    dummyPair = new Pair(Card.BLUE, Card.RED);
    MapMaker mapMaker = new MapMaker();

    FileInputStream inputStream = new FileInputStream("maps/simple.rbmap");
    dummyRailroadMap = mapMaker.readMap(inputStream);
    inputStream.close();

    fakeObserver = new PlayerObserver() {
      @Override
      public void playerChanged(model.Player player) {

      }
    };

  }

  @After
  public void tearDown() throws Exception {
    player1 = null;
    player2 = null;
    dummyPair = null;
    dummyRailroadMap = null;

  }


  @Test
  public void reset() throws RailroadBaronsException {
    System.out.println("Running reset() test");
    player1.getHand().put(Card.RED, 8);
    player1.getHand().put(Card.WILD, 4);
    player1.setTrainPieces(15);
    player1.claimRoute(dummyRailroadMap.getRoute(2,5));
    player1.startTurn(dummyPair);
    player1.reset();

    HashMap<Card, Integer> hand = new HashMap<>();
    for (Card card : Arrays.stream(Card.values()).filter(card -> !card.equals(Card.NONE) && !card.equals(Card.BACK)).collect(
        Collectors.toList())) {
      hand.put(card, 0);
    }
    assertEquals("Hand should be empty", hand, player1.getHand());
    assertEquals("Expected 45 train pieces", 45, player1.getNumberOfPieces());
    assertEquals("Last two should be NONE", Card.NONE, player1.getLastTwoCards().getFirstCard());
    assertEquals("Last two should be NONE", Card.NONE, player1.getLastTwoCards().getSecondCard());
    assertEquals("No claimed routes", 0, player1.getClaimedRoutes().size());
    assertEquals("Baron should be red", Baron.RED, player1.getBaron());
  }

  @Test
  public void addPlayerObserver() {
    System.out.println("Running addObserver() test");
    assertTrue("No observers yet", player1.getObservers().isEmpty());

    player1.addPlayerObserver(fakeObserver);
    assertEquals("One observer present", 1, player1.getObservers().size());
  }

  @Test
  public void removePlayerObserver() {
    System.out.println("Running removeObserver() test");

    player1.addPlayerObserver(fakeObserver);
    assertEquals("One observer present", 1, player1.getObservers().size());
    player1.removePlayerObserver(fakeObserver);
    assertFalse("Observer removed", player1.getObservers().contains(fakeObserver));
  }

  @Test
  public void getBaron() {
    System.out.println("Running getBaron() test");

    assertEquals("Get player 1", Baron.RED, player1.getBaron());
    assertEquals("Get player 2", Baron.BLUE, player2.getBaron());
  }

  @Test
  public void startTurn() {
    System.out.println("Running startTurn() test");
    player1.getHand().put(Card.RED, 3);
    player1.getHand().put(Card.WILD, 2);
    player1.startTurn(dummyPair);

    assertEquals("4 Red Cards Expected", 4, player1.countCardsInHand(Card.RED));
    assertEquals("1 Blue Card Expected", 1, player1.countCardsInHand(Card.BLUE));

  }

  @Test
  public void getLastTwoCards() {
    System.out.println("Running getLastTwoCard()");
    player1.startTurn(dummyPair);

    assertEquals("Pair of 1 red and 1 blue card Expected", dummyPair, player1.getLastTwoCards());
  }

  @Test
  public void countCardsInHand() {
    System.out.println("Running countCardsInHand() test");
    assertEquals("No Red cards in hand", 0, player1.countCardsInHand(Card.RED));
    player1.getHand().put(Card.RED, 8);
    assertEquals("8 Red cards in hand", 8, player1.countCardsInHand(Card.RED));
    player1.getHand().put(Card.WILD, 4);
    assertEquals("4 Wild cards in hand", 4, player1.countCardsInHand(Card.WILD));
  }

  @Test
  public void getNumberOfPieces() {
    System.out.println("Running countCardsInHand() test");
    player1.setTrainPieces(45);
    assertEquals("Expected 5 train pieces", 45, player1.getNumberOfPieces());
    player1.setTrainPieces(5);
    assertEquals("Expected 5 train pieces", 5, player1.getNumberOfPieces());
    player1.setTrainPieces(10);
    assertEquals("Expected 5 train pieces", 10, player1.getNumberOfPieces());
  }

  @Test
  public void canClaimRoute() {
    Route route1 = dummyRailroadMap.getRoute(2, 5);
    Route route2 = dummyRailroadMap.getRoute(5, 2);

    player1.reset();
    assertFalse("Enough train pieces but not enough cards", player1.canClaimRoute(route1));
    assertFalse("Enough train pieces but not enough cards", player1.canClaimRoute(route2));

    player1.getHand().put(Card.RED, route1.getLength());
    assertTrue("Should be able to claim", player1.canClaimRoute(route1));
    player1.getHand().put(Card.BLUE, route2.getLength());
    assertTrue("Should be able to claim", player1.canClaimRoute(route2));
    player1.reset();

    player1.getHand().put(Card.RED, route1.getLength());
    player1.setTrainPieces(0);
    assertFalse("Enough cards but not enough pieces", player1.canClaimRoute(route1));
    player1.getHand().put(Card.BLUE, route2.getLength());
    assertFalse("Enough cards but not enough pieces", player1.canClaimRoute(route2));
    player1.reset();

    player1.getHand().put(Card.RED, route1.getLength()-1);
    assertFalse("Enough train pieces but not enough cards", player1.canClaimRoute(route1));
    player1.getHand().put(Card.BLUE, route2.getLength()-1);
    assertFalse("Enough train pieces but not enough cards", player1.canClaimRoute(route2));
    player1.reset();

    player1.getHand().put(Card.RED, route1.getLength()-1);
    player1.getHand().put(Card.WILD, 1);
    assertTrue("Enough cards with 1 wild card", player1.canClaimRoute(route1));
    player1.getHand().put(Card.BLUE, route2.getLength()-1);
    assertTrue("Enough cards with 1 wild card", player1.canClaimRoute(route2));
    player1.reset();

    player1.getHand().put(Card.WILD, route1.getLength() + route2.getLength());
    assertFalse("Cannot use only wildcards to claim a route", player1.canClaimRoute(route1));
    assertFalse("Cannot use only wildcards to claim a route", player1.canClaimRoute(route2));
    player1.reset();

    model.Route length1Route = new student.Route(new Station("a",0, 0), new Station("b", 0, 2),
        Orientation.HORIZONTAL);
    player1.getHand().put(Card.WILD, 4);
    assertFalse("Cannot use a wildcard to claim a length 1 route", player1.canClaimRoute(length1Route));
    player1.reset();

    route1.claim(player1.getBaron());
    player1.getHand().put(Card.RED, route1.getLength());
    assertFalse("Cannot claim claimed route", player1.canClaimRoute(route1));
    player1.reset();

    player1.setHasClaimedRoute(true);
    assertFalse("Cannot claim more than one route per turn", player1.canClaimRoute(route1));





  }

  @Test
  public void claimRoute() {
  }

//  @Test
//  public void getClaimedRoutes() throws RailroadBaronsException {
//    System.out.println("Running getClaimedRoutes()");
//    model.Route dummyRoute1 = dummyRailroadMap.getRoute(2,5);
//    model.Route dummyRoute2 = dummyRailroadMap.getRoute(5,2);
//
//    player1.getHand().put(Card.RED, dummyRailroadMap.getRoute(2,5).getLength());
//    player1.getHand().put(Card.BLUE, dummyRailroadMap.getRoute(5,2).getLength());
//    player1.getHand().put(Card.WILD, 4);
//    player1.setTrainPieces(30);
//    player1.claimRoute(dummyRoute1);
//    player1.claimRoute(dummyRoute2);
//
//    assertEquals("True expected, route 2,5 should be in set", true,
//        player1.getClaimedRoutes().contains(dummyRoute1));
//    assertEquals("True expected, route 5,2 should be in set", true,
//        player1.getClaimedRoutes().contains(dummyRoute2));
//
//  }
//
//  @Test
//  public void getScore() throws RailroadBaronsException {
//    System.out.println("Running getScore()");
//
//    model.Route dummyRoute1 = dummyRailroadMap.getRoute(2,5);
//    model.Route dummyRoute2 = dummyRailroadMap.getRoute(5,2);
//
//    player1.getHand().put(Card.RED, dummyRailroadMap.getRoute(2,5).getLength());
//    player1.getHand().put(Card.BLUE, dummyRailroadMap.getRoute(5,2).getLength());
//    player1.getHand().put(Card.WILD, 4);
//    player1.setTrainPieces(30);
//    player1.claimRoute(dummyRoute1);
//    player1.claimRoute(dummyRoute2);
//
//    assertEquals("Score should be correct", dummyRoute1.getPointValue() + dummyRoute2.getPointValue(),
//        player1.getScore());
//
//  }

  @Test
  public void canContinuePlaying() {
  }
}