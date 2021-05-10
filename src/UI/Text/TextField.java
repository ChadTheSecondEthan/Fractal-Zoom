package UI.Text;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Main.Input;
import UI.Button;
import UI.MouseListener;
import UI.Resources;

public class TextField extends Button {
	
	private StringBuilder text;
	private Document document;
	private Color color;
	private boolean selected;
	private float cursorTimer;

	public TextField(String text) {
		super();
		this.text = new StringBuilder(text);
		document = null;
		color = Resources.defaultColor;
		
		mouseListener = new MouseListener();
		
		cursorTimer = 0;
	}
	
	@Override
	public void update(float dt) {
		if (!selected) return;
		
		cursorTimer += dt * 3;
	}

	@Override
	public void draw(Graphics g) {
		g.setFont(Resources.defaultFont);
		g.setColor(color);
		g.drawRect(x, y, width, height);
		g.drawString(text.toString(), x + 5, y + (height + g.getFont().getSize()) / 2 - 5);
		
		if (selected && ((int) cursorTimer) % 2 == 0) {
			int width = g.getFontMetrics().stringWidth(text.toString());
			g.drawLine(x + width + 6, y + 7, x + width + 6, y + height - 7);
		}
	}
	
	@Override
	public boolean mouseClick() {
		selected = mouseListener.mouseEntered();
		return selected;
	}
	
	public void setDocument(Document document) {
		this.document = document;
	}
	
	public void append(char c) {
		text.append(c);
	}
	
	public void keyPressed(int code) {
		if (selected) {
			
			if (code == KeyEvent.VK_BACK_SPACE && text.length() >= 1) {
				text.deleteCharAt(text.length() - 1);
				document.charRemoved();
			}
			else if (document != null) 
				document.insertChar((char) code);
		}
	}
	
	public void setText(String text) {
		this.text = new StringBuilder(text);
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public String getText() {
		return text.toString();
	}

}
