package geometry; 
public class CartesianCoordinate { 
	private double xPosition ; 
	private double yPosition ;

	public CartesianCoordinate( double x ,  double y ) { 
		xPosition =  x ;  yPosition =  y ;  
	}  
	
	public double getX() {
		return xPosition;
	}
	
	public double getY() {
		return yPosition;
	}
	
	public void setX(double newx) {
		xPosition = newx;
	}
	
	public void setY(double newy) {
		yPosition = newy;
	}
	
	public int turnAngle(CartesianCoordinate newPos) {
		double diff_y = yPosition-newPos.getY();
		double diff_x = xPosition-newPos.getX();
		double t_angle = 90 + Math.toDegrees(Math.atan(diff_y/diff_x));
		return (int)t_angle;
	}
	
	public int lengthTo(CartesianCoordinate newPos) {
		return (int)(new LineSegment(this, newPos)).length();
	}
	
	public String toString() {
		return "("+xPosition+", "+yPosition+")";
	}
	
}