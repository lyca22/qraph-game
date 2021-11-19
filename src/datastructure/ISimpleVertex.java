package datastructure;

public interface ISimpleVertex<E extends Comparable<E>> {
	public E getValue();
	public int getId();
	public int getDistance();
	public void setValue(E value);
	public void setId(int id);
	public void setDistance(int distance);
	
}
