package student;

import static org.junit.Assert.*;

import model.Baron;
import model.Orientation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test unit for the student.Route class
 *
 * @author PedroBreton
 */
public class RouteTest {

  private Station origin;
  private Station destination;
  private Station destination2;
  private Route dummyRoute1;
  private Route dummyRoute2;

  @Before
  public void setUp() {
    origin = new Station("OriginStation", 1, 0);
    destination = new Station("DestinationStation", 3, 0);
    destination2= new Station("DestinationStation2", 1, 3);
    dummyRoute1 = new Route(origin, destination, Orientation.VERTICAL);
    dummyRoute2 = new Route(origin, destination2, Orientation.HORIZONTAL);
  }

  @After
  public void tearDown() {
    origin = null;
    destination = null;
    dummyRoute1 = null;
  }

  @Test
  public void getOrigin() {
    System.out.println("Running getOrigin() test");
    assertEquals("Wrong origin station", origin, dummyRoute1.getOrigin());
  }

  @Test
  public void getDestination() {
    System.out.println("Running getDestination() test");
    assertEquals("Wrong destination orientation", destination, dummyRoute1.getDestination());
  }

  @Test
  public void getOrientation() {
    System.out.println("Running getOrientation() test");
    assertEquals("Wrong orientation", Orientation.VERTICAL, dummyRoute1.getOrientation());
  }

  @Test
  public void getTracks() {
    System.out.println("Running getTracks() test");
    assertEquals("Wrong track col", 0, dummyRoute1.getTracks().get(0).getCol());
    assertEquals("Wrong track row", 2, dummyRoute1.getTracks().get(0).getRow());
  }

  @Test
  public void getLength() {
    System.out.println("Running getLength() test");
    assertEquals("Wrong length", 1, dummyRoute1.getLength());
  }

  @Test
  public void getPointValue() {
    System.out.println("Running getPointValue() test");
    assertEquals("Wrong points", 1, dummyRoute1.getPointValue());
  }

  @Test
  public void includesCoordinate() {
    System.out.println("Running includesCoodinates() test");
    assertTrue("Expected true", dummyRoute1.includesCoordinate(new Space(1, 0)));
    assertTrue("Expected true", dummyRoute1.includesCoordinate(new Space(2, 0)));
    assertTrue("Expected true", dummyRoute1.includesCoordinate(new Space(3, 0)));

    assertFalse("Expected false", dummyRoute1.includesCoordinate(new Space(2, 1)));

    assertTrue("Expected true", dummyRoute2.includesCoordinate(new Space(1, 0)));
    assertTrue("Expected true", dummyRoute2.includesCoordinate(new Space(1, 1)));
    assertTrue("Expected true", dummyRoute2.includesCoordinate(new Space(1, 2)));

    assertFalse("Expected false", dummyRoute2.includesCoordinate(new Space(2, 1)));
  }

  @Test
  public void getBaron() {
    System.out.println("Running getBaron() test");
    assertEquals("Expected Unclaimed", Baron.UNCLAIMED, dummyRoute1.getBaron());
  }

  @Test
  public void claim() {
    System.out.println("Running claim() test");
    assertEquals("Route has not been claimed yet", Baron.UNCLAIMED, dummyRoute1.getBaron());
    assertTrue("Route is available to be claimed", dummyRoute1.claim(Baron.BLUE));
    assertEquals("Route has been claimed by Blue", Baron.BLUE, dummyRoute1.getBaron());
    assertEquals("Track has been been claimed yet", Baron.BLUE,
        dummyRoute1.getTracks().get(0).getBaron());
    assertFalse("Route not available to be claimed", dummyRoute1.claim(Baron.RED));

  }
}