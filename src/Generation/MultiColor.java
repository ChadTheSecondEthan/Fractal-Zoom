package Generation;

import java.awt.Color;

import GameState.GameState;
import UI.ColorField;

public class MultiColor extends ColorMode {
	
	private ColorField[] colorFields;
	private Color[] colors;

	public MultiColor() {
		colorFields = new ColorField[3];
		colors = new Color[] {
				Color.red, Color.green, Color.blue
		};
	}

	@Override
	public int[][] iterationsToColors(int[][] iterations, int maxIterations) {
		int[][] colors = new int[iterations.length][iterations[0].length];
		for (int x = 0; x < iterations.length; x++) {
			for (int y = 0; y < iterations[0].length; y++) {
				int iteration = iterations[x][y];
				if (iteration == maxIterations)
					colors[x][y] = 0;
				else
					colors[x][y] = this.colors[(iteration / 5) % this.colors.length].getRGB();
			}
		}
		return colors;
	}
	
	@Override
	public void display() {
		
		if (colorFields[0] != null) {
			for (int i = 0; i < colorFields.length; i++) {
				GameState.curState.add(colorFields[i]);
				colorFields[i].setVisible(true);
			}
			return;
		}
		
		for (int i = 0; i < colorFields.length; i++) {
			final int num = i;
			ColorField colorField = new ColorField(colors[i], true);
			colorField.setBounds(100 + 300 * i, 400, 200, 400);
			colorField.addListener((color) -> colors[num] = color);
			colorFields[i] = colorField;
			GameState.curState.add(colorField);
		}
	}
	
	@Override
	public void removeDisplay() {
		for (int i = 0; i < colorFields.length; i++)
			colorFields[i].setVisible(false);
	}
	
	@Override
	public String toString() { return "Multi-Colored"; }

}
