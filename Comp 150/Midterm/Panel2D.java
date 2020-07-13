import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.util.TimerTask;
import java.util.Timer; // note this is different from Timer in AnimatedGUI
import java.util.Arrays;
import java.util.ArrayList;

// NOTE: a text character is being used in place of the idea of a pixel in graphics

/**
 * This class manages the JTextArea component that contains a 2D array of text characters,
 * and the execution of thread code that periodically draws to make animation possible.
 * 
 * @author Dr Russell Campbell
 */
public class Panel2D {
	
	private final static int PANEL_FONT_SIZE = 12;
	private final static int WIDTH = 100;
	private final static int HEIGHT = 50;
	private final static char DEFAULT_PIXEL = '`';
	private final static char[] PIXELS = { ' ', '.', '-', '|', '@', '*', '=' };
	
	private static JPanel jpanel = new JPanel();
	private static JTextArea text = new JTextArea();
	private static StringBuilder output = new StringBuilder();
	private static int[][] bitmap = new int[HEIGHT][WIDTH];
	private static Font mono = new Font(Font.MONOSPACED, Font.PLAIN, PANEL_FONT_SIZE);
	
	private static ArrayList<RoseCurve> actors;
	
	/**
	 * Initializes a JPanel and a JTextArea for displaying text characters for animation.
	 * The dimensions are set to be the correct size to show the JTextArea within the JPanel.
	 */
	public Panel2D() {
		// no layout management of JTextArea components
		jpanel.setLayout(null);
		
		initPixels();
		
		for (int i = 0; i < HEIGHT; i++) {
			Arrays.fill(bitmap[i], 0);
		}
		
		setDimensions();
	}
	
	// Setup the text area.
	private void initPixels() {
		text = new JTextArea(WIDTH, HEIGHT);
		text.setFont(mono);
		jpanel.add(text);
		// test initialization with backtick chars
		fillDefaultText();
		text.setBounds(
			0,
			0,
			WIDTH*(PANEL_FONT_SIZE-5) + 2, // adjust for thin-width font
			HEIGHT*(PANEL_FONT_SIZE+3) // 3 extra pixels per row
		);
		// Do not need user to interactively insert text.
		text.setFocusable(false);
		text.setVisible(true);
	}
	
	// Makes the JPanel the correct size for its JTextArea rows and columns.
	private void setDimensions() {
		jpanel.setPreferredSize(
			new Dimension(
				WIDTH*(PANEL_FONT_SIZE-5) + 2, // adjust for thin-width font
				HEIGHT*(PANEL_FONT_SIZE+3) // 3 extra pixels per row
			)
		);
	}
	
	/**
	 * This method changes the shapes we might want to animate.
	 * 
	 * @param actors
	 *   A collection of shapes to be updated every <code>getMillis()</code>.
	 */
	public void setActors(ArrayList<RoseCurve> actors) {
		this.actors = actors;
	}
	
	/* 
	 * Code that executes on a separate thread from our main program.
	 * We need this so that repainting frames for animation are not
	 * dependant on slow calculations used to draw in the frame.
	 */ 
	public void schedulePanelUpdates(int millis) {
		// For asynchronous updating of the text data within the JPanel.
		// If slow, these will get in the way of regular repainting, and then lagged animation.
		Panel2D self = this;
		// an example of writing a class inline where it is used.
		TimerTask updater = new TimerTask() {
			// WARNING: avoid keyword "new", to animate fast enough avoid memory operations!
			public void run() {
				
				// draw each actor
				for (RoseCurve wave : actors) {
					wave.draw();
				}
				
				// modify the main display
				jpanel.setEnabled(false); // disable jpanel interaction
				// notice using keyword "this" would refer to updater, not this Panel2D
				self.displayPixels();
				jpanel.setEnabled(true); // enable jpanel interaction
				
				// Clear the old data that was just used to display,
				// so we have a fresh display to draw with next time.
				// This is much faster than creating new objects every frame.
				for (int i = 0; i < HEIGHT; i++) {
						Arrays.fill(bitmap[i], 0);
				}
			}
		};
		
		// Use the util package, so that it places execution on a separate thread from the EDT.
		new Timer().schedule(updater, 0, millis);
	}
	
	// Takes care of changing all text inside JTextArea.
	private void displayPixels() {
		text.setText("");
		output.setLength(0);
		for (int row = HEIGHT - 1; row >= 0; row--) {
			for (int col = 0; col < WIDTH; col++) {
				output.append(getChar(col, row));
			}
			output.append('\n');
		}
		// for display
		text.setText(output.toString());
	}
	
	// Takes care of filling text inside JTextArea with a default character.
	private void fillDefaultText() {
		text.setText("");
		output.setLength(0);
		for (int row = HEIGHT - 1; row >= 0; row--) {
			for (int col = 0; col < WIDTH; col++) {
				output.append(DEFAULT_PIXEL);
			}
			output.append('\n');
		}
		// for display
		text.setText(output.toString());
	}
	
	/**
	 * Assign a char value to an <code>(x, y)</code> position. This also shifts
	 * coordinates so that the origin (0, 0) is in the centre of the display.
	 * 
	 * @param x 
	 *   The horizontal position inside the 2D array of JTextAreas.
	 * @param y 
	 *   The vertical position inside the 2D array of JTextAreas.
	 * @param pixelsIndex 
	 *   The index for <code>PIXELS</code> array to be stored within the 
	 *   <code>bitmap</code> 2D array.
	 */
	public void set(int x, int y, int pixelsIndex) {
		// shift the values of x and y so that (0, 0) is in the centre of the display
		x += WIDTH / 2;
		y += HEIGHT / 2;
		
		// check if the point (x, y) is outside the 2D array boundaries
		if (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT)
			bitmap[HEIGHT - y - 1][x] = pixelsIndex;
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
	 */
	public void drawLine(Point2D first, Point2D second, int pixelsIndex) {

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
		set(x1, y1, pixelsIndex);

		// The algorithm is simplified by ensuring slope m is always -1 < m < 1.
		if (dx > dy) {
			int error = dy - (dx >> 1);
			while (x1 != x2) {
				x1 += stepx;
				error += dy;
				if (error >= 0) {
					y1 += stepy;
					error -= dx;
				}
				set(x1, y1, pixelsIndex);
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
				set(x1, y1, pixelsIndex);
			}
		}
	} // End of drawLine method.
	
	// GETTER METHODS
	
	// Return the character used in position (x, y) within the jpanel array.
	private char getChar(int x, int y) {
		return PIXELS[ bitmap[HEIGHT - y - 1][x] ];
	}
	
	/**
	 * Get the JPanel <code>jpanel</code> container which holds the JTextArea.
	 * 
	 * @return
	 *  The JPanel that contains the 2D array of JTextAreas.
	 */
	public JPanel getJPanel() {
		return jpanel;
	}
	
	/**
	 * Get the <code>WIDTH</code>, the number of JTextArea character columns.
	 * 
	 * @return 
	 *   The number of JTextArea character columns in the JPanel.
	 */
	public static int getWidth() {
		return WIDTH;
	}
	
	/**
	 * Get the <code>HEIGHT</code>, the number of JTextArea character rows.
	 * 
	 * @return 
	 *   The number of JTextArea character rows in the JPanel.
	 */
	public static int getHeight() {
		return HEIGHT;
	}
	
}