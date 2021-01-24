import java.util.ArrayList;
import java.util.Random;

/**
 * This is the main program that demos how to use an AnimatedGUI instance.
 * In the example code given, there is a Box that moves its corners every frame
 * to show some animation controlled by math formulae.
 * 
 * @author Dr Russell Campbell
 */
public class Console {
	// the main application window for our program
	private static AnimatedGUI window;
	
	/**
	 * Program execution starts here.
	 * 
	 * additional shapes were added to satisfy the 3 shape requirement
	 * I tried all in my power to make it so that they would stay within the boundaries of the panel but it was to no avail.
	 * @param args 
	 *   An array of Strings that we can use to pass data into our program when it runs.
	 */
	public static void main(String[] args) {
		// our main application
		window = new AnimatedGUI();
		
		// create shapes we want to animate inside our application
		ArrayList<Shape> actors = new ArrayList<Shape>();
		actors.add(
			new Shape(17, 18, 25, 18, 30, 10, 3, 10)
		);
		actors.add(
			new Shape(16, 21, 25, 21, 30, 13, 3, 13)
		);
		actors.add(
			new Shape(15, 24, 25, 24, 30, 16, 3, 16)
		);

		// multiple method calls in one statement are executed from left to right 
		actors.get(0).setParent(window.getPanel2D());
		// the get method call returns the added Box, the setParent sets where all Boxes will draw 
		
		window.getPanel2D().setActors(actors);
		window.beginAnimation();
		// program will stop execution (and all its threads) when window is closed
		
	}
	
}
