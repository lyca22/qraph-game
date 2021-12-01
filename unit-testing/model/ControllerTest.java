package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import datastr.ListGraph;
import datastr.ListVertex;
import datastr.SimpleGraph;
import datastr.Vertex;

class ControllerTest {

	public Controller c;
	
	ArrayList<Player> setup1() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player p = new Player(0, 0);
		players.add(p);
		c = new Controller();
		SimpleGraph<Box> sg = new SimpleGraph<Box>();
		Board b = new Board(sg);
		c.setCurrentBoard(b);
		for(int i = 0; i < 6; i++) {
			Box box = new Box(0, 0);
			sg.addVertex(box);
			b.getBoxes().add(box);
		}
		Vertex<Box> v1 = sg.getVertices().get(0);
		Vertex<Box> v2 = sg.getVertices().get(1);
		Vertex<Box> v3 = sg.getVertices().get(2);
		Vertex<Box> v4 = sg.getVertices().get(3);
		Vertex<Box> v5 = sg.getVertices().get(4);
		Vertex<Box> v6 = sg.getVertices().get(5);
		
		sg.addEdge(v1, v2, 3);
		sg.addEdge(v1, v4, 4);
		sg.addEdge(v2, v3, 1);
		sg.addEdge(v2, v4, 5);
		sg.addEdge(v3, v5, 2);
		sg.addEdge(v3, v6, 2);
		sg.addEdge(v4, v5, 5);
		sg.addEdge(v4, v6, 2);
		sg.addEdge(v5, v6, 4);
		
		c.setCurrentPlayer(p);
		Question q1 = new Question("Q1", new ArrayList<String>(), 0);
		Question q2 = new Question("Q2", new ArrayList<String>(), 1);
		Question q3 = new Question("Q3", new ArrayList<String>(), 2);
		Question q4 = new Question("Q4", new ArrayList<String>(), 3);
		c.getQuestionsDB().get(1).add(q1);
		c.getQuestionsDB().get(2).add(q2);
		c.getQuestionsDB().get(3).add(q3);
		c.getQuestionsDB().get(4).add(q4);
		return players;
	}
	
	ArrayList<Player> setup2() {
		ArrayList<Player> players = new ArrayList<Player>();
		Player p = new Player(0, 0);
		players.add(p);
		Controller c = new Controller();
		ListGraph<Box> lg = new ListGraph<Box>();
		Board b = new Board(lg);
		c.setCurrentBoard(b);
		for(int i = 0; i < 6; i++) {
			Box box = new Box(0, 0);
			lg.addVertex(box);
			b.getBoxes().add(box);
		}
		ListVertex<Box> v1 = lg.getAdjList().get(0);
		ListVertex<Box> v2 = lg.getAdjList().get(1);
		ListVertex<Box> v3 = lg.getAdjList().get(2);
		ListVertex<Box> v4 = lg.getAdjList().get(3);
		ListVertex<Box> v5 = lg.getAdjList().get(4);
		ListVertex<Box> v6 = lg.getAdjList().get(5);
		
		lg.addEdge(v1, v2, 3);
		lg.addEdge(v1, v4, 4);
		lg.addEdge(v2, v3, 1);
		lg.addEdge(v2, v4, 5);
		lg.addEdge(v3, v5, 2);
		lg.addEdge(v3, v6, 2);
		lg.addEdge(v4, v5, 5);
		lg.addEdge(v4, v6, 2);
		lg.addEdge(v5, v6, 4);
		
		c.setCurrentPlayer(p);
		Question q1 = new Question("Q1", new ArrayList<String>(), 0);
		Question q2 = new Question("Q2", new ArrayList<String>(), 1);
		Question q3 = new Question("Q3", new ArrayList<String>(), 2);
		Question q4 = new Question("Q4", new ArrayList<String>(), 3);
		c.getQuestionsDB().get(1).add(q1);
		c.getQuestionsDB().get(2).add(q2);
		c.getQuestionsDB().get(3).add(q3);
		c.getQuestionsDB().get(4).add(q4);
		return players;
	}
	
	@Test
	void testStart() {
		
		ArrayList<Player> players;
		int crownCount;
		int crocodileCount;
		int boostCount;
		int normalCount;
		int playerCount;
		
		players = setup1();
		c.start(players);
		crownCount = 0;
		crocodileCount = 0;
		boostCount = 0;
		normalCount = 0;
		playerCount = 0;
		for(int i = 0; i < c.getCurrentBoard().getBoxes().size(); i++) {
			if(c.getCurrentBoard().getBoxes().get(i).getType() == BoxType.CROWN) {
				crownCount++;
			}else if(c.getCurrentBoard().getBoxes().get(i).getType() == BoxType.CROCODILE) {
				crocodileCount++;
			}else if(c.getCurrentBoard().getBoxes().get(i).getType() == BoxType.BOOST) {
				boostCount++;
			}else {
				normalCount++;
				playerCount = playerCount + c.getCurrentBoard().getBoxes().get(i).getPlayers().size();
			}
		}
		assertTrue(crownCount == 1);
		assertTrue(crocodileCount == 1);
		assertTrue(boostCount == 3);
		assertTrue(normalCount == 1);
		assertTrue(playerCount == 1);
		
		players = setup2();
		c.start(players);
		crownCount = 0;
		crocodileCount = 0;
		boostCount = 0;
		normalCount = 0;
		playerCount = 0;
		for(int i = 0; i < c.getCurrentBoard().getBoxes().size(); i++) {
			if(c.getCurrentBoard().getBoxes().get(i).getType() == BoxType.CROWN) {
				crownCount++;
			}else if(c.getCurrentBoard().getBoxes().get(i).getType() == BoxType.CROCODILE) {
				crocodileCount++;
			}else if(c.getCurrentBoard().getBoxes().get(i).getType() == BoxType.BOOST) {
				boostCount++;
			}else {
				normalCount++;
				playerCount = playerCount + c.getCurrentBoard().getBoxes().get(i).getPlayers().size();
			}
		}
		assertTrue(crownCount == 1);
		assertTrue(crocodileCount == 1);
		assertTrue(boostCount == 3);
		assertTrue(normalCount == 1);
		assertTrue(playerCount == 1);
	}
	
	@Test
	void testMovePlayer() {
		
		ArrayList<Player> p;
		boolean found;
		Box b;
		Box cb;
		
		p = setup1();
		c.start(p);
		found = false;
		b = null;
		for(int i = 0; i < c.getCurrentBoard().getBoxes().size() && !found; i++) {
			if(c.getCurrentBoard().getBoxes().get(i).getType() != BoxType.NORMAL) {
				found = true;
				b = c.getCurrentBoard().getBoxes().get(i);
			}
		}
		
		cb = c.getCurrentPlayer().getCurrentBox();
		c.movePlayer(b);
		
		assertTrue(cb.getPlayers().isEmpty());
		assertTrue(c.getCurrentPlayer().getCurrentBox() == b);
		assertTrue(c.getCurrentPlayer().getCurrentBox().getPlayers().contains(c.getCurrentPlayer()));
		
		p = setup2();
		c.start(p);
		found = false;
		b = null;
		for(int i = 0; i < c.getCurrentBoard().getBoxes().size() && !found; i++) {
			if(c.getCurrentBoard().getBoxes().get(i).getType() != BoxType.NORMAL) {
				found = true;
				b = c.getCurrentBoard().getBoxes().get(i);
			}
		}
		
		cb = c.getCurrentPlayer().getCurrentBox();
		c.movePlayer(b);
		
		assertTrue(cb.getPlayers().isEmpty());
		assertTrue(c.getCurrentPlayer().getCurrentBox() == b);
		assertTrue(c.getCurrentPlayer().getCurrentBox().getPlayers().contains(c.getCurrentPlayer()));
		
	}
	
	@Test
	void testGetQuestion() {
		ArrayList<Player> p;
		p = setup1();
		c.start(p);
		Question q = c.getQuestion();
		if(c.getCurrentPlayer().getCurrentBox().getCategory() == 1) {
			assertTrue(c.getQuestionsDB().get(1).contains(q));
		}else if(c.getCurrentPlayer().getCurrentBox().getCategory() == 2) {
			assertTrue(c.getQuestionsDB().get(2).contains(q));
		}else if(c.getCurrentPlayer().getCurrentBox().getCategory() == 3) {
			assertTrue(c.getQuestionsDB().get(3).contains(q));
		}else if(c.getCurrentPlayer().getCurrentBox().getCategory() == 4) {
			assertTrue(c.getQuestionsDB().get(4).contains(q));
		}
	}
	
	@Test
	void testRechargeCoins() {
		fail("Not yet implemented");
	}
	
	@Test
	void testDuplicateCoins() {
		fail("Not yet implemented");
	}
	
	@Test
	void testStealCoins() {
		fail("Not yet implemented");
	}
	
	@Test
	void testStealCrowns() {
		fail("Not yet implemented");
	}

	@Test
	void testTriggerCrownEvent() {
		fail("Not yet implemented");
	}
	
	@Test
	void testTriggerCrocodileEvent() {
		fail("Not yet implemented");
	}
	
	@Test
	void testFindWinner() {
		fail("Not yet implemented");
	}

}
