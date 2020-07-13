import java.util.Random;


/**
 * A coordinate point in the 2D plane.
 * 
 * @author Dr Russell Campbell
 */
public class Point2D {
	public int x = 0;
	public int y = 0;
	
	/**
	 * A simple constructor for creating instances of a coordinate point in 2D space.
	 *
	 * @param x
	 *   The horizontal position of this coordinate.
	 * @param y
	 *   The vertical position of this coordinate.
	 */
	Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * This method modifies the <code>x</code> and <code>y</code> values.
	 * 
	 * edits were made in an attempt to change the "animation' of the shape itself
	 * @param dx
	 *   Used to add <code>dx</code> to <code>x</code>.
	 * 
	 * @param dy
	 *   Used to add <code>dy</code> to <code>y</code>.
	 */
	public void translate(int dx, int dy) {
		x += (dx);
		y += (1 - Math.sqrt(dx));
	}
}
