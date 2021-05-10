package Main;

public class Time {
	
	private long start;
	private long end;

	public Time() {
		start = System.nanoTime();
		end = System.nanoTime();
	}
	
	public void reset() {
		start = System.nanoTime();
	}
	
	public float getElapsed() { 
		end = System.nanoTime();
		return (float) ((end - start) / 1000000000.0);
	}

}
