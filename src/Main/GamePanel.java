package Main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import GameState.GameStateManager;
import UI.Buttons;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	// dimensions
	public static final int WIDTH = 960;
	public static final int HEIGHT = 960;

	// game thread
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;

	// image
	private BufferedImage image;
	private Graphics2D g;

	// game state manager
	private GameStateManager gsm;

	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		requestFocus();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			thread.setName("Game Loop Thread");
			thread.start();
		}
	}

	private void init() {

		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();

		running = true;
		
		gsm = new GameStateManager();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
	}

	public void run() {

		init();

		Time gameStateTime = new Time();
		Time waitTime = new Time();
		long wait;

		// game loop
		while (running) {
			
			gsm.update(gameStateTime.getElapsed());
			gameStateTime.reset();
			Input.update();
			
			draw();

			wait = targetTime - (int) (waitTime.getElapsed() * 1000);
			if (wait > 0)
				try {
					Thread.sleep(wait);
				} catch (Exception e) {
					e.printStackTrace();
				}
			waitTime.reset();
		}
	}

	private void draw() {
		clearScreen();
		gsm.draw(g);
		
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
	
	public void clearScreen() {
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		Input.keyPressed(e.getKeyCode());
		gsm.keyPressed(e.getKeyCode());
	}
	@Override
	public void keyReleased(KeyEvent e) {
		Input.keyReleased(e.getKeyCode());
	}
	@Override 
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if ((event.getModifiers() & InputEvent.BUTTON1_MASK) != 0) 
			gsm.mouseClick(event.getPoint());
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		gsm.mouseMoved(e.getPoint());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gsm.mousePress();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gsm.mouseRelease();
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		gsm.mouseWheelMoved(e.getWheelRotation());
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
