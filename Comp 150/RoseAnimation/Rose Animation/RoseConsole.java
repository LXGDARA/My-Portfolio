import java.util.ArrayList;

/**
 * This is the main program that demos how to use an AnimatedGUI instance.
 * A Rose Curve shifts horizontally every frame to show some animation
 * controlled by math formulae.
 * 
 * @author Dr Russell Campbell
 */
public class RoseConsole {
	// The main application window for our program.
	private static AnimatedGUI window;
	
	/**
	 * Program execution starts here.
	 * 
	 * @param args 
	 *   An array of Strings that we can use to pass data into our program when it runs.
	 */
	public static void main(String[] args) {
		
		// Our main application.
		window = new AnimatedGUI();
		
		// Create shapes we want to animate inside our application.
		ArrayList<RoseCurve> actors = new ArrayList<RoseCurve>();
		actors.add(
			new RoseCurve(5)
		);
		
		actors.get(0).setParent(window.getPanel2D());
		
		window.getPanel2D().setActors(actors);
		window.beginAnimation();
		// Program will stop execution (and all its threads) when window is closed.
		
	}
}
