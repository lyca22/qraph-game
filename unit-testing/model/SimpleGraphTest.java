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
	void testAddVertex() throws Exception {
		
		setUp1();
		sp.addVertex(1);
		assertEquals(sp.getVertices().size(), 1);
		
		setUp2();
		sp.addVertex(3);
		assertEquals(sp.getVertices().size(), 2);
		assertTrue(sp.getVertices().get(1).getValue()==3);
		
		setUp3();
		sp.addVertex(5);
		assertEquals(sp.getVertices().size(), 5);
		assertTrue(sp.getVertices().get(4).getValue()==5);
	}

	@Test
	void testDeleteVertex() throws Exception {
		setUp1();
		
		setUp2();
		sp.deleteVertex(sp.getVertices().get(0));
		assertTrue(sp.getVertices().isEmpty());
		
		setUp3();
		sp.deleteVertex(sp.getVertices().get(3));
		assertTrue(sp.getVertices().size() == 3);
	}
	
	
	@Test
	void testAddEdge() throws Exception {		
		setUp2();
		
		setUp3();
		sp.addEdge(sp.getVertices().get(0), sp.getVertices().get(1), 10);
		assertTrue(true);
	}
	
	
	@Test
	void testBFS() throws Exception {
		setUp2();
		sp.breadthFirstSearch(sp.getVertices().get(0));
		assertEquals(sp.getVertices().get(0).getDistance(), 0);
		
		
		setUp3();
		sp.breadthFirstSearch(sp.getVertices().get(1));
		assertEquals(sp.getVertices().get(0).getDistance(), 1);
		assertEquals(sp.getVertices().get(1).getDistance(), 0);
		assertEquals(sp.getVertices().get(2).getDistance(), 1);
		assertEquals(sp.getVertices().get(3).getDistance(), 1);
		
		assertEquals(sp.getVertices().get(0).getPredecessor(), sp.getVertices().get(1));
		assertEquals(sp.getVertices().get(1).getPredecessor(), null);
		assertEquals(sp.getVertices().get(2).getPredecessor(), sp.getVertices().get(1));
		assertEquals(sp.getVertices().get(3).getPredecessor(), sp.getVertices().get(1));		
	}
	
	
	@Test
	void testDFS() {
		fail("Not yet implemented");
	}
	
	/*
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
	*/
}
