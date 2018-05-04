package turtle;
import java.awt.Color;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import geometry.LineSegment;

public class DynamicTurtle extends Turtle {
	private int speed = 500;
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
}