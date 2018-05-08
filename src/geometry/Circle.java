package geometry;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.Paint;
import geometry.CartesianCoordinate;
import drawing.Canvas;

public class Circle extends ObjectShape {
	private int radius;
	
	public Circle(Canvas canvas, CartesianCoordinate pos, int radius, Paint colour) {
		super.position = pos;
		this.canvas = canvas;
		this.radius = radius;
		this.colour = colour;
	}
	
	public void drawShape(Graphics2D g) {
		//System.out.println("x = " + getPosition().getX());
		g.setPaint(colour);
		g.draw(new Ellipse2D.Double(
				getPosition().getX(),
				getPosition().getY(),
				radius, radius));	
	}
	
	public boolean containsPoint(CartesianCoordinate point) {
		Ellipse2D myCircle = new Ellipse2D.Double(
				getPosition().getX(),
				getPosition().getY(),
				radius, radius);
		if(myCircle.contains(new Point2D.Double(point.getX(), point.getY()))) {
			return true;
		}
		return false;
	}
	
	public void scaleShape(float factor) {
		radius = (int)(factor * radius);
	}
	
}