package student;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VertexTest {

  private Vertex<Integer> dummyVertex1;
  private Vertex<Integer> dummyVertex2;
  private Vertex<Integer> dummyVertex3;

  @Before
  public void setUp() throws Exception {
    dummyVertex1 = new Vertex<>(1);
    dummyVertex2 = new Vertex<>(2);
    dummyVertex3 = new Vertex<>(3);
  }

  @After
  public void tearDown() throws Exception {
    dummyVertex1 = null;
    dummyVertex2 = null;
    dummyVertex3 = null;
  }

  @Test
  public void getNeighbors() {
    System.out.println("Running getNeighbors() test");

    dummyVertex1.connectNeighbor(dummyVertex2);

    assertEquals("Vertex 1 should have vertex 2 as neighbors", true, dummyVertex1.getNeighbors().contains(dummyVertex2));

  }

  @Test
  public void getData() {
    System.out.println("Running getData() test");

    assertEquals("The vertex 1 should have value of 1", new Integer(1), dummyVertex1.getData());
    assertEquals("The vertex 2 should have value of 2", new Integer(2), dummyVertex2.getData());
    assertEquals("The vertex 3 should have value of 3", new Integer(3), dummyVertex3.getData());

  }

  @Test
  public void connectNeighbor() {
    System.out.println("Running connectNeighbor() test");

    dummyVertex1.connectNeighbor(dummyVertex2);

    assertEquals("Vertex 1 should have vertex 2 as neighbors", true, dummyVertex1.getNeighbors().contains(dummyVertex2));
  }
}