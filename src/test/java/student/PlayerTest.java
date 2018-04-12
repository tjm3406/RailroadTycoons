package student;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import model.Baron;
import model.Card;
import model.RailroadBaronsException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  private Player player1;
  private Player player2;
  private model.Pair dummyPair;
  private model.RailroadMap dummyRailroadMap;

  @Before
  public void setUp() throws Exception {
    player1 = new student.Player(Baron.RED);
    player2 = new student.Player(Baron.BLUE);
    dummyPair = new Pair(Card.BLUE, Card.RED);
    MapMaker mapMaker = new MapMaker();

    FileInputStream inputStream = new FileInputStream("maps/simple.rbmap");
    dummyRailroadMap = mapMaker.readMap(inputStream);
    inputStream.close();

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

    assertEquals("Hand should be empty", 0, player1.getHand().size());
    assertEquals("Expected 35 train pieces", 45, player1.getNumberOfPieces());
    assertEquals("Last two should be NONE", Card.NONE, player1.getLastTwoCards().getFirstCard());
    assertEquals("Last two should be NONE", Card.NONE, player1.getLastTwoCards().getSecondCard());
    assertEquals("No claimed routes", 0, player1.getClaimedRoutes().size());
    assertEquals("Baron should be red", Baron.RED, player1.getBaron());
  }

  @Test
  public void addPlayerObserver() {
  }

  @Test
  public void removePlayerObserver() {
  }

  @Test
  public void getBaron() {
  }

  @Test
  public void startTurn() {
  }

  @Test
  public void getLastTwoCards() {
  }

  @Test
  public void countCardsInHand() {
  }

  @Test
  public void getNumberOfPieces() {
  }

  @Test
  public void canClaimRoute() {
  }

  @Test
  public void claimRoute() {
  }

  @Test
  public void getClaimedRoutes() {
  }

  @Test
  public void getScore() {
  }

  @Test
  public void canContinuePlaying() {
  }
}