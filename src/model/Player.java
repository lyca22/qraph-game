package model;

import java.util.ArrayList;
import java.nio.file.Path;

public class Player {

	public static final int DEFAULT_SPEED = 1;
	
	private int posX;
	private int posY;
	private Box currentBox;
	private String nickname;
	private int coins;
	private int crowns;
	private Path avatar;
	private ArrayList<Box> previousBoxes;
	private int costPreviousPath;
	private int speed;
	
	public Player(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.speed = DEFAULT_SPEED;
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

	public Path getAvatar() {
		return avatar;
	}

	public void setAvatar(Path avatar) {
		this.avatar = avatar;
	}
	
}
