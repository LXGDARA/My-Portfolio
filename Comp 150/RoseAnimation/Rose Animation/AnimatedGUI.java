import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/*
 * NOTE: Canadian spelling "colour" vs American spelling "color"
 * NOTE: Canadian spelling "centre" vs American spelling "center"
 * Comments will use Canadian spelling; names of code parts will use American spelling.
 */

/**
 * This class manages the layout and higher-level functionality of 
 * controlling animation components.
 * 
 * @author Dr Russell Campbell
 */
public class AnimatedGUI {
	// CONSTANTS
	
	// displayed in the window's top bar
	private final static String TITLE_BAR = "Animation Example";
	// DO NOT CHANGE!
	private final static int MILLIS_PER_SECOND = 1000;
	// Not much need to set this above 30; slower computers might need to lower fps to 5 or less.
	private final static int FRAMES_PER_SECOND = 20;
	// Note that this is integer division here.
	private final static int MILLIS_PER_FRAME = MILLIS_PER_SECOND / FRAMES_PER_SECOND;
	
	// CLASS VARIABLES (static)
	
	// The main window that has the familiar x close button, window resizing buttons, etc.
	private static JFrame frame;
	// An extra JPanel to use default FlowLayout for horizontal centring of its child "display".
	private static JPanel wrapper;
	// Display will contain the 2D array of JTextAreas, each controlling an individual char.
	private static Panel2D display;
	
	/**
	 * Constructor to set up the GUI window heirarchy of objects, their layout, and dimensions.
	 */
	public AnimatedGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // the x button closes the program
		wrapper = new JPanel();
		display = new Panel2D();
		setGUILayout();
		
		// prep layout of JFrame and display it
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * Used by the main program to get animations started.
	 */
	public void beginAnimation() {
		scheduleFrameDrawing(); // creates a separate thread of execution managed by EDT
		display.schedulePanelUpdates(MILLIS_PER_FRAME); // creates a separate thread of execution apart from EDT
	}
	
	// Notice how it is nice to read method names that are self-explanatory.
	private void setGUILayout() {
		frame.setTitle(TITLE_BAR);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));
		
		// this automatically centres display within JFrame
		wrapper.add(display.getJPanel());
		
		// vertically centre layout
		frame.add(Box.createVerticalGlue());
		frame.add(wrapper);
	}
	
	/*
	 * Sets up regular redrawings of the frame for animation,
	 * but controlled by the EDT, which helps handle GUI events. 
	 * This is so the user has a better experience with
	 * responsive and fast interactivity.
	 */
	private void scheduleFrameDrawing() {
		// an example of writing a class inline where we use it
		ActionListener animator = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				// redraws the display according to changes in a connected Panel2D
				frame.repaint(); 
			}
		};
		
		// schedules the periodic execution of actionPerformed()
		new Timer(MILLIS_PER_FRAME, animator).start();
	}
	
	/**
	 * Get the Panel2D <code>display</code>.
	 * 
	 * @return 
	 *   The Panel2D <code>display</code> instance used by this AnimatedGUI.
	 */
	public Panel2D getPanel2D() {
		return display;
	}
}