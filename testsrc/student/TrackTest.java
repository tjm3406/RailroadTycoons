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

  @Before
  public void setUp() throws Exception {

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