package datastr;

public class ListEdge<V> {

	private ListVertex<V> v1;
	private ListVertex<V> v2;
	private int weight;
	
	public ListEdge(ListVertex<V> v1, ListVertex<V> v2, int weight) {
		super();
		this.v1 = v1;
		this.v2 = v2;
		this.weight = weight;
	}
	
	public ListVertex<V> getV1() {
		return v1;
	}
	public void setV1(ListVertex<V> v1) {
		this.v1 = v1;
	}
	public ListVertex<V> getV2() {
		return v2;
	}
	public void setV2(ListVertex<V> v2) {
		this.v2 = v2;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
}
