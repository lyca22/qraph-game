package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import datastr.ListGraph;
import datastr.ListVertex;

class ListGraphTest {

	void setUp1() throws Exception {
	}

	void setUp2() throws Exception {
		ListGraph<Integer> sp = new ListGraph<Integer>();
		sp.addVertex(1);
	}

	void setUp3() throws Exception {
		ListGraph<Integer> sp = new ListGraph<Integer>();
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
		sp.addEdge(v2, v1, 3);
		sp.addEdge(v2, v3, 4);
		sp.addEdge(v2, v4, 1);
		sp.addEdge(v3, v1, 6);
		sp.addEdge(v3, v2, 4);
		sp.addEdge(v3, v4, 2);
		sp.addEdge(v4, v1, 3);
		sp.addEdge(v4, v2, 1);
		sp.addEdge(v4, v3, 2);
	}
	
	@Test
	void testAddVertex() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDeleteVertex() {
		fail("Not yet implemented");
	}
	
	@Test
	void testAddEdge() {
		fail("Not yet implemented");
	}
	
	@Test
	void testBFS() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDFS() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDijkstra() {
		fail("Not yet implemented");
	}
	
	@Test
	void testFloydWarshall() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}
	
}
