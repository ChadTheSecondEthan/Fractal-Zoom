package UI;

import java.awt.Color;
import java.awt.Graphics;

import Main.Mathf;
import UI.Text.Text;

public class Button extends Text {
	
	private Color mainColor;
	private Color hoverColor;
	private Color clickColor;
	private Color curColor;
	private Color lerpColor;

	public Button(String text) {
		super(text);
		
		width = 200;
		height = 50;
		setLocation(0, 0);
		
		mainColor = Color.LIGHT_GRAY;
		hoverColor = Color.GRAY;
		clickColor = Color.DARK_GRAY;
		
		curColor = mainColor;
		lerpColor = curColor;
		
		mouseListener = new MouseListener() {

			@Override
			public void onMouseEnter() {
				lerpColor = hoverColor;
			}

			@Override
			public void onMouseExit() {
				lerpColor = mainColor;
			}

			@Override
			public void onMousePress() {
				lerpColor = clickColor;
			}

			@Override
			public void onMouseRelease() {
				lerpColor = mouseEntered ? hoverColor : mainColor;
			}
			
		};
	}
	
	public Button() {
		this(null);
	}
	
	@Override
	public void setMouseListener(MouseListener mouseListener) {
		throw new Error("Error: You cannot set the mouse listener of a button");
	}
	
	@Override
	public void update(float dt) {
		if (dt > 0.1) dt = 0.1f;
		curColor = Mathf.lerpColor(curColor, lerpColor, dt * 10);
	}

	@Override
	public void draw(Graphics g) {
		
		g.setColor(curColor);
		g.fillRect(x, y, width, height);
		
		if (text == null) return;
		
		g.setFont(font);
		g.setColor(color);
		
		int drawX = x + width / 2;
		if (alignment != TextAlign.LEFT) {
			int width = g.getFontMetrics().stringWidth(text.toString());
			
			if (alignment == TextAlign.RIGHT)
				drawX -= width;
			else 
				drawX -= width / 2;
		}
		
		g.drawString(text.toString(), drawX, y + (height + font.getSize() / 2) / 2);
	}
	
	public void setOnClickListener(OnClickListener ocl) {
		mouseListener.setOnClickListener(ocl);
	}
	
	public void setColors(Color mainColor, Color hoverColor, Color clickColor) {
		this.mainColor = mainColor;
		this.hoverColor = hoverColor;
		this.clickColor = clickColor;
	}
	
	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}
	
	public void setHoverColor(Color hoverColor) {
		this.hoverColor = hoverColor;
	}
	
	public void setClickColor(Color clickColor) {
		this.clickColor = clickColor;
	}
}
