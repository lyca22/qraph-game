package model;

import datastr.Edge;
import datastr.ListEdge;

public class Path {
	
	private int posX;
	private int posY;
	private Edge<Box> simpleEdge;
	private ListEdge<Box> listEdge;
	private double angle;
	//private Path image;
	
	public Path(int posX, int posY, Edge<Box> simpleEdge) {
		this.posX = posX;
		this.posY = posY;
		this.simpleEdge = simpleEdge;
		calculateAngle();
	}
	
	public Path(int posX, int posY, ListEdge<Box> listEdge) {
		this.posX = posX;
		this.posY = posY;
		this.listEdge = listEdge;
		calculateAngle();
	}
	
	private void calculateAngle() {
		//TODO: Implement a method for calculate angle
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public Edge<Box> getSimpleEdge() {
		return simpleEdge;
	}

	public void setSimpleEdge(Edge<Box> simpleEdge) {
		this.simpleEdge = simpleEdge;
	}

	public ListEdge<Box> getListEdge() {
		return listEdge;
	}

	public void setListEdge(ListEdge<Box> listEdge) {
		this.listEdge = listEdge;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
