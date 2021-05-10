package Fractals;

import GameState.RenderingPage;

public class Mandelbrot extends Fractal {

	@Override
	protected int generateIterationCount(double x, double y, int max, boolean asJulia) {
		ComplexNumber start = new ComplexNumber(0, 0);
		ComplexNumber add = new ComplexNumber(x, y);
		
		int iterations = 0;
		while (start.sqrDistance() < 4 && iterations < max) {
			start = start.squared().add(add);
			iterations++;
		}
		
		return iterations;
	}

}
