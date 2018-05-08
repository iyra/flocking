package geometry;

import turtle.DynamicTurtle;

public class Obstacle extends SimObject {
	boolean turtleAvoid = true;
	private ObjectShape shape;
	int boost;
	
	public Obstacle(ObjectShape shape, int boost) {
		this.shape = shape;
		this.boost = boost;
	}
	
	public void turtleCollision(DynamicTurtle t) {
		t.setSpeed(t.getSpeed() - boost);
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
