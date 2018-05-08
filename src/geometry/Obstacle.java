package geometry;

import turtle.DynamicTurtle;

public class Obstacle extends SimObject {
	boolean turtleAvoid = true;
	private ObjectShape shape;
	
	public Obstacle(ObjectShape shape) {
		this.shape = shape;
	}
	
	@Override
	public void draw() {
		canvas.drawShape(shape);
	}
	
	@Override
	public void undraw() {
		canvas.removeMostRecentShape();
	}
}
