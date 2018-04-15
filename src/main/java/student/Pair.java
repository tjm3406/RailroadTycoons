package student;

import model.Card;

/**
 * Concrete implementation of Pair Interface
 *
 * @author PedroBreton and Tyler Miller
 */
public class Pair implements model.Pair {

  private Card firstCard;
  private Card secondCard;

  /**
   * Construct an instance of the Pair class
   *
   * @param firstCard first Card
   * @param secondCard second Card
   */
  public Pair(Card firstCard, Card secondCard) {
    this.firstCard = firstCard;
    this.secondCard = secondCard;
  }

  /**
   * Returns the first {@linkplain Card card} in the pair. Note that, if the game deck is empty, the
   * value of this card may be {@link Card#NONE}.
   *
   * @return The first {@link Card} in the pair.
   */
  @Override
  public Card getFirstCard() {
    return firstCard;
  }

  /**
   * Returns the second {@linkplain Card card} in the pair. if the game deck is empty, the value of
   * this card may be {@link Card#NONE}.
   *
   * @return The second {@link Card} in the pair.
   */
  @Override
  public Card getSecondCard() {
    return secondCard;
  }
}
