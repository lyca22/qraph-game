package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import datastr.Edge;
import datastr.Graph;
import datastr.ListEdge;
import datastr.ListGraph;
import datastr.ListVertex;
import datastr.SimpleGraph;
import datastr.Vertex;

public class Controller {

	public static final int QUESTION_TIME = 15;		//Time per question in seconds
	public static final int RECHARGED_MONEY = 5;
	public static final int SPECIAL_BOX_WEIGHT = 5;
	public static final int GOTTEN_CROWNS = 1;
	public static final int STOLEN_COINS = 5;
	public static final int STOLEN_CROWNS = 1;

	private int numRounds;
	private int currentRound;
	private boolean isSimpleGraph;
	private Board currentBoard;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private ArrayList<Box> posibleMoves; //Used to display.
	private Player winner; //Used to display.
	private HashMap<Integer, ArrayList<Question>> questionsDB; //Create 4 lists, each one with each type of question given a category. Or do it with HashMap I dunno Julian.
	private ArrayList<String> categoriesNames;

	public Controller() {
		numRounds = 10;
		currentRound = 0;
		isSimpleGraph = true;
		setSimpleGraph(true);
		categoriesNames = new ArrayList<String>();
		setQuestionsDB(new HashMap<Integer, ArrayList<Question>>());
		posibleMoves = new ArrayList<Box>();
		setupDeafultCategories();
	}

	public void setupDeafultCategories() {
		questionsDB.put(1, new ArrayList<Question>());
		questionsDB.put(2, new ArrayList<Question>());
		questionsDB.put(3, new ArrayList<Question>());
		questionsDB.put(4, new ArrayList<Question>());
	}

	public void start(ArrayList<Player> players) {
		setupBoxes();
		setPlayers(players);
		shufflePlayersPositions();
		for(int i = 0; i < players.size(); i++) {
			players.get(i).setPosX(players.get(i).getCurrentBox().getPosX()-40);
			players.get(i).setPosY(players.get(i).getCurrentBox().getPosY()-30);
		}
	}

	private void setupBoxes() {
		
		for(int i = 0; i < currentBoard.getBoxes().size(); i++) {
			currentBoard.getBoxes().get(i).setType(BoxType.NORMAL);
		}
		
		int crownPos = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);

		int crocodilePos;
		do {
			crocodilePos = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);
		}while(crocodilePos == crownPos);

		int boost1Pos;
		do {
			boost1Pos = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);
		}while(boost1Pos == crownPos || boost1Pos == crocodilePos);

		int boost2Pos;
		do {
			boost2Pos = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);
		}while(boost2Pos == crownPos || boost2Pos == crocodilePos || boost2Pos == boost1Pos);

		int boost3Pos;
		do {
			boost3Pos = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);
		}while(boost3Pos == crownPos || boost3Pos == crocodilePos || boost3Pos == boost1Pos || boost3Pos == boost2Pos);

		Graph<Box> graph = currentBoard.getGraph();

		currentBoard.getBoxes().get(crownPos).setType(BoxType.CROWN);
		currentBoard.getBoxes().get(crocodilePos).setType(BoxType.CROCODILE);
		currentBoard.getBoxes().get(boost1Pos).setType(BoxType.BOOST);
		currentBoard.getBoxes().get(boost2Pos).setType(BoxType.BOOST);
		currentBoard.getBoxes().get(boost3Pos).setType(BoxType.BOOST);

		if(graph instanceof SimpleGraph) {
			ArrayList<Integer> crownList = ((SimpleGraph<Box>) graph).getEdges().get(crownPos);
			ArrayList<Integer> crocodileList = ((SimpleGraph<Box>) graph).getEdges().get(crocodilePos);
			ArrayList<Integer> b1List = ((SimpleGraph<Box>) graph).getEdges().get(boost1Pos);
			ArrayList<Integer> b2List = ((SimpleGraph<Box>) graph).getEdges().get(boost2Pos);
			ArrayList<Integer> b3List = ((SimpleGraph<Box>) graph).getEdges().get(boost3Pos);
			
			simpleSpecialEdge(crownList, crownPos, true);
			simpleSpecialEdge(crocodileList, crocodilePos, true);
			simpleSpecialEdge(b1List, boost1Pos, true);
			simpleSpecialEdge(b2List, boost1Pos, true);
			simpleSpecialEdge(b3List, boost1Pos, true);
		}else if(graph instanceof ListGraph){
			ArrayList<ListEdge<Box>> crownEdges = ((ListGraph<Box>) graph).getAdjList().get(crownPos).getEdges();
			ArrayList<ListEdge<Box>> crocodileEdges = ((ListGraph<Box>) graph).getAdjList().get(crocodilePos).getEdges();
			ArrayList<ListEdge<Box>> b1Edges = ((ListGraph<Box>) graph).getAdjList().get(boost1Pos).getEdges();
			ArrayList<ListEdge<Box>> b2Edges = ((ListGraph<Box>) graph).getAdjList().get(boost2Pos).getEdges();
			ArrayList<ListEdge<Box>> b3Edges = ((ListGraph<Box>) graph).getAdjList().get(boost3Pos).getEdges();

			listSpecialEdge(crownEdges, true);
			listSpecialEdge(crocodileEdges, true);
			listSpecialEdge(b1Edges, true);
			listSpecialEdge(b2Edges, true);
			listSpecialEdge(b3Edges, true);
		}
		for(int i = 0; i < currentBoard.getBoxes().size(); i++) {
			int number = randomNumberWithRange(1, 4);
			currentBoard.getBoxes().get(i).setCategory(number);
		}
	}

	private int randomNumberWithRange(int min, int max) {
		int range = (max - min) + 1;
		return (int)(Math.random() * range) + min;
	}

	private void simpleSpecialEdge(ArrayList<Integer> list, int pos, boolean isAdding) {
		int value = isAdding ? 1 : -1;
		for(int i = 0; i < currentBoard.getBoxes().size(); i++) {
			if(i != pos || list.get(i) != Integer.MAX_VALUE) {
				list.set(i, list.get(i) + (SPECIAL_BOX_WEIGHT * value));
			}
		}
	}

	private void listSpecialEdge(ArrayList<ListEdge<Box>> list, boolean isAdding) {
		int value = isAdding ? 1 : -1;
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setWeight(list.get(i).getWeight() + 5);
			ArrayList<ListEdge<Box>> endEdges = list.get(i).getEnd().getEdges();
			for(int j = 0; j < endEdges.size(); j++) {
				endEdges.get(j).setWeight(endEdges.get(j).getWeight() + (SPECIAL_BOX_WEIGHT * value));
			}
		}
	}

	private void shufflePlayersPositions() {
		for(int i = 0; i < players.size(); i++) {
			int number;
			do {
				number = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);
			}while(currentBoard.getBoxes().get(number).getType() != BoxType.NORMAL);
			players.get(i).setCurrentBox(currentBoard.getBoxes().get(number));
			currentBoard.getBoxes().get(number).getPlayers().add(players.get(i));
		}
	}

	public void movePlayer(Box finish) {
		
		currentPlayer.getCurrentBox().getPlayers().remove(currentPlayer);
		finish.getPlayers().add(currentPlayer);
		currentPlayer.setCurrentBox(finish);
	}

	public Question getQuestion() {
		int number;
		switch(currentPlayer.getCurrentBox().getCategory()) {
		case 1:
			number = randomNumberWithRange(0, questionsDB.get(1).size()-1);
			return questionsDB.get(1).get(number);
		case 2:
			number = randomNumberWithRange(0, questionsDB.get(2).size()-1);
			return questionsDB.get(2).get(number);
		case 3:
			number = randomNumberWithRange(0, questionsDB.get(3).size()-1);
			return questionsDB.get(3).get(number);
		case 4:
			number = randomNumberWithRange(0, questionsDB.get(4).size()-1);
			return questionsDB.get(4).get(number);
		}
		return null;
	}

	public void rechargeCoins() {
		for(int i = 0; i < players.size(); i++) {
			players.get(i).setCoins(players.get(i).getCoins() + RECHARGED_MONEY);
		}
	}

	public void triggerCrownEvent() {
		currentPlayer.setCrowns(currentPlayer.getCrowns() + GOTTEN_CROWNS);
		Graph<Box> graph = currentBoard.getGraph();
		int crownPos = currentBoard.getBoxes().indexOf(currentPlayer.getCurrentBox());
		
		if(graph instanceof SimpleGraph) {
			ArrayList<Integer> crownList = ((SimpleGraph<Box>) graph).getEdges().get(crownPos);
			simpleSpecialEdge(crownList, crownPos, false);
		}else if(graph instanceof ListGraph){
			ArrayList<ListEdge<Box>> crownEdges = ((ListGraph<Box>) graph).getAdjList().get(crownPos).getEdges();
			listSpecialEdge(crownEdges, false);
		}
		
		int number;
		BoxType type;
		
		do {
			number = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);
			type = currentBoard.getBoxes().get(number).getType();
		}while(type != BoxType.NORMAL);
		
		currentBoard.getBoxes().get(number).setType(BoxType.CROWN);
		crownPos = currentBoard.getBoxes().indexOf(currentBoard.getBoxes().get(number));
		
		if(graph instanceof SimpleGraph) {
			ArrayList<Integer> crownList = ((SimpleGraph<Box>) graph).getEdges().get(crownPos);
			simpleSpecialEdge(crownList, crownPos, true);
		}else if(graph instanceof ListGraph){
			ArrayList<ListEdge<Box>> crownEdges = ((ListGraph<Box>) graph).getAdjList().get(crownPos).getEdges();
			listSpecialEdge(crownEdges, true);
		}
		currentPlayer.getCurrentBox().setType(BoxType.NORMAL);
	}
	
	public void stealCoins(Player stolenPlayer) {
		if(stolenPlayer.getCoins() - STOLEN_COINS >= 0) {
			stolenPlayer.setCoins(stolenPlayer.getCoins() - STOLEN_COINS);
			currentPlayer.setCoins(currentPlayer.getCoins() + STOLEN_COINS);
		}
	}
	
	public void stealCrowns(Player stolenPlayer) {
		if(stolenPlayer.getCrowns() - STOLEN_CROWNS >= 0) {
			stolenPlayer.setCrowns(stolenPlayer.getCrowns() - STOLEN_CROWNS);
			currentPlayer.setCrowns(currentPlayer.getCrowns() + STOLEN_CROWNS);
		}
	}
	
	public void duplicateCoins() {
		currentPlayer.setCoins(currentPlayer.getCoins() * 2);
	}
	
	public void triggerCrocodileEvent() {
		createBoard1();
		setupBoxes();
		shufflePlayersPositions();
	}

	public void findWinner() {
		int winnerCoins = 0;
		int winnerCrowns = 0;
		for (Player player : players) {
			if(player.getCrowns() > winnerCrowns) {
				winnerCrowns = player.getCrowns();
				winnerCoins = player.getCoins();
				winner = player;
			}else if(player.getCrowns() == winnerCrowns && player.getCoins() > winnerCoins) {
				winnerCoins = player.getCoins();
				winner = player;
			}
		}
	}
	
	public ArrayList<Integer> getMovementCost(Box startBox){
		int index = currentBoard.getBoxes().indexOf(startBox);
		if(currentBoard.getGraph() instanceof SimpleGraph) {
			SimpleGraph<Box> sg = (SimpleGraph<Box>) currentBoard.getGraph();
			return sg.dijkstra(sg.getVertices().get(index));
		}else {
			ListGraph<Box> sg = (ListGraph<Box>) currentBoard.getGraph();
			return sg.dijkstra(sg.getAdjList().get(index));
		}
	}
	
	//Changed needed.
	public void calculatePossibleMoves(Box box, int distance) {
		ArrayList<Box> temp = new ArrayList<Box>();
		if(currentBoard.getGraph() instanceof SimpleGraph) {
			boolean found = false;
			Vertex<Box> vertex = null;
			for (int i = 0; i < ((SimpleGraph<Box>) currentBoard.getGraph()).getVertices().size() && !found; i++) {
				if(((SimpleGraph<Box>) currentBoard.getGraph()).getVertices().get(i).getValue().equals(box)) {
					vertex = ((SimpleGraph<Box>) currentBoard.getGraph()).getVertices().get(i);
					found = true;
				}
			}
			((SimpleGraph<Box>) currentBoard.getGraph()).breadthFirstSearch(vertex);
			
			for (Vertex<Box> tempVertex : ((SimpleGraph<Box>) currentBoard.getGraph()).getVertices()) {
				if(tempVertex.getDistance() == distance) {
					temp.add(tempVertex.getValue());
				}
			}
			
			
		}else if(currentBoard.getGraph() instanceof ListGraph) {
			boolean found = false;
			ListVertex<Box> vertex = null;
			for (int i = 0; i < ((ListGraph<Box>) currentBoard.getGraph()).getAdjList().size() && !found; i++) {
				if(((ListGraph<Box>) currentBoard.getGraph()).getAdjList().get(i).getValue().equals(box)) {
					vertex = ((ListGraph<Box>) currentBoard.getGraph()).getAdjList().get(i);
					found = true;
				}
			}
			((ListGraph<Box>) currentBoard.getGraph()).breadthFirstSearch(vertex);
			
			for (ListVertex<Box> tempVertex : ((ListGraph<Box>) currentBoard.getGraph()).getAdjList()) {
				if(tempVertex.getDistance() == distance) {
					temp.add(tempVertex.getValue());
				}
			}
		}
		
		posibleMoves = temp;
	}
	
	public void readDB(String fileName) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		br.readLine();	//Read header
		String line = br.readLine();
		while(line != null) {
			String[] fields = line.split(";");
			Integer category = Integer.valueOf(fields[0]);
			String statement = fields[1];
			String op1 = fields[2];
			String op2 = fields[3];
			String op3 = fields[4];
			String op4 = fields[5];
			int answer = Integer.valueOf(fields[6]);
			ArrayList<String> options = new ArrayList<String>();
			options.add(op1);
			options.add(op2);
			options.add(op3);
			options.add(op4);
			
			Question question = new Question(statement, options, answer);
			questionsDB.get(category).add(question);
		}
		br.close();
	}
	
	public void createBoard1() {
		Graph<Box> graph;
		if(isSimpleGraph) {
			graph = new SimpleGraph<Box>();
		}else {
			graph = new ListGraph<Box>();
		}
		
		ArrayList<Box> boxes = addAllBoxes(graph);
		ArrayList<Road> roads = new ArrayList<>();
		if(graph instanceof SimpleGraph) {
			roads = addAllEdges((SimpleGraph<Box>) graph, ((SimpleGraph<Box>) graph).getVertices());
		}else if(graph instanceof ListGraph){
			roads = addAllEdges((ListGraph<Box>)graph, ((ListGraph<Box>) graph).getAdjList());
		}
		currentBoard = new Board(graph);
		currentBoard.setBoxes(boxes);
		currentBoard.setRoads(roads);
	}

	public ArrayList<Box> addAllBoxes(Graph<Box> graph){
		Box box;
		ArrayList<Box> boxes = new ArrayList<Box>();

		//1
		box = new Box(216, 163);
		graph.addVertex(box);
		boxes.add(box);

		//2
		box = new Box(370, 105);
		graph.addVertex(box);
		boxes.add(box);

		//3
		box = new Box(479, 154);
		graph.addVertex(box);
		boxes.add(box);

		//4
		box = new Box(534, 252);
		graph.addVertex(box);
		boxes.add(box);
		
		//5
		box = new Box(626, 139);
		graph.addVertex(box);
		boxes.add(box);
		
		//6
		box = new Box(760, 217);
		graph.addVertex(box);
		boxes.add(box);
		
		//7
		box = new Box(785, 121);
		graph.addVertex(box);
		boxes.add(box);
		
		//8
		box = new Box(144, 303);
		graph.addVertex(box);
		boxes.add(box);
		
		//9
		box = new Box(301, 255);
		graph.addVertex(box);
		boxes.add(box);
		
		//10
		box = new Box(423, 304);
		graph.addVertex(box);
		boxes.add(box);
		
		//11
		box = new Box(626, 306);
		graph.addVertex(box);
		boxes.add(box);
		
		//12
		box = new Box(888, 312);
		graph.addVertex(box);
		boxes.add(box);

		//13
		box = new Box(198, 443);
		graph.addVertex(box);
		boxes.add(box);

		//14
		box = new Box(323, 421);
		graph.addVertex(box);
		boxes.add(box);

		//15
		box = new Box(463, 397);
		graph.addVertex(box);
		boxes.add(box);

		//16
		box = new Box(759, 343);
		graph.addVertex(box);
		boxes.add(box);

		//17
		box = new Box(220, 540);
		graph.addVertex(box);
		boxes.add(box);

		//18
		box = new Box(337, 591);
		graph.addVertex(box);
		boxes.add(box);

		//19
		box = new Box(539, 473);
		graph.addVertex(box);
		boxes.add(box);

		//20
		box = new Box(687, 431);
		graph.addVertex(box);
		boxes.add(box);

		//21
		box = new Box(833, 463);
		graph.addVertex(box);
		boxes.add(box);

		//22
		box = new Box(449, 532);
		graph.addVertex(box);
		boxes.add(box);

		//23
		box = new Box(597, 602);
		graph.addVertex(box);
		boxes.add(box);

		//24
		box = new Box(689, 543);
		graph.addVertex(box);
		boxes.add(box);

		//25
		box = new Box(786, 597);
		graph.addVertex(box);
		boxes.add(box);
		
		return boxes;
	}
	
	public ArrayList<Road> addAllEdges(SimpleGraph<Box> graph, ArrayList<Vertex<Box>> vertices){
		ArrayList<Road> roads = new ArrayList<>();
		Edge<Box> edge;
		Road road;
		
		//Edge 1 to 2
		graph.addEdge(vertices.get(1-1), vertices.get(2-1), 3);
		edge = new Edge<Box>(vertices.get(1-1), vertices.get(2-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);

		//Edge 1 to 8
		graph.addEdge(vertices.get(1-1), vertices.get(8-1), 4);
		edge = new Edge<Box>(vertices.get(1-1), vertices.get(8-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);

		//Edge 2 to 3
		graph.addEdge(vertices.get(2-1), vertices.get(3-1), 3);
		edge = new Edge<Box>(vertices.get(2-1), vertices.get(3-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);

		//Edge 2 to 9
		graph.addEdge(vertices.get(2-1), vertices.get(9-1), 7);
		edge = new Edge<Box>(vertices.get(2-1), vertices.get(9-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 3 to 4
		graph.addEdge(vertices.get(3-1), vertices.get(4-1), 1);
		edge = new Edge<Box>(vertices.get(3-1), vertices.get(4-1), 1);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 4 to 5
		graph.addEdge(vertices.get(4-1), vertices.get(5-1), 6);
		edge = new Edge<Box>(vertices.get(4-1), vertices.get(5-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 4 to 11
		graph.addEdge(vertices.get(4-1), vertices.get(11-1), 7);
		edge = new Edge<Box>(vertices.get(4-1), vertices.get(11-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 4 to 10
		graph.addEdge(vertices.get(4-1), vertices.get(10-1), 15);
		edge = new Edge<Box>(vertices.get(4-1), vertices.get(10-1), 15);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 5 to 6
		graph.addEdge(vertices.get(5-1), vertices.get(6-1), 4);
		edge = new Edge<Box>(vertices.get(5-1), vertices.get(6-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 5 to 7
		graph.addEdge(vertices.get(5-1), vertices.get(7-1), 9);
		edge = new Edge<Box>(vertices.get(5-1), vertices.get(7-1), 9);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 6 to 7
		graph.addEdge(vertices.get(6-1), vertices.get(7-1), 1);
		edge = new Edge<Box>(vertices.get(6-1), vertices.get(7-1), 1);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 6 to 12
		graph.addEdge(vertices.get(6-1), vertices.get(12-1), 7);
		edge = new Edge<Box>(vertices.get(6-1), vertices.get(12-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 7 to 12
		graph.addEdge(vertices.get(7-1), vertices.get(12-1), 4);
		edge = new Edge<Box>(vertices.get(7-1), vertices.get(12-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 8 to 13
		graph.addEdge(vertices.get(8-1), vertices.get(13-1), 5);
		edge = new Edge<Box>(vertices.get(8-1), vertices.get(13-1), 5);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 9 to 10
		graph.addEdge(vertices.get(9-1), vertices.get(10-1), 1);
		edge = new Edge<Box>(vertices.get(9-1), vertices.get(10-1), 1);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 9 to 14
		graph.addEdge(vertices.get(9-1), vertices.get(14-1), 3);
		edge = new Edge<Box>(vertices.get(9-1), vertices.get(14-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 10 to 15
		graph.addEdge(vertices.get(10-1), vertices.get(15-1), 3);
		edge = new Edge<Box>(vertices.get(10-1), vertices.get(15-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 11 to 16
		graph.addEdge(vertices.get(11-1), vertices.get(16-1), 3);
		edge = new Edge<Box>(vertices.get(11-1), vertices.get(16-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 12 to 21
		graph.addEdge(vertices.get(12-1), vertices.get(21-1), 10);	
		edge = new Edge<Box>(vertices.get(12-1), vertices.get(21-1), 10);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 13 to 14
		graph.addEdge(vertices.get(13-1), vertices.get(14-1), 4);
		edge = new Edge<Box>(vertices.get(13-1), vertices.get(14-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 13 to 17
		graph.addEdge(vertices.get(13-1), vertices.get(17-1), 6);	
		edge = new Edge<Box>(vertices.get(13-1), vertices.get(17-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 14 to 18
		graph.addEdge(vertices.get(14-1), vertices.get(18-1), 2);
		edge = new Edge<Box>(vertices.get(14-1), vertices.get(18-1), 2);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 15 to 19
		graph.addEdge(vertices.get(15-1), vertices.get(19-1), 8);
		edge = new Edge<Box>(vertices.get(15-1), vertices.get(19-1), 8);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 16 to 20
		graph.addEdge(vertices.get(16-1), vertices.get(20-1), 2);
		edge = new Edge<Box>(vertices.get(16-1), vertices.get(20-1), 2);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 17 to 18
		graph.addEdge(vertices.get(17-1), vertices.get(18-1), 7);
		edge = new Edge<Box>(vertices.get(17-1), vertices.get(18-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 18 to 22
		graph.addEdge(vertices.get(18-1), vertices.get(22-1), 12);	
		edge = new Edge<Box>(vertices.get(18-1), vertices.get(22-1), 12);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 19 to 20
		graph.addEdge(vertices.get(19-1), vertices.get(20-1), 7);	
		edge = new Edge<Box>(vertices.get(19-1), vertices.get(20-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 20 to 21
		graph.addEdge(vertices.get(20-1), vertices.get(21-1), 6);
		edge = new Edge<Box>(vertices.get(20-1), vertices.get(21-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 20 to 24
		graph.addEdge(vertices.get(20-1), vertices.get(24-1), 3);
		edge = new Edge<Box>(vertices.get(20-1), vertices.get(24-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 21 to 25
		graph.addEdge(vertices.get(21-1), vertices.get(25-1), 2);
		edge = new Edge<Box>(vertices.get(21-1), vertices.get(25-1), 2);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 22 to 23
		graph.addEdge(vertices.get(22-1), vertices.get(23-1), 10);
		edge = new Edge<Box>(vertices.get(22-1), vertices.get(23-1), 10);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 23 to 24
		graph.addEdge(vertices.get(23-1), vertices.get(24-1), 6);	
		edge = new Edge<Box>(vertices.get(23-1), vertices.get(24-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 24 to 25
		graph.addEdge(vertices.get(24-1), vertices.get(25-1), 8);
		edge = new Edge<Box>(vertices.get(24-1), vertices.get(25-1), 8);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		return roads;
	}
	
	public ArrayList<Road> addAllEdges(ListGraph<Box> graph, ArrayList<ListVertex<Box>> vertices){
		ArrayList<Road> roads = new ArrayList<>();
		ListEdge<Box> edge;
		Road road;
		
		//Edge 1 to 2
		graph.addEdge(vertices.get(1-1), vertices.get(2-1), 3);
		edge = new ListEdge<Box>(vertices.get(1-1), vertices.get(2-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);

		//Edge 1 to 8
		graph.addEdge(vertices.get(1-1), vertices.get(8-1), 4);
		edge = new ListEdge<Box>(vertices.get(1-1), vertices.get(8-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);

		//Edge 2 to 3
		graph.addEdge(vertices.get(2-1), vertices.get(3-1), 3);
		edge = new ListEdge<Box>(vertices.get(2-1), vertices.get(3-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);

		//Edge 2 to 9
		graph.addEdge(vertices.get(2-1), vertices.get(9-1), 7);
		edge = new ListEdge<Box>(vertices.get(2-1), vertices.get(9-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 3 to 4
		graph.addEdge(vertices.get(3-1), vertices.get(4-1), 1);
		edge = new ListEdge<Box>(vertices.get(3-1), vertices.get(4-1), 1);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 4 to 5
		graph.addEdge(vertices.get(4-1), vertices.get(5-1), 6);
		edge = new ListEdge<Box>(vertices.get(4-1), vertices.get(5-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 4 to 11
		graph.addEdge(vertices.get(4-1), vertices.get(11-1), 7);
		edge = new ListEdge<Box>(vertices.get(4-1), vertices.get(11-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 4 to 10
		graph.addEdge(vertices.get(4-1), vertices.get(10-1), 15);
		edge = new ListEdge<Box>(vertices.get(4-1), vertices.get(10-1), 15);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 5 to 6
		graph.addEdge(vertices.get(5-1), vertices.get(6-1), 4);
		edge = new ListEdge<Box>(vertices.get(5-1), vertices.get(6-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 5 to 7
		graph.addEdge(vertices.get(5-1), vertices.get(7-1), 9);
		edge = new ListEdge<Box>(vertices.get(5-1), vertices.get(7-1), 9);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 6 to 7
		graph.addEdge(vertices.get(6-1), vertices.get(7-1), 1);
		edge = new ListEdge<Box>(vertices.get(6-1), vertices.get(7-1), 1);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 6 to 12
		graph.addEdge(vertices.get(6-1), vertices.get(12-1), 7);
		edge = new ListEdge<Box>(vertices.get(6-1), vertices.get(12-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 7 to 12
		graph.addEdge(vertices.get(7-1), vertices.get(12-1), 4);
		edge = new ListEdge<Box>(vertices.get(7-1), vertices.get(12-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 8 to 13
		graph.addEdge(vertices.get(8-1), vertices.get(13-1), 5);
		edge = new ListEdge<Box>(vertices.get(8-1), vertices.get(13-1), 5);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 9 to 10
		graph.addEdge(vertices.get(9-1), vertices.get(10-1), 1);
		edge = new ListEdge<Box>(vertices.get(9-1), vertices.get(10-1), 1);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 9 to 14
		graph.addEdge(vertices.get(9-1), vertices.get(14-1), 3);
		edge = new ListEdge<Box>(vertices.get(9-1), vertices.get(14-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 10 to 15
		graph.addEdge(vertices.get(10-1), vertices.get(15-1), 3);
		edge = new ListEdge<Box>(vertices.get(10-1), vertices.get(15-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 11 to 16
		graph.addEdge(vertices.get(11-1), vertices.get(16-1), 3);
		edge = new ListEdge<Box>(vertices.get(11-1), vertices.get(16-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 12 to 21
		graph.addEdge(vertices.get(12-1), vertices.get(21-1), 10);	
		edge = new ListEdge<Box>(vertices.get(12-1), vertices.get(21-1), 10);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 13 to 14
		graph.addEdge(vertices.get(13-1), vertices.get(14-1), 4);
		edge = new ListEdge<Box>(vertices.get(13-1), vertices.get(14-1), 4);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 13 to 17
		graph.addEdge(vertices.get(13-1), vertices.get(17-1), 6);	
		edge = new ListEdge<Box>(vertices.get(13-1), vertices.get(17-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 14 to 18
		graph.addEdge(vertices.get(14-1), vertices.get(18-1), 2);
		edge = new ListEdge<Box>(vertices.get(14-1), vertices.get(18-1), 2);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 15 to 19
		graph.addEdge(vertices.get(15-1), vertices.get(19-1), 8);
		edge = new ListEdge<Box>(vertices.get(15-1), vertices.get(19-1), 8);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 16 to 20
		graph.addEdge(vertices.get(16-1), vertices.get(20-1), 2);
		edge = new ListEdge<Box>(vertices.get(16-1), vertices.get(20-1), 2);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 17 to 18
		graph.addEdge(vertices.get(17-1), vertices.get(18-1), 7);
		edge = new ListEdge<Box>(vertices.get(17-1), vertices.get(18-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 18 to 22
		graph.addEdge(vertices.get(18-1), vertices.get(22-1), 12);	
		edge = new ListEdge<Box>(vertices.get(18-1), vertices.get(22-1), 12);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 19 to 20
		graph.addEdge(vertices.get(19-1), vertices.get(20-1), 7);	
		edge = new ListEdge<Box>(vertices.get(19-1), vertices.get(20-1), 7);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 20 to 21
		graph.addEdge(vertices.get(20-1), vertices.get(21-1), 6);
		edge = new ListEdge<Box>(vertices.get(20-1), vertices.get(21-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 20 to 24
		graph.addEdge(vertices.get(20-1), vertices.get(24-1), 3);
		edge = new ListEdge<Box>(vertices.get(20-1), vertices.get(24-1), 3);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 21 to 25
		graph.addEdge(vertices.get(21-1), vertices.get(25-1), 2);
		edge = new ListEdge<Box>(vertices.get(21-1), vertices.get(25-1), 2);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 22 to 23
		graph.addEdge(vertices.get(22-1), vertices.get(23-1), 10);
		edge = new ListEdge<Box>(vertices.get(22-1), vertices.get(23-1), 10);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 23 to 24
		graph.addEdge(vertices.get(23-1), vertices.get(24-1), 6);	
		edge = new ListEdge<Box>(vertices.get(23-1), vertices.get(24-1), 6);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		//Edge 24 to 25
		graph.addEdge(vertices.get(24-1), vertices.get(25-1), 8);
		edge = new ListEdge<Box>(vertices.get(24-1), vertices.get(25-1), 8);
		road = new Road(0, 0, edge);
		roads.add(road);
		
		return roads;
	}
	
	public int getNumRounds() {
		return numRounds;
	}

	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public ArrayList<Box> getPosibleMoves() {
		return posibleMoves;
	}

	public void setPosibleMoves(ArrayList<Box> posibleMoves) {
		this.posibleMoves = posibleMoves;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public static int getQuestionTime() {
		return QUESTION_TIME;
	}

	public Board getCurrentBoard() {
		return currentBoard;
	}

	public void setCurrentBoard(Board currentBoard) {
		this.currentBoard = currentBoard;
	}

	public HashMap<Integer, ArrayList<Question>> getQuestionsDB() {
		return questionsDB;
	}

	public void setQuestionsDB(HashMap<Integer, ArrayList<Question>> questionsDB) {
		this.questionsDB = questionsDB;
	}

	public ArrayList<String> getCategoriesNames() {
		return categoriesNames;
	}

	public void setCategoriesNames(ArrayList<String> categoriesNames) {
		this.categoriesNames = categoriesNames;
	}

	public boolean isSimpleGraph() {
		return isSimpleGraph;
	}

	public void setSimpleGraph(boolean isSimpleGraph) {
		this.isSimpleGraph = isSimpleGraph;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public int getCurrentRound() {
		return currentRound;
	}

	public void setCurrentRound(int currentRound) {
		this.currentRound = currentRound;
	}
}
