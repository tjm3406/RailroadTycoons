package student;

import static org.junit.Assert.*;

import model.Card;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * A test unit for the student.Deck class
 *
 * @author Pedro Breton
 */
public class DeckTest {

  private Deck dummyDeck;

  @Before
  public void setUp() throws Exception {
    dummyDeck = new Deck();
  }

  @After
  public void tearDown() throws Exception {
    dummyDeck = null;
  }

  @Test
  public void reset() {
    System.out.println("Running reset() test");
    dummyDeck.drawACard();
    dummyDeck.reset();
    assertNotEquals("Deck was shuffled", 200, dummyDeck.numberOfCardsRemaining());
  }

  @Test
  public void drawACard() {
    System.out.println("Running drawACard() test");
    assertEquals("Untouched deck", 200, dummyDeck.numberOfCardsRemaining());
    assertFalse("Non-none card", dummyDeck.drawACard().equals(Card.NONE));
    assertEquals("Untouched deck", 199, dummyDeck.numberOfCardsRemaining());

    while (dummyDeck.numberOfCardsRemaining() > 0) {
      dummyDeck.drawACard();
    }

    assertTrue("No cards left", dummyDeck.drawACard().equals(Card.NONE));

  }

  @Test
  public void numberOfCardsRemaining() {
    System.out.println("Running numberOfCardsRemaining() test");
    assertEquals("200 cards left", 200, dummyDeck.numberOfCardsRemaining());

    while (dummyDeck.numberOfCardsRemaining() > 100) {
      dummyDeck.drawACard();
    }
    assertEquals("100 cards left", 100, dummyDeck.numberOfCardsRemaining());

    while (dummyDeck.numberOfCardsRemaining() > 0) {
      dummyDeck.drawACard();
    }
    assertEquals("0 cards left", 0, dummyDeck.numberOfCardsRemaining());

    dummyDeck.drawACard();
    assertEquals("0 cards left", 0, dummyDeck.numberOfCardsRemaining());


  }
}