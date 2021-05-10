package UI;

import java.awt.*;

import javax.swing.JComponent;

import Main.GamePanel;

public class UI {
	
	/**
	 * Summary: Centers text on the screen at a given y value
	 */
	public static void drawCenteredText(Graphics g, String text, int y) {
		int width = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (GamePanel.WIDTH - width) / 2, y);
	}
	
	/**
	 * Summary: Centers text on the screen at a given x and y value
	 */
	public static void drawCenteredText(Graphics g, String text, int x, int y) {
		int width = g.getFontMetrics().stringWidth(text);
		g.drawString(text, x - width / 2, y);
	}
	
	/**
	 * Summary: Positions a button at the center of the screen while also settings bounds
	 */
	public static void centerComponentBounds(UIElement e, int y, int width, int height) {
		e.setBounds((GamePanel.WIDTH - width) / 2, y, width, height);
	}
	
	/**
	 * Summary: Positions a button at the center of the screen while also settings bounds
	 */
	public static void centerComponentBounds(UIElement e, int x, int y, int width, int height) {
		e.setBounds(x - width / 2, y, width, height);
	}
	
	/**
	 * Summary: Aligns the centers of two components
	 */
	public static Point getComponentCenter(Component c) {
		return new Point(c.getLocation().x + c.getWidth() / 2, c.getLocation().y + c.getHeight() / 2);
	}

}
