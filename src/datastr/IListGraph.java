package datastr;

import java.util.ArrayList;

public interface IListGraph<V> {
	
	public ListVertex<V> searchInListVertexList(int id);
	public void addEdge(ListVertex<V> vertex1, ListVertex<V> vertex2, int weight);
	public void breadthFirstSearch(ListVertex<V> start);
	public ArrayList<Integer> dijkstra(ListVertex<V> start);
	//We might want to change the return type. TODO
	public Graph<ListVertex<V>> prim();
	//We might want to change the return type. TODO
	public Graph<ListVertex<V>> kruskal();
	public int degreeOf(ListVertex<V> vertex);
	
}
