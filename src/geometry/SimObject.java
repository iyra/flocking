package geometry;

import java.util.concurrent.CopyOnWriteArrayList;

import drawing.Canvas;
import turtle.DynamicTurtle;

public abstract class SimObject {
	protected CartesianCoordinate position; // https://stackoverflow.com/questions/215497/in-java-difference-between-package-private-public-protected-and-private
	protected Canvas canvas;
	
	public void update(int t, CopyOnWriteArrayList<SimObject> simObjects, int index) {
	}
	
	public double getAngle() {
		return 0;
	}
	
	public void setPosition(CartesianCoordinate newPos) {
		position = newPos;
	}
	
	public CartesianCoordinate getPosition() {
		return position;
	}
	
	public void draw() {}
	public void undraw() {}
	
	public void scaleShape(float factor) {
		
	}
	
	public boolean containsPoint(CartesianCoordinate p) {
		return false;
	}
	
	public void turtleCollision(DynamicTurtle t, CopyOnWriteArrayList<SimObject> simObjects) {
		
	}
	
}
