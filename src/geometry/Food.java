package geometry;
import drawing.Canvas;
import java.util.concurrent.CopyOnWriteArrayList;

import turtle.DynamicTurtle;

public class Food extends SimObject {
	boolean turtleAvoid = false;
	private ObjectShape shape;
	int boost;
	int amount;
	private Canvas c;
	
	public Food(ObjectShape shape, int boost, int amount) {
		this.shape = shape;
		position = shape.position;
		this.boost = boost;
		if(amount <= 0) {
			amount = 2;
		} else {
			this.amount = amount;
		}
		c = shape.canvas;
	}
	
	public void turtleCollision(DynamicTurtle t, CopyOnWriteArrayList<SimObject> simObjects) {
		t.setSpeed(t.getSpeed() + boost);
		int removeIndex = -1;
		if(amount == 1) {
			for(int c = 0; c < simObjects.size(); c++) {
				if(this == simObjects.get(c)) {
					removeIndex = c;
				}
			}
			if(removeIndex != -1) {
				simObjects.remove(removeIndex);
			}
			undraw();
		} else {
			shape.scaleShape((float)0.5);
			amount--;
		}
	}
	
	@Override
	public void draw() {
		//System.out.println(c);
		c.drawShape(shape);
	}
	
	@Override
	public void undraw() {
		c.removeMostRecentShape();
	}
	
	
	public boolean containsPoint(CartesianCoordinate point) {
		return shape.containsPoint(point);
	}
}
