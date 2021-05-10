package UI;

import java.awt.image.BufferedImage;

public class Animation {
	
	private BufferedImage[] frames;
	private int currentFrame;
	
	private float timePassed;
	private float delay;
	
	private boolean playedOnce;

	public Animation() {
		playedOnce = false;
	}

	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		timePassed = 0;
		playedOnce = false;
	}
	
	public void setDelay(float millis) { delay = millis; }
	public void setFrame(int i) { currentFrame = i; }
	
	public void update(float dt) {
		if (delay == -1) return;
		
		timePassed += dt * 1000f;
		if (timePassed > delay) {
			currentFrame++;
			timePassed -= delay;
		}
		if (currentFrame == frames.length) {
			currentFrame = 0;
			playedOnce = true;
		}
	}
	
	public int getFrame() { return currentFrame; }
	public BufferedImage getImage() { return frames[currentFrame]; }
	public boolean hasPlayedOnce() { return playedOnce; }
	
}
