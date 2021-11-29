package model;

import java.util.ArrayList;

import datastr.Graph;

public class Board {

	private Graph<Box> graph;
	private ArrayList<Player> players;
	//private Path image;		PENDING: ADD TO CONSTRUSTOR
	
	public Board(Graph<Box> graph, ArrayList<Player> players) {
		this.graph = graph;
		this.players = players;
	}

	public Graph<Box> getGraph() {
		return graph;
	}

	public void setGraph(Graph<Box> graph) {
		this.graph = graph;
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	
}
