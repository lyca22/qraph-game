package model;

import java.util.ArrayList;

public class Box {

	private int posX;
	private int posY;
	private ArrayList<Player> players;
	private String category;			//CHECK
	//private Path imageNormal;
	//private Path imageBFS;
	
	public Box(int posX, int posY, String category) {
		this.posX = posX;
		this.posY = posY;
		this.category = category;
		players = new ArrayList<Player>();
		//Assign image according to category
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

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
