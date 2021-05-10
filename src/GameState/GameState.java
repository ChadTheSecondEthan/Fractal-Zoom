package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;

import UI.UIElement;
import UI.Text.TextField;

public abstract class GameState {
	
	public static GameState curState;
	
	protected GameStateManager gsm;
	
	private ArrayList<UIElement> uiElements;
	
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
		uiElements = new ArrayList<>();
	}
	
	public abstract void init();
	public void onStateChanged() {}
	public void mouseWheelMoved(int rotation) {}
	
	public void keyPressed(int code) {
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		for (Iterator<UIElement> elements = uiCopy.iterator(); elements.hasNext();) {
			UIElement element = elements.next();
			if (element instanceof TextField)
				((TextField) element).keyPressed(code);
		}
	}
	
	public void update(float dt) {
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		for (Iterator<UIElement> elements = uiCopy.iterator(); elements.hasNext();) {
			UIElement element = elements.next();
			if (element.shouldDestroy())
				elements.remove();
			else if (element.isVisible())
				element.update(dt);
		}
		uiElements = uiCopy;
	}
	
	public void draw(Graphics2D g) {
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		for (UIElement element : uiCopy)
			if (element.isVisible())
				element.draw(g);
	}

	// mouse functions
	public void mouseMoved(Point p) {
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		for (UIElement element : uiCopy)
			if (element.isVisible())
				element.mouseMoved(p);
	}
	
	public boolean mouseClick(Point point) {
		boolean clicked = false;
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		for (UIElement element : uiCopy)
			if (element.isVisible() && element.mouseClick()) 
				clicked = true;
		return clicked;
	}	
	
	public void mousePress() {
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		for (UIElement element : uiCopy)
			if (element.isVisible())
				element.mousePress();
	}
	
	public void mouseRelease() {
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		for (UIElement element : uiCopy)
			if (element.isVisible())
				element.mouseRelease();
	}
	
	public void add(UIElement element) {
		if (!uiElements.contains(element)) {
			uiElements.add(element);
			element.onAdd();
		}
	}
	
	public void add(UIElement... elements) {
		for (UIElement element : elements)
			add(element);
	}
	
	public void remove(UIElement element) {
		ArrayList<UIElement> uiCopy = new ArrayList<>(uiElements);
		uiCopy.remove(element);
		uiElements = uiCopy;
	}
	
	public void removeAll() {
		uiElements = new ArrayList<>();
	}
}
