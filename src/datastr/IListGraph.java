package datastr;

import java.util.ArrayList;

public interface IListGraph<V> {

	public void deleteVertex(ListVertex<V> vertex);
	public void addEdge(ListVertex<V> vertex1, ListVertex<V> vertex2, int weight);
	public void breadthFirstSearch(ListVertex<V> start);
	public ArrayList<Integer> dijkstra(ListVertex<V> start);
	//We might want to change the return type. TODO
	public ArrayList<ListVertex<V>> prim(ListVertex<V> initial);
	//We might want to change the return type. TODO
	public ArrayList<ListEdge<V>> kruskal();
	public int degreeOf(ListVertex<V> vertex);
	
}
