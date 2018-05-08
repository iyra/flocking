import drawing.Canvas;
import javax.swing.JFrame;
import java.util.concurrent.CopyOnWriteArrayList; 
import turtle.DynamicTurtle;
import geometry.CartesianCoordinate;
import geometry.SimObject;
import geometry.Rect;
import geometry.Circle;
import geometry.Food;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Lab5Turtle {
	
	CopyOnWriteArrayList<SimObject> simObjects; 
	
	
	public Lab5Turtle() {
		simObjects = new CopyOnWriteArrayList<SimObject>();
		
		JFrame frame = new JFrame();
		Canvas canvas = new Canvas();
		frame.setTitle("Turtle Frame");
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(canvas);
		
		simObjects.add(new DynamicTurtle(canvas, 300,100)); 
		simObjects.add(new Food(new Circle(canvas, new CartesianCoordinate(200,200), 100, Color.cyan), 900, 3));
		for(int i = 0; i < 10; i++) {
			CartesianCoordinate position = canvas.randomCoordinate();
			simObjects.add(new DynamicTurtle(canvas, position.getX(), position.getY()));
		}
		
		// milliseconds
		int deltaTime = 20;
		boolean continueRunning = true;
		// game loop
		while (continueRunning) {
			
			for(int c = 0; c < simObjects.size(); c++) {
				simObjects.get(c).update(deltaTime, simObjects, c);
			}
			for(SimObject t : simObjects) {
				t.draw();
			}
			Utils.pause(deltaTime);

			for(SimObject t : simObjects) {
				t.undraw();
			}
			/*for(DynamicTurtle t : turtles ) {
				t.update(deltaTime);
				Utils.pause(deltaTime);
			}*/
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
		    		
		    	} else {
		    	    throw new IllegalArgumentException("Line `" + line + "` is not a valid setting.");
		    	}
		    }
		} catch(FileNotFoundException e) {
			
		} catch(IOException e) {
			
		}
	}

	public static void main(String[] args) {
		new Lab5Turtle();
	}
}
