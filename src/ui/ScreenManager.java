package ui;

import java.io.IOException;
import java.util.ArrayList;


import model.Box;
import model.BoxType;
import model.Controller;
import model.Player;
import model.Question;
import processing.core.PApplet;
import processing.core.PImage;

public class ScreenManager {
	private ScreenIdentifier screenId;
	
	private PApplet app;
	private Controller controler;
	private boolean choosed;
	private boolean showQuestion;
	private int playerIndex;
	private int currentAnswer;
	
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
	
	//boxes
	private PImage norm1;
	private PImage norm2;
	private PImage norm3;
	private PImage norm4;
	//
	//private PImage var1;
	//private PImage var2;
	//private PImage var3;
	//private PImage var4;
	//
	private PImage crown;
	private PImage coins;
	//private PImage bag;
	private PImage croco;
	//
	private PImage player1;
	private PImage player2;
	private PImage player3;
	private PImage player4;
	//
	private PImage card1;
	private PImage card2;
	private PImage card3;
	private PImage card4;

	//private PImage card1S;
	//private PImage card2S;
	//private PImage card3S;
	//private PImage card4S;
	//
	private PImage dice;
	//
	private PImage gameBg;
	private PImage canSelect;
	
	private PImage thumb;
	private PImage backBtn;
	
	
	public ScreenManager(PApplet app) throws IOException {
		
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
		lessSmall = app.loadImage("data/imgs/lessSmall-08.png");
		//	
		norm1 = app.loadImage("data/imgs/norm1-09.png");
		norm2 = app.loadImage("data/imgs/norm2-09.png");
		norm3 = app.loadImage("data/imgs/norm3-09.png");
		norm4 = app.loadImage("data/imgs/norm4-09.png");
		//
		//var1 = app.loadImage("data/imgs/var1-09.png");
		//var2 = app.loadImage("data/imgs/var2-09.png");
		//var3 = app.loadImage("data/imgs/var3-09.png");
		//var4 = app.loadImage("data/imgs/var4-09.png");
		//
		crown = app.loadImage("data/imgs/crown-10.png");
		coins = app.loadImage("data/imgs/coin-10.png");
		//bag = app.loadImage("data/imgs/bag-10.png");
		croco = app.loadImage("data/imgs/croco-10.png");
		//
		player1 = app.loadImage("data/imgs/Player1-11.png");
		player2 = app.loadImage("data/imgs/Player2-11.png");
		player3 = app.loadImage("data/imgs/Player3-11.png");
		player4 = app.loadImage("data/imgs/Player4-11.png");
		//
		card1 = app.loadImage("data/imgs/CARD1-12.png");
		card2 = app.loadImage("data/imgs/CARD2-12.png");
		card3 = app.loadImage("data/imgs/CARD3-12.png");
		card4 = app.loadImage("data/imgs/CARD4-12.png");
		
		//card1S = app.loadImage("data/imgs/CARD1S-12.png");
		//card2S = app.loadImage("data/imgs/CARD2S-12.png");
		//card3S = app.loadImage("data/imgs/CARD3S-12.png");
		//card4S = app.loadImage("data/imgs/CARD4S-12.png");
		
		dice = app.loadImage("data/imgs/dice-07.png");
		//
		gameBg = app.loadImage("data/imgs/gameBG.png");
		canSelect = app.loadImage("data/imgs/canSelect-09.png");
		
		thumb = app.loadImage("data/imgs/maprhumb-09.png");
		
		backBtn = app.loadImage("data/imgs/backBtn-13.png");
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
	
	public void loadGameScreen() {
		uiButtons.add(new Button(1010,550,50,50, false, ButtonIdentifier.THROW_DICE));
	}
	
	public void loadEndScreen() {
		uiButtons.add(new Button(500,500,105,40, false, ButtonIdentifier.BACK));
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
		app.textSize(15);
		app.text("Please select at least 2 players", 710, 350);
		app.textSize(22);
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
		app.image(thumb, 290, 480,135,105);
		//zapp.rect(uiButtons.get(15).getPosX(), uiButtons.get(15).getPosY(), uiButtons.get(15).getWidth(), uiButtons.get(15).getHeight());
		//START
		//app.rect(uiButtons.get(16).getPosX(), uiButtons.get(16).getPosY(), uiButtons.get(16).getWidth(), uiButtons.get(16).getHeight());
		
		app.image(playBtn, 824, 589,145,40);
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
	
	public void renderGameScreen() {
		app.image(gameBg, 0, 0);	
		app.fill(252, 158, 189);
		app.text("ROUND: "+controler.getCurrentRound()+"/"+controler.getNumRounds() , 470, 80);
		
		for (int i = 0; i < controler.getCurrentBoard().getRoads().size(); i++) {
			app.stroke(10);
			app.strokeWeight(5);
			app.line(controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getInitial().getValue().getPosX(), 
					controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getInitial().getValue().getPosY(), 
					controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getEnd().getValue().getPosX(),
					controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getEnd().getValue().getPosY());
			app.fill(255);
			app.text(controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getWeight()+"", (controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getInitial().getValue().getPosX()+controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getEnd().getValue().getPosX())/2,
					(controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getInitial().getValue().getPosY()+controler.getCurrentBoard().getRoads().get(i).getSimpleEdge().getEnd().getValue().getPosY())/2);
		}
		
		for (int i = 0; i < controler.getCurrentBoard().getBoxes().size(); i++) {
			if(controler.getCurrentBoard().getBoxes().get(i).getCategory()==1) {
				app.image(norm1, controler.getCurrentBoard().getBoxes().get(i).getPosX()-40, controler.getCurrentBoard().getBoxes().get(i).getPosY()-30, 90, 75);
			}else if(controler.getCurrentBoard().getBoxes().get(i).getCategory()==2){
				app.image(norm2, controler.getCurrentBoard().getBoxes().get(i).getPosX()-40, controler.getCurrentBoard().getBoxes().get(i).getPosY()-30, 90, 75);
			}else if(controler.getCurrentBoard().getBoxes().get(i).getCategory()==3){
				app.image(norm3, controler.getCurrentBoard().getBoxes().get(i).getPosX()-40, controler.getCurrentBoard().getBoxes().get(i).getPosY()-30, 90, 75);
			}else {
				app.image(norm4, controler.getCurrentBoard().getBoxes().get(i).getPosX()-40, controler.getCurrentBoard().getBoxes().get(i).getPosY()-30, 90, 75);
			}
			//
			
			if(controler.getCurrentBoard().getBoxes().get(i).getType().equals(BoxType.BOOST)) {
				app.image(coins, controler.getCurrentBoard().getBoxes().get(i).getPosX()-18, controler.getCurrentBoard().getBoxes().get(i).getPosY()-45, 45, 45);
			}else if(controler.getCurrentBoard().getBoxes().get(i).getType().equals(BoxType.CROCODILE)) {
				app.image(croco, controler.getCurrentBoard().getBoxes().get(i).getPosX()-18, controler.getCurrentBoard().getBoxes().get(i).getPosY()-40, 45, 45);
			}else if(controler.getCurrentBoard().getBoxes().get(i).getType().equals(BoxType.CROWN)) {
				app.image(crown, controler.getCurrentBoard().getBoxes().get(i).getPosX()-18, controler.getCurrentBoard().getBoxes().get(i).getPosY()-45, 45, 45);
			}
		}
		
		//POSSIBLE MOVES
		for (int i = 0; i < controler.getPosibleMoves().size() && !choosed; i++) {
			app.image(canSelect, controler.getPosibleMoves().get(i).getPosX()-40, controler.getPosibleMoves().get(i).getPosY()-30, 90, 75);
		}
		
		if(controler.getPlayers().size()> 0) {
			switch(controler.getPlayers().get(0).getAvatar()) {
			case 1:
				app.image(player1, controler.getPlayers().get(0).getPosX()-10,  controler.getPlayers().get(0).getPosY()-25,60,60);
				app.image(card1, 10, 10);
				break;
			case 2:
				app.image(player2, controler.getPlayers().get(0).getPosX()-10,  controler.getPlayers().get(0).getPosY()-25,60,60);
				app.image(card2, 10, 10);
				break;
			case 3:
				app.image(player3, controler.getPlayers().get(0).getPosX()-10,  controler.getPlayers().get(0).getPosY()-25,60,60);
				app.image(card3, 10, 10);
				break;
			case 4:
				app.image(player4, controler.getPlayers().get(0).getPosX()-10,  controler.getPlayers().get(0).getPosY()-25,60,60);
				app.image(card4, 10, 10);
				break;
			}
			app.fill(255);
			app.text(controler.getPlayers().get(0).getCoins()+"", 60, 55);
			app.text(controler.getPlayers().get(0).getCrowns()+"", 165, 55);
		
			
		}if(controler.getPlayers().size()> 1){
			switch(controler.getPlayers().get(1).getAvatar()) {
			case 1:
				app.image(player1, controler.getPlayers().get(1).getPosX()+5,  controler.getPlayers().get(1).getPosY()-40,60,60);
				
				app.image(card1, 810, 10);
				break;
			case 2:
				app.image(player2, controler.getPlayers().get(1).getPosX()+5,  controler.getPlayers().get(1).getPosY()-40,60,60);
				app.image(card2, 810, 10);
				break;
			case 3:
				app.image(player3, controler.getPlayers().get(1).getPosX()+5,  controler.getPlayers().get(1).getPosY()-40,60,60);
				app.image(card3, 810, 10);
				break;
			case 4:
				app.image(player4, controler.getPlayers().get(1).getPosX()+5,  controler.getPlayers().get(1).getPosY()-40,60,60);
				app.image(card4, 810, 10);
				break;
			}
			
			app.fill(255);
			app.text(controler.getPlayers().get(1).getCoins()+"", 860, 55);
			app.text(controler.getPlayers().get(1).getCrowns()+"", 965, 55);
			
			
			
		}if(controler.getPlayers().size()> 2){
			switch(controler.getPlayers().get(2).getAvatar()) {
			case 1:
				app.image(player1, controler.getPlayers().get(2).getPosX()+20,  controler.getPlayers().get(2).getPosY()-25,60,60);
				app.image(card1, 10, 640);
				break;
			case 2:
				app.image(player2, controler.getPlayers().get(2).getPosX()+20,  controler.getPlayers().get(2).getPosY()-25,60,60);
				app.image(card2, 10, 640);
				break;
			case 3:
				app.image(player3, controler.getPlayers().get(2).getPosX()+20,  controler.getPlayers().get(2).getPosY()-25,60,60);
				app.image(card3, 10, 640);
				break;
			case 4:
				app.image(player4, controler.getPlayers().get(2).getPosX()+20,  controler.getPlayers().get(2).getPosY()-25,60,60);
				app.image(card4, 10, 640);
				break;
			}
			
			app.fill(255);
			app.text(controler.getPlayers().get(2).getCoins()+"", 60, 685);
			app.text(controler.getPlayers().get(2).getCrowns()+"", 165, 685);
			
		}if(controler.getPlayers().size()> 3){
			switch(controler.getPlayers().get(3).getAvatar()) {
			case 1:
				app.image(player1, controler.getPlayers().get(3).getPosX()+5,  controler.getPlayers().get(3).getPosY()-10,60,60);
				app.image(card1, 810, 640);
				break;
			case 2:
				app.image(player2, controler.getPlayers().get(3).getPosX()+5,  controler.getPlayers().get(3).getPosY()-10,60,60);
				app.image(card2, 810, 640);
				break;
			case 3:
				app.image(player3, controler.getPlayers().get(3).getPosX()+5,  controler.getPlayers().get(3).getPosY()-10,60,60);
				app.image(card3, 810, 640);
				break;
			case 4:
				app.image(player4, controler.getPlayers().get(3).getPosX()+5,  controler.getPlayers().get(3).getPosY()-10,60,60);
				app.image(card4, 810, 640);
				break;
			}
			app.fill(255);
			app.text(controler.getPlayers().get(3).getCoins()+"", 860, 685);
			app.text(controler.getPlayers().get(3).getCrowns()+"", 965, 685);	
			
		}
		
		if(controler.getCurrentPlayer().getAvatar()==1) {
				app.text("Glop's turn", 490, 50);
		}else if(controler.getCurrentPlayer().getAvatar()==2) {
			app.text("Kyle's turn", 490, 50);
		}else if(controler.getCurrentPlayer().getAvatar()==3) {
			app.text("Rena's turn", 490, 50);
		}else {
			app.text("Tiko's turn", 490, 50);
		}
		
		app.image(dice, uiButtons.get(17).getPosX(), uiButtons.get(17).getPosY());
		
		if(showQuestion) {
			showQuestion(null);
		}
	}
	
	public void renderEndScreen() {
		app.fill(63,11,96);	
		app.rect(0, 0, 1080, 720);
		app.fill(255);
		app.text("WINNER", 500, 300);
		switch(controler.getWinner().getAvatar()) {
		case 1:
			app.image(p1img, 480, 350);
			break;
		case 2:
			app.image(p2img, 480, 350);
			break;
		case 3:
			app.image(p3img, 480, 350);
			break;
		case 4:
			app.image(p4img, 480, 350);
			break;
		}
		
		app.image(backBtn, uiButtons.get(18).getPosX()-15, uiButtons.get(18).getPosY());
		
		
	}
	
	
	public Box verifySelection(int mouseX, int mouseY) {
		Box selected = null;
		for (int i = 0; i < controler.getPosibleMoves().size(); i++) {
			if(mouseX > controler.getPosibleMoves().get(i).getPosX()-50 && mouseX < controler.getPosibleMoves().get(i).getPosX()+50
				&& mouseY > controler.getPosibleMoves().get(i).getPosY()-35 && mouseY < controler.getPosibleMoves().get(i).getPosY()+35) {
				selected = controler.getPosibleMoves().get(i);
			}
		}
		
		return selected;
	}
	
	public void showQuestion(Question question) {
		ArrayList<String> options = new ArrayList();
		options.add("2");
		options.add("4");
		options.add("6");
		options.add("8");
		question = new Question("1+1?", options, 1);
		//errase above
		currentAnswer = question.getAnswer();
		app.fill(35, 11, 99, 50);
		app.rect(0, 0, 1080, 720);
		app.fill(240);
		app.rect(360, 250, 320, 220);
		app.fill(20);
		app.text(question.getStatement(), 380, 280);
		for (int i = 0; i < question.getOptions().size(); i++) {
			app.strokeWeight(1);
			app.fill(230);
			app.rect(370, 290+((i)*40), 180, 40);
			app.fill(20);
			app.text(question.getOptions().get(i), 380, 280+((1+i)*40));
		}
	}
	
	public void movePLayer(ArrayList<ArrayList<Integer>> coordinates) {
		
		controler.getCurrentPlayer().setPosX(controler.getCurrentPlayer().getCurrentBox().getPosX());
		controler.getCurrentPlayer().setPosY(controler.getCurrentPlayer().getCurrentBox().getPosY());
		
		if(controler.getCurrentPlayer().getCurrentBox().getType().equals(BoxType.CROWN)) {
			controler.triggerCrownEvent();
		}else if(controler.getCurrentPlayer().getCurrentBox().getType().equals(BoxType.CROCODILE)) {
			controler.triggerCrocodileEvent();
			for (int i = 0; i < controler.getPlayers().size(); i++) {
				controler.getPlayers().get(i).setPosX(controler.getPlayers().get(i).getCurrentBox().getPosX());
				controler.getPlayers().get(i).setPosY(controler.getPlayers().get(i).getCurrentBox().getPosY());
			}
		}else if(controler.getCurrentPlayer().getCurrentBox().getType().equals(BoxType.BOOST)) {
			int boost = (int) (Math.random()*3);
			if(boost==0) {
				
				int indexSteal = (int) (Math.random()*controler.getPlayers().size());
				
				for (int i = 0; i <getControler().getPlayers().size(); i++) {
					while(getControler().getPlayers().get(i).equals(getControler().getPlayers().get(indexSteal))){
						indexSteal = (int) (Math.random()*controler.getPlayers().size());
					}
				}
				
				controler.stealCrowns(getControler().getPlayers().get(indexSteal));
				
			}else if(boost==1) {
				
				
				int indexSteal = (int) (Math.random()*controler.getPlayers().size());
				
				for (int i = 0; i <getControler().getPlayers().size(); i++) {
					while(getControler().getPlayers().get(i).equals(getControler().getPlayers().get(indexSteal))){
						indexSteal = (int) (Math.random()*controler.getPlayers().size());
					}
				}
				
				controler.stealCoins(getControler().getPlayers().get(indexSteal));
			}else{
				
				controler.duplicateCoins();
			}
		}		
		
		controler.removeCoins();
	}
	
	public void changeTurn() {
		//System.out.println("asdfdsdf");
		//System.out.println(playerIndex);
		playerIndex++;
		//System.out.println(playerIndex);
		if(playerIndex >= controler.getPlayers().size()) {
			playerIndex = 0;
		}
		
		controler.setCurrentPlayer(controler.getPlayers().get(playerIndex));
		
		controler.getPosibleMoves().clear();
		choosed = false;
		uiButtons.get(17).setActive(true);
		controler.setCanMove(true);
		
		if(controler.getCurrentPlayer().equals(controler.getInitialPlayer())) {
			controler.setCurrentRound(controler.getCurrentRound()+1);
			controler.rechargeCoins();
			if(controler.getCurrentRound() > controler.getNumRounds()) {
				controler.findWinner();
				screenId = ScreenIdentifier.END_SCREEN;
				loadEndScreen();
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

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public boolean isShowQuestion() {
		return showQuestion;
	}

	public void setShowQuestion(boolean showQuestion) {
		this.showQuestion = showQuestion;
	}

	public int getCurrentAnswer() {
		return currentAnswer;
	}

	public void setCurrentAnswer(int currentAnswer) {
		this.currentAnswer = currentAnswer;
	}


	

	

	

	
}
