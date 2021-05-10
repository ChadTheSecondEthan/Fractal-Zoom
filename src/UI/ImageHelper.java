package UI;

import java.awt.image.BufferedImage;

public class ImageHelper {

	public static BufferedImage[] splitImage(BufferedImage image, int splitWidth, int splitHeight) {
		
		int imagesX = image.getWidth() / splitWidth;
		int imagesY = image.getHeight() / splitHeight;
		int dx = image.getWidth() / imagesX;
		int dy = image.getHeight() / imagesY;
		
		BufferedImage[] images = new BufferedImage[imagesX * imagesY];
		
		for (int y = 0; y < imagesY; y++) 
			for (int x = 0; x < imagesX; x++) 
				images[y * imagesX + x] = image.getSubimage(x * dx, y * dy, dx, dy);
		
		return images;
	}
	
	public static BufferedImage rotate(BufferedImage image, int quarterRotations) {
		
		BufferedImage newImage;
		int width = image.getWidth();
		int height = image.getHeight();
		
		switch (quarterRotations) {
			case 1:
				newImage = new BufferedImage(height, width, image.getType());
				for (int x = 0; x < height; x++) {
					for (int y = 0; y < width; y++) {
						newImage.setRGB(x, y, image.getRGB(y, height - 1 - x));
					}
				}
				break;
			case 2:
				newImage = new BufferedImage(width, height, image.getType());
				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						newImage.setRGB(x, y, image.getRGB(width - 1 - x, height - 1 - y));
					}
				}
				break;
			case 3:
				newImage = new BufferedImage(height, width, image.getType());
				for (int x = 0; x < height; x++) {
					for (int y = 0; y < width; y++) {
						newImage.setRGB(x, y, image.getRGB(width - 1 - y, x));
					}
				}
				break;
			default:
				return image;
		}
		
		return newImage;
	}
	
}
