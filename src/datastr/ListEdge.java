package datastr;

public class ListEdge<V> {

	private ListVertex<V> initial;
	private ListVertex<V> end;
	private int weight;
	
	public ListEdge(ListVertex<V> initial, ListVertex<V> end, int weight) {
		this.initial = initial;
		this.end = end;
		this.weight = weight;
	}

	public ListVertex<V> getInitial() {
		return initial;
	}

	public void setInitial(ListVertex<V> initial) {
		this.initial = initial;
	}

	public ListVertex<V> getEnd() {
		return end;
	}

	public void setEnd(ListVertex<V> end) {
		this.end = end;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
