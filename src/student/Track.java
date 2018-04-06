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
   * @return the route
   */
  @Override
  public Orientation getOrientation() {
    return route.getOrientation();
  }

  @Override
  public Baron getBaron() {
    return route.getBaron();
  }

  @Override
  public Route getRoute() {
    return route;
  }
}
