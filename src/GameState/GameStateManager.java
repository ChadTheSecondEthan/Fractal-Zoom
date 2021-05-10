package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

import Main.Input;
import Main.Time;

public class GameStateManager {
	
	private ArrayList<GameState> gameStates;
	private ArrayList<Integer> prevStates;
	private boolean initComplete;
	
	public static final int FRONT_PAGE = 0;
	public static final int SELECT_PAGE = 1;
	public static final int RENDERING_PAGE = 2;
	public static final int INSTRUCTIONS_PAGE = 3;
	public static final int SETTINGS_PAGE = 4;

	public GameStateManager() {
		
		gameStates = new ArrayList<>();
		gameStates.add(FRONT_PAGE, new FrontPage(this));
		gameStates.add(SELECT_PAGE, new SelectFractalPage(this));
		gameStates.add(RENDERING_PAGE, new RenderingPage(this));
		gameStates.add(INSTRUCTIONS_PAGE, new Instructions(this));
		gameStates.add(SETTINGS_PAGE, new Settings(this));
		
		prevStates = new ArrayList<>();
		setState(FRONT_PAGE);
	}
	
	public void setState(int state) {
		if (GameState.curState != null) {
			GameState.curState.onStateChanged();
			GameState.curState.removeAll();
		}
		GameState.curState = gameStates.get(state);
		
		initComplete = false;
		prevStates.add(state);
		GameState.curState.init();
		initComplete = true;
	}
	
	public void back() {
		if (prevStates.size() < 2) {
			if (prevStates.size() == 1)
				setState(prevStates.get(0));
			else
				setState(FRONT_PAGE);
			prevStates.clear();
			return;
		}
		int state = prevStates.remove(prevStates.size() - 2);
		setState(state);
		prevStates.remove(prevStates.size() - 2);
	}
	
	public void update(float dt) {
		if (Input.getKey(KeyEvent.VK_ESCAPE))
			back();
		if (initComplete) {
			GameState.curState.update(dt);
		}
	}
	
	public void draw(Graphics2D g) {
		if (initComplete)
			GameState.curState.draw(g);
	}
	
	public void mouseClick(Point point) {
		if (initComplete)
			GameState.curState.mouseClick(point);
	}
	
	public void mouseWheelMoved(int rotation) {
		if (initComplete)
			GameState.curState.mouseWheelMoved(rotation);
	}
	
	public void mouseMoved(Point p) {
		if (initComplete)
			GameState.curState.mouseMoved(p);
	}
	
	public void mousePress() {
		if (initComplete)
			GameState.curState.mousePress();
	}
	
	public void mouseRelease() {
		if (initComplete)
			GameState.curState.mouseRelease();
	}
	
	public void keyPressed(int code) {
		GameState.curState.keyPressed(code);
	}

}
