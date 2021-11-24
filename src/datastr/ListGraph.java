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

	public ArrayList<ListVertex<V>> getAdjList() {
		return adjList;
	}

	public void setAdjList(ArrayList<ListVertex<V>> adjList) {
		this.adjList = adjList;
	}

	@Override
	public void addVertex(V value) {
		ListVertex<V> vertex = new ListVertex<V>(value, adjList.size());
		adjList.add(vertex);
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
	public void depthFirstSearch() {
		
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
	public ArrayList<ListVertex<V>> prim(ListVertex<V> initial) {
		ArrayList<ListVertex<V>> predecessors = new ArrayList<>();
		LinkedList<ListVertex<V>> pq = new LinkedList<>();
		for (ListVertex<V> vertex : adjList) {
			vertex.setWeightFromPoint(Integer.MAX_VALUE);
			vertex.setColor(VertexColor.WHITE);
			pq.add(vertex);
		}
		
		initial.setWeightFromPoint(0);
		while(!pq.isEmpty()) {
			Collections.sort(pq, new VertexWeightComparator<V>());
			ListVertex<V> vertex = pq.poll();
			for (ListEdge<V>  edge : vertex.getEdges()) {
				ListVertex<V> end = edge.getEnd();
				if(end.getColor() == VertexColor.WHITE && edge.getWeight() < end.getWeightFromPoint()) {
					end.setWeightFromPoint(edge.getWeight());
					predecessors.set(end.getId(), vertex);
				}
			}
			vertex.setColor(VertexColor.BLACK);
		}
		
		return predecessors;
	}

	@Override
	public ArrayList<ListEdge<V>> kruskal() {
		ArrayList<ListEdge<V>> msp = new ArrayList<>();
		int[] represent = new int[adjList.size()];
		for (int i = 0; i < represent.length; i++) {
			represent[i] = i;
		}
		
		ArrayList<ListEdge<V>> sortedEdges = new ArrayList<>();
		for (ListVertex<V> vertex : adjList) {
			for (ListEdge<V> edge : vertex.getEdges()) {
				if(!listContainsEdge(sortedEdges, edge)) {
					sortedEdges.add(edge);
				}
			}
		}
		
		Collections.sort(sortedEdges, new ListEdgeComparator<>());
		
		for (ListEdge<V> edge : sortedEdges) {
			if(represent[edge.getInitial().getId()] != represent[edge.getEnd().getId()]) {
				represent[edge.getEnd().getId()] = edge.getInitial().getId();
				msp.add(edge);
			}
		}
		
		return msp;
	}

	private boolean listContainsEdge(ArrayList<ListEdge<V>> list, ListEdge<V> edge) {
		boolean contains = false;
		for (int i = 0; i < list.size() && !contains; i++) {
			ListEdge<V> current = list.get(i);
			if((edge.getInitial().equals(current.getInitial()) && edge.getEnd().equals(current.getEnd())) || 
					(edge.getEnd().equals(current.getInitial()) && edge.getInitial().equals(current.getEnd()))) {
				contains = true;
			}
		}
		return contains;
	}

	@Override
	public int degreeOf(ListVertex<V> vertex) {
		return vertex.getEdges().size();
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ListVertex<V> searchInListVertexList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
