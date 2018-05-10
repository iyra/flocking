package geometry;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Graphics2D;

/* A class to contain the shapes we will use on the canvas */
public abstract class ObjectShape extends SimObject {
	
	/* shapes have a colour, and a drawing function
	 * which takes a Graphics2D object
	 */
	protected Paint colour;
	public abstract void drawShape(Graphics2D g);
	
	/* set black to be the default colour */
	public ObjectShape() {
		this.colour = Color.black;
	}
	
	public Paint getColour() {
		return colour;
	}
	
	public void setColour(Paint newColour) {
		colour = newColour;
	}
	
	/* all shapes have the same draw/undraw methods */
	@Override
	public void draw() {
		canvas.drawShape(this);
	}
	
	@Override
	public void undraw() {
		canvas.removeMostRecentShape();
	}
	
	/* a method to check whether a shape contains
	 * a particular point or not; this should be overridden
	 */
	public boolean containsPoint(Vector p) {
		return false;
	}
	
}
