package student;

import model.Baron;
import model.Orientation;
import model.Route;

public class Track extends Space implements model.Track {

  private Route route;

  /**
   * Constructs a Space instance
   *
   * @param row row in the board
   * @param col column in the board
   */
  public Track(int row, int col, student.Route route) {
    super(row, col);
    this.route = route;

  }

  /**
   * Returns the orientation of this track
   *
   * @return the orientation the route is going that this track belongs to (horizontal or vertical)
   */
  @Override
  public Orientation getOrientation() {
    return route.getOrientation();
  }

  /**
   * Returns the Baron of this track
   *
   * @return the Baron that owns the route this track belongs too
   */
  @Override
  public Baron getBaron() {
    return route.getBaron();
  }

  /**
   * Returns the route of this track
   *
   * @return the route this track belongs too
   */
  @Override
  public Route getRoute() {
    return route;
  }
}
