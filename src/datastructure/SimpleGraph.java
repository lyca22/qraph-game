package datastructure;

import java.util.ArrayList;
import java.util.PriorityQueue;
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
		int source = containsVertex(value);
		int[] dist = null;
		ArrayList<SimpleVertex<E>> prev = new ArrayList<>(vertices.size());
		
		if(source != -1) {
			dist = new int[vertices.size()];
			dist[source] = 0;
			//WARNING: PriorityQueue is java built in class that is not sorted
			//Requires a Comparator parameter
			PriorityQueue<SimpleVertex<E>> queue = new PriorityQueue<>(vertices.size()); 
			for (int i = 0; i < vertices.size(); i++) {
				if(i != source) {
					dist[i] = Integer.MAX_VALUE;
				}
				queue.add(vertices.get(i)); //It does not works cause the queue has not order
			}
			
			while(!queue.isEmpty()) {
				SimpleVertex<E> first = queue.remove();
				int id = first.getId();
				for (SimpleVertex<E> neighbor : vertices) {
					if(edges[id][neighbor.getId()] != 0 && edges[id][neighbor.getId()] != Integer.MAX_VALUE) {
						int alt = dist[id] + edges[id][neighbor.getId()];
						if(alt < dist[neighbor.getId()]) {
							dist[neighbor.getId()] = alt;
							prev.set(neighbor.getId(), first);
							//The queue does not support decrease operation
							//queue.decreasePriority(neighbor, alt);
						}
					}
				}
			}
			
		}
		
		return prev;
	}

	@Override
	public int[][] floydWarshall() {
		int[][] distances = edges;
		for (int k = 0; k < distances.length; k++) {
			for (int i = 0; i < distances.length; i++) {
				for (int j = 0; j < distances.length; j++) {
					if(distances[i][j] > distances[i][k] + distances[k][j]) {
						distances[i][j] = distances[i][k] + distances[k][j];
					}
				}
			}
		}
		
		return distances;
	}

	@Override
	public Set<Integer> kruskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int degreeOf(SimpleVertex<E> vertex) {
		int degree = 0;
		for (int i = 0; i < edges.length; i++) {
			if(edges[i][vertex.getId()] != 0 && edges[i][vertex.getId()] != Integer.MAX_VALUE) {
				degree++;
			}
		}
		
		return degree;
	}

	@Override
	public int containsVertex(E value) {
		int position = -1;
		int i = 0;
		while(i < vertices.size()) {
			if(vertices.get(i).getValue().equals(value)) {
				position = i;
			}
			i++;
		}
		
		return position;
	}

	@Override
	public void addVertex(E value) {
		SimpleVertex<E> vertex = new SimpleVertex<E>(value, vertices.size());
		vertices.add(vertex);
		
		int[][] tempEdges = new int[vertices.size()][vertices.size()];
		for (int i = 0; i < tempEdges.length - 1; i++) {
			//Fill of the new column and row
			tempEdges[i][tempEdges.length - 1] = Integer.MAX_VALUE;
			tempEdges[tempEdges.length - 1][i] = Integer.MAX_VALUE;
			
			//Copy of previous values
			for(int j = 0; j < tempEdges.length - 1; j++) {
				tempEdges[i][j] = edges[i][j];
			}
		}
		edges = tempEdges;
	}

	@Override
	public void addEdge(E initial, E end, int weight) {
		int indexInitial = containsVertex(initial);
		int indexEnd = containsVertex(end);
		if(indexInitial != -1 && indexEnd != -1) {
			edges[indexInitial][indexEnd] = weight;
			edges[indexEnd][indexInitial] = weight;
		}else {
			throw new IndexOutOfBoundsException("The graph does not contains the vertex initial and/or end");
		}
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

	@Override
	public void setEdges(int[][] edges) {
		this.edges = edges;
	}

	
	
}
