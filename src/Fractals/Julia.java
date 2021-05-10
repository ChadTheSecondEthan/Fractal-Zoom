//package Fractals;
//
//import GameState.RenderingPage;
//import UI.Button;
//import UI.Text.NumberDocument;
//import UI.Text.TextField;
//import GameState.GameState;
//
//public class Julia extends Fractal {
//	
//	private ComplexNumber add;
//	private TextField imaginaryField;
//	private TextField realField;
//	private Button updateButton;
//
//	public Julia() {
//		super();
//		add = new ComplexNumber(0.65, 0.0);
//	}
//
//	@Override
//	protected int getIterationCount(double x, double y, int maxIterations) {
//		
//		int iterations = 0;
//		ComplexNumber start = new ComplexNumber(x, y);
//		while(start.sqrDistance() < 4 && iterations < maxIterations) {
//			start = start.squared();
//			start.add(add);
//			iterations++;
//		}
//		return iterations;
//	}
//	
//	@Override
//	public void display(RenderingPage page) {
//		
//		if (imaginaryField != null) {
//			page.add(imaginaryField, realField, updateButton);
//			imaginaryField.setVisible(true);
//			realField.setVisible(true);
//			updateButton.setVisible(true);
//			return;
//		}
//		
//		int textFieldX = 800;
//		int textFieldY = 400;
//		int yDist = 50;
//		
//		imaginaryField = new TextField("" + add.getImaginaryValue());
//		NumberDocument document = new NumberDocument(imaginaryField, true, true);
//		document.setLimit(10);
//		imaginaryField.setDocument(document);
//		imaginaryField.setBounds(textFieldX, textFieldY, 100, 30);
//		
//		realField = new TextField("" + add.getRealValue());
//		document = new NumberDocument(realField, true, true);
//		document.setLimit(10);
//		realField.setDocument(document);
//		realField.setBounds(textFieldX, textFieldY + yDist, 100, 30);
//		
//		updateButton = new Button("Update");
//		updateButton.setBounds(textFieldX - 10, textFieldY + yDist * 2, 120, 60);
//		updateButton.setOnClickListener(() -> {
//			try {
//				if (page.canUpdateImage()) {
//					page.updateImage();
//					checkInputs();
//				}
//			} catch (Exception exc) {}
//		});
//		
//		page.add(imaginaryField, realField, updateButton);
//	}
//	
//	private void checkInputs() throws Exception {
//
//		// imaginary and real values
//		double imaginary = Double.parseDouble(imaginaryField.getText());
//		double real = Double.parseDouble(realField.getText());
//		
//		add = new ComplexNumber(imaginary, real);
//	}
//
//}
