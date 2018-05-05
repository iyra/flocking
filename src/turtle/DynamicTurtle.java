package turtle;
import java.awt.Color;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import geometry.LineSegment;
import java.util.concurrent.CopyOnWriteArrayList;

public class DynamicTurtle extends Turtle {
	private int speed = 400;
	public DynamicTurtle(Canvas canvas) {
		super(canvas);
		//draw();
	}
	
	public DynamicTurtle(Canvas canvas, double startX, double startY) {
		super(canvas);
		CartesianCoordinate newPos = new CartesianCoordinate(startX, startY);
		
		turn(getPosition().turnAngle(newPos));
		move(getPosition().lengthTo(newPos), Color.black);
		
		/* after moving to the initial position, give it a random angle  */
		turn(randomAngle());
		//undraw();
		//draw();
	}
	
	public int randomAngle() {
		return (int)Math.random()*360;
	}

	/*private void draw() {
		// TODO Auto-generated method stub
	}*/
	
	public void update(int t) {
		//undraw();
		move(speed * t/1000, Color.black);
		System.out.println("moving by "+speed*(t/1000));
		wrapPosition();
		//draw();
	}
	
	public CartesianCoordinate centerOfMass(CopyOnWriteArrayList<DynamicTurtle> turtles) {
		int totalX = 0;
		int totalY = 0;
		for(DynamicTurtle t : turtles) {
			totalX += t.getPosition().getX() - getPosition().getX();
			totalY += t.getPosition().getY() - getPosition().getY();
		}
		return new CartesianCoordinate(totalX/turtles.size(), totalY/turtles.size());
	}
	
	public double generalDirection(CopyOnWriteArrayList<DynamicTurtle> turtles) {
		double x = 0;
		double y = 0;
		for(DynamicTurtle t : turtles) {
			x += Math.cos(Math.toRadians(t.getAngle()));
			y += Math.sin(Math.toRadians(t.getAngle()));
		}
		return Math.toDegrees(Math.atan2(y, x));
	}
	
	public void cohere(CopyOnWriteArrayList<DynamicTurtle> turtles, int radius, double k_c, int index) {
		DynamicTurtle focusTurtle = this;
		CopyOnWriteArrayList<DynamicTurtle> includedTurtles = new CopyOnWriteArrayList<DynamicTurtle>();
		for(DynamicTurtle t : turtles) {
			LineSegment ls = new LineSegment(focusTurtle.getPosition(), t.getPosition());
			if((ls.length() <= radius) && (ls.length() > 30)) {
				System.out.println(turtles.indexOf(t));
				includedTurtles.add(t);
			}
		}
		if(includedTurtles.size() > 0) {
			CartesianCoordinate centerPosition = centerOfMass(includedTurtles);
			
			
			int angleToTurn = getPosition().turnAngle(centerPosition)/20;
			System.out.println("turning "+index+" to "+angleToTurn+" and the length is "+includedTurtles.size()+"index is"+index);
			turn((int)(k_c * angleToTurn));
		}
	}
	
	
	public void align(CopyOnWriteArrayList<DynamicTurtle> turtles, int radius, double k_c, int index) {
		DynamicTurtle focusTurtle = this;
		CopyOnWriteArrayList<DynamicTurtle> includedTurtles = new CopyOnWriteArrayList<DynamicTurtle>();
		for(DynamicTurtle t : turtles) {
			LineSegment ls = new LineSegment(focusTurtle.getPosition(), t.getPosition());
			if((ls.length() <= radius) && (ls.length() > 0)) {
				System.out.println(turtles.indexOf(t));
				includedTurtles.add(t);
			}
		}
		if(includedTurtles.size() > 0) {
			double dir = (360-focusTurtle.getAngle()) + generalDirection(includedTurtles);
			System.out.println("direction is "+generalDirection(includedTurtles));
			
			//System.out.println("turning "+index+" to "+angleToTurn+" and the length is "+includedTurtles.size()+"index is"+index);
			//turn((int)(k_c * dir/20));
			setAngle(k_c * dir/20);
		}
	}
	
}