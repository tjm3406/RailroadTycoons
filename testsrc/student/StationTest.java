package student;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * A test unit for the student.Station class
 * @author PedroBreton
 */
public class StationTest {
  private Station dummyStation1;
  private Station dummyStation2;
  private Station dummyStation3;

  @Before
  public void setUp(){
    dummyStation1 = new Station("Station1",1,2);
    dummyStation2 = new Station("Station2",2, 2);
    dummyStation3 = new Station("Station3",1,2);
  }
  @Test
  public void getName() {
    System.out.println("Running getName() test");
    assertEquals("Wrong name", "Station1", dummyStation1.getName());
    assertEquals("Wrong name", "Station2", dummyStation2.getName());
  }

  @Test
  public void getRow() {
    System.out.println("Running getRow() test");
    assertEquals("Wrong column", 1, dummyStation1.getRow());
    assertEquals("Wrong column", 2, dummyStation2.getRow());
  }

  @Test
  public void getCol() {
    System.out.println("Running getCol() test");
    assertEquals("Wrong column", 2, dummyStation1.getCol());
    assertEquals("Wrong column", 2, dummyStation2.getCol());
  }

  @Test
  public void collocated() {
    System.out.println("Running collocated() test");
    assertEquals("Station1 not on same space as Station2", false, dummyStation1.collocated(dummyStation2));
    assertEquals("Station1 is on same space as Station3", true, dummyStation1.collocated(dummyStation3));
  }

  @After
  public void tearDown(){
    dummyStation1 = null;
    dummyStation2 = null;
    dummyStation3 = null;
  }
}