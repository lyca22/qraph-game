package datastr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

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
			
			for (ListEdge<V> edge : vertex.getEdges()) {
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
				ListEdge<V> edge1 = new ListEdge<V>(vertex1, vertex2, weight);
				vertex1.getEdges().add(edge1);
				ListEdge<V> edge2 = new ListEdge<V>(vertex2, vertex1, weight);
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
		for (ListVertex<V> vertex : adjList) {
			vertex.setColor(VertexColor.WHITE);
			vertex.setDistance(Integer.MAX_VALUE);
			vertex.setPredecessor(null);
		}
		start.setColor(VertexColor.GRAY);
		start.setDistance(0);
		
		Queue<ListVertex<V>> queue = new LinkedList<ListVertex<V>>();
		queue.add(start);
		while(!queue.isEmpty()) {
			ListVertex<V> vertex = queue.poll();
			ArrayList<ListEdge<V>> edgeList = vertex.getEdges();
			for (ListEdge<V> edge : edgeList) {
				ListVertex<V> endVertex = edge.getEnd();
				if(endVertex.getColor() == VertexColor.WHITE) {
					endVertex.setColor(VertexColor.GRAY);
					endVertex.setDistance(vertex.getDistance()+1);
					endVertex.setPredecessor(vertex);
					queue.add(endVertex);
				}
			}
		}
	}

	@Override
	public ArrayList<Integer> dijkstra(ListVertex<V> start) {
		Integer[] previous = new Integer[adjList.size()];
		LinkedList<ListVertex<V>> pq = new LinkedList<ListVertex<V>>();
		for (ListVertex<V> vertex : adjList) {
			if(vertex.equals(start)) {
				vertex.setWeightFromPoint(0);
			}else {
				vertex.setWeightFromPoint(Integer.MAX_VALUE);
			}
			pq.offer(vertex);
		}
		
		while(!pq.isEmpty()) {
			Collections.sort(pq, new VertexWeightComparator<V>());
			ListVertex<V> vertex = pq.poll();
			for (ListEdge<V> edge : vertex.getEdges()) {
				int alternative = edge.getWeight() + vertex.getWeightFromPoint();
				if(alternative < edge.getEnd().getWeightFromPoint()) {
					edge.getEnd().setWeightFromPoint(alternative);
					previous[edge.getEnd().getId()] = vertex.getId();
				}
			}
		}
		
		ArrayList<Integer> output = new ArrayList<Integer>();
		Collections.addAll(output, previous);
		return output;
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
