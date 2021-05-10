package Fractals;

import java.awt.Point;

import Generation.FractalRenderer;
import Main.GamePanel;

/**
 * Stores information about the fractal, like zoom, iterations, offset, etc.
 */
public class FractalInfo {
	
	private Fractal fractal;
	private FractalInfo prevValues;
	
	private boolean renderAsJulia;
	private ComplexNumber juliaAdd;
	
	private double offsetX;
	private double offsetY;
	private int imageSize;
	private int zoomPower;
	private int iterations;

	public FractalInfo(Fractal fractal) {
		this.fractal = fractal;
		prevValues = new FractalInfo();
		
		renderAsJulia = false;
		juliaAdd = null;
		
		offsetX = 0;
		offsetY = 0;
		imageSize = GamePanel.WIDTH;
		zoomPower = 0;
		iterations = 100;
	}
	
	private FractalInfo() {
		fractal = null;
		offsetX = 0;
		offsetY = 0;
		imageSize = 0;
		zoomPower = 0;
		iterations = 100;
	}
	
	public boolean valuesChanged() { return !equals(prevValues); }
	
	public Fractal getFractal() { return fractal; }
	public boolean renderAsJulia() { return renderAsJulia; }
	public ComplexNumber juliaAdd() { return juliaAdd; }
	public double getOffsetX() { return offsetX; }
	public double getOffsetY() { return offsetY; }
	public int getImageSize() { return imageSize; }
	public int getZoomPower() { return zoomPower; }
	public int getIterations() { return iterations; }
	
	public void setFractal(Fractal fractal) { this.fractal = fractal; }
	public void addOffsetX(double offset) { offsetX += offset; }
	public void addOffsetY(double offset) { offsetY += offset; }
	public void setOffsetX(double offset) { offsetX = offset; }
	public void setOffsetY(double offset) { offsetY = offset; }
	public void setImageSize(int imageSize) { this.imageSize = imageSize; }
	
	public void zoomIn() { setZoomPower(zoomPower + 1); }
	public void zoomOut() { setZoomPower(zoomPower - 1); }
	
	public void reset() {
		prevValues.offsetX = offsetX;
		prevValues.offsetY = offsetY;
		prevValues.imageSize = imageSize;
		prevValues.zoomPower = zoomPower;
		prevValues.iterations = iterations;
		prevValues.fractal = fractal;
	}

	public void addOffset(Point o) {
		double zoom = Math.pow(2, zoomPower);
		addOffsetX((o.x - imageSize * 1.0 / 2) / zoom);
		addOffsetY(-(o.y - imageSize * 1.0 / 2) / zoom);
	}
	
	public void setZoomPower(int zoomPower) {
		if (zoomPower >= 0 && zoomPower <= FractalRenderer.MAX_ZOOM_POWER) 
			this.zoomPower = zoomPower;
	}
	
	public void setIterations(int iterations) {
		if (iterations > 0 && iterations < FractalRenderer.MAX_ITERATIONS) 
			this.iterations = iterations;
	}
	
	public void setJuliaAdd(ComplexNumber cn) {
		if (cn == null) {
			renderAsJulia = false;
			juliaAdd = null;
		} else {
			renderAsJulia = true;
			juliaAdd = new ComplexNumber(cn);
		}
	}
	
	public boolean equals(FractalInfo other) {
		return offsetX == other.offsetX
				&& offsetY == other.offsetY 
				&& imageSize == other.imageSize 
				&& zoomPower == other.zoomPower
				&& iterations == other.iterations
				&& other.fractal.getClass().getSimpleName().equals(fractal.getClass().getSimpleName());
	}

}
