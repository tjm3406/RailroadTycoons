package student;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TrackTest {

  private Track dummyTrack1;
  private Track dummyTrack2;
  private Track dummyTrack3;
  private Station dummyStation1;
  private Station dummyStation2;
  private Station dummyStation3;

  @Before
  public void setUp() throws Exception {
    dummyStation1 = new Station("Station1", 0, 0);
    dummyStation2 = new Station("Station2", 0, 2);
    dummyStation3 = new Station("Station3", 2, 0);

    dummyTrack1 = new Track(0,0, new Route());
    //dummyTrack2 = new Track(1,0);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void getOrientation() {
  }

  @Test
  public void getBaron() {
  }

  @Test
  public void getRoute() {
  }
}