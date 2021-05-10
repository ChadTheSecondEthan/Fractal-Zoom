package UI;

import java.awt.Color;
import java.awt.Graphics;

public class ProgressBar extends UIElement {
	
	private double progress;
	private int fillWidth;
	private Color fillColor;
	private Color backgroundColor;

	public ProgressBar() {
		super();
		height = 20;
		
		progress = 0;
		fillWidth = 2;
		fillColor = Color.red;
		backgroundColor = Color.black;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(backgroundColor);
		g.fillRect(x, y, width, height);
		g.setColor(fillColor);
		
		int drawWidth = (int) ((width - fillWidth * 2) * progress);
		if (drawWidth < 5)
			drawWidth = 5;
		g.fillRect(x + fillWidth, y + fillWidth, drawWidth, (height - fillWidth * 2));
	}

	public double getProgress() {
		return progress;
	}

	public int getFillWidth() {
		return fillWidth;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setProgress(double progress) {
		if (progress <= 1 && progress >= 0)
			this.progress = progress;
	}

	public void setFillWidth(int fillWidth) {
		this.fillWidth = fillWidth;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

}
