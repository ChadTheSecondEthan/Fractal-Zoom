package Generation;

import Fractals.*;

public class FractalRenderer {
	
	// calculation stuff
	public static final int MAX_ZOOM_POWER = 45;
	public static final int MAX_ITERATIONS = 10000;
	
	private static FractalRenderer instance;

	private FractalInfo info;

	private FractalRenderer() {
		instance = this;

		info = new FractalInfo(null);
	}
	
	public FractalInfo getFractalInfo() { return info; }
	
	public void setFractalInfo(FractalInfo info) { this.info = info; } 
	
	public static FractalRenderer getInstance() { 
		if (instance == null)
			new FractalRenderer();
		return instance;
	}
}
