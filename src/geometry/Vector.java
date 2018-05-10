package geometry;

public class Vector {
	private double x;
	private double y;
	
	public Vector(double xVal, double yVal) {
		x = xVal;
		y = yVal;
	}
	
	public Vector(Vector newVector) {
		x = newVector.getX();
		y = newVector.getY();
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double xVal) {
		x = xVal;
	}
	
	public void setY(double yVal) {
		y = yVal;
	}
	
	/* adding/subtracting vectors is equal to doing it
	 * to their components
	 */
	public void add(Vector v) {
		x = x + v.getX();
		y = y + v.getY();
	}
	
	public void subtract(Vector v) {
		x = x - v.getX();
		y = y - v.getY();
	}
	
	/* vector division and multiplication are not needed
	 * so implement scalar versions for these arithmetic
	 * functions
	 */
	public void divide(double scalar) {
		x = x/scalar;
		y = y/scalar;
	}
	
	public void multiply(double scalar) {
		x = x * scalar;
		y = y * scalar;
	}
	
	/* the distance from this vector to another one */
	public double dist(Vector v) {
		return Math.sqrt((v.getX()-x)*(v.getX()-x) + (v.getY()-y)*(v.getY()-y));
	}
	
	/* size of this vector */
	public double getMagnitude() {
		return Math.sqrt(x*x + y*y);
	}
	
	/* scale down the vector to obtain only its direction */
	public void normalise() {
		double m = getMagnitude();
		if(m != 0) {
			divide(m);
		}
	}
	
	public void setMagnitude(double m) {
		normalise();
		multiply(m);
	}
	
	// https://github.com/processing/processing-android/blob/master/core/src/processing/core/PVector.java#L841
	public void slow(double maximum) {
		if(x*x + y*y > maximum*maximum) {
			normalise();
			multiply(maximum);
		}
	}
	
	public void zero() {
		x = 0;
		y = 0;
	}
	
}
