package datastructure;

import java.util.ArrayList;
import java.util.Set;

public class SimpleGraph<E extends Comparable<E>> implements ISimpleGraph<E> {

	private ArrayList<SimpleVertex<E>> vertices;
	private int[][] edges;
	
	public SimpleGraph() {
		vertices = new ArrayList<>();
		edges = new int[0][0];
	}

	@Override
	public void bfs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dfs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SimpleVertex<E>> dijkstra(E value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] floydWarshall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Integer> kruskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int degreeOf(SimpleVertex<E> vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean containsVertex(E value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addVertex(E value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addEdge(E initial, E end, int weight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<SimpleVertex<E>> getVertices() {
		return vertices;
	}

	@Override
	public void setVertices(ArrayList<SimpleVertex<E>> vertices) {
		this.vertices = vertices;
	}

	@Override
	public int[][] getEdges() {
		return edges;
	}

	@Overrides
	public void setEdges(int[][] edges) {
		this.edges = edges;
	}

	
	
}
