package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import datastr.ListEdge;
import datastr.ListGraph;
import datastr.ListVertex;
import datastr.Pair;

class ListGraphTest {

	ListGraph<Integer> sp;
	
	void setUp1() throws Exception {
		sp = new ListGraph<Integer>();
	}

	void setUp2() throws Exception {
		sp = new ListGraph<Integer>();
		sp.addVertex(1);
	}

	void setUp3() throws Exception {
		sp = new ListGraph<Integer>();
		sp.addVertex(1);
		sp.addVertex(2);
		sp.addVertex(3);
		sp.addVertex(4);
		ListVertex<Integer> v1 = sp.getAdjList().get(0);
		ListVertex<Integer> v2 = sp.getAdjList().get(1);
		ListVertex<Integer> v3 = sp.getAdjList().get(2);
		ListVertex<Integer> v4 = sp.getAdjList().get(3);
		sp.addEdge(v1, v2, 3);
		sp.addEdge(v1, v3, 6);
		sp.addEdge(v1, v4, 3);
		sp.addEdge(v2, v3, 4);
		sp.addEdge(v2, v4, 1);
		sp.addEdge(v3, v4, 2);
	}
	
	@Test
	void testAddVertex() {
		try {
			setUp1();
			
			sp.addVertex(1);
			assertTrue(sp.getAdjList().size() == 1);
			assertTrue(sp.getAdjList().get(0).getValue() == 1);
			
			setUp2();
			
			sp.addVertex(3);
			assertTrue(sp.getAdjList().size() == 2);
			assertTrue(sp.getAdjList().get(1).getValue() == 3);
			
			setUp3();
			
			sp.addVertex(5);
			assertTrue(sp.getAdjList().size() == 5);
			assertTrue(sp.getAdjList().get(4).getValue() == 5);
				
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testDeleteVertex() {
		ListVertex<Integer> vertex;
		try {
			setUp1();
			
			vertex = new ListVertex<Integer>(621, 0);
			sp.deleteVertex(vertex);
			assertTrue(sp.getAdjList().isEmpty());
			assertTrue(!sp.getAdjList().contains(vertex));
			
			setUp2();
			
			vertex = sp.getAdjList().get(0);
			sp.deleteVertex(vertex);
			assertTrue(sp.getAdjList().isEmpty());
			assertTrue(!sp.getAdjList().contains(vertex));
			
			setUp3();
			
			vertex = sp.getAdjList().get(0);
			sp.deleteVertex(vertex);
			assertTrue(sp.getAdjList().size() == 3);
			assertTrue(!sp.getAdjList().contains(vertex));
			
			for(int i = 0; i < sp.getAdjList().size(); i++) {
				ArrayList<ListEdge<Integer>> edgeList = sp.getAdjList().get(i).getEdges();
				for(int j = 0; j < edgeList.size(); j++) {
					if(edgeList.get(j).getEnd().equals(vertex)) {
						fail();
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	void testAddEdge() {
		ListVertex<Integer> vertex1;
		ListVertex<Integer> vertex2;
		try {
			setUp1();
			
			vertex1 = new ListVertex<Integer>(621, 0);
			vertex2 = new ListVertex<Integer>(926, 1);
			sp.addEdge(vertex1, vertex2, 10);
			assertTrue(vertex1.getEdges().isEmpty());
			
			setUp2();
			
			vertex1 = sp.getAdjList().get(0);
			vertex2 = new ListVertex<Integer>(20, sp.getAdjList().size());
			sp.addEdge(vertex1, vertex2, 10);
			assertTrue(vertex1.getEdges().isEmpty());
			
			setUp3();

			vertex1 = sp.getAdjList().get(0);
			vertex2 = sp.getAdjList().get(1);
			sp.addEdge(vertex1, vertex2, 10);
			boolean found = false;
			for(int i = 0; i < vertex1.getEdges().size() && !found; i++) {
				if(vertex1.getEdges().get(i).getWeight() == 10) {
					found = true;
				}
			}
			
			assertTrue(found);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testBFS() {
		ListVertex<Integer> vertex;
		try {
			setUp1();
			
			vertex = new ListVertex<Integer>(621, 0);
			sp.breadthFirstSearch(vertex);
			assertTrue(vertex.getDistance() == 0);
			
			setUp2();
			
			vertex = sp.getAdjList().get(0);
			sp.breadthFirstSearch(vertex);
			assertTrue(vertex.getDistance() == 0);
			assertTrue(vertex.getPredecessor() == null);
			
			setUp3();
			
			vertex = sp.getAdjList().get(1);
			sp.breadthFirstSearch(vertex);
			
			assertTrue(vertex.getDistance() == 0);
			assertTrue(sp.getAdjList().get(0).getDistance() == 1);
			assertTrue(sp.getAdjList().get(2).getDistance() == 1);
			assertTrue(sp.getAdjList().get(3).getDistance() == 1);
			
			assertTrue(vertex.getPredecessor() == null);
			assertTrue(sp.getAdjList().get(0).getPredecessor().equals(vertex));
			assertTrue(sp.getAdjList().get(2).getPredecessor().equals(vertex));
			assertTrue(sp.getAdjList().get(3).getPredecessor().equals(vertex));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testDFS() {
		Pair<Integer, Integer> timestamps;
		try {
			setUp1();
			
			sp.depthFirstSearch();
			
			setUp2();
			
			sp.depthFirstSearch();
			timestamps = sp.getAdjList().get(0).getTimestamps();
			assertTrue(timestamps.getFirst().intValue() == 1);
			assertTrue(timestamps.getSecond().intValue() == 2);
			
			setUp3();
			
			sp.depthFirstSearch();
			
			timestamps = sp.getAdjList().get(0).getTimestamps();
			assertTrue(timestamps.getFirst().intValue() == 1);
			assertTrue(timestamps.getSecond().intValue() == 8);
			
			timestamps = sp.getAdjList().get(1).getTimestamps();
			assertTrue(timestamps.getFirst().intValue() == 2);
			assertTrue(timestamps.getSecond().intValue() == 7);
			
			timestamps = sp.getAdjList().get(2).getTimestamps();
			assertTrue(timestamps.getFirst().intValue() == 3);
			assertTrue(timestamps.getSecond().intValue() == 6);
			
			timestamps = sp.getAdjList().get(3).getTimestamps();
			assertTrue(timestamps.getFirst().intValue() == 4);
			assertTrue(timestamps.getSecond().intValue() == 5);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testDijkstra() {
		ListVertex<Integer> vertex;
		ArrayList<Integer> output;
		try {
			setUp1();
			
			vertex = new ListVertex<Integer>(621, 0);
			output = sp.dijkstra(vertex);
			assertTrue(output.isEmpty());
			
			setUp2();
			
			vertex = sp.getAdjList().get(0);
			output = sp.dijkstra(vertex);
			assertTrue(output.get(0) == null);
			
			setUp3();
			
			vertex = sp.getAdjList().get(1);
			output = sp.dijkstra(vertex);
			assertTrue(output.get(0) == 1);
			assertTrue(output.get(1) == null);
			assertTrue(output.get(2) == 3);
			assertTrue(output.get(3) == 1);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testFloydWarshall() {
		try {
			setUp1();
			
			sp.floydWarshall();
			assertTrue(sp.getMinimumWeightPaths().isEmpty());
			
			setUp2();
			
			sp.floydWarshall();
			assertTrue(sp.getMinimumWeightPaths().size() == 1);
			assertTrue(sp.getMinimumWeightPaths().get(0).size() == 1);
			
			assertTrue(sp.getMinimumWeightPaths().get(0).get(0) == 0);
			
			setUp3();
			
			sp.floydWarshall();
			assertTrue(sp.getMinimumWeightPaths().size() == 4);
			assertTrue(sp.getMinimumWeightPaths().get(0).size() == 4);
			
			assertTrue(sp.getMinimumWeightPaths().get(0).get(0) == 0);
			assertTrue(sp.getMinimumWeightPaths().get(1).get(0) == 3);
			assertTrue(sp.getMinimumWeightPaths().get(1).get(1) == 0);
			assertTrue(sp.getMinimumWeightPaths().get(2).get(0) == 5);
			assertTrue(sp.getMinimumWeightPaths().get(2).get(1) == 3);
			assertTrue(sp.getMinimumWeightPaths().get(2).get(2) == 0);
			assertTrue(sp.getMinimumWeightPaths().get(3).get(0) == 3);
			assertTrue(sp.getMinimumWeightPaths().get(3).get(1) == 1);
			assertTrue(sp.getMinimumWeightPaths().get(3).get(2) == 2);
			assertTrue(sp.getMinimumWeightPaths().get(3).get(3) == 0);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	void testPrim() {
		fail("Not yet implemented");
	}
	
	@Test
	void testKruskal() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDegreeOf() {
		ListVertex<Integer> vertex;
		try {
			setUp1();
			
			vertex = new ListVertex<Integer>(621, 0);
			assertTrue(sp.degreeOf(vertex) == 0);
			
			setUp2();
			
			vertex = sp.getAdjList().get(0);
			assertTrue(sp.degreeOf(vertex) == 0);
			
			setUp3();
			
			vertex = sp.getAdjList().get(0);
			assertTrue(sp.degreeOf(vertex) == 3);
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
}
