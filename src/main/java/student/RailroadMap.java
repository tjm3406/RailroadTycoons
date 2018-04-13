package student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import model.RailroadMapObserver;

/**
 * Concrete implementation of RailroadMap Interface
 *
 * @author PedroBreton
 */
public class RailroadMap implements model.RailroadMap {

  private List<RailroadMapObserver> observers;
  private model.Space[][] grid;
  private TreeSet<model.Route> routes;
  private TreeSet<model.Route> unclaimedRoutes;
  private int rows;
  private int cols;

  /**
   * Construct a RailroadMap instance
   *
   * @param routes routes in the map
   */
  public RailroadMap(List<model.Route> routes) {
    this.observers = new ArrayList<>();
    this.routes = new TreeSet<>(Comparator.comparingInt(model.Route::getLength));
    this.unclaimedRoutes = new TreeSet<>(Comparator.comparingInt(model.Route::getLength));

    //lambda to get easternmost station
    this.rows = routes.stream().max(Comparator.comparingInt(a -> a.getDestination().getRow())).get()
        .getDestination().getRow() + 1;
    //lambda to get southernmost station
    this.cols = routes.stream().max(Comparator.comparingInt(a -> a.getDestination().getCol())).get()
        .getDestination().getCol() + 1;

    grid = new model.Space[rows][cols];

    //Traverse routes list to populate grid with stations and tracks
    for (model.Route route : routes) {
      this.routes.add(route);
      this.unclaimedRoutes.add(route);
      grid[route.getOrigin().getRow()][route.getOrigin().getCol()] = route.getOrigin();
      grid[route.getDestination().getRow()][route.getDestination().getCol()] = route
          .getDestination();
      for (model.Track track : route.getTracks()) {
        grid[track.getRow()][track.getCol()] = track;
      }
    }

    //Fill remaining indexes of grid with Spaces
    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == null) {
          grid[i][j] = new Space(i, j);
        }
      }

    }


  }


  /**
   * Add an observer to the observers list
   *
   * @param observer The {@link RailroadMapObserver} being added to the map.
   */
  public void addObserver(RailroadMapObserver observer) {
    observers.add(observer);

  }

  /**
   * Remove observer from observers list
   *
   * @param observer The observer to remove from the collection of registered observers that will
   * be
   */
  @Override
  public void removeObserver(RailroadMapObserver observer) {
    observers.remove(observer);

  }

  /**
   * Get number of rows
   *
   * @return int rows
   */
  @Override
  public int getRows() {
    return rows;
  }

  /**
   * Get number of columns
   *
   * @return int columns
   */
  @Override
  public int getCols() {
    return cols;
  }

  /**
   * Retrieve specific space
   *
   * @param row The row of the desired {@link Space}.
   * @param col The column of the desired {@link Space}.
   * @return an object that implements space at position (row, col)
   */
  @Override
  public model.Space getSpace(int row, int col) {

    if ((row >=0 && row<rows) && (col>=0 && col<cols) ){
      return grid[row][col];
    }
    return null;
  }

  /**
   * Retrieve route associated to a space
   *
   * @param row The row of the location of one of the {@link Track tracks} in the route.
   * @param col The column of the location of one of the {@link Track tracks} in the route.
   * @return A route or null if no routes associated
   */
  @Override
  public model.Route getRoute(int row, int col) {
    if (grid[row][col] instanceof model.Track) {
      Track track = (Track) grid[row][col];
      return track.getRoute();
    }
    return null;
  }

  /**
   * Notify observers of a route being claimed
   *
   * @param route The {@link Route} that has been claimed.
   */
  @Override
  public void routeClaimed(model.Route route) {
    unclaimedRoutes.remove(route);
    for (RailroadMapObserver observer : observers) {
      observer.routeClaimed(this, route);
    }
  }

  /**
   * Retrieve length of the shortest unclaimed route
   *
   * @return int length
   */
  @Override
  public int getLengthOfShortestUnclaimedRoute() {
    if (!unclaimedRoutes.isEmpty()){
      return unclaimedRoutes.first().getLength();
  } else{
      return 0;
    }
  }

  /**
   * Retrieve routes in the map
   *
   * @return a collection of routes
   */
  @Override
  public Collection<model.Route> getRoutes() {
    return routes;
  }

  /**
   * Accessor to the list of observers for testing purposes
   *
   * @return list of observers
   */
  public List<RailroadMapObserver> getObservers() {
    return observers;
  }

  /**
   * Accessor to the unclaimed routes for testing purposes
   *
   * @return treeset with unclaimed routes
   */
  public TreeSet<model.Route> getUnclaimedRoutes() {
    return unclaimedRoutes;
  }
}
