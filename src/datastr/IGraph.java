package datastr;

public interface IGraph<V> {

	public void addVertex(V value);
	public void depthFirstSearch();
	public void floydWarshall();
	public boolean containsValue(V value);
	
}
