package model;

import java.util.ArrayList;
import java.util.HashMap;

import datastr.Graph;
import datastr.ListEdge;
import datastr.ListGraph;
import datastr.SimpleGraph;

public class Controller {

	public static final int QUESTION_TIME = 15;		//Time per question in seconds

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
			
			simpleSpecialEdge(crownList, crownPos);
			simpleSpecialEdge(crocodileList, crocodilePos);
			simpleSpecialEdge(b1List, boost1Pos);
			simpleSpecialEdge(b2List, boost1Pos);
			simpleSpecialEdge(b3List, boost1Pos);
		}else if(graph instanceof ListGraph){
			ArrayList<ListEdge<Box>> crownEdges = ((ListGraph<Box>) graph).getAdjList().get(crownPos).getEdges();
			ArrayList<ListEdge<Box>> crocodileEdges = ((ListGraph<Box>) graph).getAdjList().get(crocodilePos).getEdges();
			ArrayList<ListEdge<Box>> b1Edges = ((ListGraph<Box>) graph).getAdjList().get(boost1Pos).getEdges();
			ArrayList<ListEdge<Box>> b2Edges = ((ListGraph<Box>) graph).getAdjList().get(boost2Pos).getEdges();
			ArrayList<ListEdge<Box>> b3Edges = ((ListGraph<Box>) graph).getAdjList().get(boost3Pos).getEdges();
			
			listSpecialEdge(crownEdges);
			listSpecialEdge(crocodileEdges);
			listSpecialEdge(b1Edges);
			listSpecialEdge(b2Edges);
			listSpecialEdge(b3Edges);
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

	private void simpleSpecialEdge(ArrayList<Integer> list, int pos) {
		for(int i = 0; i < currentBoard.getBoxes().size(); i++) {
			if(i != pos || list.get(i) != Integer.MAX_VALUE) {
				list.set(i, list.get(i) + 5);
			}
		}
	}

	private void listSpecialEdge(ArrayList<ListEdge<Box>> list) {
		for(int i = 0; i < list.size(); i++) {
			list.get(i).setWeight(list.get(i).getWeight() + 5);
			ArrayList<ListEdge<Box>> endEdges = list.get(i).getEnd().getEdges();
			for(int j = 0; j < endEdges.size(); j++) {
				endEdges.get(j).setWeight(endEdges.get(j).getWeight() + 5);
			}
		}
	}

	private void shufflePlayersPositions() {
		for(int i = 0; i < players.size(); i++) {
			int number;
			do {
				number = randomNumberWithRange(0, currentBoard.getBoxes().size() - 1);
			}while(!currentBoard.getBoxes().get(number).getType().equals(BoxType.NORMAL));
			players.get(i).setCurrentBox(currentBoard.getBoxes().get(number));
			currentBoard.getBoxes().get(number).getPlayers().add(players.get(i));
		}
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
