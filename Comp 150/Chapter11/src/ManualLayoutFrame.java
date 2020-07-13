import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ManualLayoutFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Manual Layout Frame");
		
		frame.setLayout(null);
		
		JPanel
			bluePanel = new JPanel(),
			redPanel = new JPanel(),
			greenPanel = new JPanel(),
			bckgrnd = new JPanel();
		bluePanel.setBackground(Color.blue);
		redPanel.setBackground(Color.red);
		greenPanel.setBackground(Color.green);
		bckgrnd.setBackground(Color.white);
		
		bluePanel.setBounds(100, 100, 100, 100);
		redPanel.setBounds(50, 175, 400, 200);
		greenPanel.setBounds(150, 100, 75, 75);
		
		// notice the order of adding determines layering (z-order)
		frame.add(greenPanel);
		frame.add(bluePanel);
		frame.add(redPanel);
		frame.add(bckgrnd);
		
		/*
		// Otherwise, you can change the order of stacking components.
		// Lower number component layers on top of higher numbered components.
		// The range of integer z values is in interval [ 0, frame.getComponentCount() )
		frame.setComponentZOrder(bluePanel, 0);
		frame.setComponentZOrder(redPanel, 1); // must be called in order of z value or you get drawing bugs
		frame.setComponentZOrder(greenPanel, 2);
		frame.setComponentZOrder(bckgrnd, 3); // last component stretches size to fit parent
		// this is hacky---swing was meant to be used with layout managers
		*/
		
		frame.pack();
		frame.setVisible(true);
		
		frame.setSize(500, 500);
	}
}