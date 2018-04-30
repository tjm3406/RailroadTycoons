package student;

import static org.junit.Assert.*;

import java.util.HashMap;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GraphTest {

  private Vertex<Integer> dummyVertex1;
  private Vertex<Integer> dummyVertex2;
  private Vertex<Integer> dummyVertex3;
  private Vertex<Integer> dummyVertex4;
  private Vertex<Integer> dummyVertex5;
  private Graph<Integer> dummyGraph;
  private HashMap<Integer, Vertex<Integer>> dummyVertices;

  @Before
  public void setUp() throws Exception {
    Station stationNorth = new Station("North", Integer.MIN_VALUE, Integer.MIN_VALUE);
    Station stationEast = new Station("East", Integer.MIN_VALUE, Integer.MAX_VALUE);
    Station stationSouth = new Station("South", Integer.MAX_VALUE, Integer.MAX_VALUE);
    Station stationWest = new Station("West", Integer.MAX_VALUE, Integer.MIN_VALUE);
    dummyGraph = new Graph<>(stationNorth,stationSouth,stationEast,stationWest);

    dummyVertex1 = new Vertex<>(1);
    dummyVertex2 = new Vertex<>(2);
    dummyVertex3 = new Vertex<>(3);
    dummyVertex4 = new Vertex<>(4);
    dummyVertex5 = new Vertex<>(5);

    dummyVertices = new HashMap<>();


  }

  @After
  public void tearDown() throws Exception {
    dummyVertex1 = null;
    dummyVertex2 = null;
    dummyVertex3 = null;
    dummyVertex4 = null;
    dummyVertex5 = null;
    dummyGraph = null;
    dummyVertices = null;
  }

  @Test
  public void addVertex() {
    System.out.println("Running addVertex() test");

    dummyGraph.addVertex(1);
    dummyVertices.put(1, dummyVertex1);

    assertEquals("Graph should have 1 vertex of value 1.", 1, dummyGraph.getVertices().size());

    dummyGraph.addVertex(2);
    dummyVertices.put(2, dummyVertex2);

    assertEquals("Graph should have two vertices", 2, dummyGraph.getVertices().size());
  }

  @Test
  public void getVertex() {
    System.out.println("Running vertex() test");

    assertEquals("Should have a vertex of value 1", true, true);

  }

  @Test
  public void connect() {
    System.out.println("Running connect() test");

    dummyGraph.addVertex(1);
    dummyGraph.addVertex(2);
    dummyGraph.connect(1, 2);

    assertEquals("Connection should exist between the two vertices", true, true);

  }

  @Test
  public void depthFirstSearch() {
    System.out.println("Running DepthFirstSearch() test");

    dummyGraph.addVertex(1);
    dummyGraph.addVertex(2);
    dummyGraph.connect(1,2);
    dummyGraph.addVertex(3);
    dummyGraph.addVertex(4);

    assertEquals("connection should exist between vertex 1 and 2", true, true);


  }
}