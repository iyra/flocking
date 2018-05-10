package geometry;
import java.util.concurrent.CopyOnWriteArrayList;
import drawing.Canvas;
import turtle.Turtle;

/* SimObject includes every thing that will be drawn to the JFrame
 * in this simulation
 */
public abstract class SimObject {
	/* objects have a position, velocity and shape */
	protected Canvas canvas; // the canvas we are going to draw this object to
	protected int objectSize = 0;
	protected Vector positionVec;
	protected Vector velocityVec;
	public Vector accelerationVec;
	protected int maxSpeed;
	

	public void update(CopyOnWriteArrayList<SimObject> simObjects, double cohesionFactor, double alignmentFactor, double separationFactor, double maxSpeed) {
	}
	
	
	public int getObjectSize() {
		return objectSize;
	}
	
	public void setObjectSize(int size) {
		objectSize = size;
	}

	
	public void setPositionVec(Vector newPos) {
		positionVec = newPos;
	}
	
	public Vector getPositionVec() {
		return positionVec;
	}
	
	
	public void setVelocity(Vector v) {
		velocityVec = v;
	}
	
	public void setVelocityX(double val) {
		velocityVec.setX(val);
	}
	
	public void setVelocityY(double val) {
		velocityVec.setY(val);
	}
	
	public Vector getVelocity() {
		return velocityVec;
	}
	
	/* dummy draw/undraw functions which will be specified
	 * by each object, as some objects may not be shapes
	 */
	public void draw() {}
	public void undraw() {}
	
	public void scaleShape(float factor) {
		
	}
	
	public boolean containsPoint(Vector p) {
		return false;
	}
	
	public void turtleCollision(Turtle myTurtle, CopyOnWriteArrayList<SimObject> simObjects) {
		
	}
	
}
