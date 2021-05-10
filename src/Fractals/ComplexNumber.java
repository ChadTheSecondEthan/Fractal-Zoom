package Fractals;

public class ComplexNumber {
		
	private double x;
	private double y;
	
	public ComplexNumber(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public ComplexNumber(ComplexNumber c) {
		this.x = c.x;
		this.y = c.y;
	}
	
	public ComplexNumber squared() {
		return new ComplexNumber(2 * x * y, y * y - x * x);
	}
	
	public ComplexNumber thirdPower() {
		return new ComplexNumber(-x * x * x + 3 * x * y * y, -3 * x * x * y + y * y * y);
	}
	
	public ComplexNumber multiply(ComplexNumber other) {
		return new ComplexNumber(y * other.x + other.y * x, -x * other.x + y * other.y);
	}
	
	public ComplexNumber dividedBy(ComplexNumber other) {
		return new ComplexNumber(x / other.x, y / other.y);
	}
	
	public ComplexNumber add(ComplexNumber c) {
		x += c.x;
		y += c.y;
		return this;
	}
	
	public ComplexNumber add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public double dot(ComplexNumber other) {
		return x * other.x + y * other.y;
	}
	
	public void setRealValue(double value) { y = value; }
	public void setImaginaryValue(double value) { x = value; }
	
	public double getRealValue() { return y; }
	public double getImaginaryValue() { return x; }
	
	public void abs() {
		x = Math.abs(x);
		y = Math.abs(y);
	}
	
	public double sqrDistance() {
		return x * x + y * y;
	}
}