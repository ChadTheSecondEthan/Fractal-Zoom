package Fractals;

import GameState.RenderingPage;
import UI.Button;
import UI.Spinner;

import java.awt.Color;

import GameState.GameState;

public class WeirdFractals extends Fractal {
	
	private int whichOne;
	
	private Spinner spinner;
	private Button updateButton;
	
	public WeirdFractals() { 
		super(); 
		whichOne = 0;
	}
	
	@Override
	public void display(RenderingPage page) {
		
		if (spinner != null) {
			page.add(spinner, updateButton);
			spinner.setVisible(true);
			updateButton.setVisible(true);
			return;
		}
		
		spinner = new Spinner();
		spinner.setIncrement(1);
		spinner.setDisplayAsInt(true);
		spinner.setValue(whichOne);
		spinner.setMinAndMax(0, 5);
		spinner.setBounds(800, 400, 100, 40);
		spinner.setColor(Color.red);
		
		updateButton = new Button("Update");
		updateButton.setBounds(800, 500, 120, 60);
		updateButton.setOnClickListener(() -> {
			if (page.canUpdateImage()) {
				whichOne = (int) spinner.getValue();
				page.updateImage();
			}
		});
		
		GameState.curState.add(spinner, updateButton);
	}
	
//	@Override
//	protected ComplexNumber compute(double x, double y, double cx, double cy) {
//		
//		double img;
//		double real;
//		
//		ComplexNumber start = new ComplexNumber(x, y);
//		ComplexNumber add = new ComplexNumber(cx, cy);
//		
//		switch(whichOne) {
//		case 0:
//			start = start.squared();
//			img = start.getImaginaryValue();
//			start.setImaginaryValue(start.getRealValue());
//			start.setRealValue(img);
//			start.abs();
//			start.add(add);
//			break;
//		case 1:
//			start = start.squared();
//			img = start.getImaginaryValue();
//			start.setImaginaryValue(start.getRealValue());
//			start.setRealValue(img);
//			start.add(add);
//			break;
//		case 2:
//			start = start.squared();
//			img = start.getImaginaryValue();
//			start.setImaginaryValue(start.getRealValue() * img);
//			start.setRealValue(img);
//			start.add(add);
//			break;
//		case 3:
//			start = start.squared();
//			img = start.getImaginaryValue();
//			real = start.getRealValue();
//			start.setImaginaryValue(real * real);
//			start.setRealValue(img);
//			start.add(add);
//			break;
//		case 4:
//			start = start.squared();
//			img = start.getImaginaryValue();
//			real = start.getRealValue();
//			start.setImaginaryValue(real);
//			start.setRealValue(img * img);
//			start.add(add);
//			break;
//		case 5:
//			double nx = y;
//			double ny = -0.2 * x + 2.75 * y - y * y * y;
//			start.setRealValue(nx);
//			start.setImaginaryValue(ny);
//			break;
//		}
//		
//		return start;
//	}

	@Override
	protected int generateIterationCount(double x, double y, int max, boolean asJulia) {
		ComplexNumber start = new ComplexNumber(0, 0);
		ComplexNumber add = new ComplexNumber(x, y);
		
		return 0;
	}

}
