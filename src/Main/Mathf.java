package Main;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class Mathf {
	
	public static double clamp(double min, double max, double value) {
		if (value > max) return max;
		if (value < min) return min;
		return value;
	}
	
	public static double lerp(double a, double b, double t) {
		return a + (b - a) * t;
	}
	
	public static Color lerpColor(Color a, Color b, double t) {
		int red = (int) Mathf.lerp(a.getRed(), b.getRed(), t);
		int green = (int) Mathf.lerp(a.getGreen(), b.getGreen(), t);
		int blue = (int) Mathf.lerp(a.getBlue(), b.getBlue(), t);
		return new Color(red, green, blue);
	}
	
	public static boolean pointWithinRect(Point p, Rectangle rect) {
		return p.x >= rect.x && 
				p.y >= rect.y && 
				p.x <= rect.x + rect.width && 
				p.y <= rect.y + rect.height;
	}

}
