package datastr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleGraph<V> extends Graph<V> implements IMatrixGraph<V> {

	private ArrayList<Vertex<V>> vertices;
	private ArrayList<ArrayList<Integer>> edges;

	public SimpleGraph() {
		super();
		setVertices(new ArrayList<Vertex<V>>());
		edges = new ArrayList<ArrayList<Integer>>();
	}

	public ArrayList<ArrayList<Integer>> getEdges() {
		return edges;
	}

	public void setEdges(ArrayList<ArrayList<Integer>> edges) {
		this.edges = edges;
	}
	
	public ArrayList<Vertex<V>> getVertices() {
		return vertices;
	}
	
	public void setVertices(ArrayList<Vertex<V>> vertices) {
		this.vertices = vertices;
	}

	@Override
	public void addVertex(V value) {
		Vertex<V> vertex = new Vertex<V>(value, getVertices().size());
		getVertices().add(vertex);
		edges.add(new ArrayList<Integer>());
		for(int i = 0; i < edges.size()-1; i++) {
			edges.get(i).add(Integer.MAX_VALUE);
			edges.get(edges.size()-1).add(Integer.MAX_VALUE);
		}
		edges.get(edges.size()-1).add(0);
	}

	@Override
	public void addEdge(Vertex<V> vertex1, Vertex<V> vertex2, int weight) {
		edges.get(vertex1.getId()).set(vertex2.getId(), weight);
		edges.get(vertex2.getId()).set(vertex1.getId(), weight);
	}

	@Override
	public void breadthFirstSearch(Vertex<V> start) {
		for(int i = 0; i < getVertices().size(); i++) {
			getVertices().get(i).setColor(VertexColor.WHITE);
			getVertices().get(i).setDistance(Integer.MAX_VALUE);
			getVertices().get(i).setPredecessor(null);
		}
		start.setColor(VertexColor.GRAY);
		start.setDistance(0);
		Queue<Vertex<V>> queue = new LinkedList<Vertex<V>>();
		queue.add(start);
		while(!queue.isEmpty()) {
			Vertex<V> vertex = queue.poll();
			ArrayList<Integer> edgeList = edges.get(vertex.getId());
			for(int i = 0; i < edgeList.size(); i++) {
				if(edgeList.get(i) != Integer.MAX_VALUE && i != vertex.getId()) {
					Vertex<V> endVertex = getVertices().get(i);
					if(endVertex.getColor() == VertexColor.WHITE) {
						endVertex.setColor(VertexColor.GRAY);
						endVertex.setDistance(vertex.getDistance()+1);
						endVertex.setPredecessor(vertex);
						queue.add(endVertex);
					}
				}
			}
			vertex.setColor(VertexColor.BLACK);
		}
	}

	@Override
	public void depthFirstSearch() {
		for(int i = 0; i < getVertices().size(); i++) {
			getVertices().get(i).setColor(VertexColor.WHITE);
			getVertices().get(i).setPredecessor(null);
		}
		for(int i = 0; i < getVertices().size(); i++) {
			if(getVertices().get(i).getColor().equals(VertexColor.WHITE)) {
				depthVisit(getVertices().get(i), 0);
			}
		}
	}

	private int depthVisit(Vertex<V> vertex, int time) {
		time = time + 1;
		vertex.getTimestamps().setFirst(time);
		vertex.setColor(VertexColor.GRAY);
		ArrayList<Integer> edgeList = edges.get(vertex.getId());
		for(int i = 0; i < edgeList.size(); i++) {
			if(edgeList.get(i) != Integer.MAX_VALUE && i != vertex.getId()) {
				Vertex<V> endVertex = getVertices().get(i);
				if(endVertex.getColor() == VertexColor.WHITE) {
					endVertex.setPredecessor(vertex);
					time = depthVisit(endVertex, time);
				}
			}
		}
		vertex.setColor(VertexColor.BLACK);
		time = time + 1;
		vertex.getTimestamps().setSecond(time);
		return time;
	}
	
	@Override
	public ArrayList<Integer> dijkstra(Vertex<V> start) {
		Integer[] previous = new Integer[getVertices().size()];
		LinkedList<Vertex<V>> pq = new LinkedList<Vertex<V>>();
		for(int i = 0; i < getVertices().size(); i++) {
			if(getVertices().get(i).equals(start)) {
				getVertices().get(i).setWeightFromPoint(0);
			}else {
				getVertices().get(i).setWeightFromPoint(Integer.MAX_VALUE);
			}
			pq.offer(getVertices().get(i));
		}
		while(!pq.isEmpty()) {
			Collections.sort(pq, new VertexWeightComparator<V>());
			Vertex<V> vertex = pq.poll();
			ArrayList<Integer> edgeList = edges.get(vertex.getId());
			for(int i = 0; i < edgeList.size(); i++) {
				if(edgeList.get(i) != Integer.MAX_VALUE && i != vertex.getId()) {
					int alternative = vertex.getWeightFromPoint() + edgeList.get(i);
					if(alternative < getVertices().get(i).getWeightFromPoint()) {
						getVertices().get(i).setWeightFromPoint(alternative);
						previous[i] = vertex.getId();
					}
				}
			}
		}
		ArrayList<Integer> output = new ArrayList<Integer>();
		Collections.addAll(output, previous);
		return output;
	}

	@Override
	public void floydWarshall() {
		//This method doesn't work. We need to fix it. TODO
		getMinimumWeightPaths().clear();
		for(int i = 0; i < edges.size(); i++) {
			ArrayList<Integer> newArrayList = new ArrayList<Integer>();
			getMinimumWeightPaths().add(newArrayList);
			for(int j = 0; j < edges.get(i).size(); j++) {
				Integer value = edges.get(i).get(j);
				getMinimumWeightPaths().get(i).add(value);
			}
		}
		for(int k = 0; k < getVertices().size(); k++) {
			for(int i = 0; i < getVertices().size(); i++) {
				for(int j = 0; j < getVertices().size(); j++) {
					if(getMinimumWeightPaths().get(i).get(j) > getMinimumWeightPaths().get(i).get(k) + getMinimumWeightPaths().get(k).get(j)) {
						getMinimumWeightPaths().get(i).set(j, getMinimumWeightPaths().get(i).get(k) + getMinimumWeightPaths().get(k).get(j));
					}
				}
			}
		}
	}

	@Override
	public ArrayList<Vertex<V>> prim(Vertex<V> initial) {
		ArrayList<Vertex<V>> predecessors = new ArrayList<>();
		for (int i = 0; i < getVertices().size(); i++) {
			getVertices().get(i).setWeightFromPoint(Integer.MAX_VALUE);
			getVertices().get(i).setColor(VertexColor.WHITE);
			predecessors.add(null);
		}
		
		initial.setWeightFromPoint(0);
		LinkedList<Vertex<V>> pq = new LinkedList<>();
		for (Vertex<V> vertex : getVertices()) {
			pq.add(vertex);
		}
		
		while(!pq.isEmpty()) {
			Collections.sort(pq, new VertexWeightComparator<V>());
			Vertex<V> vertex = pq.poll();
			ArrayList<Integer> edgeList = edges.get(vertex.getId());
			for(int i = 0; i < edgeList.size(); i++) {
				if(edgeList.get(i) != Integer.MAX_VALUE && i != vertex.getId()) {
					if(getVertices().get(i).getColor() == VertexColor.WHITE && edgeList.get(i) < getVertices().get(i).getWeightFromPoint()) {
						getVertices().get(i).setWeightFromPoint(edgeList.get(i));
						predecessors.set(i, vertex);
					}
				}
			}
			vertex.setColor(VertexColor.BLACK);
		}
		
		return predecessors;
	}

	@Override
	public ArrayList<Edge<V>> kruskal() {
		//Creates the msp, the represent array for each vertex
		ArrayList<Edge<V>> msp = new ArrayList<>();
		int[] represent = new int[getVertices().size()];
		for (int i = 0; i < represent.length; i++) {
			represent[i] = i;
		}
		
		//Gets the edges from the adjacency matrix. Iterates only through the upper part
		//cause it's a symmetric matrix.
		ArrayList<Edge<V>> sortedEdges = new ArrayList<>();
		for (int i = 0; i < edges.size() - 1; i++) {
			for (int j = i + 1; j <  edges.size(); j++) {
				if(!edges.get(i).get(j).equals(Integer.MAX_VALUE)) {
					Vertex<V> init = getVertices().get(i);
					Vertex<V> end = getVertices().get(j);
					Edge<V> edge = new Edge<V>(init, end, edges.get(i).get(j));
					sortedEdges.add(edge);
				}
			}
		}
		
		Collections.sort(sortedEdges, new EdgeComparator<>());
		
		//Compares for each edge if it's vertices are in the same "set" based on their represent
		//If they have different represents, the edge is added to the msp.
		for (Edge<V> edge : sortedEdges) {
			if(represent[edge.getInitial().getId()] != represent[edge.getEnd().getId()]) {
				represent[edge.getEnd().getId()] = edge.getInitial().getId();
				msp.add(edge);
			}
		}
		
		return msp; //Returns A, the msp. Change return type. TODO
	}

	@Override
	public int degreeOf(Vertex<V> vertex) {
		int count = 0;
		ArrayList<Integer> edgeList = edges.get(vertex.getId());
		for(int i = 0; i < edgeList.size(); i++) {
			if(edgeList.get(i) != Integer.MAX_VALUE && i != vertex.getId()) {
				count++;
				}
			}
		return count;
	}

	@Override
	public boolean containsValue(V value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vertex<V> searchInVertexList(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
