package student;

import java.io.InputStream;
import java.io.OutputStream;
import model.Baron;
import model.RailroadBaronsException;

public class MapMaker implements model.MapMaker {

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
    return null;
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

  }
}
