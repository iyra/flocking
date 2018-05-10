package geometry;

import java.util.concurrent.CopyOnWriteArrayList;

import drawing.Canvas;

public class Obstacle extends SimObject {
	private ObjectShape shape;
	private Canvas c;
	
	public Obstacle(ObjectShape shape) {
		/* simulation object shape is the shape we've just given */
		this.shape = shape;
		positionVec = shape.positionVec;
		c = shape.canvas;
		
		// obstacles don't move (yet):
		velocityVec = new Vector(0,0);
		objectSize = shape.objectSize;
	}
	
	@Override
	public void draw() {
		c.drawShape(shape);
	}
	
	@Override
	public void undraw() {
		c.removeMostRecentShape();
	}
	
	public boolean containsPoint(Vector point) {
		return shape.containsPoint(point);
	}
}
