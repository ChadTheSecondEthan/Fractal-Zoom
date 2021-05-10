package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageButton extends Button {
	
	private BufferedImage image;
	
	public ImageButton(BufferedImage image, float imageScale) {
		super();
		this.image = image;
		width = (int) (image.getWidth() * imageScale);
		height = (int) (image.getHeight() * imageScale);
	}
	
	public ImageButton(BufferedImage image) {
		super();
		this.image = image;
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		g.drawImage(image, x, y, width, height, null);
	}

}
