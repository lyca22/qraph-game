package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import datastr.SimpleGraph;
import datastr.Vertex;

class SimpleGraphTest {

	SimpleGraph<Integer> sp;
	
	void setUp1() throws Exception {
		sp = new SimpleGraph<Integer>();
	}

	void setUp2() throws Exception {
		sp = new SimpleGraph<Integer>();
		sp.addVertex(1);
	}

	void setUp3() throws Exception {
		sp = new SimpleGraph<Integer>();
		sp.addVertex(1);
		sp.addVertex(2);
		sp.addVertex(3);
		sp.addVertex(4);
		Vertex<Integer> v1 = sp.getVertices().get(0);
		Vertex<Integer> v2 = sp.getVertices().get(1);
		Vertex<Integer> v3 = sp.getVertices().get(2);
		Vertex<Integer> v4 = sp.getVertices().get(3);
		sp.addEdge(v1, v2, 3);
		sp.addEdge(v1, v3, 6);
		sp.addEdge(v1, v4, 3);
		sp.addEdge(v2, v3, 4);
		sp.addEdge(v2, v4, 1);
		sp.addEdge(v3, v4, 2);
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
