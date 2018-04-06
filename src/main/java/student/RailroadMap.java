package student;

import java.util.Collection;
import model.Baron;
import model.RailroadMapObserver;

/**
 * Concrete implementation of RailroadMap Interface
 */
public class RailroadMap implements model.RailroadMap {


  /**
   * Adds the specified {@linkplain RailroadMapObserver observer} to the map. The observer will be
   * notified of significant events involving this map such as when a {@linkplain Route route} has
   * been claimed by a {@linkplain Baron}.
   *
   * @param observer The {@link RailroadMapObserver} being added to the map.
   */
  @Override
  public void addObserver(RailroadMapObserver observer) {

  }

  /**
   * Removes the specified {@linkplain RailroadMapObserver observer} from the map. The observer will
   * no longer be notified of significant events involving this map.
   *
   * @param observer The observer to remove from the collection of registered observers that will be
   * notified of significant events involving this map.
   */
  @Override
  public void removeObserver(RailroadMapObserver observer) {

  }

  /**
   * Returns the number of rows in the map. This is determined by the location of the southernmost
   * {@linkplain Station station} on the map.
   *
   * @return The number of rows in the map.
   */
  @Override
  public int getRows() {
    return 0;
  }

  /**
   * Returns the number of columns in the map. This is determined by the location of the easternmost
   * {@linkplain Station station} on the map.
   *
   * @return The number of columns in the map.
   */
  @Override
  public int getCols() {
    return 0;
  }

  /**
   * Returns the {@linkplain Space space} located at the specified coordinates.
   *
   * @param row The row of the desired {@link Space}.
   * @param col The column of the desired {@link Space}.
   * @return The {@link Space} at the specified location, or null if the location doesn't exist on
   * this map.
   */
  @Override
  public model.Space getSpace(int row, int col) {
    return null;
  }

  /**
   * Returns the {@linkplain Route route} that contains the {@link Track track} at the specified
   * location (if such a route exists}.
   *
   * @param row The row of the location of one of the {@link Track tracks} in the route.
   * @param col The column of the location of one of the {@link Track tracks} in the route.
   * @return The {@link Route} that contains the {@link Track} at the specified location, or null if
   * there is no such {@link Route}.
   */
  @Override
  public model.Route getRoute(int row, int col) {
    return null;
  }

  /**
   * Called to update the {@linkplain RailroadMap map} when a {@linkplain Baron} has claimed a
   * {@linkplain Route route}.
   *
   * @param route The {@link Route} that has been claimed.
   */
  @Override
  public void routeClaimed(model.Route route) {

  }

  /**
   * Returns the length of the shortest unclaimed {@linkplain Route route} in the map.
   *
   * @return The length of the shortest unclaimed {@link Route}.
   */
  @Override
  public int getLengthOfShortestUnclaimedRoute() {
    return 0;
  }

  /**
   * Returns all of the {@link Route Routes} in this map.
   *
   * @return A {@link Collection} of all of the {@link Route Routes} in this RailroadMap.
   */
  @Override
  public Collection<model.Route> getRoutes() {
    return null;
  }
}
