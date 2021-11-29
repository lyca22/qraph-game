package model;

import java.util.ArrayList;

public class Player {

	private int posX;
	private int posY;
	private Box currentBox;
	private String nickname;
	private int coins;
	private int crowns;
	//private Path avatar;   PENDING TO ADD IN CONSTRUCTOR
	private ArrayList<Box> previousBoxes;
	private int costPreviousPath;
	private int speed;
	
	public Player(int posX, int posY, Box currentBox, int speed) {
		this.posX = posX;
		this.posY = posY;
		this.currentBox = currentBox;
		this.speed = speed;
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

	public Box getCurrentBox() {
		return currentBox;
	}

	public void setCurrentBox(Box currentBox) {
		this.currentBox = currentBox;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int getCrowns() {
		return crowns;
	}

	public void setCrowns(int crowns) {
		this.crowns = crowns;
	}

	public ArrayList<Box> getPreviousBoxes() {
		return previousBoxes;
	}

	public void setPreviousBoxes(ArrayList<Box> previousBoxes) {
		this.previousBoxes = previousBoxes;
	}

	public int getCostPreviousPath() {
		return costPreviousPath;
	}

	public void setCostPreviousPath(int costPreviousPath) {
		this.costPreviousPath = costPreviousPath;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}