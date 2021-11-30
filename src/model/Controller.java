package model;

import java.util.ArrayList;
import java.util.HashMap;

import datastr.Graph;
import datastr.SimpleGraph;

public class Controller {

	public static final int QUESTION_TIME = 15;		//Time per question in seconds
	
	private int numRounds;
	private Graph<Box> graph;
	private ArrayList<Board> boards;
	private Board currentBoard;
	private Player currentPlayer;
	private ArrayList<Box> posibleMoves;
	private boolean isCorrectAnswer;
	private Player winner;
	private ArrayList<Button> buttons;
	private HashMap<String, Question> questionsDB;
	
	
	public Controller() {
		boards = new ArrayList<Board>();
		graph = new SimpleGraph<Box>();
		setQuestionsDB(new HashMap<String, Question>());
		addBoards();
		addDeafultQuestions();
	}

	private void addBoards() {
		addBoard1();
		addBoard2();
	}

	private void addBoard1() {
		//TODO: Create the graph for board 1
		if(graph instanceof SimpleGraph) {
			
		}else {
			
		}
	}
	
	private void addBoard2() {
		//TODO: Create the graph for board 2
		if(graph instanceof SimpleGraph) {
			
		}else {
			
		}
	}
	
	private void addDeafultQuestions() {
		// TODO Auto-generated method stub
		
	}
	
	public void start() {
		
	}

	public int getNumRounds() {
		return numRounds;
	}

	public void setNumRounds(int numRounds) {
		this.numRounds = numRounds;
	}

	public Graph<Box> getGraph() {
		return graph;
	}

	public void setGraph(Graph<Box> graph) {
		this.graph = graph;
		addBoards();
	}

	public ArrayList<Board> getBoards() {
		return boards;
	}

	public void setBoards(ArrayList<Board> boards) {
		this.boards = boards;
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

	public boolean isCorrectAnswer() {
		return isCorrectAnswer;
	}

	public void setCorrectAnswer(boolean isCorrectAnswer) {
		this.isCorrectAnswer = isCorrectAnswer;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public ArrayList<Button> getButtons() {
		return buttons;
	}

	public void setButtons(ArrayList<Button> buttons) {
		this.buttons = buttons;
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

	public HashMap<String, Question> getQuestionsDB() {
		return questionsDB;
	}

	public void setQuestionsDB(HashMap<String, Question> questionsDB) {
		this.questionsDB = questionsDB;
	}
	
}
