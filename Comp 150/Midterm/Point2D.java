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
	 *   The horizontal position of this point.
	 * @param y
	 *   The vertical position of this point.
	 */
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Write this method to calculate the distance of this point to the origin.
	 * You do not need to use any parameters.
	 *
	 * @return
	 *   The distance of this point from the origin.
	 */
	// PART 5a (6 marks)
	public double dist2Origin() {
		// with distance formula FROM ORIGIN, we have just x + y
		double myDist;
		myDist = x + y;
		return myDist;
	}
	
	/**
	 * Write this method to rotate this coordinate point 90 degrees about the origin
	 * in a counterclockwise direction. You do not need to use any parameters.
	 */
	// PART 7a (6 marks)
	public void rotate90() {
		// Consider swapping and/or negating the x and y values.
		// You will need a temporary holder variable to swap the x and y values.
		// (try it on paper first and convince yourself for a simple coordinate)
		int myHold;
		myHold = x;
		x = y;
		x*=-1;
		y = myHold;
	}
	
	/**
	 * This method calculates a rectangular coordinate point from the current
	 * <code>(x, y)</code> values used as a polar coordinate <code>(r, theta)</code>.
	 */
	public void polar2XY() {
		int x = (int) (this.x * Math.cos(Math.PI*this.y/180));
		int y = (int) (this.x * Math.sin(Math.PI*this.y/180));
		this.x = x;
		this.y = y;
	}
}
