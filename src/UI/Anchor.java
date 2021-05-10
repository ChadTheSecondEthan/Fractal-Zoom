package UI;

import java.awt.Point;

import Main.GamePanel;

public enum Anchor {
	
	TOP_LEFT, TOP_CENTER, TOP_RIGHT,
	CENTER_LEFT, CENTER, CENTER_RIGHT,
	BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT;
	
	public Point toPoint() {
		if (this == TOP_LEFT) return new Point();
		if (this == TOP_CENTER) return new Point(GamePanel.WIDTH / 2, 0);
		if (this == TOP_RIGHT) return new Point(GamePanel.WIDTH, 0);
		if (this == CENTER_LEFT) return new Point(0, GamePanel.HEIGHT / 2);
		if (this == CENTER) return new Point(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
		if (this == CENTER_RIGHT) return new Point(GamePanel.WIDTH, GamePanel.HEIGHT / 2);
		if (this == BOTTOM_LEFT) return new Point(0, GamePanel.HEIGHT);
		if (this == BOTTOM_CENTER) return new Point(GamePanel.WIDTH / 2, GamePanel.HEIGHT);
		return new Point(GamePanel.WIDTH, GamePanel.HEIGHT);
	}

}
