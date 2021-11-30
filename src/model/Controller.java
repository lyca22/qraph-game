package model;

import java.util.ArrayList;

import datastr.Graph;

public class Controller {

	public static final int QUESTION_TIME = 15;		//Time per question in seconds
	
	private int numRounds;
	private Graph<Box> graph;
	private ArrayList<Board> boards;
	private Player currentPlayer;
	private ArrayList<Box> posibleMoves;
	private boolean isCorrectAnswer;
	private Player winner;
	private ArrayList<Button> buttons;
	
	public Controller(int numRounds, boolean useSimpleGraph) {
		this.numRounds = numRounds;
		boards = new ArrayList<Board>();
		posibleMoves = new ArrayList<Box>();
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
	
}
