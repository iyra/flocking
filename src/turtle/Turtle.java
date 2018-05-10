package turtle;

import drawing.Canvas;
import geometry.ObjectShape;
import java.awt.Paint;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.Color;
import geometry.SimObject;
import geometry.Vector;

public class Turtle extends SimObject {
	private ObjectShape shape;
	private Canvas c;
	
	public Turtle(Canvas myCanvas, ObjectShape objShape, Vector velocityVec, Vector accelerationVec) {
		canvas = myCanvas;
		
		/* the shape of the simulation object is the shape we give to the turtle */
		shape = objShape;
		shape.setObjectSize(0); // give a size of 0, because otherwise collision avoidance makes it jittery
		
		/* give the SimObject our velocity and acceleration */
		this.velocityVec = velocityVec;
		this.accelerationVec = accelerationVec;
		
		/* set the turtle speed initially */
		maxSpeed = 2;
		
		/* the initial position of the turtle will be the one of its shape */
		this.positionVec = shape.getPositionVec();
	}
	
	
	/* override draw and undraw to be those for the turtle's shape */
	@Override
	public void draw() {
		canvas.drawShape(shape);
	}
	
	@Override
	public void undraw() {
		canvas.removeMostRecentShape();
	}
	
	/* the turtle's update function
	 * in charge of moving the turtles around, and calculating the next
	 * place to move to.
	 */
	public void update(CopyOnWriteArrayList<SimObject> simObjects, double cohesionFactor, double alignmentFactor, double separationFactor, double maxSpeed) {
		this.maxSpeed = (int)maxSpeed;
		
		/* accelerate by adding the amount of acceleration to the velocity */
		velocityVec.add(accelerationVec);
		
		/* the acceleration may go over our maximum speed, so make sure that
		 * the maximum component of velocity in any direction is our speed
		 */
		//velocityVec.slow(0.01);
		
		/* collision detection
		 * go through each object, and see whether a potential movement (summing the position
		 * and velocity vectors) would land us in side an obstacle or not
		 */
		boolean canMove = true;
		for(SimObject obj : simObjects) {
			if(obj.getObjectSize() > 0) {
				if(obj.containsPoint(new Vector(positionVec.getX()+velocityVec.getX(), positionVec.getY()+velocityVec.getY()))) {
					System.out.println("can't move");
					obj.turtleCollision(this, simObjects);
					canMove = false;
				}
			}
		}
		
		
		if(canMove) {
			positionVec.add(velocityVec);
			
		} else {
			positionVec.add(new Vector(velocityVec.getX()+(Math.random()*2), velocityVec.getY()+(Math.random()*2)));
		}
		
		/* if acceleration continues to accumulate over each
		 * turtle then the turtles will keep getting faster and faster
		 * until maximum speed, so reset it here
		 */
		accelerationVec.zero();
		
		cohere(simObjects, cohesionFactor);
		align(simObjects, alignmentFactor);
		separate(simObjects, separationFactor);
		
		/* keep the turtles moving in some way by pushing them
		 * constantly in one direction
		 */
		positionVec.add(new Vector(1.2, 0.3));
		
		/* check that the turtle isn't at an edge, and if it is
		 * then "wrap" it back round
		 */
		wrap();
	}
	
	public void wrap() {
		/* check for hitting the edges
		 * when a turtle hits the edges of the screen, then bring them
		 * back to the opposite edge; for example, if it hits the extreme x
		 * side, then put it back at the same y position at x = 0
		 */
		if(positionVec.getX() > canvas.getWidth()) {
			positionVec.setX(0);
		} else if(positionVec.getX() < 0) {
			positionVec.setX(canvas.getWidth());
		}
		if(positionVec.getY() > canvas.getHeight()) {
			positionVec.setY(0);
		} else if(positionVec.getY() < 0) {
			positionVec.setY(canvas.getHeight());
		}
	}
	
	
	/* separation deals with turtles being forced in opposite
	 * directions from each other, and indeed from other obstacles
	 */
	public void separate(CopyOnWriteArrayList<SimObject> simObjects, double factor) {
		
		/* copy the current position into a new vector for later */
		Vector thisPosition = new Vector(positionVec.getX(), positionVec.getY());
		
		/* sumVelocity will become the direction which the turtles will
		 * head towards
		 */
		Vector sumVelocity = new Vector(0,0);
		
		int count = 0;
		for(SimObject obj : simObjects) {
			/* if the distance to each other object is more than 0
			 * (i.e we're not talking about our own object) and if the position
			 * of each one is closer than the radius of effect, then include it
			 * in our calculations.
			 * 
			 * this ignores any objects that are out of range.
			 */
			if(positionVec.dist(obj.getPositionVec()) > 0 && positionVec.dist(obj.getPositionVec()) + obj.getObjectSize() < 50) {
				
					/* the vector facing in the other direction to our turtle
					 * is the direction we want to head in, so subtract the velocity from our current position
					 */
					thisPosition.subtract(obj.getVelocity());
					
					/* add the calculated direction to the sum of
					 * velocities
					 */
					sumVelocity.add(thisPosition);
					count++;
			}
		}
		
		if(count > 0) {
			/* take the average */
			sumVelocity.divide((double)count);
			
			/* vector from this turtle to the average */
			//sumVelocity.subtract(positionVec);
			
			/* scale down the speed */
			sumVelocity.setMagnitude(maxSpeed);
			
			/* steering force = desired velocity - current velocity
			 * http://natureofcode.com/book/chapter-6-autonomous-agents/
			 */
			sumVelocity.subtract(getVelocity());
			
			/* limit the components of the velocity to be 0.01
			 * steering force in this case is very small */
			sumVelocity.slow(0.01); 
			
			sumVelocity.multiply(factor);
			
			/* accelerate towards the position */
			accelerationVec.add(sumVelocity);
		}
	}
	
	public void align(CopyOnWriteArrayList<SimObject> simObjects, double factor) {
		//Vector thisPosition = new Vector(positionVec.getX(), positionVec.getY());
		Vector sumVelocity = new Vector(0,0);
		int count = 0;
		
		for(SimObject obj : simObjects) {
			if(positionVec.dist(obj.getPositionVec()) > 0 && positionVec.dist(obj.getPositionVec()) < 50) {
					/* add the velocity of this object in the loop to a big one which we will 
					 * take the average of
					 */
					sumVelocity.add(obj.getVelocity());
					count++;
			}
		}
		
		if(count > 0) {
			/* take the average */
			sumVelocity.divide((double)count);
			
			/* vector from this turtle to the average */
			sumVelocity.subtract(positionVec);
			
			/* scale down the speed */
			sumVelocity.setMagnitude(maxSpeed);
			
			/* steering force = desired velocity - current velocity
			 * http://natureofcode.com/book/chapter-6-autonomous-agents/
			 */
			sumVelocity.subtract(getVelocity());
			sumVelocity.slow(0.01);
			
			sumVelocity.multiply(factor);
			
			/* accelerate towards the position */
			accelerationVec.add(sumVelocity);
		}
	}
	
	public void cohere(CopyOnWriteArrayList<SimObject> simObjects, double factor) {
		//Vector thisPosition = new Vector(positionVec.getX(), positionVec.getY());
		Vector sumDirection = new Vector(0,0);
		int count = 0;
		
		for(SimObject obj : simObjects) {
			if(positionVec.dist(obj.getPositionVec()) > 0 && positionVec.dist(obj.getPositionVec()) < 20) {
					sumDirection.add(obj.getPositionVec());
					count++;
			}
		}
		
		if(count > 0) {
			/* take the average */
			sumDirection.divide((double)count);
			
			/* vector from this turtle to the average */
			sumDirection.subtract(positionVec);
			
			/* scale down the speed */
			//sumDirection.setMagnitude(maxSpeed);
			
			/* steering force = desired velocity - current velocity
			 * http://natureofcode.com/book/chapter-6-autonomous-agents/
			 */
			sumDirection.subtract(getVelocity());
			sumDirection.slow(0.01);
			
			sumDirection.multiply(factor);
			
			/* accelerate towards the position */
			accelerationVec.add(sumDirection);
		}
	}
	
	/* The Food class will call this method to accelerate the turtle when
	 * needed; it accelerates the turtle by 'amount' but unlike regular
	 * acceleration it is not reset
	 */
	public void boostSpeed(double amount) {
		velocityVec.add(new Vector(maxSpeed/amount, maxSpeed/amount));
	}
	
}
