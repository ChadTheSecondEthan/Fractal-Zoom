package Fractals;

import GameState.RenderingPage;
import UI.Button;
import UI.Spinner;

public class BurningShip extends Fractal {
	
	private int power;
	
	private Spinner powerSpinner;
	private Button updateButton;
	
	public BurningShip() { 
		power = 2;
	}
	
	@Override
	protected int generateIterationCount(double x, double y, int max, boolean asJulia) {
		ComplexNumber start = new ComplexNumber(0, 0);
		ComplexNumber add = new ComplexNumber(x, y);
		
		int iterations = 0;
		while(start.sqrDistance() < 4 && iterations < max) {
			start = start.squared();
			start.abs();
			start.add(add);
			iterations++;
		}
		
		return iterations;
	}
	
	@Override
	public void display(RenderingPage page) {
		
		if (powerSpinner != null) {
			page.add(powerSpinner, updateButton);
			powerSpinner.setVisible(true);
			powerSpinner.setVisible(false);
		}
		
		powerSpinner = new Spinner();
		powerSpinner.setValue(power);
		powerSpinner.setDisplayAsInt(true);
		powerSpinner.setIncrement(1);
		powerSpinner.setBounds(500, 300, 120, 60);
		powerSpinner.setMinAndMax(2, 10);
		
		updateButton = new Button("Update");
		updateButton.setBounds(500, 500, 120, 60);
		updateButton.setOnClickListener(() -> {
			if (page.canUpdateImage()) {
				power = (int) powerSpinner.getValue();
				page.updateImage();
			}
		});
		
		page.add(powerSpinner, updateButton);
	}
	
	public void setPower(int power) {
		if (power >= 2)
			this.power = power;
	}

}
