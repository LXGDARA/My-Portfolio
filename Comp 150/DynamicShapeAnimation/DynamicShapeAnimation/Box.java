/**
 * A shape of a rectangle that can be drawn in its <code>Panel2D parentPanel</code>.
 * 
 * @author Dr Russell Campbell
 */
class Box {
	// only ONE Panel2D shared by all
	// NOTE: this is a simplification and not a good code design choice
	private static Panel2D parentPanel;
	
	// INSTANCE VARIABLES (different values for *every* Box instance we create)
	
	private Point2D topLeft, topRight, bottomRight, bottomLeft;
	private int pixelsIndex, colorsIndex;
	// velocity (or speed): the difference in x and y coordinates used in each update call
	private int dx1, dy1, dx2, dy2; // chosen to modify topLeft and bottomRight points
	
	/**
	 * Create an instance of a rectangle shape and set up a formula to 
	 * conrol its movement for animation.
	 * 
	 * @param x1 
	 *   Horizontal position of the top-left corner.
	 * @param y1 
	 *   Vertical position of the top-left corner.
	 * @param x2 
	 *   Horizontal position of the bottom-right corner.
	 * @param y2 
	 *   Vertical position of the bottom-right corner.
	 * @param pixelsIndex
	 *   The index of the character used to draw the rectangle.
	 * @param colorsIndex
	 *   The index of the colour used to draw the rectangle.
	 */
	Box(int x1, int y1, int x2, int y2, int pixelsIndex, int colorsIndex) {
		topLeft = new Point2D(x1, y1);
		topRight = new Point2D(x2, y1);
		bottomRight = new Point2D(x2, y2);
		bottomLeft = new Point2D(x1, y2);
		this.pixelsIndex = pixelsIndex;
		this.colorsIndex = colorsIndex;
		 // a default amount of movement controllig animation
		dx1 = dy1 = dx2 = dy2 = 1;
	}

	/**
	 * Set the <code>parentPanel</code> to draw inside for later.
	 * 
	 * @param parentPanel
	 *   An instance of a <code>Panel2D</code> to be used for drawing inside later.
	 */
	public void setParent(Panel2D parentPanel) {
		this.parentPanel = parentPanel;
	}
	
	/**
	 * Translates (moves) the two corner points of this box.
	 * 
	 * @param x1
	 *   The horizontal amount of translation to the top left corner.
	 * 
	 * @param y1
	 *   The vertical amount of translation to the top left corner.
	 *   
	 * @param x2
	 *   The horizontal amount of translation to the bottom right corner.
	 * 
	 * @param y2
	 *   The vertical amount of translation to the bottom right corner.
	 */
	public void moveCorners(int x1, int y1, int x2, int y2) {
		// clockwise order
		topLeft.translate(x1, y1);
		topRight.translate(x2, y1);
		bottomRight.translate(x2, y2);
		bottomLeft.translate(x1, y2);
	}
	
	/**
	 * This method changes the direction of motion of each corner point if near 
	 * the edge of the display. Then it changes the position of each corner point
	 * by a small amount so that this shape seems to "move" for animation.
	 */
	public void update() {
		if (topLeft.x < 1 || parentPanel.getWidth() < topLeft.x+2)  dx1 *= -1;
		if (topLeft.y < 1 || parentPanel.getHeight() < topLeft.y+2) dy1 *= -1;
		if (bottomRight.x < 1 || parentPanel.getWidth() < bottomRight.x+2)  dx2 *= -1;
		if (bottomRight.y < 1 || parentPanel.getHeight() < bottomRight.y+2) dy2 *= -1;
		// NOTE: if dx1, dy1, dx2, dy2 have magnitude smaller than 1, then the range
		// tests above must be restricted further, or else Box points can get stuck
		moveCorners(dx1, dy1, dx2, dy2);
	}
	
	/**
	 * Draws this rectangle shape in its <code>parentPanel</code>.
	 */
	public void draw() {
		update();
		parentPanel.drawLine(topLeft, topRight, pixelsIndex, colorsIndex);
		parentPanel.drawLine(topRight, bottomRight, pixelsIndex, colorsIndex);
		parentPanel.drawLine(bottomRight, bottomLeft, pixelsIndex, colorsIndex);
		parentPanel.drawLine(bottomLeft, topLeft, pixelsIndex, colorsIndex);
	}
	
}
