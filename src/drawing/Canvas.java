package drawing;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JPanel;
import geometry.ObjectShape;
import geometry.Vector;

/**
 * <h2>Canvas</h2> This class represents a canvas object that can be drawn to with various line segments.
 */
public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private int xSize, ySize;
	private CopyOnWriteArrayList<ObjectShape> shapes;
	private final static int DEFAULT_X = 800;
	private final static int DEFAULT_Y = 600;

	/**
	 * Default constructor which produces a canvas of the default size of 800 x 600.
	 */
	public Canvas() {
		this(DEFAULT_X, DEFAULT_Y);
	}

	/**
	 * Constructor which produces a canvas of a specified size.
	 * 
	 * @param x
	 *            Width of the canvas.
	 * @param y
	 *            Height of the canvas.
	 */
	public Canvas(int x, int y) {
		xSize = x;
		ySize = y;
		setupCanvas();
		shapes = new CopyOnWriteArrayList<ObjectShape>();
	}

	private void setupCanvas() {
		setSize(xSize, ySize);
		setVisible(true);
		repaint();
	}

    /**
     * <b>NB: You never need to call this method yourself.</b>
     * It handles the drawing but is called automatically each
     * time a line segment is drawn.
     */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Smoother lines
		g2.setStroke(new BasicStroke(1));
		
		
		for(ObjectShape shape : shapes) {
			//System.out.println("making shape");
			shape.drawShape(g2);
		}
	}
	
	public void drawShape(ObjectShape shape) {
		shapes.add(shape);
		repaint();
	}
	
	public void drawShapes(ObjectShape[] shapeArray) {
		for (ObjectShape shape : shapeArray) {
			shapes.add(shape);
		}
		repaint();
	}
	
	public void removeMostRecentShape() {
		if(shapes.size() > 0) {
			shapes.remove(shapes.size() - 1);
		}
	}

	
	public Vector randomVector() {
		int randomx = (int) (Math.random()*(this.getWidth()-30));
		int randomy = (int) (Math.random()*(this.getHeight()-30));
		return new Vector(randomx, randomy);
	}
}
