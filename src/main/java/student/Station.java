package student;

import model.Space;

/**
 * Concrete implementation of Station interface
 *
 * @author PedroBreton
 */
public class Station extends student.Space implements model.Station {

  private String name;

  /**
   * Constructs a Station instance
   *
   * @param name name of the station
   * @param row row in the board
   * @param col column in the board
   */
  public Station(String name, int row, int col) {
    super(row, col);
    this.name = name;

  }

  /**
   * Get the Space's name
   *
   * @return String name
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Get the Space's row
   *
   * @return int row
   */
  @Override
  public int getRow() {
    return super.getRow();
  }

  /**
   * Get the Space's col
   *
   * @return int col
   */
  @Override
  public int getCol() {
    return super.getCol();
  }

  /**
   * Check if two spaces share the same location
   *
   * @param other The other space to which this space is being compared for collocation.
   * @return boolean whether the two Spaces have the same row and column
   */
  @Override
  public boolean collocated(Space other) {
    return super.collocated(other);
  }
}
