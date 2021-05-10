package UI.Text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import UI.Resources;
import UI.UIElement;

public class Text extends UIElement {
	
	protected StringBuilder text;
	protected Font font;
	protected Color color;
	protected TextAlign alignment;

	public Text(String text) {
		super();
		if (text == null) this.text = null;
		else this.text = new StringBuilder(text);
		
		font = Resources.defaultFont;
		color = Resources.defaultColor;
		alignment = TextAlign.CENTER;
	}

	public Text() { this(null); }

	public void draw(Graphics g) {
		g.setFont(font);
		g.setColor(color);
		
		int drawX = x;
		if (alignment != TextAlign.LEFT) {
			int width = g.getFontMetrics().stringWidth(text.toString());
			
			if (alignment == TextAlign.RIGHT)
				drawX -= width;
			else 
				drawX -= width / 2;
		}
		
		g.drawString(text.toString(), drawX, y);
	}
	
	public enum TextAlign { LEFT, CENTER, RIGHT }
	
	/**
	 * 
	 * 
	 * 
	 * Getters and Setters
	 * 
	 * 
	 * 
	 */
	
	public int getTextWidth(Graphics g) {
		return g.getFontMetrics().stringWidth(text.toString());
	}
	
	public void deleteCharAt(int index) {
		if (index > -1 && index < text.length())
			text.deleteCharAt(index);
	}
	
	public int length() {
		return text.length();
	}
	
	public String getText() {
		return text.toString();
	}

	public void setText(String text) {
		this.text = new StringBuilder(text);
	}

	public Font getFont() {
		return font;
	}

	public void setFont(Font font) {
		this.font = font;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public TextAlign getAlignment() {
		return alignment;
	}

	public void setAlignment(TextAlign alignment) {
		this.alignment = alignment;
	}

}
