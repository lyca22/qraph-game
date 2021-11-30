package model;

import java.nio.file.Path;
import java.util.ArrayList;

public class Box {

	private int posX;
	private int posY;
	private ArrayList<Player> players;
	private String category;
	private Path imageNormal;
	private Path imageBFS;
	private BoxType type;
	
	public Box(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
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

	public Path getImageNormal() {
		return imageNormal;
	}

	public void setImageNormal(Path imageNormal) {
		this.imageNormal = imageNormal;
	}

	public Path getImageBFS() {
		return imageBFS;
	}

	public void setImageBFS(Path imageBFS) {
		this.imageBFS = imageBFS;
	}
	
}
