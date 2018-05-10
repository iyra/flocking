package geometry;
import drawing.Canvas;
import java.util.concurrent.CopyOnWriteArrayList;
import turtle.Turtle;

public class Food extends SimObject {
	private ObjectShape shape;
	double boost;
	int amount;
	private Canvas c;
	
	public Food(ObjectShape shape, double boost, int amount) {
		this.boost = boost;
		
		/* amounts of food less than 0 aren't allowed,
		 * so set a sensible default like 2
		 */
		if(amount <= 0) {
			amount = 2;
		} else {
			this.amount = amount;
		}
		c = shape.canvas;
		
		/* set the shape of the object to the one given */
		this.shape = shape;
		
		/* position of the food is the same */
		positionVec = shape.positionVec;
		
		// food doesn't move (yet):
		velocityVec = new Vector(0,0);
		objectSize = shape.objectSize;
	}
	
	@Override
	public void turtleCollision(Turtle myTurtle, CopyOnWriteArrayList<SimObject> simObjects) {
		/* when food is eaten, turtles should move faster, so boost it
		 * by the amount we gave it
		 */
		myTurtle.boostSpeed(boost);
		
		/* after the food has been eaten, it will either shrink, or
		 * it will disappear, depending on the amount. Set up a variable
		 * to hold the index of simObjects which holds this Food
		 */
		
		int removeIndex = -1;
		if(amount == 1) {
			for(int c = 0; c < simObjects.size(); c++) {
				if(this == simObjects.get(c)) {
					removeIndex = c;
				}
			}
			if(removeIndex != -1) {
				// if we actually found the index, then delete it from simObjects
				simObjects.remove(removeIndex);
			}
			undraw();
		} else {
			/* we have more than 1 piece of Food, so just make it shrink by a factor
			 * of 0.5
			 */
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
	
	
	public boolean containsPoint(Vector point) {
		return shape.containsPoint(point);
	}
}
