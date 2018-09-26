package student;

import static org.junit.Assert.*;

import model.Baron;
import model.Orientation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test unit for the student.Track class
 *
 * @Author Tyler Miller and Pedro Breton
 */


public class TrackTest {

  private Track dummyTrack1;
  private Track dummyTrack2;
  private Station dummyStation1;
  private Station dummyStation2;
  private Station dummyStation3;
  private Route route1;
  private Route route2;

  @Before
  public void setUp() {
    dummyStation1 = new Station("Station1", 0, 0);
    dummyStation2 = new Station("Station3", 2, 0);
    dummyStation3 = new Station("Station2", 0, 2);

    route1 = new Route(dummyStation1, dummyStation2, Orientation.VERTICAL);
    route2 = new Route(dummyStation1, dummyStation3, Orientation.HORIZONTAL);

    dummyTrack1 = new Track(1, 0, route1);
    dummyTrack2 = new Track(0, 1, route2);

  }

  @After
  public void tearDown() {
    dummyStation1 = null;
    dummyStation2 = null;
    dummyStation3 = null;
    dummyTrack1 = null;
    dummyTrack2 = null;
  }

  @Test
  public void getOrientation() {
    System.out.println("Running getOrientation test");
    assertEquals("Wrong Orientation", Orientation.VERTICAL, dummyTrack1.getOrientation());
    assertEquals("Wrong Orientation", Orientation.HORIZONTAL, dummyTrack2.getOrientation());
  }

  @Test
  public void getBaron() {
    System.out.println("Running getBaron test");
    assertEquals("Wrong owner", Baron.UNCLAIMED, dummyTrack1.getBaron());
    assertEquals("Wrong owner", Baron.UNCLAIMED, dummyTrack2.getBaron());
  }

  @Test
  public void getRoute() {
    System.out.println("Running getRoute test");
    assertEquals("Wrong route", route1, dummyTrack1.getRoute());
    assertEquals("Wrong route", route2, dummyTrack2.getRoute());
  }
}