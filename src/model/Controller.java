package model;

import java.util.ArrayList;
import java.util.HashMap;

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
	
	public void start() {
		setupBoxes();
		setupPlayers();
		shufflePlayersPositions();
	}

	private void setupBoxes() {
	}

	private void setupPlayers() {
	}

	private void shufflePlayersPositions() {
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
