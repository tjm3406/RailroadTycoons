package student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import model.Card;

/**
 * Concrete implementation of Deck Interface
 *
 * @author PedroBreton
 */
public class Deck implements model.Deck {

  private ArrayList<Card> deck;

  /**
   * Construct an instance of the Deck class
   */
  public Deck() {
    deck = new ArrayList<>();
    reset();
  }

  /**
   * Resets the {@linkplain Deck deck} to its starting state. Restores any {@linkplain Card cards}
   * that were drawn and shuffles the deck.
   */
  @Override
  public void reset() {
    for (Card card : Arrays.stream(Card.values()).filter(card -> !card.equals(Card.NONE) && !card.equals(Card.BACK)).collect(
        Collectors.toList())) {
      for (int i = 0; i < 20; i++) {
        deck.add(card);
      }
    }
    Collections.shuffle(deck);

  }

  /**
   * Draws the next {@linkplain Card card} from the "top" of the deck.
   *
   * @return The next {@link Card}, unless the deck is empty, in which case this should return
   * {@link Card#NONE}.
   */
  @Override
  public Card drawACard() {
    Card card;
    if (deck.size() != 0) {
      card = deck.remove(deck.size() - 1);
    } else {
      card = Card.NONE;
    }
    return card;
  }

  /**
   * Returns the number of {@link Card cards} that have yet to be drawn.
   *
   * @return The number of {@link Card cards} that have yet to be drawn.
   */
  @Override
  public int numberOfCardsRemaining() {
    return deck.size();
  }
}
