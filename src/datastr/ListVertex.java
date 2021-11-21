package datastr;

import java.util.ArrayList;

public class ListVertex<V> extends Vertex<V>{

	private ArrayList<ListEdge<V>> edges;
	
	public ListVertex(V value, int id, int distance) {
		super(value, id, distance);
		edges = new ArrayList<ListEdge<V>>();
	}

	public ArrayList<ListEdge<V>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<ListEdge<V>> edges) {
		this.edges = edges;
	}

}
