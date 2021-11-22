package datastr;

import java.util.ArrayList;
import java.util.Collections;

public class ListGraph<V> extends Graph<V> implements IListGraph<V> {

	private ArrayList<ListVertex<V>> adjList;
	
	public ListGraph() {
		super();
		adjList = new ArrayList<ListVertex<V>>();
	}

	@Override
	public void addVertex(V value) {
		ListVertex<V> vertex = new ListVertex<V>(value, adjList.size());
		adjList.add(vertex);
	}

	@Override
	public void depthFirstSearch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void floydWarshall() {
		createDistanceMatrix();
		for(int k = 0; k < adjList.size(); k++) {
			for(int i = 0; i < adjList.size(); i++) {
				for(int j = 0; j < adjList.size(); j++) {
					if(getMinimumWeightPaths().get(i).get(j) > getMinimumWeightPaths().get(i).get(k) + getMinimumWeightPaths().get(k).get(j)) {
						getMinimumWeightPaths().get(i).set(j, getMinimumWeightPaths().get(i).get(k) + getMinimumWeightPaths().get(k).get(j));
					}
				}
			}
		}
	}

	private void createDistanceMatrix() {
		ArrayList<ArrayList<Integer>> distance = new ArrayList<ArrayList<Integer>>();
		for (ListVertex<V> vertex : adjList) {
			ArrayList<Integer> rowList = new ArrayList<>();
			for (int i = 0; i < adjList.size(); i++) {
				if(i == vertex.getId()) {
					rowList.add(0);
				}else {
					rowList.add(Integer.MAX_VALUE);
				}
				
			}
			
			for (Edge<V> edge : vertex.getEdges()) {
				rowList.set(edge.getEnd().getId(), edge.getWeight());
			}
			
			distance.add(rowList);
		}
		
		setMinimumWeightPaths(distance);
	}

	@Override
	public ListVertex<V> searchInListVertexList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addEdge(ListVertex<V> vertex1, ListVertex<V> vertex2, int weight) {
		
		if(adjList.contains(vertex1) && adjList.contains(vertex2)) {
			int idx1 = containsEdge(vertex1, vertex2);
			int idx2 = containsEdge(vertex2, vertex1);
			if (idx1 != -1) {
				vertex1.getEdges().get(idx1).setWeight(weight);
				vertex2.getEdges().get(idx2).setWeight(weight);
			}else {
				Edge<V> edge1 = new Edge<V>(vertex1, vertex2, weight);
				vertex1.getEdges().add(edge1);
				Edge<V> edge2 = new Edge<V>(vertex2, vertex1, weight);
				vertex2.getEdges().add(edge2);
			}
		}
	}
	
	public int containsEdge(ListVertex<V> initial, ListVertex<V> end) {
		int found = -1;
		for (int i = 0; i < initial.getEdges().size() && found != -1; i++) {
			if(initial.getEdges().get(i).getEnd().equals(end)) {
				found = i;
			}
		}
		
		return found;
	}

	@Override
	public void breadthFirstSearch(ListVertex<V> start) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Integer> dijkstra(ListVertex<V> start) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph<ListVertex<V>> prim() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graph<ListVertex<V>> kruskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int degreeOf(ListVertex<V> vertex) {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList<ListVertex<V>> getAdjList() {
		return adjList;
	}

	public void setAdjList(ArrayList<ListVertex<V>> adjList) {
		this.adjList = adjList;
	}

}
