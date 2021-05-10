package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import Fractals.FractalInfo;
import Generation.Colorer;
import Generation.FractalRenderer;
import Main.GamePanel;
import Main.Input;
import UI.Buttons;
import UI.Resources;
import UI.UI;

public class RenderingPage extends GameState implements Runnable {
	
	private FractalRenderer fr;
	private FractalInfo info;
	private BufferedImage image;
	private Thread updateThread;
	private int[][] iterations;
	
	private boolean hideStats;
	private double calcTime;

	public RenderingPage(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {
		
		fr = FractalRenderer.getInstance();
		info = fr.getFractalInfo();
		if (image == null)
			image = new BufferedImage(GamePanel.WIDTH, GamePanel.HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		hideStats = false;
		
		info.getFractal().display(this);
		
		add(Buttons.getBackButton(gsm::back));
		add(Buttons.getInstructionsButton(() -> gsm.setState(GameStateManager.INSTRUCTIONS_PAGE)));
		add(Buttons.getSettingsButton(() -> gsm.setState(GameStateManager.SETTINGS_PAGE)));
		
		updateImage();
	}
	
	@Override
	public void run() {
		
		long startTime = System.nanoTime();
		
		FractalRenderer renderer = FractalRenderer.getInstance();
		FractalInfo info = renderer.getFractalInfo();
		info.reset();
		
		iterations = info.getFractal().generateIterationCounts(info);
		updateImageColors();
		
		calcTime = (System.nanoTime() - startTime) / 1000000.0;
		
		updateThread = null;
	}

	@Override
	public void draw(Graphics2D g) {
		
		g.drawImage(image, 0, 0, GamePanel.WIDTH, GamePanel.HEIGHT, null);

		g.setFont(Resources.defaultFont);
		g.setColor(Color.red);

		int textX = 25;
		int textY = 90;
		
		if (!hideStats) {
			g.drawString(String.format("Time to calculate: %4.2f ms", calcTime), textX, textY);
			g.drawString(String.format("Zoom Power: %d", info.getZoomPower()), textX, textY + 30);
			g.drawString(String.format("Offset: %f, %f", info.getOffsetX(), info.getOffsetY()), textX, textY + 60);
			g.drawString(String.format("Iterations: %d", info.getIterations()), textX, textY + 90);
		}

		if (!canUpdateImage())
			UI.drawCenteredText(g, "Calculating...", GamePanel.HEIGHT - 50);
		
		super.draw(g);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		if (Input.getKeyDown(KeyEvent.VK_S)) 
			saveImage();
		else if (Input.getKey(KeyEvent.VK_UP))
			info.setIterations(info.getIterations() + 10);
		else if (Input.getKey(KeyEvent.VK_DOWN))
			info.setIterations(info.getIterations() - 10);
		else if (Input.getKey(KeyEvent.VK_ENTER) && canUpdateImage())
			updateImage();
		else if (Input.getKey(KeyEvent.VK_H))
			hideStats = !hideStats;
	}

	@Override
	public boolean mouseClick(Point point) {
		if (super.mouseClick(point)) return true;
		if (canUpdateImage()) {
			info.addOffset(point);
			updateImage();
			return true;
		}
		return false;
	}

	@Override
	public void mouseWheelMoved(int wheelRotation) {
		if (canUpdateImage()) {
			if (wheelRotation < 1) info.zoomIn();
			else info.zoomOut();
		}
	}
	
	public boolean canUpdateImage() { return updateThread == null; }
	
	public void updateImage() {
		updateThread = new Thread(this);
		updateThread.start();
	}

	private void saveImage() {
		try {
			File dir = new File("FractalPics");
			if (!dir.exists())
				dir.mkdir();
			
			String pathPrefix = "FractalPics/saveFile";
			String pathSuffix = ".png";
			File file = new File(pathPrefix + pathSuffix);

			if (!file.createNewFile()) {

				int number = 1;

				while (!file.createNewFile()) {
					file = new File(pathPrefix + "(" + number + ")" + pathSuffix);
					number++;
				}
			}
			
			ImageIO.write(image, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void updateImageColors() {
		int[][] rgbs = Colorer.getInstance().iterationsToColors(iterations, FractalRenderer.getInstance().getFractalInfo().getIterations());
		
		for (int x = 0; x < info.getImageSize(); x++)
			for (int y = 0; y < info.getImageSize(); y++)
				image.setRGB(x, y, rgbs[x][y]);
	}

}
