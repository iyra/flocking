package geometry;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.Paint;
import drawing.Canvas;
import geometry.Vector;

public class Circle extends ObjectShape {
	private int radius;
	
	public Circle(Canvas canvas, Vector posVec, int radius, Paint colour) {
		super.setPositionVec(posVec);
		super.setObjectSize(radius);
		this.canvas = canvas;
		this.radius = radius;
		this.colour = colour;
	}
	
	public void drawShape(Graphics2D g) {
		/* this Circle uses geom.Ellipse2D which can draw an ellipse,
		 * but a circle is just an ellipse with equal width and height
		 * (i.e both are the radius)
		 */
		g.setPaint(colour);
		g.draw(new Ellipse2D.Double(
				positionVec.getX(),
				positionVec.getY(),
				radius, radius));	
	}
	
	/* test if a particular point vector lies within this circle,
	 * which is useful for collision detection.
	 */
	@Override
	public boolean containsPoint(Vector point) {
		Ellipse2D myCircle = new Ellipse2D.Double(
				positionVec.getX(),
				positionVec.getY(),
				radius, radius);
		if(myCircle.contains(new Point2D.Double(point.getX(), point.getY()))) {
			return true;
		}
		return false;
	}
	
	/* circles are scaled only by reducing the radius */
	public void scaleShape(float factor) {
		radius = (int)(factor * radius);
	}
	
	
	
}