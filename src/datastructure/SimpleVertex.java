package datastructure;

public class SimpleVertex<E extends Comparable<E>> implements ISimpleVertex<E> {

	private E value;
	private int id;
	private int distance;
	
	public SimpleVertex(E value, int id) {
		this.value = value;
		this.id = id;
		distance = 0;
	}
	
	@Override
	public E getValue() {
		return value;
	}
	
	@Override
	public void setValue(E value) {
		this.value = value;
	}
	
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getDistance() {
		return distance;
	}
	
	@Override
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
