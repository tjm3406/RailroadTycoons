package student;

/**
 * Concrete implementation of the Space interface
 *
 * @author PedroBreton and Tyler Miller
 */
public class Space implements model.Space {

  private int row;
  private int col;

  /**
   * Constructs a Space instance
   *
   * @param row row in the board
   * @param col column in the board
   */
  public Space(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * Get the Space's row
   *
   * @return int row
   */
  @Override
  public int getRow() {
    return row;
  }

  /**
   * Get the Space's column
   *
   * @return int column
   */
  @Override
  public int getCol() {
    return col;
  }

  /**
   * Check if two spaces share the same location
   *
   * @param other The other space to which this space is being compared for collocation.
   * @return boolean whether the two Spaces have the same row and column
   */
  @Override
  public boolean collocated(model.Space other) {
    return row == other.getRow() && col == other.getCol();
  }
}
