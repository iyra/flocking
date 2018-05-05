import drawing.Canvas;
import javax.swing.JFrame;
import java.util.concurrent.CopyOnWriteArrayList; 
import turtle.DynamicTurtle;
import geometry.CartesianCoordinate;
public class Lab5Turtle {
	
	CopyOnWriteArrayList<DynamicTurtle> turtles; 
	
	
	public Lab5Turtle() {
		turtles = new CopyOnWriteArrayList<DynamicTurtle>();
		
		JFrame frame = new JFrame();
		Canvas canvas = new Canvas();
		frame.setTitle("Turtle Frame");
		frame.setSize(1920, 1080);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(canvas);
		
		turtles.add(new DynamicTurtle(canvas, 300,100)); 
		
		for(int i = 0; i < 20; i++) {
			CartesianCoordinate position = canvas.randomCoordinate();
			turtles.add(new DynamicTurtle(canvas, position.getX(), position.getY()));
		}
		
		// milliseconds
		int deltaTime = 20;
		boolean continueRunning = true;
		// game loop
		while (continueRunning) {
			for(DynamicTurtle t : turtles) {
				t.cohere(turtles, 50, 0.5, turtles.indexOf(t));
				t.align(turtles, 50, 0.1, turtles.indexOf(t));
				System.out.println("updating");
				t.update(deltaTime);
			}
			for(DynamicTurtle t : turtles) {
				t.draw();
			}
			Utils.pause(deltaTime);

			for(DynamicTurtle t : turtles) {
				t.undraw();
			}
			/*for(DynamicTurtle t : turtles ) {
				t.update(deltaTime);
				Utils.pause(deltaTime);
			}*/
		}
	}

	public static void main(String[] args) {
		new Lab5Turtle();
	}
}
