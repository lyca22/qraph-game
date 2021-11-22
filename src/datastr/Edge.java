package datastr;

public class Edge<V> {
	
	private Vertex<V> initial;
	private Vertex<V> end;
	private int weight;
	
	public Edge(Vertex<V> initial, Vertex<V> end, int weight) {
		this.initial = initial;
		this.end = end;
		this.weight = weight;
	}

	public Vertex<V> getInitial() {
		return initial;
	}

	public void setInitial(Vertex<V> initial) {
		this.initial = initial;
	}

	public Vertex<V> getEnd() {
		return end;
	}

	public void setEnd(Vertex<V> end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
