package Generation;

public abstract class ColorMode {
	
	public static final int ONE_COLOR = 0;
	public static final int MULTI_COLOR = 1;
	public static final int NUM_MODES = 2;
	
	public ColorMode() {}
	
	public abstract int[][] iterationsToColors(int[][] iterations, int maxIterations);
	public abstract void display();
	public abstract void removeDisplay();

}
