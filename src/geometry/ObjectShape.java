package geometry;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Graphics2D;

public abstract class ObjectShape extends SimObject {
	protected Paint colour;
	public abstract void drawShape(Graphics2D g);
	
	public ObjectShape() {
		this.colour = Color.black;
	}
	
	
	public Paint getColour() {
		return colour;
	}
	
	public void setColour(Paint newColour) {
		colour = newColour;
	}
	
	@Override
	public void draw() {
		canvas.drawShape(this);
	}
	
	@Override
	public void undraw() {
		canvas.removeMostRecentShape();
	}
	
}
