package turtle;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import java.awt.Paint;
import java.awt.Color;
import geometry.SimObject;

public class Turtle extends SimObject {
	
	private boolean penIsDown;
	private double angle;
	//private CartesianCoordinate position;
	
	public Turtle(Canvas myCanvas) {
		this.canvas = myCanvas;
		// TODO Auto-generated constructor stub
		setPosition(new CartesianCoordinate(100,100));
		penIsDown = false;
		angle = 0;
	}
	
	public CartesianCoordinate nextCoordinate(int distance) {
		double end_x = (distance * Math.sin(Math.toRadians(angle)))+position.getX();
		double end_y = -(distance * Math.cos(Math.toRadians(angle)))+position.getY();
	
		return new CartesianCoordinate(end_x, end_y);
	}

	/**
	 * The turtle is moved in its current direction for the given number of pixels. 
	 * If the pen is down when the robot moves, a line will be drawn on the floor.
	 * 
	 * @param i The number of pixels to move.
	 */
	public void move(int i, Paint lineColor) {
		// TODO Auto-generated method stub
		
		CartesianCoordinate new_position = nextCoordinate(i);
		
		//System.out.println("end point is "+new_position);
		
		if(penIsDown) {
			canvas.drawLineBetweenPoints(getPosition(), new_position, lineColor);
		}
		position = new_position;
	}
	
	public void draw() {
		putPenDown();
		move((int)(15 * Math.sqrt(3)/2), Color.blue);
		turn(150);
		putPenDown();
		move(30, Color.red);
		turn(180-60);
		move(30, Color.black);
		turn(180-60);
		move(30, Color.red);
		turn(180-30);
		putPenUp();
		move((int)(15 *Math.sqrt(3)/2), Color.black);
		turn(180);
		putPenUp();
	}
	
	
	
	public void undraw() {
		for(int i = 0; i < 4; i++){
			canvas.removeMostRecentLine();
		}
	}

	/**
	 * Rotates the turtle clockwise by the specified angle in degrees.
	 * 
	 * @param i The number of degrees to turn.
	 */
	public void turn(int i) {
		// TODO Auto-generated method stub
		angle += i;
		while(angle > 180){
			angle = angle-360;
		}
		while(angle < -180){
			angle = angle+360;
		}
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setAngle(double a) {
		angle = a;
	}

	/**
	 * Moves the pen off the canvas so that the turtle’s route isn’t drawn for any subsequent movements.
	 */
	public void putPenUp() {
		// TODO Auto-generated method stub
		penIsDown = false;
	}

	/**
	 * Lowers the pen onto the canvas so that the turtle’s route is drawn.
	 */
	public void putPenDown() {
		// TODO Auto-generated method stub
		penIsDown = true;
	}

	
	public void wrapPosition() {
		if(position.getX() >= canvas.getWidth()) {
			position.setX(0);
		}
		if(position.getY() >= canvas.getHeight()) {
			position.setY(0);
		}
		if(position.getY() < 0) {
			position.setY(canvas.getHeight());
		}
		if(position.getX() < 0) {
			position.setX(canvas.getWidth());
		}
	}
}
