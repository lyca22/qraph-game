package datastr;

import java.util.ArrayList;

public abstract class Graph<V> implements IGraph<V> {

	private ArrayList<Vertex<V>> vertices;
	private ArrayList<ArrayList<Integer>> minimumWeightPaths;
	
	public Graph() {
		super();
		setVertices(new ArrayList<Vertex<V>>());
		setMinimumWeightPaths(new ArrayList<ArrayList<Integer>>());
	}
	
	public ArrayList<Vertex<V>> getVertices() {
		return vertices;
	}
	
	public void setVertices(ArrayList<Vertex<V>> vertices) {
		this.vertices = vertices;
	}
	
	public ArrayList<ArrayList<Integer>> getMinimumWeightPaths() {
		return minimumWeightPaths;
	}
	
	public void setMinimumWeightPaths(ArrayList<ArrayList<Integer>> minimumWeightPaths) {
		this.minimumWeightPaths = minimumWeightPaths;
	}

	public Vertex<V> searchInVertexList(int id) {
		return vertices.get(id);
	}

	public boolean containsValue(V value) {
		boolean found = false;
		for(int i = 0; i < vertices.size() && !found; i++) {
			if(vertices.get(i).getValue().equals(value)) {
				found = true;
			}
		}
		return found;
	}

}
