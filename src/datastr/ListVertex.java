package datastr;

import java.util.ArrayList;

public class ListVertex<V> extends Vertex<V>{

	private ArrayList<Edge<V>> edges;
	
	public ListVertex(V value, int id) {
		super(value, id);
		edges = new ArrayList<Edge<V>>();
	}

	public ArrayList<Edge<V>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<Edge<V>> edges) {
		this.edges = edges;
	}

}
