import drawing.Canvas;
import javax.swing.JFrame;
import java.util.concurrent.CopyOnWriteArrayList; 
import geometry.SimObject;
import geometry.Vector;
import geometry.Rect;
import geometry.Circle;
import geometry.Food;
import geometry.ObjectShape;
import geometry.Obstacle;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import turtle.Turtle;
public class TurtleSim {
	
	/* Array of objects that will be drawn to the canvas
	 * in the main simulation loop.
	 */
	CopyOnWriteArrayList<SimObject> simObjects; 
	private int numTurtles = 150;
	private double cohesionFactor, alignmentFactor, separationFactor, maxSpeed;
	
	public TurtleSim() {
		cohesionFactor = 0.3;
		alignmentFactor = 0.2;
		separationFactor = 0.8;
		maxSpeed = 3;
		ReadSettings("xyz.txt");
		simObjects = new CopyOnWriteArrayList<SimObject>();
		
		/* Set up the simulation window */
		JFrame frame = new JFrame();
		Canvas canvas = new Canvas();
		frame.setTitle("Turtle Simulation Frame");
		frame.setSize(900, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(canvas);
		
		/* Add some obstacles and food for the turtles */
		simObjects.add(new Food(new Circle(canvas, new Vector(200, 400), 50, Color.pink), 0.01, 2));
		simObjects.add(new Food(new Rect(canvas, new Vector(10, 700), 600, 5, Color.getHSBColor(190, 40, 68)), 0.2, 2));
		simObjects.add(new Obstacle(new Circle(canvas, new Vector(500, 500), 50, Color.magenta)));
		simObjects.add(new Obstacle(new Rect(canvas, new Vector(600, 20), 70, 50, Color.cyan)));
		
		for(int i = 0; i < numTurtles; i++) {
			Vector position = canvas.randomVector();
			double randomAngle = (Math.random()*2 - 1);
			simObjects.add(new Turtle(canvas, new Circle(canvas, position, 20, Color.blue), new Vector(randomAngle, randomAngle), new Vector(0.1,0.2)));
		}
		
		// milliseconds
		int deltaTime = 10;
		boolean continueRunning = true;
		// game loop
		while (continueRunning) {
			
			for(int c = 0; c < simObjects.size(); c++) {
				simObjects.get(c).update(simObjects, cohesionFactor, alignmentFactor, separationFactor, maxSpeed);
				}
			for(SimObject t : simObjects) {
				t.draw();
			}
			Utils.pause(deltaTime);

			for(SimObject t : simObjects) {
				t.undraw();
			}
		}
	}
	
	private void ReadSettings(String filename) {
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if (line.contains("=")) {
		    		String[] parts = line.split("=");
		    		if(parts.length > 2) {
		    			throw new IllegalArgumentException("Line `" + line + "` is not a valid setting.");
		    		}
		    		if(parts[0].equals("turtles")) {
		    			try {
		    				numTurtles = Integer.parseInt(parts[1]);
		    			}
		    			catch (NumberFormatException e) {
		    				throw new IllegalArgumentException("Line `" + line + "` did not have a valid number of turtles to add.");
		    			}
		    		}
		    		
		    		if(parts[0].equals("cohesion")) {
		    			try {
		    				cohesionFactor = Double.parseDouble(parts[1]);
		    				if(cohesionFactor < 0  || cohesionFactor > 1) {
		    					throw new IllegalArgumentException("Line `" + line + "` did not have a valid cohesion factor.");
		    				}
		    			}
		    			catch (NumberFormatException e) {
		    				throw new IllegalArgumentException("Line `" + line + "` did not have a valid cohesion factor.");
		    			}
		    		}
		    		
		    		if(parts[0].equals("separation")) {
		    			try {
		    				separationFactor = Double.parseDouble(parts[1]);
		    				if(separationFactor < 0  || separationFactor > 1) {
		    					throw new IllegalArgumentException("Line `" + line + "` did not have a valid separation factor.");
		    				}
		    			}
		    			catch (NumberFormatException e) {
		    				throw new IllegalArgumentException("Line `" + line + "` did not have a valid separation factor.");
		    			}
		    		}
		    		
		    		if(parts[0].equals("alignment")) {
		    			try {
		    				alignmentFactor = Double.parseDouble(parts[1]);
		    				if(alignmentFactor < 0  || alignmentFactor > 1) {
		    					throw new IllegalArgumentException("Line `" + line + "` did not have a valid alignment factor.");
		    				}
		    			}
		    			catch (NumberFormatException e) {
		    				throw new IllegalArgumentException("Line `" + line + "` did not have a valid alignment factor.");
		    			}
		    		}
		    		
		    		if(parts[0].equals("speed")) {
		    			try {
		    				maxSpeed = Double.parseDouble(parts[1]);
		    				if(maxSpeed < 0  || maxSpeed > 1) {
		    					throw new IllegalArgumentException("Line `" + line + "` did not have a valid maximum speed.");
		    				}
		    			}
		    			catch (NumberFormatException e) {
		    				throw new IllegalArgumentException("Line `" + line + "` did not have a valid maximum speed.");
		    			}
		    		}
		    	} else {
		    	    throw new IllegalArgumentException("Line `" + line + "` is not a valid setting.");
		    	}
		    }
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new TurtleSim();
	}
}
