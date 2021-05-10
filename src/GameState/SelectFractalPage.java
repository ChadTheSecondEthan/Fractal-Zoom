package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import Fractals.*;
import Generation.Colorer;
import Generation.FractalRenderer;
import Main.GamePanel;
import UI.*;
import UI.Text.NumberDocument;
import UI.Text.TextField;

public class SelectFractalPage extends GameState implements Runnable {
	
	private final Point initialButtonPos = new Point(100, 100);
	private final int buttonWidth = 200;
	private final int buttonHeight = 100;
	private final int buttonYDist = 225;
	
	private final Point initialTextFieldPos = new Point(700, 200);
	private final int textFieldWidth = 200;
	private final int textFieldHeight = 50;
	private final int textFieldYDist = 125;
	
	private final int imageDrawSize = 200;
	private final int imageCalcSize = 100;
	
	private BufferedImage[] fractalImages;
	private Button[] fractalButtons;
	private TextField[] textFields;
	private String[] textFieldNames;

	private Font font;
	private Color color;
	
	public SelectFractalPage(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		/**
		 * Initialize font and color for drawing text
		 */
		
		font = new Font("Century Gothic", Font.BOLD, 24);
		color = Color.black;
		
		/**
		 * Initialize the images before the thread so they aren't null when drawn
		 */
		
		fractalImages = new BufferedImage[Fractal.NUM_FRACTALS];
		for (int i = 0; i < fractalImages.length; i++)
			fractalImages[i] = new BufferedImage(imageCalcSize, imageCalcSize, BufferedImage.TYPE_INT_RGB);
		
		/**
		 * Create buttons for each fractal
		 */
		
		String[] fractalNames = { "Mandelbrot", "Burning Ship", "Other" };
		final FractalInfo info = FractalRenderer.getInstance().getFractalInfo();
		
		fractalButtons = new Button[Fractal.NUM_FRACTALS];
		for (int i = 0; i < fractalButtons.length; i++) {
			
			final int num = i;
			
			Button button = new Button(fractalNames[i]);
			button.setBounds(initialButtonPos.x, initialButtonPos.y + buttonYDist * i, buttonWidth, buttonHeight);
			button.setOnClickListener(() -> {
				try {
					checkInputs();
				} catch (Exception exc) {
					if (exc instanceof FractalInfoInputException) 
						textFields[((FractalInfoInputException) exc).getTextField()].setColor(Color.red);
					return;
				}
				info.setFractal(Fractal.getFractal(num));
				gsm.setState(GameStateManager.RENDERING_PAGE);
			});
			add(button);
			
			fractalButtons[i] = button;
		}
		
		/**
		 * Add text fields to enter specific starting offsets, zooms, and iteration counts
		 */
		
		textFieldNames = new String[] { 
				"X Offset (" + -GamePanel.WIDTH / 2 + " to " + GamePanel.WIDTH / 2 + ")", 
				"Y Offset (" + -GamePanel.HEIGHT / 2 + " to " + GamePanel.HEIGHT / 2 + ")", 
				"Iterations (10 to " + FractalRenderer.MAX_ITERATIONS + ")", 
				"Zoom (0 to " + FractalRenderer.MAX_ZOOM_POWER + ")" };
		
		textFields = new TextField[textFieldNames.length];

		for (int i = 0; i < textFields.length; i++) {
			
			TextField textField = new TextField("");
			NumberDocument document = new NumberDocument(textField, true, true);
			document.setLimit(10);
			textField.setDocument(document);
			textField.setBounds(initialTextFieldPos.x, initialTextFieldPos.y + textFieldYDist * i, textFieldWidth, textFieldHeight);
			
			add(textField);
			textFields[i] = textField;
		}
		
		textFields[0].setText(info.getOffsetX() + "");
		textFields[1].setText(info.getOffsetY() + "");
		textFields[2].setText(info.getIterations() + "");
		textFields[3].setText(info.getZoomPower() + "");
		
		/**
		 * Add a button to reset all the values of the text fields
		 */
		Button resetButton = new Button("Reset Values");
		resetButton.setBounds(initialTextFieldPos.x + textFieldWidth / 2 - 100, 700, 200, 100);
		resetButton.setOnClickListener(() -> {
			
			info.setOffsetX(0);
			info.setOffsetY(0);
			info.setIterations(100);
			info.setZoomPower(0);
			
			textFields[0].setText(info.getOffsetX() + "");
			textFields[1].setText(info.getOffsetY() + "");
			textFields[2].setText(info.getIterations() + "");
			textFields[3].setText(info.getZoomPower() + "");
			
		});
		add(resetButton);
		
		/**
		 * Add back, instructions, and settings buttons
		 */
		add(Buttons.getBackButton(gsm::back));
		add(Buttons.getInstructionsButton(() -> gsm.setState(GameStateManager.INSTRUCTIONS_PAGE)));
		add(Buttons.getSettingsButton(() -> gsm.setState(GameStateManager.SETTINGS_PAGE)));
		
		/**
		 * Start the thread to render the images
		 */
		new Thread(this).start();
	}
	
	private void checkInputs() throws Exception {
		
		for (int i = 0; i < textFields.length; i++)
			textFields[i].setColor(Resources.defaultColor);
		
		FractalRenderer fr = FractalRenderer.getInstance();
		FractalInfo info = fr.getFractalInfo();
	
		// offset x and y
		try {
			double offsetX = Double.parseDouble(textFields[0].getText());
		
			if (Math.abs(offsetX) <= GamePanel.WIDTH / 2) 
				info.setOffsetX(offsetX);
			else 
				throw new FractalInfoInputException(0);
		} catch (Exception e) {
			throw new FractalInfoInputException(0);
		}
		
		try {
			double offsetY = Double.parseDouble(textFields[1].getText());
		
			if (Math.abs(offsetY) <= GamePanel.HEIGHT / 2) 
				info.setOffsetY(offsetY);
			else
				throw new FractalInfoInputException(1);
		} catch (Exception e) {
			throw new FractalInfoInputException(1);
		}
		
		// iterations
		try {
			int iterations = Integer.parseInt(textFields[2].getText());
			
			if (iterations >= 10 && iterations <= FractalRenderer.MAX_ITERATIONS)
				info.setIterations(iterations);
			else 
				throw new FractalInfoInputException(2);
		} catch (Exception e) {
			throw new FractalInfoInputException(2);
		}
		
		// zoom
		try {
			int zoomPower = Integer.parseInt(textFields[3].getText());
	
			if (zoomPower >= 0 && zoomPower <= FractalRenderer.MAX_ZOOM_POWER)
				info.setZoomPower(zoomPower);
			else
				throw new FractalInfoInputException(3);
		} catch (Exception e) {
			throw new FractalInfoInputException(3);
		}
	}
	
	@Override
	public void run() {
		
		FractalRenderer renderer = FractalRenderer.getInstance();
		FractalInfo prevInfo = renderer.getFractalInfo();
		FractalInfo info = new FractalInfo(null);
		renderer.setFractalInfo(info);
		info.setImageSize(imageCalcSize);
		
		for (int i = 0; i < fractalImages.length; i++) {
			Fractal fractal = Fractal.getFractal(i);
			info.setFractal(fractal);
			
			int[][] iterations = fractal.generateIterationCounts(info);
			int[][] rgbs = Colorer.getInstance().iterationsToColors(iterations, info.getIterations());
			
			for (int x = 0; x < imageCalcSize; x++)
				for (int y = 0; y < imageCalcSize; y++)
					fractalImages[i].setRGB(x, y, rgbs[x][y]);
		}
		
		renderer.setFractalInfo(prevInfo);
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		g.setColor(Color.white);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		/**
		 * Draw each image
		 */
		
		Point imagePos = new Point(initialButtonPos);
		imagePos.y -= (imageDrawSize - buttonHeight) / 2;
		imagePos.x += 300;
		
		for (int i = 0; i < fractalImages.length; i++) {
			g.drawImage(fractalImages[i], imagePos.x, imagePos.y + buttonYDist * i
					, imageDrawSize, imageDrawSize, null);
		}
		
		/**
		 * Draw text next to each of the text fields
		 */
		
		Point textPos = new Point(initialTextFieldPos);
		textPos.x += textFieldWidth / 2;
		textPos.y -= 30;
		
		g.setColor(color);
		g.setFont(font);
		
		for (int i = 0; i < textFieldNames.length; i++) 
			UI.drawCenteredText(g, textFieldNames[i], textPos.x, textPos.y + textFieldYDist * i);
		
		super.draw(g);
	}
	
	class FractalInfoInputException extends Exception {
		
		private int textField;
		
		public FractalInfoInputException(int textField) {
			this.textField = textField;
		}
		
		public int getTextField() {
			return textField;
		}
	}

}
