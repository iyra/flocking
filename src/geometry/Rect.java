package geometry;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.Point2D;
import java.awt.Paint;
import geometry.CartesianCoordinate;
import drawing.Canvas;

public class Rect extends ObjectShape {
	private int width;
	private int height;
	
	public Rect(Canvas canvas, CartesianCoordinate pos, int width, int height, Paint colour) {
		super.position = pos;
		this.canvas = canvas;
		this.width = width;
		this.height = height;
		this.colour = colour;
	}
	
	public void drawShape(Graphics2D g) {
		//System.out.println("x = " + getPosition().getX());
		g.setPaint(colour);
		g.draw(new Rectangle2D.Double(
				getPosition().getX(),
				getPosition().getY(),
				width, height));	
	}
	
	public boolean containsPoint(CartesianCoordinate point) {
		Rectangle2D myRect = new Rectangle2D.Double(
				getPosition().getX(),
				getPosition().getY(),
				width, height);
		if(myRect.contains(new Point2D.Double(point.getX(), point.getY()))) {
			return true;
		}
		return false;
	}
	
	public void scaleShape(float factor) {
		width = (int)(factor * width);
		height = (int)(factor * height);
	}
	
}
