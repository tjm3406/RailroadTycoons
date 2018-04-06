package student;

import java.util.ArrayList;
import java.util.List;
import model.Baron;
import model.Orientation;
import model.Space;
import model.Station;
import model.Track;

public class Route implements model.Route {

  private Baron baron;
  private student.Station origin;
  private student.Station destination;
  private Orientation orientation;
  private List<Track> tracks;


  public Route(student.Station origin, student.Station destination, Orientation orientation) {
    this.origin = origin;
    this.destination = destination;
    this.orientation = orientation;
    this.baron = Baron.UNCLAIMED;

    tracks = new ArrayList<>();

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

  @Override
  public Baron getBaron() {
    return baron;
  }

  @Override
  public Station getOrigin() {
    return origin;
  }

  @Override
  public Station getDestination() {
    return destination;
  }

  @Override
  public Orientation getOrientation() {
    return orientation;
  }

  @Override
  public List<Track> getTracks() {
    return tracks;
  }

  @Override
  public int getLength() {
    return tracks.size();
  }

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
        points = (getLength() - 3) * 2;
        break;
    }

    return points;
  }

  @Override
  public boolean includesCoordinate(Space space) {
    boolean isOnRoute = false;

    if (origin.collocated(space) || destination.collocated(space) ) {
      isOnRoute = true;
    } else {
      for (Track track : tracks){
        if (track.collocated(space)){
          isOnRoute = true;
          break;
        }
      }
    }

    return isOnRoute;
  }

  @Override
  public boolean claim(Baron claimant) {
    if (baron == Baron.UNCLAIMED){
      baron = claimant;
      return true;
    }
    return false;
  }
}
