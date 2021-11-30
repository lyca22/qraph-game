package model;

import java.util.ArrayList;

import datastr.Graph;

public class Board {

	private Graph<Box> graph;
	private ArrayList<Player> players;
	private ArrayList<Path> paths;
	//private Path image;		PENDING: ADD TO CONSTRUSTOR
	
	public Board(Graph<Box> graph, ArrayList<Player> players, ArrayList<Path> paths) {
		this.graph = graph;
		this.players = players;
		this.setPaths(paths);
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

	public ArrayList<Path> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<Path> paths) {
		this.paths = paths;
	}
	
}
