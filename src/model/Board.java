package model;

import java.nio.file.Path;
import java.util.ArrayList;

import datastr.Graph;

public class Board {

	private Graph<Box> graph;
	private ArrayList<Box> boxes;
	private ArrayList<Road> roads;
	private Path image;
	
	public Board(Graph<Box> graph, ArrayList<Box> boxes, ArrayList<Road> roads) {
		this.graph = graph;
		this.setBoxes(boxes);
		this.setRoads(roads);
	}

	public Graph<Box> getGraph() {
		return graph;
	}

	public void setGraph(Graph<Box> graph) {
		this.graph = graph;
	}

	public ArrayList<Road> getRoads() {
		return roads;
	}

	public void setRoads(ArrayList<Road> roads) {
		this.roads = roads;
	}

	public ArrayList<Box> getBoxes() {
		return boxes;
	}

	public void setBoxes(ArrayList<Box> boxes) {
		this.boxes = boxes;
	}

	public Path getImage() {
		return image;
	}

	public void setImage(Path image) {
		this.image = image;
	}
	
}
