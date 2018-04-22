package student;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Vertex<T> {

  private T data;

  private List<Vertex<T>> neighbors;


  public Vertex(T data) {
    this.data = data;
    neighbors = new LinkedList<>();
  }

  public Collection<Vertex<T>> getNeighbors() {
    return neighbors;
  }

  public T getData() {
    return data;
  }

  public void connectNeighbor(Vertex<T> neighbor) {
    neighbors.add(neighbor);
  }
}
