package UI;

import java.awt.Color;
import java.awt.Graphics;

import GameState.GameState;
import UI.Text.Text;

public class Spinner extends UIElement {
	
	private static final int buttonWidth = 100, buttonHeight = 50;
	
	private double value;
	private double incrementValue;
	private double min, max;
	private boolean displayAsInt;
	private Text text;
	private Button increment, decrement;

	public Spinner() {
		value = 0;
		incrementValue = 1;
		min = -10;
		max = 10;
		displayAsInt = false;
		
		text = new Text("" + value);
		
		increment = new Button("Up");
		increment.setBounds(buttonWidth / 2,- buttonHeight / 2, buttonWidth, buttonHeight);
		increment.setOnClickListener(this::increment);

		decrement = new Button("Down");
		decrement.setBounds(buttonWidth / 2, buttonHeight / 2, buttonWidth, buttonHeight);
		decrement.setOnClickListener(this::decrement);
	}
	
	@Override
	public void onAdd() {
		GameState.curState.add(text, increment, decrement);
	}
	
	@Override
	public void setAnchor(Anchor anchor) {
		text.setAnchor(anchor);
		increment.setAnchor(anchor);
		decrement.setAnchor(anchor);
	}
	
	@Override
	public void setBounds(int x, int y, int width, int height) {
		super.setBounds(x, y, width, height);
		text.setLocation(x, y + text.getFont().getSize());
		increment.setLocation(x + buttonWidth / 2, y - buttonHeight / 2 - 10);
		decrement.setLocation(x + buttonWidth / 2, y + buttonHeight / 2 + 10);
	}

	@Override
	public void draw(Graphics g) {}
	
	public void setValue(double value) {
		this.value = value;
		if (this.value > max) this.value = max;
		else if (this.value < min) this.value = min;
		
		if (displayAsInt)
			text.setText(Math.round(this.value) + "");
		else
			text.setText("" + value);
	}
	
	public void setDisplayAsInt(boolean displayAsInt) { 
		this.displayAsInt = displayAsInt; 
		if (displayAsInt) text.setText("" + (int) value);
	}
	
	public double getValue() { return value; }
	
	public void setMin(double min) { this.min = min; }
	public void setMax(double max) { this.max = max; }
	
	public void setIncrement(double increment) {
		incrementValue = increment;
	}
	
	public void setMinAndMax(double min, double max) {
		this.min = min;
		this.max = max;
	}
	
	public void setColor(Color color) {
		text.setColor(color);
	}
	
	private void increment() {
		setValue(value + incrementValue);
	}
	
	private void decrement() {
		setValue(value - incrementValue);
	}

}
