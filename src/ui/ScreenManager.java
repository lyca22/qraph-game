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
	private ArrayList<PImage> screenImages;
	private ArrayList<Player> playersUi;
	
	private PImage mainBg;
	private PImage textMain;
	private PImage playBtn;
	private PImage aboutBtn;
	private PImage exitBtn;
	private PImage configBg;
	private PImage popUpC;
	private PImage p1img;
	private PImage p2img;
	private PImage p3img;
	private PImage p4img;
	private PImage remove;
	private PImage mas10;
	private PImage menos10;
	private PImage plusP;
	private PImage plusSmall;
	private PImage lessSmall;
	
	public ScreenManager(PApplet app) {
		
		this.app = app;
		screenImages = new ArrayList<>();
		uiButtons = new ArrayList<>();
		playersUi = new ArrayList<>();
		screenId = ScreenIdentifier.MAIN_SCREEN;
		
		controler = new Controller();
		
		//Initialization
		loadMainScreen();
		loadImages();
	}

	private void loadImages() {
		mainBg = app.loadImage("data/imgs/MainMenuBgBlue.png");
		textMain = app.loadImage("data/imgs/TituloMSBGv2-03.png");
		playBtn = app.loadImage("data/imgs/playBtn.png");
		aboutBtn = app.loadImage("data/imgs/aboutBtn.png");
		exitBtn = app.loadImage("data/imgs/quitGameBtn.png");
		configBg = app.loadImage("data/imgs/ConfigBG.png");
		popUpC = app.loadImage("data/imgs/popUpConfig-03.png");
		p1img = app.loadImage("data/imgs/P1-04.png");
		p2img = app.loadImage("data/imgs/P2-04.png");
		p3img = app.loadImage("data/imgs/P3-04.png");
		p4img = app.loadImage("data/imgs/P4-04.png");
		remove = app.loadImage("data/imgs/removeImg-05.png");
		mas10 = app.loadImage("data/imgs/mas10-06.png");
		menos10 = app.loadImage("data/imgs/menos10-06.png");
		plusP = app.loadImage("data/imgs/plusPlayer-07.png");
		plusSmall = app.loadImage("data/imgs/plusSmall-08.png");
		lessSmall = app.loadImage("data/imgs/lessSmall-08.png")
;	}

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
		app.image(mainBg, 0, 0, 1080, 720);
		app.image(textMain, 50, 50 );
		app.image(playBtn, uiButtons.get(0).getPosX(), uiButtons.get(0).getPosY());
		app.image(aboutBtn, uiButtons.get(1).getPosX(), uiButtons.get(1).getPosY());
		app.image(exitBtn, uiButtons.get(2).getPosX(), uiButtons.get(2).getPosY());
		app.fill(20);
		//app.rect(uiButtons.get(0).getPosX(), uiButtons.get(0).getPosY(), uiButtons.get(0).getWidth(), uiButtons.get(0).getHeight());	
		//app.rect(uiButtons.get(1).getPosX(), uiButtons.get(1).getPosY(), uiButtons.get(1).getWidth(), uiButtons.get(1).getHeight());		
		//app.rect(uiButtons.get(2).getPosX(), uiButtons.get(2).getPosY(), uiButtons.get(2).getWidth(), uiButtons.get(2).getHeight());		
	}
	
	public void renderConfiguratoinScreen() {
		app.image(configBg, 0, 00, 1080, 720);
		app.image(popUpC, 74, 50);
		app.fill(20);
		//app.rect(74, 50, 555, 622);
		app.fill(40);
		//app.rect(629, 50, 378, 622);
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
		app.image(menos10, uiButtons.get(3).getPosX(), uiButtons.get(3).getPosY());
		//app.rect(uiButtons.get(3).getPosX(), uiButtons.get(3).getPosY(), uiButtons.get(3).getWidth(), uiButtons.get(3).getHeight());	
		app.image(lessSmall, uiButtons.get(4).getPosX(), uiButtons.get(4).getPosY());
		app.image(plusSmall, uiButtons.get(5).getPosX(), uiButtons.get(5).getPosY());
		//app.rect(uiButtons.get(4).getPosX(), uiButtons.get(4).getPosY(), uiButtons.get(4).getWidth(), uiButtons.get(4).getHeight());		
		//app.rect(uiButtons.get(5).getPosX(), uiButtons.get(5).getPosY(), uiButtons.get(5).getWidth(), uiButtons.get(5).getHeight());
		app.image(mas10, uiButtons.get(6).getPosX(), uiButtons.get(6).getPosY());
		//app.rect(uiButtons.get(6).getPosX(), uiButtons.get(6).getPosY(), uiButtons.get(6).getWidth(), uiButtons.get(6).getHeight());	
		//PLAYER
		//add
		app.image(plusP, uiButtons.get(7).getPosX(), uiButtons.get(7).getPosY());
		app.image(plusP, uiButtons.get(8).getPosX(), uiButtons.get(8).getPosY());
		app.image(plusP, uiButtons.get(9).getPosX(), uiButtons.get(9).getPosY());
		app.image(plusP, uiButtons.get(10).getPosX(), uiButtons.get(10).getPosY());
		//app.rect(uiButtons.get(7).getPosX(), uiButtons.get(7).getPosY(), uiButtons.get(7).getWidth(), uiButtons.get(7).getHeight());	
		//app.rect(uiButtons.get(8).getPosX(), uiButtons.get(8).getPosY(), uiButtons.get(8).getWidth(), uiButtons.get(8).getHeight());		
		//app.rect(uiButtons.get(9).getPosX(), uiButtons.get(9).getPosY(), uiButtons.get(9).getWidth(), uiButtons.get(9).getHeight());
		//app.rect(uiButtons.get(10).getPosX(), uiButtons.get(10).getPosY(), uiButtons.get(10).getWidth(), uiButtons.get(10).getHeight());	
		//GRAPH
		app.rect(uiButtons.get(15).getPosX(), uiButtons.get(15).getPosY(), uiButtons.get(15).getWidth(), uiButtons.get(15).getHeight());
		//START
		app.rect(uiButtons.get(16).getPosX(), uiButtons.get(16).getPosY(), uiButtons.get(16).getWidth(), uiButtons.get(16).getHeight());
		
		//Remove players
		for (int i = 7; i < 11; i++) {
		
			if(!uiButtons.get(i).isActive()) {
				if(i==7) {
					app.image(p1img,116+((i-7)*120), 289);
				}else if(i==8){
					app.image(p2img,116+((i-7)*120), 289);
				}else if(i==9){
					app.image(p3img,116+((i-7)*120), 289);
				}else if(i==10){
					app.image(p4img,116+((i-7)*120), 289);
				}
				
				app.fill(200);
				//app.rect(116+((i-7)*120), 289, 104, 106);
				app.fill(200,80,80);
				app.image(remove, uiButtons.get(i+4).getPosX()+10, uiButtons.get(i+4).getPosY()+10,30,30);
				//app.circle(uiButtons.get(i+4).getPosX()+20, uiButtons.get(i+4).getPosY()+20, uiButtons.get(i+4).getWidth());	
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

	public ArrayList<PImage> getScreenImages() {
		return screenImages;
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
