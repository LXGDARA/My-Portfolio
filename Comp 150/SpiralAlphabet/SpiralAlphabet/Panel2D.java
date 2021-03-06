

import java.util.ArrayList;
import java.util.Arrays;

/**
 * We could have many different instances of 2D char arrays that could 
 * display different graphics we design. This class encapsulates (hides)
 * the confusion of working with the 2D char array directly elsewhere 
 * in our program. 
 * 
 * @author Dr Russell Campbell
 *
 */
public class Panel2D {
	public final static int WIDTH = 80;
	public final static int HEIGHT = 50;
	private char defaultChar = '`';
	private char[][] bitmap;
	//These four points were declared for the sake of drawing out axis
	Point2D left = new Point2D(-WIDTH, 0);
	Point2D up = new Point2D(0, -HEIGHT/2);
	Point2D right = new Point2D(WIDTH, 0);
	Point2D down = new Point2D(0, HEIGHT/2);

	/**
	 * This constructor will initialize the <code>bitmap</code> by
	 * creating a new 2D char array of dimensions <code>WIDTH</code>
	 * and <code>HEIGHT</code>.
	 */
	public Panel2D() {
		bitmap = new char[HEIGHT][WIDTH];
		clear();
	}
	
	/**
	 * This will fill the 2D char array with backtick <code>`</code>
	 * characters (usually under the tilde ~ in the top-left corner of
	 * a keyboard). We are using backticks to help if debugging is needed,
	 * because printing spaces means we would see nothing on screen.
	 */
	public void clear() {
		// every character in the screen is set to a default char
		for (int i = 0; i < bitmap.length; i++) {
			Arrays.fill(bitmap[i], defaultChar);
		}
	}

	/**
	 * This method swaps the order of the <code>x</code> and
	 * <code>y</code> values for us (so we don't have to swap
	 * them everywhere else in our code). 
	 * 
	 * @param x The horizontal position inside the bitmap.
	 * @param y The vertical position inside the bitmap.
	 * @return The character stored in the (x, y) position of bitmap.
	 */
	public char get(int x, int y) {
		return bitmap[y][x];
	}

	/**
	 * Similar to the {@link get} method, but assign a char
	 * value <code>c</code> to an <code>x</code> and <code>y</code>
	 * position in the bitmap.
	 * 
	 * @param x The horizontal position in the bitmap.
	 * @param y The vertical position in the bitmap.
	 * @param c The character stored in the bitmap.
	 */
	public void set(int x, int y, char c) {
		// shift coordinates to display in the center of 2D array
		x += WIDTH/2;
		y += HEIGHT/2;
		
		// check if the point is outside the screen boundaries
		if (0 <= x && x < WIDTH && 0 <= y && y < HEIGHT)
			bitmap[y][x] = c;
	}
	
	/**
	 * Actually print the bitmap to output. The rows are
	 * printed in backward order so that the output matches the
	 * axes of the 2D plane and the index values for bitmap.
	 */
	public void display() {
		StringBuilder output = new StringBuilder("");
		

		for (int y = HEIGHT-1; y >= 0; y--) {
			for (int x = 0; x < WIDTH; x++) {
				output.append(get(x, y));
			}
			output.append('\n');
		}
		System.out.print(output);

			
	}
	
	/**
	 * Avoids using floating-point arithmetic for a faster drawing
	 * of a line. Feel free to ask me about the math and why it works.
	 * 
	 * @param first The start position of the straight line.
	 * @param second The end position of the straight line.
	 * @param stroke The character used to draw the line.
	 */
	public void drawLine(int x1, int y1, int x2, int y2, char stroke) {

		// Bresenham's Line Algorithm
		byte stepx, stepy;

		int dx = x2 - x1;
		int dy = y2 - y1;

		// Simplify keeping track of distance by removing direction (sign)
		// from the amount of changes in position <code>(dx, dy)</code> for 
		// each step. Let the direction be taken care of with step variables.
		if (dy < 0) { dy = -dy; stepy = -1; } else { stepy = 1; }
		if (dx < 0) { dx = -dx; stepx = -1; } else { stepx = 1; }
		dy <<= 1; // dy is now 2*dy
		dx <<= 1; // dx is now 2*dx
		set(x1, y1, stroke);

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
				set(x1, y1, stroke);
			}
		} else { // but this means we may have to swap roles for dy and dx
			int error = dx - (dy >> 1);
			while (y1 != y2) {
				y1 += stepy;
				error += dx;
				if (error >= 0) {
					x1 += stepx;
					error -= dy;
				}
				set(x1, y1, stroke);
			}
		}
	} // end of drawLine method
	
	/**
	 * Move, and then draw this circle shape in the bitmap. This is Bresenham's
	 * circle-drawing algorithm, which you can read a short explanation about at
	 * <a href="https://www.geeksforgeeks.org/bresenhams-circle-drawing-algorithm/">GeeksForGeeks</a>.
	 */
	public void circle(Point2D centre, int r) {
		int x = 0;
		int y = r;
		int d = 3 - 2*r;
		drawSymmetricPositions(centre, x, y);
		while (y >= x) {
			x++;
			if (d > 0) {
				y--;
				d = d + 4*(x - y) + 10;
			} else {
				d = d + 4*x + 6;
			}
			drawSymmetricPositions(centre, x, y);
		}
	}
	/**
	 * this method sets up our axis to be drawn using four points.
	 */
	public void drawAxis() {
		for (int y = HEIGHT-1; y >= 0; y--) {
			drawLine(up.x, up.y, down.x, down.y, '|');
			for (int x = 0; x < WIDTH; x++) {
				drawLine(left.x, left.y, right.x, right.y, '-');
			}
		}		
	}
	
	/**
	 * this method allows for the labeling of each point in increasing alphabetic order.
	 * @param points we intend to pass in multiple points
	 */
	public void pointLabel(ArrayList<Point2D>points) {
		int i = 0;
		for(Point2D myPoints: points) {
			i++;
			set(myPoints.x, myPoints.y, (char)(64+i));
		}
	}
	
	private void drawSymmetricPositions(Point2D centre, int x, int y) {
		// the eight symmetric points on this circle of the point (x, y)
		set(centre.x + x, centre.y + y, '%');
		set(centre.x - x, centre.y + y, '%');
		set(centre.x + x, centre.y - y, '%');
		set(centre.x - x, centre.y - y, '%');
		set(centre.x + y, centre.y + x, '%');
		set(centre.x - y, centre.y + x, '%');
		set(centre.x + y, centre.y - x, '%');
		set(centre.x - y, centre.y - x, '%');
	}
}
