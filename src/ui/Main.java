package ui;

import java.util.ArrayList;

import datastr.SimpleGraph;
import datastr.Vertex;

public class Main {

	public static void main(String[] args) {
		exampleThree();
	}

	public static void exampleOne() {
		SimpleGraph<Integer> sg = new SimpleGraph<Integer>();
		sg.addVertex(1);
		sg.addVertex(2);
		sg.addVertex(3);
		sg.addVertex(4);
		sg.addVertex(5);
		sg.addVertex(6);

		Vertex<Integer> v1 = sg.getVertices().get(0);
		Vertex<Integer> v2 = sg.getVertices().get(1);
		Vertex<Integer> v3 = sg.getVertices().get(2);
		Vertex<Integer> v4 = sg.getVertices().get(3);
		Vertex<Integer> v5 = sg.getVertices().get(4);
		Vertex<Integer> v6 = sg.getVertices().get(5);
		
		sg.addEdge(v1, v2, 2);
		sg.addEdge(v2, v1, 2);

		sg.addEdge(v2, v3, 10);
		sg.addEdge(v3, v2, 10);

		sg.addEdge(v3, v6, 2);
		sg.addEdge(v6, v3, 2);

		sg.addEdge(v6, v5, 2);
		sg.addEdge(v5, v6, 2);

		sg.addEdge(v5, v4, 4);
		sg.addEdge(v4, v5, 4);

		sg.addEdge(v4, v1, 4);
		sg.addEdge(v1, v4, 4);

		sg.addEdge(v2, v5, 2);
		sg.addEdge(v5, v2, 2);

		sg.breadthFirstSearch(v1);

		for(int i = 0; i < sg.getVertices().size(); i++) {
			System.out.println("v" + (sg.getVertices().get(i).getId()+1) + " d=" + sg.getVertices().get(i).getDistance() + " c=" + sg.getVertices().get(i).getColor());
		}

		sg.depthFirstSearch();

		for(int i = 0; i < sg.getVertices().size(); i++) {
			System.out.println("v" + (sg.getVertices().get(i).getId()+1) + " f=" + sg.getVertices().get(i).getTimestamps().getFirst().toString() + " s=" + sg.getVertices().get(i).getTimestamps().getSecond().toString());
		}

		ArrayList<Integer> result = sg.dijkstra(v1);

		System.out.println(result.toString());
	}

	public static void exampleTwo() {
		SimpleGraph<Integer> sg = new SimpleGraph<Integer>();
		sg.addVertex(1);
		sg.addVertex(2);
		sg.addVertex(3);
		sg.addVertex(4);
		sg.addVertex(5);
		sg.addVertex(6);

		Vertex<Integer> v1 = sg.getVertices().get(0);
		Vertex<Integer> v2 = sg.getVertices().get(1);
		Vertex<Integer> v3 = sg.getVertices().get(2);
		Vertex<Integer> v4 = sg.getVertices().get(3);
		Vertex<Integer> v5 = sg.getVertices().get(4);
		Vertex<Integer> v6 = sg.getVertices().get(5);
		
		sg.addEdge(v1, v2, 10);
		sg.addEdge(v2, v1, 10);
		
		sg.addEdge(v1, v3, 3);
		sg.addEdge(v3, v1, 3);
		
		sg.addEdge(v2, v3, 5);
		sg.addEdge(v3, v2, 5);
		
		sg.addEdge(v2, v4, 1);
		sg.addEdge(v4, v2, 1);
		
		sg.addEdge(v2, v5, 13);
		sg.addEdge(v5, v2, 13);
		
		sg.addEdge(v2, v6, 15);
		sg.addEdge(v6, v2, 15);
		
		sg.addEdge(v3, v4, 1);
		sg.addEdge(v4, v3, 1);
		
		sg.addEdge(v4, v5, 16);
		sg.addEdge(v5, v4, 16);
		
		sg.addEdge(v4, v6, 15);
		sg.addEdge(v6, v4, 15);
		
		sg.addEdge(v5, v6, 1);
		sg.addEdge(v6, v5, 1);
		
		ArrayList<Integer> result = sg.dijkstra(v1);

		System.out.println(result.toString());
	}
	
	public static void exampleThree() {
		SimpleGraph<Integer> sg = new SimpleGraph<Integer>();
		sg.deleteVertex(new Vertex<Integer>(10, 0));
	}
	
}
