package UI;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import GameState.GameState;
import Main.Mathf;

public abstract class UIElement {

	private Anchor anchor;

	protected MouseListener mouseListener;

	protected int x, y, width, height;
	protected boolean visible;
	protected boolean shouldDestroy;

	public UIElement() {
		anchor = Anchor.TOP_LEFT;

		x = y = 0;
		width = height = 100;
		visible = true;
	}

	public void setBounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setLocationRelativeToAnchor();
	}
	
	public void setBounds(Rectangle rect) {
		x = rect.x;
		y = rect.y;
		width = rect.width;
		height = rect.height;
		setLocationRelativeToAnchor();
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
		setLocationRelativeToAnchor();
	}

	public void setLocation(Point p) {
		setLocation(p.x, p.y);
	}
	
	public void setLocationAbsolute(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setLocationAbsolute(Point p) {
		x = p.x;
		y = p.y;
	}
	
	public void addLocation(Point p) {
		setLocation(x + p.x, y + p.y);
	}
	
	public void addLocation(int x, int y) {
		setLocation(this.x + x, this.y + y);
	}
	
	private void setLocationRelativeToAnchor() {
		Point anchorPos = anchor.toPoint();
		x += anchorPos.x;
		y += anchorPos.y;
	}
	
	private void setXRelativeToAnchor() {
		Point anchorPos = anchor.toPoint();
		x += anchorPos.x;
	}
	
	private void setYRelativeToAnchor() {
		Point anchorPos = anchor.toPoint();
		y += anchorPos.y;
	}

	public void update(float dt) {}
	public void onAdd() {}
	public abstract void draw(Graphics g);
	
	public void mouseMoved(Point p) {
		
		if (mouseListener != null && visible) {
			
			boolean inRect = Mathf.pointWithinRect(p, getBounds());
			boolean mouseEntered = mouseListener.mouseEntered();
			
			if (!mouseEntered && inRect) {
				mouseListener.setMouseEntered(true);
				mouseListener.onMouseEnter();
			}
			
			else if (mouseEntered && !inRect) {
				mouseListener.setMouseEntered(false);
				mouseListener.onMouseExit();
			}
		}
	}
	
	public boolean mouseClick() {
		if (mouseListener != null && visible && mouseListener.mouseEntered() && mouseListener.getOnClickListener() != null) {
			mouseListener.onClick();
			return true;
		}
		return false;
	}
	
	public void mousePress() {
		if (mouseListener != null && visible && mouseListener.mouseEntered()) 
			mouseListener.onMousePress();
	}
	
	public void mouseRelease() {
		if (mouseListener != null && visible && mouseListener.mouseEntered()) 
			mouseListener.onMouseRelease();
	}

	/**
	 * 
	 * 
	 * 
	 * Basic Getters and Setters
	 * 
	 * 
	 * 
	 */

	public boolean isVisible() {
		return visible;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	
	public MouseListener getMouseListener() {
		return mouseListener;
	}

	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
		setLocationRelativeToAnchor();
	}
	
	public void setX(int x) {
		this.x = x;
		setXRelativeToAnchor();
	}
	
	public void setY(int y) {
		this.y = y;
		setYRelativeToAnchor();
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void setShouldDestroy(boolean shouldDestroy) {
		this.shouldDestroy = shouldDestroy;
	}
	
	public boolean shouldDestroy() { return shouldDestroy; }

}
