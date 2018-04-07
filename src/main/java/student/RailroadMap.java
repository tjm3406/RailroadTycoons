package student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import model.Baron;
import model.Orientation;
import model.RailroadMapObserver;

/**
 * Concrete implementation of RailroadMap Interface
 */
public class RailroadMap implements model.RailroadMap {

  private List<RailroadMapObserver> observers;
  private model.Space[][] grid;
  private TreeSet<model.Route> routes;
  private TreeSet<model.Route> unclaimedRoutes;
  private int rows;
  private int cols;


  public RailroadMap(ArrayList<Route> routes) {
    this.observers = new ArrayList<>();
    this.routes = new TreeSet<>(Comparator.comparingInt(model.Route::getLength));
    this.unclaimedRoutes = new TreeSet<>(Comparator.comparingInt(model.Route::getLength));

    this.rows = routes.stream().max(Comparator.comparingInt(a -> a.getDestination().getRow())).get()
        .getDestination().getRow() + 1;
    this.cols = routes.stream().max(Comparator.comparingInt(a -> a.getDestination().getCol())).get()
        .getDestination().getCol() + 1;

    grid = new model.Space[rows][cols];

    for (Route route : routes) {
      this.routes.add(route);
      this.unclaimedRoutes.add(route);
      grid[route.getOrigin().getRow()][route.getOrigin().getCol()] = route.getOrigin();
      grid[route.getDestination().getRow()][route.getDestination().getCol()] = route
          .getDestination();
      for (model.Track track : route.getTracks()) {
        grid[track.getRow()][track.getCol()] = track;
      }
    }

    for (int i = 0; i < grid.length; i++) {
      for (int j = 0; j < grid[0].length; j++) {
        if (grid[i][j] == null) {
          grid[i][j] = new Space(i, j);
        }
      }

    }


  }

  public void addObserver(RailroadMapObserver observer) {
    observers.add(observer);

  }

  @Override
  public void removeObserver(RailroadMapObserver observer) {
    observers.remove(observer);

  }


  @Override
  public int getRows() {
    return rows;
  }


  @Override
  public int getCols() {
    return cols;
  }

  @Override
  public model.Space getSpace(int row, int col) {
    return grid[row][col];
  }

  @Override
  public model.Route getRoute(int row, int col) {
    if (grid[row][col] instanceof model.Track) {
      Track track = (Track) grid[row][col];
      return track.getRoute();
    }
    return null;
  }

  @Override
  public void routeClaimed(model.Route route) {
    unclaimedRoutes.remove(route);
    for (RailroadMapObserver observer : observers) {
      observer.routeClaimed(this, route);
    }

  }

  @Override
  public int getLengthOfShortestUnclaimedRoute() {
    return unclaimedRoutes.first().getLength();
  }

  @Override
  public Collection<model.Route> getRoutes() {
    return routes;
  }

  public List<RailroadMapObserver> getObservers() {
    return observers;
  }

  public TreeSet<model.Route> getUnclaimedRoutes() {
    return unclaimedRoutes;
  }
}
