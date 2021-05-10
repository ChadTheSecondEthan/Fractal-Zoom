package Generation;

import java.util.ArrayList;

/**
 * Colors the fractal iteration arrays
 */
public class Colorer {
	
	private static Colorer instance;
	
	private ArrayList<ColorMode> colorModes;
	private ColorMode colorMode;
	private int colorIndex;
	
	private Colorer() {
		instance = this;
		
		colorModes = new ArrayList<>();
		colorModes.add(new OneColor());
		colorModes.add(new MultiColor());
		colorIndex = 0;
		colorMode = colorModes.get(colorIndex);
	}
	
	public int[][] iterationsToColors(int[][] iterations, int max) {
		return colorMode.iterationsToColors(iterations, max);
	}
	
	public ColorMode getColorMode() { return colorMode; }
	public void setColorIndex(int index) {
		colorMode = colorModes.get(index);
		colorIndex = index;
	}
	
	public int getColorIndex() { return colorIndex; }
	
	public static Colorer getInstance() { 
		if (instance == null)
			new Colorer();
		return instance;
	}

}
