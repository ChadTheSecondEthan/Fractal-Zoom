package Fractals;

import GameState.RenderingPage;
import Main.Mathf;

public abstract class Fractal {
	
	// fractals
	public static final int MANDELBROT = 0;
	public static final int BURNING_SHIP = 1;
	public static final int CUSTOM = 2;
	public static final int NUM_FRACTALS = 3;
	
	public static Fractal getFractal(int number) {
		switch (number) {
			case MANDELBROT:
				return new Mandelbrot();
			case BURNING_SHIP:
				return new BurningShip();
			default:
				return new WeirdFractals();
		}
	}
	
	public int[][] generateIterationCounts(FractalInfo info) {
		
		int size = info.getImageSize();
		double zoom = Math.pow(2, info.getZoomPower());
		double offsetX = mapScreenValueToWorld(info.getOffsetX() + size / 2, 1, size);
		double offsetY = mapScreenValueToWorld(info.getOffsetY() + size / 2, 1, size);
		
		int[][] iterations = new int[info.getImageSize()][info.getImageSize()];
		
		for (int x = 0; x < size; x++) {
			
			double worldX = mapScreenValueToWorld(x, zoom, size) + offsetX;
			
			for (int y = 0; y < size; y++) {
				
				double worldY = mapScreenValueToWorld(y, zoom, size) - offsetY;
				
				iterations[x][y] = generateIterationCount(worldY, worldX, info.getIterations(), info.renderAsJulia());
			}
		}
		
		return iterations;
	}

	public static double mapScreenValueToWorld(double screenPos, double zoom, int imageSize) {
		return Mathf.lerp(-2.0 / zoom, 2.0 / zoom, screenPos / imageSize);
	}
	
	/**
	 * Summary: Allows a fractal to add something to the gamePanel
	 * Example: The julia set allows the user to change the starting point
	 */
	public void display(RenderingPage page) {}
	
	protected abstract int generateIterationCount(double x, double y, int max, boolean asJulia);

}
