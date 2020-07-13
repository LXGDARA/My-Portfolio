//2
/**
 * This Rose Curve is a polar coordinate function that draws a flower
 * with four symmetrical petals and animates it using timed rotation
 * within its <code>Panel2D parentPanel</code>.
 * 
 * @author Dr Russell Campbell
 */
public class RoseCurve {
	private final static int REV = 360; // a full revolution in degrees
	private final static int DELTA = 5; // increment step of angle theta (change in value)
	private final static double NUM_OF_REVS = 1.5; // number of revolutions
	
	private static Panel2D parentPanel;
	private int pixelsIndex; // the character used to draw the curve
	
	private double radius; // controls the size of the Rose Curve
	private double shift; // rotation speed of the Rose Curve
	private Point2D origin = new Point2D(0, 0);
	
	// PART 1 (5 marks) SEARCH FOR "Rewrite" AND COMPLETE EACH INSTRUCTION (there are 5 of them)
	// (1) Rewrite the calculation for the number of elements so that it is easier to read.
	private Point2D[] points = new Point2D[(int)(REV/DELTA*NUM_OF_REVS + 1)];
	
	/**
	 * Create an instance of a Rose Curve and initializes instance variables to 
	 * conrol its size and rotation of movement for animation.
	 * 
	 * @param pixelsIndex
	 *   The index within the PIXELS array of the character used to draw the curve.
	 */
	public RoseCurve(int pixelsIndex) {
		this.pixelsIndex = pixelsIndex;
		
		radius = 0.95; // in percentage of HALF of the display height
		shift = 0.0; // no horizontal shift at start
		
		// (2) Rewrite the calculation for the stop condition so that it is easier to read.
		for (int i = 0; i < REV/DELTA*NUM_OF_REVS + 1; i++) {
			points[i] = new Point2D(0, 0); // a new Point2D instance for every element in the array
		}
	}

	/**
	 * Set the <code>parentPanel</code> to draw inside for later. Then the radius of
	 * this Rose Curve is also set to a size that will always fit the parentPanel 
	 * display HEIGHT.
	 * 
	 * @param parentPanel
	 *   An instance of a <code>Panel2D</code> to be used for drawing inside later.
	 */
	public void setParent(Panel2D parentPanel) {
		this.parentPanel = parentPanel;
		radius *= parentPanel.getHeight()/2; // relative to HALF of display HEIGHT
	}
	
	/**
	 * This method changes the rotational shift of the curve each time step so that
	 * it seems to "move" for animation.
	 */
	public void update() {
		shift += 0.75;
	}
	
	/**
	 * Follow the instructions in the comments for the  parts.
	 */
	public void draw() {
		update();
		int width = Panel2D.getWidth(); // this value is 100
		int height = Panel2D.getHeight(); // this value is 50

		Point2D negW = new Point2D(-50,0);
		Point2D negH = new Point2D(0,-25);
		Point2D posW = new Point2D(50,0);
		Point2D posH = new Point2D(0,25);
		// PART 2 (5 marks)
		// Change so that axes are displayed using drawLine instead.
		//for (int i = -width/2; i < width/2; i++)
			parentPanel.drawLine(negW, posW, 2);
		//for (int i = -height/2; i < height/2; i++)
			parentPanel.drawLine(negH, posH, 3);
		
		// ------------- calculates points to draw a Rose Curve -------------
		// PART 4 (4 marks)
		// Change the code so that four times as many points help to draw the Rose Curve smoother.
		int k = 0; // counter for the points array
		// (3) Rewrite the calculation for the stop condition and increment so that they are easier to read.
		for (int theta = 0; theta < REV*NUM_OF_REVS + DELTA; theta += DELTA) {
			points[k].x = (int) (Math.sin(3*Math.PI*(theta-shift)/(NUM_OF_REVS*180)) * radius);
			points[k].y = theta;
			points[k++].polar2XY(); // converts to rectangular coordinates
		}
		
		// PART 3 (6 marks)
		// Draw the lines connecting the calculated points for the Rose Curve.
		// NOTE: you will need both i and (i+1) element access.
		// (4) Rewrite the calculation for the stop condition so that it is easier to read.
		for (int i = 0; i < REV/DELTA*NUM_OF_REVS; i++) {
			parentPanel.drawLine(points[i], points[i++], 5);
		}
		
		// PART 5b (6 marks)
		// (5a: first complete the Point2D method dist2Origin (see Point2D.java))
		// Find a point with the maximum r value (distance from the origin) for the first petal.
		double maxR = 0;
		Point2D maxPt = null; //going to use this object as the maximum point
		// (5) Rewrite the calculation for the stop condition so that it is easier to read.
		// NOTE: the first petal uses the first quarter of all points.
		for (int i = 0; i < REV/DELTA*NUM_OF_REVS/4; i++) {
			if(points[i].dist2Origin() > maxR) {
				maxPt = points[i];
				maxR = maxPt.dist2Origin();
			}
		}
		
		// PART 6 (6 marks)
		// Draw a line from the origin to the point with maximum r value with an '=' character.
		parentPanel.drawLine(origin, maxPt, 6);
		
		// PART 7b (6 marks)
		// (7a: first complete the Point2D method rotate90 (see Point2D.java))
		// Use rotation by 90 degrees THREE TIMES to draw the other three petal lines.
		for(int i = 0; i<=2; i++) {
			maxPt.rotate90();
			parentPanel.drawLine(origin, maxPt, 6);
		}
		
		// DO NOT CHANGE
		// This draws all the points in the points array with an '@' character.
		for (Point2D pt : points) {
			parentPanel.set(pt.x, pt.y, 4);
		}
	}
	
}
