package student;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.util.ArrayList;
import model.Baron;
import model.Orientation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test unit for the student.MapMaker class
 *
 * @author Pedro Breton and Tyler Miller
 */
public class MapMakerTest {

  private MapMaker mapMaker;

  @Before
  public void setUp() throws Exception {
    mapMaker = new MapMaker();
  }

  @After
  public void tearDown() throws Exception {
    mapMaker = null;
  }

  @Test
  public void readMap() throws Exception {
    System.out.println("Running readMap() test");
    FileInputStream inputStream = new FileInputStream("maps/simple.rbmap");
    model.RailroadMap dummyRailroadMap1 = mapMaker.readMap(inputStream);

    Station dummyStation1 = new Station("Crux Station", 2, 2);
    Station dummyStation2 = new Station("East End Station", 2, 10);
    Station dummyStation3 = new Station("South Bend Station", 12, 2);

    Route dummyRoute1 = new Route(dummyStation1, dummyStation2, Orientation.HORIZONTAL);
    Route dummyRoute2 = new Route(dummyStation1, dummyStation3, Orientation.VERTICAL);

    ArrayList<model.Route> routes = new ArrayList<>();
    routes.add(dummyRoute1);
    routes.add(dummyRoute2);

    model.RailroadMap dummyRailroadMap2 = new RailroadMap(routes);

    assertEquals("Different number of Columns", dummyRailroadMap1.getCols(),
        dummyRailroadMap2.getCols());
    assertEquals("Different number of rows", dummyRailroadMap1.getRows(),
        dummyRailroadMap2.getRows());
    assertEquals("Different shortest unclaimed route",
        dummyRailroadMap1.getLengthOfShortestUnclaimedRoute(),
        dummyRailroadMap2.getLengthOfShortestUnclaimedRoute());


    inputStream.close();

  }

  @Test
  public void writeMapNoChanges() throws Exception {
    System.out.println("Running writeMapNoChanges() test");
    FileInputStream inputStream = new FileInputStream("maps/simple.rbmap");
    model.RailroadMap dummyRailroadMap1 = mapMaker.readMap(inputStream);

    FileOutputStream fileOutputStream = new FileOutputStream("maps/simple.save");
    mapMaker.writeMap(dummyRailroadMap1, fileOutputStream);

    FileInputStream inputStream2 = new FileInputStream("maps/simple.save");
    model.RailroadMap dummyRailroadMap2 = mapMaker.readMap(inputStream2);

    assertEquals("Different number of Columns", dummyRailroadMap1.getCols(),
        dummyRailroadMap2.getCols());
    assertEquals("Different number of rows", dummyRailroadMap1.getRows(),
        dummyRailroadMap2.getRows());
    assertEquals("Different shortest unclaimed route",
        dummyRailroadMap1.getLengthOfShortestUnclaimedRoute(),
        dummyRailroadMap2.getLengthOfShortestUnclaimedRoute());


    fileOutputStream.close();
    inputStream.close();
  }

  @Test
  public void writeMapClaimedRoute() throws Exception {
    System.out.println("Running writeMapClaimedRoute() test");
    FileInputStream inputStream = new FileInputStream("maps/simple.rbmap");
    model.RailroadMap dummyRailroadMap1 = mapMaker.readMap(inputStream);
    dummyRailroadMap1.getRoute(3, 2).claim(Baron.RED);
    dummyRailroadMap1.getRoute(2, 3).claim(Baron.BLUE);

    FileOutputStream fileOutputStream = new FileOutputStream("maps/simple.save");
    mapMaker.writeMap(dummyRailroadMap1, fileOutputStream);

    FileInputStream inputStream2 = new FileInputStream("maps/simple.save");
    model.RailroadMap dummyRailroadMap2 = mapMaker.readMap(inputStream2);

    assertEquals("Different number of Columns", dummyRailroadMap1.getCols(),
        dummyRailroadMap2.getCols());
    assertEquals("Different number of rows", dummyRailroadMap1.getRows(),
        dummyRailroadMap2.getRows());
    assertEquals("Different shortest unclaimed route",
        dummyRailroadMap1.getLengthOfShortestUnclaimedRoute(),
        dummyRailroadMap2.getLengthOfShortestUnclaimedRoute());


    fileOutputStream.close();
    inputStream.close();
  }
}