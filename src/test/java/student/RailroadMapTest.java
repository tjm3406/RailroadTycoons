package student;

import static org.junit.Assert.*;

import java.util.ArrayList;
import model.Orientation;
import model.RailroadMapObserver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test unit for the student.RailroadMap class
 *
 * @author Pedro Breton
 */
public class RailroadMapTest {

  private RailroadMap railroadMap;
  private Station dummyStation1;
  private Route dummyRoute1;
  private Route dummyRoute2;
  private RailroadMapObserver fakeObserver;

  @Before
  public void setUp() throws Exception {
    dummyStation1 = new Station("Crux Station", 2, 2);
    Station dummyStation2 = new Station("East End Station", 2, 10);
    Station dummyStation3 = new Station("South Bend Station", 12, 2);

    dummyRoute1 = new Route(dummyStation1, dummyStation2, Orientation.HORIZONTAL);
    dummyRoute2 = new Route(dummyStation1, dummyStation3, Orientation.VERTICAL);

    ArrayList<model.Route> routes = new ArrayList<>();
    routes.add(dummyRoute1);
    routes.add(dummyRoute2);

    railroadMap = new RailroadMap(routes);

    fakeObserver = new RailroadMapObserver() {
      @Override
      public void routeClaimed(model.RailroadMap map, model.Route route) {
      }
    };


  }

  @After
  public void tearDown() throws Exception {
    fakeObserver = null;
    railroadMap = null;
    dummyRoute1 = null;
    dummyRoute1 = null;
    dummyRoute2 = null;
  }

  @Test
  public void addObserver() {
    System.out.println("Running addObserver() test");
    assertTrue("No observers yet", railroadMap.getObservers().isEmpty());

    railroadMap.addObserver(fakeObserver);
    assertEquals("One observer present", 1, railroadMap.getObservers().size());

  }

  @Test
  public void removeObserver() {
    System.out.println("Running removeObserver() test");

    railroadMap.addObserver(fakeObserver);
    assertEquals("One observer present", 1, railroadMap.getObservers().size());
    railroadMap.removeObserver(fakeObserver);
    assertFalse("Observer removed", railroadMap.getObservers().contains(fakeObserver));

  }

  @Test
  public void getRows() {
    System.out.println("Running getRows() test");
    assertEquals("Number of rows is 13", 13, railroadMap.getRows());

  }

  @Test
  public void getCols() {
    System.out.println("Running getCols() test");
    assertEquals("Number of columns is 11", 11, railroadMap.getCols());
  }

  @Test
  public void getSpace() {
    System.out.println("Running getSpace() test");

    //Get empty space
    assertFalse("Empty space", railroadMap.getSpace(0, 0) instanceof Track ||
        railroadMap.getSpace(0, 0) instanceof Station);

    //Get dummyStation1
    assertEquals("Get dummyStation1", dummyStation1, railroadMap.getSpace(2, 2));

    //Get a track
    assertTrue("A track", railroadMap.getSpace(2, 3) instanceof Track);


  }

  @Test
  public void getRoute() {
    System.out.println("Running getRoute() test");

    //Get route of non-track space
    assertNull("No route on space", railroadMap.getRoute(0, 0));

    //Get dummyRoute1
    assertEquals("Expected dummyRoute1", dummyRoute1, railroadMap.getRoute(2, 5));

    //Get dummyRoute2
    assertEquals("Expected dummyRoute2", dummyRoute2, railroadMap.getRoute(5, 2));
  }

  @Test
  public void routeClaimed() {
    System.out.println("Running routeClaimed() test");

    railroadMap.addObserver(fakeObserver);
    assertTrue("dummyRoute1 not yet claimed",
        railroadMap.getUnclaimedRoutes().contains(dummyRoute1));
    railroadMap.routeClaimed(dummyRoute1);
    assertFalse("dummyRoute1 claimed", railroadMap.getUnclaimedRoutes().contains(dummyRoute1));

  }

  @Test
  public void getLengthOfShortestUnclaimedRoute() {
    System.out.println("Running getLengthOfShortestUnclaimedRoute() test");

    assertEquals("Expected length of 7", 7, railroadMap.getLengthOfShortestUnclaimedRoute());
    railroadMap.routeClaimed(dummyRoute1);
    assertEquals("Expected length of 9", 9, railroadMap.getLengthOfShortestUnclaimedRoute());

  }

  @Test
  public void getRoutes() {
    System.out.println("Running getRoutes() test");
    assertTrue("dummyRoute1 is in here", railroadMap.getRoutes().contains(dummyRoute1));
    assertTrue("dummyRoute2 is in here", railroadMap.getRoutes().contains(dummyRoute2));
  }
}
