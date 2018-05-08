package drawing;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import geometry.CartesianCoordinate;
import geometry.LineSegment;
import java.awt.Color;
import java.awt.Paint;
/**
 * <h2>Canvas</h2> This class represents a canvas object that can be drawn to with various line segments.
 */
public class Canvas extends JPanel {
	private static final long serialVersionUID = 1L;
	private int xSize, ySize;
	private CopyOnWriteArrayList<LineSegment> lines;
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
		lines = new CopyOnWriteArrayList<LineSegment>();
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
		
		for (LineSegment line : lines) {
			g2.setPaint(line.getLineColor());
			g2.draw(new Line2D.Double(line.getStartPoint().getX(), line.getStartPoint().getY(), line.getEndPoint().getX(),
					line.getEndPoint().getY()));
		}
	}

	/**
	 * Draws a line between 2 CartesianCoordinates to the canvas.
	 * 
	 * @param startPoint
	 *            Starting coordinate.
	 * @param endPoint
	 *            Ending coordinate.
	 */
	public void drawLineBetweenPoints(CartesianCoordinate startPoint, CartesianCoordinate endPoint, Paint c) {
		LineSegment ls = new LineSegment(startPoint, endPoint);
		ls.setLineColor(c);
		lines.add(ls);
		repaint();
	}

	/**
	 * Draws a line segment to the canvas.
	 * 
	 * @param lineSegment
	 *            The LineSegment to draw.
	 */
	public void drawLineSegment(LineSegment lineSegment, Paint c) {
		lines.add(lineSegment);
		repaint();
	}

	/**
	 * Draws multiple line segments to the canvas.
	 * 
	 * @param lineSegments
	 *            An array of LineSegment.
	 */
	public void drawLineSegments(LineSegment[] lineSegments) {
		for (LineSegment thisLineSegment : lineSegments) {
			lines.add(thisLineSegment);
		}
		repaint();
	}

	/**
	 * Removes the most recently added line from the drawing.
	 */
	public void removeMostRecentLine() {
		if(lines.size() > 0) {
			lines.remove(lines.size() - 1);
		}
	}

	/**
	 * Clears the canvas of all drawing.
	 */
	public void clear() {
		lines.clear();
		repaint();
	}
	
	public CartesianCoordinate randomCoordinate() {
		int randomx = (int) (Math.random()*(this.getWidth()-30));
		int randomy = (int) (Math.random()*(this.getHeight()-30));
		return new CartesianCoordinate(randomx, randomy);
	}
}
