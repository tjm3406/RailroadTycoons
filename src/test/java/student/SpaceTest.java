package student;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test unit for the student.Space class
 * @author PedroBreton
 */
public class SpaceTest {
  private Space dummySpace1;
  private Space dummySpace2;
  private Space dummySpace3;

  @Before
  public void setUp(){
    dummySpace1 = new Space(1,2);
    dummySpace2 = new Space(2, 2);
    dummySpace3 = new Space(1,2);
  }

  @Test
  public void getRow() {
    System.out.println("Running getRow() test");
    assertEquals("Wrong row", 1, dummySpace1.getRow());
    assertEquals("Wrong row", 2, dummySpace2.getRow());
  }

  @Test
  public void getCol() {
    System.out.println("Running getCol() test");
    assertEquals("Wrong column", 2, dummySpace1.getCol());
    assertEquals("Wrong column", 2, dummySpace2.getCol());
  }

  @Test
  public void collocated() {
    System.out.println("Running collocated() test");
    assertEquals("Space1 not on same space as Space2", false, dummySpace1.collocated(dummySpace2));
    assertEquals("Space1 is on same space as Space3", true, dummySpace1.collocated(dummySpace3));
  }

  @After
  public void tearDown(){
    dummySpace1 = null;
    dummySpace2 = null;
    dummySpace3 = null;
  }
}