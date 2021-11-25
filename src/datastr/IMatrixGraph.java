package datastr;

import java.util.ArrayList;

public interface IMatrixGraph<V> {

	public void deleteVertex(Vertex<V> vertex);
	public void addEdge(Vertex<V> vertex1, Vertex<V> vertex2, int weight);
	public void breadthFirstSearch(Vertex<V> start);
	public ArrayList<Integer> dijkstra(Vertex<V> start);
	//We might want to change the return type. TODO
	public ArrayList<Vertex<V>> prim(Vertex<V> initial);
	//We might want to change the return type. TODO
	public ArrayList<Edge<V>> kruskal();
	public int degreeOf(Vertex<V> vertex);
	
}
