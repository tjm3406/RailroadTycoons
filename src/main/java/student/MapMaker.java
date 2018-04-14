package student;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import model.Baron;
import model.Orientation;
import model.RailroadBaronsException;

/**
 * Concrete implementation of MapMaker Interface
 *
 * @author PedroBreton and Tyler Miller
 */
public class MapMaker implements model.MapMaker {

  private Map<Integer, model.Station> stations;
  private List<model.Route> routeList;

  /**
   * Construct an instance of the MapMaker class
   */
  public MapMaker() {
    stations = new TreeMap<>();
    routeList = new ArrayList<>();
  }

  /**
   * Loads a {@linkplain RailroadMap map} using the data in the given {@linkplain InputStream input
   * stream}.
   *
   * @param in The {@link InputStream} used to read the {@link RailroadMap map} data.
   * @return The {@link RailroadMap map} read from the given {@link InputStream}.
   * @throws RailroadBaronsException If there are any problems reading the data from the {@link
   * InputStream}.
   */
  @Override
  public model.RailroadMap readMap(InputStream in) throws RailroadBaronsException {
    Scanner input = new Scanner(in);
    String line;
    String[] lineSplit;
    while (!(line = input.nextLine()).equals("##ROUTES##")) {
      lineSplit = line.split(" ", 4);
      stations.put(Integer.parseInt(lineSplit[0]),
          new Station(lineSplit[3], Integer.parseInt(lineSplit[1]),
              Integer.parseInt(lineSplit[2])));
    }

    while (input.hasNextLine()) {
      line = input.nextLine();
      lineSplit = line.split(" ");
      model.Station origin = stations.get(Integer.parseInt(lineSplit[0]));
      model.Station destination = stations.get(Integer.parseInt(lineSplit[1]));

      Orientation orientation;

      if ((origin.getCol() == destination.getCol()) && (origin.getRow() != destination.getRow())) {
        orientation = Orientation.VERTICAL;
      } else if ((origin.getCol() != destination.getCol()) && (origin.getRow() == destination
          .getRow())) {
        orientation = Orientation.HORIZONTAL;
      } else {
        throw new RailroadBaronsException("Diagonal routes not allowed");
      }

      Route route = new Route(origin, destination, orientation);
      route.claim(Baron.valueOf(lineSplit[2]));
      routeList.add(route);
    }

    return new RailroadMap(routeList);
  }

  /**
   * Writes the specified {@linkplain RailroadMap map} in the Railroad Barons map file format to the
   * given {@linkplain OutputStream output stream}. The written map should include an accurate
   * record of any routes that have been claimed, and by which {@linkplain Baron}.
   *
   * @param map The {@link RailroadMap map} to write out to the {@link OutputStream}.
   * @param out The {@link OutputStream} to which the {@link RailroadMap map} data should be
   * written.
   * @throws RailroadBaronsException If there are any problems writing the data to the {@link
   * OutputStream}.
   */
  @Override
  public void writeMap(model.RailroadMap map, OutputStream out) throws RailroadBaronsException {
    PrintStream printer = new PrintStream(out);

    stations = new TreeMap<>();
    int counter = 0;
    HashSet<model.Station> uniqueStations = new HashSet<>();

    for (model.Route route : map.getRoutes()) {
      if (!uniqueStations.contains(route.getOrigin())) {
        stations.put(counter, route.getOrigin());
        uniqueStations.add(route.getOrigin());
        counter++;
      }
      if (!uniqueStations.contains(route.getDestination())) {
        stations.put(counter, route.getDestination());
        uniqueStations.add(route.getDestination());
        counter++;
      }
    }

    stations.forEach((k, v) -> {
      printer.printf("%d %d %d %s\n", k, v.getRow(), v.getCol(), v.getName());
    });

    HashMap<model.Station, Integer> invertedStations = new HashMap<>();
    stations.forEach((k, v) -> {
      invertedStations.put(v, k);
    });

    printer.println("##ROUTES##");

    for (model.Route route : map.getRoutes()) {
      printer.printf("%d %d %s\n", invertedStations.get(route.getOrigin()),
          invertedStations.get(route.getDestination()), route.getBaron().toString());
    }

  }
}
