import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BoxLayoutFrame {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("BoxLayout Frame");
		
		frame.setLayout(
			new BoxLayout(
				frame.getContentPane(),
				BoxLayout.PAGE_AXIS
			)
		);
		// you could probably simplify this using loops and arrays
		frame.add(
			makePanel(
				Color.red,
				10,
				Component.CENTER_ALIGNMENT
			)
		);
		frame.add(
			makePanel(
				Color.blue,
				50,
				Component.LEFT_ALIGNMENT
			)
		);
		frame.add(
			makePanel(
				Color.yellow,
				100,
				Component.RIGHT_ALIGNMENT
			)
		);
		frame.add(
			makePanel(
				Color.green,
				200,
				Component.LEFT_ALIGNMENT
			)
		);
		frame.add(
			makePanel(
				Color.pink,
				500,
				Component.CENTER_ALIGNMENT
			)
		);
		
		frame.pack();
		frame.setVisible(true);
	}
	
	private static JPanel makePanel(Color col, int w, float a) {
		JPanel panel = new JPanel();
		panel.setBackground(col);
		panel.setAlignmentX(a);
		panel.setPreferredSize(new Dimension(w, 50));
		panel.setMaximumSize(panel.getPreferredSize());
		panel.setMinimumSize(panel.getPreferredSize());
		return panel;
	}
}