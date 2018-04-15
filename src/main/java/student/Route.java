package student;

import java.util.ArrayList;
import java.util.List;
import model.Baron;
import model.Orientation;
import model.Space;
import model.Station;
import model.Track;

/**
 * Concrete implementation of Route interface
 *
 * @author PedroBreton and Tyler Miller
 */
public class Route implements model.Route {

  private Baron baron;
  private model.Station origin;
  private model.Station destination;
  private Orientation orientation;
  private List<Track> tracks;

  /**
   * Constructs a Route instance
   *
   * @param origin a station
   * @param destination a station
   * @param orientation a orientation
   */
  public Route(model.Station origin, model.Station destination, Orientation orientation) {
    this.origin = origin;
    this.destination = destination;
    this.orientation = orientation;
    this.baron = Baron.UNCLAIMED;

    tracks = new ArrayList<>();

    /*
    Create all the tracks
     */
    switch (orientation) {
      case HORIZONTAL:
        for (int i = origin.getCol() + 1; i < destination.getCol(); i++) {
          tracks.add(new student.Track(origin.getRow(), i, this));
        }
        break;
      case VERTICAL:
        for (int i = origin.getRow() + 1; i < destination.getRow(); i++) {
          tracks.add(new student.Track(i, origin.getCol(), this));
        }
        break;

      default:
        break;
    }

  }

  /**
   * Retrieve the Baron that owns the route
   *
   * @return Baron owner
   */
  @Override
  public Baron getBaron() {
    return baron;
  }

  /**
   * Retrieve the origin
   *
   * @return station origin
   */
  @Override
  public Station getOrigin() {
    return origin;
  }

  /**
   * Retrieve the destination
   *
   * @return station destination
   */
  @Override
  public Station getDestination() {
    return destination;
  }

  /**
   * Retrieve the orientation
   *
   * @return Orientation orientation
   */
  @Override
  public Orientation getOrientation() {
    return orientation;
  }

  /**
   * Retrieve the tracks that form the route
   *
   * @return List<Track> arrayList with all tracks
   */
  @Override
  public List<Track> getTracks()   {
    return tracks;
  }

  /**
   * Retrieve length of the route
   *
   * @return int length
   */
  @Override
  public int getLength() {
    return tracks.size();
  }

  /**
   * Calculate points that the track is worth
   *
   * @return int points worth
   */
  @Override
  public int getPointValue() {
    int points = 0;
    switch (getLength()) {
      case 1:
        points = 1;
        break;
      case 2:
        points = 2;
        break;
      case 3:
        points = 4;
        break;
      case 4:
        points = 7;
        break;

      default:
        points = (getLength() - 3) * 5;
        break;
    }

    return points;
  }

  /**
   * Check is a space is in a specific route
   *
   * @param space The {@link Space} that may be in this route.
   * @return boolean
   */
  @Override
  public boolean includesCoordinate(Space space) {
    boolean isOnRoute = false;

    switch (orientation) {
      case VERTICAL:
        if ((space.getCol() == this.origin.getCol()) && (space.getRow() >= this.origin.getRow()
            && space.getRow() <= this.destination.getRow())) {
          isOnRoute = true;
        }
        break;
      case HORIZONTAL:
        if ((space.getRow() == this.origin.getRow()) && (space.getCol() >= this.origin.getCol()
            && space.getCol() <= this.destination.getCol())) {
          isOnRoute = true;
        }
        break;
    }

    return isOnRoute;
  }

  /**
   * claim the route for a baron
   *
   * @param claimant The {@link Baron} attempting to claim the route. Must not be null or {@link
   * Baron#UNCLAIMED}.
   * @return boolean whether claim was successful
   */
  @Override
  public boolean claim(Baron claimant) {
    if (baron.equals(Baron.UNCLAIMED)) {
      baron = claimant;
      return true;
    }
    return false;
  }
}
