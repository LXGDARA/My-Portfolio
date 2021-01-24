import java.util.Random;
/**
 * 
 * @author Dr.Russel Campbell
 *This code does not belong to be I merely made use of what was provided by Dr. Russel Campbell 
 *Most Methods that I did not overhaul have not been commented on for the sake of not being redundant
 */
public class Shape {
	
		private static Panel2D parentPanel;
		
		
		private Point2D topLeft, topRight, bottomRight, bottomLeft;
		private int pixelsIndex;
		public static int colorsIndex;
		// velocity (or speed): the difference in x and y coordinates used in each update call
		private static int dx1, dy1, dx2, dy2, dx4, dy4, dx3, dy3; // chosen to modify topLeft and bottomRight points
		
		public int colorChange(int colorsIndex) {
			Random rand = new Random();
			this.colorsIndex = rand.nextInt(8);
			return colorsIndex;
		}
		
		/**
		 * Create an instance of a rectangle shape and set up a formula to 
		 * control its movement for animation.
		 * 
		 * Because the shape will be different the orientation of two of the points do NOT depend on the other two
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
		Shape(int x1, int y1, int x2, int y2, int x3, int  y3, int x4, int y4) {
			topLeft = new Point2D(x1, y1);
			topRight = new Point2D(x2, y1);
			bottomRight = new Point2D(x3, y3);
			bottomLeft = new Point2D(x4, y4);
			this.pixelsIndex = pixelsIndex;
			this.colorsIndex = colorsIndex;
			 // a default amount of movement controlling animation
			dx1 = dy1 = dx2 = dy2 = dy3 = dx3 = dx4 = dy4 = 1;
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
		public void moveCorners(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
			// clockwise order
			topLeft.translate(x1, y1);
			topRight.translate(x2, y1);
			bottomRight.translate(x3, y3);
			bottomLeft.translate(x4, y4);
		}
		/**
		 * On the condition that we randomly arrive on an even number index
		 * our pixelcharacter will change along with
		 * this is not applied to the color of the pixel itself
		 */
		void myColorUpdate() {
			Random rand = new Random();
			int myRand;
			myRand = rand.nextInt(7);
			switch(myRand) {
			case 0:{
				pixelsIndex  = myRand;
			}
			
			case 2:{
				pixelsIndex  = myRand;
			}
			case 4:{
				pixelsIndex  = myRand;
			}
			case 6:{
				pixelsIndex  = myRand;
			}
		}
			
			colorsIndex = rand.nextInt(8);
		}
		/**
		 * This method changes the direction of motion of each corner point if near 
		 * the edge of the display. Then it changes the position of each corner point
		 * by a small amount so that this shape seems to "move" for animation.
		 * 
		 * Additional statements ensuring the object stays inside the panel 
		 * Note they all need their own <code>dy<code> and <code>dx<code>
		 */
		public void update() {
			if (topLeft.x < 1 || parentPanel.getWidth() < topLeft.x+2)  dx1 *= -1;
			if (topLeft.y < 1 || parentPanel.getHeight() < topLeft.y+2) dy1 *= -1;
			if (topRight.x < 1 || parentPanel.getWidth() < topRight.x+2)  dx2 *= -1;
			if (topRight.y < 1 || parentPanel.getHeight() < topRight.y+2) dy2 *= -1;
			if (bottomRight.x < 1 || parentPanel.getWidth() < bottomRight.x+2)  dx3 *= -1;
			if (bottomRight.y < 1 || parentPanel.getHeight() < bottomRight.y+2) dy3 *= -1;
			if (bottomLeft.x < 1 || parentPanel.getWidth() < bottomLeft.x+2)  dx4 *= -1;
			if (bottomLeft.y < 1 || parentPanel.getHeight() < bottomLeft.y+2) dy4 *= -1;
			myColorUpdate();
			
			// NOTE: if dx1, dy1, dx2, dy2 have magnitude smaller than 1, then the range
			// tests above must be restricted further, or else Box points can get stuck
			moveCorners(dx1, dy1, dx2, dy2, dx3, dy3, dx4, dy4);

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
