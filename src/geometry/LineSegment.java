package geometry;
import java.awt.Paint;
import java.awt.Color;
import javax.swing.JPanel;

public class LineSegment {
	private CartesianCoordinate startPoint;
	private CartesianCoordinate endPoint;
	private Paint lineColor;
	
	public LineSegment( CartesianCoordinate a, CartesianCoordinate b ) { 
		startPoint = a;
		endPoint = b;
		lineColor = Color.black;
	}
	
	public Paint getLineColor() {
		return lineColor;
	}
	
	public void setLineColor(Paint c) {
		lineColor = c;
	}
	
	public CartesianCoordinate getStartPoint() {
		return startPoint;
	}
	
	public CartesianCoordinate getEndPoint() {
		return endPoint;
	}
	
	public double length() {
		return Math.sqrt(Math.pow(endPoint.getX() - startPoint.getX(), 2.0) + Math.pow(endPoint.getY() - startPoint.getY(), 2.0));
	}
	
	public String toString() {
		return "LineSegment start: " + startPoint.toString() + " and end: " + endPoint.toString() + " and length is "+length();
	}
}
