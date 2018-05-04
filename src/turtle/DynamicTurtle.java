package turtle;
import java.awt.Color;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import geometry.LineSegment;
import java.util.concurrent.CopyOnWriteArrayList;

public class DynamicTurtle extends Turtle {
	private int speed = 10;
	public DynamicTurtle(Canvas canvas) {
		super(canvas);
		//draw();
	}
	
	public DynamicTurtle(Canvas canvas, double startX, double startY) {
		super(canvas);
		CartesianCoordinate newPos = new CartesianCoordinate(startX, startY);
		
		turn(getPosition().turnAngle(newPos));
		move(getPosition().lengthTo(newPos), Color.black);
		//undraw();
		//draw();
	}

	/*private void draw() {
		// TODO Auto-generated method stub
	}*/
	
	public void update(int t) {
		//undraw();
		move(speed * t/1000, Color.black);
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
	
	public void cohere(CopyOnWriteArrayList<DynamicTurtle> turtles, int radius, double k_c, int index) {
		DynamicTurtle focusTurtle = this;
		CopyOnWriteArrayList<DynamicTurtle> includedTurtles = new CopyOnWriteArrayList<DynamicTurtle>();
		for(DynamicTurtle t : turtles) {
			LineSegment ls = new LineSegment(focusTurtle.getPosition(), t.getPosition());
			if((ls.length() <= radius) && (turtles.indexOf(t) != index)) {
				System.out.println(turtles.indexOf(t));
				includedTurtles.add(t);
			}
		}
		CartesianCoordinate centerPosition = centerOfMass(includedTurtles);
		int angleToTurn = getPosition().turnAngle(centerPosition);
		System.out.println("turning "+index+" to "+angleToTurn+" and the length is "+includedTurtles.size()+"index is"+index);
		turn((int)(k_c * angleToTurn));
	}
	
}