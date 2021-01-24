import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.util.TimerTask;
import java.util.Timer; // note this is different from Timer in AnimatedGUI
import java.util.Arrays;
import java.util.ArrayList;

// NOTE: a text character is being used in place of the idea of a pixel in graphics

/**
 * This class manages the 2D array of components that contain individual text characters,
 * and the execution of thread code that periodically draws to make animation possible.
 * 
 * @author Dr Russell Campbell
 */
public class Panel2D {
	// CONSTANTS
	
	// Dimensions of the display and characters.
	private final static int PANEL_FONT_SIZE = 12; // size of text characters
	private final static int WIDTH = 80;  // units in number of characters
	private final static int HEIGHT = 60; // units in number of characters
	private final static String DEFAULT_PIXEL = "`";
	// These will be the limited set of characters we will use in the display.
	private final static String[] PIXELS = { " ", ".", "-", "=", "+", "*", "#" };
	// Takes too much time to dynamically create new Color instances,
	// so create a limited number of them at start and reuse them.
	private final static Color[] COLORS = {
		new Color(255, 0, 0),		// red
		new Color(0, 255, 0),		// green
		new Color(0, 0, 255),		// blue
		new Color(255, 175, 100),	// orange
		new Color(255, 255, 0),		// yellow
		new Color(255, 0, 255),		// magenta
		new Color(0, 255, 255),		// cyan
		new Color(100, 175, 255)	// light blue
	};
	// if you prefer to use named constants
	final static byte RED = 0;
	final static byte GREEN = 1;
	final static byte BLUE = 2;
	final static byte ORANGE = 3;
	final static byte YELLOW = 4;
	final static byte MAGENTA = 5;
	final static byte CYAN = 6;
	final static byte LIGHT_BLUE = 7;
	
	// CLASS VARIABLES
	
	// create a display for individually coloured characters
	private static JPanel jpanel = new JPanel();
	private static JTextArea[][] text = new JTextArea[HEIGHT][WIDTH];
	private static int[][] bitmap = new int[HEIGHT][WIDTH]; // elements are indices for PIXELS
	private static int[][] colors = new int[HEIGHT][WIDTH]; // elements are indices for COLORS
	// each character should be displayed with the same amount of space, so monospaced
	private static Font mono = new Font(Font.MONOSPACED, Font.PLAIN, PANEL_FONT_SIZE);
	// the number of milliseconds between JFrame repaint calls
	private static int millis;
	
	// a collection of objects to draw---change Box (element type) to the new shape you design
	private static ArrayList<Shape> actors;
	
	/**
	 * Initializes a JPanel and a 2D array of JTextAreas for displaying animation.
	 * The first characters filled into the 2D array are backticks (`) to help with debugging.
	 * The dimensions are set to be the correct size to show all JTextAreas in the 2D array.
	 */
	Panel2D() {
		// no layout management of JTextArea components
		jpanel.setLayout(null);
		
		// the 2D array of individual JTextAreas, each one controlling a coloured char
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				initPixel(x, y);
			}
		}
		
		for (int i = 0; i < HEIGHT; i++) {
			Arrays.fill(bitmap[i], 1);
		}
		
		setDimensions();
	}
	
	// setup an individual character and its colour
	private void initPixel(int x, int y) {
		// vertical flip
		int y_flip = HEIGHT - y - 1; 
		text[y_flip][x] = new JTextArea(1, 1);
		text[y_flip][x].setFont(mono);
		jpanel.add(text[y_flip][x]);
		// test initialization with backtick chars
		text[y_flip][x].setText(DEFAULT_PIXEL);
		text[y_flip][x].setBounds(
			x*PANEL_FONT_SIZE,
			y*PANEL_FONT_SIZE,
			PANEL_FONT_SIZE,
			PANEL_FONT_SIZE
		);
		// do not need user to interactively insert text
		text[y_flip][x].setFocusable(false);
		text[y_flip][x].setVisible(true);
	}
	
	// makes the JPanel the correct size for its JTextArea rows and columns
	private void setDimensions() {
		jpanel.setPreferredSize(
			new Dimension(
				PANEL_FONT_SIZE*WIDTH,
				PANEL_FONT_SIZE*HEIGHT + 3 // calculated height is about 3 pixels off
			)
		);
	}
	
	/**
	 * This method changes the shapes we might want to animate.
	 * 
	 * @param actors
	 *   A collection of shapes to be updated every <code>getMillis()*2</code>.
	 */
	public void setActors(ArrayList<Shape> actors) {
		this.actors = actors;
	}
	
	/* 
	 * Code that executes on a separate thread from our main program.
	 * We need this so that repainting frames for animation are not
	 * dependant on slow calculations used to draw in the frame.
	 */ 
	public void schedulePanelUpdates(JFrame frame, int millis) {
		this.millis = millis;
		
		// For asynchronous updating of the text and colour data within the JPanel.
		// If slow, these will get in the way of regular repainting, and then lagged animation.
		Panel2D self = this;
		// an example of writing a class inline where it is used.
		TimerTask updater = new TimerTask() {
			// WARNING: avoid keyword "new", to animate fast enough avoid memory operations!
			public void run() {
				
				// draw each actor
				for (Shape shape : actors) {
					shape.draw();
				}
				
				// modify the main display
				jpanel.setEnabled(false); // disable jpanel interaction
				for (int x = 0; x < WIDTH; x++) {
					for (int y = 0; y < HEIGHT; y++) {
						// notice using keyword "this" would refer to updater, not this Panel2D
						self.updatePixel(x, y);
					}
				}
				jpanel.setEnabled(true); // enable jpanel interaction
				
				// Clear the old data that was just used to display,
				// so we have a fresh display to draw with next time.
				// This is much faster than creating new objects every frame.
				for (int i = 0; i < HEIGHT; i++) {
						Arrays.fill(bitmap[i], 0);
						Arrays.fill(colors[i], 0);
				}
			}
		};
		
		// Use the util package, so that it places execution on a separate thread from the EDT.
		new Timer().schedule(updater, 0, millis*2);
	}
	
	// takes care of changing text and color inside JTextArea at position (x, y)
	private void updatePixel(int x, int y) {
		JTextArea temp = getJTextArea(x, y);
		temp.setText( getString(x, y) );
		temp.setForeground( getBitmapColor(x, y) );
	}
	
	/**
	 * Assign a char value to an <code>(x, y)</code> position along with its colour.
	 * 
	 * @param x 
	 *   The horizontal position inside the 2D array of JTextAreas.
	 * @param y 
	 *   The vertical position inside the 2D array of JTextAreas.
	 * @param pixelsIndex 
	 *   The index for <code>PIXELS</code> array to be stored within the 
	 *   <code>bitmap</code> 2D array.
	 * @param colorsIndex
	 *   The index for <code>COLORS</code> array to be stored within the
	 *   <code>colors</code> 2D array.
	 */
	public void set(int x, int y, int pixelsIndex, int colorsIndex) {
		setText(x, y, pixelsIndex);
		setBitmapColor(x, y, colorsIndex);
	}
	
	// Assign a char value to an (x, y) position within the jpanel 2D array of JTextAreas.
	private void setText(int x, int y, int pixelsIndex) {
		// check if the point (x, y) is outside the 2D array boundaries
		y = HEIGHT - y - 1;
		if (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT)
			bitmap[y][x] = pixelsIndex;
	}
	
	// set the index of the Color used in the jpanel at position (x, y)
	private void setBitmapColor(int x, int y, int colorsIndex) {
		// check if the point (x, y) is outside the 2D array boundaries
		y = HEIGHT - y - 1;
		if (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT)
			colors[y][x] = colorsIndex;
	}
	
	/**
	 * Avoids using floating-point arithmetic for a faster drawing of a line.
	 * 
	 * @param first 
	 *   The start position of the straight line.
	 * @param second 
	 *   The end position of the straight line.
	 * @param pixelsIndex 
	 *   The index of the <code>PIXELS</code> character used to draw the line.
	 * @param colorsIndex
	 *   The index for which of the <code>COLORS</code> is used to draw the line.
	 */
	public void drawLine(Point2D first, Point2D second, int pixelsIndex, int colorsIndex) {

		// Bresenham's Line Algorithm
		int x1 = first.x;
		int y1 = first.y;
		int x2 = second.x;
		int y2 = second.y;
		byte stepx, stepy;

		int dx = x2 - x1;
		int dy = y2 - y1;

		// Simplify keeping track of distance by removing direction (sign)
		// from the amount of changes in position for each step.
		// Let the direction be taken care of with step variables.
		if (dy < 0) { dy = -dy; stepy = -1; } else { stepy = 1; }
		if (dx < 0) { dx = -dx; stepx = -1; } else { stepx = 1; }
		dy <<= 1; // dy is now 2*dy
		dx <<= 1; // dx is now 2*dx
		set(x1, y1, pixelsIndex, colorsIndex);

		// the algorithm is simplified by ensuring slope m is always -1 < m < 1
		if (dx > dy) {
			int error = dy - (dx >> 1);
			while (x1 != x2) {
				x1 += stepx;
				error += dy;
				if (error >= 0) {
					y1 += stepy;
					error -= dx;
				}
				set(x1, y1, pixelsIndex, colorsIndex);
			}
		} else { // but this means we may have to swap roles of dy and dx
			int error = dx - (dy >> 1);
			while (y1 != y2) {
				y1 += stepy;
				error += dx;
				if (error >= 0) {
					x1 += stepx;
					error -= dy;
				}
				set(x1, y1, pixelsIndex, colorsIndex);
			}
		}
	} // end of drawLine method
	
	// GETTER METHODS
	
	/* 
	 * The JTextAreas add yet another layer of design between data and output
	 * so, other classes should not be aware of this extra design separation.
	 */
	private JTextArea getJTextArea(int x, int y) {
		return text[y][x];
	}
	
	// return the character (as a String) used in position (x, y) within the jpanel array
	private String getString(int x, int y) {
		return PIXELS[ bitmap[HEIGHT - y - 1][x] ];
	}
	
	// return the Color used in position (x, y) within the jpanel array
	private Color getBitmapColor(int x, int y) {
		return COLORS[ colors[HEIGHT - y - 1][x] ];
	}
	
	/**
	 * Get the JPanel <code>jpanel</code> container which holds all JTextAreas.
	 * 
	 * @return
	 *  The JPanel that contains the 2D array of JTextAreas.
	 */
	public JPanel getJPanel() {
		return jpanel;
	}
	
	/**
	 * Get the <code>WIDTH</code>, the number of JTextArea columns.
	 * 
	 * @return 
	 *   The number of JTextArea columns in the JPanel.
	 */
	public int getWidth() {
		return WIDTH;
	}
	
	/**
	 * Get the <code>HEIGHT</code>, the number of JTextArea rows.
	 * 
	 * @return 
	 *   The number of JTextArea rows in the JPanel.
	 */
	public int getHeight() {
		return HEIGHT;
	}
	
	/**
	 * Get the the number of milliseconds between each repaint of the JFrame.
	 * 
	 * @return 
	 *   The number of milliseconds between each JFrame repaint.
	 */
	public int getMillis() {
		return millis;
	}
}