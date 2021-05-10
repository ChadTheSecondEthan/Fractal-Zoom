package Generation;

import java.awt.Color;

import Main.Mathf;
import UI.ColorField;
import GameState.GameState;

public class OneColor extends ColorMode {
	
	private ColorField colorField;
	private Color color;

	public OneColor() {
		super();
		colorField = null;
		color = Color.black;
	}

	@Override
	public int[][] iterationsToColors(int[][] iterations, int max) {

		int[][] rgbs = new int[iterations.length][iterations[0].length];
		for (int x = 0; x < iterations.length; x++)
			for (int y = 0; y < iterations[0].length; y++) {
				
				int iteration = iterations[x][y];
				
				if (iteration == max) {
					rgbs[x][y] = 0;
					continue;
				}
				
				double iterationPercent = 1.0 * iteration / max;
				iterationPercent = Math.cbrt(iterationPercent);
				Color newColor = new Color(
						(int) Mathf.lerp(color.getRed(), 255, iterationPercent),
						(int) Mathf.lerp(color.getGreen(), 255, iterationPercent),
						(int) Mathf.lerp(color.getBlue(), 255, iterationPercent));
				rgbs[x][y] = newColor.getRGB();
			}
		return rgbs;
	}
	
	@Override
	public void display() {
		
		if (colorField != null) {
			GameState.curState.add(colorField);
			colorField.setVisible(true);
			return;
		}

		colorField = new ColorField(color, true);
		colorField.setBounds(400, 400, 100, 100);
		colorField.addListener((color) -> this.color = color);
		GameState.curState.add(colorField);
	}
	
	public void removeDisplay() {
		colorField.setVisible(false);
	}

	@Override
	public String toString() { return "One Color"; }
	
	public Color getColor() { return color; }

}
