package turtle;

import drawing.Canvas;
import geometry.CartesianCoordinate;
import java.awt.Paint;
import java.awt.Color;
public class Turtle {
	private Canvas myCanvas;
	private boolean penIsDown;
	private double angle;
	private CartesianCoordinate position;
	
	public Turtle(Canvas myCanvas) {
		this.myCanvas = myCanvas;
		// TODO Auto-generated constructor stub
		position = new CartesianCoordinate(100,100);
		penIsDown = false;
		angle = 0;
	}

	/**
	 * The turtle is moved in its current direction for the given number of pixels. 
	 * If the pen is down when the robot moves, a line will be drawn on the floor.
	 * 
	 * @param i The number of pixels to move.
	 */
	public void move(int i, Paint lineColor) {
		// TODO Auto-generated method stub
		double end_x = (i * Math.sin(Math.toRadians(angle)))+position.getX();
		double end_y = -(i * Math.cos(Math.toRadians(angle)))+position.getY();
	
		CartesianCoordinate new_position = new CartesianCoordinate(end_x, end_y);
		
		//System.out.println("end point is "+new_position);
		
		if(penIsDown) {
			myCanvas.drawLineBetweenPoints(position, new_position, lineColor);
		}
		position = new_position;
	}
	
	public void draw() {
		turn(150);
		putPenDown();
		move(30, Color.red);
		turn(180-60);
		move(30, Color.black);
		turn(180-60);
		move(30, Color.red);
		turn(-30);
		putPenUp();
	}
	
	public void undraw() {
		for(int i = 0; i < 3; i++){
			myCanvas.removeMostRecentLine();
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
		if(angle > 360){
			angle = angle-360;
		}
		//System.out.println("angle is now "+angle);
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

	public CartesianCoordinate getPosition() {
		return position;
	}
	
	public void wrapPosition() {
		if(position.getX() >= myCanvas.getWidth()) {
			position.setX(0);
		}
		if(position.getY() >= myCanvas.getHeight()) {
			position.setY(0);
		}
		if(position.getY() < 0) {
			position.setY(myCanvas.getHeight());
		}
		if(position.getX() < 0) {
			position.setX(myCanvas.getWidth());
		}
	}
}
