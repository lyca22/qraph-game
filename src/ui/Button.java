package model;

public class Button {
	
	//MOVE TO UI.
	
	private int posX;
	private int posY;
	private int height;
	private int width;
	private boolean state;
	private ButtonIdentifier identifier;
	//private Path image;		PENDING TO ADD IN CONSTRUCTOR
	
	public Button(int posX, int posY, int height, int width, boolean state, ButtonIdentifier identifier) {
		this.posX = posX;
		this.posY = posY;
		this.height = height;
		this.width = width;
		this.state = state;
		this.identifier = identifier;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public ButtonIdentifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(ButtonIdentifier identifier) {
		this.identifier = identifier;
	}
	
}
