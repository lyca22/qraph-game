package ui;

import java.util.ArrayList;

import model.Controller;
import model.Player;
import processing.core.PApplet;
import processing.core.PImage;

public class ScreenManager {
	private ScreenIdentifier screenId;
	
	private PApplet app;
	private Controller controler;
	
	private ArrayList<Button> uiButtons;
	private ArrayList<PImage> mainScreenImages;
	private ArrayList<Player> playersUi;
	
	
	public ScreenManager(PApplet app) {
		
		this.app = app;
		mainScreenImages = new ArrayList<>();
		uiButtons = new ArrayList<>();
		playersUi = new ArrayList<>();
		screenId = ScreenIdentifier.MAIN_SCREEN;
		
		controler = new Controller();
		
		//Initialization
		loadMainScreen();
	}

	private void loadMainScreen() {
		uiButtons.add(new Button(50,359,70,250, false, ButtonIdentifier.PLAY));
		uiButtons.add(new Button(50,464,50,200, false, ButtonIdentifier.ABOUT));
		uiButtons.add(new Button(50,545,50,200, false, ButtonIdentifier.EXIT));
		//(int posX, int posY, int height, int width, boolean state, ButtonIdentifier identifier)
	}
	
	public void loadConfigurationScreen() {
		//ROUNDS
		uiButtons.add(new Button(206,166,23,45, false, ButtonIdentifier.DECREASE_ROUNDS_BY_10));
		uiButtons.add(new Button(266,166,23,23, false, ButtonIdentifier.DECREASE_ROUNDS));
		uiButtons.add(new Button(413,166,23,23, false, ButtonIdentifier.INCREASE_ROUNDS));
		uiButtons.add(new Button(449,166,23,45, false, ButtonIdentifier.INCREASE_ROUNDS_BY_10));
		//PLAYERS
		//Add
		uiButtons.add(new Button(144,319,50,50, false, ButtonIdentifier.ADD_PLAYER));
		uiButtons.add(new Button(263,319,50,50, false, ButtonIdentifier.ADD_PLAYER2));
		uiButtons.add(new Button(381,319,50,50, false, ButtonIdentifier.ADD_PLAYER3));
		uiButtons.add(new Button(500,319,50,50, false, ButtonIdentifier.ADD_PLAYER4));
		//remove
		uiButtons.add(new Button(202,268,30,30, false, ButtonIdentifier.DELETE_PLAYER));
		uiButtons.add(new Button(321,268,30,30, false, ButtonIdentifier.DELETE_PLAYER2));
		uiButtons.add(new Button(439,268,30,30, false, ButtonIdentifier.DELETE_PLAYER3));
		uiButtons.add(new Button(559,268,30,30, false, ButtonIdentifier.DELETE_PLAYER4));
		//GRAPH
		uiButtons.add(new Button(303,598,30,100, false, ButtonIdentifier.CHANGE_GRAPH));
		//START
		uiButtons.add(new Button(826,589,40,140, false, ButtonIdentifier.START));
	}
	
	
	//RENDERS
	public void renderMainScreen() {
		app.fill(20);
		app.rect(uiButtons.get(0).getPosX(), uiButtons.get(0).getPosY(), uiButtons.get(0).getWidth(), uiButtons.get(0).getHeight());	
		app.rect(uiButtons.get(1).getPosX(), uiButtons.get(1).getPosY(), uiButtons.get(1).getWidth(), uiButtons.get(1).getHeight());		
		app.rect(uiButtons.get(2).getPosX(), uiButtons.get(2).getPosY(), uiButtons.get(2).getWidth(), uiButtons.get(2).getHeight());		
	}
	
	public void renderConfiguratoinScreen() {
		app.fill(20);
		app.rect(74, 50, 555, 622);
		app.fill(40);
		app.rect(629, 50, 378, 622);
		//TEXTS
		//rounds
		app.textSize(22);
		app.fill(230);
		app.text("Rounds", 315, 140);
		app.textSize(18);
		app.text(controler.getNumRounds(), 345, 182);
		//select players
		app.textSize(22);
		app.fill(230);
		app.text("Select players", 288, 244);
		//select map
		app.textSize(22);
		app.fill(230);
		app.text("Select map", 300, 455);
		//select questions
		app.textSize(22);
		app.fill(230);
		app.text("Select question categories", 690, 140);
		
		app.text(controler.isSimpleGraph()+"", 330, 510);
		//BUTTONS
		//rpunds
		app.rect(uiButtons.get(3).getPosX(), uiButtons.get(3).getPosY(), uiButtons.get(3).getWidth(), uiButtons.get(3).getHeight());	
		app.rect(uiButtons.get(4).getPosX(), uiButtons.get(4).getPosY(), uiButtons.get(4).getWidth(), uiButtons.get(4).getHeight());		
		app.rect(uiButtons.get(5).getPosX(), uiButtons.get(5).getPosY(), uiButtons.get(5).getWidth(), uiButtons.get(5).getHeight());
		app.rect(uiButtons.get(6).getPosX(), uiButtons.get(6).getPosY(), uiButtons.get(6).getWidth(), uiButtons.get(6).getHeight());	
		//PLAYER
		//add
		app.rect(uiButtons.get(7).getPosX(), uiButtons.get(7).getPosY(), uiButtons.get(7).getWidth(), uiButtons.get(7).getHeight());	
		app.rect(uiButtons.get(8).getPosX(), uiButtons.get(8).getPosY(), uiButtons.get(8).getWidth(), uiButtons.get(8).getHeight());		
		app.rect(uiButtons.get(9).getPosX(), uiButtons.get(9).getPosY(), uiButtons.get(9).getWidth(), uiButtons.get(9).getHeight());
		app.rect(uiButtons.get(10).getPosX(), uiButtons.get(10).getPosY(), uiButtons.get(10).getWidth(), uiButtons.get(10).getHeight());	
		//GRAPH
		app.rect(uiButtons.get(15).getPosX(), uiButtons.get(15).getPosY(), uiButtons.get(15).getWidth(), uiButtons.get(15).getHeight());
		//START
		app.rect(uiButtons.get(16).getPosX(), uiButtons.get(16).getPosY(), uiButtons.get(16).getWidth(), uiButtons.get(16).getHeight());
		
		//Remove players
		for (int i = 7; i < 11; i++) {
		
			if(!uiButtons.get(i).isActive()) {
				app.fill(200);
				app.rect(116+((i-7)*120), 289, 104, 106);
				app.fill(200,80,80);
				app.circle(uiButtons.get(i+4).getPosX()+20, uiButtons.get(i+4).getPosY()+20, uiButtons.get(i+4).getWidth());	
			}
		}
	}
	
	//GETTERS AND SETTERS
	
	//To change and detect current screen
	public ScreenIdentifier getScreenId() {
		return screenId;
	}

	public void setScreenId(ScreenIdentifier screenId) {
		this.screenId = screenId;
	}

	//
	public ArrayList<Button> getUiButtons() {
		return uiButtons;
	}

	public ArrayList<PImage> getMainScreenImages() {
		return mainScreenImages;
	}
	public ScreenManager() {
		// TODO Auto-generated constructor stub
	}

	public Controller getControler() {
		return controler;
	}

	public ArrayList<Player> getPlayersUi() {
		return playersUi;
	}

	public void setPlayersUi(ArrayList<Player> playersUi) {
		this.playersUi = playersUi;
	}
	
	
}
