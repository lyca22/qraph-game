package datastr;

import java.util.ArrayList;

public abstract class Graph<V> implements IGraph<V> {

	private ArrayList<ArrayList<Integer>> minimumWeightPaths;
	
	public Graph() {
		setMinimumWeightPaths(new ArrayList<ArrayList<Integer>>());
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
