package student;

import java.util.List;
import model.Baron;
import model.Orientation;
import model.Space;
import model.Station;
import model.Track;

public class Route implements model.Route {

  @Override
  public Baron getBaron() {
    return null;
  }

  @Override
  public Station getOrigin() {
    return null;
  }

  @Override
  public Station getDestination() {
    return null;
  }

  @Override
  public Orientation getOrientation() {
    return null;
  }

  @Override
  public List<Track> getTracks() {
    return null;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public int getPointValue() {
    return 0;
  }

  @Override
  public boolean includesCoordinate(Space space) {
    return false;
  }

  @Override
  public boolean claim(Baron claimant) {
    return false;
  }
}
