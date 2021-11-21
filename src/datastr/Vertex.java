package datastr;

public class Vertex<V> {

	private V value;
	private int id;
	private int distance;
	private Pair<Integer, Integer> timestamps;
	private Vertex<V> predecessor;
	private VertexColor color;
	private int weightFromPoint;
	
	public Vertex(V value, int id, int distance) {
		super();
		this.value = value;
		this.id = id;
		this.distance = 0;
		timestamps = new Pair<Integer, Integer>(null, null);
		color = null;
		predecessor = null;
		weightFromPoint = 0;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public Pair<Integer, Integer> getTimestamps() {
		return timestamps;
	}

	public void setTimestamps(Pair<Integer, Integer> timestamps) {
		this.timestamps = timestamps;
	}

	public Vertex<V> getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex<V> predecessor) {
		this.predecessor = predecessor;
	}

	public VertexColor getColor() {
		return color;
	}

	public void setColor(VertexColor color) {
		this.color = color;
	}

	public int getWeightFromPoint() {
		return weightFromPoint;
	}

	public void setWeightFromPoint(int weightFromPoint) {
		this.weightFromPoint = weightFromPoint;
	}
	
}
