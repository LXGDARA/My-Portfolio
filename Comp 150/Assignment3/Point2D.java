/**
* A coordinate point in the 2D plane.
* 
* @author Dr Russell Campbell
*/
public class Point2D {
	public int x;
	public int y;
	
	/**
	* You need to provide an x and y value to create a point.
	* 
	* @param x
	*   The horizontal coordinate value.
	* 
	* @param y
	*   The vertical coordinate value.
	*   
	*/
	public Point2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Calculates the distance to the origin of this Point2D instance.
	 *
	 * @return
	 *   The distance to the origin, truncated to an integer value.
	 */
	public int dist2Origin() {
		return (int) (Math.sqrt(x*x + y*y));
	}
}
