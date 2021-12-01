package ui;
	
import java.util.ArrayList;

import datastr.SimpleGraph;
import datastr.Vertex;
import model.Player;
import processing.core.PApplet;

public class Main extends PApplet{

	public static void main(String[] args) {
		exampleThree();
		PApplet.main("ui.Main");
	}
	
	ScreenManager scManager;
	
	public static void exampleOne() {
		SimpleGraph<Integer> sg = new SimpleGraph<Integer>();
		sg.addVertex(1);
		sg.addVertex(2);
		sg.addVertex(3);
		sg.addVertex(4);
		sg.addVertex(5);
		sg.addVertex(6);

		Vertex<Integer> v1 = sg.getVertices().get(0);
		Vertex<Integer> v2 = sg.getVertices().get(1);
		Vertex<Integer> v3 = sg.getVertices().get(2);
		Vertex<Integer> v4 = sg.getVertices().get(3);
		Vertex<Integer> v5 = sg.getVertices().get(4);
		Vertex<Integer> v6 = sg.getVertices().get(5);
		
		sg.addEdge(v1, v2, 2);
		sg.addEdge(v2, v1, 2);

		sg.addEdge(v2, v3, 10);
		sg.addEdge(v3, v2, 10);

		sg.addEdge(v3, v6, 2);
		sg.addEdge(v6, v3, 2);

		sg.addEdge(v6, v5, 2);
		sg.addEdge(v5, v6, 2);

		sg.addEdge(v5, v4, 4);
		sg.addEdge(v4, v5, 4);

		sg.addEdge(v4, v1, 4);
		sg.addEdge(v1, v4, 4);

		sg.addEdge(v2, v5, 2);
		sg.addEdge(v5, v2, 2);

		sg.breadthFirstSearch(v1);

		for(int i = 0; i < sg.getVertices().size(); i++) {
			System.out.println("v" + (sg.getVertices().get(i).getId()+1) + " d=" + sg.getVertices().get(i).getDistance() + " c=" + sg.getVertices().get(i).getColor());
		}

		sg.depthFirstSearch();

		for(int i = 0; i < sg.getVertices().size(); i++) {
			System.out.println("v" + (sg.getVertices().get(i).getId()+1) + " f=" + sg.getVertices().get(i).getTimestamps().getFirst().toString() + " s=" + sg.getVertices().get(i).getTimestamps().getSecond().toString());
		}

		ArrayList<Integer> result = sg.dijkstra(v1);

		System.out.println(result.toString());
	}

	public static void exampleTwo() {
		SimpleGraph<Integer> sg = new SimpleGraph<Integer>();
		sg.addVertex(1);
		sg.addVertex(2);
		sg.addVertex(3);
		sg.addVertex(4);
		sg.addVertex(5);
		sg.addVertex(6);

		Vertex<Integer> v1 = sg.getVertices().get(0);
		Vertex<Integer> v2 = sg.getVertices().get(1);
		Vertex<Integer> v3 = sg.getVertices().get(2);
		Vertex<Integer> v4 = sg.getVertices().get(3);
		Vertex<Integer> v5 = sg.getVertices().get(4);
		Vertex<Integer> v6 = sg.getVertices().get(5);
		
		sg.addEdge(v1, v2, 10);
		sg.addEdge(v2, v1, 10);
		
		sg.addEdge(v1, v3, 3);
		sg.addEdge(v3, v1, 3);
		
		sg.addEdge(v2, v3, 5);
		sg.addEdge(v3, v2, 5);
		
		sg.addEdge(v2, v4, 1);
		sg.addEdge(v4, v2, 1);
		
		sg.addEdge(v2, v5, 13);
		sg.addEdge(v5, v2, 13);
		
		sg.addEdge(v2, v6, 15);
		sg.addEdge(v6, v2, 15);
		
		sg.addEdge(v3, v4, 1);
		sg.addEdge(v4, v3, 1);
		
		sg.addEdge(v4, v5, 16);
		sg.addEdge(v5, v4, 16);
		
		sg.addEdge(v4, v6, 15);
		sg.addEdge(v6, v4, 15);
		
		sg.addEdge(v5, v6, 1);
		sg.addEdge(v6, v5, 1);
		
		ArrayList<Integer> result = sg.dijkstra(v1);

		System.out.println(result.toString());
	}
	
	public static void exampleThree() {
		SimpleGraph<Integer> sg = new SimpleGraph<Integer>();
		sg.deleteVertex(new Vertex<Integer>(10, 0));
	}
	
	public void settings() {
		size(1080, 720);
	}
	
	public void setup() {
		scManager = new ScreenManager(this);
		noStroke();
	}
	
	public void draw() {
		background(80);
		switch (scManager.getScreenId()) {
		case MAIN_SCREEN:
			scManager.renderMainScreen();
			break;
		case CONFIGURATION_SCREEN:
			scManager.renderConfiguratoinScreen();
			break;
		case GAME_SCREEN:
			scManager.renderGameScreen();
			break;
		default:
			break;
		}
		
	}
	
	public void mousePressed() {
		Button pressedBtn = pressesSensibleArea();
		if(pressedBtn!=null) {
			ButtonIdentifier identifier = pressedBtn.getIdentifier();
			
			//Verifies in which screen the user is currently located (to not activate buttons in other screens)
			switch (scManager.getScreenId()) {
			case MAIN_SCREEN:
				if(identifier!=null) {
					
					switch (identifier) {
					case PLAY:
						scManager.loadConfigurationScreen();
						scManager.setScreenId(ScreenIdentifier.CONFIGURATION_SCREEN);
						break;
					case EXIT:
						System.out.println("EXIT");
						break;
					case ABOUT:
						scManager.setScreenId(ScreenIdentifier.ABOUT_SCREEN);
						break;
					default:
						break;
					}
				}				
			case CONFIGURATION_SCREEN:
				if(identifier!=null) {
					
					switch (identifier) {
					case INCREASE_ROUNDS:
						if(scManager.getControler().getNumRounds() < 50) {
							scManager.getControler().setNumRounds(scManager.getControler().getNumRounds()+1);
						}					
						break;
					case INCREASE_ROUNDS_BY_10:
						if(scManager.getControler().getNumRounds() <= 40) {
							scManager.getControler().setNumRounds(scManager.getControler().getNumRounds()+10);
						}else {
							scManager.getControler().setNumRounds(50);
						}
						break;
					case DECREASE_ROUNDS:
						if(scManager.getControler().getNumRounds() > 10) {
							scManager.getControler().setNumRounds(scManager.getControler().getNumRounds()-1);
						}
						break;
					case DECREASE_ROUNDS_BY_10:
						if(scManager.getControler().getNumRounds() >= 20) {
							scManager.getControler().setNumRounds(scManager.getControler().getNumRounds()-10);
						}else {
							scManager.getControler().setNumRounds(10);
						}
						break;
					case ADD_PLAYER:
						addPlayer(pressedBtn);
						scManager.getPlayersUi().add(new Player(0, 0));
						break;
					case ADD_PLAYER2:
						addPlayer(pressedBtn);
						scManager.getPlayersUi().add(new Player(0, 0));
						break;
					case ADD_PLAYER3:
						addPlayer(pressedBtn);
						scManager.getPlayersUi().add(new Player(0, 0));
						break;
					case ADD_PLAYER4:
						addPlayer(pressedBtn);
						scManager.getPlayersUi().add(new Player(0, 0));
						break;
					case DELETE_PLAYER:
						deletePLayer(7);
						scManager.getPlayersUi().remove(0);
						break;
					case DELETE_PLAYER2:
						deletePLayer(8);
						scManager.getPlayersUi().remove(0);
						break;
					case DELETE_PLAYER3:
						deletePLayer(9);
						scManager.getPlayersUi().remove(0);
						break;
					case DELETE_PLAYER4:
						deletePLayer(10);
						scManager.getPlayersUi().remove(0);
						break;
					case CHANGE_GRAPH:
						scManager.getControler().setSimpleGraph(!scManager.getControler().isSimpleGraph());
						break;
					case START:
						if(scManager.getPlayersUi().size() > 1) {
							scManager.setScreenId(ScreenIdentifier.GAME_SCREEN);
							scManager.getControler().createBoard1();
							scManager.getControler().start(scManager.getPlayersUi());
						}
						//scManager.getControler().start(scManager.getPlayersUi());
						break;
					default:
						break;
					}
				}
				break;
			default:
				break;
			}
		}
	}

	public Button pressesSensibleArea() {
		Button pressed = null;
		for (int i = 0; i < scManager.getUiButtons().size(); i++) {
			if(mouseX > scManager.getUiButtons().get(i).getPosX() && mouseX < scManager.getUiButtons().get(i).getPosX()+scManager.getUiButtons().get(i).getWidth()
				&& mouseY > scManager.getUiButtons().get(i).getPosY() && mouseY < scManager.getUiButtons().get(i).getPosY()+scManager.getUiButtons().get(i).getHeight()) {
				pressed = scManager.getUiButtons().get(i);
			}
		}
		return pressed;
	}
	
	public void addPlayer(Button pressedBtn) {
		if(pressedBtn.isActive()) {
			scManager.getControler().getPlayers();
			pressedBtn.setActive(false);
		}
	}
	
	public void deletePLayer(int playerIndex) {
		if(!scManager.getUiButtons().get(playerIndex).isActive()) {
			scManager.getControler().getPlayers();
			scManager.getUiButtons().get(playerIndex).setActive(true);
		}
	}
	
}
