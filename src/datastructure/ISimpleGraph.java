package datastructure;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public interface ISimpleGraph<E extends Comparable<E>> {

	public void bfs();
	public void dfs();
	public ArrayList<SimpleVertex<E>> dijkstra(E value);
	public int[][] floydWarshall();
	public Set<Integer> kruskal();	//Pending: Change the return type
	//public TreeSet<SimpleVertex<E>> prim();
	public int degreeOf(SimpleVertex<E> vertex);
	public int containsVertex(E value);		//Return changed from boolean to int
	public void addVertex(E value);
	public void addEdge(E initial, E end, int weight);
	public ArrayList<SimpleVertex<E>> getVertices();
	public int[][] getEdges();
	public void setVertices(ArrayList<SimpleVertex<E>> vertices);
	public void setEdges(int[][] edges);
	
}
