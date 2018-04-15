package student;

import static org.junit.Assert.*;

import model.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test unit for the student.Pair class
 *
 * @author Pedro Breton and Tyler Miller
 */
public class PairTest {

  private Pair pair;

  @Before
  public void setUp() throws Exception {
    pair = new Pair(Card.BLACK, Card.BLUE);

  }

  @After
  public void tearDown() throws Exception {
    pair = null;
  }

  @Test
  public void getFirstCard() {
    System.out.println("Running getFirstCard() test");
    assertEquals("First card is Black", Card.BLACK, pair.getFirstCard());
  }

  @Test
  public void getSecondCard() {
    System.out.println("Running getFirstCard() test");
    assertEquals("Second card is Blue", Card.BLUE, pair.getSecondCard());
  }
}