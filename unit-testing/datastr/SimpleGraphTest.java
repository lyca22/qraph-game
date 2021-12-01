package datastr;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;

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
	void testAddVertex() throws Exception {
		
		setUp1();
		sp.addVertex(1);
		assertEquals(sp.getVertices().size(), 1);
		
		setUp2();
		sp.addVertex(3);
		assertEquals(sp.getVertices().size(), 2);
		assertTrue(sp.getVertices().get(1).getValue()==3);
		
		assertEquals(sp.getEdges().get(0).get(0), 0);
		
		
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
	void testDFS() throws Exception {
		setUp2();
		sp.depthFirstSearch();
		assertEquals(sp.getVertices().get(0).getTimestamps().getFirst(), 1);
		assertEquals(sp.getVertices().get(0).getTimestamps().getSecond(), 2);
		
		setUp3();
		sp.depthFirstSearch();
		assertEquals(sp.getVertices().get(0).getTimestamps().getFirst(), 1);
		assertEquals(sp.getVertices().get(0).getTimestamps().getSecond(), 8);
		assertEquals(sp.getVertices().get(1).getTimestamps().getFirst(), 2);
		assertEquals(sp.getVertices().get(1).getTimestamps().getSecond(), 7);
		assertEquals(sp.getVertices().get(2).getTimestamps().getFirst(), 3);
		assertEquals(sp.getVertices().get(2).getTimestamps().getSecond(), 6);
		assertEquals(sp.getVertices().get(3).getTimestamps().getFirst(), 4);
		assertEquals(sp.getVertices().get(3).getTimestamps().getSecond(), 5);
	}
	
	
	@Test
	void testDijkstra() throws Exception {
		
		setUp2();
		assertEquals(sp.dijkstra(sp.getVertices().get(0)).get(0), null);
		
		
		setUp3();
		ArrayList<Integer> a = sp.dijkstra(sp.getVertices().get(1));
			assertEquals(a.get(0), 1);
			assertEquals(a.get(1), null);
			assertEquals(a.get(2), 3);
			assertEquals(a.get(3), 1);
	}
	
	
	@Test
	void testFloydWarshall() throws Exception {
		
		setUp2();
		sp.floydWarshall();		
		assertEquals(sp.getEdges().get(0).get(0), 0);
		
		setUp3();
		sp.floydWarshall();		
		assertEquals(sp.getEdges().get(0).get(0), 0);
		assertEquals(sp.getEdges().get(0).get(1), 3);
		assertEquals(sp.getEdges().get(0).get(2), 6);
		assertEquals(sp.getEdges().get(0).get(3), 3);
		assertEquals(sp.getEdges().get(1).get(1), 0);
		assertEquals(sp.getEdges().get(1).get(2), 4);
		assertEquals(sp.getEdges().get(1).get(3), 1);
		assertEquals(sp.getEdges().get(2).get(2), 0);
		assertEquals(sp.getEdges().get(2).get(3), 2);
		assertEquals(sp.getEdges().get(3).get(3), 0);
	}
	
	
	@Test
	void testPrim() throws Exception {
		setUp2();
		
		
		setUp3();
		ArrayList<Vertex<Integer>> a;
		a = sp.prim(sp.getVertices().get(0));
			
		assertTrue(a.get(1).equals(sp.getVertices().get(0)));
		assertTrue(a.get(2).equals(sp.getVertices().get(3)));
		assertTrue(a.get(3).equals(sp.getVertices().get(1)));
	}
	
	
	@Test
	void testKruskal() throws Exception {
		
		setUp2();
		ArrayList<Edge<Integer>> b = sp.kruskal();	
		assertTrue(b.isEmpty());
		
		setUp3();
		ArrayList<Edge<Integer>> a = sp.kruskal();		
		assertTrue(a.size()==6);
		assertTrue(a.get(0).getInitial().getId() == 1);
		assertTrue(a.get(0).getEnd().getId() == 3);
		assertTrue(a.get(0).getWeight() == 1);
	}
	
	@Test
	void testDegreeOf() throws Exception {		
		setUp2();
		assertEquals(sp.degreeOf(sp.getVertices().get(0)), 0);
		
		
		setUp3();
		assertEquals(sp.degreeOf(sp.getVertices().get(0)), 3);
	}
	
}
