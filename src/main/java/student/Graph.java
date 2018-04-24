package student;

import java.util.*;
import model.Station;

public class Graph<T> {

  private final Map<T, Vertex<T>> vertices;
  Station northStation;
  Station southStation;
  Station eastStation;
  Station westStation;

  public Graph(Station northStation, Station southStation, Station eastStation, Station westStation) {
    vertices = new HashMap<>();
    this.northStation = northStation;
    this.southStation = southStation;
    this.eastStation = eastStation;
    this.westStation = westStation;
  }

  public void addVertex(T data) {
    Vertex<T> v = new Vertex<>(data);
    if (!vertices.containsKey(data)) {
      vertices.put(data, v);
    }
  }

  public Vertex<T> getVertex(T data) {
    return vertices.get(data);
  }

  public void connect(T data1, T data2) {
    Vertex<T> v1 = getVertex(data1);
    Vertex<T> v2 = getVertex(data2);

    if (!v1.getNeighbors().contains(v2)) {
      v1.connectNeighbor(v2);
      v2.connectNeighbor(v1);
    }
  }


  public boolean depthFirstSearch(T data1, T data2) {
    Vertex<T> start = vertices.get(data1);
    Vertex<T> end = vertices.get(data2);

    HashSet<Vertex<T>> set = new HashSet<>();
    Station cardinality = (Station) data1;
    if (cardinality.getName().equals("North")) {
      set.add(vertices.get(eastStation));
      set.add(vertices.get(westStation));
    } else if (cardinality.getName().equals("East")){
      set.add(vertices.get(northStation));
      set.add(vertices.get(southStation));
    }
    Stack stack = buildPathDfs(start, set, end, 0);
    return stack != null;
  }

  private Stack<Vertex<T>> buildPathDfs(Vertex<T> vertex,
      Set<Vertex<T>> visited,
      Vertex<T> end, int length) {
    visited.add(vertex);
    if(vertex == end && length >= 3) {
      Stack<Vertex<T>> path = new Stack<>();
      path.push(vertex);
      return path;
    }

    for(Vertex<T> neighbor : vertex.getNeighbors()) {
      if(!visited.contains(neighbor)) {
        Stack<Vertex<T>> path = buildPathDfs(neighbor, visited, end, length+=1);
        if(path != null) {
          path.push(vertex);
          return path;
        }
      }
    }
    return null;
  }

  private boolean findPathDfs(Vertex<T> start, Vertex<T> end) {
    Set<Vertex<T>> visited = new HashSet<>();
    visited.add(start);
    visitDfs(start, visited);

    return visited.contains(end);
  }

  private void visitDfs(Vertex<T> vertex, Set<Vertex<T>> visited) {
    for(Vertex<T> neighbor: vertex.getNeighbors()) {
      if(!visited.contains(neighbor)) {
        visited.add(neighbor);
        visitDfs(neighbor, visited);
      }
    }
  }

  public boolean breadthFirstSearch(T data1, T data2) {
    Vertex<T> start = vertices.get(data1);
    Vertex<T> end = vertices.get(data2);

    Set<Vertex<T>> visited = new HashSet<>();
    Queue<Vertex<T>> queue = new LinkedList<>();

    visited.add(start);
    queue.add(start);

    while(!queue.isEmpty()) {
      Vertex<T> vertex = queue.poll();

      if(vertex == end) {
        return true;
      }

      for(Vertex<T> neighbor : vertex.getNeighbors()) {
        if(!visited.contains(neighbor)) {
          visited.add(neighbor);
          queue.add(neighbor);
        }
      }
    }
    return false;
  }

}