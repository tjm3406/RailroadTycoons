package student;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RailroadBaronsLonelyTest {

  private RailroadBaronsLonely dummyRailRoadBarons;

  @Before
  public void setUp() throws Exception {
    dummyRailRoadBarons = new RailroadBaronsLonely();

  }

  @After
  public void tearDown() throws Exception {
    dummyRailRoadBarons = null;
  }

  @Test
  public void constructor() {
    System.out.println("Testing constructor");

    assertEquals("Player list should be of length 4", 4, dummyRailRoadBarons.getPlayers().size());
    assertEquals("Player at the start should be the human player", false, dummyRailRoadBarons.getCurrentPlayer() instanceof ComputerPlayer);

    dummyRailRoadBarons.setCurrentPlayer(1);

    assertEquals("Player at index 1 should be a computer player", true, dummyRailRoadBarons.getCurrentPlayer() instanceof ComputerPlayer);
  }
}