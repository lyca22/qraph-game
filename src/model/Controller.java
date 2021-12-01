package model;

import java.util.ArrayList;
import java.util.HashMap;

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
	public static final int STOLEN_COINS = 5;
	public static final int STOLEN_CROWNS = 1;

	private int numRounds;
	private boolean isSimpleGraph;
	private Board currentBoard;
	private Player currentPlayer;
	private ArrayList<Player> players;
	private ArrayList<Box> posibleMoves; //Used to display.
	private Player winner; //Used to display.
	private HashMap<Integer, ArrayList<Question>> questionsDB; //Create 4 lists, each one with each type of question given a category. Or do it with HashMap I dunno Julian.
	private ArrayList<String> categoriesNames;

	public Controller() {
		setSimpleGraph(true);
		categoriesNames = new ArrayList<String>();
		setQuestionsDB(new HashMap<Integer, ArrayList<Question>>());
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
	}

	private void setupBoxes() {
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

	public void duplicateCoins() {
		currentPlayer.setCoins(currentPlayer.getCoins()*2);
	}

	public void stealCoins(Player player) {
		currentPlayer.setCoins(currentPlayer.getCoins() + STOLEN_COINS);
		player.setCoins(player.getCoins() - STOLEN_COINS);
	}

	public void stealCrowns(Player player) {
		currentPlayer.setCrowns(currentPlayer.getCrowns() + STOLEN_CROWNS);
		player.setCrowns(player.getCrowns() - STOLEN_CROWNS);
	}

	public void triggerCrownEvent() {
		currentPlayer.setCrowns(currentPlayer.getCrowns() + STOLEN_CROWNS);
		currentPlayer.getCurrentBox().setType(BoxType.NORMAL);
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
	}
	
	public void triggerBoostEventStoleCoins(Player stoledPlayer) {
		if(stoledPlayer.getCoins() - STOLEN_COINS >= 0) {
			stoledPlayer.setCoins(stoledPlayer.getCoins() - STOLEN_COINS);
			currentPlayer.setCoins(currentPlayer.getCoins() + STOLEN_COINS);
		}
	}
	
	public void triggerBoostEventStoleCrown(Player stoledPlayer) {
		if(stoledPlayer.getCrowns() - STOLEN_CROWNS >= 0) {
			stoledPlayer.setCrowns(stoledPlayer.getCrowns() - STOLEN_CROWNS);
			currentPlayer.setCoins(currentPlayer.getCoins() + STOLEN_COINS);
		}
	}
	
	public void triggerBoostEventDuplicateCoins() {
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void createBoard1() {
		Graph<Box> graph;
		if(isSimpleGraph) {
			graph = new SimpleGraph<Box>();
		}else {
			graph = new ListGraph<Box>();
		}
		//addAllBoxes(graph);
		if(graph instanceof SimpleGraph) {
			addAllEdges((SimpleGraph<Box>) graph, ((SimpleGraph<Box>) graph).getVertices());
		}else if(graph instanceof ListGraph){
			addAllEdges((ListGraph<Box>)graph, ((ListGraph<Box>) graph).getAdjList());
		}
		currentBoard = new Board(graph, new ArrayList<Box>(), new ArrayList<Road>());
	}
	
	/*
	public void addAllBoxes(Graph<Box> graph){
		Box box;

		//1
		box = new Box(posX, posY);
		graph.addVertex(box);

		//2
		box = new Box(posX, posY);
		graph.addVertex(box);

		//3
		box = new Box(posX, posY);
		graph.addVertex(box);

		//4
		box = new Box(posX, posY);
		graph.addVertex(box);

		//5
		box = new Box(posX, posY);
		graph.addVertex(box);

		//6
		box = new Box(posX, posY);
		graph.addVertex(box);

		//7
		box = new Box(posX, posY);
		graph.addVertex(box);

		//8
		box = new Box(posX, posY);
		graph.addVertex(box);

		//9
		box = new Box(posX, posY);
		graph.addVertex(box);

		//10
		box = new Box(posX, posY);
		graph.addVertex(box);

		//11
		box = new Box(posX, posY);
		graph.addVertex(box);

		//12
		box = new Box(posX, posY);
		graph.addVertex(box);

		//13
		box = new Box(posX, posY);
		graph.addVertex(box);

		//14
		box = new Box(posX, posY);
		graph.addVertex(box);

		//15
		box = new Box(posX, posY);
		graph.addVertex(box);

		//16
		box = new Box(posX, posY);
		graph.addVertex(box);

		//17
		box = new Box(posX, posY);
		graph.addVertex(box);

		//18
		box = new Box(posX, posY);
		graph.addVertex(box);

		//19
		box = new Box(posX, posY);
		graph.addVertex(box);

		//20
		box = new Box(posX, posY);
		graph.addVertex(box);

		//21
		box = new Box(posX, posY);
		graph.addVertex(box);

		//22
		box = new Box(posX, posY);
		graph.addVertex(box);

		//23
		box = new Box(posX, posY);
		graph.addVertex(box);

		//24
		box = new Box(posX, posY);
		graph.addVertex(box);

		//25
		box = new Box(posX, posY);
		graph.addVertex(box);
	}*/
	
	public void addAllEdges(SimpleGraph<Box> graph, ArrayList<Vertex<Box>> vertices){
		//Edge 1 to 2
		graph.addEdge(vertices.get(1), vertices.get(2), 3);

		//Edge 1 to 8
		graph.addEdge(vertices.get(1), vertices.get(8), 4);

		//Edge 2 to 3
		graph.addEdge(vertices.get(2), vertices.get(3), 3);

		//Edge 2 to 9
		graph.addEdge(vertices.get(2), vertices.get(9), 7);

		//Edge 3 to 4
		graph.addEdge(vertices.get(3), vertices.get(4), 1);

		//Edge 4 to 5
		graph.addEdge(vertices.get(4), vertices.get(5), 6);

		//Edge 4 to 11
		graph.addEdge(vertices.get(4), vertices.get(11), 7);

		//Edge 4 to 10
		graph.addEdge(vertices.get(4), vertices.get(10), 15);

		//Edge 5 to 6
		graph.addEdge(vertices.get(5), vertices.get(6), 4);

		//Edge 5 to 7
		graph.addEdge(vertices.get(5), vertices.get(7), 9);

		//Edge 6 to 7
		graph.addEdge(vertices.get(6), vertices.get(7), 1);

		//Edge 6 to 12
		graph.addEdge(vertices.get(6), vertices.get(12), 7);

		//Edge 7 to 12
		graph.addEdge(vertices.get(7), vertices.get(12), 4);

		//Edge 8 to 13
		graph.addEdge(vertices.get(8), vertices.get(13), 5);

		//Edge 9 to 10
		graph.addEdge(vertices.get(9), vertices.get(10), 1);

		//Edge 9 to 14
		graph.addEdge(vertices.get(9), vertices.get(14), 3);

		//Edge 10 to 15
		graph.addEdge(vertices.get(10), vertices.get(15), 3);

		//Edge 11 to 16
		graph.addEdge(vertices.get(11), vertices.get(16), 3);

		//Edge 12 to 21
		graph.addEdge(vertices.get(12), vertices.get(21), 10);	

		//Edge 13 to 14
		graph.addEdge(vertices.get(13), vertices.get(14), 4);

		//Edge 13 to 17
		graph.addEdge(vertices.get(13), vertices.get(17), 6);	

		//Edge 14 to 18
		graph.addEdge(vertices.get(14), vertices.get(18), 2);

		//Edge 15 to 19
		graph.addEdge(vertices.get(15), vertices.get(19), 8);

		//Edge 16 to 20
		graph.addEdge(vertices.get(16), vertices.get(20), 2);
		
		//Edge 17 to 18
		graph.addEdge(vertices.get(17), vertices.get(18), 7);

		//Edge 18 to 22
		graph.addEdge(vertices.get(18), vertices.get(22), 12);	

		//Edge 19 to 20
		graph.addEdge(vertices.get(19), vertices.get(20), 7);	

		//Edge 20 to 21
		graph.addEdge(vertices.get(20), vertices.get(21), 6);

		//Edge 20 to 24
		graph.addEdge(vertices.get(20), vertices.get(24), 3);

		//Edge 21 to 25
		graph.addEdge(vertices.get(21), vertices.get(25), 2);

		//Edge 22 to 23
		graph.addEdge(vertices.get(22), vertices.get(23), 10);

		//Edge 23 to 24
		graph.addEdge(vertices.get(23), vertices.get(24), 6);	

		//Edge 24 to 25
		graph.addEdge(vertices.get(24), vertices.get(25), 8);
		
	}
	
	public void addAllEdges(ListGraph<Box> graph, ArrayList<ListVertex<Box>> vertices){
		//Edge 1 to 2
		graph.addEdge(vertices.get(1), vertices.get(2), 3);

		//Edge 1 to 8
		graph.addEdge(vertices.get(1), vertices.get(8), 4);

		//Edge 2 to 3
		graph.addEdge(vertices.get(2), vertices.get(3), 3);

		//Edge 2 to 9
		graph.addEdge(vertices.get(2), vertices.get(9), 7);

		//Edge 3 to 4
		graph.addEdge(vertices.get(3), vertices.get(4), 1);

		//Edge 4 to 5
		graph.addEdge(vertices.get(4), vertices.get(5), 6);

		//Edge 4 to 11
		graph.addEdge(vertices.get(4), vertices.get(11), 7);

		//Edge 4 to 10
		graph.addEdge(vertices.get(4), vertices.get(10), 15);

		//Edge 5 to 6
		graph.addEdge(vertices.get(5), vertices.get(6), 4);

		//Edge 5 to 7
		graph.addEdge(vertices.get(5), vertices.get(7), 9);

		//Edge 6 to 7
		graph.addEdge(vertices.get(6), vertices.get(7), 1);

		//Edge 6 to 12
		graph.addEdge(vertices.get(6), vertices.get(12), 7);

		//Edge 7 to 12
		graph.addEdge(vertices.get(7), vertices.get(12), 4);

		//Edge 8 to 13
		graph.addEdge(vertices.get(8), vertices.get(13), 5);

		//Edge 9 to 10
		graph.addEdge(vertices.get(9), vertices.get(10), 1);

		//Edge 9 to 14
		graph.addEdge(vertices.get(9), vertices.get(14), 3);

		//Edge 10 to 15
		graph.addEdge(vertices.get(10), vertices.get(15), 3);

		//Edge 11 to 16
		graph.addEdge(vertices.get(11), vertices.get(16), 3);

		//Edge 12 to 21
		graph.addEdge(vertices.get(12), vertices.get(21), 10);	

		//Edge 13 to 14
		graph.addEdge(vertices.get(13), vertices.get(14), 4);

		//Edge 13 to 17
		graph.addEdge(vertices.get(13), vertices.get(17), 6);	

		//Edge 14 to 18
		graph.addEdge(vertices.get(14), vertices.get(18), 2);

		//Edge 15 to 19
		graph.addEdge(vertices.get(15), vertices.get(19), 8);

		//Edge 16 to 20
		graph.addEdge(vertices.get(16), vertices.get(20), 2);
		
		//Edge 17 to 18
		graph.addEdge(vertices.get(17), vertices.get(18), 7);

		//Edge 18 to 22
		graph.addEdge(vertices.get(18), vertices.get(22), 12);	

		//Edge 19 to 20
		graph.addEdge(vertices.get(19), vertices.get(20), 7);	

		//Edge 20 to 21
		graph.addEdge(vertices.get(20), vertices.get(21), 6);

		//Edge 20 to 24
		graph.addEdge(vertices.get(20), vertices.get(24), 3);

		//Edge 21 to 25
		graph.addEdge(vertices.get(21), vertices.get(25), 2);

		//Edge 22 to 23
		graph.addEdge(vertices.get(22), vertices.get(23), 10);

		//Edge 23 to 24
		graph.addEdge(vertices.get(23), vertices.get(24), 6);	

		//Edge 24 to 25
		graph.addEdge(vertices.get(24), vertices.get(25), 8);
		
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

}
