package student;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import model.Baron;
import model.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
  private model.Player player1;
  private model.Player player2;
  private model.Pair pair;
  private model.RailroadMap dummyRailroadMap;

  @Before
  public void setUp() throws Exception {
    player1 = new student.Player(Baron.RED);
    player2 = new student.Player(Baron.RED);
    pair = new Pair(Card.BLUE, Card.RED);
    MapMaker mapMaker = new MapMaker();

    FileInputStream inputStream = new FileInputStream("maps/simple.rbmap");
    model.RailroadMap dummyRailroadMap = mapMaker.readMap(inputStream);
    inputStream.close();

  }

  @After
  public void tearDown() throws Exception {
    player1 = null;
    player2 = null;
    pair = null;
    dummyRailroadMap = null;

  }

  @Test
  public void reset() {

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